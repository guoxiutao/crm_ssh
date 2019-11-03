package cn.itcast.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.User;
import cn.itcast.utils.PageBean;

public interface UserService {
	//注册用户
	void regist(User u);
	//获得登陆用户
	User getLoginUser(User user);
	//用户列表
	PageBean getPageBean(DetachedCriteria dc, Integer page, Integer rows);
	//删除用户
	void delete(Long user_id);
}
