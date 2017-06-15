package com.Vshop.front.module.store.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Vshop.service.module.area.service.AreaService;
import com.Vshop.service.module.goods.service.GoodsClassService;
import com.Vshop.service.module.website.service.ArticleClassService;


/**
 * Created by rabook on 2015/3/28.
 */
@Controller
@RequestMapping("/joinIn")
public class StoreJoinInAction {

    @Resource
    private ArticleClassService articleClassService;

    @Resource
    private AreaService areaService;

//    @Resource
//    private StoreGradeService storeGradeService;
//
//    @Resource
//    private StoreClassService storeClassService;

    @Resource
    private GoodsClassService goodsClassService;

//    @Resource
//    private StoreJoinInService storeJoinInService;

//    @Resource
//    private SellerService sellerService;
    /**
     * 步骤1
     * @return
     */
//    @RequestMapping("/step1")
//    public String step1(){
//        Subject subject = SecurityUtils.getSubject();
//        if(subject.isAuthenticated() || subject.isRemembered()){
//            StoreJoinin storeJoinin = storeJoinInService.findByMemberId(CacheUtils.getCacheUser().getMember().getMemberId());
//            if (storeJoinin == null) {
//                return "store/joinIn";
//            } else if ("10".equals(storeJoinin.getJoininState())) {
//                return "store/joinSuccess";
//            } else if ("11".equals(storeJoinin.getJoininState())) {
//                return "store/joinSuccess1";
//            } else if ("20".equals(storeJoinin.getJoininState())) {
//                return "redirect:/joinIn/step3";
//            } else if ("40".equals(storeJoinin.getJoininState())) {
//                return "redirect:"+CommonConstants.SELLER_SERVER;
//            } else {
//                return "store/joinError";
//            }
//
//        }else{
//            return "redirect:/login";
//        }
//
//    }

    /**
     * 步骤2
     * @return
     */
//    @RequestMapping("/step2")
//    public String step2(Model model){
//
//        // 尾部菜单
//        List<ArticleClassTitleVo> listArticleClassTitleVo = articleClassService.findTitleList();
//        List<Area> areas = areaService.queryAll();
//        List<Classs> classList = storeClassService.queryClassList();
//
//        model.addAttribute("areas", areas);
//        model.addAttribute("listArticleClassTitleVo", listArticleClassTitleVo);
//        model.addAttribute("gradeList",storeGradeService.queryStoreGradeList());
//        model.addAttribute("classList",classList);
//        model.addAttribute("gcList",goodsClassService.findByGcId(0));
//        return "store/joinInStep2";
//    }
//
//    @RequestMapping("/getGc")
//    public @ResponseBody List<GoodsClass> getGoodsClass(@RequestParam int gcId){
//
//        List<GoodsClass> list = goodsClassService.findByGcId(gcId);
//        return list;
//    }

//    @RequestMapping("/save")
//    public String saveJoinIn(@ModelAttribute StoreJoinin storeJoinin ,@RequestParam Integer[] storeJoinClassId,@RequestParam String[] storeJoinClassName
//       ,@RequestParam("file0") MultipartFile[] file0
//       ,@RequestParam("file1") MultipartFile[] file1,@RequestParam("file2") MultipartFile[] file2,
//        @RequestParam("file3") MultipartFile[] file3, @RequestParam("file4") MultipartFile[] file4){
//
//        String businessLicenceNumberElectronic = "";
//        try {
//            Map<String,Object> map = com.Vshop.core.common.FileUtils.fileUpload(file4,
//                    CommonConstants.FILE_BASEPATH,Constants.LOGO_UPLOAD_URL);
//            businessLicenceNumberElectronic = (String)map.get("result");
//        } catch (IOException e) {
//            log.error("上传文件失败", e.toString());
//        }
//        storeJoinin.setBusinessLicenceNumberElectronic(businessLicenceNumberElectronic);
//        String organizationCodeElectronic = "";
//        try {
//            Map<String,Object> map = com.Vshop.core.common.FileUtils.fileUpload(file0,
//                    CommonConstants.FILE_BASEPATH,Constants.LOGO_UPLOAD_URL);
//            organizationCodeElectronic = (String)map.get("result");
//        } catch (IOException e) {
//            log.error("上传文件失败", e.toString());
//        }
//        storeJoinin.setOrganizationCodeElectronic(organizationCodeElectronic);
//        String generalTaxpayer = "";
//        try {
//            Map<String,Object> map = com.Vshop.core.common.FileUtils.fileUpload(file1,
//                    CommonConstants.FILE_BASEPATH,Constants.LOGO_UPLOAD_URL);
//            generalTaxpayer = (String)map.get("result");
//        } catch (IOException e) {
//            log.error("上传文件失败", e.toString());
//        }
//        storeJoinin.setGeneralTaxpayer(generalTaxpayer);
//        String bankLicenceElectronic = "";
//        try {
//            Map<String,Object> map = com.Vshop.core.common.FileUtils.fileUpload(file2,
//                    CommonConstants.FILE_BASEPATH,Constants.LOGO_UPLOAD_URL);
//            bankLicenceElectronic = (String)map.get("result");
//        } catch (IOException e) {
//            log.error("上传文件失败", e.toString());
//        }
//        storeJoinin.setBankLicenceElectronic(bankLicenceElectronic);
//        String taxRegistrationCertificateElectronic = "";
//        try {
//            Map<String,Object> map = com.Vshop.core.common.FileUtils.fileUpload(file3,
//                    CommonConstants.FILE_BASEPATH,Constants.LOGO_UPLOAD_URL);
//            taxRegistrationCertificateElectronic = (String)map.get("result");
//        } catch (IOException e) {
//            log.error("上传文件失败", e.toString());
//        }
//        storeJoinin.setTaxRegistrationCertificateElectronic(taxRegistrationCertificateElectronic);
//        List<String> idList = Lists.newArrayList();
//        idList.add(Collections3.convertToString(Arrays.asList(storeJoinClassId),","));
//        List<String> nameList = Lists.newArrayList();
//        nameList.add(Collections3.convertToString(Arrays.asList(storeJoinClassName),","));
//        storeJoinin.setStoreClassNames(JsonUtils.toJsonStr(nameList));
//        storeJoinin.setStoreClassIds(JsonUtils.toJsonStr(idList));
//        storeJoinin.setMemberId(CacheUtils.getCacheUser().getMember().getMemberId());
//        storeJoinin.setMemberName(CacheUtils.getCacheUser().getMember().getMemberName());
//        storeJoinin.setBusinessLicenceStart(DateUtils.parse(storeJoinin.getStartTime(), "yyyy-MM-dd"));
//        storeJoinin.setBusinessLicenceEnd(DateUtils.parse(storeJoinin.getEndTime(), "yyyy-MM-dd"));
//        storeJoinin.setJoininState("10");
//        storeJoinInService.save(storeJoinin);
//
//        return "redirect:/joinIn/JoinInSuccess";
//    }

