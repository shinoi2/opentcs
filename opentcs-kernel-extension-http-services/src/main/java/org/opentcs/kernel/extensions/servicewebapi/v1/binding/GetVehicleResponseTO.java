// SPDX-FileCopyrightText: The openTCS Authors
// SPDX-License-Identifier: MIT
package org.opentcs.kernel.extensions.servicewebapi.v1.binding;

import static java.util.Objects.requireNonNull;

import jakarta.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.opentcs.data.TCSObjectReference;
import org.opentcs.data.model.TCSResourceReference;
import org.opentcs.data.model.Vehicle;
import org.opentcs.data.model.Vehicle.IntegrationLevel;
import org.opentcs.data.model.Vehicle.ProcState;
import org.opentcs.data.model.Vehicle.State;
import org.opentcs.util.annotations.ScheduledApiChange;

/**
 */
public class GetVehicleResponseTO {

  private String name;

  private Map<String, String> properties = new HashMap<>();

  private int length;

  private int energyLevelGood;

  private int energyLevelCritical;

  private int energyLevelSufficientlyRecharged;

  private int energyLevelFullyRecharged;

  private int energyLevel;

  private IntegrationLevel integrationLevel = IntegrationLevel.TO_BE_RESPECTED;

  private boolean paused;

  private ProcState procState = ProcState.IDLE;

  private String transportOrder;

  private String currentPosition;

  private PrecisePosition precisePosition;

  private double orientationAngle;

  private State state = State.UNKNOWN;

  private List<List<String>> allocatedResources = new ArrayList<>();

  private List<List<String>> claimedResources = new ArrayList<>();

  private List<String> allowedOrderTypes = new ArrayList<>();

  private String envelopeKey;

  public GetVehicleResponseTO() {
  }

  public String getName() {
    return name;
  }

  public GetVehicleResponseTO setName(String name) {
    this.name = requireNonNull(name, "name");
    return this;
  }

  public int getLength() {
    return length;
  }

  public GetVehicleResponseTO setLength(int length) {
    this.length = length;
    return this;
  }

  public int getEnergyLevelGood() {
    return energyLevelGood;
  }

  public GetVehicleResponseTO setEnergyLevelGood(int energyLevelGood) {
    this.energyLevelGood = energyLevelGood;
    return this;
  }

  public int getEnergyLevelCritical() {
    return energyLevelCritical;
  }

  public GetVehicleResponseTO setEnergyLevelCritical(int energyLevelCritical) {
    this.energyLevelCritical = energyLevelCritical;
    return this;
  }

  public int getEnergyLevelSufficientlyRecharged() {
    return energyLevelSufficientlyRecharged;
  }

  public GetVehicleResponseTO setEnergyLevelSufficientlyRecharged(
      int energyLevelSufficientlyRecharged
  ) {
    this.energyLevelSufficientlyRecharged = energyLevelSufficientlyRecharged;
    return this;
  }

  public int getEnergyLevelFullyRecharged() {
    return energyLevelFullyRecharged;
  }

  public GetVehicleResponseTO setEnergyLevelFullyRecharged(int energyLevelFullyRecharged) {
    this.energyLevelFullyRecharged = energyLevelFullyRecharged;
    return this;
  }

  public int getEnergyLevel() {
    return energyLevel;
  }

  public GetVehicleResponseTO setEnergyLevel(int energyLevel) {
    this.energyLevel = energyLevel;
    return this;
  }

  public IntegrationLevel getIntegrationLevel() {
    return integrationLevel;
  }

  public GetVehicleResponseTO setIntegrationLevel(IntegrationLevel integrationLevel) {
    this.integrationLevel = requireNonNull(integrationLevel, "integrationLevel");
    return this;
  }

  public boolean isPaused() {
    return paused;
  }

  public GetVehicleResponseTO setPaused(boolean paused) {
    this.paused = paused;
    return this;
  }

  public ProcState getProcState() {
    return procState;
  }

  public GetVehicleResponseTO setProcState(Vehicle.ProcState procState) {
    this.procState = requireNonNull(procState, "procState");
    return this;
  }

  public String getTransportOrder() {
    return transportOrder;
  }

  public GetVehicleResponseTO setTransportOrder(String transportOrder) {
    this.transportOrder = transportOrder;
    return this;
  }

  public String getCurrentPosition() {
    return currentPosition;
  }

  public GetVehicleResponseTO setCurrentPosition(String currentPosition) {
    this.currentPosition = currentPosition;
    return this;
  }

  public PrecisePosition getPrecisePosition() {
    return precisePosition;
  }

  public GetVehicleResponseTO setPrecisePosition(PrecisePosition precisePosition) {
    this.precisePosition = precisePosition;
    return this;
  }

  public double getOrientationAngle() {
    return orientationAngle;
  }

  public GetVehicleResponseTO setOrientationAngle(double orientationAngle) {
    this.orientationAngle = orientationAngle;
    return this;
  }

  public State getState() {
    return state;
  }

  public GetVehicleResponseTO setState(State state) {
    this.state = requireNonNull(state, "state");
    return this;
  }

  public List<List<String>> getAllocatedResources() {
    return allocatedResources;
  }

  public GetVehicleResponseTO setAllocatedResources(List<List<String>> allocatedResources) {
    this.allocatedResources = requireNonNull(allocatedResources, "allocatedResources");
    return this;
  }

