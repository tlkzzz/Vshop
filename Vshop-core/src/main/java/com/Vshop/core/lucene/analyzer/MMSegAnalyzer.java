package com.Vshop.core.lucene.analyzer;

import java.io.File;
import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;

import com.Vshop.core.mmseg4j.core.Dictionary;
import com.Vshop.core.mmseg4j.core.MaxWordSeg;
import com.Vshop.core.mmseg4j.core.Seg;

public class MMSegAnalyzer extends Analyzer {
	protected Dictionary dic;

	public MMSegAnalyzer() {
		this.dic = Dictionary.getInstance();
	}

	public MMSegAnalyzer(String path) {
		this.dic = Dictionary.getInstance(path);
	}

	public MMSegAnalyzer(File path) {
		this.dic = Dictionary.getInstance(path);
	}

	public MMSegAnalyzer(Dictionary dic) {
		this.dic = dic;
	}

	protected Seg newSeg() {
		return new MaxWordSeg(this.dic);
	}

	public Dictionary getDict() {
		return this.dic;
	}

	protected Analyzer.TokenStreamComponents createComponents(String fieldName,
			Reader reader) {
		return new Analyzer.TokenStreamComponents(new MMSegTokenizer(newSeg(),
				reader));
	}
}
