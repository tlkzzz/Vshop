package com.Vshop.core.lucene.analyzer;

import java.io.File;

import com.Vshop.core.mmseg4j.core.Dictionary;
import com.Vshop.core.mmseg4j.core.Seg;
import com.Vshop.core.mmseg4j.core.SimpleSeg;

public class SimpleAnalyzer extends MMSegAnalyzer {
	public SimpleAnalyzer() {
	}

	public SimpleAnalyzer(String path) {
		super(path);
	}

	public SimpleAnalyzer(Dictionary dic) {
		super(dic);
	}

	public SimpleAnalyzer(File path) {
		super(path);
	}

	protected Seg newSeg() {
		return new SimpleSeg(this.dic);
	}
}
