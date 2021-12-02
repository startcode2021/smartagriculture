//显示验证结果
function show_validate_msg(ele, status, msg) {
    if ("success" == status) {
        // 让父容器变色
        $(ele).parent().removeClass("has-error");
        $(ele).parent().addClass("has-success");
        // 给sapn赋值正确信息
        $(ele).next("span").text(msg);
    } else if ("error" == status) {
        // 让父容器变色
        $(ele).parent().removeClass("has-success");
        $(ele).parent().addClass("has-error");
        // 给sapn赋值错误信息
        $(ele).next("span").text(msg);
    }
}

// 验证账号密码
function validate_add_form() {
    //验证手机号
    var phone = $("#insert_phone").val();
    var reg_phone = /(^1[345678]\d{9}$)/;
    if (!reg_phone.test(phone)) {
        show_validate_msg("#insert_phone", "error", "手机号必须是11位数字的组合");
        return false;
    } else {
        show_validate_msg("#insert_phone", "success", "");
    }
    //验证邮箱
    var email = $("#insert_email").val();
    var reg_email = /(^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$)/;
    if (!reg_email.test(email)) {
        show_validate_msg("#insert_email", "error", "邮箱格式错误");
        return false;
    } else {
        show_validate_msg("#insert_email", "success", "");
    }
    // 验证用户名
    var name = $("#insert_name").val();
    var reg_name = /(^[a-zA-Z0-9_-]{6,19}$)|(^[\u2E80-\u9FFF]{2,5})/;
    if (!reg_name.test(name)) {
        show_validate_msg("#insert_name", "error", "用户名必须是2-5位中文或6-19位英文和数字的组合");
        return false;
    } else {
        show_validate_msg("#insert_name", "success", "");
    }

    // 验证密码
    var password = $("#insert_password").val();
    var reg_password = /(^[a-zA-Z0-9_-]{6,19}$)/;
    if (!reg_password.test(password)) {
        show_validate_msg("#insert_password", "error", "密码必须是6-19位英文和数字的组合");
        return false;
    } else {
        show_validate_msg("#insert_password", "success", "");
    }
    var password2 = $("#insert_password2").val();
    if(password!==password2)
    {
        return false;
    }
    return true;
}
function IsPhone(){
    var phone = $("#insert_phone").val();
    var reg_phone = /(^1[345678]\d{9}$)/;
    var flag = 1;
    $.ajax(
        {
            url:"havephone",
            data:{"phone":phone},
            type:"post",
            success:function(msg){
                if(msg.code==100){
                    flag = 1;
                }else{
                    flag = 0;
                }
                if (!reg_phone.test(phone)) {
                    show_validate_msg("#insert_phone", "error", "格式错误");
                    $("#phonediv").removeClass("has-success");
                    $("#phonediv").addClass("has-error");
                    $("#Phonestate2").removeClass("sr-only");
                    $("#Phonestate1").addClass("sr-only");
                    return false;
                } else {
                    if(flag == 1)
                    {
                        show_validate_msg("#insert_phone", "success", "");
                        $("#phonediv").removeClass("has-error");
                        $("#phonediv").addClass("has-success");
                        $("#Phonestate1").removeClass("sr-only");
                        $("#Phonestate2").addClass("sr-only");
                        return true;
                    }else
                    {
                        show_validate_msg("#insert_phone", "error", "手机号已经存在");
                        $("#phonediv").removeClass("has-success");
                        $("#phonediv").addClass("has-error");
                        $("#Phonestate2").removeClass("sr-only");
                        $("#Phonestate1").addClass("sr-only");
                        return false;
                    }
                }
            },
            error:function(){
            }
        }
    );
}
function  IsEmail(){
    //验证邮箱
    var email = $("#insert_email").val();
    var reg_email = /(^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$)/;
    if (!reg_email.test(email)) {
        show_validate_msg("#insert_email", "error", "邮箱格式错误");
        $("#emaildiv").removeClass("has-success");
        $("#emaildiv").addClass("has-error");
        $("#Emailstate2").removeClass("sr-only");
        $("#Emailstate1").addClass("sr-only");
        return false;
    } else {
        show_validate_msg("#insert_email", "success", "");
        $("#emaildiv").removeClass("has-error");
        $("#emaildiv").addClass("has-success");
        $("#Emailstate1").removeClass("sr-only");
        $("#Emailstate2").addClass("sr-only");
    }
    return true;
}
function IsName() {
    // 验证用户名
    var name = $("#insert_name").val();
    var reg_name = /(^[a-zA-Z0-9_-]{6,19}$)|(^[\u2E80-\u9FFF]{2,5})/;
    if (!reg_name.test(name)) {
        show_validate_msg("#insert_name", "error", "用户名必须是2-5位中文或6-19位英文和数字的组合");
        $("#namediv").removeClass("has-success");
        $("#namediv").addClass("has-error");
        $("#Namestate2").removeClass("sr-only");
        $("#Namestate1").addClass("sr-only");
        return false;
    } else {
        show_validate_msg("#insert_name", "success", "");
        $("#namediv").removeClass("has-error");
        $("#namediv").addClass("has-success");
        $("#Namestate1").removeClass("sr-only");
        $("#Namestate2").addClass("sr-only");
    }
    return true;
}
function IsPassword(){
    //密码验证
    var password = $("#insert_password").val();
    var password2 = $("#insert_password2").val();
    var reg_password = /(^[a-zA-Z0-9_-]{6,19}$)/;
    if(password === password2)
    {
        $("#password2div").addClass("has-success");
        $("#password2div").removeClass("has-error");
        $("#Password2state2").addClass("sr-only");
        $("#Password2state1").removeClass("sr-only");
    }else {
        $("#password2div").removeClass("has-success");
        $("#password2div").addClass("has-error");
        $("#Password2state2").removeClass("sr-only");
        $("#Password2state1").addClass("sr-only");
        return false;
    }
    if (!reg_password.test(password)) {
        show_validate_msg("#insert_password", "error", "必须是6-19位英文和数字的组合");
        $("#passworddiv").removeClass("has-success");
        $("#passworddiv").addClass("has-error");
        $("#Passwordstate2").removeClass("sr-only");
        $("#Passwordstate1").addClass("sr-only");
        return false;
    } else {
        show_validate_msg("#insert_password", "success", "");
        $("#passworddiv").addClass("has-success");
        $("#passworddiv").removeClass("has-error");
        $("#Passwordstate2").addClass("sr-only");
        $("#Passwordstate1").removeClass("sr-only");
    }
    return true;
}
function IsPassword2() {
    var password2 = $("#insert_password2").val();
    var password = $("#insert_password").val();
    if(password === password2)
    {
        $("#password2div").addClass("has-success");
        $("#password2div").removeClass("has-error");
        $("#Password2state2").addClass("sr-only");
        $("#Password2state1").removeClass("sr-only");
    }else {
        $("#password2div").removeClass("has-success");
        $("#password2div").addClass("has-error");
        $("#Password2state2").removeClass("sr-only");
        $("#Password2state1").addClass("sr-only");
        return false;
    }
}

