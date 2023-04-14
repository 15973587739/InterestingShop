<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/common/client/header.jsp" %>
    <script src="${ctx}/statics/js/cart/cart.js"></script>
    <title>易买网</title>
</head>
<body>
<!--Begin Header Begin-->
<div id="searchBar">
    <%@ include file="/common/client/searchBar.jsp" %>
</div>
<!--End Header End-->
<!--Begin Menu Begin-->
<div class="menu_bg">
    <div class="menu">
        <!--Begin 商品分类详情 Begin-->
        <%@ include file="/common/client/classifyBar.jsp" %>
        <!--End 商品分类详情 End-->
    </div>
</div>
<!--End Menu End-->
<div class="i_bg">
    <div id="settlement"></div>
   <div class="content mar_20">
	<!--Begin 银行卡支付 Begin -->
		<div class="warning">
			<table border="0" style="width: 1000px; text-align: center;"
				cellspacing="0" cellpadding="0">
				<tr height="35">
					<td style="font-size: 18px;">
					<div style="margin-top:10px;margin-bottom: 10px;font-size: 20px;">
						<img src="${ctx}/statics/images/fail.png" style="vertical-align:middle;">
                        <input type="hidden" id="flag" value="false">
						<span style="padding-left: 5px;">支付失败</span>
						<span style="padding-left: 5px;">，支付订单号：<span>${order.orderNo }</span></span>
					</div>
					<div>
						点击<a href="${ctx}/home?action=index">返回首页</a>或者<a href="${ctx}/home?action=index">重新支付</a>
					</div>
					<div class="clear"></div>
					</td>
				</tr>
				</table>
		</div>
</div>
    <%@ include file="/common/client/footer.jsp" %>
</div>
</body>
</html>
