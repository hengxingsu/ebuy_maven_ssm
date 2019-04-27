package com.lcvc.ebuy_maven_ssm.web.backstage.adminmanage;

import com.lcvc.ebuy_maven_ssm.model.Admin;
import com.lcvc.ebuy_maven_ssm.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminManageController {
	@Resource
	private AdminService adminService;

	//跳转到管理员管理页面
	@RequestMapping(value = "/backstage/adminmanage/toManageAdmin", method = RequestMethod.GET)
	public String toManageAdmin(HttpServletRequest request){
		request.setAttribute("list",adminService.getAdminList());
		return "/jsp/backstage/adminmanage/adminmanage.jsp";
	}
	//执行删除管理员操作
	@RequestMapping(value = "/backstage/adminmanage/toDeleteAdmin", method = RequestMethod.GET)
	public String toDeleteAdmin(HttpServletRequest request,HttpSession session,Integer id){
		Admin admin=(Admin) session.getAttribute("admin");
        adminService.deleteAdmin(id,admin.getId());
		request.setAttribute("list",adminService.getAdminList());
		return "/backstage/adminmanage/toManageAdmin";
	}

	//跳转到添加管理员页面
	@RequestMapping(value = "/backstage/adminmanage/toAddAdmin", method = RequestMethod.GET)
	  public String toAddAdmin(HttpServletRequest request){
		return "/jsp/backstage/adminmanage/adminadd.jsp";
	}
	//执行添加管理员操作
	@RequestMapping(value = "/backstage/adminmanage/doAddAdmin", method = RequestMethod.POST)
	public String doAddAdmin(String username,String name,Integer id,HttpSession session,HttpServletRequest request) {
		Admin admin = (Admin) session.getAttribute("admin");

		if (username.equals("")){
			request.setAttribute("myMessage","账户名不能为空");
		}else  if (name.equals("")){
			request.setAttribute("myMessage","网名不能为空");
		}else  if (adminService.existsAdmin(username,admin.getId())){
			request.setAttribute("myMessage","账户名重名，请重新输入");
		}else {
          // if (adminService.saveAdmin(admin)){

			admin.setUsername(username);
			admin.setName(name);
			adminService.saveAdmin(admin);
			request.setAttribute("myMessage", "添加管理员成功");
		 //  }
		}
		return "/jsp/backstage/adminmanage/adminadd.jsp";
	}


}
