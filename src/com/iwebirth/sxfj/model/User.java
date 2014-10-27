package com.iwebirth.sxfj.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

/**
 * 测试
 * 数据模型
 * **/
@Entity
@Table(name="user")
//@DynamicInsert(true) 若插入的对象有部分值没有set，那么只会插入已经set后的字段，没有set的字段根据default值进行补全。
//该annotation可以在表生成后进行改动
//与之相仿的还有@DynamicUpdate  参考http://www.cnblogs.com/quanyongan/p/3152290.html
@DynamicInsert(true) 
public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public User(){
		
	}
	@Id
	@GeneratedValue(generator="identity")
	@GenericGenerator(name="identity", strategy = "identity")   
	private Integer id;
	@Column(name="username",columnDefinition="varchar(10)  not null default 1")
	private String userName ;
	@Column(name="password",columnDefinition="varchar(10)  not null default 1")
	private String passWord ;
	@Column
	private Integer age;
	@Column(name="registerTime",columnDefinition="datetime not null default CURRENT_TIMESTAMP()")
	private Date registerTime ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	
	
}
