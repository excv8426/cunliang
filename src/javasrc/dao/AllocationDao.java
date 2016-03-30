package javasrc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javasrc.entity.Allocation;

@Repository
@Transactional(readOnly=true)
public class AllocationDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional(readOnly=false)
	public String saveAllocation(Allocation allocation){
		Session session=sessionFactory.getCurrentSession();
		return session.save(allocation).toString();
	}
	
	@Transactional(readOnly=false)
	public String updateAllocation(Allocation allocation){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Allocation.class);
		criteria.add(Restrictions.idEq(allocation.getPaidanid()));
		Allocation allocation1=(Allocation) criteria.uniqueResult();
		if (allocation1.getYifankui().equals("是")) {
			return "feedbacked";
		} else {
			BeanUtils.copyProperties(allocation, allocation1);
			return allocation.getPaidanid().toString();
		}
	}
	
	@Transactional(readOnly=false,isolation=Isolation.SERIALIZABLE)
	public String feedbackAllocation(Allocation allocation){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Allocation.class);
		criteria.add(Restrictions.idEq(allocation.getPaidanid()));
		Allocation allocation2=(Allocation) criteria.uniqueResult();
		if ((allocation2.getYifankui().equals("是"))&&(!allocation.getFankuirenyuangonghao().equals(allocation2.getFankuirenyuangonghao()))) {
			return "can not modify others feedbackAllocation";
		} else {
			allocation2.setYonghushifoudaoting(allocation.getYonghushifoudaoting());
			allocation2.setKehushifoubanliyuyueyewu(allocation.getKehushifoubanliyuyueyewu());
			allocation2.setYonghuchenggongbanliyewu(allocation.getYonghuchenggongbanliyewu());
			allocation2.setBanlishijian(allocation.getBanlishijian());
			allocation2.setCaozuoyuanxingming(allocation.getCaozuoyuanxingming());
			allocation2.setYonghububanliyuanyin(allocation.getYonghububanliyuanyin());
			allocation2.setDuanxinbianmabiaowaikehu(allocation.getDuanxinbianmabiaowaikehu());
			allocation2.setYingyebeizhu(allocation.getYingyebeizhu());
			allocation2.setFankuirenyuangonghao(allocation.getFankuirenyuangonghao());
			allocation2.setPaidanfankuiriqi(allocation.getPaidanfankuiriqi());
			allocation2.setYifankui(allocation.getYifankui());
			return allocation.getPaidanid().toString();
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Allocation> getAllocations(Allocation allocation){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Allocation.class);
		if ((allocation.getMaxresult()!=null)&&(allocation.getFirstresult()!=null)) {
			criteria.setMaxResults(allocation.getMaxresult());
			criteria.setFirstResult(allocation.getFirstresult());
		}
		criteria.addOrder(Order.desc("dianhuayuyueshijian"));
		this.addRestrictions(criteria, allocation);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Allocation> extractAllocations(Allocation allocation){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Allocation.class);
		criteria.addOrder(Order.desc("dianhuayuyueshijian"));
		this.addRestrictions(criteria, allocation);
		return criteria.list();
	}
	
	public Integer getAllocationscount(Allocation allocation){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Allocation.class);
		criteria.setProjection(Projections.rowCount());
		this.addRestrictions(criteria, allocation);
		return Integer.parseInt(criteria.uniqueResult().toString());
	}
	
	private void addRestrictions(Criteria criteria,Allocation allocation){
		if (allocation.getPaidanid()!=null) {
			criteria.add(Restrictions.idEq(allocation.getPaidanid()));
			return;
		}
		if (allocation.getPaidanrenyuangonghao()!=null) {
			criteria.add(Restrictions.eq("paidanrenyuangonghao", allocation.getPaidanrenyuangonghao()));
		}
		if (allocation.getYonghuhaoma()!=null) {
			criteria.add(Restrictions.eq("yonghuhaoma", allocation.getYonghuhaoma()));
		}
		if (allocation.getShixianqu()!=null) {
			criteria.add(Restrictions.eq("shixianqu", allocation.getShixianqu()));
		}
		if (allocation.getJutiyingyeting()!=null) {
			criteria.add(Restrictions.eq("jutiyingyeting", allocation.getJutiyingyeting()));
		}
		if ((allocation.getDianhuayuyueshijianhi()!=null)&&(allocation.getDianhuayuyueshijianlo()!=null)) {
			criteria.add(Restrictions.between("dianhuayuyueshijian", allocation.getDianhuayuyueshijianlo(), allocation.getDianhuayuyueshijianhi()));
		}
		if ((allocation.getYudaotingbanlishijianhi()!=null)&&(allocation.getYudaotingbanlishijianlo()!=null)) {
			criteria.add(Restrictions.between("yudaotingbanlishijian", allocation.getYudaotingbanlishijianlo(), allocation.getYudaotingbanlishijianhi()));
		}
		if (allocation.getYifankui()!=null) {
			criteria.add(Restrictions.eq("yifankui", allocation.getYifankui()));
		}
	}
}
