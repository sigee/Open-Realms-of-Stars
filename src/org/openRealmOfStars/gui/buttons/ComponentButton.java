package org.openRealmOfStars.gui.buttons;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import org.openRealmOfStars.gui.GuiStatics;
import org.openRealmOfStars.gui.icons.Icons;
import org.openRealmOfStars.player.ship.Ship;
import org.openRealmOfStars.player.ship.ShipComponent;

/**
 * 
 * Open Realm of Stars game project
 * Copyright (C) 2016  Tuomo Untinen
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, see http://www.gnu.org/licenses/
 * 
 * 
 * Class for creating ship's component button with current ship component status
 * 
 */

public class ComponentButton extends JButton {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  
  /**
   * Full ship, contains component and component hitpoints
   */
  private Ship ship;
  
  /**
   * Component index
   */
  private int index;
  
  private ImageIcon icon;
  
  /**
   * Construct component button
   * @param ship Ship 
   * @param index Component Index
   */
  public ComponentButton(Ship ship, int index) {
    super();
    this.ship = ship;
    this.index = index;
    if (ship.getComponent(index) != null) {
      this.setText(ship.getComponent(index).getName());
      icon = new ImageIcon(Icons.getIconByName(ship.getComponent(index).getType().getIconName()).getIcon());
      this.setIcon(icon);
    } else {
      this.setText("");
    }
    this.setFont(GuiStatics.getFontCubellanSmaller());
    this.setForeground(GuiStatics.COLOR_COOL_SPACE_BLUE);
    this.setBackground(GuiStatics.COLOR_DEEP_SPACE_PURPLE);
    this.setFocusPainted(false);
    updateButton();
  }

  /**
   * Set Component to show
   * @param ship Ship
   * @param index Component Index
   */
  public void setComponent(Ship ship, int index) {
    this.ship = ship;
    this.index = index;
    updateButton();
  }
  
  /**
   * Update button according the component
   */
  public void updateButton() {
    ShipComponent comp = ship.getComponent(index);
    boolean hasEnergy = ship.hasComponentEnergy(index);
    int hp = ship.getHullPointForComponent(index);
    int maxHP = ship.getHull().getSlotHull();
    if (comp != null) {
      this.setEnabled(true);
      if (!hasEnergy) {
        icon = new ImageIcon(Icons.getIconByName(Icons.ICON_BATTERY).getIcon());
        this.setIcon(icon);
      } else {
        icon = new ImageIcon(Icons.getIconByName(comp.getType().getIconName()).getIcon());
        this.setIcon(icon);
      }
      if (hp == 0) {
        this.setBackground(GuiStatics.COLOR_DESTROYED);
      } else {
        int ratio = hp*100/maxHP;
        if (ratio == 100) {
          this.setBackground(GuiStatics.COLOR_DEEP_SPACE_PURPLE);
        } else if (ratio >= 75) {
          this.setBackground(GuiStatics.COLOR_DAMAGE_LITTLE);
        } else if (ratio >= 50) {
          this.setBackground(GuiStatics.COLOR_DAMAGE_HALF);
        } else if (ratio > 25) {
          this.setBackground(GuiStatics.COLOR_DAMAGE_MUCH);
        } else {
          this.setBackground(GuiStatics.COLOR_DAMAGE_ALMOST_DESTROYED);
        }
      }
      
    } else {
      this.setBackground(GuiStatics.COLOR_DEEP_SPACE_PURPLE);
      this.setEnabled(false);
      this.setText("");
    }
  }
}