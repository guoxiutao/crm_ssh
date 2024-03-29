package cn.itcast.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/*
 * CREATE TABLE `sys_user` (
  `user_id` bigint(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_code` varchar(32) NOT NULL COMMENT '用户账号',
  `user_name` varchar(64) NOT NULL COMMENT '用户名称',
  `user_password` varchar(32) NOT NULL COMMENT '用户密码',
  `user_state` char(1) NOT NULL COMMENT '1:正常,0:暂停',
  PRIMARY KEY (`user_id`)
)
 */
@Entity(name="sys_user") //指定实体对应的表
public class User {
	@Id//该属性是ID
	//@Column(name="uid")//user_id对应的列是uid
	@GeneratedValue(strategy=GenerationType.IDENTITY)//主键生成策略
	private Long user_id;
	@Column(name="user_code")
	private String user_code;
	@Column
	private String user_name;
	@Column
	private String user_password;
	@Column
	private Character user_state;
	//一对多
	 @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
	private Set<SaleVisit> saleVisits = new HashSet<SaleVisit>();

	
	public Set<SaleVisit> getSaleVisits() {
		return saleVisits;
	}

	public void setSaleVisits(Set<SaleVisit> saleVisits) {
		this.saleVisits = saleVisits;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public Character getUser_state() {
		return user_state;
	}

	public void setUser_state(Character user_state) {
		this.user_state = user_state;
	}

	
}
