// SPDX-FileCopyrightText: The openTCS Authors
// SPDX-License-Identifier: MIT
package org.opentcs.kernelcontrolcenter.util;

import java.util.List;
import org.opentcs.configuration.ConfigurationEntry;
import org.opentcs.configuration.ConfigurationPrefix;
import org.opentcs.util.gui.dialog.ConnectionParamSet;

/**
 * Provides methods to configure the KernelControlCenter application.
 */
@ConfigurationPrefix(KernelControlCenterConfiguration.PREFIX)
public interface KernelControlCenterConfiguration {

  /**
   * This configuration's prefix.
   */
  String PREFIX = "kernelcontrolcenter";

  @ConfigurationEntry(
      type = "String",
      description = {"The kernel control center application's locale, as a BCP 47 language tag.",
          "Examples: 'en', 'de', 'zh'"},
      changesApplied = ConfigurationEntry.ChangesApplied.ON_APPLICATION_START,
      orderKey = "0_init_0"
  )
  String locale();

  @ConfigurationEntry(
      type = "Comma-separated list of <description>\\|<hostname>\\|<port>",
      description = "Kernel connection bookmarks to be used.",
      changesApplied = ConfigurationEntry.ChangesApplied.ON_APPLICATION_START,
      orderKey = "1_connection_0"
  )
  List<ConnectionParamSet> connectionBookmarks();

  @ConfigurationEntry(
      type = "Boolean",
      description = {"Whether to automatically connect to the kernel on startup.",
          "If 'true', the first connection bookmark will be used for the initial "
              + "connection attempt.",
          "If 'false', a dialog will be shown to enter connection parameters."},
      changesApplied = ConfigurationEntry.ChangesApplied.ON_APPLICATION_START,
      orderKey = "1_connection_1"
  )
  boolean connectAutomaticallyOnStartup();

  @ConfigurationEntry(
      type = "Integer",
      description = "The maximum number of characters in the logging text area.",
      changesApplied = ConfigurationEntry.ChangesApplied.INSTANTLY,
      orderKey = "9_misc_0"
  )
  int loggingAreaCapacity();

  @ConfigurationEntry(
      type = "Boolean",
      description = "Whether to enable and show the panel for peripheral drivers.",
      changesApplied = ConfigurationEntry.ChangesApplied.ON_APPLICATION_START,
      orderKey = "9_misc_1"
  )
  boolean enablePeripheralsPanel();
}
