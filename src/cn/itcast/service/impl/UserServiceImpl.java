package cn.itcast.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;
import cn.itcast.service.UserService;
import cn.itcast.utils.PageBean;

@Transactional(propagation=Propagation.REQUIRED,readOnly=true,isolation=Isolation.REPEATABLE_READ)
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao ud;
	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//1 查询总记录数
		Integer totalCount = ud.getTotalCount(dc);
		//2 创建PageBean对象
		PageBean pb = new PageBean(currentPage, totalCount, pageSize);
		//3 查询列表数据,并放入PageBean中
		List list = ud.getPageList(dc,pb.getStart(),pb.getPageSize());
		pb.setList(list);
		//4 返回PageBean对象
		return pb;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation=Isolation.REPEATABLE_READ)
	public void regist(User u) {
		if (u.getUser_id()==null) {//注册
			//1 判断登陆名是否重复
			User existU = ud.getByUserCode(u.getUser_code());
			if (existU != null) {
				throw new RuntimeException("用户名已被注册!");
			} 
		}
		//2 保存user
		ud.save(u);
	}
	@Override
	public User getLoginUser(User user) {
		//1.调用Dao根据登陆名查询User
		User existU = ud.getByUserCode(user.getUser_code());
		if(existU==null){
			//未获得=>用户名不正确
			throw new RuntimeException("用户名不存在!");
		}
		//2.比对密码是否一致
		if(!existU.getUser_password().equals(user.getUser_password())){
			//不一致=>密码错误
			throw new RuntimeException("密码错误!");
		}
		//3.返回查询的User
		return existU;
	}
	public void setUd(UserDao ud) {
		this.ud = ud;
	}
	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,isolation=Isolation.REPEATABLE_READ)
	public void delete(Long user_id) {
		ud.delete(user_id);
	}



	
}
