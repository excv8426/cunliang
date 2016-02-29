/**
 * 电话号码。*/
var Kehuxinxiid=null;
/**
 * 客户信息*/
var clientobj=null;
/**
 * 终端信息模板*/
var terminaltemplate='<table class="table table-hover">'+
			    '<tr><td>终端厂商ID:</td><td>{{:zhongduanchangshangid}}</td><td>网络制式:</td><td>{{:wangluozhishi}}</td></tr>'+
			    '<tr><td>操作系统:</td><td>{{:caozuoxitong}}</td><td>终端价格:</td><td>{{:zhongduanjiage}}</td></tr>'+
			    '<tr><td>主屏尺寸:</td><td>{{:zhupingchicun}}</td><td>CPU核数:</td><td>{{:cpuheshu}}</td></tr>'+
			    '<tr><td>RAM容量:</td><td>{{:ramrongliang}}</td><td>是否双卡:</td><td>{{:shifoushuangka}}</td></tr>'+
			    '<tr><td>终端上市时间:</td><td>{{:zhongduanshangshishijian}}</td></tr>'+
			'</table>';
/**
 * 优惠信息模板*/
var discounttemplate='<tr><td>包:</td><td>{{:bao}}</td></tr>'+
			    '<tr><td>优惠:</td><td>{{:youhui}}</td></tr>'+
			    '<tr><td>优惠失效时间:</td><td>{{:youhuishixiaoshijian}}</td></tr>';
/**
 * 初始化电话号码参数。*/
function paraminit(){
	var args = new Object();
	args = GetUrlParms();
	Kehuxinxiid=args["kehuxinxiid"];
}
/**
 * 查询一个用户的所有信息，并且在多行表格中显示。*/
function getclientinfo(){
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
				clientobj=data[0];
				var clienttemplate=$.templates("#clienttemplate");
				$("#singleclientinfocontainer").html(clienttemplate.render(data[0]));
				$("#terminalinformation").bind("click",function(){
					displayterminal();
				});
				$("#discountinformation").bind("click",function(){
					displaydiscount();
				});
			}
		}
	});
}
/**
 * 根据json格式的数据生成多行表格。*/
function generateclientinfostr(userlist){
	var Singleclientinfotemplate = $.templates(singleclientinfotemplate);
	var clientinfostr="";
	for(listkey in userlist){
		var user=userlist[listkey];
		clientinfostr=clientinfostr+Singleclientinfotemplate.render(user);
	}
	return clientinfostr;
}
/**
 * 在模态对话框中显示终端信息*/
function displayterminal(){
	$('#myModal').find(".modal-body").html($.templates(terminaltemplate).render(clientobj));
	$('#myModal').modal();
}
/**
 * 在模态对话框中显示优惠信息*/
function displaydiscount(){
	$.ajax({
		cache:false,
		async:false,
		data :{dianhuahaoma:clientobj.dianhuahaoma},
		url : rootpath+"/loginuser/default/getDiscounts.do",
		type : "GET",
		dataType : "text",
		success : function(data) {
			var discounttable="";
			if(hasauthority(data)){
				data=$.parseJSON(data);
				var Discounttemplate=$.templates(discounttemplate);
				for(var listkey in data){
					discounttable=discounttable+Discounttemplate.render(data[listkey]);
				}
				discounttable='<table class="table table-hover">'+discounttable+"</table>";
				$('#myModal').find(".modal-body").html(discounttable);
				$('#myModal').modal();
			}
		}
	});

}
/**
 * 初始化函数。*/
$(function(){
	$.ajaxSetup({
		error:function(XMLHttpRequest,errormessage,errorobject){
			alert("error:"+XMLHttpRequest+"-"+errormessage+"-"+errorobject);
		}
	});
	paraminit();
	getclientinfo();
	$("#feedback_button").bind("click",function(){
		window.open("feedback.html?kehuxinxiid="+Kehuxinxiid+"&loginname="+Loginname);
	});
});