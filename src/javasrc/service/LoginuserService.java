package javasrc.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javasrc.dao.LoginuserDao;
import javasrc.entity.Loginuser;

@Service
public class LoginuserService {
	@Autowired
	private LoginuserDao loginuserDao;
	
	/**
	 * 添加一个登录用户。
	 * @param loginuser单个登录用户。*/
	public String addLoginuser(Loginuser loginuser){
		String namepsd=loginuser.getLoginname()+loginuser.getPassword();
		loginuser.setPassword(DigestUtils.md5DigestAsHex(namepsd.getBytes()));
		loginuser.setErrorcount(0);
		return loginuserDao.addLoginuser(loginuser);
	}
	
	/**
	 * 根据用户名和密码查询登录用户。
	 * @param loginuser单个登录用户。
	 * @return 单个登录用户。*/
	public Loginuser checkLoginuser(Loginuser loginuser){
		return loginuserDao.checkLoginuser(loginuser);
	}
	
	/**
	 * 批量添加登录用户。
	 * @param inputStream登录用户Excel列表*/
	public Integer addLoginuser(InputStream inputStream){
		List<List<String>> loginusers=ExcelService.getcells(inputStream);
		List<Loginuser> loginusers2=new ArrayList<>();
		for (int i = 1; i < loginusers.size(); i++) {
			Loginuser loginuser=new Loginuser();
			loginuser.setLoginname(loginusers.get(i).get(0).trim());
			loginuser.setPersonname(loginusers.get(i).get(1).trim());
			String namepsd=loginuser.getLoginname()+loginusers.get(i).get(2);
			loginuser.setPassword(DigestUtils.md5DigestAsHex(namepsd.getBytes()));
			String authority=loginusers.get(i).get(3);
			if ((authority.equals("1"))||(authority.equals("2"))||(authority.equals("3"))) {
				loginuser.setAuthority(authority);
			} else {
				continue;
			}
			loginuser.setErrorcount(0);
			loginusers2.add(loginuser);
		}
		return loginuserDao.addLoginuser(loginusers2);
	}
	
	public Integer updatepassword(Loginuser loginuser){
		String namepsd=loginuser.getLoginname()+loginuser.getPassword();
		loginuser.setPassword(DigestUtils.md5DigestAsHex(namepsd.getBytes()));
		return loginuserDao.updatepassword(loginuser);
	}
	
	public List<Loginuser> getLoginusers(Loginuser loginuser){
		return loginuserDao.getLoginusers(loginuser);
	}
	
	public Integer reseterrorcount(){
		return loginuserDao.reseterrorcount();
	}
	
	public Integer getLoginuserscount(){
		return loginuserDao.getLoginuserscount();
	}
}
