package cn.itcast.domain;

import org.apache.commons.lang3.StringUtils;

/*
 * CREATE TABLE `cst_linkman` (
  `lkm_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '联系人编号(主键)',
  `lkm_name` varchar(16) DEFAULT NULL COMMENT '联系人姓名',
  `lkm_cust_id` bigint(32) NOT NULL COMMENT '客户id',
  `lkm_gender` char(1) DEFAULT NULL COMMENT '联系人性别',
  `lkm_phone` varchar(16) DEFAULT NULL COMMENT '联系人办公电话',
  `lkm_mobile` varchar(16) DEFAULT NULL COMMENT '联系人手机'
  PRIMARY KEY (`lkm_id`),
  KEY `FK_cst_linkman_lkm_cust_id` (`lkm_cust_id`),
  CONSTRAINT `FK_cst_linkman_lkm_cust_id` FOREIGN KEY (`lkm_cust_id`) REFERENCES `cst_customer` (`cust_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
)
 */
public class LinkMan {
	
	private Long lkm_id;
	private String lkm_name;
	private String lkm_phone;
	private String lkm_mobile;
	private Character lkm_gender;
	
	//表达联系人与客户的多对一关系
	//使用客户类型属性引用联系人唯一所属的客户
	private Customer customer;

	public Long getLkm_id() {
		return lkm_id;
	}

	public void setLkm_id(Long lkm_id) {
		this.lkm_id = lkm_id;
	}

	public String getLkm_name() {
		return lkm_name;
	}

	public void setLkm_name(String lkm_name) {
		this.lkm_name = lkm_name;
	}

	public String getLkm_phone() {
		return lkm_phone;
	}

	public void setLkm_phone(String lkm_phone) {
		this.lkm_phone = lkm_phone;
	}

	public String getLkm_mobile() {
		return lkm_mobile;
	}

	public void setLkm_mobile(String lkm_mobile) {
		this.lkm_mobile = lkm_mobile;
	}

	public Character getLkm_gender() {
		return lkm_gender;
	}
	public String getLkm_gender_s() {
		if(lkm_gender == null){
			return "";
		}
		switch (lkm_gender) {
		case '1':
			return "男";
		case '2':
			return "女";
		default:
			return "";
		}
		
		
	}
	public void setLkm_gender(Character lkm_gender) {
		this.lkm_gender = lkm_gender;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "LinkMan [lkm_id=" + lkm_id + ", lkm_name=" + lkm_name + "]";
	}
	
	

}
