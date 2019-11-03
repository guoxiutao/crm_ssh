package cn.itcast.web.action;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.itcast.domain.Customer;
import cn.itcast.service.CustomerService;
import cn.itcast.utils.PageBean;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	
	private Customer customer = new Customer();

	private CustomerService cs;
	
	
	private Integer currentPage;
	private Integer pageSize;
	//分页列表
	public String list() throws Exception {
		//1 接收参数(属性驱动+模型驱动)
		//2 封装查询条件到DC
		DetachedCriteria dc= DetachedCriteria.forClass(Customer.class);
		if(StringUtils.isNotBlank(customer.getCust_name())){
			dc.add(Restrictions.like("cust_name", "%"+customer.getCust_name()+"%"));
		}
		//3 调用Service执行分页逻辑 => PageBean
		PageBean pb = cs.getPageBean(dc,currentPage,pageSize);
		//4 将PageBean放入request域
		ActionContext.getContext().put("pageBean", pb);
		//5 转发到列表页面显示
		return "list";
		
	}
	//接收上传文件内容
	private File cust_image;
	//接收文件名
	private String cust_imageFileName;
	//接收文件MIME类型(没啥用)
	private String cust_imageContentType;
	//保存
	public String add() throws Exception {
		if(cust_image!=null){
			//将文件转存到e盘upload文件夹下
			cust_image.renameTo(new File("e:/upload/"+UUID.randomUUID()+".jpg"));
			
			System.out.println("文件名:"+cust_imageFileName+",MIME类型:"+cust_imageContentType);
		}
		
		//1 调用Service执行保存
		cs.save(customer);
		//2 重定向到客户列表Action
		return "toList";
	}
	
	
	//回显
	public String toEdit() throws Exception {
		//1 调用Service根据id查询客户对象
		Customer c = cs.getById(customer.getCust_id());
		//2 将客户放入request域
		ActionContext.getContext().put("customer", c);
		//3 转发到添加页面回显
		return "add";
	}

	//删除
	public String delete() throws Exception {
		//1 调用Service执行删除
		//cs.deleteById(customer.getCust_id());
		//2 重定向到客户列表Action
		return "toList";
	}
	
	//行业统计
	public String industryCount() throws Exception {
			//1 调用Service查询统计信息
		List<Object[]> list = cs.getIndustryCount();
			//2 将统计信息放入request
		ActionContext.getContext().put("list", list);
			//3 转发到统计信息页面
			return "industryCount";
	}
	@Override
	public Customer getModel() {
		return customer;
	}

	public void setCs(CustomerService cs) {
		this.cs = cs;
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


	public File getCust_image() {
		return cust_image;
	}


	public void setCust_image(File cust_image) {
		this.cust_image = cust_image;
	}


	public String getCust_imageFileName() {
		return cust_imageFileName;
	}


	public void setCust_imageFileName(String cust_imageFileName) {
		this.cust_imageFileName = cust_imageFileName;
	}


	public String getCust_imageContentType() {
		return cust_imageContentType;
	}


	public void setCust_imageContentType(String cust_imageContentType) {
		this.cust_imageContentType = cust_imageContentType;
	}
	
	
}
