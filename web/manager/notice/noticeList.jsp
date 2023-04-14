<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/common/client/header.jsp" %>
    <link type="text/css" rel="stylesheet" href="${ctx}/statics/css/style.css" />
</head>
<body>
<%@ include file="/common/manager/searchBar.jsp" %>
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
	<div class="m_content">
        <%@ include file="/common/manager/leftBar.jsp"%>
        <div class="m_right">
            <p></p>
            <div class="mem_tit">资讯列表</div>
            <table border="0" class="order_tab" style="width:930px; text-align:center; margin-bottom:30px;" cellspacing="0" cellpadding="0">
                <tbody>
                <tr>
                    <td width="20%">文章标题</td>
                    <td width="25%">创建时间</td>
                </tr>
                <c:forEach items="${noticeList}" var="temp">
                    <tr>
                        <td><a href="${ctx}/manager/notice?action=noticeDetail&id=${temp.id}">${temp.title}</a></td>
                        <td><fmt:formatDate value="${temp.createTime}" pattern="yyyy-MM-dd"/></td>
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
