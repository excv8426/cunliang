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

var allocationparam={paidanid:null,
		paidanrenyuansuoshubanzu:"",
		paidanrenyuan:"",
		shixianqu:"",
		jutiyingyeting:"",
		dianhuayuyueshijian:"",
		yonghuhaoma:"",
		yonghuxingming:"",
		xianshiyongzifei:"",
		huiyuandengji:"",
		tuijianchanpin:"",
		yudaotingbanlishijian:"",
		kehujingli:"",
		duanxinbianma:"",
		waihubeizhu:"",
		yifankui:"否"};

function initallocationparam(){
	var args = new Object();
	args = GetUrlParms();
	if((args["Paidanid"]!=null)&&(args["Paidanid"]!=undefined)){
		allocationparam.paidanid=args["Paidanid"];
	}
}

function initallocationinput(){
	if(allocationparam.paidanid!=null){
		$.ajax({
			cache:false,
			async:true,
			url : rootpath+"/loginuser/default/getAllocations.do",
			data : {paidanid:allocationparam.paidanid},
			type : "GET",
			dataType : "text",
			success : function(data) {
				if(hasauthority(data)){
					var allocationjson=$.parseJSON(data)[0];
					$("#paidanrenyuansuoshubanzu_input").val(allocationjson.paidanrenyuansuoshubanzu);
					$("#paidanrenyuan_input").val(allocationjson.paidanrenyuan);
					$("#shixianqu_select").val(allocationjson.shixianqu);
					createselect(allocationjson.shixianqu);
					$("#jutiyingyeting_select").val(allocationjson.jutiyingyeting);
					$("#yonghuhaoma_input").val(allocationjson.yonghuhaoma);
					$("#yonghuxingming_input").val(allocationjson.yonghuxingming);
					$("#xianshiyongzifei_select").val(allocationjson.xianshiyongzifei);
					$("#huiyuandengji_select").val(allocationjson.huiyuandengji);
					$("#tuijianchanpin_input").val(allocationjson.tuijianchanpin);
					$("#kehujingli_input").val(allocationjson.kehujingli);
					$("#duanxinbianma_input").val(allocationjson.duanxinbianma);
					$("#waihubeizhu_input").val(allocationjson.waihubeizhu);
					$("#dianhuayuyueshijian_input").val(allocationjson.dianhuayuyueshijian);
					$("#yudaotingbanlishijian_input").val(allocationjson.yudaotingbanlishijian);
				}
			}
		});
	}
}

/**
 * 创建具体营业厅的select标签。*/
function createselect(selectedval){
	var arraydata=null;
	switch(selectedval){
	case "市区营业":
		arraydata=shiquyingyeting;
		break;
	case "昌黎":
		arraydata=changliyingyeting;
		break;
	case "卢龙":
		arraydata=lulongyingyeting;
		break;
	case "山海关":
		arraydata=shanhaiguanyingyeting;
		break;
	case "北戴河":
		arraydata=beidaiheyingyeting;
		break;
	case "抚宁":
		arraydata=funingyingyeting;
		break;
	case "青龙":
		arraydata=qinglongyingyeting;
		break;
	default:
		break;
	}
	
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
		createselect(selectedval);
	});
}
function setallocationparam(){
	allocationparam.paidanrenyuansuoshubanzu=$("#paidanrenyuansuoshubanzu_input").val();
	allocationparam.paidanrenyuan=$("#paidanrenyuan_input").val();
	allocationparam.shixianqu=$("#shixianqu_select").val();
	allocationparam.jutiyingyeting=$("#jutiyingyeting_select").val();
	allocationparam.yonghuhaoma=$("#yonghuhaoma_input").val();
	allocationparam.yonghuxingming=$("#yonghuxingming_input").val();
	allocationparam.xianshiyongzifei=$("#xianshiyongzifei_select").val();
	allocationparam.huiyuandengji=$("#huiyuandengji_select").val();
	allocationparam.tuijianchanpin=$("#tuijianchanpin_input").val();
	allocationparam.kehujingli=$("#kehujingli_input").val();
	allocationparam.duanxinbianma=$("#duanxinbianma_input").val();
	allocationparam.waihubeizhu=$("#waihubeizhu_input").val();
	allocationparam.dianhuayuyueshijian=$("#dianhuayuyueshijian_input").val();
	allocationparam.yudaotingbanlishijian=$("#yudaotingbanlishijian_input").val();
}
function addallocation(){
	setallocationparam();
	$.ajax({
		cache:false,
		async:true,
		url : rootpath+"/loginuser/default/saveorupdateAllocation.do",
		data : allocationparam,
		type : "POST",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				data=$.parseJSON(data);
				if((data.exception!=null)||(data.exception!=undefined)){
					$("#myModal").find(".modal-body").find("p").html(data.exception);
				}else{
					if((data.error!=null)||(data.error!=undefined)){
						$("#myModal").find(".modal-body").find("p").html("此派单已被反馈过，不能修改。");
					}else{
						$("#myModal").find(".modal-body").find("p").html("派单成功。");
					}
				}
				$('#myModal').modal();
			}
		}
	});
}
function bindbuttonclick(){
	$("#addallocation_button").bind("click",function(){
		temporarydisable($(this));
		addallocation();
	});
	$("#cancel_button").bind("click",function(){
		clearinput();
	});
}
/**
 * 初始化日期选择器。*/
function initdatetimepicker(){
	$("#dianhuayuyueshijian_input").datetimepicker({
		autoclose: true,
		format: 'yyyy-mm-dd',
		minView: 'month',
		language: 'zh-CN'
	});
	$("#yudaotingbanlishijian_input").datetimepicker({
		autoclose: true,
		format: 'yyyy-mm-dd',
		minView: 'month',
		language: 'zh-CN'
	});
}
$(function(){
	bindselectchange();
	initallocationparam();
	initallocationinput();
	initdatetimepicker();
	bindbuttonclick();	
	$("#myModal").on('hide',function(){
		if(allocationparam.paidanid!=null){
			window.location.href="allocation.html?loginname="+Loginname;
		}
	});
});