package cn.itcast.web.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.utils.PageBean;

/*
 * <action name="UserAction_*" class="userAction" method="{1}" >
			<result name="toHome" type="redirect" >/index.htm</result>
			<result name="error"  >/${errorPage}</result>
	</action> 
 */
@Controller("userAction")
@Scope("prototype")

@Namespace("/")//指定访问Action命名空间
@ParentPackage("struts-default")//指定继承package
public class UserAction extends ActionSupport implements ModelDriven<User> {
	private User user = new User();
	
	private String errorPage;
	@Autowired
	private UserService us;
	
	private Integer page;
	private Integer rows;
	//分页列表
	@Action(value="UserAction_list")
	public String list() throws Exception {
		//1 接收参数(属性驱动+模型驱动)
		//2 封装查询条件到DC
		DetachedCriteria dc= DetachedCriteria.forClass(User.class);
		//3 调用Service执行分页逻辑 => PageBean
		PageBean pb = us.getPageBean(dc,page,rows);
		//4 生成JSON
			Map map = new HashMap();
			map.put("total",pb.getTotalCount());
			map.put("rows", pb.getList());
			
			String json = JSON.toJSONString(map);
		//5 将Json发送
			ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
			ServletActionContext.getResponse().getWriter().write(json);
		return null;
	}
	//注册方法
	@Action(value="UserAction_regist")
	public String regist() throws Exception {
		ServletActionContext.getResponse().setContentType("text/plain;charset=utf-8");
		//1 调用Service执行注册逻辑
		try {
			us.regist(user);
			//告诉浏览器注册成功
			ServletActionContext.getResponse().getWriter().write("操作成功!");
		} catch (Exception e) {
			ServletActionContext.getResponse().getWriter().write(e.getMessage());
		}
		
		return null;
	}
	
	@Action(value="UserAction_delete")
	//删除用户
	public String delete() throws Exception {
		ServletActionContext.getResponse().setContentType("text/plain;charset=utf-8");
		//1 调用Service执行注册逻辑
		try {
			us.delete(user.getUser_id());
			//告诉浏览器注册成功
			ServletActionContext.getResponse().getWriter().write("操作成功!");
		} catch (Exception e) {
			ServletActionContext.getResponse().getWriter().write(e.getMessage());
		}
		
		return null;
	}
	
	
	//登陆方法
	@Action(value="UserAction_login",
				results=
					@Result(name="toHome",type="redirect",location="/index.htm"))
	public String login() throws Exception {
		errorPage = "login.jsp";
		//1 调用Service传递登陆参数 => User对象
		User existU = us.getLoginUser(user);
		//2 将返回的User对象放入session域
		ActionContext.getContext().getSession().put("user",existU);
		//3 重定向到项目首页
		return "toHome";
	}



	@Override
	public User getModel() {
		return user;
	}
	public void setUs(UserService us) {
		this.us = us;
	}


	public String getErrorPage() {
		return errorPage;
	}


	public void setErrorPage(String errorPage) {
		this.errorPage = errorPage;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
}
