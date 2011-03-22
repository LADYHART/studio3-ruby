/**
 * Aptana Studio
 * Copyright (c) 2005-2011 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the GNU Public License (GPL) v3 (with exceptions).
 * Please see the license.html included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 */
package com.aptana.editor.ruby.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.aptana.editor.ruby.RubyCodeScannerTest;
import com.aptana.editor.ruby.RubyContentAssistTest;
import com.aptana.editor.ruby.RubyEditorTest;
import com.aptana.editor.ruby.RubyParserTest;
import com.aptana.editor.ruby.RubyRegexScannerTest;
import com.aptana.editor.ruby.RubySourcePartitionScannerTest;
import com.aptana.editor.ruby.RubyTokenScannerTest;
import com.aptana.editor.ruby.outline.RubyOutlineTest;

public class AllTests
{

	public static Test suite()
	{
		TestSuite suite = new TestSuite("Test for com.aptana.editor.ruby.tests");
		// $JUnit-BEGIN$
		suite.addTestSuite(RubySourcePartitionScannerTest.class);
		suite.addTestSuite(RubyTokenScannerTest.class);
		suite.addTestSuite(RubyCodeScannerTest.class);
		suite.addTestSuite(RubyRegexScannerTest.class);
		suite.addTestSuite(RubyParserTest.class);
		suite.addTestSuite(RubyEditorTest.class);
		suite.addTestSuite(RubyOutlineTest.class);
		suite.addTestSuite(RubyContentAssistTest.class);
		// $JUnit-END$
		return suite;
	}
}
