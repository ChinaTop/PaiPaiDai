<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/3/21
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>资金记录 - 中国创新民间借贷网络平台，解决个人小额借款、短期借款问题。</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="拍拍贷，中国创新民间借贷网络平台，解决个人小额借款、短期借款问题。" />
    <meta name="keywords" content="网络借款,民间借贷,小额借款,无抵押借款,信用借款,网络借贷,借贷平台,拍拍贷,人人贷,投资,个人,投资,借款,借款,互联网金融,投融资" />
    <link rel="stylesheet" type="text/css" href="//www.ppdaicdn.com/2014/css/basic.css?20171115" />
    <link rel="stylesheet" type="text/css" href="//www.ppdaicdn.com/2014/css/layout.css?20171115" />
    <link href="//www.ppdaicdn.com/css/min/validation-min.css" rel="stylesheet" />
    <link rel="shortcut icon" href="//www.ppdaicdn.com/favicon.ico" type="image/x-icon" />
    <script src="//www.ppdaicdn.com/js/newRefer.js?v=0807"></script>



    <link href="https://www.ppdaicdn.com/2014/css/account.css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="https://www.ppdaicdn.com/2014/css/Helpcs.css" />
    <style>
        td.c39a1eatd a {
            color: #39a1ea;
        }
    </style>


</head>
<body>
<c:if test="${empty sessionScope.username}">
    <c:redirect url="login.jsp"/>
</c:if>
<div class="top PPD_header_nav">
    <div class="top_inner w1188center clearfix PPD_login_status">
        <h5>
            Hi, ${sessionScope.username}
            &nbsp;&nbsp;
            <a href="UsersServlet?action=logout">退出</a>
        </h5>
    </div>
</div>
<!--头部菜单-->
<!--头部开始-->
<div class="mainNav">
    <div class="mainNav_inner clearfix w1000center">
        <h1 class="logo">
            <a href="">
                <img src="" alt="" />
            </a>
        </h1>
        <ul id="tabIcon">
            <li class="hasSubMenu">
                <a href="FundServlet" {1} category="Lend" id="top-menu-lend">我要投资</a>
                <div class="subMenu">
                </div>
            </li>

            <li class="hasSubMenu">
                <a href="borrowing.jsp" {2} category="Borrow">我要借款</a>
                <div class="subMenu">
                </div>
            </li>

            <li class="hasSubMenu">
                <a href="QueryServlet?action=remain" {3} category="Account">我的账户</a>
            </li>

            <li class="hasSubMenu">
                <a href="//tz.ppdai.com/compliance/baseInfo" id="top-menu-about">信息披露</a>
            </li>

            <li class="hasSubMenu">
                <a  href="help.jsp" target="_blank">客服</a>
                <div class="subMenu">
                    <a href="help.jsp">客服大厅</a>
                </div>
            </li>

            <li class="hasSubMenu">
                <a  href="http://ir.ppdai.com" target="_blank">INVESTORS</a>
            </li>
        </ul>
    </div>
</div>

<!--头部结束-->



