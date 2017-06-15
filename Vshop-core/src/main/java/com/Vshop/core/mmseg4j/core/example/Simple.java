package com.Vshop.core.mmseg4j.core.example;

import java.io.IOException;

import com.Vshop.core.mmseg4j.core.Seg;
import com.Vshop.core.mmseg4j.core.SimpleSeg;

/**
 * 
 * @author chenlb 2009-3-14 上午12:38:40
 */
public class Simple extends Complex {
	
	protected Seg getSeg() {

		return new SimpleSeg(dic);
	}

	public static void main(String[] args) throws IOException {
		new Simple().run(args);
	}

}
