package javasrc.component;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javasrc.service.LoginuserService;
@Component
public class AutoTask {
	@Autowired
	private LoginuserService loginuserService;
	
	/**
	 * 定时任务。
	 * 每天x秒x分x时执行一次。*/
	@Scheduled(cron="0 0 11 * * ?" )
	private void deletetemporaryfiles(){
		File temporaryfiles=new File(AppProperty.serverpath+"temporary/");
		try {
			FileUtils.cleanDirectory(temporaryfiles);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		loginuserService.reseterrorcount();
	}
}
