
var listDeal = "/exchangerecordaction/list";
var addIndex = "/exchangerecordaction/add";
var detailIndex = "/exchangerecordaction/detailIndex";

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
            exchangeRecords: {
                isRecord : true,
                title : "兑换记录号",
                display : false
            },
            clientMobile: {
                isRecord : true,
                title : "客户电话"
            },
            exchangeDate: {
                title : "兑换日期",
                type : "time"
            },
            exchangePoints: {
                title : "兑换积分"
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
            clientMobile : clientMobile
        },
        page: {
            page : toPage
        }

    });
}

function info_click(){
    var sNum = MENU_ID;
    var tm = new Date();
    var exchangeRecords = $("#row_"+this.id).attr("tr_record_exchangeRecords");
    var sHref = detailIndex+"?exchangeRecords="+exchangeRecords;
//	sHref += "&time="+ tm.getTime();
    $(location).attr('href', sHref);

}



function addClick(){
    var tm = new Date();
    var sHref = addIndex+"?time="+ tm.getTime();
    $(location).attr('href', sHref);
}



