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

import free.util.Struct;
import java.util.StringTokenizer;


/**
 * A structure holding parsed information from a seekinfo line. More information
 * about the format is available in the "help iv_seekinfo" file on the Free
 * Internet Chess Server (http://www.freechess.org).
 */

public class SeekInfoStruct extends Struct{



  /**
   * The bit mask for an unregistered player.
   */

  public static final int UNREGISTERED = 0x1;




  /**
   * The bit mask for a computer player.
   */

  public static final int COMPUTER = 0x2;




  /**
   * The bit mask for a GM.
   */

  public static final int GM = 0x4;




  /**
   * The bit mask for an IM.
   */

  public static final int IM = 0x8;




  /**
   * The bit mask for an FM.
   */

  public static final int FM = 0x10;




  /**
   * The bit mask for a WGM.
   */

  public static final int WGM = 0x20;




  /**
   * The bit mask for a WIM.
   */

  public static final int WIM = 0x40;




  /**
   * The bit mask for a WFM.
   */

  public static final int WFM = 0x80;



  
  /**
   * Creates a new SeekInfoStruct with the specified arguments.
   *
   * @param canAcceptSeek <code>true</code> if the user can accept the seek,
   * <code>false</code> otherwise.
   * @param index The seek index.
   * @param name The handle of the seeking player.
   * @param titles The titles of the player, ORed into an int. 
   * @param rating The rating of the player.
   * @param provShow The seeker's rating provshow character, 'E' is estimated,
   * 'P' if provisional and ' ' if neither.
   * @param time The time of the sought game, in minutes.
   * @param inc The increment of the sought game, in seconds.
   * @param isRated The ratedness of the sought game.
   * @param matchType The type of the match (either variant or rating type - 
   * "suicide", "lightning", "blitz" etc.).
   * @param color The requested color. 'W' if white, 'B' if black and '?' if
   * doesn't care.
   * @param minRating The minimum rating of the sought opponent.
   * @param maxRating The maximum rating of the sought opponent.
   * @param isAutomaticAccept <code>true</code> if the acceptance of the seek is
   * automatic, <code>false</code> if manual.
   * @param isFormulaUsed <code>true</code> if the you must pass the seeker's
   * formula to accept the offer.
   */

  public SeekInfoStruct(boolean canAcceptSeek, int index, String name, int titles, int rating, 
      char provshow, int time, int inc, boolean isRated, String matchType, char color,
      int minRating, int maxRating, boolean isAutomaticAccept, boolean isFormulaUsed){

    if (time < 0)
      throw new IllegalArgumentException("The game's initial time ("+time+") may not be negative");

    if (inc < 0)
      throw new IllegalArgumentException("The game's increment ("+inc+") may not be negative");

    switch (color){
      case '?':
      case 'W':
      case 'B':
        break;
      default:
        throw new IllegalArgumentException("Bad color character: "+color);
    }

    setBooleanProperty("CanAcceptSeek", canAcceptSeek);
    setIntegerProperty("Index", index);
    setStringProperty("Name", name);
    setIntegerProperty("Titles", titles);
    setIntegerProperty("Rating", rating);
    setCharProperty("ProvShow", provshow);
    setIntegerProperty("Time", time);
    setIntegerProperty("Increment", inc);
    setBooleanProperty("IsRated", isRated);
    setStringProperty("MatchType", matchType);
    setCharProperty("RequestedColor", color);
    setIntegerProperty("MinRating", minRating);
    setIntegerProperty("MaxRating", maxRating);
    setBooleanProperty("IsAutomaticAccept", isAutomaticAccept);
    setBooleanProperty("IsFormulaUsed", isFormulaUsed);
  }




  /**
   * Parses the given seekinfo line and returns a corresponding SeekInfoStruct
   * object. Here's an example seekinfo line:<PRE>
   * <s> 29 w=Snaps ti=02 rt=1532  t=1 i=0 r=r tp=lightning c=? rr=0-9999 a=t f=t
   * </PRE>
   * If the seek cannot be accepted by the user, the line identifier is "<sn>"
   * and not "<s>".
   * More information about the format is available in the FICS iv_seekinfo
   * help file.
   */

