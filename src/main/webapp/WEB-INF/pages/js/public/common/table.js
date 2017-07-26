/*!
 * jQuery Table Plugin
 * version: 1.0
 * @requires jQuery v1.3.2 or later
 *
 * table样式
 * 
 * 作用：
 * 1，根据配置生产table表格
 * 2，根据提供的action地址，获得具体的数据
 * 3，分页监听
 * 4，tr的id数据的保存，为后续的edit和del做准备
 *
 */
;
(function($) {
	$.fn
			.extend({
				"mytable" : function(options) {

					var _this = this;
					var _options = null;

					if ($.fn.east_my_table_options == null) {
						// 第一次调用该方法
						if (options.fields == undefined
								|| options.fields == null) {
							// 连列都没定义 没有意义；
							return;
						}
						if (options.actions == null || options.actions == {}) {
							return;
						}

						options = $.extend(true, {
							// Options
							actions : {},
							data : {},
							fields : {},// 列数据
							animationsEnabled : true,
							defaultDateFormat : 'yy-mm-dd',
							loadingAnimationDelay : 500,
							tableTrSize : 10,// 列数
							selectCheck: false,
							nullShow: true,
							actionDeal:false,
							actionTitle:"操作",
							page : {
								page : 0,
								pageSize : 10
							},
							ajaxSettings : {
								type : 'POST',
								dataType : 'json'
							}
						}, options);

						$.fn.east_my_table_options = options;
						_options = options;

						// 第一次，初始化table
						var table = _this.find("table");
						// 设置高度
						//var height = 28 * 1 + 30 * options.tableTrSize;
						//table.css("height", height);

						var thead = $("<thead>");
						
						table.append(thead);
						if(_this.find("table").find("tbody").length == 0){
							var tbody = $("<tbody>");
							table.append(tbody);
						}

						var headTr = $("<tr>");
						headTr.attr("class", "tb_main_table_tit1");
						thead.append(headTr);
						
						if(options.selectCheck){
							
							var th = $("<th>");
							th.html("全选");
//							 $("<br>").appendTo(th);
							 $("<input>", { type: "checkbox", id: "checkbox", name: "checkbox", onclick:"keepAllSelect(this)" }).appendTo(th);
							headTr.append(th);
							
							$("#checkbox").addClass("checkbox");
						}
						for (obj in options.fields) {
							
							var thconf = options.fields[obj];

							if(thconf.display === false){
								continue;
							}else{
								var th = $("<th>");
								th.attr("id", "th_"+obj);
								th.html(thconf.title);
								headTr.append(th);
							}
							
						}
						if(options.actionDeal){
							var th = $("<th>");
							th.html(options.actionTitle);
							headTr.append(th);
						}
						// 初始化分页监听
						$(".tb_page").find(".tb_page_btn")
								.click(
										function() {	
											var curPage = parseInt($("#current_page").html());
											var totalPages = parseInt($("#total_page").html());

											var selectPage = 1;
											if ($(this)
													.is(".tb_page_btn_first")) {
												if (curPage == 1) {
													return;
												}
												selectPage = 1;
											} else if ($(this).is(
													".tb_page_btn_pre")) {
												if (curPage == 1) {
													return;
												}
												selectPage = curPage - 1;
											} else if ($(this).is(
													".tb_page_btn_next")) {
												if (curPage == totalPages) {
													return;
												}
												selectPage = curPage + 1;
											} else if ($(this).is(
													".tb_page_btn_last")) {
												if (curPage == totalPages) {
													return;
												}
												selectPage = totalPages;
											}

											$(_this).mytable({
												page : {
													page : selectPage - 1
												}
											});
										});

					} else {
						_this.find("table").find("tbody").find("td").html("");
						_this.find("table").find("tbody").find("tr.tr_need_listen").unbind();
						_options = $.extend(true, $.fn.east_my_table_options,
								options);
						_this.find("table").find("tbody").find("tr.tr_need_listen").each(function(){
							for (obj in _options.fields) {
								var flconf = _options.fields[obj];
								if (flconf.isRecord == true){
									$(this).removeAttr("tr_record_"+ obj);
								}
							}
							$(this).removeAttr("tr_need_listen");
						});
						_this
						.parent()
						.find(
								"tr.tb_main_tr1_hover")
						.removeClass(
								"tb_main_tr1_hover")
						.addClass(
								"tb_main_tr1");
						_this
						.parent()
						.find(
								"tr.tb_main_tr2_hover")
						.removeClass(
								"tb_main_tr2_hover")
						.addClass(
								"tb_main_tr2");
						$.fn.east_my_table_options = _options;
					}

					// 将page的信息放到date中。
					var addPagedate = _options.data;
					if (_options.page && _options.page.page >= 0) {
						addPagedate.page = _options.page.page;
						addPagedate.pageSize = _options.tableTrSize;
					}

					// 开始进行业务
					parent.PARENT_TABLE_DATA = _options.data;
					parent.PARENT_CURRENT_PAGE = _options.page.page;
					$
							.ajax({
								url : _options.actions.listAction,
								type : _options.ajaxSettings.type,
								data : _options.data,
								dataType : _options.ajaxSettings.dataType,
								success : function(msg) {
									if (checkJsonResult(msg.code)) {
										var bean = msg.bean;
										// 分页数据
										$("#to_page").val(bean.indexPage+1);
										$("#current_page").html(bean.indexPage+1);
										$("#total_page").html(bean.totalPages);
										$("#total_count").html(bean.totalCount);
										
										

										// 表格数据
										_this.find("table").find("tbody").html(
												"");
										var tableData = bean.content;
										for (var i = 0; i < _options.tableTrSize; i++) {
											var value = tableData[i];
											var tr = $("<tr>");
											tr.attr("id", "row_"+i);
											tr.attr("class", "tb_main_tr"
													+ (i % 2 + 1));
											if(!_options.nullShow){
												if(!value)
													tr.hide();
											}
											if(_options.selectCheck){
												if(value){
													var td = $("<td>");
													//checkbox
													//$("<input>", { type: "checkbox",id: "checkbox_"+i,name: "checkbox_"+i }).appendTo(td);
													td.append("<input type='checkbox' name='checkbox_"+i+"' id='checkbox_"+i+"' class='checkbox'/>");
													tr.append(td);
												}else{
													var td = $("<td>");
													tr.append(td);
												}
											}
											// 构造td
											// 需要显示的数据的规则
											for (obj in _options.fields) {
												var td = $("<td>");
												if (value) {
													// 为了监听做一个标记
													tr.attr("tr_need_listen",
															"");

													// 获得列的具体配置信息
													var flconf = _options.fields[obj];
													if (flconf.isRecord == true) {
														tr.attr("tr_record_"
																+ obj,
																value[obj]);
													}

													if (flconf.display === false) {
														// 不显示
														continue;
													} else {
														var getval = value[obj];
														if(flconf.relaField != null && flconf.relaField != "" && flconf.relaField != undefined)
															getval = value[flconf.relaField];
														if(getval !== null && getval !== "" && getval !== undefined){
															if (flconf.type == "date") {// 用日期方式显示
																getval = comm_format_date(getval,"yyyy-MM-dd");
															} else if (flconf.type == "time") {
																getval = comm_format_date(getval,"yyyy-MM-dd hh:mm:ss");
															}  else if (flconf.type == "enum") {// 用枚举值的方式显示
																var valueMapping = flconf.mapping;
																getval = valueMapping[getval];
															} else if (flconf.type == "listJson") {//用json对象方式显示
																var mapping = flconf.json;
																for(var p=0;p<mapping.length;p++){
																	if(eval("mapping["+p+"]."+flconf.code) == getval ){
																		getval = eval("mapping["+p+"]."+flconf.name);
																		break;
																	}
																}
															}
															
															if(flconf.length){
																if(getval.length > flconf.length){
																	getval = getval.substring(0, flconf.length);
																}
															}
															
															if (flconf.type == "href") {
																var nodeTitle = getval;
																getval = $("<a>");
																getval.attr("id", i);
																getval.css("cursor","pointer");
																getval.attr("href", "#");
																if(flconf.click != null)
																	getval.bind("click",flconf.click);
																getval.text(nodeTitle);
															}														
														}else{
															if (flconf.type == "image") {//显示图片
																getval = $("<img>");
																getval.attr("id", i);
																getval.css("cursor","pointer");
																if(flconf.src != null)
																	getval.attr("src", flconf.src);
																if(flconf.click != null)
																	getval.bind("click",flconf.click);
															}else{
																getval = " ";
															}
														}
														
														var span = $("<span>");
														span.attr("id", "row_"+i+"_"+obj);
														span.html(getval);
														td.html(span);
													}

												} else {
													var flconf = _options.fields[obj];
													if (flconf.display === false) {
														// 不显示
														continue;
													}
													
													td.html("");
												}
												tr.append(td);
											}
											
											if(_options.actionDeal){
												if(value){
													var td = $("<td>");
													var node = $("<a>");
													node.attr("class", "cx_tb_btn");
													node.attr("id", i);
													if(/firefox/.test(navigator.userAgent.toLowerCase())){
														node.attr("onclick", "comm_menu_click(this,event)");
													}else {
														node.click(function(){comm_menu_click(this,event)});
													}
	
													var text_node =  $("<p>");
													text_node.attr("class", "dropdown_p");
													text_node.text(_options.actionTitle); 
													text_node.appendTo(node);
												    
													node.appendTo(td);
													tr.append(td);
												}else{
													var td = $("<td>");
													tr.append(td);
												}	
											}

											_this.find("table").find("tbody").append(tr);
													
											/*		
											var tr = $("<tr>");
											tr.attr("id", "row_"+i+"_frame");
											tr.css("display", "none");
											
											
											var td = $("<td>");
											td.attr("class", "tb_main_frame");
											var iColLen = 0;
											for (obj in _options.fields) {
												var flconf = _options.fields[obj];
												if (flconf.display != false) 
												 	iColLen ++;
											}
											if(_options.selectCheck){
												iColLen ++;
											}
											if(_options.actionDeal){
												iColLen ++;
											}
											td.attr("colspan",iColLen);
											
											var node = $("<iframe></iframe>");
											node.attr("id","page_frame_"+i);
											node.attr("frameBorder","0");
											node.attr("scrolling","no");
											node.attr("class","tb_page_frame");
											
											td.append(node);
											tr.append(td);
											
											_this.find("table").find("tbody").append(tr);
											*/
  											comm_mytable_return(i);
										}
										comm_init_scroll();
										comm_mytable_end();
									}

									// 给所有的行 加上监听，拥有点击后聚焦
									_this
											.find("table")
											.find("tbody")
											.find("tr")
											.click(
													function() {
														if ($(this)
																.is(
																		"[tr_need_listen]")) {
													
															//将按钮变亮
															$(".tb_bottom_btn_hui").each(function(){
																var x = $(this).find("div").attr("class");
																x = x.substring(0, x.length - 4);
																$(this).find("div").removeClass().addClass(x);
																
																$(this).removeClass().addClass("tb_bottom_btn need_hui");
															});
															
														}else{
															//将按钮变灰
															$(".need_hui").each(function(){
																var x = $(this).find("div").attr("class");
																x = x + "_hui";
																$(this).find("div").removeClass().addClass(x);
																
																$(this).removeClass().addClass("tb_bottom_btn_hui");
															});
														}
														

													});
								}
							});
				}});
	
})(jQuery);


