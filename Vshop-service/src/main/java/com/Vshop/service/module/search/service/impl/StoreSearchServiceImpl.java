package com.Vshop.service.module.search.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopDocsCollector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Vshop.core.common.Constants;
import com.Vshop.core.lucene.analyzer.SimpleAnalyzer;
import com.Vshop.service.module.search.service.SearchService;
import com.Vshop.service.module.search.service.StoreSearchService;
import com.Vshop.service.module.store.dao.StoreDao;
import com.Vshop.service.utils.CommonConstants;
import com.Vshop.service.utils.lucene.LucenePager;
import com.Vshop.service.utils.page.Pager;

/**
 * 店铺搜索
 * @author cgl
 */
@Service
public class StoreSearchServiceImpl implements StoreSearchService{

	@Autowired
	private StoreDao storeDao;
	
	@Autowired
	private SearchService searchService;
	
	
	/**
	 * 分词器
	 */
	private Analyzer analyzer = new SimpleAnalyzer();
	
	/**
	 * lucene内核版本
	 */
	private Version luceneVersion = Version.LUCENE_48;
	

	/**
	 * 商品索引路径
	 */
	public static final String STORE_INDEX_PATH = CommonConstants.LUCENE_BASEPATH + Constants.STORE_SEARCH_INDEX_PATH;
	
	/**
	 * 批量建立商品索引
	 * @return 返回建立索引的商品个数
	 */
	@Override
	public Integer createStoreIndex(com.Vshop.core.entity.base.Store storeConditions) {
		//前期准备
		Directory directory = null;
		IndexWriterConfig indexWriterConfig = null;
		IndexWriter indexWriter = null;
		
		//准备分页,这里的分页是为了在建立索引的时候防止内存溢出
		Pager pager = new Pager();
		//设置每次索引2000条,暂时写死
		int pageSize = 2000;
		pager.setPageSize(pageSize);
		//设置类
		com.Vshop.core.entity.base.Store storeCondition = null;
		if(storeConditions == null){
			storeCondition = new com.Vshop.core.entity.base.Store();
		}else{
			storeCondition = storeConditions;
		}
		//设置商品状态
		storeCondition.setStoreState(1);
		pager.setCondition(storeCondition);
		//得到总条数
		int count = storeDao.queryCount(storeCondition);
		//设置总条数
		pager.setTotalRows(count);
		try {
			//准备目录
			directory = FSDirectory.open(new File(STORE_INDEX_PATH));
			//准备索引流的配置
			indexWriterConfig = new IndexWriterConfig(luceneVersion, analyzer);
			//获得indexwriter流
			indexWriter = new IndexWriter(directory, indexWriterConfig);
			
			//将原先的全部删除
			indexWriter.deleteAll();
			
			//准备list
			List<com.Vshop.core.entity.base.Store> storeList = null;
			//设置一共有几页
			int pageCount = 0;
			pageCount = count%pager.getPageSize()==0?count/pager.getPageSize():count/pager.getPageSize()+1;
			
			//开始循环分页取出
			for(int j = 1; j <= pageCount; j++ ){
				
				//这里分页循环取出storeList
				pager.setPageNo(j);
				
				storeList = storeDao.queryList(pager);
				
				//循环取出goodstype
				for(int i = 0; i < storeList.size(); i++){
					//准备document
					Document document = new Document();
					//取出goodstype
					com.Vshop.core.entity.base.Store store = storeList.get(i);
					//准备field
					Field storeId = new IntField("storeId", store.getStoreId(), Store.YES);
					document.add(storeId);
					Field storeName = new StringField("storeName", store.getStoreName(), Store.YES);
					document.add(storeName);
					Field storeLogo = new StringField("storeLogo", store.getStoreLogo()==null?"":store.getStoreLogo(), Store.YES);
					document.add(storeLogo);
					Field memberName = new TextField("memberName", store.getMemberName(), Store.YES);
					document.add(memberName);
					Field scId = new IntField("scId", store.getScId(), Store.YES);
					document.add(scId);
					Field provinceId = new IntField("provinceId", store.getProvinceId(), Store.YES);
					document.add(provinceId);
					Field storeAddress = new TextField("storeAddress", store.getStoreAddress(), Store.YES);
					document.add(storeAddress);
					Field storeSales = new IntField("storeSales", store.getStoreSales(), Store.YES);
					document.add(storeSales);
					Field storeCollect = new IntField("storeCollect", store.getStoreCollect(), Store.YES);
					document.add(storeCollect);
					Field storeClick = new IntField("storeClick", store.getStoreClick(), Store.YES);
					document.add(storeClick);
					Field storeCredit = new IntField("storeCredit", store.getStoreCredit(), Store.YES);
					document.add(storeCredit);
					Field praiseRate = new DoubleField("praiseRate", store.getPraiseRate(), Store.YES);
					document.add(praiseRate);
					indexWriter.addDocument(document);// 添加文本到索引中
				}
				indexWriter.commit();
			}
				return 1;
		}catch(Exception e){
			e.printStackTrace();
			return 0;
		}finally{
			try {
				indexWriter.close();
				indexWriter = null;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				return 0;
			}
		}
	}

