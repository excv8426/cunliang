package javasrc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="yujingcelve")
public class Yujingcelve implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="预警策略")
	private String yujingcelve;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="预警策略序号")
	private Integer yujingcelveid;
	
	public String getYujingcelve() {
		return yujingcelve;
	}
	
	public void setYujingcelve(String yujingcelve) {
		this.yujingcelve = yujingcelve;
	}
	
	public Integer getYujingcelveid() {
		return yujingcelveid;
	}
	
	public void setYujingcelveid(Integer yujingcelveid) {
		this.yujingcelveid = yujingcelveid;
	}
	
}
