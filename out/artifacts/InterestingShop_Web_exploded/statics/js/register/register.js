/**
 * Created by bdqn on 2016/5/3.
 */
function register() {
    //获取相关字段的值
    var account = $("input[name='account']").val();
    var nickName = $("input[name='nickName']").val();
    var password = $("input[name='password']").val();
    var confirmPassword = $("input[name='confirmPassword']").val();
    var email = $("input[name='email']").val();
    var phone = $("input[name='phone']").val();
    var idCardNo = $("input[name='idCardNo']").val();
    var address = $("input[name='address']").val();
    var gender = $("input[name='gender']:checked").val();
    //判断密码是否一致
    if(account==null || account==""){
        showMessage("用户名不能为空.");
        return;
    }

    if(account.length<2 || account>10){
        showMessage("登录名不能小于两个字符或者大于十个字符.");
        return;
    }

    if(nickName==null || nickName==""){
        showMessage("真实姓名不能为空.");
        return;
    }

    if(nickName.length<2 || nickName>10){
        showMessage("真实姓名不能小于两个字符或者大于十个字符.");
        return;
    }

    if (password != confirmPassword) {
        showMessage("两次输入的密码不一致.");
        return;
    }
    //判断密码是否为空
    if (password =="") {
        showMessage("密码不能为空");
        return;
    }
    //验证邮箱格式
    if(email!=null && email!="" && !checkMail(email)){
    	showMessage("邮箱格式不正确");
        return;
    }
    //验证邮箱格式
    if(phone!=null && phone!="" && !checkphone(phone)){
    	showMessage("手机格式不正确");
        return;
    }
     //验证邮箱格式
    if(idCardNo!=null && idCardNo!="" && !checkidCardNo(idCardNo)){
    	showMessage("身份证号格式不正确");
        return;
    }
    
    $.ajax({
        url: contextPath + "/register",
        method: "post",
        data: {
            action: "login",
            account: account,
            nickName: nickName,
            password: password,
            gender: gender,
            email: email,
            phone: phone,
            action: 'saveUserToDatabase',
            idCardNo: idCardNo,
            address: address
        },
        success: function (jsonStr) {
            var result = eval("(" + jsonStr + ")");
            if (result.status == 1) {
                showMessage(result.message);
                window.location.href = contextPath + "/login?action=toLogin";
            } else {
                showMessage(result.message);
            }
        }
    })
}


function checkMail(mail) {
  var filter  = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
  if (filter.test(mail)) 
	  return true;
  else {
	 return false;}
}

function checkphone(phone) {
  var filter  = /^\d{5,11}$/;
  if (filter.test(phone)) 
	  return true;
  else {
	 return false;
  }
}

function checkidCardNo(idCardNo) {
	  var filter  = /^\w{18}$/;
	  if (filter.test(idCardNo))
		  return true;
	  else {
		 return false;
	  }
}
