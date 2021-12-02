$(function() {
    //点击更换验证码
    $('#kaptchaImage').click(function () {
        $(this).attr('src', 'kaptcha.jpg?' + Math.floor(Math.random() * 100));
    });
    $('#user_insert_btn').attr('disabled', "true");
    //失去焦点判断验证码是否正确
    $('#kaptcha').bind({
        focus: function () {
            //            if (this.value == this.defaultValue){
            //                this.value="";
            //            }
        },
        blur: function () {
            //var paramsTime = $("#kaptcha").val();
            var paramsTime = {
                kaptcha: this.value
            };
            $.ajax({
                url: "kaptcha",
                data: paramsTime,
                type: "POST",
                success: function (data) {
                    if (data == "kaptcha_error") {
                        //显示验证码错误信息
                        show_validate_msg("#kaptcha", "error", "验证码错误");
                        //禁用按钮
                        $('#user_insert_btn').attr('disabled', "true");
                    } else {
                        //显示验证码正确信息
                        show_validate_msg("#kaptcha", "success", "验证码正确");
                        //移除禁用
                        $('#user_insert_btn').removeAttr("disabled");
                    }

                }
            });
        }
    });
});