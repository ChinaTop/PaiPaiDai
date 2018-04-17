<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>保守型区_默认排序_我要借出_第1页__拍拍贷</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="保守型区,我要借出，拍拍贷-一家互联网金融网贷平台 提供网络借款，投资 小额借款,短期借款,个人借款,无抵押借款服务 拍拍贷借贷投资，获得收益回报，低门槛" />
    <meta name="keywords" content="保守型区,我要借出,拍拍贷" />
    <link rel="stylesheet" type="text/css" href="//www.ppdaicdn.com/2014/css/basic.css?v=20180108" />
    <link rel="stylesheet" type="text/css" href="//www.ppdaicdn.com/2014/css/layout.css?v=20180108" />
    <link href="//www.ppdaicdn.com/css/min/validation-min.css?v=20180108" rel="stylesheet" />
    <link rel="shortcut icon" href="//www.ppdaicdn.com/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="CSS/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="//www.ppdaicdn.com/invest/2016/css/borrow.css?v=20180208"/>

</head>
<body>
<div class="top PPD_header_nav">
    <div class="top_inner w1188center clearfix PPD_login_status">
    </div>
</div>


<!--头部开始-->
<div class="mainNav">
    <div class="mainNav_inner clearfix w1000center">
        <h1 class="logo">
            <a href="index.jsp">
                <img src="https://ac.ppdaicdn.com/img/logo.png" alt="" />
            </a>
        </h1>
        <ul id="tabIcon">
            <li class="hasSubMenu">
                <a href="FundServlet"  category="Lend" id="top-menu-lend">我要投资</a>
            </li>

            <li class="hasSubMenu">
                <a href="borrowing.jsp"  category="Borrow">我要借款</a>
            </li>

            <li class="hasSubMenu">
                <a href="QueryServlet?action=remain"  category="Account">我的账户</a>
            </li>

            <li class="hasSubMenu">
                <a href="//tz.ppdai.com/compliance/baseInfo" id="top-menu-about">信息披露</a>
            </li>

            <li class="hasSubMenu">
                <a id="top-menu-about" href="//group.ppdai.com/forum.php" target="_blank">客服</a>
            </li>
        </ul>
    </div>
</div>
<!--头部结束-->
<div class="dv_tab clearfix">
        <ul class="tab_left">
            <li class="selected"><a href="//invest.ppdai.com/loan/listnew">散标列表</a></li>
        </ul>
    </div>
<!--筛选条件-->
<div class="w1000center">
        <form action="FundServlet" method="post" name="f">
            <input name="action" type="hidden" value="find">
            <div class="filtersection clearfix">
                <div class="filter-left">
                    <p datatype='1' class="option_must">
                        <label class="options_title">风险等级（必须）</label>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="risklevel" value="保守型" checked >保守型
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="risklevel" value="平衡型" >平衡型
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="risklevel" value="进取型" >进取型
                        &nbsp;&nbsp;&nbsp;&nbsp;
                    </p>

                    <p datatype='2'>
                        <label class="options_title" style="position:relative;z-index:1;">协议利率</label>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="rate" value="6-13" onclick="uncheck(this)">6-13%
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="rate" value="14-17" onclick="uncheck(this)">14-17%
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="rate" value="18-21" onclick="uncheck(this)">18-21%
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="rate" value="22-50" onclick="uncheck(this)">22-50%
                        &nbsp;&nbsp;&nbsp;&nbsp;
                    </p>

                    <p datatype='3'>
                        <label class="options_title">购买金额</label>
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="investamount" value="1000-3000" onclick="uncheck(this)">1000-3000
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="investamount" value="3001-5000" onclick="uncheck(this)">3001-5000
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="investamount" value="5001-8000" onclick="uncheck(this)">5001-8000
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="investamount" value="8001-10000" onclick="uncheck(this)">8001-10000
                        &nbsp;&nbsp;&nbsp;&nbsp;
                        <button class="btn btn-primary" type="submit" >筛选</button>
                    </p>
                </div>
            </div>
        </form>
    </div>
<!--筛选条件结束-->
<div class="borrowStatic clearfix">
    <div class="Static_center">
        符合筛选条件标的数<span class="sp_num_col">${count}</span>个
    </div>
</div>

