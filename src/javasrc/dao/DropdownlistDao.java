package javasrc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javasrc.entity.Bubanliyuanyin;
import javasrc.entity.Yujingcelve;

@Repository
@Transactional(readOnly=true)
public class DropdownlistDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Yujingcelve> getYujingcelves(){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Yujingcelve.class);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Bubanliyuanyin> getBubanliyuanyins(){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Bubanliyuanyin.class);
		return criteria.list();
	}
	
	public String addYujingcelve(Yujingcelve yujingcelve){
		Session session=sessionFactory.getCurrentSession();
		return session.save(yujingcelve).toString();
	}
	
	public String addBubanliyuanyin(Bubanliyuanyin bubanliyuanyin){
		Session session=sessionFactory.getCurrentSession();
		return session.save(bubanliyuanyin).toString();
	}
	
	@Transactional(readOnly=false)
	public Integer deleteYujingcelve(Yujingcelve yujingcelve){
		String hql="delete Yujingcelve yujingcelve where yujingcelve.yujingcelveid=?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		query.setInteger(0, yujingcelve.getYujingcelveid());
		return query.executeUpdate();
	}
	
	@Transactional(readOnly=false)
	public Integer deleteBubanliyuanyin(Bubanliyuanyin bubanliyuanyin){
		String hql="delete Bubanliyuanyin bubanliyuanyin where bubanliyuanyin.bubanliyuanyinid=?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		query.setInteger(0, bubanliyuanyin.getBubanliyuanyinid());
		return query.executeUpdate();
	}
}
