package javasrc.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javasrc.service.LoginuserService;
@Controller
public class FileController {
	@Autowired
	private LoginuserService loginuserService;
	
	@RequestMapping("filesupload")
	public void filesupload(@RequestParam("file") MultipartFile[] files,@RequestParam("type") String type){
		switch (type) {
		case "loginuser":
			for (int i = 0; i < files.length; i++) {
				try {
					loginuserService.addLoginuser(files[i].getInputStream());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			break;
		default:
			break;
		}
	}
}
