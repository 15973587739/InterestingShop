/**
 * Created by bdqn on 2016/5/3.
 */
/**
 * 商品详情页添加到购物车
 */
function addCart(){
    var entityId=$("input[name='entityId']").val();
    var buyNum=$("input[name='buyNum']").val();
    //添加到购物车
    addCartByParam(entityId,buyNum);
}
/**
 * 商品列表页添加到购物车
 * @param entityId
 * @param buyNum
 */
function addCartByParam(entityId,buyNum){
    //添加到购物车
    $.ajax({
        url: contextPath + "/cart",
        method: "post",
        data: {
            action: "add",
            entityId: entityId,
            buyNum: buyNum
        },
        success: function (jsonStr) {
        	 var result = eval("(" + jsonStr + ")");
             //状态判断
             if (result.status == 1) {
            	 showMessage("操作成功");
            	 refreshCart();
             }else{
            	 showMessage(result.message);
             }
        }
    } )
}
/**
 * 刷新购物车 searchBar DIV
 */
function refreshCart(){
    $.ajax({
        url: contextPath + "/cart",
        method: "post",
        data: {
            action: "refreshCart"
        },
        success: function (jsonStr) {
            $("#searchBar").html(jsonStr);
        }
    })
}
/**
 * 结算 加载购物车列表
 */
function settlement1(){
    $.ajax({
        url: contextPath + "/cart",
        method: "post",
        data: {
            action: "settlement1"
        },
        success: function (jsonStr) {
        	refreshCart();
            $("#settlement").html(jsonStr);
        }
    });
}
/**
 * 结算 形成预备订单
 */
function settlement2(){
    $.ajax({
        url: contextPath + "/cart",
        method: "post",
        data: {
            action: "settlement2"
        },
        success: function (jsonStr) {
            $("#settlement").html(jsonStr);
        }
    });
}
/**
 * 结算 订单支付提醒
 */
function settlement3(){
    //判断地址
    var addressId=$("input[name='selectAddress']:checked").val();
    var newAddress=$("input[name='address']").val();
    var newRemark=$("input[name='remark']").val();
    if(addressId=="" || addressId==null){
        showMessage("请选择收货地址");
        return;
    }else if(addressId=="-1"){
        if(newAddress=="" || newAddress==null){
            showMessage("请填写新的收货地址");
            return;
        }else if(newAddress.length<=2 || newAddress.length>=50){
            showMessage("收货地址为2-50个字符");
            return;
        }
    }
    $.ajax({
        url: contextPath + "/cart",
        method: "post",
        data: {
            action: "settlement3",
            addressId:addressId,
            newAddress:newAddress,
            newRemark:newRemark
        },
        success: function (jsonStr) {
        	if(jsonStr.substr(0,1)=="{"){
        		var result = eval("(" + jsonStr + ")");
        		showMessage(result.message);
        	}else{
        		$("#settlement").html(jsonStr);
        	}
        }
    });
}
/**
 * 商品详情页的 数量加
 * @param obj
 * @param entityId
 */
function addbuyNum(obj,entityId,stock){
	var buyNum=Number(getPCount(jq(obj)))+1;
	if(stock<buyNum){
		showMessage("商品数量不足");
		return;
	}
    modifyCart(entityId,buyNum,jq(obj));
}
/**
 * 减去 数量减
 * @param obj
 * @param entityId
 */
function subbuyNum(obj,entityId){
    var buyNum=getPCount(jq(obj))-1;
    if(buyNum==0) buyNum=1;
    modifyCart(entityId,buyNum,jq(obj));
}
/**
 * 修改购物车列表
 * @param entityId
 * @param buyNum
 */
function modifyCart(entityId,buyNum,obj){
	$.ajax({
        url: contextPath + "/cart",
        method: "post",
        data: {
            action: "modCart",
            entityId:entityId,
            buyNum:buyNum
        },
        success: function (jsonStr) {
        	var result = eval("(" + jsonStr + ")");
            //状态判断
            if (result.status == 1) {
                obj.parent().find(".car_ipt").val(buyNum);
                settlement1();
            }else{
           	 	showMessage(result.message);
            }
        }
    });
}
/**
 * 清空购物车
 */
function clearCart(){
    $.ajax({
        url: contextPath + "/cart",
        method: "post",
        data: {
            action: "clearCart"
        },
        success: function (jsonStr) {
            $("#settlement").html(jsonStr);
            showMessage("操作成功");
        }
    });
}
/**
 * 删除购物车
 * @param entityId
 */
function removeCart(entityId){
	$.ajax({
        url: contextPath + "/cart",
        method: "post",
        data: {
            action: "modCart",
            entityId:entityId,
            buyNum:0
        },
        success: function (jsonStr) {
        	var result = eval("(" + jsonStr + ")");
            //状态判断
            if (result.status == 1) {
            	settlement1();
            }else{
           	 	showMessage(result.message);
            }
        }
    });
}
/**
 * 喜欢的列表
 */
function favoriteList(){
    $.ajax({
        url: contextPath + "/favorite",
        method: "post",
        data: {
            action: "toFavoriteList"
        },
        success: function (jsonStr) {
            $("#favoriteList").html(jsonStr);
        }
    });
}
/**
 * 添加到收藏列表
 * @param goodsId
 */
function addFavorite(goodsId){
    $.ajax({
        url: contextPath + "/favorite",
        method: "post",
        data: {
            action: "addFavorite",
            id:goodsId
        },
        success: function (jsonStr) {
            favoriteList();
        }
    });
}

/**
 * 结算 订单支付提醒
 */
function toPay(){
//    //获取支付方式
//    var payType= $("input[name='selectPay']:checked").val();
//    //获取订单编号
//    var serialNumber = $("#serialNumber").html();
//    //获取订单笔数作为订单名称
//    var title = $("input[name='orderSize']").val();
//    //获取订单金额
//    var cost = $("#cost").html();
//    //微信支付暂不实现
//    if('1' == payType){
//    	return;
//    }else{
//    	//支付宝支付
//    	//发送请求跳转到支付宝支付接口
//    	$.ajax({
//            url: contextPath + "/Alipay",
//            method: "post",
//            data: {
//                action: "pay",
//                WIDout_trade_no:serialNumber,
//                WIDsubject:title,
//                WIDtotal_amount:cost
//            },
//            success: function () {
//            }
//    	});
//    }
	debugger;
	var form = $("#payForm");
	var path = "";
//    获取支付方式
    var payType= $("input[name='selectPay']:checked").val();
    if('1' == payType){
    	path = '';
    }else{
    	path = contextPath + "/Alipay?action=pay";
    	form.attr("action", path).submit();
    }
}