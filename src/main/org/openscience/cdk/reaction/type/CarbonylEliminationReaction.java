/*
 *  $RCSfile$
 *  $Author$
 *  $Date$
 *  $Revision$
 *
 *  Copyright (C) 2008 Miguel Rojas <miguelrojasch@users.sf.net>
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
package org.openscience.cdk.reaction.type;

import java.util.ArrayList;
import java.util.Iterator;

import org.openscience.cdk.CDKConstants;
import org.openscience.cdk.exception.CDKException;
import org.openscience.cdk.interfaces.IAtom;
import org.openscience.cdk.interfaces.IBond;
import org.openscience.cdk.interfaces.IMolecule;
import org.openscience.cdk.interfaces.IMoleculeSet;
import org.openscience.cdk.interfaces.IReaction;
import org.openscience.cdk.interfaces.IReactionSet;
import org.openscience.cdk.reaction.IReactionMechanism;
import org.openscience.cdk.reaction.IReactionProcess;
import org.openscience.cdk.reaction.ReactionEngine;
import org.openscience.cdk.reaction.ReactionSpecification;
import org.openscience.cdk.reaction.mechanism.HeterolyticCleavageMechanism;
import org.openscience.cdk.tools.LoggingTool;

/**
 * <p>IReactionProcess which participate mass spectrum process.  
 * This reaction could be represented as RC-C#[O+] => R[C] + |C#[O+]</p>
 * <p>Make sure that the molecule has the correspond lone pair electrons
 * for each atom. You can use the method: <pre> LonePairElectronChecker </pre>
 * <p>It is processed by the HeterolyticCleavageMechanism class</p>
 * 
 * <pre>
 *  IMoleculeSet setOfReactants = DefaultChemObjectBuilder.getInstance().newMoleculeSet();
 *  setOfReactants.addMolecule(new Molecule());
 *  IReactionProcess type = new CarbonylEliminationReaction();
 *  Object[] params = {Boolean.FALSE};
    type.setParameters(params);
 *  IReactionSet setOfReactions = type.initiate(setOfReactants, null);
 *  </pre>
 * 
 * <p>We have the possibility to localize the reactive center. Good method if you
 * want to localize the reaction in a fixed point</p>
 * <pre>atoms[0].setFlag(CDKConstants.REACTIVE_CENTER,true);</pre>
 * <p>Moreover you must put the parameter Boolean.TRUE</p>
 * <p>If the reactive center is not localized then the reaction process will
 * try to find automatically the possible reactive center.</p>
 * 
 * 
 * @author         Miguel Rojas
 * 
 * @cdk.created    2006-10-16
 * @cdk.module     reaction
 * @cdk.svnrev  $Revision$
 * @cdk.set        reaction-types
 * 
 * @see HeterolyticCleavageMechanism
 **/
public class CarbonylEliminationReaction extends ReactionEngine implements IReactionProcess{
	private LoggingTool logger;
	private IReactionMechanism mechanism;
	
	/**
	 * Constructor of the CarbonylEliminationReaction object.
	 *
	 */
	public CarbonylEliminationReaction(){
		logger = new LoggingTool(this);
		mechanism = new HeterolyticCleavageMechanism();
	}
	/**
	 *  Gets the specification attribute of the CarbonylEliminationReaction object.
	 *
	 *@return    The specification value
	 */
	public ReactionSpecification getSpecification() {
		return new ReactionSpecification(
				"http://almost.cubic.uni-koeln.de/jrg/Members/mrc/reactionDict/reactionDict#CarbonylElimination",
				this.getClass().getName(),
				"$Id$",
				"The Chemistry Development Kit");
	}
	
