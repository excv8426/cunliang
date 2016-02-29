var yujingcelvetemplate='<tr>'+
'<td style="display:none">{{:yujingcelveid}}</td>'+
'<td>{{:yujingcelve}}</td>'+
'<td><button class="btn btn-link btn-block">删除</button></td>'+
'</tr>';
var bubanliyuanyintemplate='<tr>'+
'<td style="display:none">{{:bubanliyuanyinid}}</td>'+
'<td>{{:bubanliyuanyin}}</td>'+
'<td><button class="btn btn-link btn-block">删除</button></td>'+
'</tr>';
function getyujingcelve(){
	var yujingcelvetable="";
	$.ajax({
		cache:false,
		async:false,
		url : rootpath+"/loginuser/default/getYujingcelves.do",
		type : "GET",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				if(!hasexception(data)){
					data=$.parseJSON(data);
					var Yujingcelvetemplate=$.templates(yujingcelvetemplate);
					var yujingcelve=null;
					for(var listkey in data){
						yujingcelve=data[listkey];
						yujingcelvetable=yujingcelvetable+Yujingcelvetemplate.render(yujingcelve);
					}
					$("#yujingcelve_table").find("tbody").html(yujingcelvetable);
					binddeleteyujingcelve();
				}
			}
		}
	});
}
function getbubanliyuanyin(){
	var bubanliyuanyintable="";
	$.ajax({
		cache:false,
		async:false,
		url : rootpath+"/loginuser/default/getBubanliyuanyins.do",
		type : "GET",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				if(!hasexception(data)){
					data=$.parseJSON(data);
					var Bubanliyuanyintemplate=$.templates(bubanliyuanyintemplate);
					var bubanliyuanyin=null;
					for(var listkey in data){
						bubanliyuanyin=data[listkey];
						bubanliyuanyintable=bubanliyuanyintable+Bubanliyuanyintemplate.render(bubanliyuanyin);
					}
					$("#bubanliyuanyin_table").find("tbody").html(bubanliyuanyintable);
					binddeletebubanliyuanyin();
				}
			}
		}
	});
}
function deleteyujingcelve(yujingcelve){
	$.ajax({
		cache:false,
		async:false,
		url : rootpath+"/loginuser/system/deleteYujingcelve.do",
		data : yujingcelve,
		type : "POST",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				if(!hasexception(data)){
					getyujingcelve();
				}
			}
		}
	});
}
function deletebubanliyuanyin(bubanliyuanyin){
	$.ajax({
		cache:false,
		async:false,
		url : rootpath+"/loginuser/system/deleteBubanliyuanyin.do",
		data : bubanliyuanyin,
		type : "POST",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				if(!hasexception(data)){
					getbubanliyuanyin();
				}
			}
		}
	});
}
function addyujingcelve(yujingcelve){
	$.ajax({
		cache:false,
		async:false,
		url : rootpath+"/loginuser/system/addYujingcelve.do",
		data : yujingcelve,
		type : "POST",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				if(!hasexception(data)){
					getyujingcelve();
				}
			}
		}
	});
}
function addbubanliyuanyin(bubanliyuanyin){
	$.ajax({
		cache:false,
		async:false,
		url : rootpath+"/loginuser/system/addBubanliyuanyin.do",
		data : bubanliyuanyin,
		type : "POST",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				if(!hasexception(data)){
					getbubanliyuanyin();
				}
			}
		}
	});
}
function binddeleteyujingcelve(){
	$("#yujingcelve_table").find("tbody").find("tr").each(function(){
		var tr=$(this);
		tr.children().last().bind("click",function(){
			var yujingcelve={yujingcelveid:tr.children().first().text()};
			var c=confirm("将删除"+tr.children().eq(1).text());
			if(c){
				deleteyujingcelve(yujingcelve);
			}
		});
	});
}
function binddeletebubanliyuanyin(){
	$("#bubanliyuanyin_table").find("tbody").find("tr").each(function(){
		var tr=$(this);
		tr.children().last().bind("click",function(){
			var bubanliyuanyin={bubanliyuanyinid:tr.children().first().text()};
			var c=confirm("将删除"+tr.children().eq(1).text());
			if(c){
				deletebubanliyuanyin(bubanliyuanyin);
			}
		});
	});
}
function bindbuttonclick(){
	$("#addyujingcelve_button").bind("click",function(){
		temporarydisable($(this));
		var yujingcelve={yujingcelve:$("#yujingcelve_input").val()};
		addyujingcelve(yujingcelve);
	});
	$("#addbubanliyuanyin_button").bind("click",function(){
		temporarydisable($(this));
		var bubanliyuanyin={bubanliyuanyin:$("#bubanliyuanyin_input").val()};
		addbubanliyuanyin(bubanliyuanyin);
	});
}
$(function(){
	getyujingcelve();
	getbubanliyuanyin();
	bindbuttonclick();
});