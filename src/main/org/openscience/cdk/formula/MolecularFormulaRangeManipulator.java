/* $RCSfile$
 * $Author$ 
 * $Date$
 * $Revision$
 * 
 *  Copyright (C) 2007  Miguel Rojasch <miguelrojasch@users.sf.net>
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
package org.openscience.cdk.formula;

import java.util.Iterator;

import org.openscience.cdk.annotations.TestClass;
import org.openscience.cdk.annotations.TestMethod;
import org.openscience.cdk.interfaces.IIsotope;


/**
 * Class with convenience methods that provide methods to manipulate
 * MolecularFormulaRange's. 
 *
 * @cdk.module  formula
 * @author      miguelrojasch
 * @cdk.created 2007-11-20
 */
@TestClass("org.openscience.cdk.formula.MolecularFormulaRangeManipulatorTest")
public class MolecularFormulaRangeManipulator {
	
	/**
	 * Extract from a set of MolecularFormula the range of each each element found and
	 * put the element and occurrence in a new MolecularFormulaRange.
	 * 
	 * @param mfSet    The set of molecularFormules to inspect
	 * @return         A MolecularFormulaRange containing range occurrence of the elements
	 */
    @TestMethod("testGetRange_IMolecularFormulaSet")
	public static MolecularFormulaRange getRange(IMolecularFormulaSet mfSet){
		MolecularFormulaRange mfRange = new MolecularFormulaRange();
		
		for(IMolecularFormula mf: mfSet.molecularFormulas()){
			Iterator<IIsotope> iterIsot = mf.isotopes();
			while(iterIsot.hasNext()){
				IIsotope isotope = iterIsot.next();
				int occur_new = mf.getIsotopeCount(isotope);
				if(!mfRange.contains(isotope)){
						mfRange.addIsotope(isotope,occur_new,occur_new);
				}else{
					int occur_old_Max = mfRange.getIsotopeCountMax(isotope);
					int occur_old_Min = mfRange.getIsotopeCountMin(isotope);
					if(occur_new > occur_old_Max){
						mfRange.removeIsotope(isotope);
						mfRange.addIsotope(isotope,occur_old_Min,occur_new);
					}else if(occur_new < occur_old_Min){
						mfRange.removeIsotope(isotope);
						mfRange.addIsotope(isotope,occur_new,occur_old_Max);
					}
				}
			}
		}
		// looking for those Isotopes which are not contained which then should be 0.
		for(IMolecularFormula mf: mfSet.molecularFormulas()){
			if(mf.getIsotopeCount() != mfRange.getIsotopeCount()){
				Iterator<IIsotope> iterIsot = mfRange.isotopes();
				while(iterIsot.hasNext()){
					IIsotope isotope = iterIsot.next();
					if(!mf.contains(isotope)){
						int occurMax = mfRange.getIsotopeCountMax(isotope);
						mfRange.addIsotope(isotope,0,occurMax);
					}
				}
			}
		}
		return mfRange;
	}
	/**
	 * Returns the maximal occurrence of the IIsotope into IMolecularFormula 
	 * from this MolelecularFormulaRange.
	 * 
	 * @param   The MolecularFormulaRange to analyze
	 * @return  A IMolecularFormula containing the maximal occurrence of each isotope 
	 */
    @TestMethod("testGetMaximalFormula_MolecularFormulaRange")
	public static IMolecularFormula getMaximalFormula(MolecularFormulaRange mfRange){
		IMolecularFormula formula = new MolecularFormula();
		
		Iterator<IIsotope> iterIsot = mfRange.isotopes();
		while(iterIsot.hasNext()){
			IIsotope isotope = iterIsot.next();
			formula.addIsotope(isotope,mfRange.getIsotopeCountMax(isotope));
		}
		
		return formula;
	}

	/**
	 * Returns the minimal occurrence of the IIsotope into IMolecularFormula 
	 * from this MolelecularFormulaRange.
	 * 
	 * @param   The MolecularFormulaRange to analyze
	 * @return  A IMolecularFormula containing the minimal occurrence of each isotope 
	 */
    @TestMethod("testGetMinimalFormula_MolecularFormulaRange")
	public static IMolecularFormula getMinimalFormula(MolecularFormulaRange mfRange){
		IMolecularFormula formula = new MolecularFormula();
		
		Iterator<IIsotope> iterIsot = mfRange.isotopes();
		while(iterIsot.hasNext()){
			IIsotope isotope = iterIsot.next();
			formula.addIsotope(isotope,mfRange.getIsotopeCountMin(isotope));
		}
		
		return formula;
	}

}

