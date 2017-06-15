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
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.base.GoodsWords;
import com.Vshop.core.lucene.analyzer.SimpleAnalyzer;
import com.Vshop.core.state.goods.GoodsState;
import com.Vshop.service.module.goods.dao.GoodsDao;
import com.Vshop.service.module.search.dao.GoodsWordsDao;
import com.Vshop.service.module.search.service.GoodsWordsService;
import com.Vshop.service.utils.page.Pager;

/**
 * 商品关键词
 * @author cgl
 * 2015年08月18日10:05:53
 */
@Service
public class GoodsWordsServiceImpl implements GoodsWordsService{
	
	@Autowired
	private GoodsWordsDao goodsWordsDao;
	
	@Autowired
	private GoodsDao goodsDao;

	/**
	 * 将商品名称,进行分词,存入数据库
	 * @param goodsName 商品名称
	 */
	@Override
	public void saveKeywordToDatabase(Analyzer analyzer, String goodsName) throws IOException{
		TokenStream tokenStream = analyzer.tokenStream("text", goodsName);
		
		/**将商品名称分词,将分出来的词语,放入list中*/
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
		Integer wordsId = goodsWordsDao.isExist(keyword);
		
		GoodsWords goodsWords = new GoodsWords();
		
		if(wordsId == null){/*不存在,需要插入*/
			
			/**全拼*/
			String quanPing = PinYinUtil.getPingYin(keyword); 
			
			/**首字母*/
			String shouZiMu = PinYinUtil.getFirstSpell(keyword);
			
			
			goodsWords.setKeyword(keyword);
			goodsWords.setQuanPing(quanPing);
			goodsWords.setShouZiMu(shouZiMu);
			goodsWords.setWordsNum(1);
			goodsWords.setCreateTime(System.currentTimeMillis());
			/**保存*/
			goodsWordsDao.saveGoodsWords(goodsWords);
			
		}else{/*已经存在这个关键词了,直接修改数据库中的words_num字段+1*/
			
			goodsWords.setWordsId(wordsId);
			goodsWords.setWordsNum(1);
			/**修改存在数量*/
			goodsWordsDao.updateWordsNum(goodsWords);
			
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
	public List<GoodsWords> keywordMatch(String keyword){

		GoodsWords goodsWords = new GoodsWords();
		
		goodsWords.setKeyword(keyword);
		
		goodsWords.setQuanPing(PinYinUtil.getPingYin(keyword));
		
		goodsWords.setShouZiMu(PinYinUtil.getFirstSpell(keyword));
		
		/**
		 * 设置商品单位
		 */
		List<GoodsWords> list = goodsWordsDao.keywordMatch(goodsWords);
		
		for(GoodsWords goodsWords2 : list){
			goodsWords2.setUnit("件");
		}
		
		return list;
	}
	
	/**
	 * 初始化
	 */
	public void deleteAndInitGoodsWord() throws IOException{
		
		/**删除原来的*/
		goodsWordsDao.deleteAll();
		
		Goods goodsCondition = new Goods();
		
		//设置商品状态
		goodsCondition.setGoodsShow(GoodsState.GOODS_ON_SHOW);
		goodsCondition.setGoodsState(GoodsState.GOODS_OPEN_STATE);
		//设置店铺状态
		goodsCondition.setGoodsStoreState(GoodsState.GOODS_STORE_OPEN);
		
		Pager pager = new Pager();
		
		pager.setPageSize(2000);
		
		pager.setCondition(goodsCondition);
		
		int count = goodsDao.countGoods(goodsCondition);
		
		int pageCont = 0;
		
		pageCont = count % pager.getPageSize() == 0?pageCont/pager.getPageSize():pageCont/pager.getPageSize() + 1;
		
		for(int i = 1; i <= pageCont; i++){
			
			pager.setPageNo(i);
			
			List<Goods> list = goodsDao.findGoodPagerList(pager);
			
			for(Goods goods : list){
				
				saveKeywordToDatabase(new SimpleAnalyzer(), goods.getGoodsName());
				
			}
			
		}
	}
}
