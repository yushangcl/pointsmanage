<%@ page isELIgnored ="false"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>客户消费记录</title>
    <jsp:include page="/WEB-INF/pages/manage/public/comm.jsp"></jsp:include>
    <link rel="stylesheet" type="text/css"
          href="/css/product.css" />
    <script type="text/javascript"
            src="/js/manage/expensesrecord_add.js"></script>
</head>
<body>


<div class="top">

    <div class="main">
        <div class="if_main">
            <form action="" id="saveForm" method="post">
                <div class="if_main">
                    <div class="topbar topber_line">
                        <p class="topbar_text">客户消费记录</p>
                        <div class="c"></div>
                    </div>
                    <div class="pro_classinfo">
                        <input type="hidden" id="operMode" name="operMode" value="add" />
                        <input type="hidden" id="recordNumber" name="recordNumber" value="${expensesRecord.recordNumber}" />
                        <table cellpadding="0" cellspacing="0" class="pro_classinfo_tb1 w1">

                            <tr>
                                <td colspan="3"><span>客户电话</span>
                                    <p>:</p> <input type="text" name="clientMobile" id="clientMobile"
                                                    value="${expensesRecord.clientMobile}" maxlength="20"
                                            <c:if test="${not empty expensesRecord }">
                                                class="readonly"  readonly
                                            </c:if>
                                    />
                                    <p class="i">*</p>
                                </td>
                            </tr>
                            <tr>
                                <td><span>消费金额</span>
                                    <p>: </p> <input type="text" id="amount" name="amount"
                                                     maxlength="10" style="width: 160px;"
                                            <c:if test="${not empty expensesRecord }">
                                                value="${expensesRecord.amount}"
                                            </c:if>
                                            <c:if test="${empty expensesRecord }">
                                                value="0"
                                            </c:if>
                                    />
                                </td>
                                <td>
                                    <div id="expensesTime" >
                                        <span>兑换日期</span>

                                        <p>：</p>
                                        <input type="text" name="expensesDate" id="expensesDate"
                                               value="${expensesRecord.consumptionDate}" style="width: 185px;"
                                               class="Wdate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" />

                                    </div></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td colspan="3"><span class="p3">备注</span>
                                    <p>：</p><br />
                                    <textarea cols="45" rows="5" style="width: 600px;"  id="remarks" name="remarks">
                                        ${expensesRecord.remarks}
                                    </textarea>
                                </td>
                            </tr>


                            <%--<c:if test="${not empty exchangeRecord}">
                                <tr>
                                    <td colspan="3"><span>客户电话</span>
                                        <p>:</p>
                                        <input type="text" name="clientMobile" id="clientMobile"
                                                        value="${exchangeRecord.clientMobile}" maxlength="20" class="readonly"   readonly/>
                                    </td>
                                </tr>
                                <tr>
                                    <td><span>已购积分</span>
                                        <p>: </p> <input type="text" id="purchasedPoints" name="purchasedPoints"
                                                         maxlength="10" style="width: 160px;" value="${clientInfo.purchasedPoints}" class="readonly" readonly/>
                                    </td>
                                    <td><span>已换积分</span>
                                        <p>: </p> <input type="text" id="convertedPoints" name="convertedPoints"
                                                         maxlength="10" style="width: 160px;" value="${clientInfo.convertedPoints}" class="readonly" readonly/>
                                    </td>
                                    <td><span>剩余积分</span>
                                        <p>: </p> <input type="text" id="remainingPoints" name="remainingPoints"
                                                         maxlength="10" style="width: 160px;" value="${clientInfo.remainingPoints}" class="readonly" readonly/>
                                    </td>
                                </tr>
                            </c:if>--%>

                        </table>
                        <div class="c"></div>
                    </div>

                </div>


                <div class="full_btn_box">
                    <div style="margin: 0 auto; width: 260px;">
                        <a class="btn_blue" href="#" onclick="submitClick(this)">保 存</a> <a
                            id="return_btn" class="btn_green" href="/expenses/index" >返
                        回</a>
                        <div class="c"></div>
                    </div>
                </div>

            </form>
        </div>
    </div>

</div>
<jsp:include page="/WEB-INF/pages/manage/public/flooter.jsp"></jsp:include>



</body>
</html>

