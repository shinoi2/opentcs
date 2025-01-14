// SPDX-FileCopyrightText: The openTCS Authors
// SPDX-License-Identifier: MIT
package org.opentcs.drivers.peripherals.management;

import jakarta.annotation.Nonnull;
import java.util.List;
import org.opentcs.components.Lifecycle;
import org.opentcs.data.model.Location;
import org.opentcs.data.model.TCSResourceReference;
import org.opentcs.drivers.peripherals.PeripheralCommAdapterDescription;
import org.opentcs.drivers.peripherals.PeripheralProcessModel;

/**
 * Provides peripheral comm adapter specific panels used for interaction and displaying information.
 */
public interface PeripheralCommAdapterPanelFactory
    extends
      Lifecycle {

  /**
   * Returns a list of {@link PeripheralCommAdapterPanel}s.
   *
   * @param description The description to create panels for.
   * @param location The location to create panels for.
   * @param processModel The current state of the process model a panel may want to initialize its
   * components with.
   * @return A list of comm adapter panels, or an empty list, if this factory cannot provide panels
   * for the given description.
   */
  List<PeripheralCommAdapterPanel> getPanelsFor(
      @Nonnull
      PeripheralCommAdapterDescription description,
      @Nonnull
      TCSResourceReference<Location> location,
      @Nonnull
      PeripheralProcessModel processModel
  );
}
