var shiquyingyeting = new Array("存量中心","秦皇岛市开发区天台山路营业厅", "秦皇岛市海港区香格里营业厅",
		"秦皇岛市海港区西港路营业厅", "秦皇岛市海港区河东营业厅", "秦皇岛市海港区红旗路营业厅", "秦皇岛市海港区滨海城营业厅",
		"秦皇岛市海港区文化路营业厅", "秦皇岛市海港区燕山营业厅", "秦皇岛市海港区峨眉山营业厅");
var changliyingyeting = new Array("秦皇岛市昌黎县团林营业厅", "秦皇岛市昌黎县大蒲河营业厅",
		"秦皇岛市昌黎县新集营业厅", "秦皇岛市昌黎县民生街营业厅", "秦皇岛市昌黎县龙家店营业厅", "秦皇岛市昌黎县安山营业厅",
		"秦皇岛市昌黎县靖安营业厅", "秦皇岛市昌黎县朱各局营业厅(外包)", "秦皇岛市昌黎县虹桥营业厅", "秦皇岛市昌黎县泥井营业厅",
		"秦皇岛市昌黎县刘台庄营业厅", "秦皇岛市昌黎县荒佃庄营业厅", "秦皇岛市昌黎县黄金海岸旅游区营业厅", "秦皇岛市昌黎县两山营业厅");
var lulongyingyeting = new Array("秦皇岛市卢龙县新城大街营业厅", "秦皇岛市卢龙县迎宾路营业厅",
		"秦皇岛市卢龙县石门营业厅", "秦皇岛市卢龙县木井营业厅", "秦皇岛市卢龙县刘田庄营业厅", "秦皇岛市卢龙县双望营业厅",
		"秦皇岛市卢龙县陈官屯营业厅", "秦皇岛市卢龙县燕河营营业厅", "秦皇岛市卢龙县潘庄营业厅");
var shanhaiguanyingyeting = new Array("秦皇岛市山海关区南海西路营业厅", "秦皇岛市山海关区开发区营业厅",
		"秦皇岛市山海关区斌杨", "秦皇岛市山海关区老龙头营业厅");
var beidaiheyingyeting = new Array("秦皇岛市北戴河区东经路营业厅", "秦皇岛市北戴河区联峰路营业厅");
var funingyingyeting = new Array("秦皇岛市抚宁县台营营业厅", "秦皇岛市抚宁县大新寨营业厅",
		"秦皇岛市抚宁县深河营业厅", "秦皇岛市抚宁县南戴河营业厅", "秦皇岛市抚宁县北大街营业厅", "秦皇岛市抚宁县富强路营业厅",
		"秦皇岛市抚宁县榆关营业厅", "秦皇岛市抚宁县驻操营营业厅", "秦皇岛市抚宁县石门寨营业厅", "秦皇岛市抚宁县杜庄营业厅",
		"秦皇岛市抚宁县牛头崖营业厅", "秦皇岛市抚宁县留守营营业厅", "秦皇岛市抚宁县曹东庄营业厅");
var qinglongyingyeting = new Array("秦皇岛市青龙县燕山路营业厅", "秦皇岛市青龙县马圈子营业厅",
		"秦皇岛市青龙县土门子营业厅", "秦皇岛市青龙县大巫岚营业厅", "秦皇岛市青龙县木头凳营业厅", "秦皇岛市青龙县祖山营业厅",
		"秦皇岛市青龙县双山子营业厅", "秦皇岛市青龙县隔河头营业厅", "秦皇岛市青龙县八道河营业厅", "秦皇岛市青龙县肖营子营业厅",
		"秦皇岛市青龙县凉水河营业厅");

var searchallocationparam={shixianqu:"",
		jutiyingyeting:"",
		dianhuayuyueshijianlo:"",
		dianhuayuyueshijianhi:"",
		yonghuhaoma:"",
		yudaotingbanlishijianlo:"",
		yudaotingbanlishijianhi:"",
		yifankui:"",
		maxresult:15,
		firstresult:0};
/**
 * 反馈列表模板。*/
var allocationlistbodytemplate='<tr>'+
'<td><button class="btn btn-link btn-block">{{:paidanid}}</button></td>'+
'<td>{{:paidanrenyuansuoshubanzu}}</td>'+
'<td>{{:paidanrenyuan}}</td>'+
'<td>{{:shixianqu}}</td>'+
'<td>{{:jutiyingyeting}}</td>'+
'<td>{{:dianhuayuyueshijian}}</td>'+
'<td>{{:yonghuhaoma}}</td>'+
'<td>{{:yonghuxingming}}</td>'+
'<td>{{:xianshiyongzifei}}</td>'+
'<td>{{:huiyuandengji}}</td>'+
'<td>{{:tuijianchanpin}}</td>'+
'<td>{{:yudaotingbanlishijian}}</td>'+
'<td>{{:kehujingli}}</td>'+
'<td>{{:duanxinbianma}}</td>'+
'<td>{{:waihubeizhu}}</td>'+
'<td>{{:yonghushifoudaoting}}</td>'+
'<td>{{:kehushifoubanliyuyueyewu}}</td>'+
'<td>{{:yonghuchenggongbanliyewu}}</td>'+
'<td>{{:banlishijian}}</td>'+
'<td>{{:caozuoyuanxingming}}</td>'+
'<td>{{:yonghububanliyuanyin}}</td>'+
'<td>{{:duanxinbianmabiaowaikehu}}</td>'+
'<td>{{:yingyebeizhu}}</td>'+
'</tr>';

/**
 * 创建具体营业厅的select标签。*/
