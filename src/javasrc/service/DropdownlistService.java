package javasrc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javasrc.dao.DropdownlistDao;
import javasrc.entity.Bubanliyuanyin;
import javasrc.entity.Yujingcelve;

@Service
public class DropdownlistService {
	@Autowired
	private DropdownlistDao dropdownlistDao;
	
	public List<Yujingcelve> getYujingcelves(){
		return dropdownlistDao.getYujingcelves();
	}
	
	public List<Bubanliyuanyin> getBubanliyuanyins(){
		return dropdownlistDao.getBubanliyuanyins();
	}
	
	public String addYujingcelve(Yujingcelve yujingcelve){
		return dropdownlistDao.addYujingcelve(yujingcelve);
	}
	
	public String addBubanliyuanyin(Bubanliyuanyin bubanliyuanyin){
		return dropdownlistDao.addBubanliyuanyin(bubanliyuanyin);
	}
	
	public Integer deleteYujingcelve(Yujingcelve yujingcelve){
		return dropdownlistDao.deleteYujingcelve(yujingcelve);
	}
	
	public Integer deleteBubanliyuanyin(Bubanliyuanyin bubanliyuanyin){
		return dropdownlistDao.deleteBubanliyuanyin(bubanliyuanyin);
	}
}
