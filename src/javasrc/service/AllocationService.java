package javasrc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javasrc.component.ObjectUtils;
import javasrc.dao.AllocationDao;
import javasrc.entity.Allocation;

@Service
public class AllocationService {
	@Autowired
	private AllocationDao allocationDao;
	
	private static String[] allocationtitle={"派单人员所属班组","派单人员","市/县区","具体营业厅","电话预约时间","用户号码","用户姓名","现使用资费","会员等级","推荐产品","预到厅办理时间","客户经理","短信编码","备注","用户是否到厅","客户是否办理预约业务","用户成功办理业务","办理时间","操作员姓名","用户不办理原因","短信编码（未在本预约表中的客户）","备注"};
	
	public String saveorupdateAllocation(Allocation allocation){
		allocation.setPaidanriqi(new Date());
		if (allocation.getPaidanid()==null) {
			return allocationDao.saveAllocation(allocation);
		} else {
			return allocationDao.updateAllocation(allocation);
		}
		
	}
	
	public List<Allocation> getAllocations(Allocation allocation){
		return allocationDao.getAllocations(allocation);
	}
	
	public Integer getAllocationscount(Allocation allocation){
		return allocationDao.getAllocationscount(allocation);
	}
	
	public String feedbackAllocation(Allocation allocation){
		return allocationDao.feedbackAllocation(allocation);
	}
	
	public String extractAllocation(Allocation allocation){
		List<List<String>> list=new ArrayList<>();
		List<Allocation> allocations=allocationDao.extractAllocations(allocation);
		for (int i = 0; i < allocations.size(); i++) {
			list.add(ObjectUtils.tolist(allocations.get(i),13));
		}
		String filepath=ExcelService.createexcel(list, allocationtitle);
		return filepath;
	}
}
