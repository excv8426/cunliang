package javasrc.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javasrc.dao.ClientDao;
import javasrc.entity.Client;

@Component
public class ExportedClientFactory {
	@Autowired
	private ClientDao clientDao;
	
	public ExportedObject newExportedClient(Client selectparam){
		return new ExportedClient(selectparam);
	}
	
	private class ExportedClient implements ExportedObject{
		private static final int pagesize=10000;
		private int currentpage=0;
		private int size=0;
		private Client selectparam;
		
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

		@Override
		public int getStartrownum() {
			return currentpage*pagesize;
		}
		
	}
}
