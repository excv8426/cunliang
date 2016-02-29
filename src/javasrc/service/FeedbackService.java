package javasrc.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javasrc.component.ObjectUtils;
import javasrc.dao.ClientDao;
import javasrc.dao.FeedbackDao;
import javasrc.entity.Client;
import javasrc.entity.Feedback;

@Service
public class FeedbackService {
	private static String feedbacktitle[]={"外呼经理姓名","所属班组","外呼号码","区县","外呼策略","回访情况","客户意向","成功办理业务","不办理原因","其他不办理原因","流失预警区域","预警策略","是否有离网倾向","流失原因","挽留结果","挽留手段","失败原因","预警用户查询及回访时长（分）","备注","反馈日期"};
	@Autowired
	private FeedbackDao feedbackDao;
	@Autowired
	private ClientDao clientDao;
	
	@Transactional(readOnly=false,isolation=Isolation.SERIALIZABLE)
	public String saveorupdateFeedback(Feedback feedback){
		Client client=new Client();
		client.setKehuxinxiid(feedback.getWaihufankuiid());
		client.setYifankui("是");
		if (feedback.getWaihujinglixingming().equals("")) {
			feedback.setWaihujinglixingming(null);
		}
		feedback.setFankuiriqi(new Date());
		Feedback feedback2=feedbackDao.getFeedback(feedback);
		if (feedback2==null) {
			clientDao.updateClient(client);
			return feedbackDao.saveFeedback(feedback);
		} else {
			if (feedback.getFankuirenyuangonghao().equals(feedback2.getFankuirenyuangonghao())) {
				return feedbackDao.updateFeedback(feedback);
			} else {
				return "can not modify others feedback";
			}
		}
	}
	
	public List<Feedback> getFeedbacks(Feedback feedback){
		return feedbackDao.getFeedbacks(feedback);
	}
	
	public Feedback getFeedback(Feedback feedback){
		return feedbackDao.getFeedback(feedback);
	}
	
	public Integer getFeedbackscount(Feedback feedback){
		return feedbackDao.getFeedbackscount(feedback);
	}
	
	@Transactional(readOnly=false,isolation=Isolation.SERIALIZABLE)
	public String callbyClient(Feedback feedback){
		Client client=new Client();
		client.setKehujingli(feedback.getWaihujinglixingming());
		client.setDianhuahaoma(feedback.getWaihuhaoma());
		client.setQuxian(feedback.getQuxian());
		client.setWaihucelve(feedback.getWaihucelve());
		client.setSuoshubanzu(feedback.getSuoshubanzu());
		client.setYifankui("是");
		feedback.setWaihufankuiid(Integer.parseInt(clientDao.addClient(client)));
		feedback.setFankuiriqi(new Date());
		if (feedback.getWaihujinglixingming().equals("")) {
			feedback.setWaihujinglixingming(null);
		}
		return feedbackDao.saveFeedback(feedback);
	}
	
	public String extractFeedback(Feedback feedback){
		List<List<String>> list=new ArrayList<>();
		List<Feedback> feedbacks=feedbackDao.extractFeedbacks(feedback);
		for (int i = 0; i < feedbacks.size(); i++) {
			list.add(ObjectUtils.tolist(feedbacks.get(i),7));
		}
		String filepath=ExcelService.createexcel(list,feedbacktitle);
		return filepath;
	}
}
