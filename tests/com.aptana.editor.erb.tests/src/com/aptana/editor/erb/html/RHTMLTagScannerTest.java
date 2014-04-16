/**
 * Aptana Studio
 * Copyright (c) 2005-2011 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the GNU Public License (GPL) v3 (with exceptions).
 * Please see the license.html included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 */
package com.aptana.editor.erb.html;

import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.Token;
import org.junit.Test;

import com.aptana.editor.html.HTMLTagScanner;

public class RHTMLTagScannerTest extends AbstractTokenScannerTestCase
{

	@Override
	protected ITokenScanner createTokenScanner()
	{
		return new HTMLTagScanner()
		{
			protected IToken createToken(String string)
			{
				return RHTMLTagScannerTest.this.getToken(string);
			};
		};
	}

	@Test
	public void testSplitTag1()
	{
		String src = "<html <% %> attribute='chris'>";
		IDocument document = new Document(src);

		scanner.setRange(document, 0, 6);
		assertToken(getToken("punctuation.definition.tag.begin.html"), 0, 1);
		assertToken(getToken("entity.name.tag.structure.any.html"), 1, 4);
		assertToken(Token.WHITESPACE, 5, 1);

		scanner.setRange(document, 11, src.length() - 11);
		assertToken(Token.WHITESPACE, 11, 1);
		assertToken(getToken("entity.other.attribute-name.html"), 12, 9);
		assertToken(getToken("punctuation.separator.key-value.html"), 21, 1);
		assertToken(getToken("string.quoted.single.html"), 22, 7);
		assertToken(getToken("punctuation.definition.tag.end.html"), 29, 1);
	}

	@Test
	public void testSplitTag2()
	{
		String src = "<html <% %>attribute='chris'>";
		IDocument document = new Document(src);
		scanner.setRange(document, 0, 6);
		assertToken(getToken("punctuation.definition.tag.begin.html"), 0, 1);
		assertToken(getToken("entity.name.tag.structure.any.html"), 1, 4);
		assertToken(Token.WHITESPACE, 5, 1);

		scanner.setRange(document, 11, src.length() - 11);
		assertToken(getToken("entity.other.attribute-name.html"), 11, 9);
		assertToken(getToken("punctuation.separator.key-value.html"), 20, 1);
		assertToken(getToken("string.quoted.single.html"), 21, 7);
		assertToken(getToken("punctuation.definition.tag.end.html"), 28, 1);
	}

	@Test
	public void testSplitAttribueValue1()
	{
		String src = "<html attribute='<%= Time.now -%>'>";
		IDocument document = new Document(src);
		scanner.setRange(document, 0, 17);
		assertToken(getToken("punctuation.definition.tag.begin.html"), 0, 1);
		assertToken(getToken("entity.name.tag.structure.any.html"), 1, 4);
		assertToken(Token.WHITESPACE, 5, 1);
		assertToken(getToken("entity.other.attribute-name.html"), 6, 9);
		assertToken(getToken("punctuation.separator.key-value.html"), 15, 1);
		assertToken(getToken("string.quoted.single.html"), 16, 1);

		scanner.setRange(document, 33, src.length() - 33);
		assertToken(getToken("string.quoted.single.html"), 33, 1);
		assertToken(getToken("punctuation.definition.tag.end.html"), 34, 1);
	}

	@Test
	public void testSplitAttribueValue2()
	{
		String src = "<html attribute='<%= Time.now -%>' id='id'>";
		IDocument document = new Document(src);
		scanner.setRange(document, 0, 17);
		assertToken(getToken("punctuation.definition.tag.begin.html"), 0, 1);
		assertToken(getToken("entity.name.tag.structure.any.html"), 1, 4);
		assertToken(Token.WHITESPACE, 5, 1);
		assertToken(getToken("entity.other.attribute-name.html"), 6, 9);
		assertToken(getToken("punctuation.separator.key-value.html"), 15, 1);
		assertToken(getToken("string.quoted.single.html"), 16, 1);

		scanner.setRange(document, 33, src.length() - 33);
		assertToken(getToken("string.quoted.single.html"), 33, 1);
		assertToken(Token.WHITESPACE, 34, 1);
		assertToken(getToken("entity.other.attribute-name.id.html"), 35, 2);
		assertToken(getToken("punctuation.separator.key-value.html"), 37, 1);
		assertToken(getToken("string.quoted.single.html"), 38, 4);
		assertToken(getToken("punctuation.definition.tag.end.html"), 42, 1);
	}

	@Test
	public void testSplitAttribueValue3()
	{
		String src = "<html attribute=' <%= Time.now -%> '>";
		IDocument document = new Document(src);
		scanner.setRange(document, 0, 18);
		assertToken(getToken("punctuation.definition.tag.begin.html"), 0, 1);
		assertToken(getToken("entity.name.tag.structure.any.html"), 1, 4);
		assertToken(Token.WHITESPACE, 5, 1);
		assertToken(getToken("entity.other.attribute-name.html"), 6, 9);
		assertToken(getToken("punctuation.separator.key-value.html"), 15, 1);
		assertToken(getToken("string.quoted.single.html"), 16, 2);

		scanner.setRange(document, 34, src.length() - 34);
		assertToken(getToken("string.quoted.single.html"), 34, 2);
		assertToken(getToken("punctuation.definition.tag.end.html"), 36, 1);
	}

}
