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
	@RequestMapping(value = "/backstage/adminmanage/doDeleteAdmin", method = RequestMethod.GET)
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
	public String doAddAdmin(Admin admin,HttpSession session,HttpServletRequest request) {
	  	admin.setUsername(admin.getUsername().trim());  // trim() 删除空格符
	  	admin.setName(admin.getName().trim());
		if (admin.getUsername().length() == 0) {
			request.setAttribute("myMessage", "创建管理员失败：账户名不能为空");
		} else if (admin.getName().length() == 0) {
			request.setAttribute("myMessage", "创建管理员失败：网名不能为空");
		} else if (adminService.existsUsername(admin.getUsername())) {
			request.setAttribute("myMessage", "创建管理员失败：账户名重名，请重新输入");
		} else {
			if (adminService.saveAdmin(admin)) {
				request.setAttribute("myMessage", "添加管理员成功");
			} else {
				request.setAttribute("myMessage", "添加管理员失败");
			}
		}
		return "/jsp/backstage/adminmanage/adminadd.jsp";
	}

	//跳转到管理编辑员页面
	@RequestMapping(value = "/backstage/adminmanage/toUpdateAdmin", method = RequestMethod.GET)
	public String toUpdateAdmin(HttpServletRequest request,Integer id){
	  	request.setAttribute("admin",adminService.getAdmin(id));
		return "/jsp/backstage/adminmanage/adminupdate.jsp";
	}
	//执行管理员编辑操作
	@RequestMapping(value = "/backstage/adminmanage/doUpdateAdmin", method = RequestMethod.POST)
	public String doUpdateAdmin(HttpSession session,Admin admin,HttpServletRequest request) {
		admin.setUsername(admin.getUsername().trim());  // trim() 删除空格符
		admin.setName(admin.getName().trim());
		if (admin.getUsername().equals("")){
			request.setAttribute("myMessage","账户修改失败：账户名不能为空");
		}else  if (admin.getName().equals("")){
			request.setAttribute("myMessage","账户修改失败：网名不能为空");
		}else  if (adminService.existsAdmin(admin.getUsername(),admin.getId())){
			request.setAttribute("myMessage","账户修改失败：账户名重名,请重新输入");
		}else {
			if (adminService.updateAdmin(admin)) {
				request.setAttribute("myMessage", "账户修改成功");
			}else {
				request.setAttribute("myMessage","账户修改失败");
			}
		}
		return "/jsp/backstage/adminmanage/adminupdate.jsp";
	}

}
