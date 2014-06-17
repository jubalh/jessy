/**
 * The freechess.org connection library.
 * More information is available at http://www.jinchess.com/.
 * Copyright (C) 2002 Alexander Maryanovsky.
 * All rights reserved.
 *
 * The freechess.org connection library is free software; you can redistribute
 * it and/or modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * The freechess.org connection library is distributed in the hope that it will
 * be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with the freechess.org connection library; if not, write to the Free
 * Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package free.freechess;

import java.util.Hashtable;


/**
 * Represents an ivar (interface variable) on FICS.
 */

public class Ivar{


  /**
   * Maps ivar indices to ivars.
   */

  private final static Hashtable INDEX_TO_IVAR = new Hashtable();



  /**
   * Maps ivar names to ivars.
   */

  private final static Hashtable NAME_TO_IVAR = new Hashtable();



  /**
   * The compressmove ivar. Issue "help iv_compressmove" on FICS for more
   * information.
   */

  public static final Ivar COMPRESSMOVE = new Ivar("compressmove", 0);



  /**
   * The audiochar ivar. No idea what it does.
   */

  public static final Ivar AUDIOCHAT = new Ivar("audiochat", 1);



  /**
   * The seekremove ivar. Issue "help iv_seekremove" on FICS for more
   * information.
   */

  public static final Ivar SEEKREMOVE = new Ivar("seekremove", 2);



  /**
   * The defprompt ivar. Issue "help iv_defprompt" on FICS for more information.
   */

  public static final Ivar DEFPROMPT = new Ivar("defprompt", 3);



  /**
   * The lock ivar. Issue "help iv_lock" on FICS for more information.
   */

  public static final Ivar LOCK = new Ivar("lock", 4);



  /**
   * The startpos ivar. Issue "help iv_startpos" on FICS for more information.
   */

  public static final Ivar STARTPOS = new Ivar("startpos", 5);



  /**
   * The block ivar. Issue "help iv_block" on FICS for more information.
   */

  public static final Ivar BLOCK = new Ivar("block", 6);



  /**
   * The gameinfo ivar. Issue "help iv_gameinfo" on FICS for more information.
   */

  public static final Ivar GAMEINFO = new Ivar("gameinfo", 7);



  /**
   * The xdr ivar. No idea what it does.
   */

  public static final Ivar XDR = new Ivar("xdr", 8);



  /**
   * The pendinfo ivar. Issue "help iv_pendinfo" on FICS for more information.
   */

  public static final Ivar PENDINFO = new Ivar("pendinfo", 9);



  /**
   * The graph ivar. Issue "help iv_graph" on FICS for more information.
   */

  public static final Ivar GRAPH = new Ivar("graph", 10);



  /**
   * The seekinfo ivar. Issue "help iv_seekinfo" on FICS for more information.
   */

  public static final Ivar SEEKINFO = new Ivar("seekinfo", 11);



  /**
   * The extascii ivar. No idea what it does.
   */

  public static final Ivar EXTASCII = new Ivar("extascii", 12);



  /**
   * The nohighlight ivar. Probably disables some kind of special server
   * signals.
   */

  public static final Ivar NOHIGHLIGHT = new Ivar("nohighlight", 13);



  /**
   * The vt_highlight ivar. Probably enables some kind of special server signals
   * designed to highlight things on terminals.
   */

  public static final Ivar VT_HIGHLIGHT = new Ivar("vt_highlight", 14);



  /**
   * The showserver ivar. No idea what it does.
   */

  public static final Ivar SHOWSERVER = new Ivar("showserver", 15);
  


  /**
   * The pin ivar. Probably enables and disables notifications about players
   * arriving and departing.
   */

  public static final Ivar PIN = new Ivar("pin", 16);



  /**
   * The ms ivar. Asks the server to send current time in style12 in
   * milliseconds instead of seconds.
   */

  public static final Ivar MS = new Ivar("ms", 17);



  /**
   * The pinginfo ivar. No idea what it does.
   */

  public static final Ivar PINGINFO = new Ivar("pinginfo", 18);



