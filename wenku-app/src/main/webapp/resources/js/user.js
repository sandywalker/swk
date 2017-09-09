/**
 * Created by sandy on 07/08/2017.
 */
(function($){

    var ctx = window.ctx || '';

    $.validator.addMethod(
        "regex",
        function(value, element, regexp) {
            var re = new RegExp(regexp);
            return this.optional(element) || re.test(value);
        },
        "Please check your input."
    );

    var $regForm  = $('#regForm');
    var $loginForm = $('#formLogin');

    $regForm.validate({
        rules:{
            mail:{
                required:true,
                email:true,
                maxlength:32,
                remote: ctx + "/register/check/email?mail=" + $('#inputMail').val()
            },
            password:{
                required:true,
                minlength:8,
                maxlength:16
            },
            cpassword:{
                required:true,
                equalTo:'#inputPassword'
            }
        },
        messages:{
            password:{
              minlength:'密码长度至少需要 8 位！',
              maxlength:'密码长度不能 16 位！',
            },
            mail:{
                remote:'该用户已经被注册！'
            },
            cpassword:{
                equalTo:'密码两次输入的不一致，请确保密码输入一致！'
            }
        },

        submitHandler: function(form) {
            if (!$('#readAgree').is(':checked')){
                alert('请确认已阅读用户协议');
                return;
            }
            form.submit();
        }
    });

    $loginForm.validate({
        rules:{
            loginName:{
                required:true
            },
            password:{
                required:true
            }
        },
        messages:{
            loginName:{
                required:'用户名不能为空！'
            },
            password:{
                required:'密码不能为空！'
            }
        }
    });

    $('#showAgree').on('click',function (e) {
       e.preventDefault();
       $('#agreementModal').modal('show');
    });
})(jQuery);