/**
 * 用户信息表格行模板。*/
var clientlistbodytemplate='<tr>'+
                '<td style="display:none">{{:kehuxinxiid}}</td>'+
                '<td><button class="btn btn-link btn-block">{{:dianhuahaoma}}</button></td>'+
                '<td>{{:shujulaiyuan}}</td>'+
                '<td>{{:suoshubanzu}}</td>'+
                '<td>{{:quxian}}</td>'+
                '<td>{{:shifouronghe}}</td>'+
                '<td>{{:qudaoleixing}}</td>'+
                '<td>{{:qudao}}</td>'+
                '<td>{{:quanwangyewuleixing}}</td>'+
                '<td>{{:kehuxingming}}</td>'+
                '<td>{{:kehuleixing}}</td>'+
                '<td style="display:none">{{:shifoujituanyonghu}}</td>'+
                '<td style="display:none">{{:pinpaibaohancbss}}</td>'+
                '<td>{{:chanpinbaohancbss}}</td>'+
                '<td>{{:yuezufei3g}}</td>'+
                '<td>{{:taoneihanyuyin3g}}</td>'+
                '<td>{{:taoneihanliuliang3g}}</td>'+
                '<td>{{:taoneihanyuyin4g}}</td>'+
                '<td>{{:taoneihanliuliang4g}}</td>'+
                '<td>{{:kaihuriqi}}</td>'+
                '<td>{{:yonghuzhuangtai}}</td>'+
                '<td>{{:zhongduanchangshangid}}</td>'+
                '<td>{{:zhongduanjixing}}</td>'+
                '<td>{{:wangluozhishi}}</td>'+
                '<td>{{:zhongduanjiage}}</td>'+
                '<td>{{:caozuoxitong}}</td>'+
                '<td>{{:zhupingchicun}}</td>'+
                '<td>{{:cpuheshu}}</td>'+
                '<td>{{:ramrongliang}}</td>'+
                '<td>{{:shifoushuangka}}</td>'+
                '<td>{{:zhongduanshangshishijian}}</td>'+
                '<td>{{:shangcihuanjiyuefen}}</td>'+
                '<td>{{:rongheleixing}}</td>'+
                '<td>{{:rongheleixing1}}</td>'+
                '<td>{{:heyueleixing}}</td>'+
                '<td>{{:heyueshengxiaoshijian}}</td>'+
                '<td>{{:heyueshixiaoshijian}}</td>'+
                '<td>{{:zaiwangshichang}}</td>'+
                '<td>{{:zhujiaojifeishichang}}</td>'+
                '<td>{{:liuliang}}</td>'+
                '<td>{{:guwangbenditonghuafei}}</td>'+
                '<td>{{:changtufeifentanqian}}</td>'+
                '<td>{{:manyoufeifentanqian}}</td>'+
                '<td>{{:duanxinfeifentanqian}}</td>'+
                '<td>{{:gprsfeifentanqian}}</td>'+
                '<td>{{:fentanqianshouru}}</td>'+
                '<td>{{:feiyonghejigsmguwang}}</td>'+
                '<td>{{:yuyinbaohedu3g}}</td>'+
                '<td>{{:liuliangbaohedu3g}}</td>'+
                '<td>{{:yuyinbaohedu4g}}</td>'+
                '<td>{{:liuliangbaohedu4g}}</td>'+
                '<td>{{:kehujingli}}</td>'+
                '<td>{{:tuijianzhengce}}</td>'+
                '<td>{{:feishimingzhibiaozhi}}</td>'+
                '<td>{{:jinlianggeyuewaihuguo}}</td>'+
                '<td>{{:yingyetingmingcheng}}</td>'+
                '<td>{{:yingyetingdizhi}}</td>'+
                '</tr>';
/**
 * 查询用户的条件。*/
var searchclientparam={kehujingli:null,
		suoshubanzu:null,
		chanpinbaohancbss:null,
		heyueleixing:null,
		dianhahaoma:null,
		shituizhengce:null,
		quanwangyewuleixing:null,
		rongheleixing:null,
		kaihuriqilo:null,
		kaihuriqihi:null,
		zaiwangshichanglo:null,
		zaiwangshichanghi:null,
		fentanqianshourulo:null,
		fentanqianshouruhi:null,
		waihucelve:null,
		yifankui:null,
		bao:null,
		maxresult:15,
		firstresult:0};

