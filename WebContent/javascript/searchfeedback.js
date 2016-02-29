/**
 * 反馈列表模板。*/
var feedbacklistbodytemplate='<tr>'+
'<td><button class="btn btn-link btn-block">{{:waihufankuiid}}</button></td>'+
'<td>{{:waihujinglixingming}}</td>'+
'<td>{{:suoshubanzu}}</td>'+
'<td>{{:waihuhaoma}}</td>'+
'<td>{{:quxian}}</td>'+
'<td>{{:waihucelve}}</td>'+
'<td>{{:huifangqingkuang}}</td>'+
'<td>{{:kehuyixiang}}</td>'+
'<td>{{:chenggongbanliyewu}}</td>'+
'<td>{{:bubanliyuanyin}}</td>'+
'<td>{{:qitabubanliyuanyin}}</td>'+
'<td>{{:liushiyujingquyu}}</td>'+
'<td>{{:yujingcelve}}</td>'+
'<td>{{:shifouyouliwangqingxiang}}</td>'+
'<td>{{:liushiyuanyin}}</td>'+
'<td>{{:wanliujieguo}}</td>'+
'<td>{{:wanliushouduan}}</td>'+
'<td>{{:shibaiyuanyin}}</td>'+
'<td>{{:yujingyonghuchaxunjihuifangshichang}}</td>'+
'<td>{{:beizhu}}</td>'+
'<td>{{:fankuiriqi}}</td>'+
'</tr>';

/**
 * 外呼反馈查询条件。*/
var searchfeedbackparam={waihujinglixingming:"",
		waihucelve:"",
		chenggongbanliyewu:"",
		bubanliyuanyin:"",
		yujingcelve:"",
		maxresult:15,
		firstresult:0,
		fankuiriqilo:"",
		fankuiriqihi:""
	};

function setsearchfeedbackparam(){
	searchfeedbackparam.waihujinglixingming=$("#waihujinglixingming_input").val();
	searchfeedbackparam.fankuiriqilo=$("#fankuiriqilo_input").val();
	searchfeedbackparam.fankuiriqihi=$("#fankuiriqihi_input").val();
	searchfeedbackparam.waihucelve=$("#waihucelve_input").val();
	searchfeedbackparam.chenggongbanliyewu=$("#chenggongbanliyewu_select").val();
	searchfeedbackparam.bubanliyuanyin=$("#bubanliyuanyin_select").val();
	searchfeedbackparam.yujingcelve=$("#yujingcelve_select").val();
	searchfeedbackparam.firstresult=0;
}

/**
 * 获取查询结果数量。*/
function getFeedbackscount(){
	$.ajax({
		cache:false,
		async:true,
		url : rootpath+"/loginuser/adminmanager/getFeedbackscount.do",
		data : searchfeedbackparam,
		type : "GET",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				data=$.parseJSON(data);
				initpagination($(".pagination"),searchfeedbacks,searchfeedbackparam,data);
				$("#statistics_container").html("找到"+data+"条");
			}
		}
	});
}
/**
 * 按条件查询外呼反馈，在table中显示查询结果。*/
function searchfeedbacks(){
	$.ajax({
		cache:false,
		async:true,
		url : rootpath+"/loginuser/adminmanager/getFeedbacks.do",
		data :searchfeedbackparam,
		type : "GET",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				data=$.parseJSON(data);
				var tabletrs=generatefeedbacklisttr(data);
				$("#feedback_table").find("tbody").html(tabletrs);
				bindtrclick();
			}
		}
	});
}
/**
 * 根据json格式的数据生成反馈信息tr字符串。*/
function generatefeedbacklisttr(feedbacklist){
	var Feedbacklistbodytemplate = $.templates(feedbacklistbodytemplate);
	var feedbacklisttr="";
	for(listkey in feedbacklist){
		var feedback=feedbacklist[listkey];
		feedbacklisttr=feedbacklisttr+Feedbacklistbodytemplate.render(feedback);
	}
	return feedbacklisttr;
}
/**
 * 把外呼反馈信息导出Excel*/
function extractfeedback(){
	$.ajax({
		cache:false,
		async:true,
		url : rootpath+"/loginuser/adminmanager/extractFeedback.do",
		data :searchfeedbackparam,
		type : "GET",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				download(data);
			}
		}
	});
}
function bindtrclick(){
	$("#feedback_table").children("tbody").find("button").each(function(){
		var kehuxinxiid=$(this).text();
		$(this).bind("click",function(){
			window.open("feedback.html?kehuxinxiid="+kehuxinxiid+"&loginname="+Loginname);
		});
	});
}
function bindbuttonclick(){
	$("#searchfeedback_button").bind("click",function(){
		setsearchfeedbackparam();
		getFeedbackscount();
		searchfeedbacks();
	});
	$("#cancel_button").bind("click",function(){
		clearinput();
	});
	$("#extractfeedback_button").bind("click",function(){
		setsearchfeedbackparam();
		extractfeedback();
	});
}
/**
 * 初始化日期选择器。*/
function initdatetimepicker(){
	$("#fankuiriqilo_input").datetimepicker({
		autoclose: true,
		format: 'yyyy-mm-dd',
		minView: 'month',
		language: 'zh-CN'
	});
	$("#fankuiriqihi_input").datetimepicker({
		autoclose: true,
		format: 'yyyy-mm-dd',
		minView: 'month',
		language: 'zh-CN'
	});
}
/**
 * 初始化函数。*/
$(function(){
	$.ajaxSetup({
		error:function(XMLHttpRequest,errormessage,errorobject){
			$("#myModal").find(".modal-body").find("p").html("error:"+XMLHttpRequest+"-"+errormessage+"-"+errorobject);
			$('#myModal').modal();
		}
	});
	bindbuttonclick();
	initdatetimepicker();
});