  /**
   * The boardinfo ivar. No idea what it does.
   */

  public static final Ivar BOARDINFO = new Ivar("boardinfo", 19);



  /**
   * The extuserinfo ivar. No idea what it does.
   */

  public static final Ivar EXTUSERINFO = new Ivar("extuserinfo", 20);



  /**
   * The seekca ivar. No idea what it does.
   */

  public static final Ivar SEEKCA = new Ivar("seekca", 21);



  /**
   * The showownseek ivar. Asks the server to send the user's own seek as part
   * of seekinfo.
   */

  public static final Ivar SHOWOWNSEEK = new Ivar("showownseek", 22);



  /**
   * The premove ivar. Interfaces can set this (useless) variable on if they
   * support premove.
   */

  public static final Ivar PREMOVE = new Ivar("premove", 23);



  /**
   * The smartmove ivar. No idea what it does, but presumably the same as the
   * premove ivar.
   */

  public static final Ivar SMARTMOVE = new Ivar("smartmove", 24);



  /**
   * The movecase ivar. No idea what it does.
   */

  public static final Ivar MOVECASE = new Ivar("movecase", 25);



  /**
   * The suicide ivar. No idea what it does.
   */

  public static final Ivar SUICIDE = new Ivar("suicide", 26);



  /**
   * The crazyhouse ivar. No idea what it does.
   */

  public static final Ivar CRAZYHOUSE = new Ivar("crazyhouse", 27);



  /**
   * The losers ivar. No idea what it does.
   */

  public static final Ivar LOSERS = new Ivar("losers", 28);



  /**
   * The wildcastle ivar. No idea what it does.
   */

  public static final Ivar WILDCASTLE = new Ivar("wildcastle", 29);



  /**
   * The fr ivar. No idea what it does.
   */

  public static final Ivar FR = new Ivar("fr", 30);



  /**
   * The nowrap ivar. Disables server wrapping the text.
   */

  public static final Ivar NOWRAP = new Ivar("nowrap", 31);



  /**
   * The allresults ivar. No idea what it does.
   */

  public static final Ivar ALLRESULTS = new Ivar("allresults", 32);



  /**
   * The obsping ivar. No idea what it does.
   */

  public static final Ivar OBSPING = new Ivar("obsping", 33);



  /**
   * The singleboard ivar. No idea what it does.
   */

  public static final Ivar SINGLEBOARD = new Ivar("singleboard", 34);



  /**
   * The index of the ivar when its value is specified on the login prompt.
   */

  private final int index;



  /**
   * The name of the ivar.
   */

  private final String name;



  /**
   * Creates an ivar with the specified name and index.
   */

  private Ivar(String name, int index){
    name = name.toLowerCase();

    this.name = name;
    this.index = index;

    Object ivar = INDEX_TO_IVAR.put(new Integer(index), this);
    if (ivar != null){
      INDEX_TO_IVAR.put(new Integer(index), ivar);
      throw new IllegalArgumentException("The index "+index+" is already taken by "+ivar);
    }

    ivar = NAME_TO_IVAR.put(name, this);
    if (ivar != null){
      NAME_TO_IVAR.put(name, ivar);
      throw new IllegalArgumentException("The name "+name+" is already taken by "+ivar);
    }
  }



  /**
   * Returns the index of the ivar when its value is specified on the login
   * line. Indices start at 0.
   */

  public int getIndex(){
    return index;
  }



  /**
   * Returns the name of the variable.
   */

  public String getName(){
    return name;
  }



  /**
   * Returns the ivar with the specified index, or null if no such ivar exists.
   */

  public static Ivar getByIndex(int index){
    return (Ivar)INDEX_TO_IVAR.get(new Integer(index));
  }



  /**
   * Returns the ivar with the specified name, case insensitively.
   */

  public static Ivar getByName(String name){
    name = name.toLowerCase();

    return (Ivar)NAME_TO_IVAR.get(name);
  }



  /**
   * Returns a textual representation of this ivar.
   */

  public String toString(){
    return getName();
  }



}