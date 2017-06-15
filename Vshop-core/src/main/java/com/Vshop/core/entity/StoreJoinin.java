package com.Vshop.core.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.ToString;

import com.Vshop.core.entity.base.StoreBindClass;

/**
 * 店铺注册工商信息
 *
 * 项目名称：Vshop-entity   
 * 类名称：StoreJoinin   
 * 类描述：   
 * 创建人：yanghui   
 * 创建时间：2014年11月9日 下午8:16:41   
 * 修改人：yanghui   
 * 修改时间：2014年11月9日 下午8:16:41   
 * 修改备注：   
 * @version
 *
 */
@Data
@ToString
public class StoreJoinin {

    // Fields

    private Integer memberId;
    private String memberName;
    private String companyName;
    private String companyAddress;
    private String companyAddressDetail;
    private String companyPhone;
    private Integer companyEmployeeCount;
    private Integer companyRegisteredCapital;
    private String contactsName;
    private String contactsPhone;
    private String contactsEmail;
    private String businessLicenceNumber;
    private String businessLicenceAddress;
    private Date businessLicenceStart;
    private Date businessLicenceEnd;
    private String businessSphere;
    private String businessLicenceNumberElectronic;
    private String organizationCode;
    private String organizationCodeElectronic;
    private String generalTaxpayer;
    private String bankAccountName;
    private String bankAccountNumber;
    private String bankName;
    private String bankCode;
    private String bankAddress;
    private String bankLicenceElectronic;
    private Integer isSettlementAccount;
    private String settlementBankAccountName;
    private String settlementBankAccountNumber;
    private String settlementBankName;
    private String settlementBankCode;
    private String settlementBankAddress;
    private String taxRegistrationCertificate;
    private String taxpayerId;
    private String taxRegistrationCertificateElectronic;
    private String sellerName;
    private String storeName;
    private String storeClassIds;
    private String storeClassNames;
    private String joininState;
    private String joininMessage;
    private String sgName;
    private Integer sgId;
    private String scName;
    private Integer scId;
    private String storeClassCommisRates;
    private String payingMoneyCertificate;
    private String payingMoneyCertificateExplain;
    /**
     * 0:未删除;1.已删除
     */
    private int isDel;
    /**
     * 创建时间
     */
    private Long createdTime;
    /**
     * 更新时间
     */
    private Long updatedTime;

    /**
     * 店铺可发布商品类目
     */
    private List<StoreBindClass> bindClassList;

    private String startTime;
    private String endTime;

}