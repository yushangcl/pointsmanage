var MENU_ID="";
var PAGE_FRAME_WIDTH = "";
String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
}
/**
 * 功能预加载
 * 
 * 调整浏览器分别率下的显示
 * 
 */
function comm_init_body(){
	/*
	var sLeftWidth = 0;
	var sLeftTitWidth = 0;
	var sTbPageWidth = 0;
	var sAcBoxWidth = 0;
	var sAcMainWidth = 0;
	var sTbPageFrameWidth = 0;
	var sIboxWidth = 0;
	var sAcInput = 0;
	var sAcSelect = 0;
	var sWidth = screen.width;
	

	if(sWidth > 1280){
	
	}else if( sWidth > 1024){
		sLeftWidth  = 863;
		sLeftTitWidth  = sLeftWidth -15;
		sTbPageWidth = sLeftWidth -10;
		
		sAcBoxWidth = sLeftTitWidth;
		sAcMainWidth = sAcBoxWidth
		
		sTbPageFrameWidth = sAcBoxWidth
		
	}else if( sWidth > 800){ 
		sLeftWidth  = 653;
		sLeftTitWidth  = sLeftWidth -15;
		sTbPageWidth = sLeftWidth -10;
		
		sAcBoxWidth = sLeftTitWidth;
		sAcMainWidth = sAcBoxWidth
		
		sTbPageFrameWidth = sAcBoxWidth;
		
		sIboxWidth = sAcBoxWidth - 260;
		sAcInput = sIboxWidth -12;
		sAcSelect = sIboxWidth + 10;
	}else{
		sLeftWidth  = 423;
		sLeftTitWidth  = sLeftWidth -15;
		sTbPageWidth = sLeftWidth -10;
		
		sAcBoxWidth = sLeftTitWidth;
		sAcMainWidth = sLeftTitWidth;
		
		sTbPageFrameWidth = sLeftTitWidth;
		
		sIboxWidth = sAcBoxWidth - 260;
		sAcInput = sIboxWidth -12;
		sAcSelect = sIboxWidth + 10;
		
	}	
	
	if(sLeftWidth != 0){
		$(".left").css("width",sLeftWidth);
		$(".left").find(".left_tit").css("width",sLeftTitWidth);
		$(".tb_main_table1").css("width",sTbPageWidth);
		$(".tb_page").css("width",sTbPageWidth);
		
		$(".ac_box").css("width",sAcBoxWidth);
		$(".ac_box").find(".top_bar").css("width",sAcBoxWidth);
		$(".ac_box").find(".ac_main").css("width",sAcMainWidth);
		
		$(".tb_page_frame").css("width",sTbPageFrameWidth);
		$(".page_frame").css("width",sTbPageFrameWidth);
		if(sIboxWidth != 0){
			$(".i_box1").css("width",sIboxWidth);
			$(".ac_input").css("width",sAcInput);
			$(".ac_select").css("width",sAcSelect);
			$(".ac_textarea").css("width",sAcInput);
		}
		PAGE_FRAME_WIDTH = sTbPageFrameWidth;
	}
	*/
	comm_init_scroll();
}

function comm_init_scroll(){
    //var sHeight = document.body.scrollHeight;
    //parent.$("#moduleMain").attr("height",sHeight);
}

/**
 * 将id的弹出层，弹出。
 * 用于，对已存在在的内容进行进行复制呈现。
 * @param id
 */
function showDialog(id, type){
	var dialogid = id + type + "dialog";
	var dialogContain = "dialogContain";
	
	if($("#" + dialogid).length > 0){
		return;
	}
	
	var html = $("#" + id).clone();
	
	html.attr("id", dialogid);
	html.attr("type", type);
	
	var contain = $("<div>");
	contain.attr("class", dialogContain);
	//确定显示的位置
	contain.css("position", "absolute");
	contain.css("z-index", "19890412");
	contain.css("display", "none");
	contain.html(html);
	$("body").append(contain);

	var oT=($(window).height()-$("#" + dialogid).height())/2;
	var oL=($(window).width()-$("#" + dialogid).width())/2;
	contain.css({"top":oT,"left":oL});
	
	
	//添加监听
	$("#" + dialogid).find(".wid_tit").find("a").click(function(){
		contain.remove();
	});
	$("#" + dialogid).find(".btn_off").click(function(){
		contain.remove();
	});
	

	$("#" + dialogid).show();
	
	contain.show();
	
	contain.draggable({ containment: "parent", handle: "div.wid_tit"});
	
	return dialogid;
}

