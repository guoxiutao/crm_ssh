package cn.itcast.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.itcast.dao.BaseDictDao;
import cn.itcast.domain.BaseDict;

public class BaseDictDaoImpl implements BaseDictDao {
	
	private HibernateTemplate ht;
	
	@Override
	public List<BaseDict> getByTypeCode(String dict_type_code) {
		
		DetachedCriteria dc = DetachedCriteria.forClass(BaseDict.class);
		
		dc.add(Restrictions.eq("dict_type_code", dict_type_code));
		
		return (List<BaseDict>) ht.findByCriteria(dc);
	}

	public void setHt(HibernateTemplate ht) {
		this.ht = ht;
	}

}
