package javasrc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javasrc.component.ShortDateSerializer;
import javasrc.component.ShortDecimalStringSerializer;


/**
 * The persistent class for the cunliangweihu database table.
 * 
 */
@Entity
@Table(name="kehuxinxi")
public class Client implements Serializable {
	
	@Column(name="电话号码")
	private String dianhuahaoma;
	
	@Column(name="数据来源")
	private String shujulaiyuan;
	
	@Column(name="所属班组")
	private String suoshubanzu;
	
	@Column(name="区县")
	private String quxian;
	
	@Column(name="是否融合")
	private String shifouronghe;
	
	@Column(name="渠道类型")
	private String qudaoleixing;
	
	@Column(name="渠道")
	private String qudao;
	
	@Column(name="全网业务类型")
	private String quanwangyewuleixing;
	
	@Column(name="客户姓名")
	private String kehuxingming;
	
	@Column(name="客户类型")
	private String kehuleixing;
	
	@Column(name="产品包含CBSS")
	private String chanpinbaohancbss;
	
	@Column(name="月租费3G")
	private String yuezufei3g;
	
	@Column(name="套内含语音3G")
	private String taoneihanyuyin3g;
	
	@Column(name="套内含流量3G")
	private String taoneihanliuliang3g;
	
	@Column(name="套内含语音4G")
	private String taoneihanyuyin4g;
	
	@Column(name="套内含流量4G")
	private String taoneihanliuliang4g;
	
	@Column(name="开户日期")
	private Date kaihuriqi;
	
	@Column(name="用户状态")
	private String yonghuzhuangtai;
	
	@Column(name="终端厂商ID")
	private String zhongduanchangshangid;
	
	@Column(name="终端机型")
	private String zhongduanjixing;
	
	@Column(name="网络制式")
	private String wangluozhishi;
	
	@Column(name="终端价格")
	private Double zhongduanjiage;
	
	@Column(name="操作系统")
	private String caozuoxitong;
	
	@Column(name="主屏尺寸")
	private String zhupingchicun;
	
	@Column(name="CPU核数")
	private String cpuheshu;
	
	@Column(name="RAM容量")
	private String ramrongliang;
	
	@Column(name="是否双卡")
	private String shifoushuangka;
	
	@Column(name="终端上市时间")
	private String zhongduanshangshishijian;
	
	@Column(name="上次换机月份")
	private String shangcihuanjiyuefen;
	
	@Column(name="融合类型")
	private String rongheleixing;

	@Column(name="融合类型1")
	private String rongheleixing1;
	
	@Column(name="合约类型")
	private String heyueleixing;

	@Column(name="合约生效时间")
	private String heyueshengxiaoshijian;

	@Column(name="合约失效时间")
	private String heyueshixiaoshijian;
	
	@Column(name="在网时长")
	private Double zaiwangshichang;

	@Column(name="主叫计费时长")
	private String zhujiaojifeishichang;
	
	@Column(name="流量")
	private String liuliang;

	@Column(name="固网本地通话费G网市话费分摊前")
	private String guwangbenditonghuafei;
	
	@Column(name="长途费分摊前")
	private String changtufeifentanqian;

	@Column(name="漫游费分摊前")
	private String manyoufeifentanqian;

	@Column(name="短信费分摊前")
	private String duanxinfeifentanqian;
	
	@Column(name="GPRS费分摊前")
	private String gprsfeifentanqian;
	
	@Column(name="分摊前收入")
	private Double fentanqianshouru;

	@Column(name="费用合计GSM固网")
	private String feiyonghejigsmguwang;
	
	@Column(name="语音饱和度3G")
	private String yuyinbaohedu3g;
	
	@Column(name="流量饱和度3G")
	private String liuliangbaohedu3g;
	
	@Column(name="语音饱和度4G")
	private String yuyinbaohedu4g;
	
	@Column(name="流量饱和度4G")
	private String liuliangbaohedu4g;
	
	@Column(name="客户经理")
	private String kehujingli;
	
	@Column(name="推荐政策")
	private String tuijianzhengce;
	
	@Column(name="非实名制标识")
	private String feishimingzhibiaozhi;
	
	@Column(name="近两个月外呼过")
	private String jinlianggeyuewaihuguo;
	
	@Column(name="营业厅名称")
	private String yingyetingmingcheng;
	
