package javasrc.dao;

import java.util.Date;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javasrc.entity.Mylog;

@Repository
@Transactional(readOnly=true)
public class MylogDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Transactional(readOnly=false)
	public String addMylog(Mylog mylog){
		Session session=sessionFactory.getCurrentSession();
		mylog.setDate(new Date());
		return session.save(mylog).toString();
	}
	
	@Transactional(readOnly=false)
	public Integer clearMylog(){
		Session session=sessionFactory.getCurrentSession();
		String hql="delete Mylog log";
		Query query=session.createQuery(hql);
		return query.executeUpdate();
	}
}
