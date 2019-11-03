package cn.itcast.web.action;

import java.io.File;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.Customer;
import cn.itcast.domain.LinkMan;
import cn.itcast.service.CustomerService;
import cn.itcast.service.LinkManService;
import cn.itcast.utils.PageBean;

public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan> {
	
	private LinkMan linkMan = new LinkMan();
	
	private LinkManService lms ;
	
	
	private Integer currentPage;
	private Integer pageSize;
	//分页列表
	public String list() throws Exception {
		//1 接收参数(属性驱动+模型驱动)
		//2 封装查询条件到DC
		DetachedCriteria dc= DetachedCriteria.forClass(LinkMan.class);
		if(StringUtils.isNotBlank(linkMan.getLkm_name())){
			dc.add(Restrictions.like("lkm_name", "%"+linkMan.getLkm_name()+"%"));
		}
		if(linkMan.getCustomer()!=null && linkMan.getCustomer().getCust_id()!=null){
			dc.add(Restrictions.eq("customer.cust_id", linkMan.getCustomer().getCust_id()));
		}
		//3 调用Service执行分页逻辑 => PageBean
		PageBean pb = lms.getPageBean(dc,currentPage,pageSize);
		//4 将PageBean放入request域
		ActionContext.getContext().put("pageBean", pb);
		//5 转发到列表页面显示
		return "list";
		
	}
	
	//添加联系人
	public String add() throws Exception {
		
		//1 调用Service执行保存联系人逻辑
		lms.save(linkMan);
		//2 重定向到联系人列表Action
		return "toList";
	}
	//回显联系人
	public String toEdit() throws Exception {
			
			//1 调用Service根据id获得LinkMan对象
			LinkMan lm = lms.getById(linkMan.getLkm_id());
			//2将LinkMan对象放入request域,转发到添加页面回显
			ActionContext.getContext().put("linkMan", lm);
			return "add";
	}
	//回显联系人
		public String delete() throws Exception {
				
				//1 调用Service根据id删除LinkMan对象
				lms.delete(linkMan.getLkm_id());
				//2 重定向回联系人列表
				return "toList";
		}
	@Override
	public LinkMan getModel() {
		return linkMan;
	}

	public void setLms(LinkManService lms) {
		this.lms = lms;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
}
