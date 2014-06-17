/**
 * Jin - a chess client for internet chess servers.
 * More information is available at http://www.jinchess.com/.
 * Copyright (C) 2005 Alexander Maryanovsky.
 * All rights reserved.
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
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package free.util.models;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonModel;


/**
 * Provides various model related utilities.
 */

public class ModelUtils{
  
  
  
  /**
   * Links between the specified <code>BooleanModel</code> and
   * <code>ButtonModel</code>. That is, when one changes, the other one is
   * changed to match its state (for the button, its selected state is used).
   * Note that the two models' state must match initially.
   */
  
  public static void link(final BooleanModel booleanModel, final ButtonModel buttonModel){
    if (booleanModel.get() != buttonModel.isSelected())
      throw new IllegalStateException("Mismatching model states");
    
    booleanModel.addListener(new BooleanListener(){
      public void modelChanged(ConstBooleanModel model){
        buttonModel.setSelected(model.isOn());
      }
    });
    
    buttonModel.addItemListener(new ItemListener(){
      public void itemStateChanged(ItemEvent evt){
        booleanModel.set(evt.getStateChange() == ItemEvent.SELECTED);
      }
    });
  }

  
  
}
