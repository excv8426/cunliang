package javasrc.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javasrc.entity.Client;
import javasrc.service.ClientService;

@Controller
public class ClientController {
	@Autowired
	private ClientService clientService;
	
	@RequestMapping("loginuser/default/getClients")
	@ResponseBody
	public List<Client> getClients(HttpSession session,Client client){
		if (session.getAttribute("authority").equals("2")) {
			client.setKehujingli(session.getAttribute("personname").toString());
		}
		return clientService.getClients(client);
	}
	
	@RequestMapping("loginuser/default/getClientscount")
	@ResponseBody
	public Integer getClientscount(HttpSession session,Client client){
		if (session.getAttribute("authority").equals("2")) {
			client.setKehujingli(session.getAttribute("personname").toString());
		}
		return clientService.getClientscount(client);
	}
	
	@RequestMapping("loginuser/default/extractClient")
	@ResponseBody
	public String extractClient(HttpSession session,Client client){
		if (session.getAttribute("authority").equals("2")) {
			client.setKehujingli(session.getAttribute("personname").toString());
		}
		return clientService.extractClient(client);
	}
	
	@RequestMapping("loginuser/system/clearClient")
	@ResponseBody
	public Integer clearClient(){
		return clientService.clearClient();
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping("loginuser/system/getgroupbywaihucelve")
	@ResponseBody
	public List getgroupbywaihucelve(){
		return clientService.getgroupbywaihucelve();
	}
	
	@RequestMapping("loginuser/system/deletebywaihucelve")
	@ResponseBody
	public Integer deletebywaihucelve(Client client){
		return clientService.deletebywaihucelve(client);
	}
}
