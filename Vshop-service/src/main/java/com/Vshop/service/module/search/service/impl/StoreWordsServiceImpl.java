package com.Vshop.service.module.search.service.impl;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.common.PinYinUtil;
import com.Vshop.core.entity.base.Store;
import com.Vshop.core.entity.base.StoreWords;
import com.Vshop.core.lucene.analyzer.SimpleAnalyzer;
import com.Vshop.service.module.search.dao.StoreWordsDao;
import com.Vshop.service.module.search.service.StoreWordsService;
import com.Vshop.service.module.store.dao.StoreDao;
import com.Vshop.service.utils.page.Pager;

/**
 * 店铺关键词
 * @author cgl
 * 2015年08月31日15:36:39
 */
@Service
public class StoreWordsServiceImpl implements StoreWordsService{
	
	@Autowired
	private StoreWordsDao storeWordsDao;
	
	@Autowired
	private StoreDao storeDao;

	/**
	 * 将店铺名称,进行分词,存入数据库
	 * @param goodsName 店铺名称
	 */
	@Override
	public void saveKeywordToDatabase(Analyzer analyzer, String storeName) throws IOException{
		TokenStream tokenStream = analyzer.tokenStream("text", storeName);
		
		/**将店铺名称分词,将分出来的词语,放入list中*/
		List<String> keywords = displayTokens(tokenStream);
		
		/**循环将关键词存入数据库中*/
		for(String keyword : keywords){
			saveToDatabase(keyword);
		}
		tokenStream.close();
	}
	
	/**
	 * 分词
	 * @param tokenStream
	 * @return
	 * @throws IOException
	 */
	private List<String> displayTokens(TokenStream tokenStream) throws IOException {
//		OffsetAttribute offsetAttribute = tokenStream.addAttribute(OffsetAttribute.class);
//		PositionIncrementAttribute positionIncrementAttribute = tokenStream.addAttribute(PositionIncrementAttribute.class);
		CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
//		TypeAttribute typeAttribute = tokenStream.addAttribute(TypeAttribute.class);

		/*准备一个容器涌来放分词以后的结果*/
		List<String> list = new ArrayList<String>();
		
		tokenStream.reset();
//		int position = 0;
		while (tokenStream.incrementToken()) {
//			int increment = positionIncrementAttribute.getPositionIncrement();
//			if(increment > 0) {
//				position = position + increment;
//				System.out.print(position + ":");
//			}
//			/*开始的下标*/
//		    int startOffset = offsetAttribute.startOffset();
//		    /*结束下标*/
//		    int endOffset = offsetAttribute.endOffset();
			
			//分词
		    String term = charTermAttribute.toString();
		    
		    System.out.println("分词:\t" + term);
		    
		    list.add(term);
		}
		return list;
	}
	
	/**
	 * 存入数据库
	 */
	private void saveToDatabase(String keyword){
		
		/**判断是否已经存在这个关键词,如果存在返回的是他的索引id,如果不存在返回的是null,所以要判断是否为null*/
		Integer wordsId = storeWordsDao.isExist(keyword);
		
		StoreWords storeWords = new StoreWords();
		
		if(wordsId == null){/*不存在,需要插入*/
			
			/**全拼*/
			String quanPing = PinYinUtil.getPingYin(keyword); 
			
			/**首字母*/
			String shouZiMu = PinYinUtil.getFirstSpell(keyword);
			
			
			storeWords.setKeyword(keyword);
			storeWords.setQuanPing(quanPing);
			storeWords.setShouZiMu(shouZiMu);
			storeWords.setWordsNum(1);
			/**保存*/
			storeWordsDao.saveStoreWords(storeWords);
			
		}else{/*已经存在这个关键词了,直接修改数据库中的words_num字段+1*/
			
			storeWords.setWordsId(wordsId);
			storeWords.setWordsNum(1);
			/**修改存在数量*/
			storeWordsDao.updateWordsNum(storeWords);
			
		}
		
	}
	
//	public static void main(String[] args) {
//		try {
//			createKeywordToDatabase(new SimpleAnalyzer(), "清华北大是中国非常有名的大学");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 关键词匹配
	 */
	public List<StoreWords> keywordMatch(String keyword){

		StoreWords storeWords = new StoreWords();
		
		storeWords.setKeyword(keyword);
		
		storeWords.setQuanPing(PinYinUtil.getPingYin(keyword));
		
		storeWords.setShouZiMu(PinYinUtil.getFirstSpell(keyword));
		
		/**
		 * 设置商品单位
		 */
		List<StoreWords> list = storeWordsDao.keywordMatch(storeWords);
		
		for(StoreWords storeWords2 : list){
			storeWords2.setUnit("家");
		}
		
		
		return list;
	}
	
	/**
	 * 初始化
	 */
	public void deleteAndInitStoreWord() throws IOException{
		
		/**删除原来的*/
		storeWordsDao.deleteAll();
		
		Store storeCondition = new Store();
		
		//设置店铺状态
		storeCondition.setStoreState(1);
		
		Pager pager = new Pager();
		
		pager.setPageSize(2000);
		
		pager.setCondition(storeCondition);
		
		int count = storeDao.queryCount(storeCondition);
		
		int pageCont = 0;
		
		pageCont = count % pager.getPageSize() == 0?pageCont/pager.getPageSize():pageCont/pager.getPageSize() + 1;
		
		for(int i = 1; i <= pageCont; i++){
			
			pager.setPageNo(i);
			
			List<Store> list = storeDao.queryList(pager);
			
			for(Store store : list){
				
				saveKeywordToDatabase(new SimpleAnalyzer(), store.getStoreName());
				
			}
			
		}
	}
}
