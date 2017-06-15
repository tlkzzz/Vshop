package com.Vshop.service.module.test;

/**
 * 分词器的效果展示
 * @author cgl
 * 2015年08月17日17:03:31
 */
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

import com.Vshop.core.lucene.analyzer.SimpleAnalyzer;

public class AnalyzerTest {
	
	public static void main(String[] args) {
	}
	
	/**
	 * 测试入口
	 */
	public static void analyzerTestDo(String testStr){
		Analyzer analyzer = new SimpleAnalyzer("/Users/chen/B2B2C/trunk/Vshop-service/src/main/resources/data/words.dic");
		try {
			displayTokens(analyzer,testStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void displayTokens(Analyzer analyzer,String text) throws IOException {
		TokenStream tokenStream = analyzer.tokenStream("text", text);
		displayTokens(tokenStream);
	}
	
	public static void displayTokens(TokenStream tokenStream) throws IOException {
		OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
		PositionIncrementAttribute positionIncrementAttribute = tokenStream.addAttribute(PositionIncrementAttribute.class);
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
		TypeAttribute typeAttribute = tokenStream.addAttribute(TypeAttribute.class);
		
		tokenStream.reset();
		int position = 0;
		while (tokenStream.incrementToken()) {
			int increment = positionIncrementAttribute.getPositionIncrement();
			if(increment > 0) {
				position = position + increment;
				System.out.print(position + ":");
			}
		    int startOffset = offsetAttribute.startOffset();
		    int endOffset = offsetAttribute.endOffset();
		    String term = charTermAttribute.toString();
		    System.out.println("[" + term + "]" + ":(" + startOffset + "-->" + endOffset + "):" + typeAttribute.type());
		}
	}
}
