/* $RCSfile$
 * $Author: egonw $
 * $Date: 2006-04-21 10:59:31 +0200 (Fr, 21 Apr 2006) $
 * $Revision: 6067 $
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
 */

package org.openscience.cdk.test;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.openscience.cdk.test.qsar.descriptors.atomic.AtomDegreeDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.AtomHybridizationDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.AtomHybridizationVSEPRDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.AtomValenceDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.BondsToAtomDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.CovalentRadiusDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.DistanceToAtomDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.EffectiveAtomPolarizabilityDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.IPAtomicDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.InductiveAtomicHardnessDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.InductiveAtomicSoftnessDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.IsProtonInAromaticSystemDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.IsProtonInConjugatedPiSystemDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.PartialPiChargeDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.PartialSigmaChargeDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.PartialTChargeMMFF94DescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.PartialTChargePEOEDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.PeriodicTablePositionDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.PiElectronegativityDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.ProtonTotalPartialChargeDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.RDFProtonDescriptorTest_G3R;
import org.openscience.cdk.test.qsar.descriptors.atomic.RDFProtonDescriptorTest_GDR;
import org.openscience.cdk.test.qsar.descriptors.atomic.RDFProtonDescriptorTest_GHR;
import org.openscience.cdk.test.qsar.descriptors.atomic.RDFProtonDescriptorTest_GHR_topol;
import org.openscience.cdk.test.qsar.descriptors.atomic.RDFProtonDescriptorTest_GSR;
import org.openscience.cdk.test.qsar.descriptors.atomic.SigmaElectronegativityDescriptorTest;
import org.openscience.cdk.test.qsar.descriptors.atomic.VdWRadiusDescriptorTest;

/**
 * TestSuite that runs all the sample tests.
 *
 * @cdk.module test-qsarAtomic
 * @cdk.depends log4j.jar
 * @cdk.depends junit.jar
 */
public class MqsarAtomicTests {

    public static Test suite() {

        TestSuite suite = new TestSuite("All QSAR Tests");

        // Individual Tests - Please add correlatively	

//      from cdk.test.qsar.atomic
        suite.addTest(AtomDegreeDescriptorTest.suite());
        suite.addTest(AtomHybridizationDescriptorTest.suite());
        suite.addTest(AtomHybridizationVSEPRDescriptorTest.suite());
        suite.addTest(AtomValenceDescriptorTest.suite());
        suite.addTest(BondsToAtomDescriptorTest.suite());
        suite.addTest(CovalentRadiusDescriptorTest.suite());
        suite.addTest(DistanceToAtomDescriptorTest.suite());
        suite.addTest(EffectiveAtomPolarizabilityDescriptorTest.suite());
        suite.addTest(InductiveAtomicHardnessDescriptorTest.suite());
        suite.addTest(InductiveAtomicSoftnessDescriptorTest.suite());
        suite.addTest(IPAtomicDescriptorTest.suite());
        suite.addTest(IsProtonInAromaticSystemDescriptorTest.suite());
        suite.addTest(IsProtonInConjugatedPiSystemDescriptorTest.suite());
        suite.addTest(PartialPiChargeDescriptorTest.suite());
        suite.addTest(PartialSigmaChargeDescriptorTest.suite());
        suite.addTest(PartialTChargeMMFF94DescriptorTest.suite());
        suite.addTest(PartialTChargePEOEDescriptorTest.suite());
        suite.addTest(PeriodicTablePositionDescriptorTest.suite());
        suite.addTest(PiElectronegativityDescriptorTest.suite());
        suite.addTest(ProtonTotalPartialChargeDescriptorTest.suite());
        suite.addTest(RDFProtonDescriptorTest_G3R.suite());
        suite.addTest(RDFProtonDescriptorTest_GDR.suite());
        suite.addTest(RDFProtonDescriptorTest_GHR.suite());
        suite.addTest(RDFProtonDescriptorTest_GHR_topol.suite());
        suite.addTest(RDFProtonDescriptorTest_GSR.suite());
        suite.addTest(SigmaElectronegativityDescriptorTest.suite());
        suite.addTest(VdWRadiusDescriptorTest.suite());
        
        return suite;
    }

}
