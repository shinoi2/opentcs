// SPDX-FileCopyrightText: The openTCS Authors
// SPDX-License-Identifier: MIT
package org.opentcs.operationsdesk.util;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 * Provides cursors for various situations.
 */
public final class Cursors {

  /**
   * A cursor suitable for dragging a vehicle to a destination point.
   */
  private static final Cursor DRAG_VEHICLE_CURSOR = createDragVehicleCursor();

  /**
   * Prevents instantiation.
   */
  private Cursors() {
  }

  /**
   * Returns a cursor suitable for dragging a vehicle to a destination point.
   *
   * @return A cursor suitable for dragging a vehicle to a destination point.
   */
  public static Cursor getDragVehicleCursor() {
    return DRAG_VEHICLE_CURSOR;
  }

  private static Cursor createDragVehicleCursor() {
    // Load an image for the vehicle drag cursor.
    BufferedImage bi = new BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB);
    bi.createGraphics().drawImage(
        new ImageIcon(
            Cursors.class.getClassLoader().getResource(
                "org/opentcs/guing/res/symbols/toolbar/create-order.22.png"
            )
        ).getImage(),
        0,
        0,
        null
    );
    return Toolkit.getDefaultToolkit().createCustomCursor(bi, new Point(0, 0), "toCursor");
  }
}
