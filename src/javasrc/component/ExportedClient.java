package javasrc.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import javasrc.dao.ClientDao;
import javasrc.entity.Client;

public class ExportedClient implements ExportedObject {
	private static int pagesize=10000;
	private int currentpage=0;
	private int size=0;
	private Client selectparam;
	@Autowired
	private ClientDao clientDao;
	
	public ExportedClient(Client selectparam){
		this.selectparam=selectparam;
		size=clientDao.getClientscount(selectparam);
	}

	@Override
	public List<List<String>> getData() {
		selectparam.setFirstresult(currentpage*pagesize);
		selectparam.setMaxresult(pagesize);
		List<List<String>> data=new ArrayList<>();
		List<Client> clients=clientDao.getClients(selectparam);
		for (int i = 0; i < clients.size(); i++) {
			data.add(ObjectUtils.tolist(clients.get(i), 19));
		}
		currentpage++;
		return data;
	}

	@Override
	public boolean haveNext() {
		return size>(currentpage*pagesize);
	}

}
