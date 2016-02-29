package javasrc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javasrc.component.ShortDateSerializer;


/**
 * The persistent class for the paidandaoting database table.
 * 
 */
@Entity
@Table(name="paidandaoting")
public class Allocation  implements Serializable {
	
	private String paidanrenyuansuoshubanzu;
	
	private String paidanrenyuan;
	
	private String shixianqu;
	
	private String jutiyingyeting;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dianhuayuyueshijian;
	
	private String yonghuhaoma;
	
	private String yonghuxingming;
	
	private String xianshiyongzifei;
	
	private String huiyuandengji;
	
	private String tuijianchanpin;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date yudaotingbanlishijian;
	
	private String kehujingli;
	
	private String duanxinbianma;
	
	private String waihubeizhu;
	
	private String yonghushifoudaoting;
	
	private String kehushifoubanliyuyueyewu;
	
	private String yonghuchenggongbanliyewu;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date banlishijian;
	
	private String caozuoyuanxingming;
	
	private String yonghububanliyuanyin;
	
	private String duanxinbianmabiaowaikehu;
	
	private String yingyebeizhu;
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer paidanid;
	
	private String paidanrenyuangonghao;
	
	private String fankuirenyuangonghao;
	
	private Date paidanriqi;
	
	private Date paidanfankuiriqi;
	
	private String yifankui;
	
	@Transient
	private Integer maxresult;
	
	@Transient
	private Integer firstresult;
	
	@Transient
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dianhuayuyueshijianlo;
	
	@Transient
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date dianhuayuyueshijianhi;
	
	@Transient
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date yudaotingbanlishijianlo;
	
	@Transient
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date yudaotingbanlishijianhi;

	public Allocation() {
	}

	public Integer getPaidanid() {
		return this.paidanid;
	}

	public void setPaidanid(Integer paidanid) {
		this.paidanid = paidanid;
	}

	public String getPaidanrenyuangonghao() {
		if (paidanrenyuangonghao==null) {
			return "";
		} else {
			return paidanrenyuangonghao;
		}
	}

	public void setPaidanrenyuangonghao(String paidanrenyuangonghao) {
		this.paidanrenyuangonghao = paidanrenyuangonghao;
	}

	public String getFankuirenyuangonghao() {
		return fankuirenyuangonghao;
	}

	public void setFankuirenyuangonghao(String fankuirenyuangonghao) {
		this.fankuirenyuangonghao = fankuirenyuangonghao;
	}

	public Date getPaidanriqi() {
		return paidanriqi;
	}

	public void setPaidanriqi(Date paidanriqi) {
		this.paidanriqi = paidanriqi;
	}

	public Date getPaidanfankuiriqi() {
		return paidanfankuiriqi;
	}

	public void setPaidanfankuiriqi(Date paidanfankuiriqi) {
		this.paidanfankuiriqi = paidanfankuiriqi;
	}

	public String getYifankui() {
		if (yifankui==null) {
			return "";
		} else {
			return yifankui;
		}
	}

	public void setYifankui(String yifankui) {
		this.yifankui = yifankui;
	}

	@JsonSerialize(using=ShortDateSerializer.class)
	public Date getBanlishijian() {
		return this.banlishijian;
	}

	public void setBanlishijian(Date banlishijian) {
		this.banlishijian = banlishijian;
	}

	public String getCaozuoyuanxingming() {
		return this.caozuoyuanxingming;
	}

	public void setCaozuoyuanxingming(String caozuoyuanxingming) {
		this.caozuoyuanxingming = caozuoyuanxingming;
	}
	
	@JsonSerialize(using=ShortDateSerializer.class)
	public Date getDianhuayuyueshijian() {
		return this.dianhuayuyueshijian;
	}

	public void setDianhuayuyueshijian(Date dianhuayuyueshijian) {
		this.dianhuayuyueshijian = dianhuayuyueshijian;
	}

	public String getDuanxinbianma() {
		return this.duanxinbianma;
	}

	public void setDuanxinbianma(String duanxinbianma) {
		this.duanxinbianma = duanxinbianma;
	}

	public String getDuanxinbianmabiaowaikehu() {
		return this.duanxinbianmabiaowaikehu;
	}

	public void setDuanxinbianmabiaowaikehu(String duanxinbianmabiaowaikehu) {
		this.duanxinbianmabiaowaikehu = duanxinbianmabiaowaikehu;
	}

	public String getHuiyuandengji() {
		return this.huiyuandengji;
	}

	public void setHuiyuandengji(String huiyuandengji) {
		this.huiyuandengji = huiyuandengji;
	}

	public String getJutiyingyeting() {
		if (jutiyingyeting!=null) {
			return this.jutiyingyeting;
		} else {
			return "";
		}
	}

	public void setJutiyingyeting(String jutiyingyeting) {
		this.jutiyingyeting = jutiyingyeting;
	}

	public String getKehujingli() {
		return this.kehujingli;
	}

	public void setKehujingli(String kehujingli) {
		this.kehujingli = kehujingli;
	}

