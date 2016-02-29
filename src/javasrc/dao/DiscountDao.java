package javasrc.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javasrc.entity.Discount;

@Repository
@Transactional(readOnly=true)
public class DiscountDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Discount> getDiscounts(Discount discount){
		Session session=sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(Discount.class);
		if (discount.getDianhuahaoma()!=null) {
			criteria.add(Restrictions.eq("dianhuahaoma", discount.getDianhuahaoma()));
		}
		@SuppressWarnings("unchecked")
		List<Discount> discounts=criteria.list();
		return discounts;
	}
	
	@Transactional(readOnly=false)
	public Integer deletenullDiscounts(){
		String sql="delete from youhui where 包 is null and 优惠 is null and 优惠失效时间 is null";
		return jdbcTemplate.update(sql);
	}
	
	@Transactional(readOnly=false)
	public Integer deleteduplicateDiscounts() {
		String sql = " delete from youhui where youhuiid in ( " + " (select youhuiid from youhui) " + " except "
				+ " (select max(youhuiid)  from youhui group by 电话号码,包,优惠,优惠失效时间) )";
		return jdbcTemplate.update(sql);
	}
	
	@Transactional(readOnly=false)
	public Integer truncateDiscountrelation(){
		String sql="truncate table yonghu_youhui";
		return jdbcTemplate.update(sql);
	}
	
	@Transactional(readOnly=false)
	public Integer insertDiscountrelation(){
		String sql="insert into yonghu_youhui (dianhuahaoma,youhuiid) select 电话号码,youhuiid from youhui ";
		return jdbcTemplate.update(sql);
	}
}
