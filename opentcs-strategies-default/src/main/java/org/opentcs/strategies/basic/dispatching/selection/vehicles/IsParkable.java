// SPDX-FileCopyrightText: The openTCS Authors
// SPDX-License-Identifier: MIT
package org.opentcs.strategies.basic.dispatching.selection.vehicles;

import static java.util.Objects.requireNonNull;

import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import org.opentcs.components.kernel.services.TCSObjectService;
import org.opentcs.data.TCSObjectReference;
import org.opentcs.data.model.Point;
import org.opentcs.data.model.Vehicle;
import org.opentcs.data.order.OrderConstants;
import org.opentcs.strategies.basic.dispatching.selection.ParkVehicleSelectionFilter;

/**
 * Filters vehicles that are parkable.
 */
public class IsParkable
    implements
      ParkVehicleSelectionFilter {

  /**
   * The object service.
   */
  private final TCSObjectService objectService;

  /**
   * Creates a new instance.
   *
   * @param objectService The object service.
   */
  @Inject
  public IsParkable(TCSObjectService objectService) {
    this.objectService = requireNonNull(objectService, "objectService");
  }

  @Override
  public Collection<String> apply(Vehicle vehicle) {
    return parkable(vehicle) ? new ArrayList<>() : Arrays.asList(getClass().getName());
  }

  private boolean parkable(Vehicle vehicle) {
    return vehicle.getIntegrationLevel() == Vehicle.IntegrationLevel.TO_BE_UTILIZED
        && vehicle.hasProcState(Vehicle.ProcState.IDLE)
        && vehicle.hasState(Vehicle.State.IDLE)
        && !isParkingPosition(vehicle.getCurrentPosition())
        && vehicle.getOrderSequence() == null
        && hasAllowedOrderTypesForParking(vehicle);
  }

  private boolean isParkingPosition(TCSObjectReference<Point> positionRef) {
    if (positionRef == null) {
      return false;
    }

    Point position = objectService.fetchObject(Point.class, positionRef);
    return position.isParkingPosition();
  }

  private boolean hasAllowedOrderTypesForParking(Vehicle vehicle) {
    return vehicle.getAllowedOrderTypes().contains(OrderConstants.TYPE_PARK)
        || vehicle.getAllowedOrderTypes().contains(OrderConstants.TYPE_ANY);
  }
}
