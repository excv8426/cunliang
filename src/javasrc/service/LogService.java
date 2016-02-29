package javasrc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javasrc.dao.MylogDao;
import javasrc.entity.Mylog;

@Service
public class LogService {
	@Autowired
	private MylogDao mylogDao;
	
	public void addlog(Mylog log){
		mylogDao.addMylog(log);
	}
	
	public Integer clearMylog(){
		return mylogDao.clearMylog();
	}
}
