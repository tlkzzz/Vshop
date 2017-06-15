package com.Vshop.core.lucene.analyzer;

import java.io.IOException;

import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

public class TokenUtils {

	public static Token nextToken(TokenStream input, Token reusableToken)
			throws IOException {
		if (input == null) {
			return null;
		}
		if (!input.incrementToken()) {
			return null;
		}

		CharTermAttribute termAtt = (CharTermAttribute) input
				.getAttribute(CharTermAttribute.class);
		OffsetAttribute offsetAtt = (OffsetAttribute) input
				.getAttribute(OffsetAttribute.class);
		TypeAttribute typeAtt = (TypeAttribute) input
				.getAttribute(TypeAttribute.class);

		if (reusableToken == null) {
			reusableToken = new Token();
		}

		reusableToken.clear();
		if (termAtt != null) {
			reusableToken.copyBuffer(termAtt.buffer(), 0, termAtt.length());
		}
		if (offsetAtt != null) {
			reusableToken.setOffset(offsetAtt.startOffset(),
					offsetAtt.endOffset());
		}

		if (typeAtt != null) {
			reusableToken.setType(typeAtt.type());
		}

		return reusableToken;
	}
}
