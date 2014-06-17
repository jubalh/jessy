/**
 * The chessclub.com connection library.
 * More information is available at http://www.jinchess.com/.
 * Copyright (C) 2005 Alexander Maryanovsky.
 * All rights reserved.
 *
 * The chessclub.com connection library is free software; you can redistribute
 * it and/or modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * The chessclub.com connection library is distributed in the hope that it will
 * be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the chessclub.com connection library; if not, write to the Free
 * Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package free.chessclub.level2;

import java.util.EventObject;
import free.chessclub.ChessclubConnection;



/**
 * The event fired when an ICC datagram is received. 
 */

public class DatagramEvent extends EventObject{
  
  
  
  /**
   * The received datagram.
   */
   
  private final Datagram datagram;
  
  
  
  /**
   * Creates a new <code>DatagramEvent</code> with the specified source
   * <code>ChessclubConnection</code> and the received <code>Datagram</code>. 
   */
   
  public DatagramEvent(ChessclubConnection conn, Datagram datagram){
    super(conn);
    
    if (datagram == null)
      throw new IllegalArgumentException("null datagram not allowed");
    
    this.datagram = datagram;
  }
  
  

  /**
   * Returns the ChessclubConnection object which received the datagram.
   */
   
  public ChessclubConnection getConnection(){
    return (ChessclubConnection)super.getSource();
  }
  
  
  
  /**
   * Returns the received datagram.
   */
   
  public Datagram getDatagram(){
    return datagram;
  }
  
  
}
