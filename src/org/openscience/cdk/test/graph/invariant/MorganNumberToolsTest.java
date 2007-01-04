/* $RCSfile$    
 * $Author$    
 * $Date$    
 * $Revision$
 * 
 * Copyright (C) 1997-2007  The Chemistry Development Kit (CDK) project
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
package org.openscience.cdk.test.graph.invariant;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openscience.cdk.AtomContainer;
import org.openscience.cdk.Molecule;
import org.openscience.cdk.graph.invariant.MorganNumbersTools;
import org.openscience.cdk.templates.MoleculeFactory;
import org.openscience.cdk.test.CDKTestCase;

/**
 * Checks the functionality of the MorganNumberTools.
 *
 * @cdk.module test-extra
 */
public class MorganNumberToolsTest extends CDKTestCase
{
	// This is an array with the expected Morgan Numbers for a-pinene
	int[] reference = {28776,17899,23549,34598,31846,36393,9847,45904,15669,15669};
	public MorganNumberToolsTest(String name)
	{
		super(name);
	}
	
	public void setUp()
	{

	}

	public static Test suite() {
		return new TestSuite(MorganNumberToolsTest.class);
	}

	public void testMorganNumbers()
	{
		Molecule mol = MoleculeFactory.makeAlphaPinene();
		int[] morganNumbers = null;
		try
		{
			morganNumbers = MorganNumbersTools.getMorganNumbers((AtomContainer)mol);
		}
		catch(Exception exc)
		{
			exc.printStackTrace();
			System.err.println("An Exception");
			fail();
		}
		assertTrue(morganNumbers.length == reference.length);
		for (int f = 0; f < morganNumbers.length; f ++)
		{
			//logger.debug(morganNumbers[f]);
			assertTrue(reference[f] == morganNumbers[f]);
		}
	}

	public static void main(String[] args)
	{
		new MorganNumberToolsTest("MorganNumberToolsTest").testMorganNumbers();
	}
}
