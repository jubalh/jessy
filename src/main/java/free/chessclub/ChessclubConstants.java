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

package free.chessclub;



/**
 * This inteface defines various magic constants used in the chessclub.com
 * protocol.
 */
 
public interface ChessclubConstants{
  
  
  /**
   * The code used used to specify that a player does not yet have a rating.
   */
  
  public static final int NONE_RATING_TYPE = 0;
  
  
  
  /**
   * The code used to specify that a rating is provisional.
   */
   
  public static final int PROVISIONAL_RATING_TYPE = 1;
  
  
  
  /**
   * The code used to specify that a rating is established.
   */
   
  public static final int ESTABLISHED_RATING_TYPE = 2;
  
  
  
  /**
   * The code for a "playing" player state.
   */
   
  public static final String PLAYING_PLAYER_STATE = "P";
  
  
  
  /**
   * The code for an "examining" player state.
   */
   
  public static final String EXAMINING_PLAYER_STATE = "E";
  
  
  
  /**
   * The code for a "playing a simul" player state.
   */
   
  public static final String PLAYING_SIMUL_PLAYER_STATE = "S";
  
  
  
  /**
   * The code for an "observing" player state.
   */
   
  public static final String OBSERVING_PLAYER_STATE = "O";
  
  
  
  /**
   * The code for a "playing as white" player state.
   */
   
  public static final String PLAYING_WHITE_PLAYER_STATE = "PW";
  
   
   
  /**
   * The code for a "playing as black" player state.
   */
   
  public static final String PLAYING_BLACK_PLAYER_STATE = "PB";



  /**
   * The code for a "playing a simul as white" player state.
   */
   
  public static final String PLAYING_SIMUL_WHITE_PLAYER_STATE = "SW";
  
   
   
  /**
   * The code for a "playing a simul as black" player state.
   */
   
  public static final String PLAYING_SIMUL_BLACK_PLAYER_STATE = "SB";
  
  
  
  /**
   * The code for a "doing nothing" player state.
   */
   
  public static final String DOING_NOTHING_PLAYER_STATE = "X";
  
  
  
  /**
   * The code for no color preference.
   */
   
  public static final int NO_COLOR_PREFERENCE = -1;
  
  
  
  /**
   * The code for white color preference.
   */
   
  public static final int WHITE_COLOR_PREFERENCE = 1;
  
  
  
  /**
   * The code for black color preference.
   */
   
  public static final int BLACK_COLOR_PREFERENCE = 0;
  
  
  
  /**
   * The code for a stored game type.
   */
   
  public static final int STORED_GAME_TYPE = -1;
  
  
  
  /**
   * The code for an examined game type.
   */
   
  public static final int EXAMINED_GAME_TYPE = 0;
  
  
  
  /**
   * The code for a played game type.
   */
   
  public static final int PLAYED_GAME_TYPE = 1;



  /**
   * The code for a "say" tell. 
   */
   
  public static final int SAY_TELL = 0;
  
  
  
  /**
   * The code for a regular personal tell.
   */
   
  public static final int REGULAR_TELL = 1;
  
  
  
  /**
   * The code for a "partner" tell.
   */
   
  public static final int P_TELL = 2;
  
  
  
  /**
   * The code for a "qtell" (a tell used by special types of bots). 
   */
   
  public static final int Q_TELL = 3;
  
  
  
  /**
   * The code for admin tell.
   */
  
  public static final int A_TELL = 4;
  
  
  
  /**
   * The code for a regular shout.
   */
   
  public static final int REGULAR_SHOUT = 0;
  
  
  
  /**
   * The code for an "i" shout.
   */
   
  public static final int I_SHOUT = 1;
  
  
  
  /**
   * The code for a serious shout (sshout).
   */
   
  public static final int S_SHOUT = 2;
  
  
  
  /**
   * The code for an announcement.
   */
   
  public static final int ANNOUNCEMENT_SHOUT = 3;
  
  
  
  /**
   * The code for a regular channel tell.
   */
   
  public static final int REGULAR_CHANNEL_TELL = 1;
  
  
  
  /**
   * The code for an admin channel tell.
   */
   
  public static final int A_CHANNEL_TELL = 4;
  
  
  
  /**
   * The code for a main line move, part of a DG_POSITION_BEGIN (either play or
   * examine mode)
   */

  public static final int INITIAL_MOVE = 0;   



  /**
   * The code for a main line move, play mode.
   */

	public static final int PLAYED_MOVE = 1;



  /**
   * The code for a main line move, examine mode. The result of "forward 1",
   * for example.
   */

	public static final int FORWARD_MOVE = 2;



  /**
   * The code for a move in examine mode, make what of it you will.
   */
     
	public static final int EXAMINE_MOVE = 3;



  /**
   * The code for a move in examine mode, intended to overwrite a previous line.
   * (Not implemented yet).
   */

  public static final int OVERWRITE_MOVE = 4;



  /**
   * The code for a move in examine mode, intended to be the new main line.
   * (Not implemented yet).
   */

	public static final int MAINLINE_MOVE = 5;



  /**
   * The code for a move in examine mode, intended to be a side line.
   * (Not implemented yet).
   */

  public static final int SIDELINE_MOVE = 6;
  
  
   
}
