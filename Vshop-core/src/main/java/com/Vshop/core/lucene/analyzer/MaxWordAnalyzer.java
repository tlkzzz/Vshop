package com.Vshop.core.lucene.analyzer;

import java.io.File;

import com.Vshop.core.mmseg4j.core.Dictionary;
import com.Vshop.core.mmseg4j.core.MaxWordSeg;
import com.Vshop.core.mmseg4j.core.Seg;

public class MaxWordAnalyzer extends MMSegAnalyzer {
	public MaxWordAnalyzer() {
	}

	public MaxWordAnalyzer(String path) {
		super(path);
	}

	public MaxWordAnalyzer(Dictionary dic) {
		super(dic);
	}

	public MaxWordAnalyzer(File path) {
		super(path);
	}

	protected Seg newSeg() {
		return new MaxWordSeg(this.dic);
	}
}
