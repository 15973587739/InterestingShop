<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
    <c:if test="${recentGoods!=null}">
        <div class="l_history">
        <div class="fav_t">我的收藏</div>
        <c:forEach items="${recentGoods}" var="temp">
        <ul>
            <li>
                <div class="img">
                    <a href="${ctx}/goods?action=queryGoodsDetail&id=${temp.id}">
                        <img src="${ctx}/files/${temp.fileName}" width="185" height="162"/>
                    </a>
                </div>
                <div class="name">
                    <a href="${ctx}/goods?action=queryGoodsDetail&id=${temp.id}">${temp.goodsName}</a>
                </div>
                <div class="price">
                    <font>￥<span>${temp.price}</span></font> &nbsp;
                </div>
            </li>
        </ul>
    </c:forEach>
    </c:if>
</div>