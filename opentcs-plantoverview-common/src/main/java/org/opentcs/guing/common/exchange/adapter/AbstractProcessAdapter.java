// SPDX-FileCopyrightText: The openTCS Authors
// SPDX-License-Identifier: MIT
package org.opentcs.guing.common.exchange.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.opentcs.access.to.model.VisualLayoutCreationTO;
import org.opentcs.data.TCSObject;
import org.opentcs.guing.base.components.properties.type.KeyValueProperty;
import org.opentcs.guing.base.components.properties.type.KeyValueSetProperty;
import org.opentcs.guing.base.components.properties.type.Property;
import org.opentcs.guing.base.model.ModelComponent;
import org.opentcs.guing.common.model.SystemModel;

/**
 * Basic implementation of a <code>ProcessAdapter</code>.
 * Synchronizes between the local <code>ModelComponent</code> and the
 * corresponding kernel object.
 */
public abstract class AbstractProcessAdapter
    implements
      ProcessAdapter {

  protected Map<String, String> getKernelProperties(ModelComponent model) {
    Map<String, String> result = new HashMap<>();

    KeyValueSetProperty misc = (KeyValueSetProperty) model.getProperty(
        ModelComponent.MISCELLANEOUS
    );

    if (misc != null) {
      for (KeyValueProperty p : misc.getItems()) {
        result.put(p.getKey(), p.getValue());
      }
    }

    return result;
  }

  protected void unmarkAllPropertiesChanged(ModelComponent model) {
    for (Property prop : model.getProperties().values()) {
      prop.unmarkChanged();
    }
  }

  /**
   * Reads the current misc properties from the kernel and adopts these for the model object.
   *
   * @param model The model object to adopt the properties for.
   * @param tcsObject The <code>TCSObject</code> to read from.
   */
  protected void updateMiscModelProperties(ModelComponent model, TCSObject<?> tcsObject) {
    List<KeyValueProperty> items = new ArrayList<>();

    for (Map.Entry<String, String> curEntry : tcsObject.getProperties().entrySet()) {
      if (!curEntry.getValue().contains("Unknown")) {
        items.add(new KeyValueProperty(model, curEntry.getKey(), curEntry.getValue()));
      }
    }

    KeyValueSetProperty miscellaneous = (KeyValueSetProperty) model
        .getProperty(ModelComponent.MISCELLANEOUS);
    miscellaneous.setItems(items);
  }

  protected List<VisualLayoutCreationTO> updatedLayouts(
      ModelComponent model,
      List<VisualLayoutCreationTO> layouts,
      SystemModel systemModel
  ) {
    List<VisualLayoutCreationTO> result = new ArrayList<>(layouts.size());

    for (VisualLayoutCreationTO layout : layouts) {
      result.add(updatedLayout(model, layout, systemModel));
    }

    return result;
  }

  protected VisualLayoutCreationTO updatedLayout(
      ModelComponent model,
      VisualLayoutCreationTO layout,
      SystemModel systemModel
  ) {
    return layout;
  }
}
