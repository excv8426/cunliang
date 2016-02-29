/**
 * 必须填写的input id*/
var requiredid=["yonghushifoudaoting_input","kehushifoubanliyuyueyewu_input","yonghuchenggongbanliyewu_input","banlishijian_input","caozuoyuanxingming_input"];
var Paidanid="";
var feedbackallocationparam={
		paidanid:"",
		yonghushifoudaoting:"",
		kehushifoubanliyuyueyewu:"",
		yonghuchenggongbanliyewu:"",
		banlishijian:"",
		caozuoyuanxingming:"",
		yonghububanliyuanyin:"",
		duanxinbianmabiaowaikehu:"",
		yingyebeizhu:"",
		yifankui:"是"};
/**
 * 初始化电话号码参数。*/
function paraminit(){
	var args = new Object();
	args = GetUrlParms();
	Paidanid=args["Paidanid"];
	feedbackallocationparam.paidanid=Paidanid;
}
/**
 * 根据派单id查询派单信息，初始化派单反馈表单。*/
function inputinit(){
	$.ajax({
		cache:false,
		async:true,
		url : rootpath+"/loginuser/default/getAllocations.do",
		data :{paidanid:Paidanid},
		type : "GET",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				data=$.parseJSON(data);
				var allocation=data[0];
				$("#paidanrenyuansuoshubanzu_input").val(allocation.paidanrenyuansuoshubanzu);
				$("#paidanrenyuan_input").val(allocation.paidanrenyuan);
				$("#shixianqu_input").val(allocation.shixianqu);
				$("#jutiyingyeting_input").val(allocation.jutiyingyeting);
				$("#yonghuhaoma_input").val(allocation.yonghuhaoma);
				$("#yonghuxingming_input").val(allocation.yonghuxingming);
				$("#xianshiyongzifei_input").val(allocation.xianshiyongzifei);
				$("#huiyuandengji_input").val(allocation.huiyuandengji);
				$("#tuijianchanpin_input").val(allocation.tuijianchanpin);
				$("#kehujingli_input").val(allocation.kehujingli);
				$("#duanxinbianma_input").val(allocation.duanxinbianma);
				$("#waihubeizhu_input").val(allocation.waihubeizhu);
				$("#dianhuayuyueshijian_input").val(allocation.dianhuayuyueshijian);
				$("#yudaotingbanlishijian_input").val(allocation.yudaotingbanlishijian);
				
				$("#yonghushifoudaoting_input").val(allocation.yonghushifoudaoting);
				$("#kehushifoubanliyuyueyewu_input").val(allocation.kehushifoubanliyuyueyewu);
				$("#yonghuchenggongbanliyewu_input").val(allocation.yonghuchenggongbanliyewu);
				$("#banlishijian_input").val(allocation.banlishijian);
				$("#caozuoyuanxingming_input").val(allocation.caozuoyuanxingming);
				$("#yonghububanliyuanyin_input").val(allocation.yonghububanliyuanyin);
				$("#duanxinbianmabiaowaikehu_input").val(allocation.duanxinbianmabiaowaikehu);
				$("#yingyebeizhu_input").val(allocation.yingyebeizhu);
				checkmissinginput();
			}
		}
	});
}
/**
 * 添加或修改派单反馈。*/
function feedbackallocation(){
	if(checkmissinginput()==0){
		$.ajax({
			cache:false,
			async:true,
			url : rootpath+"/loginuser/default/feedbackAllocation.do",
			data :feedbackallocationparam,
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
							$("#myModal").find(".modal-body").find("p").html("派单反馈成功。");
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
 * 绑定按钮点击事件。*/
function buttonclick(){
	$("#cancel_button").bind("click",function(){
		clearinput();
	});
	$("#feedbackallocation_button").bind("click",function(){
		temporarydisable($(this));
		feedbackallocationparam.yonghushifoudaoting=$("#yonghushifoudaoting_input").val();
		feedbackallocationparam.kehushifoubanliyuyueyewu=$("#kehushifoubanliyuyueyewu_input").val();
		feedbackallocationparam.yonghuchenggongbanliyewu=$("#yonghuchenggongbanliyewu_input").val();
		feedbackallocationparam.banlishijian=$("#banlishijian_input").val();
		feedbackallocationparam.caozuoyuanxingming=$("#caozuoyuanxingming_input").val();
		feedbackallocationparam.yonghububanliyuanyin=$("#yonghububanliyuanyin_input").val();
		feedbackallocationparam.duanxinbianmabiaowaikehu=$("#duanxinbianmabiaowaikehu_input").val();
		feedbackallocationparam.yingyebeizhu=$("#yingyebeizhu_input").val();
		feedbackallocation();
	});
}

/**
 * 检出未填项，返回未填项数目。*/
function checkmissinginput(){
	var missingcount=0;
	for(var i=0;i<requiredid.length;i++){
		if($.trim($("#"+requiredid[i]).val())==""){
			$("#"+requiredid[i]).tooltip('show');
			missingcount++;
		}else{
			$("#"+requiredid[i]).tooltip('hide');
		}
	}
	return missingcount;
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
/**
 * 初始化必填输入提示。*/
function inittooltip(){
	$("#yonghushifoudaoting_input").tooltip({
		placement:"right",
		title:"必填",
		trigger:"manual"
	});
	$("#kehushifoubanliyuyueyewu_input").tooltip({
		placement:"right",
		title:"必填",
		trigger:"manual"
	});
	$("#yonghuchenggongbanliyewu_input").tooltip({
		placement:"right",
		title:"必填",
		trigger:"manual"
	});
	$("#banlishijian_input").tooltip({
		placement:"right",
		title:"必填",
		trigger:"manual"
	});
	$("#caozuoyuanxingming_input").tooltip({
		placement:"right",
		title:"必填",
		trigger:"manual"
	});
}
/**
 * 初始化日期选择器。*/
function initdatetimepicker(){
	$("#banlishijian_input").datetimepicker({
		autoclose: true,
		format: 'yyyy-mm-dd',
		minView: 'month',
		language: 'zh-CN'
	});
}
$(function(){
	paraminit();
	inputinit();
	initdatetimepicker();
	buttonclick();
	inittooltip();
	bindinputblur();
});