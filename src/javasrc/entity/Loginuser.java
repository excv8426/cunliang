package javasrc.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * The persistent class for the loginuser database table.
 * 
 */
@Entity
@Table(name="loginuser")
public class Loginuser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String loginname;

	private String authority;

	private String password;
	
	private Integer errorcount;
	
	private String personname;
	
	@Transient
	private Integer maxresult;
	
	@Transient
	private Integer firstresult;

	public Loginuser() {
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getLoginname() {
		return this.loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		if (this.password!=null) {
			return this.password;
		} else {
			return "";
		}
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getErrorcount() {
		return errorcount;
	}

	public void setErrorcount(Integer errorcount) {
		this.errorcount = errorcount;
	}

	public String getPersonname() {
		return personname;
	}

	public void setPersonname(String personname) {
		this.personname = personname;
	}

	public Integer getMaxresult() {
		return maxresult;
	}

	public void setMaxresult(Integer maxresult) {
		this.maxresult = maxresult;
	}

	public Integer getFirstresult() {
		return firstresult;
	}

	public void setFirstresult(Integer firstresult) {
		this.firstresult = firstresult;
	}

}