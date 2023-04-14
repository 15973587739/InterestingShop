/**
 * Created by bdqn on 2016/5/3.
 */
//登录的方法
function login(){
    var account=$("#account").val();
    var password=$("#password").val();
    $.ajax({
        url:contextPath+"/login",
        method:"post",
        data:{account:account,password:password,action:"login"},
        success:function(jsonStr){
            var result=eval("("+jsonStr+")");
            if(result.status==1){
                window.location.href=contextPath+"/home?action=index";
            }else{
                showMessage(result.message)
            }
        }
    })
}