//分类添加
function toAddClassify() {
    $.ajax({
        url: contextPath + "/manager/classify",
        method: "post",
        data: {
            action: "toAddClassify"
        },
        success: function (jsonStr) {
            $("#addClassify").html(jsonStr);
            $("input[name=select]").removeAttr("checked");
        }
    });
}
function addClassify() {
    var classifyLevel1 = $("#classifyLevel1").val();
    var classifyLevel2 = $("#classifyLevel2").val();
    var classifyName = $("#classifyName").val();
    var type = $("#type").val();
    $.ajax({
        url: contextPath + "/manager/classify",
        method: "post",
        data: {
            action: "addClassify",
            classifyName: classifyName,
            type: type,
            classifyLevel1: (classifyLevel1 == null || classifyLevel1 == "") ? 0 : classifyLevel1,
            classifyLevel2: (classifyLevel2 == null || classifyLevel2 == "") ? 0 : classifyLevel2
        },
        success: function (jsonStr) {
            var result = eval("(" + jsonStr + ")");
            //状态判断
            if (result.status == 1) {
                window.location.reload();
            }
        }
    });
}
//查询下级分类
function queryClassifyList(obj, selectId) {
    var parentId = $(obj).val();
    $.ajax({
        url: contextPath + "/manager/classify",
        method: "post",
        data: {
            action: "queryClassifyList",
            parentId: parentId
        },
        success: function (jsonStr) {
            var result = eval("(" + jsonStr + ")");
            //状态判断
            if (result.status == 1) {
                var options = "<option value=''>" + "请选择..." + "</option>";
                for (var i = 0; i < result.data.length; i++) {
                    var option = "<option value=" + result.data[i].id + ">" + result.data[i].classifyName + "</option>";
                    options = options + option;
                }
                $("#" + selectId).html(options);
            }
        }
    });
}
//修改
function toUpdateClassifyList(obj) {
    var id = $(obj).val();
    $.ajax({
        url: contextPath + "/manager/classify",
        method: "post",
        data: {
            action: "toUpdateClassify",
            id: id
        },
        success: function (jsonStr) {
            $("#addClassify").html(jsonStr);
        }
    });
}
//修改
function saveOrUpdate() {
    var id = $("#id").val();
    if (id == null || id == "") {
        addClassify();
    } else {
        modifyClassify();
    }
}
function modifyClassify() {
    var id = $("#id").val();
    var classifyLevel1 = $("#classifyLevel1").val();
    var classifyLevel2 = $("#classifyLevel2").val();
    var classifyName = $("#classifyName").val();
    var type = $("#type").val();
    $.ajax({
        url: contextPath + "/manager/classify",
        method: "post",
        data: {
            action: "modifyClassify",
            id: id,
            classifyName: classifyName,
            type: type,
            classifyLevel1: (classifyLevel1 == null || classifyLevel1 == "") ? 0 : classifyLevel1,
            classifyLevel2: (classifyLevel2 == null || classifyLevel2 == "") ? 0 : classifyLevel2
        },
        success: function (jsonStr) {
            var result = eval("(" + jsonStr + ")");
            //状态判断
            if (result.status == 1) {
                window.location.reload();
            }
        }
    });
};
//选择商品分类级别
function selectClassifyLevel(obj) {
    var level = $(obj).val();
    if (level == 1) {
        $('#classifyLevel1').parent().parent().hide();
        $('#classifyLevel2').parent().parent().hide();
    } else if (level == 2) {
        $('#classifyLevel1').parent().parent().show();
    } else {
        $('#classifyLevel1').parent().parent().show();
        $('#classifyLevel2').parent().parent().show();
    }
}
//删除商品分类
function deleteClassify(id,type) {
 var bool=window.confirm("确认删除此分类信息么?");
	if(bool){
		$.ajax({
	        url: contextPath + "/manager/classify",
	        method: "post",
	        data: {
	            id: id,
	            type: type,
	            action: "deleteClassify"
	        },
	        success: function (jsonStr) {
	            var result = eval("(" + jsonStr + ")");
	            if (result.status == 1) {
	                window.location.reload();
	            }else{
	            	showMessage(result.message);
	            }
	        }
	    });
	}
}
//商品发布的是很检查相关字段
function checkGoods() {
    var classifyLevel1 = $("#classifyLevel1").val();
    var classifyLevel2 = $("#classifyLevel2").val();
    var classifyLevel3 = $("#classifyLevel3").val();
    var goodsName = $("#goodsName").val();
    var price = $("#price").val();
    var stock = $("#stock").val();
    if (classifyLevel1 == null || classifyLevel1 == "") {
        showMessage("请选择商品分类");
        return false;
    }
    if (classifyName == null || classifyName == "") {
        showMessage("清填写商品名称");
        return false;
    }
    if (goodsName.length < 2 || goodsName > 16) {
        showMessage("商品名称为2到16个字符");
        return false;
    }
    if (price == null || price == "") {
        showMessage("清填写商品价格");
        return false;
    }
    if (stock == null || stock == "") {
        showMessage("清填写商品库存");
        return false;
    }
}
//检查用户
function deleteById(id) {
	var bool=window.confirm("确认删除此商品信息么?");
	if(bool){
		$.ajax({
	        url: contextPath + "/manager/goods",
	        method: "post",
	        data: {
	            id: id,
	            action: "deleteById"
	        },
	        success: function (jsonStr) {
	            var result = eval("(" + jsonStr + ")");
	            if (result.status == 1) {
	                window.location.reload();
	            }
	        }
	    });
	}
}
/**
 * 检查用户
 */