	/**
	 * 增量索引
	 */
	@Override
	public Integer addStoreIndex(Integer storeId) {
		com.Vshop.core.entity.base.Store storeCondition = new com.Vshop.core.entity.base.Store();
		storeCondition.setStoreId(storeId);
		//设置商品状态
		storeCondition.setStoreState(1);
		com.Vshop.core.entity.base.Store store = storeDao.findByIds(storeCondition);
		if(store == null){
			return 0;
		}
		//前期准备
		Directory directory = null;
		IndexWriterConfig indexWriterConfig = null;
		IndexWriter indexWriter = null;
		try {
			File file = new File(STORE_INDEX_PATH);
			//准备目录
			directory = FSDirectory.open(file);
			//准备索引流的配置
			indexWriterConfig = new IndexWriterConfig(luceneVersion,analyzer);
			//获得indexwriter流
			indexWriter = new IndexWriter(directory, indexWriterConfig);
					//准备document
					Document document = new Document();
					//准备field
					Field storeid = new IntField("storeId", store.getStoreId(), Store.YES);
					document.add(storeid);
					Field storeName = new StringField("storeName", store.getStoreName(), Store.YES);
					document.add(storeName);
					Field storeLogo = new StringField("storeLogo", store.getStoreLogo()==null?"":store.getStoreLogo(), Store.YES);
					document.add(storeLogo);
					Field memberName = new TextField("memberName", store.getMemberName(), Store.YES);
					document.add(memberName);
					Field scId = new IntField("scId", store.getScId(), Store.YES);
					document.add(scId);
					Field provinceId = new IntField("provinceId", store.getProvinceId(), Store.YES);
					document.add(provinceId);
					Field storeAddress = new TextField("storeAddress", store.getStoreAddress(), Store.YES);
					document.add(storeAddress);
					Field storeSales = new IntField("storeSales", store.getStoreSales(), Store.YES);
					document.add(storeSales);
					Field storeCollect = new IntField("storeCollect", store.getStoreCollect(), Store.YES);
					document.add(storeCollect);
					Field storeClick = new IntField("storeClick", store.getStoreClick(), Store.YES);
					document.add(storeClick);
					Field storeCredit = new IntField("storeCredit", store.getStoreCredit(), Store.YES);
					document.add(storeCredit);
					Field praiseRate = new DoubleField("praiseRate", store.getPraiseRate(), Store.YES);
					document.add(praiseRate);
					indexWriter.addDocument(document);// 添加文本到索引中
					indexWriter.forceMerge(1);
					indexWriter.commit();
					return 1;
		} catch (IOException e) {
			return 0;
		}finally{
			try {
				indexWriter.close();
				indexWriter = null;
			} catch (IOException e) {
				return 0;
			}
		}
	}

	
	/**
	 * 删除索引
	 */
	@Override
	public void deleteStoreIndex(Integer id) {
		Directory directory = null;
		IndexWriterConfig indexWriterConfig = null;
		IndexWriter indexWriter = null;
		try{
		File indexDir = new File(STORE_INDEX_PATH);
		directory = FSDirectory.open(indexDir);
		indexWriterConfig = new IndexWriterConfig(luceneVersion, analyzer);
		indexWriter = new IndexWriter(directory, indexWriterConfig);
		BooleanQuery booleanQuery = new BooleanQuery(); 
		NumericRangeQuery<Integer> rangeQuery2 = NumericRangeQuery.newIntRange("storeId", id, id, true, true);
		booleanQuery.add(rangeQuery2, BooleanClause.Occur.MUST);
		indexWriter.deleteDocuments(rangeQuery2);
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

	@Override
	public LucenePager searchStore(LucenePager lucenePager) {
		
		Directory directory = null;
		IndexSearcher indexSearcher = null;
		IndexReader indexReader = null;
		
		try{
		File indexDir = new File(STORE_INDEX_PATH);
		//目录
		directory = FSDirectory.open(indexDir);
		//获得indexreader
		indexReader = DirectoryReader.open(directory);
		//获得IndexSearch
		indexSearcher = new IndexSearcher(indexReader);
		//从lucenePage得到query
		BooleanQuery query = (BooleanQuery) searchService.getQuery(lucenePager);
		if(query == null){
			return null;
		}
		//获得该关键词下的规格list和brandList
		String keyword = lucenePager.getKeyword();
//		if(lucenePager.getSearchType().equ als("keywordSearch")){
//
//			//对应规格
//			lucenePager.setSpecList(goodsSearchDao.getSpecListByKeyword(keyword));
//			//对应品牌
//			lucenePager.setBrandList(goodsSearchDao.getBrandListByKeyword(keyword));;
//			
//		}else if(lucenePager.getSearchType().equals("allSearch")){
//			
//		}else if(lucenePager.getSearchType().equals("gcIdSearch")){
//			
//			//对应规格
//			//lucenePager.setSpecList(goodsSearchDao.getSpecListByGcId(Integer.parseInt(keyword)));
//			//对应品牌
////			lucenePager.setBrandList(goodsSearchDao.getBrandListByGcId(Integer.parseInt(keyword)));
//			
//		}else if(lucenePager.getSearchType().equals("typeIdSearch")){
//			
//			//对应规格
//			lucenePager.setSpecList(goodsSearchDao.getSpecListByTypeId(Integer.parseInt(keyword)));
//			//对应品牌
//			lucenePager.setBrandList(goodsSearchDao.getBrandListByTypeId(Integer.parseInt(keyword)));
//			
//		}else if(lucenePager.getSearchType().equals("BrandIdSearch")){
//			List<Spec> specList = goodsSearchDao.getSpecListByBrandId(Integer.parseInt(lucenePager.getKeyword()));
//			lucenePager.setSpecList(specList);
//		}
//		
		//判断筛选条件是否为空,如果为空则说明,没有数据要筛选
		if(lucenePager.getFilterConditionsStr() != null){
			query = searchService.doFilter(query, lucenePager);
		}
		
		//得到总记录数
		int count = indexSearcher.search(query, 100000000).totalHits;
		if(count != 0){
			//获得res
			TopDocsCollector res = searchService.getCollector(lucenePager);
			//使用indexsearcher查找,并且将res条件放入
			indexSearcher.search(query, res);
			/*
			 * 得到topDocs,并且指定索引,取出该页的数据
			 * 参数:从下表为几开始开始,多少一页 从0开始下标
			 */
			TopDocs docs = null;
			Integer start = lucenePager.getStart();
			Integer size = lucenePager.getPageSize();
			docs = res.topDocs(start, size);
			
			//得到doc结果集数组
			ScoreDoc[] scoreDocs = docs.scoreDocs;
			//准备list为null
			List<com.Vshop.core.entity.base.Store> list = null;
			//判断是否取出数据
			//如果有数据则new 一个list
			list = new ArrayList<com.Vshop.core.entity.base.Store>();
			for(int i = 0; i < scoreDocs.length; i++){
				//准备goodstypeVo,请注意这里引的包是search下面的
				com.Vshop.core.entity.base.Store store = new com.Vshop.core.entity.base.Store();
				//拿到doc对象,相当于拿到数据库中的一条记录
				Document document = indexSearcher.doc(scoreDocs[i].doc);
				/*
				 * 下面开始一对一的赋值给goods
				 */
				store.setStoreId(Integer.parseInt(document.get("storeId")));
				store.setStoreName(document.get("storeName"));
				store.setMemberName(document.get("memberName"));
				store.setScId(Integer.parseInt(document.get("scId")));
				store.setProvinceId(Integer.parseInt(document.get("provinceId")));
				store.setStoreAddress(document.get("storeAddress"));
				store.setStoreSales(Integer.parseInt(document.get("storeSales")));
				store.setStoreCollect(Integer.parseInt(document.get("storeCollect")));
				store.setStoreClick(Integer.parseInt(document.get("storeClick")));
				store.setStoreCredit(Integer.parseInt(document.get("storeCredit")));
				store.setPraiseRate(Float.parseFloat(document.get("praiseRate")));
				//将赋值完成的类加入list
				list.add(store);
			}
			lucenePager.setResult(list);
			lucenePager.setTotalRows(res.getTotalHits());
			return lucenePager;
		}
		}catch(Exception e){
			return null;
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
		return lucenePager;
	}
	
}
