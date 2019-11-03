package cn.itcast.dao.impl;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;

import java.util.List;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	@Override
	public List<Object[]> getIndustryCount() {
		//原生SQL
		return getHt().execute(new HibernateCallback<List<Object[]>>() {
			@Override
			public List<Object[]> doInHibernate(Session session) throws HibernateException {
				//1 创建SQL
				String sql =" SELECT                               " +
							"   d.`dict_item_name`,                " +
							"   COUNT(c.`cust_id`)                 " +
							" FROM                                 " +
							"   base_dict d,                       " +
							"   cst_customer c                     " +
							" WHERE c.`cust_industry` = d.`dict_id`" +
							" GROUP BY d.`dict_id`                 " ;
				//2 创建SQLQuery对象
				SQLQuery query = session.createSQLQuery(sql);
				//3 执行SQL, 返回结果
				return query.list();
			}
		});//万能方法
		
	}}
