/**
 * The utillib library.
 * More information is available at http://www.jinchess.com/.
 * Copyright (C) 2003 Alexander Maryanovsky.
 * All rights reserved.
 *
 * The utillib library is free software; you can redistribute
 * it and/or modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2 of the
 * License, or (at your option) any later version.
 *
 * The utillib library is distributed in the hope that it will
 * be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser
 * General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with utillib library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package free.util.zip;

import java.util.zip.ZipFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.net.URL;
import free.util.ChildClassLoader;


/**
 * A <code>ClassLoader</code> which loads classes and resources from a specified
 * zip file. Note that in order for the <code>getResource(String)</code> method
 * of this classloader to work correctly, the application must have a
 * <code>URLStreamHandlerFactory</code> which returns an instance of
 * <code>free.util.zip.ZipURLStreamHandler</code> for the "zip" protocol.
 * <strong>IMPORTANT:</strong> This classloader doesn't seem to work well with
 * compressed zipfiles under MS VM (thinks the file is corrupted sometimes).
 */

public class ZipClassLoader extends ChildClassLoader{


  
  /**
   * The ZipFile from which we load stuff.
   */

  private final ZipFile zipFile;



  /**
   * Creates a new <code>ZipClassLoader</code> for the specified zip file with
   * the specified parent <code>ClassLoader</code> to which loading is delegated
   * if a class or a resource can't be found.
   */

  public ZipClassLoader(File file, ChildClassLoader parent) throws ZipException, IOException{
    super(parent);
    this.zipFile = new ZipFile(file);
  }


  /**
   * Creates a new <code>ZipClassLoader</code> for the specified zip file.
   *
   * @throws ZipException if a ZIP format error occurs.
   * @throws IOException if an I/O error has occurs. 
   */

  public ZipClassLoader(File file) throws ZipException, IOException{
    this(file, null);
  }



  /**
   * Returns an <code>InputStream</code> for reading the resource with the
   * specified name.
   */

  protected InputStream getResourceAsStreamImpl(String name){
    try{
      ZipEntry entry = zipFile.getEntry(name);
      if (entry == null)
        return null;
      return zipFile.getInputStream(entry);
    } catch (IOException e){
        return null;
      }
  }



  /**
   * Returns a <code>URL</code> pointing to the resource with the specified
   * name.
   */

  protected URL getResourceImpl(String name){
    if (name.endsWith("/"))
      name = name.substring(0, name.length() - 1);
    
    // There seems to be no root entry in zipfiles
    if ("".equals(name) || (zipFile.getEntry(name) != null))
      return ZipURLStreamHandler.createURL(zipFile.getName(), name);
    return null;
  }



}
