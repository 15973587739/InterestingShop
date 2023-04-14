<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <%@ include file="/common/client/header.jsp" %>
    <script type="text/javascript" src="${ctx}/statics/js/common/n_nav.js"></script>
    <script src="${ctx}/statics/js/cart/cart.js"></script>
    <title>易买网</title>
</head>
<body>
<!--Begin Header Begin-->
<div id="searchBar">
    <%@ include file="/common/client/searchBar.jsp" %>
</div>
<!--Begin Header Begin-->
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
    <div class="postion">
    </div>
    <div class="content">
        <div id="tsShopContainer">
            <div id="tsImgS">
                <a href="${ctx}/files/${goods.fileName}" title="Images" class="MagicZoom" id="MagicZoom">
                    <img src="${ctx}/files/${goods.fileName}" width="390" height="390"/>
                </a>
            </div>
        </div>
        <div class="pro_des">
            <div class="des_name">
                <input type="hidden" value="${goods.id}"  name="entityId" class="n_ipt"/>
                <p>${goods.goodsName}</p>
                “开业巨惠，北京专柜直供”，不光低价，“真”才靠谱！
            </div>
            <div class="des_price">
                本店价格：<b>￥${goods.price}</b><br/>
            </div>
           <div class="des_price">
                库存：<b>${goods.stock}</b><br/>
            </div>
            <div class="des_choice">
                <span class="fl">型号选择：</span>
                <ul>
                    <li class="checked">30ml
                        <div class="ch_img"></div>
                    </li>
                    <li>50ml
                        <div class="ch_img"></div>
                    </li>
                    <li>100ml
                        <div class="ch_img"></div>
                    </li>
                </ul>
            </div>
            <div class="des_choice">
                <span class="fl">颜色选择：</span>
                <ul>
                    <li>红色
                        <div class="ch_img"></div>
                    </li>
                    <li class="checked">白色
                        <div class="ch_img"></div>
                    </li>
                    <li>黑色
                        <div class="ch_img"></div>
                    </li>
                </ul>
            </div>
            <br>
            <br>
            <div class="des_join">
                <div class="j_nums">
                    <input type="text"   value="1"  name="buyNum" class="n_ipt"/>
                    <input type="button" value="" onclick="addUpdate(jq(this));" class="n_btn_1"/>
                    <input type="button" value="" onclick="jianUpdate(jq(this));" class="n_btn_2"/>
                    <input type="hidden" name="goodsStock" value="${goods.stock}">
                </div>
                <span class="fl">
                     <img src="${ctx}/statics/images/j_car.png" onclick="addCart();"/>
                </span>
            </div>
        </div>
    </div>
    <div class="content mar_20">
        <div id="favoriteList">
        </div>
        <div class="l_list">
            <div class="des_border">
                <div class="des_tit">
                    <ul>
                        <li class="current"><a href="#p_attribute">商品属性</a></li>
                        <li><a href="#p_details">商品详情</a></li>
                    </ul>
                </div>
                <div class="des_con" id="p_attribute">
                    <table border="0" align="center" style="width:100%; font-family:'宋体'; margin:10px auto;"
                           cellspacing="0" cellpadding="0">
                        <tr>
                            <td>商品名称：${goods.goodsName}</td>
                            <td>商品价格：${goods.price}</td>
                            <td>品牌： 迪奥（Dior）</td>
                            <td>上架时间：2015-09-06 09:19:09 </td>
                        </tr>
                        <tr>
                            <td>商品毛重：160.00g</td>
                            <td>商品产地：法国</td>
                            <td>香调：果香调香型：淡香水/香露EDT</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td>容量：1ml-15ml </td>
                            <td>类型：女士香水，Q版香水，组合套装</td>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                    </table>
                </div>
            </div>
            <div class="des_border" id="p_details">
                <div class="des_t">商品详情</div>
                <div class="des_con">
                    <table border="0" align="center" style="width:745px; font-size:14px; font-family:'宋体';" cellspacing="0" cellpadding="0">
                        <tr>
                            <td>
                                ${goods.goodsDesc}
                            </td>
                        </tr>
                    </table>
                    <p align="center">
                        <img src="${ctx}/files/${goods.fileName}" width="185" height="155">
                    </p>
                </div>
            </div>
        </div>
    </div>
    <script>
        favoriteList();
    </script>
    <!--End 弹出层-收藏成功 End-->
    <%@ include file="/common/client/footer.jsp" %>
    <!--Begin 弹出层-加入购物车 Begin-->
</div>
</body>
</html>
