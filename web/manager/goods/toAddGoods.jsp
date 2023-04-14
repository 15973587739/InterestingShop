<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/common/client/header.jsp" %>
    <script src="${ctx}/statics/js/manager/manager.js"></script>
</head>
<body>
<%@ include file="/common/manager/searchBar.jsp" %>
<!--End Header End-->
<div class="i_bg bg_color">
    <!--Begin 用户中心 Begin -->
    <div class="m_content">
        <%@ include file="/common/manager/leftBar.jsp" %>
        <div class="m_right" id="content">
             <div class="mem_tit">
                <c:choose>
                    <c:when test="${empty goods.id || goods.id==0}">
                        添加商品
                    </c:when>
                    <c:otherwise>
                        修改商品
                    </c:otherwise>
                </c:choose>
            </div>
            
            <br>
            <form action="${ctx}/manager/goods?action=addGoods" method="post" enctype="multipart/form-data" id="goodsAdd" onsubmit="return checkGoods();">
            <table border="0" class="add_tab" style="width:930px;" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="135" align="right">一级分类</td>
                    <td colspan="3" style="font-family:'宋体';">
                        <select name="classifyLevel1Id" style="background-color:#f6f6f6;" id="classifyLevel1"
                                onchange="queryClassifyList(this,'classifyLevel2');">
                            <option value="" selected="selected">请选择...</option>
                            <c:forEach items="${classifyList1}" var="temp">
                                <option value="${temp.id}"
                                        <c:if test="${goods.classifyLevel1Id==temp.id}">selected="selected"</c:if> >${temp.classifyName}
								</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">二级分类</td>
                    <td>
                        <select name="classifyLevel2Id" style="background-color:#f6f6f6;"
                                id="classifyLevel2"
                                onchange="queryClassifyList(this,'classifyLevel3');">
                            <option value="0" selected="selected">请选择...</option>
                            <c:forEach items="${classifyList2}" var="temp">
                                <option value="${temp.id}"
                                        <c:if test="${goods.classifyLevel2Id==temp.id}">selected="selected"</c:if> >${temp.classifyName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">三级分类</td>
                    <td>
                        <select name="classifyLevel3Id" style="background-color:#f6f6f6;"
                                id="classifyLevel3">
                            <option value="0" selected="selected">请选择...</option>
                            <c:forEach items="${classifyList3}" var="temp">
                                <option value="${temp.id}"
                                        <c:if test="${goods.classifyLevel3Id==temp.id}">selected="selected"</c:if> >${temp.classifyName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">商品名称</td>
                    <td>
                        <input type="text" value="${goods.goodsName}" class="add_ipt" name="goodsName" id="goodsName"/>（必填）
                        <input type="hidden" name="id" value="${goods.id}" id="id">
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">商品图片</td>
                    <td>
                        <c:if test="${goods.fileName!=null && goods.fileName!=''}">
                            <img src="${ctx}/files/${goods.fileName}" width="50" height="50"/>
                        </c:if>
                        <input type="file" class="text" name="photo" /><span></span>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">单价</td>
                    <td>
                        <input type="text" value="${goods.price}" class="add_ipt" name="price" id="price"/>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">库存</td>
                    <td>
                        <input type="text" value="${goods.stock}" class="add_ipt" name="stock" id="stock"/>
                    </td>
                </tr>
                <tr>
                    <td width="135" align="right">描述</td>
                    <td>
                        <textarea name="goodsDesc">${goods.goodsDesc}</textarea>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <c:choose>
                            <c:when test="${empty goods.id}">
                                <input type="submit" value="商品上架" class="s_btn">
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="确定修改" class="s_btn">
                            </c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </table>
            </form>
        </div>
    </div>
    <%@ include file="/common/client/footer.jsp" %>
</div>
</body>
</html>


