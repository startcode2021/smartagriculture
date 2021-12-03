$(function (){
   $.ajax({
      url:"getName",
      data:{},
      type:"post",
      success:function(username){
         $('#a1').text("你好,"+username);
      },
      error:function(){
      }
   });
   $('#li1').addClass("active");
});