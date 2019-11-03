package cn.itcast.web.action;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.Customer;
import cn.itcast.domain.SaleVisit;
import cn.itcast.domain.User;
import cn.itcast.service.SaleVisitService;
import cn.itcast.utils.PageBean;

public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {
		private SaleVisit saleVisit = new SaleVisit();
		private SaleVisitService svs;
		
		
		private Integer currentPage;
		private Integer pageSize;
		//分页列表
		public String list() throws Exception {
			//1 接收参数(属性驱动+模型驱动)
			//2 封装查询条件到DC
			DetachedCriteria dc= DetachedCriteria.forClass(SaleVisit.class);
			if(saleVisit.getCustomer()!=null && saleVisit.getCustomer().getCust_id()!=null){
				dc.add(Restrictions.eq("customer.cust_id", saleVisit.getCustomer().getCust_id()));
			}
			//3 调用Service执行分页逻辑 => PageBean
			PageBean pb = svs.getPageBean(dc,currentPage,pageSize);
			//4 将PageBean放入request域
			ActionContext.getContext().put("pageBean", pb);
			//5 转发到列表页面显示
			return "list";
			
		}
		
		public String add() throws Exception {
			//1 从session获得登陆用户,并放入模型对象中
			User u = 	(User) ActionContext.getContext().getSession().get("user");
			saleVisit.setUser(u);
			//2 调用Service执行保存逻辑
			svs.save(saleVisit);
			//3 重定向的列表
			return "toList";
		}
		
		
		@Override
		public SaleVisit getModel() {
			return saleVisit;
		}
		public void setSvs(SaleVisitService svs) {
			this.svs = svs;
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
