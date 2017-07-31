var PARENT_TABLE_DATA = "";
var PARENT_CURRENT_PAGE = 0;
$(function(){
	$("#tab_menu0").click();
});
function func_lct(sId,sHref){ 
	var rowCount = $("#rowCount").val();
	for(var i=0;i<rowCount;i++){
		if(i == sId){
			$("#tab_menu"+sId).attr("class","end");
		}else{
			if($("#tab_menu"+i).attr("class") != ""){
				$("#tab_menu"+i).attr("class","");
			} 
		}
	}
	if(sHref.indexOf("http://")== -1)
		sHref = basePath+ "/framework/"+sHref;
	$(moduleMain.location).attr("href", sHref);
}

function menu_click(id){
	if($("#menu"+id).is(":hidden")){
		$("#menu"+id).show();
	}else{
		$("#menu"+id).hide();
	}
}

function returnSub(){
	location.reload();
}