    @RequestMapping("/JoinInSuccess")
    public String JoinInSuccess(Model model){

        model.addAttribute("success","success");
        return "store/joinInStep2";
    }

//    @RequestMapping("/checkSeller")
//     public @ResponseBody Map<String,Object> checkSeller(@RequestParam String sellerName){
//        Map<String,Object> map = Maps.newHashMap();
//        if(sellerService.findBySellerName(sellerName) != null){
//            map.put("success",false);
//        }else{
//            map.put("success",true);
//        }
//        return map;
//    }

//    @RequestMapping("/step3")
//    public String joinInStep3(Model model) {
//
//        Map<String, Object> map = storeJoinInService.findById(CacheUtils.getCacheUser().getMember().getMemberId().longValue());
//        StoreJoinin storeJoinin = (StoreJoinin) map.get("joinIn");
//        model.addAttribute("storeJoinin", storeJoinin);
//        model.addAttribute("name", map.get("name"));
//        return "store/joinInStep3";
//    }

//    @RequestMapping("/savePay")
//    public String savePay(@ModelAttribute StoreJoinin storeJoinin, @RequestParam("myFile") MultipartFile[] file) {
//
//        String payingMoneyCertificate = "";
//        try {
//            Map<String, Object> map = com.Vshop.core.common.FileUtils.fileUpload(file,
//                    CommonConstants.FILE_BASEPATH, Constants.LOGO_UPLOAD_URL);
//            payingMoneyCertificate = (String) map.get("result");
//        } catch (IOException e) {
//            log.error("上传文件失败", e.toString());
//        }
//        storeJoinin.setPayingMoneyCertificate(payingMoneyCertificate);
//        storeJoinin.setJoininState("11");
//        storeJoinin.setMemberId(CacheUtils.getCacheUser().getMember().getMemberId());
//        storeJoinInService.updateJoinIn(storeJoinin);
//        return "store/joinSuccess1";
//    }
}