/**
 * 想需要提示的信息展示出来
 * @param title
 */
var showDialogIndex = 0;
/**
 * 基础弹出窗口
 */
function showBaseDialog(msg, type,okMethod,infoType){
	var title = "";
	var offButton = false;
	if(type == "warn"){
		title = "警告";
	}else if(type == "error"){
		title = "错误";
	}else if(type == "off_warn"){
		offButton = true;
		title = "警告";
	}else if(type == "off_error"){
		offButton = true;
		title = "错误";
	}else if(type == "off_info"){
		offButton = true;
		title = "提示";
	}else{
		title = "提示";
	}
	if($("#infoWindow").css("display") != "block"){
	
		var msgDialogHtml = "<div id='infoWindow' class='wid_350x120'>" +
		"<div class='wid_tit'>" +
		"<p>" + title + "</p>";
		
		if(infoType == null)
			msgDialogHtml += "<a></a>";
	
		msgDialogHtml += "</div>" +
		"<div class='wid_main2'><p>" +
		msg +
		"</p></div>" +
		"<div class='wid_box_btn'>" ;
		
		if(offButton){
			msgDialogHtml += "<a href='javascript:void(0)' class='btn_ok'> </a>";
			msgDialogHtml += "<a href='javascript:void(0)' class='btn_off'> </a>";
		}else{
			msgDialogHtml += "<a href='javascript:void(0)' class='btn_ok' style='margin-left: 54px;'> </a>";
		}
		msgDialogHtml += "</div></div>";
		
		var contain = $("<div>");
		contain.attr("class", "dialogContain");
		//确定显示的位置
		contain.css("position", "absolute");
		contain.css("z-index", "19890412");
		contain.css("display", "none");
		contain.html(msgDialogHtml);
		$("body").append(contain);
		
		
		var scrollTop = document.body.scrollTop||document.documentElement.scrollTop;
		var oT= scrollTop + ($(window).height()-contain.height())/2;
		var oL=($(window).width()-contain.width())/2;
		contain.css({"top":oT,"left":oL});
		
		if(infoType == null){
			contain.find(".wid_tit").find("a").click(function(){
				contain.remove();
			});
		}
		
		if(okMethod){
			contain.find(".btn_ok").click(function(){
					okMethod();
					contain.remove();
			});
		}else{
			contain.find(".btn_ok").click(function(){
					contain.remove();
			});
		}
		contain.find(".btn_off").click(function(){
			contain.remove();
		});
		contain.show();
		
		try{
			contain.draggable({ containment: "parent", handle: "div.wid_tit"});
		}catch(ex){
		
		}
	}
}
/**
 * 提示信息
 */
function showMsgDialog(msg, type){
	showBaseDialog(msg, type,null,null);
}

/**
 * 带方法的提示信息(有关闭)
 * @param title
 */
function showOkMsgDialog(msg, type,okMethod){
	showBaseDialog(msg, type,okMethod,null);
}

/**
 * 带方法的提示信息(无关闭)
 * @param title
 */
function showOkInfoDialog(msg, type,okMethod){
	showBaseDialog(msg, type,okMethod,"info");
}

/**
 * 为了获得问下结果的dilog
 */
