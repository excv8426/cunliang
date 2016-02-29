/**
 * 未填写的input id*/
var missingid=new Array("huifangqingkuang_select");
/**
 * 必须填写的input id*/
var requiredid=["huifangqingkuang_select","kehuyixiang_select","chenggongbanliyewu_select","bubanliyuanyin_select","qitabubanliyuanyin_input"];
/**
 * 客户信息编号。*/
var Kehuxinxiid=null;
/**
 * 外呼反馈信息。*/
var feedback={waihufankuiid:null,
		beizhu:"",
		bubanliyuanyin:"",
		chenggongbanliyewu:"",
		huifangqingkuang:"",
		kehuyixiang:"",
		liushiyuanyin:"",
		liushiyujingquyu:"",
		qitabubanliyuanyin:"",
		quxian:"",
		shibaiyuanyin:"",
		shifouyouliwangqingxiang:"",
		waihucelve:"",
		suoshubanzu:"",
		waihuhaoma:"",
		waihujinglixingming:"",
		wanliujieguo:"",
		wanliushouduan:"",
		yujingcelve:"",
		yujingyonghuchaxunjihuifangshichang:"",};
/**
 * 初始化电话号码参数。*/
function paraminit(){
	var args = new Object();
	args = GetUrlParms();
	Kehuxinxiid=args["kehuxinxiid"];
}
/**
 * 初始化反馈页面。*/
function initfeedbackinput(){
	$.ajax({
		cache:false,
		async:false,
		url : rootpath+"/loginuser/default/getClients.do",
		data :{kehuxinxiid:Kehuxinxiid},
		type : "GET",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				data=$.parseJSON(data);
				var client=data[0];
				$("#waihujinglixingming_input").val(client.kehujingli);
				$("#waihuhaoma_input").val(client.dianhuahaoma);
				$("#quxian_input").val(client.quxian);
				$("#waihucelve_input").val(client.waihucelve);
				$("#suoshubanzu_input").val(client.suoshubanzu);
			}
		}
	});
	$.ajax({
		cache:false,
		async:false,
		url : rootpath+"/loginuser/adminmanager/getFeedbacks.do",
		data :{waihufankuiid:Kehuxinxiid},
		type : "GET",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				data=$.parseJSON(data);
				var feedbackjson=data[0];
				if((feedbackjson!=null)&&(feedbackjson!=undefined)){
					$("#beizhu_input").val(feedbackjson.beizhu);
					$("#bubanliyuanyin_select").val(feedbackjson.bubanliyuanyin);
					$("#chenggongbanliyewu_select").val(feedbackjson.chenggongbanliyewu);
					$("#huifangqingkuang_select").val(feedbackjson.huifangqingkuang);
					$("#kehuyixiang_select").val(feedbackjson.kehuyixiang);
					$("#liushiyuanyin_select").val(feedbackjson.liushiyuanyin);
					$("#liushiyujingquyu_select").val(feedbackjson.liushiyujingquyu);
					$("#qitabubanliyuanyin_input").val(feedbackjson.qitabubanliyuanyin);
					$("#shibaiyuanyin_select").val(feedbackjson.shibaiyuanyin);
					$("#shifouyouliwangqingxiang_select").val(feedbackjson.shifouyouliwangqingxiang);
					$("#wanliujieguo_select").val(feedbackjson.wanliujieguo);
					$("#wanliushouduan_select").val(feedbackjson.wanliushouduan);
					$("#yujingcelve_select").val(feedbackjson.yujingcelve);
					$("#yujingyonghuchaxunjihuifangshichang_input").val(feedbackjson.yujingyonghuchaxunjihuifangshichang);
					checkmissinginput();
				}
				
			}
		}
	});
	checkmissinginput();
}
function initselect(){
	$("#yujingcelve_select").html("<option></option>"+generatedropdownlist("yujingcelve"));
	$("#bubanliyuanyin_select").html("<option></option>"+generatedropdownlist("bubanliyuanyin"));
}
/**
 * 设置外呼反馈信息。*/
function setfeedback(){
	feedback.beizhu=$("#beizhu_input").val();
	feedback.bubanliyuanyin=$("#bubanliyuanyin_select").val();
	feedback.chenggongbanliyewu=$("#chenggongbanliyewu_select").val();
	feedback.huifangqingkuang=$("#huifangqingkuang_select").val();
	feedback.kehuyixiang=$("#kehuyixiang_select").val();
	feedback.liushiyuanyin=$("#liushiyuanyin_select").val();
	feedback.liushiyujingquyu=$("#liushiyujingquyu_select").val();
	feedback.qitabubanliyuanyin=$("#qitabubanliyuanyin_input").val();
	feedback.quxian=$("#quxian_input").val();
	feedback.shibaiyuanyin=$("#shibaiyuanyin_select").val();
	feedback.shifouyouliwangqingxiang=$("#shifouyouliwangqingxiang_select").val();
	feedback.waihucelve=$("#waihucelve_input").val();
	feedback.waihuhaoma=$("#waihuhaoma_input").val();
	feedback.waihujinglixingming=$("#waihujinglixingming_input").val();
	feedback.wanliujieguo=$("#wanliujieguo_select").val();
	feedback.wanliushouduan=$("#wanliushouduan_select").val();
	feedback.yujingcelve=$("#yujingcelve_select").val();
	feedback.yujingyonghuchaxunjihuifangshichang=$("#yujingyonghuchaxunjihuifangshichang_input").val();
	feedback.suoshubanzu=$("#suoshubanzu_input").val();
	feedback.waihufankuiid=Kehuxinxiid;
}
/**
 * 添加一条外呼反馈。*/
