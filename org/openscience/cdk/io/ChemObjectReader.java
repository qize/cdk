/* 
 * $RCSfile$
 * $Author$   
 * $Date$  
 * $Revision$
 * 
 * Copyright (C) 1997-2001  The Chemistry Development Kit (CDK) project
 * 
 * Contact: steinbeck@ice.mpg.de, gezelter@maul.chem.nd.edu, egonw@sci.kun.nl
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * All we ask is that proper credit is given for our work, which includes
 * - but is not limited to - adding the above copyright notice to the beginning
 * of your source code files, and to any copyright notice that you may distribute
 * with programs based on this work.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 */
package org.openscience.cdk.io;

import org.openscience.cdk.*;
import org.openscience.cdk.exception.*;
import java.io.*;
import java.util.*;
import javax.vecmath.*;

/**
 * This class is the interface that all IO readers should implement.
 * Programs need only care about this interface for any kind of IO.
 *
 * Currently, database IO and file IO is supported. Internet IO is
 * expected.
 *
 * @version  $Revision$
 **/
public interface ChemObjectReader {

    /**
     * Reads an ChemObject of type "object" from input. The constructure
     * of the actual implementation may take a Reader as input to get
     * a very flexible reader that can read from string, files, etc.
     * 
     * @param  object    the type of object to return
     * @return returns an object of that contains the content (or 
     *         part) of the input content
     *
     * @exception UnsupportedChemObjectException it is thrown if
     *            the type of information is not available from 
     *            the input
     **/
    public ChemObject read(ChemObject object) throws UnsupportedChemObjectException;

}

