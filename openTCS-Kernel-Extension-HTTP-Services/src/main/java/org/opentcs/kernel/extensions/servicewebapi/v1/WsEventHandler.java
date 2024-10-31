/**
 * Copyright (c) The openTCS Authors.
 *
 * This program is free software and subject to the MIT license. (For details,
 * see the licensing information (LICENSE.txt) you should have received with
 * this copy of the software.)
 */
package org.opentcs.kernel.extensions.servicewebapi.v1;

import java.io.IOException;
import static java.util.Objects.requireNonNull;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import javax.inject.Inject;
import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.opentcs.access.Kernel;
import org.opentcs.access.KernelStateTransitionEvent;
import org.opentcs.components.Lifecycle;
import org.opentcs.customizations.ApplicationEventBus;
import org.opentcs.data.TCSObject;
import org.opentcs.data.TCSObjectEvent;
import org.opentcs.data.model.Vehicle;
import org.opentcs.data.order.TransportOrder;
import org.opentcs.data.peripherals.PeripheralJob;
import org.opentcs.kernel.extensions.servicewebapi.JsonBinder;
import org.opentcs.kernel.extensions.servicewebapi.ServiceWebApiConfiguration;
import org.opentcs.kernel.extensions.servicewebapi.v1.binding.getevents.OrderStatusMessage;
import org.opentcs.kernel.extensions.servicewebapi.v1.binding.getevents.PeripheralJobStatusMessage;
import org.opentcs.kernel.extensions.servicewebapi.v1.binding.getevents.StatusMessage;
import org.opentcs.kernel.extensions.servicewebapi.v1.binding.getevents.VehicleStatusMessage;
import org.opentcs.util.event.EventHandler;
import org.opentcs.util.event.EventSource;

@WebSocket
public class WsEventHandler implements Lifecycle, EventHandler
{
  private final JsonBinder jsonBinder;
  private final Set<Session> sessions = new CopyOnWriteArraySet<>();

  /**
   * The interface configuration.
   */
  private final ServiceWebApiConfiguration configuration;
  /**
   * Where we register for application events.
   */
  private final EventSource eventSource;
  /**
   * Whether this instance is initialized.
   */
  private boolean initialized;
  /**
   * Whether we are collecting events.
   */
  private boolean eventCollectingOn;

  @Inject
  public WsEventHandler(JsonBinder jsonBinder,
                        ServiceWebApiConfiguration configuration,
                        @ApplicationEventBus EventSource eventSource) {
    this.jsonBinder = jsonBinder;
    this.configuration = requireNonNull(configuration, "configuration");
    this.eventSource = requireNonNull(eventSource, "eventSource");
  }

  @OnWebSocketConnect
  public void onConnect(Session session) {
    sessions.add(session);
  }

  @OnWebSocketClose
  public void onClose(Session user, int statusCode, String reason) {
    sessions.remove(user);
  }

  @OnWebSocketMessage
  public void onMessage(Session user, String message) {
  }

  @Override
  public void initialize() {
    if (isInitialized()) {
      return;
    }

    eventSource.subscribe(this);

    initialized = true;
  }

  @Override
  public boolean isInitialized() {
    return initialized;
  }

  @Override
  public void terminate() {
    if (!isInitialized()) {
      return;
    }

    eventSource.unsubscribe(this);

    initialized = false;
  }

  @Override
  public void onEvent(Object event) {
    if (event instanceof KernelStateTransitionEvent) {
      handleStateTransition((KernelStateTransitionEvent) event);
    }

    if (sessions.isEmpty()) {
      return;
    }

    if (event instanceof TCSObjectEvent) {
      handleObjectEvent((TCSObjectEvent) event);
    }
  }

  private void handleStateTransition(KernelStateTransitionEvent event) {
    boolean wasOn = eventCollectingOn;
    eventCollectingOn
        = event.getEnteredState() == Kernel.State.OPERATING && event.isTransitionFinished();

    // When switching collecting of events on, ensure we start clean.
    if (!wasOn && eventCollectingOn) {
      sessions.forEach(Session::close);
    }
  }

  private void broadcastMessage(StatusMessage message) {
    sessions.stream()
        .filter(Session::isOpen)
        .forEach(session -> {
          try {
            session.getRemote().sendString(jsonBinder.toJson(message));
          }
          catch (IOException e) {
            e.printStackTrace();
          }
        });
  }

  private void handleObjectEvent(TCSObjectEvent event) {
    TCSObject<?> object = event.getCurrentOrPreviousObjectState();
    if (object instanceof TransportOrder) {
      broadcastMessage(
          OrderStatusMessage.fromTransportOrder((TransportOrder) object, 0));
    }
    else if (object instanceof Vehicle) {
      broadcastMessage(
          VehicleStatusMessage.fromVehicle((Vehicle) object, 0));
    }
    else if (object instanceof PeripheralJob) {
      broadcastMessage(
          PeripheralJobStatusMessage.fromPeripheralJob((PeripheralJob) object, 0));
    }
  }
}
