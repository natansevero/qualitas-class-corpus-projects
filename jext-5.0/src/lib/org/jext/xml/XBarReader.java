/*
 * 18:36:04 05/09/00
 *
 * XBarReader.java - Reads xml-toolbar files
 * Copyright (C) 1999 Romain Guy
 * romain.guy@jext.org
 * www.jext.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
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

package org.jext.xml;

import java.io.*;
import com.microstar.xml.*;
import org.jext.*;

public class XBarReader
{
  public static void read(JextFrame parent, InputStream fileName, String file)
  {
    InputStreamReader reader = new InputStreamReader(Jext.getLanguageStream(fileName, file));
    XBarHandler xmh = new XBarHandler(parent);
    XmlParser parser = new XmlParser();
    parser.setHandler(xmh);

    try
    {
      parser.parse(Jext.class.getResource("xtoolbar.dtd").toString(), null, reader);
    } catch (XmlException e) {
      System.err.println("XBar: Error parsing grammar " + fileName);
      System.err.println("XBar: Error occured at line " + e.getLine() +
                         ", column " + e.getColumn());
      System.err.println("XBar: " + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }

    try
    {
      fileName.close();
      reader.close();
    } catch (IOException ioe) { }
  }
}

// End of XBarReader.java
