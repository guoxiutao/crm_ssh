package cn.itcast.dao;

import java.util.List;

import cn.itcast.domain.Customer;

public interface CustomerDao extends BaseDao<Customer> {
	//获得客户行业统计数据
	List<Object[]> getIndustryCount();}
