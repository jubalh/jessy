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

import free.util.EventListenerList;



/**
 * A superclass for all models in this package.
 */

public abstract class Model{
  
  
  
  /**
   * The name of the model (optional - may be <code>null</code>).
   */
  
  private final String name;
  
  
  
  /**
   * The listeners to changes in the model.
   */
  
  protected final EventListenerList listenerList = new EventListenerList();
  
  
  
  /**
   * Creates a new <code>Model</code>.
   */
  
  public Model(){
    this(null);
  }
  
  
  
  /**
   * Creates a new <code>Model</code> with the specified name.
   */
  
  public Model(String name){
    this.name = name;
  }
  
  

  /**
   * Returns the name of this model, or <code>null</code> if it has none.
   */
  
  public String getName(){
    return name;
  }
  
  
  
  /**
   * Returns the name of this model, if it has one.
   */
  
  public String toString(){
    return getName();
  }
  

}