<!-- 顶部占位符-->
<div class="w100per maintop"></div>
<div class="my-frame">
    <!-- 子导航-->
    <div>
        <ul style="border: none !important;" class="breadcrumb">
            <li><a href="http://www.ppdai.com">首页</a> <span class="divider">&gt;</span></li>
            <li><a href="QueryServlet?action=remain">我的账户</a> <span class="divider">&gt;</span></li>
            <li><a href="http://www.ppdai.com/moneyhistory">资金管理</a> <span class="divider">&gt;</span></li>
            <li class="active">资金记录</li>
        </ul>
    </div>
    <div class="clearfix">
        <!-- 左菜单栏-->

        <div class="wrapleftnav fl" style="width:200px;">
            <!-- 左菜单栏 start -->
            <div class="my-f-left fl">
                <div class="my-f-l-nav {1}">我的账户</div>
                <ul class="my-f-l-list">
                    <li><a href="QueryServlet?action=remain" {2}>我的账户首页</a></li>

                </ul>

                <div class="my-f-l-nav {3}">资金管理</div>
                <ul class="my-f-l-list">
                    <li><a href="QueryServlet?action=list" {4}>资金记录</a></li>
                    <li><a href="FundServlet?action=myfund" {4}>我的基金</a></li>
                </ul>
            </div>
            <!-- 左菜单栏 end --> <!--左边菜单-->

        </div>



        <form action="QueryServlet" method="post" name="f">
            <div class="my-f-right fr">

                <div class="my-f-r-main c666666">
                    <div class="my-f-r-m-tab-search clearfix" style="margin-top:0;">
                        <div class="my-f-r-m-tab-sf fl">
                            <input type="hidden" name="action" value="list">
                            <label>资金类型</label>
                            <select name="type" id="type">
                                <option value=>所有</option>
                                <option value="充值">充值</option>

                                <option value="提现">提现</option>
                                <option value="基金购买">基金购买</option>
                                <option value="借款">借款</option>

                            </select>
                            <label>时间：</label>
                            <select name="time" id="time">
                                <option value="3">三天以内</option>
                                <option selected="selected" value="7">一周以内</option>
                                <option value="30">一个月以内</option>
                                <option value="90">三个月以内</option>
                                <option value="180">六个月以内</option>
                            </select>
                            <%--<input class="my-f-r-m-tab-btn" type="button" value="查询" id="btOk" onclick=" ClickOK() " />--%>
                            <button type="submit" class="btn btn-primary">查询</button>
                        </div>

                    </div>
                </div>
                <table cellpadding="0" cellspacing="0" class="receivetab c666666">
                    <thead>
                    <tr>
                        <td><a href="QueryServlet?type=${type}&time=${time}&pageno=${pageno}&pagesize=${pagesize}&sort=querydate&order=desc">日期</a></td>
                        <td><a href="QueryServlet?type=${type}&time=${time}&pageno=${pageno}&pagesize=${pagesize}&sort=querytype&order=desc">类型</a></td>
                        <td><a href="QueryServlet?type=${type}&time=${time}&pageno=${pageno}&pagesize=${pagesize}&sort=userexpenditure&order=desc">支出</a></td>
                        <td><a href="QueryServlet?type=${type}&time=${time}&pageno=${pageno}&pagesize=${pagesize}&sort=userdeposit&order=desc">存入</a></td>
                        <td><a href="QueryServlet?type=${type}&time=${time}&pageno=${pageno}&pagesize=${pagesize}&sort=remaining&order=desc">余额</a></td>
                        <td width="200">说明</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="query" items="${requestScope.querylist}" varStatus="row">
                        <tr>
                            <td>${query.date}</td>
                            <td>${query.type}</td>
                            <td><fmt:formatNumber value="${query.expenditure}" /></td>
                            <td><fmt:formatNumber value="${query.deposit}" /></td>
                            <td><fmt:formatNumber value="${query.remaining}" /></td>
                            <td>${query.explain}</td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <td colspan="5">
                            <select id="pagesize" onchange="cargo()">
                                <option value="10" ${pagesize==10?'selected':''}>10</option>
                                <option value="20" ${pagesize==20?'selected':''}>20</option>
                                <option value="50" ${pagesize==50?'selected':''}>50</option>
                                <option value="100" ${pagesize==100?'selected':''}>100</option>
                            </select>&nbsp;&nbsp;
                            <a href="QueryServlet?type=${type}&time=${time}&pageno=1&pagesize=${pagesize}&sort=${sort}&order=${order}">首页</a>
                            &nbsp;&nbsp;
                            <a href="QueryServlet?type=${type}&time=${time}&pageno=${pageno-1}&pagesize=${pagesize}&sort=${sort}&order=${order}">上一页</a>
                            &nbsp;&nbsp;
                            <a href="QueryServlet?type=${type}&time=${time}&pageno=${pageno+1}&pagesize=${pagesize}&sort=${sort}&order=${order}">下一页</a>
                            &nbsp;&nbsp;
                            <a href="QueryServlet?type=${type}&time=${time}&pageno=${maxpage}&pagesize=${pagesize}&sort=${sort}&order=${order}">尾页</a>
                            &nbsp;&nbsp;
                            第 ${pageno} 页,共 ${maxpage} 页
                            &nbsp;&nbsp;
                            <input id="pageno" type="number" onblur="cargo()">
                        </td>
                    </tr>
                    </tbody>
                </table>


            </div>
        </form>
    </div>
</div>

<script src="js/jquery-3.3.1.min.js"></script>
<script>
    function cargo() {
        var pagesize = document.getElementById("pagesize").value
        var pageno = document.getElementById("pageno").value
        location.href = "QueryServlet?pageno=" + pageno + "&pagesize=" + pagesize + "&sort=${sort}&order=${order}"
    }

    $(window).on("load", () => {
        var checktime=${time};
        var checktype='${type}';
        $("#time").val(checktime);
        $("#type").val(checktype);
    })
</script>
</body>
</html>
