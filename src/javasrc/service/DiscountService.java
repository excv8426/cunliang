package javasrc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javasrc.dao.DiscountDao;
import javasrc.entity.Discount;

@Service
public class DiscountService {
	@Autowired
	private DiscountDao discountDao;
	
	public List<Discount> getDiscounts(Discount discount){
		return discountDao.getDiscounts(discount);
	}
	
	public Integer reorganizeDiscounts(){
		discountDao.deletenullDiscounts();
		return discountDao.deleteduplicateDiscounts();
	}
	
	public Integer updateDiscountrelation(){
		discountDao.truncateDiscountrelation();
		return discountDao.insertDiscountrelation();
	}
}
