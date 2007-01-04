/*
 *  $RCSfile$
 *  $Author$
 *  $Date$
 *  $Revision$
 *
 *  Copyright (C) 2004-2007  The Chemistry Development Kit (CDK) project
 *
 *  Contact: cdk-devel@lists.sourceforge.net
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2.1
 *  of the License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.openscience.cdk.test.charges;

import javax.vecmath.Point3d;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openscience.cdk.Atom;
import org.openscience.cdk.Molecule;
import org.openscience.cdk.charges.InductivePartialCharges;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.test.CDKTestCase;

/**
 *  TestSuite that runs a test for the MMFF94PartialCharges.
 *
 * @cdk.module test-extra
 *
 *@author        mfe4
 *@cdk.created       2004-11-04
 */

public class InductivePartialChargesTest extends CDKTestCase {

	/**
	 *  Constructor for the InductivePartialChargesTest object
	 */
	public InductivePartialChargesTest() { }


	/**
	 *  A unit test suite for JUnit
	 *
	 *@return    The test suite
	 */
	public static Test suite() {
		return new TestSuite(InductivePartialChargesTest.class);
	}


	/**
	 *  A unit test for JUnit with beta-amino-acetic-acid
	 *
	 *@exception  ClassNotFoundException  Description of the Exception
	 *@exception  CDKException            Description of the Exception
	 *@exception  java.lang.Exception     Description of the Exception
	 */
	public void testInductivePartialCharges() throws ClassNotFoundException, CDKException, java.lang.Exception {
		double [] testResult={0.197,-0.492,0.051,0.099,0.099};
		Point3d c_coord=new Point3d(1.392, 0.0, 0.0);
		Point3d f_coord=new Point3d(0.0, 0.0, 0.0);
		Point3d h1_coord=new Point3d(1.7439615035767404, 1.0558845107302222, 0.0);
		Point3d h2_coord=new Point3d(1.7439615035767404, -0.5279422553651107, 0.914422809754875);
		Point3d h3_coord=new Point3d(1.7439615035767402, -0.5279422553651113, -0.9144228097548747);
		
		Molecule mol = new Molecule(); // molecule is CF 
		
		Atom c = new Atom("C"); 
		mol.addAtom(c); 
		c.setPoint3d(c_coord);
		
		Atom f = new Atom("F"); 
		mol.addAtom(f); 
		f.setPoint3d(f_coord);
		
		Atom h1 = new Atom("H"); 
		mol.addAtom(h1); 
		h1.setPoint3d(h1_coord);
		
		Atom h2 = new Atom("H"); 
		mol.addAtom(h2); 
		h2.setPoint3d(h2_coord);
		
		Atom h3 = new Atom("H"); 
		mol.addAtom(h3); 
		h3.setPoint3d(h3_coord);
		
		mol.addBond(0, 1, 1); // 1
		mol.addBond(0, 2, 1); // 1
		mol.addBond(0, 3, 1); // 1
		mol.addBond(0, 4, 1); // 1
		InductivePartialCharges ipc = new InductivePartialCharges();
		ipc.assignInductivePartialCharges(mol);
		for (int i = 0; i < mol.getAtomCount(); i++) {
			assertEquals(testResult[i], ((Double)mol.getAtom(i).getProperty("InductivePartialCharge")).doubleValue(), 0.1);
			//logger.debug("CHARGE AT " + ac.getAtomAt(i).getSymbol() + " " + ac.getAtomAt(i).getProperty("MMFF94charge"));
		}
	}
}

