/**
 * Created by huahui.wu on 2017/8/7.
 */
var saveDeal = "/expenses/save";
var returnPath = "/expenses/index";

$(function() {
    comm_init_body();
    init_table();
});
function init_table() {
    $("#saveForm").attr("action", saveDeal);
    var sValue = $("#clientMobile").val();
    if (sValue != "" && sValue != undefined) {

        $("#operMode").val("update");
        $("return_btn").css("display", "none");

    }

}

function submitClick(theButton) {
    var exchangeRecords = $("#exchangeRecords").val();
    var clientMobile = $("#clientMobile").val();
    var exchangePoints = $("#exchangePoints").val();
    var exchangeDate = $("#exchangeDate").val();
    var remarks = $("#remarks").val();
    var re = /^1[34578]\d{9}$/;
    var option = {
        rules : {
            clientMobile : {
                required : true,
                name : "客户电话"
            },
            exchangePoints : {
                number : true,
                name : "兑换积分"
            }
        }
    };
    if (!myvalidate($(theButton).parents("form"), option)) {
        return;
    }else if(!re.test(clientMobile)){
        showMsgDialog('客户电话应为有效手机号码，请输入正确的手机号码！');
        return;
    }

    var clickEvent = "submitClick(this)";
    $(theButton).attr("onclick", "showMsgDialog('请不要重复提交')");
    $(theButton).parents("Form").ajaxSubmit({
        dataType : 'json',
        success : function(data) {
            checkJsonResult(data.code);
            $(theButton).attr("onclick", clickEvent);
            if (data.code == "success") {
                $(theButton).parents(".dialogContain").remove();
                showMsgDialog("操作成功!");
                returnBack();
            } else if (data.code == "fail") {
                showMsgDialog(data.message);
            }
        }
    });

}

function returnBack(){
    var tm = new Date();
    var sHref = returnPath+"?time="+ tm.getTime();
    $(location).attr('href', sHref);
}


