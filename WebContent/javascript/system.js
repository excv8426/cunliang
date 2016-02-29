/**
 * 用户列表模板。*/
var loginusertemplate='<tr>'+
    '<td>{{:loginname}}</td>'+
    '<td>{{:personname}}</td>'+
    '<td>{{:authority}}</td>'+
    '</tr>';
var searchloginuserparam={maxresult:15,
		firstresult:0};
/**
 * 添加单个用户。*/
function addloginuser(){
	$.ajax({
		cache:false,
		async:true,
		url : "addLoginuser.do",
		data : {loginname:$("#loginname_input").val(),password:$("#password_input").val(),authority:$("#authority_select").find("option:selected").attr("id"),personname:$("#personname_input").val()},
		type : "POST",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				data=$.parseJSON(data);
				if((data.exception!=null)||(data.exception!=undefined)){
					$("#myModal").find(".modal-body").find("p").html(data.exception);
				}else if(data.error=="nullproperty"){
					$("#myModal").find(".modal-body").find("p").html("信息填写不全。");
				}else{
					$("#myModal").find(".modal-body").find("p").html("成功添加了1个用户。");
				}
				refreshloginusertable();
				$('#myModal').modal();
			}
		}
	});
}
function refreshloginusertable(){
	searchloginuserparam.firstresult=0;
	getloginuserscount();
	getloginusers();
}
/**
 * 初始化上传文件插件。*/
function pluploadinit(){
    var uploader=null; //初始化
    uploader = new plupload.Uploader({
    	runtimes : 'html5,flash,html4',
    	browse_button: $("#uploadexcel_button").get(0),
    	flash_swf_url : '../../plupload-2.1.8/js/Moxie.swf',
    	filters:{
    		mime_types : [
    		              { title : "Excel files", extensions : "xls,xlsx" }
    		],
    		max_file_size: "200mb",
    		prevent_duplicates: true
    	},
    	url: 'addLoginusers.do',
    	init: {
    		FilesAdded : function (up, files){
    			uploader.start();
    		},
			Error: function(up, err) {
				alert(err.message);
			},
		    FileUploaded:function(uploader,file,response){
		    	if(hasauthority(response.response)){
			    	var jsonobj=$.parseJSON(response.response);
					if((jsonobj.exception!=null)||(jsonobj.exception!=undefined)){
						$("#myModal").find(".modal-body").find("p").html(jsonobj.exception);
					}else{
						$("#myModal").find(".modal-body").find("p").html("成功添加了"+jsonobj.success+"个用户。");
					}
					$('#myModal').modal();
		    	}

			},
		    UploadComplete:function(){
		    	refreshloginusertable();
			}
    	}
    });
    uploader.init();	   
}
/**
 * 获取所有用户。*/
function getloginusers(){
	$.ajax({
		cache:false,
		async:true,
		data :searchloginuserparam,
		url : "getLoginusers.do",
		type : "GET",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				data=$.parseJSON(data);
				var tabletrs=generateuserlisttr(data);
				$("#loginuser_table").find("tbody").html(tabletrs);
			}
		}
	});
}
function getloginuserscount(){
	$.ajax({
		cache:false,
		async:true,
		url : "getLoginuserscount.do",
		type : "GET",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				data=$.parseJSON(data);
				initpagination($(".pagination"),getloginusers,searchloginuserparam,data);
			}
		}
	});
}
/**
 * 生成用户列表字符串。*/
function generateuserlisttr(loginuserlist){
	var Loginusertemplate = $.templates(loginusertemplate);
	var loginuserlisttr="";
	for(listkey in loginuserlist){
		var loginuser=loginuserlist[listkey];
		switch(loginuser.authority){
		case "0":
			loginuser.authority="system";
			break;
		case "1":
			loginuser.authority="管理员";
			break;
		case "2":
			loginuser.authority="外呼经理";
			break;
		case "3":
			loginuser.authority="营业厅派单";
			break;
		default:
			break;
		}
		loginuserlisttr=loginuserlisttr+Loginusertemplate.render(loginuser);
	}
	return loginuserlisttr;
}
$(function(){
	pluploadinit();
	refreshloginusertable();
	$("#addloginuser_button").bind("click",function(){
		temporarydisable($(this));
		addloginuser();
	});
});