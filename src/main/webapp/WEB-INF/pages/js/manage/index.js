
var listDeal = "/clientinfoaction/list";
var addIndex = "/clientinfoaction/add";
var detailIndex = "/clientinfoaction/detailIndex";

$(function(){
    comm_init_body();
    init_table();
});
function init_table() {
    var pagegeNum = basePageNum;
    var infoSrc = "/images/check.png";
    if(pagegeNum == null)
        pagegeNum = 8;
    $("#tb_main_div").mytable({
        actions: {
            listAction : listDeal
        },
        data: {
        },
        tableTrSize: pagegeNum,
        fields: {
            clientId: {
                isRecord : true,
                title : "客户编号",
                display : false
            },
            clientName: {
                title : "客户姓名"
            },
            clientMobile: {
                isRecord : true,
                title : "客户电话"
            },
            purchasedPoints: {
                title : "已购积分"
            },
            convertedPoints: {
                title : "剩余积分"
            },
            info: {
                title : "详细信息",
                type : "image",
                src : infoSrc,
                click : info_click  //图片点击事件：事件中this.id获取行号

            }

        }
    });

}


/*----------------init_table-----------------------*/
function queryClick(){
    var clientMobile = $("#clientMobile").val();
    var clientName = $("#clientName").val();

    var toPage = $("#to_page").val();
    var totalPage = $("#total_page").html();

    if(toPage == "")
        toPage = 1;

    if(!isInt(toPage)){
        showMsgDialog("页码格式错误，非数字");
        return;
    }
    if(parseInt(toPage) > parseInt(totalPage)){
        toPage = totalPage;
    }
    if(toPage > 0)
        toPage =  parseInt(toPage)-1;
    $("#tb_main_div").mytable({
        data: {
            clientMobile : clientMobile,
            clientName : clientName
        },
        page: {
            page : toPage
        }

    });
}

function info_click(){
    var sNum = MENU_ID;
    var tm = new Date();
    var clientMobile = $("#row_"+this.id).attr("tr_record_clientMobile");
    var sHref = detailIndex+"?clientMobile="+clientMobile;
//	sHref += "&time="+ tm.getTime();
    $(location).attr('href', sHref);

}



function addClick(){
    var tm = new Date();
    var sHref = addIndex+"?time="+ tm.getTime();
    $(location).attr('href', sHref);
}



