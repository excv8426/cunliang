var rootpath="";
var Loginname="";
/**
 * 获取url中的参数*/
function GetUrlParms(){
    var args=new Object();   
    var query=location.search.substring(1);//获取查询串   
    var pairs=query.split("&");//在逗号处断开   
    for(var i=0;i<pairs.length;i++){
    	var pos=pairs[i].indexOf('=');//查找name=value
    	if(pos==-1){
    		continue;//如果没有找到就跳过
    	}
        var argname=pairs[i].substring(0,pos);//提取name   
        var value=pairs[i].substring(pos+1);//提取value   
        args[argname]=unescape(value);//存为属性 
    }
    return args;
}
/**
 * 获取服务器ip、端口和项目名称。*/
function getRootPath() {
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath = window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    //获取带"/"的项目名，如：/uimcardprj
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return localhostPaht+projectName;
}
/**
 * 初始化用户名。*/
function initloginuser(){
	var args = new Object();
	args = GetUrlParms();
	Loginname=args["loginname"];
	$(".navbar-text.pull-right").find(".navbar-link").text(Loginname);
	$(".nav").find("a").each(function(){
		var aobj=$(this);
		if(aobj.prop("href")!="#"){
			aobj.prop("href",aobj.prop("href")+"?loginname="+Loginname);
		}
	});
	var homepage_a=$("#homepage_a");
	if(homepage_a.prop("href")!="#"){
		homepage_a.prop("href",homepage_a.prop("href")+"?loginname="+Loginname);
	}
}
/**
 * 清除输入框。*/
function clearinput(){
	$("input").each(function(){
		var inputobj=$(this);
		if(!inputobj.is(":disabled")){
			inputobj.val("");
		}
	});
	$("select").each(function(){
		var selectobj=$(this);
		if(!selectobj.is(":disabled")){
			selectobj.val("");
		}
	});
}

/**
 * 利用隐藏的iframe实现下载文件。*/
function download(url){
	$("body").append('<iframe style="display:none" src="'+rootpath+url+'"></iframe>');
}
/**
 * 从响应数据判断请求是否有权限。*/
function hasauthority(data){
	if(data.substring(0,8)=="<script>"){
		$("body").append(data);
		return false;
	}else{
		return true;
	}
}
/**
 * 从响应数据判断请求是发生异常。*/
function hasexception(textdata){
	var r=false;
	var data=null;
	try{
		data=$.parseJSON(textdata);
		if((data.exception!=null)||(data.exception!=undefined)){
			r=true;
			$("#myModal").find(".modal-body").find("p").html(data.exception);
			$('#myModal').modal();
		}
	}catch(err){
		console.log(err);
	}
	return r;
}
/**
 * 从数组arrayobj中，移除元素element。*/
function removearrayelement(arrayobj,element){
	for(var i=0;i<arrayobj.length;i++){
		if(arrayobj[i]==element){
			arrayobj.splice(i,1);
		}
	}
}
/**
 * 判断数组arrayobj中，是否包含元素element。*/
function arraycontain(arrayobj,element){
	var contain=false;
	for(var i=0;i<arrayobj.length;i++){
		if(arrayobj[i]==element){
			contain=true;
			break;
		}
	}
	return contain;
}
/**
 * 清空数组。*/
function cleararray(arrayobj){
	while(arrayobj.length>0){
		arrayobj.pop();
	}
}
function temporarydisable(obj){
	obj.prop("disabled",true);
	setTimeout(function(){
		obj.prop("disabled",false);
	},3000);
}
function generatedropdownlist(name){
	var url="";
	var dropdownliststr="";
	var dropdownlist=null;
	switch(name){
	case "yujingcelve":
		url="/loginuser/default/getYujingcelves.do"
		break;
	case "bubanliyuanyin":
		url="/loginuser/default/getBubanliyuanyins.do";
		break;
	default:
		break;
	}
	$.ajax({
		cache : true,
		async : false,
		url : rootpath+url,
		type : "GET",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				if(!hasexception(data)){
					data=$.parseJSON(data);
					switch(name){
					case "yujingcelve":
						for(var listkey in data){
							dropdownlist=data[listkey];
							dropdownliststr=dropdownliststr+"<option>"+dropdownlist.yujingcelve+"</option>";
						}
						break;
					case "bubanliyuanyin":
						for(var listkey in data){
							dropdownlist=data[listkey];
							dropdownliststr=dropdownliststr+"<option>"+dropdownlist.bubanliyuanyin+"</option>";
						}
						break;
					default:
						break;
					}
				}
			}
		}
	});
	return dropdownliststr;
}
function emptytonull(obj){
	for(var key in obj){
		if(obj[key]!=null){
			obj[key]=$.trim(obj[key]);
			if(obj[key]==""){
				obj[key]=null;
			}
		}
	}
}
$(function(){
	rootpath=getRootPath();
	initloginuser();
});