	public String getKehushifoubanliyuyueyewu() {
		return this.kehushifoubanliyuyueyewu;
	}

	public void setKehushifoubanliyuyueyewu(String kehushifoubanliyuyueyewu) {
		this.kehushifoubanliyuyueyewu = kehushifoubanliyuyueyewu;
	}

	public String getPaidanrenyuan() {
		return this.paidanrenyuan;
	}

	public void setPaidanrenyuan(String paidanrenyuan) {
		this.paidanrenyuan = paidanrenyuan;
	}

	public String getPaidanrenyuansuoshubanzu() {
		return this.paidanrenyuansuoshubanzu;
	}

	public void setPaidanrenyuansuoshubanzu(String paidanrenyuansuoshubanzu) {
		this.paidanrenyuansuoshubanzu = paidanrenyuansuoshubanzu;
	}

	public String getShixianqu() {
		if (shixianqu!=null) {
			return this.shixianqu;
		} else {
			return "";
		}
		
	}

	public void setShixianqu(String shixianqu) {
		this.shixianqu = shixianqu;
	}

	public String getTuijianchanpin() {
		return this.tuijianchanpin;
	}

	public void setTuijianchanpin(String tuijianchanpin) {
		this.tuijianchanpin = tuijianchanpin;
	}

	public String getWaihubeizhu() {
		return this.waihubeizhu;
	}

	public void setWaihubeizhu(String waihubeizhu) {
		this.waihubeizhu = waihubeizhu;
	}

	public String getXianshiyongzifei() {
		return this.xianshiyongzifei;
	}

	public void setXianshiyongzifei(String xianshiyongzifei) {
		this.xianshiyongzifei = xianshiyongzifei;
	}

	public String getYingyebeizhu() {
		return this.yingyebeizhu;
	}

	public void setYingyebeizhu(String yingyebeizhu) {
		this.yingyebeizhu = yingyebeizhu;
	}

	public String getYonghububanliyuanyin() {
		return this.yonghububanliyuanyin;
	}

	public void setYonghububanliyuanyin(String yonghububanliyuanyin) {
		this.yonghububanliyuanyin = yonghububanliyuanyin;
	}

	public String getYonghuhaoma() {
		if (yonghuhaoma!=null) {
			return this.yonghuhaoma;
		} else {
			return "";
		}
		
	}

	public void setYonghuhaoma(String yonghuhaoma) {
		this.yonghuhaoma = yonghuhaoma;
	}

	public String getYonghushifoudaoting() {
		return this.yonghushifoudaoting;
	}

	public void setYonghushifoudaoting(String yonghushifoudaoting) {
		this.yonghushifoudaoting = yonghushifoudaoting;
	}

	public String getYonghuchenggongbanliyewu() {
		return this.yonghuchenggongbanliyewu;
	}

	public void setYonghuchenggongbanliyewu(String yonghuchenggongbanliyewu) {
		this.yonghuchenggongbanliyewu = yonghuchenggongbanliyewu;
	}

	public String getYonghuxingming() {
		return this.yonghuxingming;
	}

	public void setYonghuxingming(String yonghuxingming) {
		this.yonghuxingming = yonghuxingming;
	}

	@JsonSerialize(using=ShortDateSerializer.class)
	public Date getYudaotingbanlishijian() {
		return this.yudaotingbanlishijian;
	}

	public void setYudaotingbanlishijian(Date yudaotingbanlishijian) {
		this.yudaotingbanlishijian = yudaotingbanlishijian;
	}

	public Integer getMaxresult() {		
		return maxresult;
	}

	public void setMaxresult(Integer maxresult) {
		this.maxresult = maxresult;
	}

	public Integer getFirstresult() {
		if (firstresult!=null) {
			return firstresult;
		} else {
			return 0;
		}
	}

	public void setFirstresult(Integer firstresult) {
		this.firstresult = firstresult;
	}

	public Date getDianhuayuyueshijianlo() {
		return dianhuayuyueshijianlo;
	}

	public void setDianhuayuyueshijianlo(Date dianhuayuyueshijianlo) {
		this.dianhuayuyueshijianlo = dianhuayuyueshijianlo;
	}

	public Date getDianhuayuyueshijianhi() {
		return dianhuayuyueshijianhi;
	}

	public void setDianhuayuyueshijianhi(Date dianhuayuyueshijianhi) {
		this.dianhuayuyueshijianhi = dianhuayuyueshijianhi;
	}

	public Date getYudaotingbanlishijianlo() {
		return yudaotingbanlishijianlo;
	}

	public void setYudaotingbanlishijianlo(Date yudaotingbanlishijianlo) {
		this.yudaotingbanlishijianlo = yudaotingbanlishijianlo;
	}

	public Date getYudaotingbanlishijianhi() {
		return yudaotingbanlishijianhi;
	}

	public void setYudaotingbanlishijianhi(Date yudaotingbanlishijianhi) {
		this.yudaotingbanlishijianhi = yudaotingbanlishijianhi;
	}

}