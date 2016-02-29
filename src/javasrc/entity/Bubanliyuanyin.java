package javasrc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bubanliyuanyin")
public class Bubanliyuanyin implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="不办理原因")
	private String bubanliyuanyin;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="不办理原因序号")
	private Integer bubanliyuanyinid;
	
	public String getBubanliyuanyin() {
		return bubanliyuanyin;
	}
	
	public void setBubanliyuanyin(String bubanliyuanyin) {
		this.bubanliyuanyin = bubanliyuanyin;
	}
	
	public Integer getBubanliyuanyinid() {
		return bubanliyuanyinid;
	}
	
	public void setBubanliyuanyinid(Integer bubanliyuanyinid) {
		this.bubanliyuanyinid = bubanliyuanyinid;
	}
	
}
