package com.Vshop.core.entity.base;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.base.BaseEntity;

/**
 * @author llf
 * @Package com.Vshop.core.entity.trade
 * @Description:
 * @date 2014/11/12 10:12
 */
@Data
@ToString
public class EvaluateGoods extends BaseEntity implements Serializable{

    private static final long serialVersionUID = -8996826361410292057L;

    /**
     * 评价ID
     */
    private Integer gevalId;

    /**
     * 订单表自增ID
     */
    private Integer gevalOrderId;

    /**
     * 订单表order_sn
     */
    private Long gevalOrderNo;

    /**
     * 订单商品表编号
     */
    private Integer gevalOrderGoodsId;

    /**
     * 商品表编号
     */
    private Integer gevalGoodsId;

    /**
     * 商品名称
     */
    private String gevalGoodsName;

    /**
     * 商品价格
     */
    private Double gevalGoodsPrice;

    /**
     * 1-5分
     */
    private Integer gevalScore;

    /**
     * 信誉评价内容
     */
    private String gevalContent;

    /**
     * 示不是 1表示是匿名评价
     */
    private Integer gevalIsAnonymous;

    /**
     * 评价时间
     */
    private Long gevalAddTime;
    
    /**
     * 评价时间-页面字段
     */
    private Timestamp gevalAddTimeStr;

    /**
     * 店铺编号
     */
    private Integer gevalStoreId;

    /**
     * 店铺名称
     */
    private String gevalStorename;

    /**
     * 评价人编号
     */
    private Integer gevalFrommemberid;

    /**
     * 评价人名称
     */
    private String gevalFrommembername;

    /**
     * 评价信息的状态 0为正常 1为禁止显示
     */
    private Integer  gevalState;

    /**
     * 管理员对评价的处理备注
     */
    private String gevalRemark;

    /**
     * 解释内容
     */
    private String gevalExplain;

    /**
     * 晒单图片
     */
    private String gevalImage;
    
    /**
     * 规格描述
     */
    private String specInfo;

    /**
     * 0:未删除;1.已删除
     */
    private int isDel;

    private Goods goods;
    
    private Integer goodsId;
    private String goodsImage;
    
    private String memberAvatar;
    
    private String gcName;
    
    /**
     * 有无评价
     */
    private Integer haveContent; 
    
    
	public Long getGevalAddTime() {
		return gevalAddTime;
	}
	public void setGevalAddTime(Long gevalAddTime) {
		this.gevalAddTime = gevalAddTime;
		gevalAddTimeStr = DateUtils.getTimestampByLong(gevalAddTime);
		this.gevalAddTimeStr = gevalAddTimeStr;
		
	}
	public Timestamp getGevalAddTimeStr() {
		return gevalAddTimeStr;
	}
	public void setGevalAddTimeStr(Timestamp gevalAddTimeStr) {
		gevalAddTimeStr = DateUtils.getTimestampByLong(gevalAddTime);
		this.gevalAddTimeStr = gevalAddTimeStr;
	}
}
