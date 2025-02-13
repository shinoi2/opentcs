// SPDX-FileCopyrightText: The openTCS Authors
// SPDX-License-Identifier: MIT
package org.opentcs.kernel.extensions.servicewebapi.v1.converter;

import static java.util.Objects.requireNonNull;

import jakarta.inject.Inject;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.opentcs.access.to.model.BoundingBoxCreationTO;
import org.opentcs.access.to.model.VehicleCreationTO;
import org.opentcs.data.model.Vehicle;
import org.opentcs.kernel.extensions.servicewebapi.v1.binding.plantmodel.VehicleTO;
import org.opentcs.util.Colors;

/**
 * Includes the conversion methods for all Vehicle classes.
 */
public class VehicleConverter {

  private final PropertyConverter pConverter;

  @Inject
  public VehicleConverter(PropertyConverter pConverter) {
    this.pConverter = requireNonNull(pConverter, "pConverter");
  }

  public List<VehicleCreationTO> toVehicleCreationTOs(List<VehicleTO> vehicles) {
    return vehicles.stream()
        .map(
            vehicle -> new VehicleCreationTO(vehicle.getName())
                .withProperties(pConverter.toPropertyMap(vehicle.getProperties()))
                .withBoundingBox(new BoundingBoxCreationTO(vehicle.getLength(), 1000, 1000))
                .withEnergyLevelThresholdSet(
                    new VehicleCreationTO.EnergyLevelThresholdSet(
                        vehicle.getEnergyLevelCritical(),
                        vehicle.getEnergyLevelGood(),
                        vehicle.getEnergyLevelSufficientlyRecharged(),
                        vehicle.getEnergyLevelFullyRecharged()
                    )
                )
                .withMaxVelocity(vehicle.getMaxVelocity())
                .withMaxReverseVelocity(vehicle.getMaxReverseVelocity())
                .withLayout(
                    new VehicleCreationTO.Layout(
                        Colors.decodeFromHexRGB(vehicle.getLayout().getRouteColor())
                    )
                )
        )
        .collect(Collectors.toCollection(ArrayList::new));
  }

  public List<VehicleTO> toVehicleTOs(Set<Vehicle> vehicles) {
    return vehicles.stream()
        .map(
            vehicle -> new VehicleTO(vehicle.getName())
                .setLength((int) vehicle.getBoundingBox().getLength())
                .setEnergyLevelCritical(
                    vehicle.getEnergyLevelThresholdSet().getEnergyLevelCritical()
                )
                .setEnergyLevelGood(vehicle.getEnergyLevelThresholdSet().getEnergyLevelGood())
                .setEnergyLevelFullyRecharged(
                    vehicle.getEnergyLevelThresholdSet().getEnergyLevelFullyRecharged()
                )
                .setEnergyLevelSufficientlyRecharged(
                    vehicle.getEnergyLevelThresholdSet().getEnergyLevelSufficientlyRecharged()
                )
                .setMaxVelocity(vehicle.getMaxVelocity())
                .setMaxReverseVelocity(vehicle.getMaxReverseVelocity())
                .setLayout(
                    new VehicleTO.Layout()
                        .setRouteColor(Colors.encodeToHexRGB(vehicle.getLayout().getRouteColor()))
                )
                .setProperties(pConverter.toPropertyTOs(vehicle.getProperties()))
        )
        .sorted(Comparator.comparing(VehicleTO::getName))
        .collect(Collectors.toList());
  }
}
