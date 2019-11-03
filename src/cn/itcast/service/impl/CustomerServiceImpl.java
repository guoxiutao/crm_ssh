package cn.itcast.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.dao.CustomerDao;
import cn.itcast.domain.Customer;
import cn.itcast.service.CustomerService;
import cn.itcast.utils.PageBean;

public class CustomerServiceImpl implements CustomerService {

	private CustomerDao cd;
	
	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//1 查询总记录数
		Integer totalCount = cd.getTotalCount(dc);
		//2 创建PageBean对象
		PageBean pb = new PageBean(currentPage, totalCount, pageSize);
		//3 查询列表数据,并放入PageBean中
		List list = cd.getPageList(dc,pb.getStart(),pb.getPageSize());
		pb.setList(list);
		//4 返回PageBean对象
		return pb;
	}

	@Override
	public void save(Customer customer) {
		//判断数据字典对象是否有dict_id值
		if(customer.getCust_industry().getDict_id()==null){
			//没有=>表单未提交 => 将数据字典属性设置为空
			customer.setCust_industry(null);
		}
		if(customer.getCust_source().getDict_id()==null){
			//没有=>表单未提交 => 将数据字典属性设置为空
			customer.setCust_source(null);
		}
		if(customer.getCust_level().getDict_id()==null){
			//没有=>表单未提交 => 将数据字典属性设置为空
			customer.setCust_level(null);
		}
		cd.save(customer);
	}
	@Override
	public Customer getById(Long cust_id) {
		return cd.getById(cust_id);
	}
	public void setCd(CustomerDao cd) {
		this.cd = cd;
	}

	@Override
	public void deleteById(Long cust_id) {
		cd.delete(cust_id);
	}

	@Override
	public List<Object[]> getIndustryCount() {
		return cd.getIndustryCount();
	}

	
}
