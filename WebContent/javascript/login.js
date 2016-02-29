/**
 * 登录参数，用户名和密码。*/
var loginuser = {
	loginname : "",
	password : ""
};
/**
 * 设置登录参数。*/
function setloginuser() {
	loginuser.loginname = $("#loginname_input").val();
	loginuser.password = $("#password_input").val();
}
/**
 * 用户登录，并且跳转页面。*/
function userlogin() {
	$.ajax({
		cache : false,
		async : true,
		url : "checkLoginuser.do",
		data : loginuser,
		type : "GET",
		dataType : "json",
		success : function(data) {
			if (data.error == "fail") {
				$("#myModal").find(".modal-body").find("p").html("用户名或密码不正确。");
				$('#myModal').modal();
			} else if(data.error == "locked"){
				$("#myModal").find(".modal-body").find("p").html("密码连续三次错误，账户锁定一天。");
				$('#myModal').modal();
			} else {
				switch (data.authority) {
				case "0":
					window.location = "loginuser/system/addloginuser.html?loginname="+loginuser.loginname;
					break;
				case "1":
					window.location = "loginuser/admin/searchclient.html?loginname="+loginuser.loginname;
					break;
				case "2":
					window.location = "loginuser/manager/searchclient.html?loginname="+loginuser.loginname;
					break;
				case "3":
					window.location = "loginuser/business/searchallocation.html?loginname="+loginuser.loginname;
					break;
				default:
					break; 
				}
			}
		}
	});
}
$(function() {
	$("#userlogin_button").bind("click", function() {
		setloginuser();
		userlogin();
	});
	$("#password_input").keydown(function(event){
		if(event.which=="13"){
			setloginuser();
			userlogin();
		}
	});
});