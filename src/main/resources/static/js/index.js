function Openordown(temp,obj) {
    $.ajax({
        url:"/kaiguang",
        data:{"place":temp,"type":obj},
        type:"post",
        success:function(msg){
            if(msg.code==100){
/*                alert("---操作成功---");*/
            }else{
/*                alert("---操作失败---");*/
            }
        },
        error:function(){
        }
    });
}