function checkUser() {
    var account = $("input[name='account']").val();
    var nickName = $("input[name='nickName']").val();
    var idCardNo = $("input[name='idCardNo']").val();
    var email = $("input[name='email']").val();
    var phone = $("input[name='phone']").val();
    var type = $("select[name='type']").val();
    var password = $("input[name='password']").val();
    var repPassword = $("input[name='repPassword']").val();
    var id = $("input[name='id']").val();

    if (account == null || account == "") {
        showMessage("请填写登录用户名");
        return false;
    }
    
    if(account.length<2 || account>10){
        showMessage("登录名不能小于两个字符或者大于十个字符.");
        return false;
    }

    if (nickName == null || nickName == "") {
        showMessage("请填写真实姓名");
        return false;
    }
    if(id==null || id=="" || id==0){
    	if (password == null || password == "") {
            showMessage("请填写密码");
            return false;
        }
        
        if (password !=repPassword) {
            showMessage("两次输入密码不一致");
            return false;
        }
    }

    if(email!=null && email!="" && !checkMail(email)){
    	showMessage("邮箱格式不正确");
        return false;
    }
    //验证邮箱格式
    if(phone!=null && phone!="" && !checkphone(phone)){
    	showMessage("手机格式不正确");
        return false;
    }
     //验证邮箱格式
    if(idCardNo!=null && idCardNo!="" && !checkidCardNo(idCardNo)){
    	showMessage("身份证号格式不正确");
        return false;
    }
    return true;
}

function addUser() {
    if(!checkUser()){
    	return false;
    }
    var account = $("input[name='account']").val();
    var nickName = $("input[name='nickName']").val();
    var idCardNo = $("input[name='idCardNo']").val();
    var email = $("input[name='email']").val();
    var phone = $("input[name='phone']").val();
    var type = $("select[name='type']").val();
    var id = $("input[name='id']").val();
    var password = $("input[name='password']").val();
    $.ajax({
        url: contextPath + "/manager/user",
        method: "post",
        data: {
           id: id,
           action: "updateUser",
           account: account,
           nickName: nickName,
           idCardNo: idCardNo,
           email: email,
           phone: phone,
           type: type,
           password:password
        },
        success: function (jsonStr) {
            var result = eval("(" + jsonStr + ")");
            if (result.status == 1) {
                window.location.href=contextPath+"/manager/user?action=queryUserList";
            }else{
            	showMessage(result.message);
            }
        }
    });
}
/**
 * 删除用户
 * @param id
 */
function deleteUserId(id) {
	var bool=window.confirm("确认删除此用户信息么?");
	if(bool){
		$.ajax({
	        url: contextPath + "/manager/user",
	        method: "post",
	        data: {
	            id: id,
	            action: "deleteUserById"
	        },
	        success: function (jsonStr) {
	            var result = eval("(" + jsonStr + ")");
	            if (result.status == 1) {
	                window.location.reload();
	            }
	        }
	    });
	}
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
