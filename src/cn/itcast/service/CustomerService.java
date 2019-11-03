package cn.itcast.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.Customer;
import cn.itcast.utils.PageBean;

public interface CustomerService {
	//执行分页逻辑
	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
	//保存客户
	void save(Customer customer);
	//根据id获得客户
	Customer getById(Long cust_id);
	//根据id删除客户
	void deleteById(Long cust_id);
	//获得客户行业统计信息
	List<Object[]> getIndustryCount();

}
