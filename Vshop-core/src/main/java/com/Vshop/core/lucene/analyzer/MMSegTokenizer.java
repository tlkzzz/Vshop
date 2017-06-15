package com.Vshop.core.lucene.analyzer;
import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import com.Vshop.core.mmseg4j.core.MMSeg;
import com.Vshop.core.mmseg4j.core.Seg;
import com.Vshop.core.mmseg4j.core.Word;

public class MMSegTokenizer extends Tokenizer {
	private MMSeg mmSeg;
	private CharTermAttribute termAtt;
	private OffsetAttribute offsetAtt;
	private TypeAttribute typeAtt;

	public MMSegTokenizer(Seg seg, Reader input) {
		super(input);
		this.mmSeg = new MMSeg(input, seg);

		this.termAtt = ((CharTermAttribute) addAttribute(CharTermAttribute.class));
		this.offsetAtt = ((OffsetAttribute) addAttribute(OffsetAttribute.class));
		this.typeAtt = ((TypeAttribute) addAttribute(TypeAttribute.class));
	}

	public void reset() throws IOException {
		super.reset();   //加这一句
		this.mmSeg.reset(this.input);
	}

	public final boolean incrementToken() throws IOException {
		clearAttributes();
		Word word = this.mmSeg.next();
		if (word != null) {
			this.termAtt.copyBuffer(word.getSen(), word.getWordOffset(),
					word.getLength());
			this.offsetAtt
					.setOffset(word.getStartOffset(), word.getEndOffset());
			this.typeAtt.setType(word.getType());
			return true;
		}
		end();
		return false;
	}
}