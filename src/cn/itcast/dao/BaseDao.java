package cn.itcast.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface BaseDao<T> {
	//增
	void save(T t);
	//删
	void delete(Serializable id); //session.get(Class clazz,Serializable id)
	//改
	void update(T t);
	//查 根据id查
	T getById(Serializable id);
	//获得总记录数
	Integer getTotalCount(DetachedCriteria dc);
	//获得分页列表数据
	List getPageList(DetachedCriteria dc, Integer start, Integer pageSize);
}
