// SPDX-FileCopyrightText: The openTCS Authors
// SPDX-License-Identifier: MIT
package org.opentcs.util.persistence.v6;

import static java.util.Objects.requireNonNull;

import jakarta.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 */
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"name", "type", "members", "properties", "blockLayout"})
public class BlockTO
    extends
      PlantModelElementTO {

  private String type = "SINGLE_VEHICLE_ONLY";
  private List<MemberTO> members = new ArrayList<>();
  private BlockLayout blockLayout = new BlockLayout();

  /**
   * Creates a new instance.
   */
  public BlockTO() {
  }

  @XmlAttribute(required = true)
  public String getType() {
    return type;
  }

  public BlockTO setType(
      @Nonnull
      String type
  ) {
    requireNonNull(type, "type");
    this.type = type;
    return this;
  }

  @XmlElement(name = "member")
  public List<MemberTO> getMembers() {
    return members;
  }

  public BlockTO setMembers(
      @Nonnull
      List<MemberTO> members
  ) {
    requireNonNull(members, "members");
    this.members = members;
    return this;
  }

  @XmlElement(required = true)
  public BlockLayout getBlockLayout() {
    return blockLayout;
  }

  public BlockTO setBlockLayout(
      @Nonnull
      BlockLayout blockLayout
  ) {
    this.blockLayout = requireNonNull(blockLayout, "blockLayout");
    return this;
  }

  @XmlAccessorType(XmlAccessType.PROPERTY)
  public static class BlockLayout {

    private String color = "";

    /**
     * Creates a new instance.
     */
    public BlockLayout() {
    }

    @XmlAttribute(required = true)
    public String getColor() {
      return color;
    }

    public BlockLayout setColor(
        @Nonnull
        String color
    ) {
      this.color = requireNonNull(color, "color");
      return this;
    }
  }
}
