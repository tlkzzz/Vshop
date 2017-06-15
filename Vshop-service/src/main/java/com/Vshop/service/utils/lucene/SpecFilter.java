package com.Vshop.service.utils.lucene;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.AtomicReaderContext;
import org.apache.lucene.index.DocsEnum;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.DocIdSet;
import org.apache.lucene.search.Filter;
import org.apache.lucene.util.Bits;
import org.apache.lucene.util.FixedBitSet;

import com.Vshop.core.jackson.JsonUtils;
import com.Vshop.service.utils.goods.GoodsUtils;

public class SpecFilter extends Filter{
	
	private String keyword;
	private String field;
	private String[] spValueIdsFilter;
	
	public SpecFilter(String field, String keyword, String[] spValueIdsFilter){
		this.keyword = keyword;
		this.field = field;
		this.spValueIdsFilter = spValueIdsFilter;
	}

	@Override
	public DocIdSet getDocIdSet(AtomicReaderContext context, Bits acceptDocs) throws IOException {
		
		FixedBitSet bits = new FixedBitSet(context.reader().maxDoc());
		
        DocsEnum doc=context.reader().termDocsEnum(new Term(field, keyword));//必须是唯一的不重复  
        
        if(doc != null){
            //保证是单个不重复的term,如果重复的话，默认会取第一个作为返回结果集,分词后的term也不适用自定义term  
            
            while(doc.nextDoc()!=-1){
            	
            	try{
            		Document document = context.reader().document(doc.docID());
            		String goodsSpecStr = document.get("goodsSpec");
            		String goodsSpecValueIds  = GoodsUtils.getgoodsSpecValueId(goodsSpecStr);
            		if(goodsSpecValueIds != null){
	            		String[] goodsSpecAttr = goodsSpecValueIds.split(",");
	            		if(spValueIdsFilter.length > goodsSpecAttr.length){
	            			
	            		}else{
	            			int q = 0;
	            			//对比是否包含
	                		for(int i = 0; i < spValueIdsFilter.length; i++){
	                			for(int j = 0; j < goodsSpecAttr.length; j++){
	                				if(spValueIdsFilter[i].equals(goodsSpecAttr[j])){
	                					q++;
	                				}
	                			}
	                		}
	            			if(q == spValueIdsFilter.length){
	            				bits.set(doc.docID());//对付符合条件约束的docid循环添加到bits里面  
	            			}
	            		}
	
            	}
            	}catch (Exception e){
            		return bits;
              }
              }  
    	
        }

	return bits;
	}

}