function addfeedback(){
	if(checkmissinginput()==0){
		setfeedback();
		$.ajax({
			cache:false,
			async:true,
			url : rootpath+"/loginuser/adminmanager/saveorupdateFeedback.do",
			data : feedback,
			type : "POST",
			dataType : "text",
			success : function(data) {
				if(hasauthority(data)){
					data=$.parseJSON(data);
					if((data.exception!=null)||(data.exception!=undefined)){
						$("#myModal").find(".modal-body").find("p").html(data.exception);
					}else{
						if((data.error!=null)||(data.error!=undefined)){
							$("#myModal").find(".modal-body").find("p").html("其他用户已经反馈过，不能修改。");
						}else{
							$("#myModal").find(".modal-body").find("p").html("反馈成功。");
						}
					}
					$('#myModal').modal();
				}
			}
		});
	}else{
		$("#myModal").find(".modal-body").find("p").html("信息未填写完整。");
		$('#myModal').modal();
	}
}
/**
 * 初始化必填输入提示。*/
function inittooltip(){
	$("#huifangqingkuang_select").tooltip({
		placement:"right",
		title:"必填",
		trigger:"manual"
	});
	$("#kehuyixiang_select").tooltip({
		placement:"right",
		title:"必填",
		trigger:"manual"
	});
	$("#chenggongbanliyewu_select").tooltip({
		placement:"right",
		title:"必填",
		trigger:"manual"
	});
	$("#bubanliyuanyin_select").tooltip({
		placement:"right",
		title:"必填",
		trigger:"manual"
	});
	$("#qitabubanliyuanyin_input").tooltip({
		placement:"right",
		title:"必填",
		trigger:"manual"
	});
	$("#huifangqingkuang_select").tooltip('show');
}
/**
 * 检出未填项，返回未填项数目。*/
function checkmissinginput(){
	var missingcount=0;
	for(var i=0;i<requiredid.length;i++){
		if(arraycontain(missingid,requiredid[i])){
			if($.trim($("#"+requiredid[i]).val())==""){
				$("#"+requiredid[i]).tooltip('show');
				missingcount++;
			}else{
				$("#"+requiredid[i]).tooltip('hide');
			}
		}else{
			$("#"+requiredid[i]).tooltip('hide');
		}
	}
	return missingcount;
}
/**
 * 根据输入框之间的依赖关系，更新必填输入列表。*/
function bindinputchange(){
	$("#huifangqingkuang_select").bind("change",function(){
		if($("#huifangqingkuang_select").val()=="接通"){		
			missingid.push("kehuyixiang_select");
		}else{
			removearrayelement(missingid,"kehuyixiang_select");
		}
	});
	$("#kehuyixiang_select").bind("change",function(){
		if($("#kehuyixiang_select").val()=="客户办理"){
			missingid.push("chenggongbanliyewu_select");
			removearrayelement(missingid,"bubanliyuanyin_select");
		}else if($("#kehuyixiang_select").val()=="明确不办"){		
			missingid.push("bubanliyuanyin_select");
			removearrayelement(missingid,"chenggongbanliyewu_select");
		}else{		
			removearrayelement(missingid,"chenggongbanliyewu_select");
			removearrayelement(missingid,"bubanliyuanyin_select");
		}
	});
	$("#bubanliyuanyin_select").bind("change",function(){
		if($("#bubanliyuanyin_select").val()=="M.其他（需在备注写明原因）"){
			missingid.push("qitabubanliyuanyin_input");
		}else{
			removearrayelement(missingid,"qitabubanliyuanyin_input");
		}
	});
}
/**
 * 绑定失去焦点事件。*/
function bindinputblur(){
	$("input").each(function(){
		$(this).bind("click",function(){
			checkmissinginput();
		});
	});
	$("select").each(function(){
		$(this).bind("click",function(){
			checkmissinginput();
		});
	});
}
$(function(){
	paraminit();
	inittooltip();
	initfeedbackinput();
	bindinputchange();
	bindinputblur();
	initselect();
	$("#addfeedback_button").bind("click",function(){
		temporarydisable($(this));
		addfeedback();
	});
	$("#cancel_button").bind("click",function(){
		clearinput();
		cleararray(missingid);
		missingid.push("huifangqingkuang_select");
	});
});