<table class="table table-striped table-hover">
            <thead>
            <tr>
                <td>No.</td>
                <td><a href="FundServlet?risklevel=${risklevel}&rate=${rate}&investamount=${investamount}&pageno=${pageno}&sort=name&order=asc">基金名称 ^</a></td>
                <td><a href="FundServlet?risklevel=${risklevel}&rate=${rate}&investamount=${investamount}&pageno=${pageno}&sort=rate&order=asc">协议利率(%) ^</a></td>
                <td><a href="FundServlet?risklevel=${risklevel}&rate=${rate}&investamount=${investamount}&pageno=${pageno}&sort=investamount&order=asc">购买金额 ^</a></td>
                <td><a href="FundServlet?risklevel=${risklevel}&rate=${rate}&investamount=${investamount}&pageno=${pageno}&sort=deadline&order=asc">期限 ^</a></td>
                <td><a>进度</a></td>
                <td>操作</td>
            </tr>
            </thead>

            <tfoot>
            <tr>
                <td>No.</td>
                <td><a href="FundServlet?risklevel=${risklevel}&rate=${rate}&investamount=${investamount}&pageno=${pageno}&sort=name&order=desc">基金名称 v</a></td>
                <td><a href="FundServlet?risklevel=${risklevel}&rate=${rate}&investamount=${investamount}&pageno=${pageno}&sort=rate&order=desc">协议利率(%) v</a></td>
                <td><a href="FundServlet?risklevel=${risklevel}&rate=${rate}&investamount=${investamount}${pageno}&sort=investamount&order=desc">购买金额 v</a></td>
                <td><a href="FundServlet?risklevel=${risklevel}&rate=${rate}&investamount=${investamount}${pageno}&sort=deadline&order=desc">期限 v</a></td>
                <td><a>进度</a></td>
                <td>操作</td>
            </tr>
            </tfoot>

            <tbody>
            <c:forEach var="fund" items="${requestScope.funds}" varStatus="row">
                <tr>
                    <td>${row.index + 1}</td>
                    <td>${fund.name}</td>
                    <td>
                        <fmt:formatNumber value="${fund.rate}"/>
                    </td>
                    <td><fmt:formatNumber value="${fund.investamount}" pattern="#####.00"/></td>
                    <td>${fund.deadline}</td>
                    <td>已有${fund.investman}人购买</td>
                    <td>
                        <button type="button" onclick="buyfund(${fund.fundid},${fund.investamount},${fund.investman},'${fund.name}',${fund.rate},${fund.deadline})"
                                class="btn btn-info">
                            购买
                        </button>
                    </td>
                </tr>
            </c:forEach>

            <tr>
                <td colspan="5">
                    <a href="FundServlet?risklevel=${risklevel}&rate=${rate}&investamount=${investamount}&pageno=1&sort=${sort}&order=${order}">首页</a>
                    &nbsp;&nbsp;
                    <a href="FundServlet?risklevel=${risklevel}&rate=${rate}&investamount=${investamount}&pageno=${pageno-1}&sort=${sort}&order=${order}">上一页</a>
                    &nbsp;&nbsp;
                    <a href="FundServlet?risklevel=${risklevel}&rate=${rate}&investamount=${investamount}&pageno=${pageno+1}&sort=${sort}&order=${order}">下一页</a>
                    &nbsp;&nbsp;
                    <a href="FundServlet?risklevel=${risklevel}&rate=${rate}&investamount=${investamount}&pageno=${maxpage}&sort=${sort}&order=${order}">尾页</a>
                    &nbsp;&nbsp;
                    第 ${pageno} 页,共 ${maxpage} 页
                    &nbsp;&nbsp;
                    <input id="pageno" type="number" onblur="fundgo()">
                </td>
            </tr>
            </tbody>
        </table>


<!--底部-->
<div class="footer">
    <div class="footer_footerBottom">
        <ul class="footer_footerBottomNav clearfix">
            <li><span class="webindex"></span><a href="//www.ppdai.com/">网站首页</a>|</li>
            <li><span class="aboutus"></span><a href="//www.ppdai.com/help/aboutus">关于我们</a>|</li>
            <li><span class="mapsite"></span><a href="//www.ppdai.com/home/sitemap">网站地图</a>|</li>
            <li><span class="webservice"></span><a href="//help.ppdai.com/">客服大厅</a>|</li>
            <li class="nomr"><span class="onlneserve"></span><a href="//help.ppdai.com/customer?channel_id=2" target="_blank">在线咨询</a></li>
        </ul>
        <p>Copyright Reserved 2007-2018©拍拍贷（www.ppdai.com）&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;沪ICP备05063398号-4&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;上海拍拍贷金融信息服务有限公司</p>
    </div>
</div>

<script>
    function uncheck(radio)
    {
        if (radio.tag==1)
        {
            radio.checked=false;
            radio.tag=0;
        }
        else
        {
            radio.checked=true;
            radio.tag=1
        }
    }

    function fundgo() {
        var pageno = document.getElementById("pageno").value
        location.href = "FServlet?risklevel=${risklevel}&rate=${rate}&investamount=${investamount}&pageno="+pageno+"&sort=${sort}&order=${order}"
    }

    function buyfund(fundid,investamount,investman,name,rate,deadline) {
        window.location.href = "FundServlet?action=buyfund&fundid="+fundid+"&investamount="+investamount+"&investman="+investman+"&name="+name+"&rate="+rate+"&deadline="+deadline
    }

</script>
</body>
</html>

