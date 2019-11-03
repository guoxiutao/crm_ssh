package cn.itcast.dao.impl;


import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import cn.itcast.dao.UserDao;
import cn.itcast.domain.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {
	

	@Override
	public User getByUserCode(String user_code) {
		//Criteria
		//1 创建离线Criteria查询对象
		DetachedCriteria dc = DetachedCriteria.forClass(User.class);
		//2 封装参数
		dc.add(Restrictions.eq("user_code", user_code));
		//3 调用模板执行查询
		List<User> list = (List<User>) getHt().findByCriteria(dc);
		
		return list!=null&&list.size()>0?list.get(0):null;
	}
	

}
