package cn.itcast.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.dao.LinkManDao;
import cn.itcast.domain.LinkMan;
import cn.itcast.service.LinkManService;
import cn.itcast.utils.PageBean;

public class LinkManServiceImpl implements LinkManService {
	private LinkManDao lmd;
	
	@Override
	public PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize) {
		//1 查询总记录数
		Integer totalCount = lmd.getTotalCount(dc);
		//2 创建PageBean对象
		PageBean pb = new PageBean(currentPage, totalCount, pageSize);
		//3 查询列表数据,并放入PageBean中
		List list = lmd.getPageList(dc,pb.getStart(),pb.getPageSize());
		pb.setList(list);
		//4 返回PageBean对象
		return pb;
	}
	@Override
	public void save(LinkMan linkMan) {
		lmd.save(linkMan);
	}
	@Override
	public LinkMan getById(Long lkm_id) {
		return lmd.getById(lkm_id);
	}
	@Override
	public void delete(Long lkm_id) {
		lmd.delete(lkm_id);
	}

	public void setLmd(LinkManDao lmd) {
		this.lmd = lmd;
	}
	

}