(function($) {
	$.fn
	.extend({
		"updatetable" : function(options) {

			//var _this = this;
			var _options = null;
			_options = $.extend(true, $.fn.east_my_table_options,
								options);
			// 将page的信息放到date中。
			var addPagedate = _options.updatedata;
			if (_options.page && _options.page.page >= 0) {
				addPagedate.page = 0;
				addPagedate.pageSize = _options.tableTrSize;
			}
			// 开始进行业务
			$.ajax({
				url : _options.actions.listAction,
				type : _options.ajaxSettings.type,
				data : addPagedate,
				dataType : _options.ajaxSettings.dataType,
				success : function(msg) {
					if (checkJsonResult(msg.code)) {
						var bean = msg.bean;
						// 表格数据
						var tableData = bean.content;
						var i = 0;
						if(tableData.length>0){
							var value = tableData[i];
							// 需要显示的数据的规则
							for (obj in _options.fields) {
								if (value) {
									// 获得列的具体配置信息
									var flconf = _options.fields[obj];
									if (flconf.isRecord == true) {
										$("#table").find("#row_"+_options.updateRow).attr("tr_record_"
										+ obj,
										value[obj]);
									}
		
									if (flconf.display === false) {
										continue;
									} else {
										var getval = value[obj];
										if(flconf.relaField != null && flconf.relaField != "" 
												&& flconf.relaField != undefined)
											getval = value[flconf.relaField];
											if(getval !== null && getval !== "" && getval !== undefined){
												if (flconf.type == "date") {// 用日期方式显示
													getval = comm_format_date(getval,"yyyy-MM-dd");
												} else if (flconf.type == "time") {
													getval = comm_format_date(getval,"yyyy-MM-dd hh:mm:ss");
												}  else if (flconf.type == "enum") {// 用枚举值的方式显示
													var valueMapping = flconf.mapping;
													getval = valueMapping[getval];
												} else if (flconf.type == "listJson") {//用json对象方式显示
													var mapping = flconf.json;
													for(var p=0;p<mapping.length;p++){
														if(eval("mapping["+p+"]."+flconf.code) == getval ){
														getval = eval("mapping["+p+"]."+flconf.name);
														break;
													}
												} 
											} else if (flconf.type == "split"){
												var valueList = getval.split(flconf.value);
												var getvalue = "";
												for(var p=0;p<valueList.length;p++){
													getvalue += "<div>"+valueList[p]+"</div>";
												}
												getval = getvalue;
											} else {
							
											}
			
											if(flconf.length){
												if(getval.length > flconf.length){
													getval = getval.substring(0, flconf.length);
												}
											}
			
										}else{
											getval = " ";
										}
										//alert("#row_"+flconf.updateRow+"_"+obj +"="+getval)
										$("#row_"+_options.updateRow+"_"+obj).html(getval);
									}
								}
							}
						}
					}
				}
		    });
		}
	});
})(jQuery);

/**
 * MyTbale异步调用之后执行的方法
 * 在私有js中重写
 */
function comm_mytable_return(id){
	
}
function comm_mytable_end(){

}
