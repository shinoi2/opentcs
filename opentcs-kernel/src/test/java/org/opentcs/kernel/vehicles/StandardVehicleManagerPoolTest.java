// SPDX-FileCopyrightText: The openTCS Authors
// SPDX-License-Identifier: MIT
package org.opentcs.kernel.vehicles;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.opentcs.components.kernel.services.InternalVehicleService;
import org.opentcs.data.model.Vehicle;
import org.opentcs.drivers.vehicle.VehicleCommAdapter;

/**
 * Tests for the {@link StandardVehicleManagerPoolTest}.
 */
class StandardVehicleManagerPoolTest {

  /**
   * A name for a vehicle.
   */
  private static final String A_VEHICLE_NAME = "MyVehicle";
  /**
   * An name for a vehicle that does not exist.
   */
  private static final String UNKNOWN_VEHICLE_NAME = "SomeUnknownVehicle";
  /**
   * The (mocked) vehicle service.
   */
  private InternalVehicleService vehicleService;
  /**
   * A (mocked) communication adapter.
   */
  private VehicleCommAdapter commAdapter;
  /**
   * The VehicleManagerpool we're testing.
   */
  private LocalVehicleControllerPool vehManagerPool;

  @BeforeEach
  void setUp() {
    vehicleService = mock(InternalVehicleService.class);
    commAdapter = mock(VehicleCommAdapter.class);
    vehManagerPool = new DefaultVehicleControllerPool(
        vehicleService,
        new MockedVehicleManagerFactory()
    );
  }

  @Test
  void testThrowsNPEIfVehicleNameIsNull() {
    assertThrows(
        NullPointerException.class,
        () -> vehManagerPool.attachVehicleController(null, commAdapter)
    );
  }

  @Test
  void testThrowsNPEIfCommAdapterIsNull() {
    assertThrows(
        NullPointerException.class,
        () -> vehManagerPool.attachVehicleController(A_VEHICLE_NAME, null)
    );
  }

  @Test
  void testThrowsExceptionForUnknownVehicleName() {
    assertThrows(
        IllegalArgumentException.class,
        () -> vehManagerPool.attachVehicleController(UNKNOWN_VEHICLE_NAME, commAdapter)
    );
  }

  @Test
  void testThrowsNPEIfDetachingNullVehicleName() {
    assertThrows(
        NullPointerException.class,
        () -> vehManagerPool.detachVehicleController(null)
    );
  }

  /**
   * A factory delivering vehicle manager mocks.
   */
  private static class MockedVehicleManagerFactory
      implements
        VehicleControllerFactory {

    @Override
    public DefaultVehicleController createVehicleController(
        Vehicle vehicle,
        VehicleCommAdapter commAdapter
    ) {
      return mock(DefaultVehicleController.class);
    }
  }
}
