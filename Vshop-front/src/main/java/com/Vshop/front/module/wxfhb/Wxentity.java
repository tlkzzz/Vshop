package com.Vshop.front.module.wxfhb;

public class Wxentity {
	 
    private String nonce_str;  
    private String sign;  
    private String mch_billno;  
    private String mch_id;  
    private String wxappid;  
    private String send_name;  
    private String re_openid;  
    private int total_amount;  
    private int total_num;  
    private String wishing;  
    private String client_ip;  
    private String act_name;  
    private String remark;  
    public String getNonce_str() {  
        return nonce_str;  
    }  
    public void setNonce_str(String nonceStr) {  
        nonce_str = nonceStr;  
    }  
    public String getSign() {  
        return sign;  
    }  
    public void setSign(String sign) {  
        this.sign = sign;  
    }  
    public String getMch_billno() {  
        return mch_billno;  
    }  
    public void setMch_billno(String mchBillno) {  
        mch_billno = mchBillno;  
    }  
    public String getMch_id() {  
        return mch_id;  
    }  
    public void setMch_id(String mchId) {  
        mch_id = mchId;  
    }  
    public String getWxappid() {  
        return wxappid;  
    }  
    public void setWxappid(String wxappid) {  
        this.wxappid = wxappid;  
    }  
    public String getSend_name() {  
        return send_name;  
    }  
    public void setSend_name(String sendName) {  
        send_name = sendName;  
    }  
    public String getRe_openid() {  
        return re_openid;  
    }  
    public void setRe_openid(String reOpenid) {  
        re_openid = reOpenid;  
    }  
    public int getTotal_amount() {  
        return total_amount;  
    }  
    public void setTotal_amount(int totalAmount) {  
        total_amount = totalAmount;  
    }  
    public int getTotal_num() {  
        return total_num;  
    }  
    public void setTotal_num(int totalNum) {  
        total_num = totalNum;  
    }  
    public String getWishing() {  
        return wishing;  
    }  
    public void setWishing(String wishing) {  
        this.wishing = wishing;  
    }  
    public String getClient_ip() {  
        return client_ip;  
    }  
    public void setClient_ip(String clientIp) {  
        client_ip = clientIp;  
    }  
    public String getAct_name() {  
        return act_name;  
    }  
    public void setAct_name(String actName) {  
        act_name = actName;  
    }  
    public String getRemark() {  
        return remark;  
    }  
    public void setRemark(String remark) {  
        this.remark = remark;  
    }  
}
