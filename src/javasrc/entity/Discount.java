package javasrc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javasrc.component.ShortDateSerializer;

@Entity
@Table(name="youhui")
public class Discount implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="youhuiid")
	private Integer youhuiid;
	
	@Column(name="电话号码")
	private String dianhuahaoma;
	
	@Column(name="包")
	private String bao;
	
	@Column(name="优惠")
	private String youhui;
	
	@Column(name="优惠失效时间")
	private Date youhuishixiaoshijian;
	
	@ManyToMany(mappedBy="discounts",targetEntity=Client.class,cascade={CascadeType.PERSIST})
	private Set<Client> clients=new HashSet<>();

	public Integer getYouhuiid() {
		return youhuiid;
	}

	public void setYouhuiid(Integer youhuiid) {
		this.youhuiid = youhuiid;
	}

	public String getDianhuahaoma() {
		return dianhuahaoma;
	}

	public void setDianhuahaoma(String dianhuahaoma) {
		this.dianhuahaoma = dianhuahaoma;
	}

	public String getBao() {
		return bao;
	}

	public void setBao(String bao) {
		this.bao = bao;
	}

	public String getYouhui() {
		return youhui;
	}

	public void setYouhui(String youhui) {
		this.youhui = youhui;
	}

	@JsonSerialize(using=ShortDateSerializer.class)
	public Date getYouhuishixiaoshijian() {
		return youhuishixiaoshijian;
	}

	public void setYouhuishixiaoshijian(Date youhuishixiaoshijian) {
		this.youhuishixiaoshijian = youhuishixiaoshijian;
	}

	@JsonIgnore
	public Set<Client> getClients() {
		return clients;
	}

	public void setClients(Set<Client> clients) {
		this.clients = clients;
	}

}
