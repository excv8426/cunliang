package javasrc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javasrc.entity.Loginuser;
@Repository
@Transactional(readOnly=true)
public class LoginuserDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	/**
	 * 添加一个登录用户。
	 * @param loginuser单个登录用户。*/
	@Transactional(readOnly=false)
	public String addLoginuser(Loginuser loginuser){
		Session session=sessionFactory.getCurrentSession();
		return session.save(loginuser).toString();
	}
	
	/**
	 * 批量添加登录用户。
	 * @param loginusers登录用户列表*/
	@Transactional(readOnly=false)
	public Integer addLoginuser(List<Loginuser> loginusers){
		Session session=sessionFactory.getCurrentSession();
		for (int i = 0; i < loginusers.size(); i++) {
			session.save(loginusers.get(i));
			if (i%100==0) {
				session.flush();
				session.clear();
			}
		}
		return loginusers.size();
	}
	
	/**
	 * 根据用户名和密码查询登录用户。
	 * @param loginuser单个登录用户。
	 * @return 单个登录用户。*/
	@Transactional(readOnly=false)
	public Loginuser checkLoginuser(Loginuser loginuser){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Loginuser.class);
		criteria.add(Restrictions.idEq(loginuser.getLoginname()));
		Loginuser loginuser2=(Loginuser) criteria.uniqueResult();
		if ((loginuser2!=null)&&(!loginuser.getPassword().equals(loginuser2.getPassword()))) {
			int errorcount=loginuser2.getErrorcount()+1;
			loginuser2.setErrorcount(errorcount);
		}
		return loginuser2;
	}
	
	@Transactional(readOnly=false)
	public Integer updatepassword(Loginuser loginuser){
		String hql="update Loginuser loginuser set loginuser.password=? where loginuser.loginname=?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		query.setString(0, loginuser.getPassword());
		query.setString(1, loginuser.getLoginname());
		return query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Loginuser> getLoginusers(Loginuser loginuser){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Loginuser.class);
		if ((loginuser.getFirstresult()!=null)&&(loginuser.getMaxresult()!=null)) {
			criteria.setFirstResult(loginuser.getFirstresult());
			criteria.setMaxResults(loginuser.getMaxresult());
		}
		criteria.addOrder(Order.asc("authority"));
		return criteria.list();
	}
	
	public Integer getLoginuserscount(){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Loginuser.class);
		criteria.setProjection(Projections.rowCount());
		return Integer.parseInt(criteria.uniqueResult().toString());
	}
	
	/**
	 * 把所有用户的密码错误次数清零。*/
	@Transactional(readOnly=false)
	public Integer reseterrorcount(){
		String hql="update Loginuser loginuser set loginuser.errorcount=? where loginuser.errorcount>?";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		query.setInteger(0, 0);
		query.setInteger(1, 0);
		return query.executeUpdate();
	}
}
