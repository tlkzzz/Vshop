package com.Vshop.core.lucene.analyzer;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import org.apache.lucene.analysis.Token;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;


public class CutLetterDigitFilter extends TokenFilter {
	protected Queue<Token> tokenQueue = new LinkedList();
	private CharTermAttribute termAtt;
	private OffsetAttribute offsetAtt;
	private TypeAttribute typeAtt;
	private Token reusableToken;

	public CutLetterDigitFilter(TokenStream input) {
		super(input);

		this.reusableToken = new Token();
		this.termAtt = ((CharTermAttribute) addAttribute(CharTermAttribute.class));
		this.offsetAtt = ((OffsetAttribute) addAttribute(OffsetAttribute.class));
		this.typeAtt = ((TypeAttribute) addAttribute(TypeAttribute.class));
	}

	public Token next(Token reusableToken) throws IOException {
		return nextToken(reusableToken);
	}

	private Token nextToken(Token reusableToken) throws IOException {
		assert (reusableToken != null);

		Token nextToken = (Token) this.tokenQueue.poll();
		if (nextToken != null) {
			return nextToken;
		}

		nextToken = TokenUtils.nextToken(this.input, reusableToken);

		if ((nextToken != null)
				&& (("letter_or_digit".equalsIgnoreCase(nextToken.type())) || ("digit_or_letter"
						.equalsIgnoreCase(nextToken.type())))) {
			char[] buffer = nextToken.buffer();
			int length = nextToken.length();
			byte lastType = (byte) Character.getType(buffer[0]);
			int termBufferOffset = 0;
			int termBufferLength = 0;
			for (int i = 0; i < length; i++) {
				byte type = (byte) Character.getType(buffer[i]);
				if (type <= 4) {
					type = 2;
				}
				if (type != lastType) {
					addToken(nextToken, termBufferOffset, termBufferLength,
							lastType);

					termBufferOffset += termBufferLength;
					termBufferLength = 0;

					lastType = type;
				}

				termBufferLength++;
			}
			if (termBufferLength > 0) {
				addToken(nextToken, termBufferOffset, termBufferLength,
						lastType);
			}
			nextToken = (Token) this.tokenQueue.poll();
		}

		return nextToken;
	}

	private void addToken(Token oriToken, int termBufferOffset,
			int termBufferLength, byte type) {
		Token token = new Token(oriToken.buffer(), termBufferOffset,
				termBufferLength, oriToken.startOffset() + termBufferOffset,
				oriToken.startOffset() + termBufferOffset + termBufferLength);

		if (type == 9)
			token.setType("digit");
		else {
			token.setType("letter");
		}

		this.tokenQueue.offer(token);
	}

	public void close() throws IOException {
		super.close();
		this.tokenQueue.clear();
	}

	public void reset() throws IOException {
		super.reset();
		this.tokenQueue.clear();
	}

	public final boolean incrementToken() throws IOException {
		clearAttributes();
		Token token = nextToken(this.reusableToken);
		if (token != null) {
			this.termAtt.copyBuffer(token.buffer(), 0, token.length());
			this.offsetAtt.setOffset(token.startOffset(), token.endOffset());
			this.typeAtt.setType(token.type());
			return true;
		}
		end();
		return false;
	}

	public void end() {
		try {
			reset();
		} catch (IOException e) {
		}
	}
}
