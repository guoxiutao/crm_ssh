package cn.itcast.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.SaleVisit;
import cn.itcast.utils.PageBean;

public interface SaleVisitService {
	//保存拜访记录
	void save(SaleVisit saleVisit);
	//分页列表
	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);

}