	/**
	 *  Initiate process.
	 *
	 *@param  reactants         reactants of the reaction
	 *@param  agents            agents of the reaction (Must be in this case null)
	 *
	 *@exception  CDKException  Description of the Exception
	 */
	public IReactionSet initiate(IMoleculeSet reactants, IMoleculeSet agents) throws CDKException{
		logger.debug("initiate reaction: CarbonylEliminationReaction");
		
		if (reactants.getMoleculeCount() != 1) {
			throw new CDKException("CarbonylEliminationReaction only expects one reactant");
		}
		if (agents != null) {
			throw new CDKException("CarbonylEliminationReaction don't expects agents");
		}
		
		IReactionSet setOfReactions = reactants.getBuilder().newReactionSet();
		IMolecule reactant = reactants.getMolecule(0);

		/* if the parameter hasActiveCenter is not fixed yet, set the active centers*/
		if(!(Boolean)paramsMap.get("hasActiveCenter")){
			setActiveCenters(reactant);
		}
		
		Iterator<IAtom> atomis = reactant.atoms();
		while(atomis.hasNext()){
			IAtom atomi = atomis.next();
			if(atomi.getFlag(CDKConstants.REACTIVE_CENTER) && atomi.getSymbol().equals("O") &&
					atomi.getFormalCharge() == 1){
				
				Iterator<IBond> bondis = reactant.getConnectedBondsList(atomi).iterator();
				while(bondis.hasNext()){
					IBond bondi = bondis.next();
					
					if(bondi.getFlag(CDKConstants.REACTIVE_CENTER) && bondi.getOrder()== IBond.Order.TRIPLE){
						IAtom atomj = bondi.getConnectedAtom(atomi);
						if(atomj.getFlag(CDKConstants.REACTIVE_CENTER)){ 
							Iterator<IBond> bondjs = reactant.getConnectedBondsList(atomj).iterator();
							while (bondjs.hasNext()) {
					            IBond bondj = bondjs.next();
					            
					            if(bondj.equals(bondi))
					            	continue;
	
					            if(bondj.getFlag(CDKConstants.REACTIVE_CENTER) && bondj.getOrder() == IBond.Order.SINGLE){
									
					            	IAtom atomk = bondj.getConnectedAtom(atomj);
									if(atomk.getFlag(CDKConstants.REACTIVE_CENTER)&& atomk.getFormalCharge() == 0 ){

										ArrayList<IAtom> atomList = new ArrayList<IAtom>();
					                	atomList.add(atomk);
					                	atomList.add(atomj);
					                	ArrayList<IBond> bondList = new ArrayList<IBond>();
					                	bondList.add(bondj);

										IMoleculeSet moleculeSet = reactant.getBuilder().newMoleculeSet();
										moleculeSet.addMolecule(reactant);
										IReaction reaction = mechanism.initiate(moleculeSet, atomList, bondList);
										if(reaction == null)
											continue;
										else
											setOfReactions.addReaction(reaction);
					                	
									}
					            }
							}
						}
					}
				}
			}
		}
		return setOfReactions;
		
	}
	/**
	 * set the active center for this molecule. 
	 * The active center will be those which correspond with RC-C#[O+]. 
	 * <pre>
	 * C: Atom
	 * -: single bond
	 * C: Atom
	 * #: triple bond
	 * O: Atom with formal charge = 1
	 *  </pre>
	 * 
	 * @param reactant The molecule to set the activity
	 * @throws CDKException 
	 */
	private void setActiveCenters(IMolecule reactant) throws CDKException {
		Iterator<IAtom> atomis = reactant.atoms();
		while(atomis.hasNext()){
			IAtom atomi = atomis.next();
			if(atomi.getSymbol().equals("O") &&
					atomi.getFormalCharge() == 1){
				
				Iterator<IBond> bondis = reactant.getConnectedBondsList(atomi).iterator();
				while(bondis.hasNext()){
					IBond bondi = bondis.next();
					
					if(bondi.getOrder()== IBond.Order.TRIPLE){
						IAtom atomj = bondi.getConnectedAtom(atomi);
						Iterator<IBond> bondjs = reactant.getConnectedBondsList(atomj).iterator();
						while (bondjs.hasNext()) {
				            IBond bondj = bondjs.next();
				            
				            if(bondj.equals(bondi))
				            	continue;

				            if(bondj.getOrder() == IBond.Order.SINGLE){
								
				            	IAtom atomk = bondj.getConnectedAtom(atomj);
								if(atomk.getFormalCharge() == 0 ){
									atomi.setFlag(CDKConstants.REACTIVE_CENTER,true);
									bondi.setFlag(CDKConstants.REACTIVE_CENTER,true); 
									atomj.setFlag(CDKConstants.REACTIVE_CENTER,true); 
									bondj.setFlag(CDKConstants.REACTIVE_CENTER,true);
									atomk.setFlag(CDKConstants.REACTIVE_CENTER,true);
								}
				            }
						}
					 }
				}
			}
		}
	}
}
