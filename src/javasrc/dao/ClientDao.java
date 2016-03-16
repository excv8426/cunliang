package javasrc.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javasrc.entity.Client;
import javasrc.entity.Clientstatistics;

@Repository
@Transactional(readOnly=true)
public class ClientDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/**
	 * 按条件查询用户。
	 * @param client 查询条件*/
	public List<Client> getClients(Client client){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Client.class);
		if ((client.getMaxresult()!=null)&&(client.getFirstresult()!=null)) {
			criteria.setMaxResults(client.getMaxresult());
			criteria.setFirstResult(client.getFirstresult());
		}
		this.addRestrictions(client, criteria);
		@SuppressWarnings("unchecked")
		List<Client> clients=criteria.list();		
		return clients;
	}
	
	public Client getClient(Client client){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Client.class);
		criteria.add(Restrictions.idEq(client.getKehuxinxiid()));
		return (Client) criteria.uniqueResult();
	}
	
	/**
	 * 更新客户信息，已反馈。*/
	@Transactional(readOnly=false)
	public void updateClient(Client client){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Client.class);
		criteria.add(Restrictions.idEq(client.getKehuxinxiid()));
		Client client2=(Client) criteria.uniqueResult();
		client2.setYifankui(client.getYifankui());
	}
	
	/**
	 * 获取查询结果的总数。
	 * @param client 查询条件*/
	public Integer getClientscount(Client client){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Client.class);
		criteria.setProjection(Projections.rowCount());
		this.addRestrictions(client, criteria);
		Integer integer=Integer.parseInt(criteria.uniqueResult().toString());
		return integer;
	}
	
	@SuppressWarnings("unchecked")
	public List<Client> extractClient(Client client){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Client.class);
		this.addRestrictions(client, criteria);
		return criteria.list();
	}
	
	/**
	 * 清空客户信息和外呼反馈表。*/
	@Transactional(readOnly=false)
	public Integer clearClient(){
		String hql="delete Client client";
		Session session=sessionFactory.getCurrentSession();
		Query query=session.createQuery(hql);
		return query.executeUpdate();
	}
	

	@SuppressWarnings("unchecked")
	public List<Clientstatistics> getgroupbywaihucelve(){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Client.class);
		ProjectionList projectionList=Projections.projectionList();
		projectionList.add(Projections.rowCount(),"waihucelvecount");
		projectionList.add(Projections.groupProperty("waihucelve"),"waihucelve");
		criteria.setProjection(projectionList);
		criteria.setResultTransformer(Transformers.aliasToBean(Clientstatistics.class));
		return criteria.list();
	}
	
	@Transactional(readOnly=false)
	public String addClient(Client client){
		Session session=sessionFactory.getCurrentSession();
		String key=session.save(client).toString();
		session.flush();
		session.clear();
		return key;
	}
	
	@Transactional(readOnly=false)
	public Integer deletebywaihucelve(Client client){
		String sql="delete from kehuxinxi where 外呼策略=?";
		//级联删除异常
		/*Session session=sessionFactory.getCurrentSession();
		Query query=null;
		String hql="";
		if (client.getWaihucelve().equals("")) {
			hql="delete Client client where client.waihucelve is null";
			query=session.createQuery(hql);
		} else {
			hql="delete Client client where client.waihucelve=?";
			query=session.createQuery(hql);
			query.setString(0, client.getWaihucelve());
		}*/
		return jdbcTemplate.update(sql, client.getWaihucelve());
	}
	
	/**
	 * 设置查询条件。*/
	private void addRestrictions(Client client,Criteria criteria){
		if (client.getKehuxinxiid()!=null) {
			criteria.add(Restrictions.idEq(client.getKehuxinxiid()));
			return;
		}
		if (!client.getSuoshubanzu().equals("")) {
			criteria.add(Restrictions.like("suoshubanzu", "%"+client.getSuoshubanzu()+"%"));
		}
		if (!client.getKehujingli().equals("")) {
			criteria.add(Restrictions.or(Restrictions.eq("kehujingli", client.getKehujingli()), Restrictions.isNull("kehujingli")));
		}
		if (!client.getDianhuahaoma().equals("")) {
			criteria.add(Restrictions.eq("dianhuahaoma",client.getDianhuahaoma()));
		}
		if (!client.getHeyueleixing().equals("")) {
			criteria.add(Restrictions.like("heyueleixing", "%"+client.getHeyueleixing()+"%"));
		}
		if (!client.getRongheleixing().equals("")) {
			criteria.add(Restrictions.like("rongheleixing", "%"+client.getRongheleixing()+"%"));
		}
		if (!client.getTuijianzhengce().equals("")) {
			criteria.add(Restrictions.like("tuijianzhengce", "%"+client.getTuijianzhengce()+"%"));
		}
		if (!client.getChanpinbaohancbss().equals("")) {
			criteria.add(Restrictions.like("chanpinbaohancbss", "%"+client.getChanpinbaohancbss()+"%"));
		}
		if (!client.getQuanwangyewuleixing().equals("")) {
			criteria.add(Restrictions.like("quanwangyewuleixing", "%"+client.getQuanwangyewuleixing()+"%"));
		}
		if ((client.getKaihuriqilo()!=null)&&(client.getKaihuriqihi()!=null)) {
			criteria.add(Restrictions.between("kaihuriqi", client.getKaihuriqilo(), client.getKaihuriqihi()));
		}
		if ((client.getZaiwangshichanglo()!=null)&&(client.getZaiwangshichanghi()!=null)) {
			criteria.add(Restrictions.between("zaiwangshichang", client.getZaiwangshichanglo(), client.getZaiwangshichanghi()));
		}
		if ((client.getFentanqianshourulo()!=null)&&(client.getFentanqianshouruhi()!=null)) {
			criteria.add(Restrictions.between("fentanqianshouru", client.getFentanqianshourulo(), client.getFentanqianshouruhi()));
		}
		if (!client.getWaihucelve().equals("")) {
			criteria.add(Restrictions.eq("waihucelve", client.getWaihucelve()));
		}
		if (!client.getYifankui().equals("")) {
			if (client.getYifankui().equals("是")) {
				criteria.add(Restrictions.eq("yifankui", "是"));
			} else {
				criteria.add(Restrictions.isNull("yifankui"));
			}
		}
		if (!client.getBao().equals("")) {
			criteria.createCriteria("discounts").add(Restrictions.like("bao", "%"+client.getBao()+"%"));
		}
	}
}
