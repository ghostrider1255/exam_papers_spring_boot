package com.sreepapers.app.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ROLE")
public class Role {

	@Id
	@Column(name="roleId")
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int roleId;
	private String roleCode;
	private String roleName;
	private String roleDesc;
	private boolean roleStatus;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public boolean getRoleStatus() {
		return roleStatus;
	}
	public void setRoleStatus(boolean roleStatus) {
		this.roleStatus = roleStatus;
	}
}
