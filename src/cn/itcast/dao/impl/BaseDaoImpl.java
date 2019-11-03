package cn.itcast.dao.impl;

import cn.itcast.dao.BaseDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public class BaseDaoImpl<T> implements BaseDao<T> {
	
	@Autowired
	private HibernateTemplate ht;
	
	private Class clazz;
	
	
	public BaseDaoImpl() {
		//java中的反射API
		//获得可获得泛型类型的父类
		ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		//获得泛型类型
		clazz = (Class) pt.getActualTypeArguments()[0];
	}

	@Override
	public void save(T t) {
		ht.saveOrUpdate(t);
	}

	@Override
	public void delete(Serializable id) {
		T t = this.getById(id);
		ht.delete(t);
	}

	@Override
	public void update(T t) {
		ht.update(t);
	}

	@Override
	public T getById(Serializable id) {
		return (T) ht.load(clazz, id);
	}

	@Override
	public Integer getTotalCount(DetachedCriteria dc) {
		//设置查询总记录数(count)
		dc.setProjection(Projections.rowCount());//select count(*)
		List<Long> list = (List<Long>) ht.findByCriteria(dc);
		//清空总记录数条件
		dc.setProjection(null);
		System.out.println("修改开发代码");
		return list.get(0).intValue();
	}

	@Override
	public List getPageList(DetachedCriteria dc, Integer start, Integer pageSize) {
		return ht.findByCriteria(dc, start, pageSize);
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}

	public HibernateTemplate getHt() {
		return ht;
	}

}
