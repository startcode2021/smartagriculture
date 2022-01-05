function DelJournal(id) {
    $.ajax({
        url:"/DelJournal",
        data:{"id":id},
        type:"post",
        success:function(msg){
            if(msg.code==100){
                alert("---删除成功---");
                window.location.href = "/journal/1/5";
            }else{
                alert("---删除失败---");
                window.location.href = "/journal/1/5";
            }
        },
        error:function(){
        }
    });
}
function ViewContent(id) {
    $.ajax({
        url:"/ViewContent",
        data:{"id":id},
        type:"get",
        success:function(data){
            window.location.href = data;
        },
        error:function(){
        }
    });
}