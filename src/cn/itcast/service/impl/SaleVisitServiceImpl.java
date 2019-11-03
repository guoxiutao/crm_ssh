package cn.itcast.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.dao.SaleVisitDao;
import cn.itcast.domain.SaleVisit;
import cn.itcast.service.SaleVisitService;
import cn.itcast.utils.PageBean;

public class SaleVisitServiceImpl implements SaleVisitService {
	private SaleVisitDao svd;
	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//1 查询总记录数
		Integer totalCount = svd.getTotalCount(dc);
		//2 创建PageBean对象
		PageBean pb = new PageBean(currentPage, totalCount, pageSize);
		//3 查询列表数据,并放入PageBean中
		List list = svd.getPageList(dc,pb.getStart(),pb.getPageSize());
		pb.setList(list);
		//4 返回PageBean对象
		return pb;
	}
	@Override
	public void save(SaleVisit saleVisit) {
			svd.save(saleVisit);
	}

	public void setSvd(SaleVisitDao svd) {
		this.svd = svd;
	}

}
