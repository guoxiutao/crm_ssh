package cn.itcast.dao;

import cn.itcast.domain.User;

public interface UserDao extends BaseDao<User> {
	//根据登陆名获得用户
	User getByUserCode(String user_code);
	
}