  public List<List<String>> getClaimedResources() {
    return claimedResources;
  }

  public GetVehicleResponseTO setClaimedResources(List<List<String>> claimedResources) {
    this.claimedResources = requireNonNull(claimedResources, "claimedResources");
    return this;
  }

  public Map<String, String> getProperties() {
    return properties;
  }

  public GetVehicleResponseTO setProperties(Map<String, String> properties) {
    this.properties = requireNonNull(properties, "properties");
    return this;
  }

  public List<String> getAllowedOrderTypes() {
    return allowedOrderTypes;
  }

  public GetVehicleResponseTO setAllowedOrderTypes(List<String> allowedOrderTypes) {
    this.allowedOrderTypes = requireNonNull(allowedOrderTypes, "allowedOrderTypes");
    return this;
  }

  @ScheduledApiChange(when = "7.0", details = "Envelope key will become non-null.")
  @Nullable
  public String getEnvelopeKey() {
    return envelopeKey;
  }

  @ScheduledApiChange(when = "7.0", details = "Envelope key will become non-null.")
  public GetVehicleResponseTO setEnvelopeKey(
      @Nullable
      String envelopeKey
  ) {
    this.envelopeKey = envelopeKey;
    return this;
  }

  /**
   * Creates a <Code>VehicleState</Code> instance from a <Code>Vehicle</Code> instance.
   *
   * @param vehicle The vehicle whose properties will be used to create a <Code>VehicleState</Code>
   * instance.
   * @return A new <Code>VehicleState</Code> instance filled with data from the given vehicle.
   */
  public static GetVehicleResponseTO fromVehicle(Vehicle vehicle) {
    if (vehicle == null) {
      return null;
    }
    GetVehicleResponseTO vehicleState = new GetVehicleResponseTO();
    vehicleState.setName(vehicle.getName());
    vehicleState.setProperties(vehicle.getProperties());
    vehicleState.setLength((int) vehicle.getBoundingBox().getLength());
    vehicleState.setEnergyLevelCritical(
        vehicle.getEnergyLevelThresholdSet().getEnergyLevelCritical()
    );
    vehicleState.setEnergyLevelGood(vehicle.getEnergyLevelThresholdSet().getEnergyLevelGood());
    vehicleState.setEnergyLevelSufficientlyRecharged(
        vehicle.getEnergyLevelThresholdSet().getEnergyLevelSufficientlyRecharged()
    );
    vehicleState.setEnergyLevelFullyRecharged(
        vehicle.getEnergyLevelThresholdSet().getEnergyLevelFullyRecharged()
    );
    vehicleState.setEnergyLevel(vehicle.getEnergyLevel());
    vehicleState.setIntegrationLevel(vehicle.getIntegrationLevel());
    vehicleState.setPaused(vehicle.isPaused());
    vehicleState.setProcState(vehicle.getProcState());
    vehicleState.setTransportOrder(nameOfNullableReference(vehicle.getTransportOrder()));
    vehicleState.setCurrentPosition(nameOfNullableReference(vehicle.getCurrentPosition()));
    if (vehicle.getPrecisePosition() != null) {
      vehicleState.setPrecisePosition(
          new PrecisePosition(
              vehicle.getPrecisePosition().getX(),
              vehicle.getPrecisePosition().getY(),
              vehicle.getPrecisePosition().getZ()
          )
      );
    }
    vehicleState.setOrientationAngle(vehicle.getOrientationAngle());
    vehicleState.setState(vehicle.getState());
    vehicleState.setAllocatedResources(toListOfListOfNames(vehicle.getAllocatedResources()));
    vehicleState.setClaimedResources(toListOfListOfNames(vehicle.getClaimedResources()));
    vehicleState.setEnvelopeKey(vehicle.getEnvelopeKey());
    vehicleState.setAllowedOrderTypes(
        vehicle.getAllowedOrderTypes()
            .stream()
            .sorted()
            .collect(Collectors.toCollection(ArrayList::new))
    );
    return vehicleState;
  }

  private static String nameOfNullableReference(
      @Nullable
      TCSObjectReference<?> reference
  ) {
    return reference == null ? null : reference.getName();
  }

  private static List<List<String>> toListOfListOfNames(
      List<Set<TCSResourceReference<?>>> resources
  ) {
    List<List<String>> result = new ArrayList<>(resources.size());

    for (Set<TCSResourceReference<?>> resSet : resources) {
      result.add(
          resSet.stream()
              .map(resRef -> resRef.getName())
              .collect(Collectors.toList())
      );
    }

    return result;
  }

  /**
   * A precise position of a vehicle.
   */
  public static class PrecisePosition {

    private long x;

    private long y;

    private long z;

    /**
     * Creates a new instance.
     */
    public PrecisePosition() {
    }

    /**
     * Creates a new instance.
     *
     * @param x x value
     * @param y y value
     * @param z z value
     */
    public PrecisePosition(long x, long y, long z) {
      this.x = x;
      this.y = y;
      this.z = z;
    }

    public long getX() {
      return x;
    }

    public void setX(long x) {
      this.x = x;
    }

    public long getY() {
      return y;
    }

    public void setY(long y) {
      this.y = y;
    }

    public long getZ() {
      return z;
    }

    public void setZ(long z) {
      this.z = z;
    }
  }
}
