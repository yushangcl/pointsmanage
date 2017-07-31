<%@ page contentType="text/html;charset=UTF-8" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>--%>
<%--<c:set var="basePath" value="${pageContext.request.contextPath}" />
<c:set var="pagePath" value="${sessionScope.pagePath}" />
<c:set var="forwardPage" value="${sessionScope.forwardPage}" />--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>客户兑换记录页面</title>
    <jsp:include page="/WEB-INF/pages/manage/public/comm.jsp"></jsp:include>
    <script type="text/javascript" src="/js/public/common/func.js" ></script>
    <script type="text/javascript" src="/js/manage/exchangerecord_index.js" ></script>
</head>

<body>

<div class="top">

    <div class="main">
        <div class="if_main">
            <div class="topbar">
                <table cellpadding="0" cellspacing="0" class="topbar_tb1">
                    <tr>
                        <td><span>客户电话</span>：
                            <input type="text" id="clientMobile" name="clientMobile" value="" maxlength="20"/>
                        </td>
                    </tr>
                </table>

                <div class="c"></div>
            </div>
            <div class="topbar_box">
                <a onclick="queryClick()">查询</a>
                <a onclick="addClick()">增加</a>
                <div class="c"></div>
            </div>

            <div class="main_box_over">
                <div id="tb_main_div">
                    <table id="table" width="100%" border="0" cellspacing="0"
                           class="tb_main_table1">
                    </table>
                </div>
            </div>
            <div class="full_page">
                <div class="tb_page">
                    <div style="float: left">
                        <p>跳至第</p>
                        <input type="text" id="to_page" name="to_page" value="1">
                        <p>页</p>
                    </div>
                    <div class="box_right">
                        <p>
                            总共<span id="total_count">0</span>条数据
                        </p>
                        <p>
                            <span id="current_page">0</span>/<span id="total_page">0</span> 页
                        </p>
                        <a href="#" class="tb_page_btn tb_page_btn_first">
                            <img src="/images/page1.png" />
                        </a>
                        <a href="#" class="tb_page_btn tb_page_btn_pre">
                            <img src="/images/page2.png" />
                        </a> <a href="#" class="tb_page_btn tb_page_btn_next">
                        <img src="/images/page3.png" />
                    </a>
                        <a href="#" class="tb_page_btn tb_page_btn_last">
                            <img src="/images/page4.png" />
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<jsp:include page="/WEB-INF/pages/manage/public/flooter.jsp"></jsp:include>



</body>


</html>
