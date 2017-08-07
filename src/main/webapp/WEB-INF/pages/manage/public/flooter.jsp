<%--
  Created by IntelliJ IDEA.
  User: 10150
  Date: 2017/7/30
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="side">
    <div class="side_list1 end" id="tab_menu0" >
        <a href="/index.jsp" onclick="changeClass(0);">客户信息</a>
    </div>
    <div class="side_list1" id="tab_menu1" >
        <a href="/expenses/index" onclick="changeClass(1);">消费记录</a>
    </div>
    <div class="side_list1" id="tab_menu2">
        <a href="/exchangerecordaction/index" onclick="changeClass(2);">兑换记录</a>
    </div>


</div>
<div class="bottom"></div>

<script>
    function changeClass(sId) {
        var rowCount = 3;
        for(var i=0;i<rowCount;i++){
            if(i == sId){
                $("#tab_menu"+sId).attr("class","end");
            }else{
                if($("#tab_menu"+i).attr("class") != ""){
                    $("#tab_menu"+i).attr("class","");
                }
            }
        }

    }
</script>
