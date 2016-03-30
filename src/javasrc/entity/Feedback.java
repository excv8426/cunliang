package javasrc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javasrc.component.LongDateSerializer;


/**
 * The persistent class for the waihufankui database table.
 * 
 */
@Entity
@Table(name="waihufankui")
public class Feedback implements Serializable {

	private String waihujinglixingming;
	
	private String suoshubanzu;
	
	private String waihuhaoma;
	
	private String quxian;
	
	private String waihucelve;
	
	private String huifangqingkuang;
	
	private String kehuyixiang;
	
	private String chenggongbanliyewu;

	private String bubanliyuanyin;
	
	private String qitabubanliyuanyin;
	
	private String liushiyujingquyu;
	
	private String yujingcelve;
	
	private String shifouyouliwangqingxiang;

	private String liushiyuanyin;

	private String wanliujieguo;

	private String wanliushouduan;

	private String shibaiyuanyin;

	private String yujingyonghuchaxunjihuifangshichang;
	
	private String beizhu;
	
	private Date fankuiriqi;
	
	private String fankuirenyuangonghao;
	
	@Id
	private Integer waihufankuiid;
	
	private static final long serialVersionUID = 1L;

	@Transient
	private Integer maxresult;
	
	@Transient
	private Integer firstresult;
	
	@Transient
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fankuiriqilo;
	
	@Transient
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fankuiriqihi;

	public Feedback() {
	}

	public String getBeizhu() {
		return this.beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getBubanliyuanyin() {
		return this.bubanliyuanyin;

	}

	public void setBubanliyuanyin(String bubanliyuanyin) {
		this.bubanliyuanyin = bubanliyuanyin;
	}

	public String getChenggongbanliyewu() {
		return this.chenggongbanliyewu;
	}

	public void setChenggongbanliyewu(String chenggongbanliyewu) {
		this.chenggongbanliyewu = chenggongbanliyewu;
	}

	public String getHuifangqingkuang() {
		return this.huifangqingkuang;
	}

	public void setHuifangqingkuang(String huifangqingkuang) {
		this.huifangqingkuang = huifangqingkuang;
	}

	public String getKehuyixiang() {
		return this.kehuyixiang;
	}

	public void setKehuyixiang(String kehuyixiang) {
		this.kehuyixiang = kehuyixiang;
	}

	public String getLiushiyuanyin() {
		return this.liushiyuanyin;
	}

	public void setLiushiyuanyin(String liushiyuanyin) {
		this.liushiyuanyin = liushiyuanyin;
	}

	public String getLiushiyujingquyu() {
		return this.liushiyujingquyu;
	}

	public void setLiushiyujingquyu(String liushiyujingquyu) {
		this.liushiyujingquyu = liushiyujingquyu;
	}

	public String getQitabubanliyuanyin() {
		return this.qitabubanliyuanyin;
	}

	public void setQitabubanliyuanyin(String qitabubanliyuanyin) {
		this.qitabubanliyuanyin = qitabubanliyuanyin;
	}

	public String getQuxian() {
		return this.quxian;
	}

	public void setQuxian(String quxian) {
		this.quxian = quxian;
	}

	public String getShibaiyuanyin() {
		return this.shibaiyuanyin;
	}

	public void setShibaiyuanyin(String shibaiyuanyin) {
		this.shibaiyuanyin = shibaiyuanyin;
	}

	public String getShifouyouliwangqingxiang() {
		return this.shifouyouliwangqingxiang;
	}

	public void setShifouyouliwangqingxiang(String shifouyouliwangqingxiang) {
		this.shifouyouliwangqingxiang = shifouyouliwangqingxiang;
	}

	public String getWaihucelve() {
		return this.waihucelve;
	}

	public void setWaihucelve(String waihucelve) {
		this.waihucelve = waihucelve;
	}

	public String getWaihuhaoma() {
		return this.waihuhaoma;
	}

	public void setWaihuhaoma(String waihuhaoma) {
		this.waihuhaoma = waihuhaoma;
	}

	public String getWaihujinglixingming() {
		return this.waihujinglixingming;
	}

	public void setWaihujinglixingming(String waihujinglixingming) {
		this.waihujinglixingming = waihujinglixingming;
	}

	public String getSuoshubanzu() {
		return suoshubanzu;
	}

	public void setSuoshubanzu(String suoshubanzu) {
		this.suoshubanzu = suoshubanzu;
	}

	public String getWanliujieguo() {
		return this.wanliujieguo;
	}

	public void setWanliujieguo(String wanliujieguo) {
		this.wanliujieguo = wanliujieguo;
	}

	public String getWanliushouduan() {
		return this.wanliushouduan;
	}

	public void setWanliushouduan(String wanliushouduan) {
		this.wanliushouduan = wanliushouduan;
	}

	public String getYujingcelve() {
		return this.yujingcelve;
	}

	public void setYujingcelve(String yujingcelve) {
		this.yujingcelve = yujingcelve;
	}

	public String getYujingyonghuchaxunjihuifangshichang() {
		return this.yujingyonghuchaxunjihuifangshichang;
	}

	public void setYujingyonghuchaxunjihuifangshichang(String yujingyonghuchaxunjihuifangshichang) {
		this.yujingyonghuchaxunjihuifangshichang = yujingyonghuchaxunjihuifangshichang;
	}

	@JsonSerialize(using=LongDateSerializer.class)
	public Date getFankuiriqi() {
		return this.fankuiriqi;
	}

	public void setFankuiriqi(Date fankuiriqi) {
		this.fankuiriqi = fankuiriqi;
	}

	public String getFankuirenyuangonghao() {
		return fankuirenyuangonghao;
	}

	public void setFankuirenyuangonghao(String fankuirenyuangonghao) {
		this.fankuirenyuangonghao = fankuirenyuangonghao;
	}

	public Integer getWaihufankuiid() {
		return waihufankuiid;
	}

	public void setWaihufankuiid(Integer waihufankuiid) {
		this.waihufankuiid = waihufankuiid;
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

	public Date getFankuiriqilo() {
		return fankuiriqilo;
	}

	public void setFankuiriqilo(Date fankuiriqilo) {
		this.fankuiriqilo = fankuiriqilo;
	}

	public Date getFankuiriqihi() {
		return fankuiriqihi;
	}

	public void setFankuiriqihi(Date fankuiriqihi) {
		this.fankuiriqihi = fankuiriqihi;
	}

}