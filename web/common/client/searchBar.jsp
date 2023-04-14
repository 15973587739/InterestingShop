<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
<div class="top">
    <div class="logo">
        <a href="${ctx}/home?action=index"><img src="${ctx}/statics/images/logo.png"></a>
    </div>
    <div class="search">
        <form action="${ctx}/goods?action=queryGoodsList" method="post">
            <input type="text" value="${keyWord}" name="keyWord" class="s_ipt">
            <input type="submit" value="搜索" class="s_btn">
        </form>
        <!--推荐最热商品-->
    </div>
    <div class="i_car">
        <div class="car_t">
            购物车 [
            <span>
                <c:if test="${sessionScope.cart!=null && sessionScope.cart.shopGoodsList.size()>0}">
                    ${sessionScope.cart.shopGoodsList.size()}
                </c:if>
                <c:if test="${sessionScope.cart==null || sessionScope.cart.shopGoodsList.size()<=0}">
                    空
                </c:if>
            </span>]
        </div>
        <div class="car_bg">
            <!--Begin 购物车未登录 Begin-->
            <c:if test="${sessionScope.loginUser==null}">
                <div class="un_login">还未登录！<a href="${ctx}/login?action=toLogin" style="color:#ff4e00;">马上登录</a></div>
            </c:if>
            <c:if test="${sessionScope.loginUser!=null}">
                <div class="un_login">我的购物车</div>
            </c:if>
            <!--End 购物车未登录 End-->
            <!--Begin 购物车已登录 Begin-->
            <ul class="cars">
                <c:forEach items="${sessionScope.cart.shopGoodsList}" var="temp">
                    <li>
                        <div class="img"><a href="${ctx}/goods?action=queryGoodsDetail&id=${temp.goods.id}"><img src="${ctx}/files/${temp.goods.fileName}" width="58" height="58" /></a></div>
                        <div class="name"><a href="${ctx}/goods?action=queryGoodsDetail&id=${temp.goods.id}">${temp.goods.goodsName}</a></div>
                        <div class="price"><font color="#ff4e00">￥${temp.goods.price}</font> X${temp.buyNum}</div>
                    </li>
                </c:forEach>
            </ul>
            <div class="price_sum">共计&nbsp;<font color="#ff4e00">￥</font><span>${sessionScope.cart.sum}</span></div>
            <c:if test="${sessionScope.loginUser==null}">
                <div class="price_a"><a href="${ctx}/login?action=toLogin">去登录</a></div>
            </c:if>
            <c:if test="${sessionScope.loginUser!=null}">
                <div class="price_a"><a href="${ctx}/cart?action=toSettlement">去结算</a></div>
            </c:if>
            <!--End 购物车已登录 End-->
        </div>
    </div>
</div>