  public static SeekInfoStruct parseSeekInfoLine(String line){
    StringTokenizer tokens = new StringTokenizer(line, " -=");

    boolean canAcceptSeek;
    String identifier = tokens.nextToken();
    if (identifier.equals("<s>")) // Skip the <s> identifier
      canAcceptSeek = true;
    else if (identifier.equals("<sn>"))
      canAcceptSeek = false;
    else
      throw new IllegalArgumentException("Missing \"<s>\" or \"<sn>\" identifier");

    int index = Integer.parseInt(tokens.nextToken());

    assertToken(tokens, "w"); // w=
    String name = tokens.nextToken();

    assertToken(tokens, "ti"); // ti=
    int titles = Integer.parseInt(tokens.nextToken(), 16);

    assertToken(tokens, "rt"); // rt=
    String ratingString = tokens.nextToken();
    char provShow = ' ';
    if (!Character.isDigit(ratingString.charAt(ratingString.length() - 1))){
      provShow = ratingString.charAt(ratingString.length() - 1);
      ratingString = ratingString.substring(0, ratingString.length() - 1);
    }
    int rating = Integer.parseInt(ratingString);

    assertToken(tokens, "t"); // t=
    int time = Integer.parseInt(tokens.nextToken());

    assertToken(tokens, "i"); // i=
    int inc = Integer.parseInt(tokens.nextToken());

    assertToken(tokens, "r"); // r=
    boolean isRated = tokens.nextToken().equals("r");

    assertToken(tokens, "tp"); // tp=
    String matchType = tokens.nextToken();

    assertToken(tokens, "c"); // c=
    char requestedColor = tokens.nextToken().charAt(0);

    assertToken(tokens, "rr"); // rr=
    int minRating = Integer.parseInt(tokens.nextToken());
    int maxRating = Integer.parseInt(tokens.nextToken());

    assertToken(tokens, "a"); // a=
    boolean isAutomaticAccept = tokens.nextToken().equals("t");

    assertToken(tokens, "f"); // f=
    boolean isFormulaUsed = tokens.nextToken().equals("t");

    return new SeekInfoStruct(canAcceptSeek, index, name, titles, rating, provShow,
      time, inc, isRated, matchType, requestedColor, minRating, maxRating, 
      isAutomaticAccept, isFormulaUsed);
  }




  /**
   * Checks that the next token for the given StringTokenizer is the specified
   * String. Throws an IllegalArgumentException if it isn't.
   */

  private static void assertToken(StringTokenizer tokenizer, String token){
   String realToken = tokenizer.nextToken();
   if (!realToken.equals(token))
     throw new IllegalArgumentException("Bad token \""+realToken+"\", expected \""+token+"\" instead");
  }




  /**
   * Returns <code>true</code> if the user account can accept the seek, returns
   * <code>false</code> otherwise.
   */

  public boolean canAcceptSeek(){
    return getBooleanProperty("CanAcceptSeek");
  }




  /**
   * Returns the index of the seek.
   */

  public int getSeekIndex(){
    return getIntegerProperty("Index");
  }




  /**
   * Returns the handle of the seeking player.
   */

  public String getSeekerHandle(){
    return getStringProperty("Name");
  }




  /**
   * Returns the titles of the seeking player, ORed into an int. To find whether
   * the player is GM, for example, use
   * <code>(getSeekerTitles() & SeekInfoStruct.GM) != 0</code>.
   */
  
  public int getSeekerTitles(){
    return getIntegerProperty("Titles");
  }




  /**
   * Returns the seeker's rating.
   */

  public int getSeekerRating(){
    return getIntegerProperty("Rating");
  }




  /**
   * Returns the seeker rating's provshow character. 'E' if the rating is
   * estimated, 'P' if provisional, and ' ' if neither.
   */

  public char getSeekerProvShow(){
    return getCharProperty("ProvShow");
  }




  /**
   * Returns the initial time of the sought match, in minutes.
   */

  public int getMatchTime(){
    return getIntegerProperty("Time");
  }




  /**
   * Returns the increment of the sought match, in seconds.
   */

  public int getMatchIncrement(){
    return getIntegerProperty("Increment");
  }




  /**
   * Returns <code>true</code> if the sought match is rated, <code>false</code>
   * otherwise.
   */

  public boolean isMatchRated(){
    return getBooleanProperty("IsRated");
  }



  
  /**
   * Returns the sought game type. Either the name of the wild variant, or the
   * rating type, if the variant is chess. For example - "suicide", "lightning",
   * "blitz" etc.
   */

  public String getMatchType(){
    return getStringProperty("MatchType");
  }




  /**
   * Returns a character specifying the desired color for the seeker. 'W' if
   * white, 'B' if black, and '?' if the seeker doesn't care.
   */

  public char getSeekerColor(){
    return getCharProperty("RequestedColor");
  }




  /**
   * Returns the minimum rating of the desired opponent.
   */

  public int getOpponentMinRating(){
    return getIntegerProperty("MinRating");
  }




  /**
   * Returns the maximum rating of the desired opponent.
   */

  public int getOpponentMaxRating(){
    return getIntegerProperty("MaxRating");
  }




  /**
   * Returns <code>true</code> if the match will be accepted automatically,
   * <code>false</code> if the seeker must confirm it manually.
   */

  public boolean isAutomaticAccept(){
    return getBooleanProperty("IsAutomaticAccept");
  }




  /**
   * Returns <code>true</code> if in order to accept the seek, one must pass
   * the formula of the seeker. Returns <code>false</code> otherwise.
   */

  public boolean isFormulaUsed(){
    return getBooleanProperty("IsFormulaUsed");
  }


}
