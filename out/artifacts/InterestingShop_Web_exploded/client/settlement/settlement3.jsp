<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
	var contextPath = "${ctx}";
	refreshCart();
</script>
<div class="content mar_20">
	<img src="${ctx}/statics/images/img3.jpg" />
</div>
<div class="content mar_20">
	<!--Begin 银行卡支付 Begin -->
	<form id="payForm" action="${ctx}/Alipay?action=pay" method="post">
		<div class="warning">
			<table border="0" style="width: 1000px; text-align: center;"
				cellspacing="0" cellpadding="0">
				<tr height="35">
					<td style="font-size: 18px;">感谢您在本店购物！您的订单已提交成功，请记住您的订单号: <font
						color="#ff4e00" id="orderNo">${currentOrder.orderNo}</font>
						<input type="hidden" name="orderNo" value="${currentOrder.orderNo}">
					</td>
				</tr>
				<tr>
					<td	style="font-size: 14px; font-family: '宋体'; padding: 10px 0 20px 0; border-bottom: 1px solid #b6b6b6;">
						
						您选定的配送方式为: <font color="#ff4e00">申通快递</font>； &nbsp;
						
						<input type="hidden" name="orderSize" value="合计共${sessionScope.cart.shopGoodsList.size() }笔订单">
						<input type="hidden" name="amount" value="${currentOrder.amount}">
						&nbsp;您的应付款金额为: 
						<font id="cost" color="#ff4e00">￥${currentOrder.amount}</font>
					</td>
				</tr>
				<tr>
					<td style="padding: 20px 0 30px 0; font-family: '宋体';">
						收款人信息：全称 ${sessionScope.loginUser.account}；
						地址
						${currentOrder.userAddress} ；
					</td>
				</tr>
				</table>
				<div class="listMin1" text-align="right">
			<div class="mod_form sy_cont" style="text-align:right; padding-right:180px">
				<div class="inner pay_type form">
					<div class="tab">
						<label class="form-label" style="padding-right:230px">支付方式：</label> </br>
						<div id="paytab" style="margin-top: 20px;">
							<input id="wx" type="radio" name="selectPay" checked="checked" value="1">
							<label for="wx">微信支付</label> 
							<input id="zfb" type="radio" name="selectPay" value="2">
							<label for="zfb">支付宝</label>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button red" onclick="toPay()">立即支付</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>
	</form>
</div>

