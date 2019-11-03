package cn.itcast.service;

import org.hibernate.criterion.DetachedCriteria;

import cn.itcast.domain.LinkMan;
import cn.itcast.utils.PageBean;

public interface LinkManService {
	//执行分页逻辑
	PageBean getPageBean(DetachedCriteria dc, Integer currentPage, Integer pageSize);
	//保存联系人
	void save(LinkMan linkMan);
	//根据id获得LinkMan对象
	LinkMan getById(Long lkm_id);
	//删除联系人
	void delete(Long lkm_id);

}
