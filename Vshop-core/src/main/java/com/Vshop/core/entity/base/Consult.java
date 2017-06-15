package com.Vshop.core.entity.base;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import com.Vshop.core.common.DateUtils;

import lombok.Data;
import lombok.ToString;


/**
 * Created by rabook on 2014/12/20.
 */
@Data
public class Consult extends BaseEntity implements Serializable{

    private static final long serialVersionUID = -7581824786561592640L;

    private Integer consultId;

    private Integer goodsId;

    private String cgoodsName;

    private Integer memberId;

    private String cmemberName;

    private Integer storeId;

    private String email;

    private String consultContent;

    private Long consultAddtime;

    private String consultReply;

    private Long consultReplyTime;

    private Boolean isanonymous;

    private String storeName;

    private Goods goods;

    private Integer replyStatus;
    
    private Timestamp consultAddtimeStr;
    
    private Timestamp consultReplyTimeStr;

    private String memberAvatar;
//    private String goodsImage;
//    private String goodsStorePrice;
    
	public Long getConsultAddtime() {
		return consultAddtime;
	}

	public void setConsultAddtime(Long consultAddtime) {
		this.consultAddtime = consultAddtime;
		if(null != consultAddtime){
			consultAddtimeStr = DateUtils.getTimestampByLong(consultAddtime);
			this.consultAddtimeStr = consultAddtimeStr;
		}
	}

	public Timestamp getConsultAddtimeStr() {
		return consultAddtimeStr;
	}

	public void setConsultAddtimeStr(Timestamp consultAddtimeStr) {
		consultAddtimeStr = DateUtils.getTimestampByLong(consultAddtime);
		this.consultAddtimeStr = consultAddtimeStr;
	}

	public Long getConsultReplyTime() {
		return consultReplyTime;
	}

	public void setConsultReplyTime(Long consultReplyTime) {
		this.consultReplyTime = consultReplyTime;
		if(null != consultReplyTime){
			consultReplyTimeStr = DateUtils.getTimestampByLong(consultReplyTime);
			this.consultReplyTimeStr = consultReplyTimeStr;
		}
	}

	public Timestamp getConsultReplyTimeStr() {
		return consultReplyTimeStr;
	}

	public void setConsultReplyTimeStr(Timestamp consultReplyTimeStr) {
		consultReplyTimeStr = DateUtils.getTimestampByLong(consultReplyTime);
		this.consultReplyTimeStr = consultReplyTimeStr;
	}

}
