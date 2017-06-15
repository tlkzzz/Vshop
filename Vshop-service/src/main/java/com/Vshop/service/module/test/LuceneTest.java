package com.Vshop.service.module.test;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.Vshop.core.lucene.analyzer.SimpleAnalyzer;

public class LuceneTest {

	public static final String filePath = "/Users/chen/luceneTest";
	
	public static void main(String[] args) {
		String str = "iphone 6s";
		AnalyzerTest analyzerTest = new AnalyzerTest();
		analyzerTest.analyzerTestDo(str);
//		addIndex(str);
//		deleteIndex();
//		search("紧绷");
	}
	
	public static void search(String keyword){

		Directory directory = null;
		IndexSearcher indexSearcher = null;
		IndexReader indexReader = null;
		
		try{
		File indexDir = new File(filePath);
		//目录
		directory = FSDirectory.open(indexDir);
		//获得indexreader
		indexReader = DirectoryReader.open(directory);
		//获得IndexSearch
		indexSearcher = new IndexSearcher(indexReader);
		Query query = new TermQuery(new Term("content",keyword));
		TopDocs docs = indexSearcher.search(query, 100000);
		ScoreDoc [] scoreDoc = docs.scoreDocs;
		for(ScoreDoc docc : scoreDoc){
			Document document = indexSearcher.doc(docc.doc);
			System.out.println("搜索到的结果:\t" + document.get("content"));
		}
		
		}catch(Exception e){
		}finally{
			if(indexReader != null){
				try {
					indexReader.close();
					indexReader = null;
				} catch (IOException e) {
					// TODO Auto-generated catch block
				}
			}
		}
	}
	
	
	public static void addIndex(String content){
		//前期准备
		Directory directory = null;
		IndexWriterConfig indexWriterConfig = null;
		IndexWriter indexWriter = null;
		try {
			File file = new File(filePath);
			//准备目录
			directory = FSDirectory.open(file);
			//准备索引流的配置
			indexWriterConfig = new IndexWriterConfig(Version.LUCENE_48,new SimpleAnalyzer());
			//获得indexwriter流
			indexWriter = new IndexWriter(directory, indexWriterConfig);
			//准备document
			Document document = new Document();
			//准备field
			Field field = new StringField("content", content, Store.YES);
			document.add(field);
			indexWriter.addDocument(document);// 添加文本到索引中
			indexWriter.forceMerge(1);
			indexWriter.commit();
		} catch (IOException e) {
		}finally{
			try {
				indexWriter.close();
				indexWriter = null;
			} catch (IOException e) {
			}
		}
	}
	
	/**
	 * 删除索引
	 */
	public static void deleteIndex() {
		Directory directory = null;
		IndexWriterConfig indexWriterConfig = null;
		IndexWriter indexWriter = null;
		try{
			File file = new File(filePath);
			//准备目录
			directory = FSDirectory.open(file);
			//准备索引流的配置
			indexWriterConfig = new IndexWriterConfig(Version.LUCENE_48,new SimpleAnalyzer());
			//获得indexwriter流
			indexWriter = new IndexWriter(directory, indexWriterConfig);
			indexWriter.deleteAll();
			indexWriter.commit();
		}catch(Exception e){
		}finally{
			if(indexWriter != null){
				try {
					indexWriter.close();
					indexWriter = null;
				} catch (IOException e) {
				}
			}
		}
	}
}