/**
 * 查询结果的当前页。*/
var currentpage=1;
/**
 * 查询结果的最大页数。*/
var maxpage=1;
/**
 * 每页的查询结果数量*/
var pagesize=20;
/**
 * 分页插件的容器。*/
var paginationcontainer=null;
/**
 * 查找函数的名称。*/
var searchfunction=null;
/**
 * 查询的条件。*/
var searchparam=null;
/**
 * 初始化分页插件。
 * @param container 分页插件容器。
 * @param _searchfunction 查询函数。
 * @param _searchparam 查询条件。
 * @param count 查询结果数量。*/
function initpagination(container,_searchfunction,_searchparam,count){
	paginationcontainer=container;
	searchfunction=_searchfunction;
	searchparam=_searchparam;
	pagesize=_searchparam.maxresult;
	currentpage=1;
	paginationcontainer.find("ul").html(pagination(count));
	bindpagination();
	setpaginationstatus();
}
/**
 * 生成页码字符串。*/
function pagination(pagecount){
	if(parseInt(pagecount)>parseInt(pagesize)){
		maxpage=Math.ceil(pagecount/pagesize);
	}else{
		maxpage=1;
	}
	var pagenum="1";
	var paginationstr="";
	if(maxpage>=2){
		var i=maxpage;
		while(i){
			paginationstr=paginationstr+'<li><a>'+pagenum+'</a></li>';
			pagenum++;
			i--;
		}
		paginationstr='<li><a>上一页</a></li>'+paginationstr+'<li><a>下一页</a></li>';
	}
	return paginationstr;
}
/**
 * 页码点击事件。*/
function pagenumclick(num){
	currentpage=num;
	searchparam.firstresult=pagesize*(currentpage-1);
	searchfunction();
	setpaginationstatus();
}
/**
 * 下一页点击事件。*/
function nextpageclick(){
	if(currentpage<maxpage){
		currentpage++;
	}
	searchparam.firstresult=pagesize*(currentpage-1);
	searchfunction();
	setpaginationstatus();
}
/**
 * 上一页点击事件。*/
function prevpageclick(){
	if(currentpage>1){
		currentpage--;
	}
	searchparam.firstresult=pagesize*(currentpage-1);
	searchfunction();
	setpaginationstatus();
}
/**
 * 绑定分页插件点击事件。*/
function bindpagination(){	
	var lis=paginationcontainer.find("ul").find("li");
	lis.eq(0).bind("click",function(){
		prevpageclick();
	});
	for(var i=1;i<lis.length-1;i++){
		lis.eq(i).bind("click",function(){
			pagenumclick($(this).find("a").text());
		});
	}
	lis.eq(lis.length-1).bind("click",function(){
		nextpageclick();
	});
}
/**
 * 设置页码、上一页、下一页的禁用与活动状态。*/
function setpaginationstatus(){
	var lis=paginationcontainer.find("ul").find("li");
	var lilength=lis.length;
	var num=0;
	if(lilength>0){
		lis.each(function(){
			var liobj=$(this);
			liobj.removeAttr("class");
			if((num>0)&&(num<lilength-1)){
				if(Math.abs(liobj.find("a").text()-currentpage)<=20){
					liobj.show();
				}else{
					liobj.hide();
				}
			}
			num++;
		});
		if(currentpage==1){
			lis.eq(currentpage-1).prop("class","disabled");
		}else if(currentpage==maxpage){
			lis.eq(parseInt(currentpage)+1).prop("class","disabled");
		}
		lis.eq(currentpage).prop("class","active");
	}
}