	@Column(name="营业厅地址")
	private String yingyetingdizhi;
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="kehuxinxiid")
	private Integer kehuxinxiid;
	
	@Column(name="是否员工")
	private String shifouyuangong;
	
	@Column(name="预判结果")
	private String yupanjieguo;

	@Column(name="品牌包含CBSS")
	private String pinpaibaohancbss;

	@Column(name="是否集团用户")
	private String shifoujituanyonghu;
			
	@Column(name="是否专属终端")
	private String shifouzhuanshuzhongduan;
		
	@Column(name="外呼策略")
	private String waihucelve;
	
	@Column(name="已反馈")
	private String yifankui;
	
	@ManyToMany(targetEntity=Discount.class)
	@JoinTable(name="yonghu_youhui",joinColumns={@JoinColumn(name="dianhuahaoma",referencedColumnName="电话号码")},
	inverseJoinColumns={@JoinColumn(name="youhuiid",referencedColumnName="youhuiid")})
	private Set<Discount> discounts = new HashSet<Discount>();
	
	@Transient
	private Integer maxresult;
	
	@Transient
	private Integer firstresult;
	
	@Transient
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date kaihuriqilo;
	
	@Transient
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date kaihuriqihi;
	
	@Transient
	private Double zaiwangshichanglo;
	
	@Transient
	private Double zaiwangshichanghi;
	
	@Transient
	private Double fentanqianshourulo;
	
	@Transient
	private Double fentanqianshouruhi;
	
	@Transient
	private String bao;

	public Client() {
	}

	public Integer getKehuxinxiid() {
		return kehuxinxiid;
	}

	public void setKehuxinxiid(Integer kehuxinxiid) {
		this.kehuxinxiid = kehuxinxiid;
	}

	public String getDianhuahaoma() {
		if (dianhuahaoma!=null) {
			return this.dianhuahaoma;
		} else {
			return "";
		}
	}

	public void setDianhuahaoma(String dianhuahaoma) {
		this.dianhuahaoma = dianhuahaoma;
	}

	public String getCaozuoxitong() {
		return this.caozuoxitong;
	}

	public void setCaozuoxitong(String caozuoxitong) {
		this.caozuoxitong = caozuoxitong;
	}

	@JsonSerialize(using=ShortDecimalStringSerializer.class)
	public String getChangtufeifentanqian() {
		return this.changtufeifentanqian;
	}

	public void setChangtufeifentanqian(String changtufeifentanqian) {
		this.changtufeifentanqian = changtufeifentanqian;
	}

	public String getChanpinbaohancbss() {
		if (chanpinbaohancbss!=null) {
			return this.chanpinbaohancbss;
		} else {
			return "";
		}
		
	}

	public void setChanpinbaohancbss(String chanpinbaohancbss) {
		this.chanpinbaohancbss = chanpinbaohancbss;
	}
	public String getCpuheshu() {
		return this.cpuheshu;
	}

	public void setCpuheshu(String cpuheshu) {
		this.cpuheshu = cpuheshu;
	}

	@JsonSerialize(using=ShortDecimalStringSerializer.class)
	public String getDuanxinfeifentanqian() {
		return this.duanxinfeifentanqian;
	}

	public void setDuanxinfeifentanqian(String duanxinfeifentanqian) {
		this.duanxinfeifentanqian = duanxinfeifentanqian;
	}

	@JsonSerialize(using=ShortDecimalStringSerializer.class)
	public String getFeiyonghejigsmguwang() {
		return this.feiyonghejigsmguwang;
	}

	public void setFeiyonghejigsmguwang(String feiyonghejigsmguwang) {
		this.feiyonghejigsmguwang = feiyonghejigsmguwang;
	}

	public Double getFentanqianshouru() {
		return this.fentanqianshouru;
	}

	public void setFentanqianshouru(Double fentanqianshouru) {
		this.fentanqianshouru = fentanqianshouru;
	}

	@JsonSerialize(using=ShortDecimalStringSerializer.class)
	public String getGprsfeifentanqian() {
		return this.gprsfeifentanqian;
	}

	public void setGprsfeifentanqian(String gprsfeifentanqian) {
		this.gprsfeifentanqian = gprsfeifentanqian;
	}

	@JsonSerialize(using=ShortDecimalStringSerializer.class)
	public String getGuwangbenditonghuafei() {
		return guwangbenditonghuafei;
	}

	public void setGuwangbenditonghuafei(String guwangbenditonghuafei) {
		this.guwangbenditonghuafei = guwangbenditonghuafei;
	}

	public String getHeyueleixing() {
		if (heyueleixing!=null) {
			return this.heyueleixing;
		} else {
			return "";
		}
	}

	public void setHeyueleixing(String heyueleixing) {
		this.heyueleixing = heyueleixing;
	}

	public String getHeyueshengxiaoshijian() {
		return this.heyueshengxiaoshijian;
	}

	public void setHeyueshengxiaoshijian(String heyueshengxiaoshijian) {
		this.heyueshengxiaoshijian = heyueshengxiaoshijian;
	}

	public String getHeyueshixiaoshijian() {
		return this.heyueshixiaoshijian;
	}

	public void setHeyueshixiaoshijian(String heyueshixiaoshijian) {
		this.heyueshixiaoshijian = heyueshixiaoshijian;
	}

	@JsonSerialize(using=ShortDateSerializer.class)
	public Date getKaihuriqi() {
		return this.kaihuriqi;
	}

	public void setKaihuriqi(Date kaihuriqi) {
		this.kaihuriqi = kaihuriqi;
	}

	public String getKehuleixing() {
		return this.kehuleixing;
	}

	public void setKehuleixing(String kehuleixing) {
		this.kehuleixing = kehuleixing;
	}

	public String getKehuxingming() {
		return this.kehuxingming;
	}

	public void setKehuxingming(String kehuxingming) {
		this.kehuxingming = kehuxingming;
	}

	@JsonSerialize(using=ShortDecimalStringSerializer.class)
	public String getLiuliang() {
		return this.liuliang;
	}

	public void setLiuliang(String liuliang) {
		this.liuliang = liuliang;
	}

	@JsonSerialize(using=ShortDecimalStringSerializer.class)
	public String getManyoufeifentanqian() {
		return this.manyoufeifentanqian;
	}

	public void setManyoufeifentanqian(String manyoufeifentanqian) {
		this.manyoufeifentanqian = manyoufeifentanqian;
	}

	public String getPinpaibaohancbss() {
		return this.pinpaibaohancbss;
	}

	public void setPinpaibaohancbss(String pinpaibaohancbss) {
		this.pinpaibaohancbss = pinpaibaohancbss;
	}

	public String getQuanwangyewuleixing() {
		if (quanwangyewuleixing!=null) {
			return this.quanwangyewuleixing;
		} else {
			return "";
		}
	}

	public void setQuanwangyewuleixing(String quanwangyewuleixing) {
		this.quanwangyewuleixing = quanwangyewuleixing;
	}

	public String getQudao() {
		return this.qudao;
	}

	public void setQudao(String qudao) {
		this.qudao = qudao;
	}

	public String getQudaoleixing() {
		return this.qudaoleixing;
	}

	public void setQudaoleixing(String qudaoleixing) {
		this.qudaoleixing = qudaoleixing;
	}

	public String getQuxian() {
		if (quxian!=null) {
			return this.quxian;
		} else {
			return "";
		}
	}

	public void setQuxian(String quxian) {
		this.quxian = quxian;
	}

	public String getRongheleixing() {
		if (rongheleixing!=null) {
			return this.rongheleixing;
		} else {
			return "";
		}
		
	}

	public void setRongheleixing(String rongheleixing) {
		this.rongheleixing = rongheleixing;
	}

	public String getRongheleixing1() {
		return this.rongheleixing1;
	}

	public void setRongheleixing1(String rongheleixing1) {
		this.rongheleixing1 = rongheleixing1;
	}

	public String getShangcihuanjiyuefen() {
		return this.shangcihuanjiyuefen;
	}

	public void setShangcihuanjiyuefen(String shangcihuanjiyuefen) {
		this.shangcihuanjiyuefen = shangcihuanjiyuefen;
	}

	public String getZhongduanshangshishijian() {
		return this.zhongduanshangshishijian;
	}

	public void setZhongduanshangshishijian(String zhongduanshangshishijian) {
		this.zhongduanshangshishijian = zhongduanshangshishijian;
	}

	public String getShifoujituanyonghu() {
		return this.shifoujituanyonghu;
	}

	public void setShifoujituanyonghu(String shifoujituanyonghu) {
		this.shifoujituanyonghu = shifoujituanyonghu;
	}

	public String getShifoushuangka() {
		return this.shifoushuangka;
	}

	public void setShifoushuangka(String shifoushuangka) {
		this.shifoushuangka = shifoushuangka;
	}

	public String getTaoneihanyuyin3g() {
		return taoneihanyuyin3g;
	}

	public void setTaoneihanyuyin3g(String taoneihanyuyin3g) {
		this.taoneihanyuyin3g = taoneihanyuyin3g;
	}

	public String getTaoneihanliuliang3g() {
		return taoneihanliuliang3g;
	}

	public void setTaoneihanliuliang3g(String taoneihanliuliang3g) {
		this.taoneihanliuliang3g = taoneihanliuliang3g;
	}

	public String getTaoneihanyuyin4g() {
		return taoneihanyuyin4g;
	}

	public void setTaoneihanyuyin4g(String taoneihanyuyin4g) {
		this.taoneihanyuyin4g = taoneihanyuyin4g;
	}

	public String getTaoneihanliuliang4g() {
		return taoneihanliuliang4g;
	}

	public void setTaoneihanliuliang4g(String taoneihanliuliang4g) {
		this.taoneihanliuliang4g = taoneihanliuliang4g;
	}

	public String getYuyinbaohedu3g() {
		return yuyinbaohedu3g;
	}

	public void setYuyinbaohedu3g(String yuyinbaohedu3g) {
		this.yuyinbaohedu3g = yuyinbaohedu3g;
	}

	public String getLiuliangbaohedu3g() {
		return liuliangbaohedu3g;
	}

	public void setLiuliangbaohedu3g(String liuliangbaohedu3g) {
		this.liuliangbaohedu3g = liuliangbaohedu3g;
	}

	public String getYuyinbaohedu4g() {
		return yuyinbaohedu4g;
	}

	public void setYuyinbaohedu4g(String yuyinbaohedu4g) {
		this.yuyinbaohedu4g = yuyinbaohedu4g;
	}

	public String getLiuliangbaohedu4g() {
		return liuliangbaohedu4g;
	}

	public void setLiuliangbaohedu4g(String liuliangbaohedu4g) {
		this.liuliangbaohedu4g = liuliangbaohedu4g;
	}

	public String getWangluozhishi() {
		return this.wangluozhishi;
	}

	public void setWangluozhishi(String wangluozhishi) {
		this.wangluozhishi = wangluozhishi;
	}

	public String getYonghuzhuangtai() {
		return this.yonghuzhuangtai;
	}

	public void setYonghuzhuangtai(String yonghuzhuangtai) {
		this.yonghuzhuangtai = yonghuzhuangtai;
	}

	public String getYuezufei3g() {
		return this.yuezufei3g;
	}

	public void setYuezufei3g(String yuezufei3g) {
		this.yuezufei3g = yuezufei3g;
	}

	public Double getZaiwangshichang() {
		return this.zaiwangshichang;
	}

	public void setZaiwangshichang(Double zaiwangshichang) {
		this.zaiwangshichang = zaiwangshichang;
	}

	public String getZhongduanchangshangid() {
		return this.zhongduanchangshangid;
	}

	public void setZhongduanchangshangid(String zhongduanchangshangid) {
		this.zhongduanchangshangid = zhongduanchangshangid;
	}

	public Double getZhongduanjiage() {
		return this.zhongduanjiage;
	}

	public void setZhongduanjiage(Double zhongduanjiage) {
		this.zhongduanjiage = zhongduanjiage;
	}

	public String getZhongduanjixing() {
		return this.zhongduanjixing;
	}

	public void setZhongduanjixing(String zhongduanjixing) {
		this.zhongduanjixing = zhongduanjixing;
	}

	public String getZhujiaojifeishichang() {
		return this.zhujiaojifeishichang;
	}

	public void setZhujiaojifeishichang(String zhujiaojifeishichang) {
		this.zhujiaojifeishichang = zhujiaojifeishichang;
	}

	public String getZhupingchicun() {
		return this.zhupingchicun;
	}

	public void setZhupingchicun(String zhupingchicun) {
		this.zhupingchicun = zhupingchicun;
	}

	public String getRamrongliang() {
		return ramrongliang;
	}

	public void setRamrongliang(String ramrongliang) {
		this.ramrongliang = ramrongliang;
	}

	public String getShifouzhuanshuzhongduan() {
		return shifouzhuanshuzhongduan;
	}

	public void setShifouzhuanshuzhongduan(String shifouzhuanshuzhongduan) {
		this.shifouzhuanshuzhongduan = shifouzhuanshuzhongduan;
	}

	public String getJinlianggeyuewaihuguo() {
		return jinlianggeyuewaihuguo;
	}

	public void setJinlianggeyuewaihuguo(String jinlianggeyuewaihuguo) {
		this.jinlianggeyuewaihuguo = jinlianggeyuewaihuguo;
	}

	public String getShifouronghe() {
		return shifouronghe;
	}

	public void setShifouronghe(String shifouronghe) {
		this.shifouronghe = shifouronghe;
	}

	public String getYingyetingmingcheng() {
		return yingyetingmingcheng;
	}

	public void setYingyetingmingcheng(String yingyetingmingcheng) {
		this.yingyetingmingcheng = yingyetingmingcheng;
	}

	public String getYingyetingdizhi() {
		return yingyetingdizhi;
	}

	public void setYingyetingdizhi(String yingyetingdizhi) {
		this.yingyetingdizhi = yingyetingdizhi;
	}

	public String getWaihucelve() {
		if (waihucelve==null) {
			return "";
		} else {
			return waihucelve;
		}
	}

	public void setWaihucelve(String waihucelve) {
		this.waihucelve = waihucelve;
	}

	public String getShifouyuangong() {
		return shifouyuangong;
	}

	public void setShifouyuangong(String shifouyuangong) {
		this.shifouyuangong = shifouyuangong;
	}

	public String getYupanjieguo() {
		return yupanjieguo;
	}

	public void setYupanjieguo(String yupanjieguo) {
		this.yupanjieguo = yupanjieguo;
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

	@JsonIgnore
	public Set<Discount> getDiscounts() {
		return discounts;
	}

	public void setDiscounts(Set<Discount> discounts) {
		this.discounts = discounts;
	}

	public Integer getMaxresult() {
		return maxresult;
	}

	public void setMaxresult(Integer maxresult) {
		this.maxresult = maxresult;
	}

	public String getKehujingli() {
		if (kehujingli!=null) {
			return kehujingli;
		} else {
			return "";
		}
	}

	public void setKehujingli(String kehujingli) {
		this.kehujingli = kehujingli;
	}

	public String getTuijianzhengce() {
		if (tuijianzhengce!=null) {
			return tuijianzhengce;
		} else {
			return "";
		}
	}

	public void setTuijianzhengce(String tuijianzhengce) {
		this.tuijianzhengce = tuijianzhengce;
	}

	public String getShujulaiyuan() {
		return shujulaiyuan;
	}

	public void setShujulaiyuan(String shujulaiyuan) {
		this.shujulaiyuan = shujulaiyuan;
	}

	public String getFeishimingzhibiaozhi() {
		return feishimingzhibiaozhi;
	}

	public void setFeishimingzhibiaozhi(String feishimingzhibiaozhi) {
		this.feishimingzhibiaozhi = feishimingzhibiaozhi;
	}

	public Integer getFirstresult() {
		if ((firstresult!=null)&&(!firstresult.equals(""))) {
			return firstresult;
		} else {
			return 0;
		}
		
	}

	public void setFirstresult(Integer firstresult) {
		this.firstresult = firstresult;
	}

	public Date getKaihuriqilo() {
		return kaihuriqilo;
	}

	public void setKaihuriqilo(Date kaihuriqilo) {
		this.kaihuriqilo = kaihuriqilo;
	}

	public Date getKaihuriqihi() {
		return kaihuriqihi;
	}

	public void setKaihuriqihi(Date kaihuriqihi) {
		this.kaihuriqihi = kaihuriqihi;
	}

	public Double getZaiwangshichanglo() {
		return zaiwangshichanglo;
	}

	public void setZaiwangshichanglo(Double zaiwangshichanglo) {
		this.zaiwangshichanglo = zaiwangshichanglo;
	}

	public Double getZaiwangshichanghi() {
		return zaiwangshichanghi;
	}

	public void setZaiwangshichanghi(Double zaiwangshichanghi) {
		this.zaiwangshichanghi = zaiwangshichanghi;
	}

	public Double getFentanqianshourulo() {
		return fentanqianshourulo;
	}

	public void setFentanqianshourulo(Double fentanqianshourulo) {
		this.fentanqianshourulo = fentanqianshourulo;
	}

	public Double getFentanqianshouruhi() {
		return fentanqianshouruhi;
	}

	public void setFentanqianshouruhi(Double fentanqianshouruhi) {
		this.fentanqianshouruhi = fentanqianshouruhi;
	}

	public String getBao() {
		if (bao==null) {
			return "";
		} else {
			return bao;
		}
	}

	public void setBao(String bao) {
		this.bao = bao;
	}

	public String getSuoshubanzu() {
		if (suoshubanzu==null) {
			return "";
		} else {
			return suoshubanzu;
		}
	}

	public void setSuoshubanzu(String suoshubanzu) {
		this.suoshubanzu = suoshubanzu;
	}
	
}