function showDialogFrame(okOperate, msg){
	var dialogHtml = "";
	if(msg){
		dialogHtml = "<div class='wid_box width360'>" +
				"<div class='wid_tit'>" +
				"<p>删除</p>" +
				"<a></a></div>" +
				"<div class='c'></div>" +
				"<div class='wid_main_prompt'>" +
				"<p>" + msg + "</p></div>" +
				"<div class='full_btn_box' style='padding:20px 0 '>" +
				"<ul class='btn_box'>" +
				"<li><a href='javascript:void(0)' class='btn_red btn_ok'>确 定</a></li>" +
				"<li><a href='javascript:void(0)' class='btn_orange btn_margin btn_off'>取 消</a></li>" +
				"</ul>" +
				"<div class='c'></div></div></div>";
	}else{
		dialogHtml = "<div class='wid_box width360'>" +
		"<div class='wid_tit'>" +
		"<p>删除</p>" +
		"<a></a></div>" +
		"<div class='c'></div>" +
		"<div class='wid_main_prompt'>" +
		"<p>您确定要删除吗？</p></div>" +
		"<div class='full_btn_box' style='padding:20px 0 '>" +
		"<ul class='btn_box'>" +
		"<li><a href='javascript:void(0)' class='btn_red btn_ok'>确 定</a></li>" +
		"<li><a href='javascript:void(0)' class='btn_orange btn_margin btn_off'>取 消</a></li>" +
		"</ul>" +
		"<div class='c'></div></div></div>";
	}
	
			
	var contain = $("<div>");
	contain.attr("class", "dialogContain");
	//确定显示的位置
	contain.css("position", "absolute");
	contain.css("z-index", "19890412");
	contain.css("display", "none");
	contain.html(dialogHtml);
	$("body").append(contain);
	
	var oT=($(window).height()-contain.height())/2;
	var oL=($(window).width()-contain.width())/2;
	contain.css({"top":oT,"left":oL});

	
	contain.find(".wid_tit").find("a").click(function(){
		contain.remove();
	});
	contain.find(".btn_off").click(function(){
		contain.remove();
	});
	
	contain.find(".btn_ok").click(function(){
		okOperate();
		contain.remove();
	});
	
	contain.show();
	
	contain.draggable({ containment: "parent", handle: "div.wid_tit"});
}

/**
 * 为了获得问下结果的dilog
 */
function showResultDialog(okOperate, msg){
	var dialogHtml = "";
	if(msg){
		dialogHtml = "<div class='wid_350x120'>" +
		"<div class='wid_tit'>" +
		"<p>删除</p>" +
		"<a></a></div>" +
		"<div class='wid_main2'><p>" + msg + "</p></div>" +
		"<div class='wid_box_btn'>" +
		"<a href='javascript:void(0)' class='btn_ok'> </a>" +
		"<a href='javascript:void(0)' class='btn_off'> </a></div></div>";
	}else{
		dialogHtml = "<div class='wid_350x120'>" +
		"<div class='wid_tit'>" +
		"<p>删除</p>" +
		"<a></a></div>" +
		"<div class='wid_main2'><p>您确定要删除吗？</p></div>" +
		"<div class='wid_box_btn'>" +
		"<a href='javascript:void(0)' class='btn_ok'> </a>" +
		"<a href='javascript:void(0)' class='btn_off'> </a></div></div>";
	}
	
			
	var contain = $("<div>");
	contain.attr("class", "dialogContain");
	//确定显示的位置
	contain.css("position", "absolute");
	contain.css("z-index", "19890412");
	contain.css("display", "none");
	contain.html(dialogHtml);
	$("body").append(contain);
	
	var oT=($(window).height()-contain.height())/2;
	var oL=($(window).width()-contain.width())/2;
	contain.css({"top":oT,"left":oL});

	
	contain.find(".wid_tit").find("a").click(function(){
		contain.remove();
	});
	contain.find(".btn_off").click(function(){
		contain.remove();
	});
	
	contain.find(".btn_ok").click(function(){
		okOperate();
		contain.remove();
	});
	
	contain.show();
	
	contain.draggable({ containment: "parent", handle: "div.wid_tit"});
}



/**
 * 迭代获得最顶层的window
 * @param win
 * @returns
 */
function getTopDocument(win){
	if(win.parent != win){
		return getTopDocument(win.parent);
	}else{
		return win;
	}
}

/**
 * 为每个json的返回值做验证，同时验证session过期。
 * @param resultCode
 * @returns {Boolean}
 */
function checkJsonResult(resultCode){
	if("success" == resultCode){//返回数据正确
		return true;
	}else if("fail" == resultCode){
		return false;
	}else if("logout" == resultCode){
		getTopDocument(window).location.href = baseUrl + "/index.jsp";
		return false;
	}else{
		return false;
	}
}



/**
 * 一个非常简单的验证
 * 
 * required:true  必填
 * number:true 	  数字
 * ip:true		  必须是ip格式
 * length:6		  长度必须不小于6
 * equalTo:name   必须和name的值相等
 * rule:/^xx$/    自定义格式判断，子定义提示语句
 * 
 */
