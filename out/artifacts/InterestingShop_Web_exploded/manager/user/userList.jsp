<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/common/client/header.jsp" %>
    <script src="${ctx}/statics/js/manager/manager.js"></script>
</head>
<body>
<!--End Header End-->
<%@ include file="/common/manager/searchBar.jsp" %>
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
    <div class="m_content">
        <%@ include file="/common/manager/leftBar.jsp" %>
        <div class="m_right" id="content">
            <div class="mem_tit">用户列表</div>
            <p align="right">
                <a href="${ctx}/manager/user?action=toAddUser"  class="add_b">添加用户</a>
                <br>
            </p>
            <br>
            <table border="0" class="order_tab" style="width:930px; text-align:center; margin-bottom:30px;"
                   cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <td width="10%">用户名称</td>
                    <td width="10%">真实姓名</td>
                    <td width="5%">性别</td>
                    <td width="5%">类型</td>
                    <td width="5%" colspan="2">操作</td>
                </tr>
                <c:forEach items="${userList}" var="temp">
                    <tr>
                        <td>${temp.account}</td>
                        <td>${temp.nickName}</td>
                        <td>
                            <c:choose>
                                <c:when test="${temp.gender==1}">
                                    男
                                </c:when>
                                <c:otherwise>
                                    女
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${temp.type==1}">
                                    管理员
                                </c:when>
                                <c:otherwise>
                                    用户
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="${ctx}/manager/user?action=toUpdateUser&id=${temp.id}">修改</a>
                        </td>
                        <td>
                        	<c:if test="${sessionScope.loginUser.id!=temp.id}">
                           	 <a href="javascript:void(0);" onclick="deleteUserId('${temp.id}');" target="_blank">删除</a>
                        	</c:if>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%@ include file="/common/client/pagerBar.jsp" %>
        </div>
    </div>
    <%@ include file="/common/client/footer.jsp" %>
</div>
</body>
</html>


