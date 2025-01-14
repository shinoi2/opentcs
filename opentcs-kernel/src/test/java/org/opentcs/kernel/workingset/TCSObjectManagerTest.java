// SPDX-FileCopyrightText: The openTCS Authors
// SPDX-License-Identifier: MIT
package org.opentcs.kernel.workingset;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentcs.data.TCSObjectEvent;
import org.opentcs.data.model.Point;
import org.opentcs.util.event.EventBus;
import org.opentcs.util.event.SimpleEventBus;

/**
 * Unit tests for {@link TCSObjectManager}.
 */
class TCSObjectManagerTest {

  private TCSObjectRepository objectRepo;
  private EventBus eventBus;
  private TCSObjectManager objectManager;

  @BeforeEach
  void setUp() {
    objectRepo = new TCSObjectRepository();
    eventBus = new SimpleEventBus();
    objectManager = new TCSObjectManager(objectRepo, eventBus);
  }

  @Test
  void emitEvent() {
    List<Object> receivedEvents = new ArrayList<>();
    eventBus.subscribe(event -> receivedEvents.add(event));
    Point someObject = new Point("Point-00001").withType(Point.Type.HALT_POSITION);

    assertThat(receivedEvents, hasSize(0));

    objectManager.emitObjectEvent(
        someObject.withType(Point.Type.PARK_POSITION),
        someObject,
        TCSObjectEvent.Type.OBJECT_MODIFIED
    );

    assertThat(receivedEvents, hasSize(1));
  }

}
