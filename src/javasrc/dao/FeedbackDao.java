package javasrc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javasrc.entity.Feedback;

@Repository
@Transactional(readOnly=true)
public class FeedbackDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional(readOnly=false)
	public String saveFeedback(Feedback feedback){
		Session session=sessionFactory.getCurrentSession();
		return session.save(feedback).toString();
	}
	
	@Transactional(readOnly=false)
	public String updateFeedback(Feedback feedback){
		Session session=sessionFactory.getCurrentSession();
		session.update(feedback);
		return feedback.getWaihuhaoma();
	}
	
	public Feedback getFeedback(Feedback feedback){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Feedback.class);
		criteria.add(Restrictions.idEq(feedback.getWaihufankuiid()));
		Feedback feedback2=(Feedback) criteria.uniqueResult();
		session.clear();
		return feedback2;
	}
	
	public List<Feedback> getFeedbacks(Feedback feedback){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Feedback.class);
		if ((feedback.getMaxresult()!=null)&&(feedback.getFirstresult()!=null)) {
			criteria.setMaxResults(feedback.getMaxresult());
			criteria.setFirstResult(feedback.getFirstresult());
		}
		criteria.addOrder(Order.desc("fankuiriqi"));
		this.addRestrictions(feedback, criteria);
		@SuppressWarnings("unchecked")
		List<Feedback> waihufankuis=criteria.list();
		return waihufankuis;
	}
	
	public List<Feedback> extractFeedbacks(Feedback feedback){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Feedback.class);
		this.addRestrictions(feedback, criteria);
		criteria.addOrder(Order.desc("fankuiriqi"));
		@SuppressWarnings("unchecked")
		List<Feedback> waihufankuis=criteria.list();
		return waihufankuis;
	}
	
	/**
	 * 获取查询结果的总数。
	 * @param feedback 查询条件。
	 * @return 查询结果总数*/
	public Integer getFeedbackscount(Feedback feedback){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Feedback.class);
		criteria.setProjection(Projections.rowCount());
		this.addRestrictions(feedback, criteria);
		return Integer.parseInt(criteria.uniqueResult().toString());
	}
	
	/**
	 * 设置查询条件。*/
	private void addRestrictions(Feedback feedback,Criteria criteria){
		if (feedback.getWaihufankuiid()!=null) {
			criteria.add(Restrictions.idEq(feedback.getWaihufankuiid()));
			return;
		}
		if (!feedback.getWaihuhaoma().equals("")) {
			criteria.add(Restrictions.eq("waihuhaoma",feedback.getWaihuhaoma()));
		}
		if (!feedback.getWaihujinglixingming().equals("")) {
			criteria.add(Restrictions.or(Restrictions.eq("waihujinglixingming", feedback.getWaihujinglixingming()), Restrictions.isNull("waihujinglixingming")));
		}
		if (!feedback.getFankuirenyuangonghao().equals("")) {
			criteria.add(Restrictions.eq("fankuirenyuangonghao", feedback.getFankuirenyuangonghao()));
		}
		if ((feedback.getFankuiriqihi()!=null)&&(feedback.getFankuiriqilo()!=null)) {
			criteria.add(Restrictions.between("fankuiriqi", feedback.getFankuiriqilo(), feedback.getFankuiriqihi()));
		}
		if (!feedback.getWaihucelve().equals("")) {
			criteria.add(Restrictions.like("waihucelve", "%"+feedback.getWaihucelve()+"%"));
		}
		if (!feedback.getChenggongbanliyewu().equals("")) {
			criteria.add(Restrictions.eq("chenggongbanliyewu", feedback.getChenggongbanliyewu()));
		}
		if (!feedback.getBubanliyuanyin().equals("")) {
			criteria.add(Restrictions.eq("bubanliyuanyin", feedback.getBubanliyuanyin()));
		}
		if (!feedback.getYujingcelve().equals("")) {
			criteria.add(Restrictions.eq("yujingcelve", feedback.getYujingcelve()));
		}
		if (!feedback.getHuifangqingkuang().equals("")) {
			criteria.add(Restrictions.eq("huifangqingkuang", feedback.getHuifangqingkuang()));
		}
		if (!feedback.getKehuyixiang().equals("")) {
			criteria.add(Restrictions.eq("kehuyixiang", feedback.getKehuyixiang()));
		}
	}
}
