package javasrc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javasrc.component.ObjectUtils;
import javasrc.dao.ClientDao;
import javasrc.entity.Client;
import javasrc.entity.Clientstatistics;

@Service
public class ClientService {
	private String clienttitle[]={"电话号码","数据来源","所属班组","区县（资料表）","是否融合","渠道类型（自有与社会）","渠道","全网业务类型","客户姓名","客户类型","产品（包含CBSS）","月租费（3G）","套内含语音（3G）","套内含流量（3G）","套内含语音（4G）","套内含流量（4G）","开户日期","用户状态","终端厂商ID","终端机型","网络制式","终端价格","操作系统","主屏尺寸","CPU（核数）","内存容量","是否双卡","上市时间","上次换机月份","融合类型","融合类型1","合约类型","合约生效时间","合约失效时间","在网时长","主叫计费时长","流量","固网：本地通话费，G网：市话费 分摊前","长途费（分摊前）","漫游费（分摊前）","短信费（分摊前）","GPRS费（分摊前）","分摊前收入","费用合计（GSM+固网）","语音饱和度（3G）","流量饱和度（3G）","语音饱和度（4G）","流量饱和度（4G）","客户经理","推荐政策","非实名制标识","近两个月外呼过","营业厅名称","营业厅地址"};
	@Autowired
	private ClientDao clientDao;
	
	public List<Client> getClients(Client client){
		return clientDao.getClients(client);
	}
	
	public Client getClient(Client client){
		return clientDao.getClient(client);
	}
	
	public Integer getClientscount(Client client){
		return clientDao.getClientscount(client);
	}
	
	public Integer clearClient(){
		return clientDao.clearClient();
	}
	
	public List<Clientstatistics> getgroupbywaihucelve(){
		return clientDao.getgroupbywaihucelve();
	}
	
	public Integer deletebywaihucelve(Client client){
		return clientDao.deletebywaihucelve(client);
	}
	
	public String extractClient(Client client){
		List<List<String>> list=new ArrayList<>();
		List<Client> clients=clientDao.extractClient(client);
		for (int i = 0; i < clients.size(); i++) {
			list.add(ObjectUtils.tolist(clients.get(i), 19));
		}
		String filepath=ExcelService.createexcel(list, clienttitle);
		return filepath;
	}
}