function myvalidate(theObj, option){
	if(option === null){
		return false;
	}
	
	var fields = option.rules;
	
	for(var field in fields){
		var val = theObj.find("[name = '"+ field +"']").val();
		
		var config = fields[field];
		
		if(config["required"] === true){//必填
			if(val == null || val == ""){
				theObj.find("[name = '"+ field +"']").focus();
				showMsgDialog(config["name"] + "未填写！");
				return false;
			}
		}
		
		if(config["number"] === true){
			if(val != null && val != ""){
				if(!/^\d+$/.test(val)){
					theObj.find("[name = '"+ field +"']").focus();
					theObj.find("[name = '"+ field +"']").select();
					showMsgDialog(config["name"] + "必须是数字！");
					return false;
				}
			}
		}
		
		if(config["numAndLetter"] === true){
			//字母和数字
			if(val != null && val != ""){
				var reg = new RegExp("^[A-Za-z0-9]+$");
				if(val.match(reg) == null){
					theObj.find("[name = '"+ field +"']").focus();
					theObj.find("[name = '"+ field +"']").select();
					showMsgDialog(config["name"] + "必须是字母和数字！");
					return false;
				}
			}
		}
		
		if(config["ip"] === true){
			if(val != null && val != ""){
				if(!/^(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9]{1,2})(\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9]{1,2})){3}$/.test(val)){
					theObj.find("[name = '"+ field +"']").focus();
					theObj.find("[name = '"+ field +"']").select();
					showMsgDialog(config["name"] + "必须是有效IP地址！");
					return false;
				}
			}
		}
		
		if(config["email"] === true){
			if(val != null && val != ""){
				if(!/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i.test(val)){
					theObj.find("[name = '"+ field +"']").focus();
					theObj.find("[name = '"+ field +"']").select();
					showMsgDialog(config["name"] + "请输入有效的email地址！");
					return false;
				}
			}
		}
		
		if(config["length"] != null){
			if(val != null && val != ""){
				if(val.length < config["length"]){
					theObj.find("[name = '"+ field +"']").focus();
					theObj.find("[name = '"+ field +"']").select();
					showMsgDialog(config["name"] + "必须大于" + config["length"] + "！");
					return false;
				}
			}
		}
		
		if(config["equalTo"]){
			if(val != null && val != ""){
				var targetVal = theObj.find("[name = '"+ config["equalTo"] +"']").val();
				if(targetVal != val){
					theObj.find("[name = '"+ field +"']").focus();
					theObj.find("[name = '"+ field +"']").select();
					showMsgDialog(config["name"] + "必须等于" + fields[config["equalTo"]]["name"] + "！");
					return false;
				}
			}
		}
		
		if(config["rule"]){
			if(val != null && val != ""){
				if(!config["rule"].test(val)){
					showMsgDialog(config["name"]);
					return false;
				}
			}
		}
		
		
	}
	
	return true;
	
}


/**
 * 利用iframe进行下载
 */
function ajaxDownload(url){
	$("iframe").css("display", "none").appendTo("body");
}



//对日期的格式化
//对Date的扩展，将 Date 转化为指定格式的String   
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，   
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)   
//例子：   
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423   
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18  
Date.prototype.Format = function(fmt){ 
	var o = {   
    	"M+" : this.getMonth()+1,                 //月份   
    	"d+" : this.getDate(),                    //日   
    	"h+" : this.getHours(),                   //小时   
    	"m+" : this.getMinutes(),                 //分   
    	"s+" : this.getSeconds(),                 //秒   
    	"q+" : Math.floor((this.getMonth()+3)/3), //季度   
    	"S"  : this.getMilliseconds()             //毫秒   
  	};   
  	if(/(y+)/.test(fmt))   
    	fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
  	for(var k in o){ 
    	if(new RegExp("("+ k +")").test(fmt))   
  			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
  	} 
    return fmt;   
}

/**
 * 日期格式转换
 * @param strDate  日期String类型，或long类型
 * @param pattern  转换格式（"yyyy-MM-dd hh:mm:ss","yyyy-MM-dd"）
 */
