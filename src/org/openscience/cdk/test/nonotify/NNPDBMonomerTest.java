/* $RCSfile$
 * $Author: egonw $    
 * $Date: 2006-04-12 11:16:35 +0200 (Wed, 12 Apr 2006) $    
 * $Revision: 5921 $
 * 
 *  Copyright (C) 2004-2007  Miguel Rojas <miguel.rojas@uni-koeln.de>
 * 
 * Contact: cdk-devel@lists.sourceforge.net
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License
 * as published by the Free Software Foundation; either version 2.1
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA. 
 * 
 */
package org.openscience.cdk.test.nonotify;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openscience.cdk.nonotify.NoNotificationChemObjectBuilder;
import org.openscience.cdk.test.protein.data.PDBMonomerTest;

/**
 * Checks the functionality of the AtomContainer.
 *
 * @cdk.module test-nonotify
 */
public class NNPDBMonomerTest extends PDBMonomerTest {

    public NNPDBMonomerTest(String name) {
        super(name);
    }

    public void setUp() {
    	super.builder = NoNotificationChemObjectBuilder.getInstance();
    }

    public static Test suite() {
        return new TestSuite(NNPDBMonomerTest.class);
    }

}
