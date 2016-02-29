var clienttrtemplate='<tr><td>{{:waihucelve}}</button><td>{{:waihucelvecount}}</td><td><button class="btn btn-link btn-block">删除</button></td></tr>';
	function deletebywaihucelve(celve){
		var c=confirm("将删除外呼策略为"+celve+"的客户信息。");
		if(c){
			$.ajax({
				cache:false,
				async:true,
				url : "deletebywaihucelve.do",
				type : "POST",
				data : {waihucelve:celve},
				dataType : "text",
				success : function(data) {
					if(hasauthority(data)){
						data=$.parseJSON(data);
						if((data.exception!=null)||(data.exception!=undefined)){
							$("#myModal").find(".modal-body").find("p").html(data.exception);
						}else{
							$("#myModal").find(".modal-body").find("p").html("删除了"+data+"条客户信息。");
						}
						$('#myModal').modal();
						getgroupbywaihucelve();
					}
				}
			});
		}
	}
	function getgroupbywaihucelve(){
		$.ajax({
			cache:false,
			async:true,
			url : "getgroupbywaihucelve.do",
			type : "GET",
			dataType : "text",
			success : function(data) {
				if(hasauthority(data)){
					data=$.parseJSON(data);
					if((data.exception!=null)||(data.exception!=undefined)){
						$("#myModal").find(".modal-body").find("p").html(data.exception);
						$('#myModal').modal();
					}else{
						generateclienttable(data);
						bindwaihucelveclick();
					}
					
				}
			}
		});
	}
	function generateclienttable(data){
		var Clienttrtemplate=$.templates(clienttrtemplate);
		var clientstr="";
		for(var listkey in data){
			clientstr=clientstr+Clienttrtemplate.render(data[listkey]);
		}
		$("#client_table").find("tbody").html(clientstr);
	}
	function bindwaihucelveclick(){
		$("#client_table").find("tbody").find("tr").each(function(){
			var waihucelvebutton=$(this).find("button");
			var waihucelve=$(this).children().eq(0).text();
			waihucelvebutton.bind("click",function(){
				deletebywaihucelve(waihucelve);
			});
		});
	}
function reorganizediscounts(){
	$.ajax({
		cache:false,
		url : "reorganizeDiscounts.do",
		type : "POST",
		dataType : "text",
		success : function(data) {
			if(hasauthority(data)){
				data=$.parseJSON(data);
				if((data.exception!=null)||(data.exception!=undefined)){
					$("#myModal").find(".modal-body").find("p").html(data.exception);
					$('#myModal').modal();
				}else{
					updatediscountrelation();
				}
				
			}
		}
	});
}
function updatediscountrelation(){
	$.ajax({
		cache:false,
		url : "updateDiscountrelation.do",
		type : "POST",
		dataType : "text",
		success : function(data) {
			$("#reorganizediscounts_button").prop("disabled",false);
			if(hasauthority(data)){
				data=$.parseJSON(data);
				if((data.exception!=null)||(data.exception!=undefined)){
					$("#myModal").find(".modal-body").find("p").html(data.exception);
					$('#myModal').modal();
				}else{
					$("#myModal").find(".modal-body").find("p").html("整理完成。");
					$('#myModal').modal();
				}
				
			}
		}
	});
}
	$(function(){
		$("#reorganizediscounts_button").bind("click",function(){
			$("#reorganizediscounts_button").prop("disabled",true);
			reorganizediscounts();
		});
		getgroupbywaihucelve();
	});