function createselect(arraydata){
	var optionstr="<option></option>";
	for(var i=0;i<arraydata.length;i++){
		optionstr=optionstr+"<option>"+arraydata[i]+"</option>";
	}
	$("#jutiyingyeting_select").html(optionstr);
}

/**
 * 当区县select发生变化时，更改具体营业厅的select标签。*/
function bindselectchange(){
	$("#shixianqu_select").bind("change",function(){
		var selectedval=$(this).val();
		switch(selectedval){
		case "市区营业":
			createselect(shiquyingyeting);
			break;
		case "昌黎":
			createselect(changliyingyeting);
			break;
		case "卢龙":
			createselect(lulongyingyeting);
			break;
		case "山海关":
			createselect(shanhaiguanyingyeting);
			break;
		case "北戴河":
			createselect(beidaiheyingyeting);
			break;
		case "抚宁":
			createselect(funingyingyeting);
			break;
		case "青龙":
			createselect(qinglongyingyeting);
			break;
		default:
			break;
		}
	});
}
/**
 * 根据json格式的数据生成派单信息tr字符串。*/
function generateallocationlisttr(allocationlist){
	var Allocationlistbodytemplate = $.templates(allocationlistbodytemplate);
	var allocationlisttr="";
	for(listkey in allocationlist){
		var allocation=allocationlist[listkey];
		allocationlisttr=allocationlisttr+Allocationlistbodytemplate.render(allocation);
	}
	return allocationlisttr;
}

function setsearchallocationparam(){
	searchallocationparam.shixianqu=$("#shixianqu_select").val();
	searchallocationparam.jutiyingyeting=$("#jutiyingyeting_select").val();
	searchallocationparam.dianhuayuyueshijianlo=$("#dianhuayuyueshijianlo_input").val();
	searchallocationparam.dianhuayuyueshijianhi=$("#dianhuayuyueshijianhi_input").val();
	searchallocationparam.yonghuhaoma=$("#yonghuhaoma_input").val();
	searchallocationparam.yudaotingbanlishijianlo=$("#yudaotingbanlishijianlo_input").val();
	searchallocationparam.yudaotingbanlishijianhi=$("#yudaotingbanlishijianhi_input").val();
	searchallocationparam.yifankui=$("#yifankui_select").val();
	searchallocationparam.firstresult=0;
}
/**
 * 获取查询结果数量。*/
function getAllocationscount(){
	$.ajax({
		cache:false,
		async:true,
		url : rootpath+"/loginuser/default/getAllocationscount.do",
		data : searchallocationparam,
		type : "GET",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				data=$.parseJSON(data);
				initpagination($(".pagination"),getAllocations,searchallocationparam,data);
				$("#statistics_container").html("找到"+data+"条");
			}
		}
	});
}
/**
 * 按条件查询派单，在table中显示查询结果。*/
function getAllocations(){
	$.ajax({
		cache:false,
		async:true,
		url : rootpath+"/loginuser/default/getAllocations.do",
		data :searchallocationparam,
		type : "GET",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				data=$.parseJSON(data);
				var tabletrs=generateallocationlisttr(data);
				$("#allocation_table").find("tbody").html(tabletrs);
				bindtdbuttonclick();
			}
		}
	});
}
/**
 * 把派单信息导出Excel*/
function extractallocation(){
	$.ajax({
		cache:false,
		async:true,
		url : rootpath+"/loginuser/default/extractAllocation.do",
		data :searchallocationparam,
		type : "GET",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				download(data);
			}
		}
	});
}

function bindtdbuttonclick(){
	if(window.location.href.indexOf("/loginuser/business/")!=-1){
		$("#allocation_table").children("tbody").find("button").each(function(){
			var buttonobj=$(this);
			buttonobj.bind("click",function(){
				window.open("feedbackallocation.html?Paidanid="+buttonobj.text()+"&loginname="+Loginname);
			});
		});
	}else if(window.location.href.indexOf("/loginuser/manager/")!=-1){
		$("#allocation_table").children("tbody").find("button").each(function(){
			var buttonobj=$(this);
			buttonobj.bind("click",function(){
				window.open("allocation.html?Paidanid="+buttonobj.text()+"&loginname="+Loginname);
			});
		});
	}else{
		
	}
}

function bindbuttonclick(){
	$("#cancel_button").bind("click",function(){
		clearinput();
	});
	$("#searchallocation_button").bind("click",function(){
		setsearchallocationparam();
		getAllocationscount();
		getAllocations();

	});
	$("#extractallocation_button").bind("click",function(){
		setsearchallocationparam();
		extractallocation();
	});
}
/**
 * 初始化日期选择器。*/
function initdatetimepicker(){
	$("#dianhuayuyueshijianlo_input").datetimepicker({
		autoclose: true,
		format: 'yyyy-mm-dd',
		minView: 'month',
		language: 'zh-CN'
	});
	$("#dianhuayuyueshijianhi_input").datetimepicker({
		autoclose: true,
		format: 'yyyy-mm-dd',
		minView: 'month',
		language: 'zh-CN'
	});
	$("#yudaotingbanlishijianlo_input").datetimepicker({
		autoclose: true,
		format: 'yyyy-mm-dd',
		minView: 'month',
		language: 'zh-CN'
	});
	$("#yudaotingbanlishijianhi_input").datetimepicker({
		autoclose: true,
		format: 'yyyy-mm-dd',
		minView: 'month',
		language: 'zh-CN'
	});
}
$(function(){
	bindselectchange();
	initdatetimepicker();
	bindbuttonclick();
});