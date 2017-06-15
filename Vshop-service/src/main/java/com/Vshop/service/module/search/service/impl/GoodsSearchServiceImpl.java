package com.Vshop.service.module.search.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.DoubleField;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.LongField;
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
import com.Vshop.core.entity.base.Brand;
import com.Vshop.core.entity.base.Goods;
import com.Vshop.core.entity.vo.SpecVo;
import com.Vshop.core.lucene.analyzer.SimpleAnalyzer;
import com.Vshop.core.state.goods.GoodsState;
import com.Vshop.service.module.goods.dao.GoodsDao;
import com.Vshop.service.module.goods.service.GoodsSpecIndexService;
import com.Vshop.service.module.search.dao.GoodsSearchDao;
import com.Vshop.service.module.search.service.GoodsSearchService;
import com.Vshop.service.module.search.service.SearchService;
import com.Vshop.service.utils.CommonConstants;
import com.Vshop.service.utils.lucene.LucenePager;
import com.Vshop.service.utils.page.Pager;

/**
 * 商品搜索
 * @author cgl
 */
@Service
public class GoodsSearchServiceImpl implements GoodsSearchService{
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private GoodsDao goodsDao;
	
	@Autowired
	private GoodsSpecIndexService goodsSpecIndexService;
	
	@Autowired
	private GoodsSearchDao goodsSearchDao;
	
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
	public static final String GOODS_INDEX_PATH = CommonConstants.LUCENE_BASEPATH + Constants.GOODS_SEARCH_INDEX_PATH;
	
