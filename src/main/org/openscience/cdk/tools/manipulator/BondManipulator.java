/* $Revision$ $Author$ $Date$
 * 
 * Copyright (C) 2003-2007  The Chemistry Development Kit (CDK) project
 * 
 * Contact: cdk-devel@lists.sourceforge.net
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
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *  */
package org.openscience.cdk.tools.manipulator;

import java.util.Iterator;
import java.util.List;

import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IBond;

/**
 * Class with convenience methods that provide methods to manipulate
 * AtomContainer's. For example:
 * <pre>
 * AtomContainerManipulator.replaceAtomByAtom(container, atom1, atom2);
 * </pre>
 * will replace the Atom in the AtomContainer, but in all the ElectronContainer's
 * it participates too.
 *
 * @cdk.module  atomtype
 * @cdk.svnrev  $Revision$
 *
 * @author  Egon Willighagen
 * @cdk.created 2003-08-07
 */
public class BondManipulator {
	
	/**
	 * Constructs an array of Atom objects from Bond.
	 * @param  container The Bond object.
	 * @return The array of Atom objects.
	 */
	public static IAtom[] getAtomArray(IBond container) {
		IAtom[] ret = new IAtom[container.getAtomCount()];
		for (int i = 0; i < ret.length; ++i) ret[i] = container.getAtom(i);
		return ret;
	}
	
	/**
	 * Returns true if the first bond has a lower bond order than the second bond.
	 * It returns false if the bond order is equal, and if the order of the first
	 * bond is larger than that of the second. Also returns false if either bond
	 * order is unset.
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static boolean isLowerOrder(IBond.Order first, IBond.Order second) {
		if (first == null || second == null) return false;
		
		if (second == IBond.Order.QUADRUPLE) {
			if (first !=  IBond.Order.QUADRUPLE) return true;
		}
		if (second == IBond.Order.TRIPLE) {
			if (first ==  IBond.Order.SINGLE ||
				first ==  IBond.Order.DOUBLE) return true;
		} else if (second == IBond.Order.DOUBLE) {
			if (first ==  IBond.Order.SINGLE) return true;
		}
		return false;
	}
	
	/**
	 * Returns true if the first bond has a higher bond order than the second bond.
	 * It returns false if the bond order is equal, and if the order of the first
	 * bond is lower than that of the second. Also returns false if either bond
	 * order is unset.
	 * 
	 * @param first
	 * @param second
	 * @return
	 */
	public static boolean isHigherOrder(IBond.Order first, IBond.Order second) {
		if (first == null || second == null) return false;
		
		if (second == IBond.Order.QUADRUPLE) {
			return false;
		}
		if (second == IBond.Order.TRIPLE) {
			if (first ==  IBond.Order.QUADRUPLE) return true;
		} else if (second == IBond.Order.DOUBLE) {
			if (first ==  IBond.Order.TRIPLE ||
				first ==  IBond.Order.QUADRUPLE) return true;
		} else if (second == IBond.Order.SINGLE) {
			if (first !=  IBond.Order.SINGLE) return true;
		}
		return false;
	}
	
	/**
	 * Returns the IBond.Order one higher. Does not increase the bond order
	 * beyond the QUADRUPLE bond order.
	 */
	public static IBond.Order increaseBondOrder(IBond.Order oldOrder) {
		if (oldOrder == IBond.Order.TRIPLE) {
			return IBond.Order.QUADRUPLE;
		} else if (oldOrder == IBond.Order.DOUBLE) {
			return IBond.Order.TRIPLE;
		} else if (oldOrder == IBond.Order.SINGLE) {
			return IBond.Order.DOUBLE;
		}
		return oldOrder;
	}
	public static void increaseBondOrder(IBond bond) {
		bond.setOrder(increaseBondOrder(bond.getOrder()));
	}

	/**
	 * Returns the IBond.Order one lower. Does not decrease the bond order
	 * lower the QUADRUPLE bond order.
	 */
	public static IBond.Order decreaseBondOrder(IBond.Order oldOrder) {
		if (oldOrder == IBond.Order.TRIPLE) {
			return IBond.Order.DOUBLE;
		} else if (oldOrder == IBond.Order.DOUBLE) {
			return IBond.Order.SINGLE;
		} else if (oldOrder == IBond.Order.QUADRUPLE) {
			return IBond.Order.TRIPLE;
		}
		return oldOrder;
	}
	public static void decreaseBondOrder(IBond bond) {
		bond.setOrder(decreaseBondOrder(bond.getOrder()));
	}

	/**
	 * Convenience method to convert a double into an IBond.Order.
	 * Returns NULL if the bond order is not 1.0, 2.0, 3.0 and 4.0.
	 */
	public static IBond.Order createBondOrder(double bondOrder) {
		if (bondOrder == 1.0) {
			return IBond.Order.SINGLE;
		} else if (bondOrder == 2.0) {
			return IBond.Order.DOUBLE;
		} else if (bondOrder == 3.0) {
			return IBond.Order.TRIPLE;
		} else if (bondOrder == 4.0) {
			return IBond.Order.QUADRUPLE;
		}
		return null;
	}
	
	public static double destroyBondOrder(IBond.Order bondOrder) {
		if (bondOrder == IBond.Order.SINGLE) {
			return 1.0;
		} else if (bondOrder == IBond.Order.DOUBLE) {
			return 2.0;
		} else if (bondOrder == IBond.Order.TRIPLE) {
			return 3.0;
		}
		return 4.0;
	}
	
	/**
	 * Returns the maximum bond order for a List of bonds.
	 * 
	 * @param bonds
	 * @return
	 */
	public static IBond.Order getMaximumBondOrder(List<IBond> bonds) {
		return getMaximumBondOrder(bonds.iterator());
	}		
	public static IBond.Order getMaximumBondOrder(Iterator<IBond> bonds) {
		IBond.Order maxOrder = IBond.Order.SINGLE;
		while (bonds.hasNext()) {
			IBond bond = bonds.next();
			if (isHigherOrder(bond.getOrder(), maxOrder)) maxOrder = bond.getOrder();
		}
		return maxOrder;
	}

	public static int getSingleBondEquivalentSum(List<IBond> bonds) {
		return getSingleBondEquivalentSum(bonds.iterator());
	}
	
	public static int getSingleBondEquivalentSum(Iterator<IBond> bonds) {
		int sum = 0;
		while (bonds.hasNext()) {
			IBond nextBond = bonds.next();
			if (nextBond.getOrder() == IBond.Order.SINGLE) {
				sum += 1;
			} else if (nextBond.getOrder() == IBond.Order.DOUBLE) {
				sum += 2;
			} else if (nextBond.getOrder() == IBond.Order.TRIPLE) {
				sum += 3;
			} else if (nextBond.getOrder() == IBond.Order.QUADRUPLE) {
				sum += 4;
			}
		}
		return sum;
	}

	
}

