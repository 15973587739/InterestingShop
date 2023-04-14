<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/common/client/header.jsp" %>
    <title>易买网</title>
</head>
<body>
<!--Begin Header Begin-->
<%@ include file="/common/client/searchBar.jsp" %>
<!--End Header End-->
<!--Begin Menu Begin-->
<div class="menu_bg">
    <div class="menu">
        <!--Begin 商品分类详情 Begin-->
        <div class="nav">
            <div class="nav_t">全部商品分类</div>
            <div class="leftNav">
                <ul>
                    <c:forEach items="${classifyVoList}" var="temp" >
                        <li>
                            <div class="fj">
                        <span class="n_img"><span></span>
                            <img src="${ctx}/statics/images/${temp.classify.icon}"/></span>
                                <span class="fl">${temp.classify.classifyName}</span>
                            </div>
                            <div class="zj">
                                <div class="zj_l">
                                    <c:forEach items="${temp.classifyVoList}" var="vo">
                                        <div class="zj_l_c">
                                            <h2>
                                                <a href="${ctx}/goods?action=queryGoodsList&category=${vo.classify.id}&level=2">${vo.classify.classifyName}</a>
                                            </h2>
                                            <c:forEach items="${vo.classifyVoList}" var="vo2">
                                                <a href="${ctx}/goods?action=queryGoodsList&category=${vo2.classify.id}&level=3">${vo2.classify.classifyName}</a> |
                                            </c:forEach>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <ul class="menu_r">
            <li><a href="${ctx}/home?action=index">首页</a></li>
            <c:forEach items="${classifyVoList}" var="temp">
                <li><a href="${ctx}/goods?action=queryGoodsList&level=1&category=${temp.classify.id}">${temp.classify.classifyName}</a></li>
            </c:forEach>
        </ul>
        <div class="m_ad">中秋送好礼！</div>
        <!--End 商品分类详情 End-->
    </div>
</div>
<!--End Menu End-->
<div class="i_bg bg_color">
    <div class="i_ban_bg">
        <!--Begin Banner Begin-->
        <div class="banner">
            <div class="top_slide_wrap">
                <ul class="slide_box bxslider">
                    <li><img src="${ctx}/statics/images/ban1.jpg" width="740" height="401"/></li>
                    <li><img src="${ctx}/statics/images/ban1.jpg" width="740" height="401"/></li>
                    <li><img src="${ctx}/statics/images/ban1.jpg" width="740" height="401"/></li>
                </ul>
                <div class="op_btns clearfix">
                    <a href="#" class="op_btn op_prev"><span></span></a>
                    <a href="#" class="op_btn op_next"><span></span></a>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            var jq2 = jQuery.noConflict();
            (function () {
                $(".bxslider").bxSlider({
                    auto: true,
                    prevSelector: jq2(".top_slide_wrap .op_prev")[0], nextSelector: jq2(".top_slide_wrap .op_next")[0]
                });
            })();
        </script>
        <!--End Banner End-->
        <div class="inotice">
            <div class="notice_t">
                <span class="fr"><a href="${ctx}/manager/notice?action=queryNoticeList">更多 ></a></span>新闻资讯
            </div>
            <ul>
                <c:forEach items="${notice}" var="temp">
                    <li><span>[ 公告 ]</span>
                        <a href="${ctx}/manager/notice?action=noticeDetail&id=${temp.id}">${temp.title}</a>
                    </li>
                </c:forEach>
            </ul>
            <div class="charge_t">
                话费充值
                <div class="ch_t_icon"></div>
            </div>
            <form>
                <table border="0" style="width:205px; margin-top:10px;" cellspacing="0" cellpadding="0">
                    <tr height="35">
                        <td width="33">号码</td>
                        <td><input type="text" value="" class="c_ipt"/></td>
                    </tr>
                    <tr height="35">
                        <td>面值</td>
                        <td>
                            <select class="jj" name="city">
                                <option value="0" selected="selected">100元</option>
                                <option value="1">50元</option>
                                <option value="2">30元</option>
                                <option value="3">20元</option>
                                <option value="4">10元</option>
                            </select>
                            <span style="color:#ff4e00; font-size:14px;">￥99.5</span>
                        </td>
                    </tr>
                    <tr height="35">
                        <td colspan="2"><input type="submit" value="立即充值" class="c_btn"/></td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <div class="content mar_20">
        <img src="${ctx}/statics/images/mban_1.jpg" width="1200" height="110"/>
    </div>
    <!--Begin 进口 生鲜 Begin-->
    <c:forEach items="${classifyVoList}" var="temp" end="${fn:length(classifyVoList)}" varStatus="status">
        <div class="i_t mar_10">
            <span class="floor_num">${status.index+1}F</span>
            <span class="fl">${temp.classify.classifyName}</span>
        </div>
        <div class="content">
            <div class="fresh_left">
                <div class="fre_ban">
                    <div id="imgPlay1">
                        <ul class="imgs" id="actor1">
                            <li><a href="#"><img src="${ctx}/statics/images/fre_r.jpg" width="211" height="286"/></a></li>
                            <li><a href="#"><img src="${ctx}/statics/images/fre_r.jpg" width="211" height="286"/></a></li>
                            <li><a href="#"><img src="${ctx}/statics/images/fre_r.jpg" width="211" height="286"/></a></li>
                        </ul>
                        <div class="prevf">上一张</div>
                        <div class="nextf">下一张</div>
                    </div>
                </div>
                <div class="fresh_txt">
                    <div class="fresh_txt_c">
                        <c:forEach items="${temp.classifyVoList}" var="vo">
                            <a href="${ctx}/goods?action=queryGoodsList&category=${vo.classify.id}&level=2">${vo.classify.classifyName}</a>
                        </c:forEach>
                    </div>
                </div>
            </div>
            <div class="fresh_mid">
                <ul>
                    <c:forEach items="${temp.goodsList}" var="goodsVo">
                        <li>
                            <div class="name"><a href="#">${goodsVo.goodsName}</a></div>
                            <div class="price">
                                <font>￥<span>${goodsVo.price}</span></font> &nbsp;
                            </div>
                            <div class="img">
                                <a href="${ctx}/goods?action=queryGoodsDetail&id=${goodsVo.id}">
                                    <img src="${ctx}/files/${goodsVo.fileName}" width="185"  height="155"/>
                                </a>
                            </div>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div class="fresh_right">
                <ul>
                    <li><a href="#"><img src="${ctx}/statics/images/fre_b1.jpg" width="260" height="220"/></a></li>
                    <li><a href="#"><img src="${ctx}/statics/images/fre_b2.jpg" width="260" height="220"/></a></li>
                </ul>
            </div>
        </div>
    </c:forEach>
    <%@ include file="/common/client/footer.jsp" %>
</div>
</body>
</html>