	/**
	 * 批量建立商品索引
	 * @return Integer 
	 */
	@Override
	public Integer saveGoodsIndex(Goods goodsConditions) {
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
		Goods goodsCondition = null;
		if(goodsConditions == null){
			goodsCondition = new Goods();
		}else{
			goodsCondition = goodsConditions;
		}
		//设置商品状态
		goodsCondition.setGoodsShow(GoodsState.GOODS_ON_SHOW);
		goodsCondition.setGoodsState(GoodsState.GOODS_OPEN_STATE);
		//设置店铺状态
		goodsCondition.setGoodsStoreState(GoodsState.GOODS_STORE_OPEN);
		pager.setCondition(goodsCondition);
		//得到总条数
		int count = goodsSearchDao.findGoodPagerListCount(goodsCondition);
		//设置总条数
		pager.setTotalRows(count);
		try {
			//准备目录
			directory = FSDirectory.open(new File(GOODS_INDEX_PATH));
			//准备索引流的配置
			indexWriterConfig = new IndexWriterConfig(luceneVersion, analyzer);
			//获得indexwriter流
			indexWriter = new IndexWriter(directory, indexWriterConfig);
			
			//将原先的全部删除
			indexWriter.deleteAll();
			
			//准备list
			List<Goods> goodsList = null;
			
			//设置一共有几页
			int pageCount = 0;
			pageCount = count%pager.getPageSize()==0?count/pager.getPageSize():count/pager.getPageSize()+1;
			
			//开始循环分页取出
			for(int j = 1; j <= pageCount; j++ ){
				
				//这里分页循环取出goods
				pager.setPageNo(j);
				
				//获得到当前页的数据
				goodsList = goodsSearchDao.findGoodPagerList(pager);

				//循环取出goodstype
				for(int i = 0; i < goodsList.size(); i++){
					//准备document
					Document document = new Document();
					//取出goodstype
					Goods goods = goodsList.get(i);
					
					document = putAttrToDoc(goods, document);
					
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
	public Integer saveOneGoodsIndex(Integer goodsId) {
		Goods goodsCondition = new Goods();
		goodsCondition.setGoodsId(goodsId);
		//设置商品状态
		goodsCondition.setGoodsShow(GoodsState.GOODS_ON_SHOW);
		goodsCondition.setGoodsState(GoodsState.GOODS_OPEN_STATE);
		//设置店铺状态
		goodsCondition.setGoodsStoreState(GoodsState.GOODS_STORE_OPEN);
		Goods goods = goodsSearchDao.findOneGoodByCondition(goodsCondition);
		if(goods == null){
			return 0;
		}
		//前期准备
		Directory directory = null;
		IndexWriterConfig indexWriterConfig = null;
		IndexWriter indexWriter = null;
		try {
			File file = new File(GOODS_INDEX_PATH);
			//准备目录
			directory = FSDirectory.open(file);
			//准备索引流的配置
			indexWriterConfig = new IndexWriterConfig(luceneVersion, analyzer);
			//获得indexwriter流
			indexWriter = new IndexWriter(directory, indexWriterConfig);
			
			//准备document
			Document document = new Document();
			
			document = putAttrToDoc(goods, document);
			
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
	 * 将goods中的属性放入到document中
	 */
	private Document putAttrToDoc(Goods goods, Document document){
		
		//准备field
		Field goodsId = new IntField("goodsId", goods.getGoodsId(), Store.YES);
		document.add(goodsId);
		Field goodsName = new TextField("goodsName", goods.getGoodsName(), Store.YES);
		document.add(goodsName);
		Field goodsSubtitle = new TextField("goodsSubtitle", goods.getGoodsSubtitle()==null?"":goods.getGoodsSubtitle(), Store.YES);
		document.add(goodsSubtitle);
		Field gcId = new StringField("gcId", goods.getGcId()+"", Store.YES);
		document.add(gcId);
		Field gcName = new TextField("gcName", goods.getGcName(), Store.YES);
		document.add(gcName);
		Field brandId = new IntField("brandId", goods.getBrandId(), Store.YES);
		document.add(brandId);
		Field brandName = new TextField("brandName", goods.getBrandName()==null?"":goods.getBrandName(), Store.YES);
		document.add(brandName);
		Field typeId = new IntField("typeId", goods.getTypeId(), Store.YES);
		document.add(typeId);
		Field storeId = new IntField("storeId", goods.getStoreId(), Store.YES);
		document.add(storeId);
		Field storeName = new TextField("storeName", goods.getStoreName(), Store.YES);
		document.add(storeName);
		Field specId = new IntField("specId", goods.getSpecId()==null?0:goods.getSpecId(), Store.YES);//设个默认值不然报错20150906
		document.add(specId);
		Field specName = new TextField("specName", goods.getSpecName()==null?"":goods.getSpecName(), Store.YES);
		document.add(specName);
		Field goodsImage = new TextField("goodsImage", goods.getGoodsImage()==null?"":goods.getGoodsImage(), Store.YES);
		document.add(goodsImage);
		Field goodsImageMore = new TextField("goodsImageMore", goods.getGoodsImageMore()==null?"":goods.getGoodsImageMore(), Store.YES);
		document.add(goodsImageMore);
		Field goodsStorePrice = new DoubleField("goodsStorePrice", goods.getGoodsStorePrice().doubleValue(), Store.YES);
		document.add(goodsStorePrice);
		Field goodsStorePriceInterval = new TextField("goodsStorePriceInterval", goods.getGoodsStorePriceInterval()==null?"":goods.getGoodsStorePriceInterval(), Store.YES);
		document.add(goodsStorePriceInterval);
		Field goodsSerial = new TextField("goodsSerial", goods.getGoodsSerial()==null?"":goods.getGoodsSerial(), Store.YES);
		document.add(goodsSerial);
		Field goodsClick = new IntField("goodsClick", goods.getGoodsClick(), Store.YES);
		document.add(goodsClick);
		Field goodsCommend = new IntField("goodsCommend", goods.getGoodsCommend(), Store.YES);
		document.add(goodsCommend);
		Field createTime = new LongField("createTime", goods.getCreateTime()==null? 0 :goods.getCreateTime(), Store.YES);
		document.add(createTime);
		Field goodsKeywords = new TextField("goodsKeywords", goods.getGoodsKeywords()==null?"":goods.getGoodsKeywords(), Store.YES);
		document.add(goodsKeywords);
		Field goodsDescription = new TextField("goodsDescription", goods.getGoodsDescription()==null?"":goods.getGoodsDescription(), Store.YES);
		document.add(goodsDescription);
		Field goodsBody = new TextField("goodsBody", goods.getGoodsBody()==null?"":goods.getGoodsBody(), Store.YES);
		document.add(goodsBody);
		Field goodsAttr = new TextField("storeName", goods.getGoodsAttr()==null?"":goods.getGoodsAttr(), Store.YES);
		document.add(goodsAttr);
		Field goodsSpec = new TextField("goodsSpec", goods.getGoodsSpec()==null?"":goods.getGoodsSpec(), Store.YES);
		document.add(goodsSpec);
		Field goodsColImg = new TextField("goodsColImg", goods.getGoodsColImg()==null?"":goods.getGoodsColImg(), Store.YES);
		document.add(goodsColImg);
		Field goodsForm = new IntField("goodsForm", goods.getGoodsForm(), Store.YES);
		document.add(goodsForm);
		Field transportId = new IntField("transportId", goods.getTransportId()==null?-1:goods.getTransportId(), Store.YES);
		document.add(transportId);
		Field pyPrice = new DoubleField("pyPrice", goods.getPyPrice()==null?Double.parseDouble(0+""):goods.getPyPrice().doubleValue(), Store.YES);
		document.add(pyPrice);
		Field kdPrice = new DoubleField("kdPrice", goods.getKdPrice()==null?Double.parseDouble(0+""):goods.getKdPrice().doubleValue(), Store.YES);
		document.add(kdPrice);
		Field esPrice = new DoubleField("esPrice", goods.getEsPrice()==null?Double.parseDouble(0+""):goods.getEsPrice().doubleValue(), Store.YES);
		document.add(esPrice);
		Field cityId = new IntField("cityId", goods.getCityId()==null?-1:goods.getCityId(), Store.YES);
		document.add(cityId);
		Field cityName = new TextField("cityName", goods.getCityName()==null?"":goods.getCityName(), Store.YES);
		document.add(cityName);
		Field provinceId = new IntField("provinceId", goods.getProvinceId()==null?-1:goods.getProvinceId(), Store.YES);
		document.add(provinceId);
		Field provinceName = new TextField("provinceName", goods.getProvinceName()==null?"":goods.getProvinceName(), Store.YES);
		document.add(provinceName);
		Field goodsCloseReason = new TextField("goodsCloseReason", goods.getGoodsCloseReason()==null?"":goods.getGoodsCloseReason(), Store.YES);
		document.add(goodsCloseReason);
		Field commentnum = new IntField("commentnum", goods.getCommentnum(), Store.YES);
		document.add(commentnum);
		Field salenum = new IntField("salenum", goods.getSalenum(), Store.YES);
		document.add(salenum);
		Field goodsCollect = new IntField("goodsCollect", goods.getGoodsCollect(), Store.YES);
		document.add(goodsCollect);
		Field goodsTransfeeCharge = new IntField("goodsTransfeeCharge", goods.getGoodsTransfeeCharge(), Store.YES);
		document.add(goodsTransfeeCharge);
		Field storeClassId = new IntField("storeClassId", goods.getStoreClassId()==null?-1:goods.getStoreClassId(), Store.YES);
		document.add(storeClassId);
		Field classPath = new StringField("classPath", ","+goods.getClassPath(), Store.YES);
		document.add(classPath);
		
		return document;
	}

	
	/**
	 * 删除索引
	 */
	@Override
	public void deleteGoodsIndex(String field,Integer id) {
		Directory directory = null;
		IndexWriterConfig indexWriterConfig = null;
		IndexWriter indexWriter = null;
		try{
		File indexDir = new File(GOODS_INDEX_PATH);
		directory = FSDirectory.open(indexDir);
		indexWriterConfig = new IndexWriterConfig(luceneVersion, analyzer);
		indexWriter = new IndexWriter(directory, indexWriterConfig);
		BooleanQuery booleanQuery = new BooleanQuery(); 
		NumericRangeQuery<Integer> rangeQuery2 = NumericRangeQuery.newIntRange(field, id, id, true, true);
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

	
	/**
	 * 搜索商品
	 */
	@Override
	public LucenePager searchGoods(LucenePager lucenePager) {
		
		Directory directory = null;
		IndexSearcher indexSearcher = null;
		IndexReader indexReader = null;
		
		try{
		File indexDir = new File(GOODS_INDEX_PATH);
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
		if(lucenePager.getSearchType().equals("keywordSearch")){

			//对应规格
			lucenePager.setSpecList(goodsSearchDao.getSpecListByKeyword(keyword));
			//对应品牌
			lucenePager.setBrandList(goodsSearchDao.getBrandListByKeyword(keyword));;
			
		}else if(lucenePager.getSearchType().equals("allSearch")){
			
		}else if(lucenePager.getSearchType().equals("gcIdSearch")){
			
			//对应规格
			lucenePager.setSpecList(goodsSearchDao.getSpecListByGcId(Integer.parseInt(keyword)));
			//对应品牌
			lucenePager.setBrandList(goodsSearchDao.getBrandListByGcId(Integer.parseInt(keyword)));
			
		}else if(lucenePager.getSearchType().equals("typeIdSearch")){
			
			//对应规格
			lucenePager.setSpecList(goodsSearchDao.getSpecListByTypeId(Integer.parseInt(keyword)));
			//对应品牌
			lucenePager.setBrandList(goodsSearchDao.getBrandListByTypeId(Integer.parseInt(keyword)));
			
		}else if(lucenePager.getSearchType().equals("BrandIdSearch")){
			List<SpecVo> specList = goodsSearchDao.getSpecListByBrandId(Integer.parseInt(lucenePager.getKeyword()));
			lucenePager.setSpecList(specList);
		}
		
		//判断筛选条件是否为空,如果为空则说明,没有数据要筛选
		if(lucenePager.getFilterConditionsStr() != null){
			query = searchService.doFilter(query, lucenePager);
		}
		
		//得到总记录数
		int count = indexSearcher.search(query, 100000000).totalHits;
		if(count != 0){
			//获得res
			TopDocsCollector res = searchService.getCollector(lucenePager);
			//判断是否有规格筛选
			if(searchService.getSpecFilter(lucenePager) != null){ 
				indexSearcher.search(query, searchService.getSpecFilter(lucenePager), res);
			}else{
				//使用indexsearcher查找,并且将res条件放入
				indexSearcher.search(query, res);
			}
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
			List<Goods> list = null;
			//判断是否取出数据
			//如果有数据则new 一个list
			list = new ArrayList<Goods>();
			for(int i = 0; i < scoreDocs.length; i++){
				//准备goodstypeVo,请注意这里引的包是search下面的
				Goods goods = new Goods();
				//拿到doc对象,相当于拿到数据库中的一条记录
				Document document = indexSearcher.doc(scoreDocs[i].doc);
				/*
				 * 下面开始一对一的赋值给goods
				 */
				goods.setGoodsId(Integer.parseInt(document.get("goodsId")));
				goods.setGoodsName(document.get("goodsName"));
				goods.setGoodsSubtitle(document.get("goodsSubtitle"));
				goods.setGcId(Integer.parseInt(document.get("gcId")));
				goods.setGcName(document.get("gcName"));
				goods.setBrandId(Integer.parseInt(document.get("brandId")));
				goods.setBrandName(document.get("brandName"));
				goods.setTypeId(Integer.parseInt(document.get("typeId")));
				goods.setStoreId(Integer.parseInt(document.get("storeId")));
				goods.setStoreName(document.get("storeName"));
				goods.setSpecId(Integer.parseInt(document.get("specId")));
				goods.setSpecName(document.get("specName"));
				goods.setGoodsImage(document.get("goodsImage"));
				goods.setGoodsImageMore(document.get("goodsImageMore"));
				goods.setGoodsStorePrice(BigDecimal.valueOf(Double.parseDouble(document.get("goodsStorePrice"))));
				goods.setGoodsStorePriceInterval(document.get("goodsStorePriceInterval"));
				goods.setGoodsSerial(document.get("goodsSerial"));
				goods.setGoodsClick(Integer.parseInt(document.get("goodsClick")));
				goods.setGoodsCommend(Integer.parseInt(document.get("goodsCommend")));
				goods.setCreateTime(Long.parseLong(document.get("createTime")));
				goods.setGoodsKeywords(document.get("goodsKeywords"));
				goods.setGoodsDescription(document.get("goodsDescription"));
				goods.setGoodsBody(document.get("goodsBody"));
				goods.setGoodsAttr(document.get("goodsAttr"));
				goods.setGoodsSpec(document.get("goodsSpec"));
				goods.setGoodsColImg(document.get("goodsColImg"));
				goods.setGoodsForm(Integer.parseInt(document.get("goodsForm")));
				goods.setTransportId(Integer.parseInt(document.get("transportId")));
				goods.setPyPrice(BigDecimal.valueOf(Double.parseDouble(document.get("pyPrice"))));
				goods.setKdPrice(BigDecimal.valueOf(Double.parseDouble(document.get("kdPrice"))));
				goods.setEsPrice(BigDecimal.valueOf(Double.parseDouble(document.get("esPrice"))));
				goods.setCityId(Integer.parseInt(document.get("cityId")));
				goods.setCityName(document.get("cityName"));
				goods.setProvinceId(Integer.parseInt(document.get("provinceId")));
				goods.setProvinceName(document.get("provinceName"));
				goods.setGoodsCloseReason(document.get("goodsCloseReason"));
				goods.setCommentnum(Integer.parseInt(document.get("commentnum")));
				goods.setSalenum(Integer.parseInt(document.get("salenum")));
				goods.setGoodsCollect(Integer.parseInt(document.get("goodsCollect")));
				goods.setGoodsTransfeeCharge(Integer.parseInt(document.get("goodsTransfeeCharge")));
				goods.setStoreClassId(Integer.parseInt(document.get("storeClassId")));
				//将赋值完成的类加入list
				list.add(goods);
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

	/**
	 * 获得当前关键词所对应的可选品牌
	 */
	public List<Brand> getBrandListByKeyword(String keyword) {
		return goodsSearchDao.getBrandListByKeyword(keyword);
	}

	/**
	 * 获得当前关键词所对应的可选品牌
	 */
	public List<Brand> getBrandListByGcId(Integer gcId) {
		return goodsSearchDao.getBrandListByGcId(gcId);
	}

	/**
	 * 获得当前关键词所对应的可选品牌
	 */
	public List<Brand> getBrandListByTypeId(Integer typeId) {
		return goodsSearchDao.getBrandListByTypeId(typeId);
	}
	
	
}
