<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
<table border="0" class="add_tab" style="width:930px;" cellspacing="0" cellpadding="0">
    <tr>
        <td width="135" align="right">分类级别</td>
        <td colspan="3" style="font-family:'宋体';" >
            <select class="jj" name="type" style="background-color:#f6f6f6;" id="type"
                    onchange="selectClassifyLevel(this);" <c:if test="${classify.id!=null}">disabled="disabled"</c:if>>
                <option value="" selected="selected">请选择...</option>
                <option value="1"
                        <c:if test="${classify.type==1}">selected="selected"</c:if> >一级分类
                </option>
                <option value="2"
                        <c:if test="${classify.type==2}">selected="selected"</c:if> >二级分类
                </option>
                <option value="3"
                        <c:if test="${classify.type==3}">selected="selected"</c:if> >三级分类
                </option>
            </select>
        </td>
    </tr>
        <tr  <c:if test="${classify.type==1}">style="display:none"</c:if>>
            <td width="135" align="right">一级分类</td>
            <td colspan="3" style="font-family:'宋体';">
                <select class="jj" name="classifyLevel1" style="background-color:#f6f6f6;"  id="classifyLevel1" onchange="queryClassifyList(this,'classifyLevel2');">
                    <option value="0" selected="selected">请选择...</option>
                    <c:forEach items="${classifyList1}" var="temp">
                        <option value="${temp.id}"
                                <c:if test="${classify.id==temp.id || classify.parentId==temp.id || parentclassify.parentId==temp.id}">selected="selected"</c:if> >${temp.classifyName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr <c:if test="${classify.type!=3}">style="display:none"</c:if>>
            <td width="135" align="right">二级分类</td>
            <td>
                <select class="jj" name="classifyLevel2" style="background-color:#f6f6f6;"
                        id="classifyLevel2">
                    <option value="0" selected="selected">请选择...</option>
                    <c:forEach items="${classifyList2}" var="temp">
                        <option value="${temp.id}"
                                <c:if test="${classify.id==temp.id || classify.parentId==temp.id}">selected="selected"</c:if> >${temp.classifyName}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
    <tr>
        <td align="right">分类名称</td>
        <td style="font-family:'宋体';">
            <input type="text" value="${classify.classifyName}" class="add_ipt" id="classifyName"/>（必填）
            <input type="hidden" name="id" value="${classify.id}" id="id">
        </td>
    </tr>
</table>
<p align="right">
    <br>
    <a href="javascript:void(0);" onclick="saveOrUpdate();" class="add_b">确认修改</a>
</p>