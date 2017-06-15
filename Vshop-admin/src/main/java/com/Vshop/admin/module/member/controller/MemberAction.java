package com.Vshop.admin.module.member.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Maps;
import com.Vshop.core.common.Constants;
import com.Vshop.core.common.DateUtils;
import com.Vshop.core.entity.base.Member;
import com.Vshop.core.entity.base.MemberGrade;
import com.Vshop.core.ossconfigure.OSSConfigure;
import com.Vshop.core.ossconfigure.OSSManageUtil;
import com.Vshop.service.module.member.service.MemberGradeService;
import com.Vshop.service.module.member.service.MemberService;
import com.Vshop.service.utils.CommonConstants;
import com.Vshop.service.utils.page.Pager;

/**
 *    
 * 项目名称：Vshop-admin   
 * 类名称：MemberAction   
 * 类描述：会员管理功能实现类
 * 创建人：sangyuchen   
 * 创建时间：2014年11月10日 上午9:41:21   
 * 修改人：sangyuchen   
 * 修改时间：2014年11月10日 上午9:41:21   
 * 修改备注：   
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/member")
@Slf4j
public class MemberAction {

	String message = "success";

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberGradeService membergradeservice;


	/** 导航至主操作页面 */
	@RequestMapping("/add")
	public String add() {
		return "/member/addMember";
	}

	/**
	 * 
	 * @Title: list
	 * @Description: TODO (查询方法)
	 * @param @param model
	 * @param @param div
	 * @param @param pageNoStr
	 * @param @param acctName
	 * @param @param certifyClass
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	@RequestMapping(value = "/list")
	public String list(
			Model model,
			@RequestParam(required = false, value = "pageNo", defaultValue = "") String pageNoStr,
            @ModelAttribute Member member,
            @RequestParam(required = false, value = "memberNameflag", defaultValue = "") String memberNameflag,
			@RequestParam(required = false, value = "queryType", defaultValue = "") String queryType) {
		Pager pager = new Pager();
		/** 查询条件，放入实体中， **/
        if ("1".equals(queryType)) {  // 会员名查询
            member.setMemberName(memberNameflag);
        } else if("2".equals(queryType)){ // 电子邮箱查询
            member.setMemberEmail(memberNameflag);
        } else{
            member.setMemberTruename(memberNameflag); // 真实姓名查询
        }
		if (null != pageNoStr && !pageNoStr.equals("")) {
			pageNoStr = pageNoStr.replace("," , "");
			pager.setPageNo(Integer.parseInt(pageNoStr));
		}
		pager.setCondition(member);// 实体加载在pager中

//		int total = memberService.findMemberCount(member);// 获取总条数
		List<Member> results = memberService.findMemberList(pager);// 结果集
		for(Member memberstr:results){
			if(memberstr.getMemberOldLoginTime()!=null&&!"".equals(memberstr.getMemberOldLoginTime())){
				memberstr.setMemberOldLoginTimestr(DateUtils.getTimestampByLong(memberstr.getMemberOldLoginTime()));
			}
		}