function comm_format_date(strDate,pattern){
	var sRet;
	if(strDate == 0 || strDate == null || strDate == ""){
		sRet = "";
	}else{
		if(strDate.toString().indexOf("-")>0)
			strDate = strDate.replaceAll("-","/"); 
		var date = new Date(strDate);
		if(strDate.toString().indexOf("CST")>0){
			//时区转换
			date = new Date(date.getTime()-14*60*60*1000);
		}
		
		sRet = date.Format(pattern);
	}
	return sRet;
}


function keepAllSelect(checkbox){
    $('input[type=checkbox]').prop('checked', $(checkbox).prop('checked'));
}



/**
 * 操作菜单隐藏
 * 
 */
function comm_menu_hide(){
	$("#action_menu").css("display","none");
}

/**
 * 操作菜单显示
 * 
 */
function comm_menu_show(){
	$("#action_menu").css("display","block");
}
/**
 * 操作菜单激活
 * 
 */
function comm_menu_click(obj,event){
	MENU_ID = obj.id;
	var mousePos = comm_mouse_coords(event);
	$("#action_menu").css("left",(mousePos.x-60) +"px");
	$("#action_menu").css("top",(mousePos.y+5) +"px");
	$("#action_menu").css("display","block");
}
function comm_mouse_coords(ev) { 
	if(ev.pageX || ev.pageY){ 
		return {x:ev.pageX, y:ev.pageY}; 
	} 
	return { 
		x:ev.clientX + document.body.scrollLeft - document.body.clientLeft, 
		y:ev.clientY + document.body.scrollTop - document.body.clientTop 
	}; 
} 

function comm_update_frame(){
	var sNum = MENU_ID;
	$("#row_"+sNum+"_frame").css("display","");	
	$("#row_"+sNum).attr("class","tb_main_update");
	comm_init_frame();
}

function  comm_init_frame(){	
	comm_menu_hide();
	comm_init_scroll();
}
/**
 * 列表页面所有记录收起
 * 
 */
function comm_close_frame(){
	for(var i=0;i<10;i++){
		var obj = $("#row_"+i+"_frame");
		if(obj.css("display") != "none"){
			obj.css("display","none");
		}
		$("#row_"+i).attr("class","tb_main_tr"+ (i % 2 + 1));
	}
	comm_init_scroll();
}
/**
 * 列表页面单条记录收起
 * 
 */
function comm_close_row(sRowId){
	var obj = $("#row_"+sRowId+"_frame");
	if(obj.css("display") != "none"){
		obj.css("display","none");
	}
	$("#row_"+sRowId).attr("class","tb_main_tr"+ (sRowId % 2 + 1));
	comm_init_scroll();
}
/**
 * 格式化标题
 * 仅支持2，4，6个汉字标题
 * 
 */
function format_title(sValue){
	var result = "";
	if(sValue.length == 2){
		for(var i=0;i<sValue.length;i++){
			if(i>0){
				for(var j=0;j<8;j++){
					result += "&nbsp;";
				}
			}
			result += sValue.substr(i,1);	
		}
	}else if(sValue.length == 3){
		for(var i=0;i<sValue.length;i++){
			if(i>0){
				for(var j=0;j<2;j++)
					result += "&nbsp;";
			}
			result += sValue.substr(i,1);	
		}
	}else if(sValue.length == 4){
		for(var i=0;i<sValue.length;i++){
			if(i>0)
				result += "&nbsp;";
			
			result += sValue.substr(i,1);	
		}
	}else{
		result = sValue;
	}
	return result;
}

/**
 * 获取文件后缀
 * （无后缀时返回文件名）
 */
function commGetFileType(fileName){
 	var reg = /(\\+)/g;
 	var pfn = fileName.replace(reg, "#");
 	var arrpfn = pfn.split("#");
 	var fn = arrpfn[arrpfn.length - 1];
 	var arrfn = fn.split(".");
 	return arrfn[arrfn.length - 1];
}
/**
 * 校验是否int类型
 */
function isInt(val){
	var reg_exp = /^[\d]+$/ ;
	return reg_exp.test(val);
}

function isNum(value){
	if (isEmpty(value))
		return false;
	return !isNaN(value) // 判断值是否为数字
}

function isEmpty(value){
	if ((value==null)||(value.length==0))
		return true;
    else
    	return false;
}