/**
 * 获取查询结果数量。*/
function getresultsize(){
	$.ajax({
		cache:false,
		async:true,
		url : rootpath+"/loginuser/default/getClientscount.do",
		data : searchclientparam,
		type : "GET",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				initpagination($(".pagination"),searchclient,searchclientparam,data);
				$("#statistics_container").html("找到"+data+"条");
			}
		}
	});
}
/**
 * 按条件查询用户，在table中显示查询结果。*/
function searchclient(){
	$.ajax({
		cache:false,
		async:true,
		url : rootpath+"/loginuser/default/getClients.do",
		data :searchclientparam,
		type : "GET",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				data=$.parseJSON(data);
				var tabletrs=generateclientlisttr(data);
				$("#client_table").find("tbody").html(tabletrs);
				bindtrclick();
			}
		}
	});
}
/**
 * 根据json格式的数据生成用户信息tr字符串。*/
function generateclientlisttr(clientlist){
	var Clientlistbodytemplate = $.templates(clientlistbodytemplate);
	var clientlisttr="";
	for(listkey in clientlist){
		var client=clientlist[listkey];
		clientlisttr=clientlisttr+Clientlistbodytemplate.render(client);
	}
	return clientlisttr;
}
function extractclient(){
	$.ajax({
		cache:false,
		async:true,
		url : rootpath+"/loginuser/default/extractClient.do",
		data :searchclientparam,
		type : "GET",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				download(data);
			}
		}
	});
}
function setsearchclientparam(){
	searchclientparam.kehujingli=$("#kehujingli_input").val();
	searchclientparam.suoshubanzu=$("#suoshubanzu_input").val();
	searchclientparam.chanpinbaohancbss=$("#chanpinbaohancbss_input").val();
	searchclientparam.heyueleixing=$("#heyueleixing_input").val();
	searchclientparam.dianhuahaoma=$("#dianhuahaoma_input").val();
	searchclientparam.shituizhengce=$("#shituizhengce_input").val();
	searchclientparam.quanwangyewuleixing=$("#quanwangyewuleixing_input").val();
	searchclientparam.rongheleixing=$("#rongheleixing_input").val();
	searchclientparam.kaihuriqilo=$("#kaihuriqilo_input").val();
	searchclientparam.kaihuriqihi=$("#kaihuriqihi_input").val();
	searchclientparam.zaiwangshichanglo=$("#zaiwangshichanglo_input").val();
	searchclientparam.zaiwangshichanghi=$("#zaiwangshichanghi_input").val();
	searchclientparam.fentanqianshourulo=$("#fentanqianshourulo_input").val();
	searchclientparam.fentanqianshouruhi=$("#fentanqianshouruhi_input").val();
	searchclientparam.waihucelve=$("#waihucelve_input").val();
	searchclientparam.yifankui=$("#yifankui_select").val();
	searchclientparam.bao=$("#bao_input").val();
	searchclientparam.firstresult=0;
	emptytonull(searchclientparam);
}
/**
 * 绑定按钮的点击事件。*/
function bindbutton(){
	$("#search_button").bind("click",function(){
		setsearchclientparam();
		getresultsize();
		searchclient();
	});
	$("#cancel_button").bind("click",function(){
		clearinput();
	});
	$("#extractclient_button").bind("click",function(){
		setsearchclientparam();
		extractclient();
	});
}
/**
 * 初始化日期选择器。*/
function initdatetimepicker(){
	$("#kaihuriqilo_input").datetimepicker({
		autoclose: true,
		format: 'yyyy-mm-dd',
		minView: 'month',
		language: 'zh-CN'
	});
	$("#kaihuriqihi_input").datetimepicker({
		autoclose: true,
		format: 'yyyy-mm-dd',
		minView: 'month',
		language: 'zh-CN'
	});
}

/**
 * 绑定tr的双击事件。*/
function bindtrclick(){
	$("#client_table").children("tbody").find("button").each(function(){
		var kehuxinxiid=$(this).parent().siblings().eq(0).text();
		$(this).bind("click",function(){
			window.open("singleclient.html?kehuxinxiid="+kehuxinxiid+"&loginname="+Loginname);
		});
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
	bindbutton();
	initdatetimepicker();
});
