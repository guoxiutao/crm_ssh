package cn.itcast.web.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

import cn.itcast.domain.BaseDict;
import cn.itcast.service.BaseDictService;

public class BaseDictAction extends ActionSupport {
		
	//属性驱动
	private String dict_type_code;

	
	private BaseDictService bds;
	
	
	@Override
	public String execute() throws Exception {
		//1 调用Service根据typecode查询数据字典list
		List<BaseDict> list = bds.getByTypeCode(dict_type_code);
		//2 将list转换为json字符串
		String json = JSON.toJSONString(list);
		//3 将json字符串发送给浏览器
		//* 解决乱码
		ServletActionContext.getResponse().setContentType("application/json;charset=utf-8");
		//* struts2结果集处理
		ServletActionContext.getResponse().getWriter().write(json);
		
		return null;
	}

	public String getDict_type_code() {
		return dict_type_code;
	}

	public void setDict_type_code(String dict_type_code) {
		this.dict_type_code = dict_type_code;
	}

	public void setBds(BaseDictService bds) {
		this.bds = bds;
	}
	
}
