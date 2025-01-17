// SPDX-FileCopyrightText: The openTCS Authors
// SPDX-License-Identifier: MIT
package org.opentcs.operationsdesk.transport;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.RowFilter;
import javax.swing.table.TableModel;

/**
 * A button for filtering table entries.
 */
public class FilterButton
    extends
      JToggleButton {

  /**
   * The sorter to apply the filter to.
   */
  private final FilteredRowSorter<? extends TableModel> sorter;
  /**
   * The filter to apply.
   */
  private final RowFilter<Object, Object> filter;

  /**
   * Creates a new instance.
   *
   * @param icon The image that the button should display
   * @param filter The Filter to apply.
   * @param sorter The row sorter to apply the filter to.
   */
  @SuppressWarnings("this-escape")
  public FilterButton(
      ImageIcon icon,
      RowFilter<Object, Object> filter,
      FilteredRowSorter<? extends TableModel> sorter
  ) {
    super(icon);
    this.sorter = sorter;
    this.filter = filter;

    addActionListener((ActionEvent e) -> changed());
    setSelected(true);
  }

  /**
   * Called when the button has changed.
   */
  private void changed() {
    if (isSelected()) {
      sorter.removeRowFilter(filter);
    }
    else {
      sorter.addRowFilter(filter);
    }
  }
}