//		pager.setTotalRows(total);
        pager.setResult(results);
        model.addAttribute("pager",pager);
        model.addAttribute("queryType",queryType);
        model.addAttribute("memberName",memberNameflag);
        model.addAttribute("member",member);
		// 转发请求到FTL页面
		return "/member/list";

	}


	/**
	 * 查询单挑记录
	 * @Title: findById
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param model
	 * @param @param id
	 * @param @param div
	 * @param @return    设定文件
	 * @return String    返回类型
	 * @throws
	 */
	@RequestMapping(value = "/findById")
	public String findById(
			Model model,
			@RequestParam(required = false, value = "id", defaultValue = "") int id,
			@RequestParam(required = false, value = "div", defaultValue = "") String div) {
			Member member = memberService.findMemberById(Integer.valueOf(id));
			Pager pager=new Pager();
			MemberGrade membergrade=new MemberGrade();
			pager.setCondition(membergrade);
			List<MemberGrade> mbmergradelsit=membergradeservice.findMemberGradePageList(pager);
			model.addAttribute("mbmergradelsit", mbmergradelsit);
			model.addAttribute("member", member);
			model.addAttribute("div", div);
			return "/member/editMember";
	}

	/**
	 * 编辑或修改用户
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public String saveOrUpdate(
			@RequestParam("imageFile") MultipartFile[] image,
			@ModelAttribute Member member,
			HttpServletRequest request,
			Model model,
			@RequestParam(required = false, value = "div", defaultValue = "") String div) throws IOException {

		String referer = request.getHeader("Referer");
		model.addAttribute("referer", referer);
		String memberAvatar = "";
		try {
//			 = com.Vshop.core.common.FileUtils
//					.fileUpload(image, CommonConstants.FILE_BASEPATH,
//							Constants.MEMBER_UPLOAD_URL, request,"images",1);
			
			  OSSConfigure ossConfigure=new OSSConfigure();
			  Map<String, Object> 	map =OSSManageUtil.uploadFile(ossConfigure, image, Constants.MEMBER_UPLOAD_URL ,"images",request);
			memberAvatar = (String) map.get("result");
		} catch (Exception e) {
			log.error("上传文件失败", e.toString());
		}
		member.setMemberAvatar(memberAvatar);
		if (member.getMemberId() == null) {
		 // member.setMemberPasswd(Digests.entryptPassword(member.getMemberPasswd()));
			memberService.save(member);
			model.addAttribute("msg", "保存成功");
		} else {
			// Member dbMember =
			// memberService.findMemberById(member.getMemberId());
			// if (dbMember == null) {
			// log.warn("用户未找到，Id="+member.getMemberId());
			// } else {
			// if (StringUtils.isNotBlank(member.getMemberPasswd())) {
			// dbMember.setMemberPasswd(CyptoUtils.md5(member.getMemberPasswd()));
			// }
			// if(StringUtils.isNotBlank(memberAvatar)){
			// dbMember.setMemberAvatar(memberAvatar);
			// }
			// dbMember.setMemberName(member.getMemberName());
			// dbMember.setMemberTruename(member.getMemberTruename());
			// dbMember.setMemberWw(member.getMemberWw());
			// dbMember.setMemberSex(member.getMemberSex());
			// dbMember.setMemberQq(member.getMemberQq());
			// dbMember.setMemberEmail(member.getMemberEmail());
			// dbMember.setIsBuy(member.getIsBuy());
			// dbMember.setIsAllowtalk(member.getIsAllowtalk());
			// dbMember.setInformAllow(member.getInformAllow());
			// dbMember.setMemberState(member.getMemberState());
		  //member.setMemberAvatar(memberAvatar);
			memberService.updateMember(member);
			model.addAttribute("msg", "修改成功");
			// }
		}
		return Constants.MSG_URL;

	}

	/**
	 * 
	 * @Title: delMember
	 * @Description: TODO (删除会员)
	 * @param @param ids
	 * @param @param model
	 * @param @return  设定文件
	 * @return Map<String,String>  返回类型
	 * @throws
	 */
	@RequestMapping(value = "/delMember", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, String> delMember(@RequestParam(value = "ids") String ids,
			Model model) {

		Map<String, String> map = Maps.newHashMap();

		if (StringUtils.isBlank(ids)) {
			model.addAttribute("result", "ID为空");
			map.put("result", "ID为空");
			map.put(message, "true");
			return map;
		}
		String[] idArray = StringUtils.split(ids, ",");
		for (String idStr : idArray) {
			memberService.delete(Long.parseLong(idStr));
		}
		map.put("result", "删除成功");
		map.put(message, "true");
		return map;
	}

    /**
     * 校验表单
     * @return
     */
    @RequestMapping("/validate")
    public @ResponseBody String validateForm(@RequestParam(required = false, value = "memberName", defaultValue = "") String memberName){
        Member member=memberService.findMemberByName(memberName);
        //校验重复
        if(member!=null){
            return "false";
        }else{
            return "true";
        }
    }
    
    @RequestMapping("/checkEmail")
    public
    @ResponseBody
    boolean checkEmail(@RequestParam(required = false, value = "memberEmail", defaultValue = "") String memberEmail,
    		@RequestParam(required = false, value = "memberEmailt", defaultValue = "") String memberEmailt) {
    	if(memberEmail.equals(memberEmailt)){
    		return true;
	    }else{
	        if (memberService.findMemberByEmail(memberEmail) != null) {
	            return false;
	        } else {
	            return true;
	        }
    	}
    }
}