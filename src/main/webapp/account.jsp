<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2018/3/20
  Time: 16:33
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
    <title>我的账户，解决个人小额借款</title>

    <link rel="stylesheet" type="text/css" href="//www.ppdaicdn.com/2014/css/basic.css?v=2018" />
    <link rel="stylesheet" type="text/css" href="//www.ppdaicdn.com/2014/css/layout.css?v=2018" />
    <link href="//www.ppdaicdn.com/css/min/validation-min.css?v=2018" rel="stylesheet" />
    <link rel="shortcut icon" href="//www.ppdaicdn.com/favicon.ico" type="image/x-icon" />


    <style>
        .mainNav_inner.clearfix.w1100center
        {
            margin: 0 auto;
            width: 1100px;
        }
    </style>
    <link href="//www.ppdaicdn.com/2014/css/account.css?v=2018" rel="stylesheet" />
    <link href="//www.ppdaicdn.com/2014/css/jr.css?v=2018" rel="stylesheet" />

</head>
<body>
<c:if test="${empty sessionScope.username}">
    <c:redirect url="login.jsp"/>
</c:if>
<!-- 顶部登录注册状态栏目 -->
<div class="PPD_header_nav top" style="margin-bottom: 0;">
    <div class="PPD_login_status">
        <h5>
            Hi, ${sessionScope.username}
            &nbsp;&nbsp;
            <a href="UsersServlet?action=logout">退出</a>
        </h5>
    </div>
</div>
<!-- 顶部登录注册状态栏目 -->
<!--头部开始-->
<div class="mainNav">
    <div class="mainNav_inner clearfix w1100center">
        <h1 class="logo">
            <a href="index.jsp">
                <img src="https://ac.ppdaicdn.com/img/logo.png" alt="" />
            </a>
        </h1>
        <ul id="tabIcon">
            <li class="hasSubMenu">
                <a href="FundServlet"  category="Lend" id="top-menu-lend">我要投资</a>
                <div class="subMenu">
                    <a href="FundServlet">理财投资</a>
                </div>
            </li>

            <li class="hasSubMenu">
                <a href="borrowing.jsp"  category="Borrow">我要借款</a>
                <div class="subMenu">
                    <a href="borrowing.jsp">我要借款</a>
                </div>
            </li>

            <li class="hasSubMenu">
                <a href="QueryServlet" class='tabon' category="Account">我的账户</a>
                <div class="subMenu">
                    <a href="QueryServlet">我的账户</a>
                </div>
            </li>

            <li class="hasSubMenu">
                <a href="//tz.ppdai.com/compliance/baseInfo" >信息披露</a>
                <div class="subMenu">
                    <a href="//tz.ppdai.com/compliance/baseInfo" target="_blank">信息披露</a>
                </div>
            </li>

            <li class="hasSubMenu">
                <a  href="help.jsp" target="_blank">客服</a>
                <div class="subMenu">
                    <a href="help.jsp">客服大厅</a>
                </div>
            </li>

            <li class="hasSubMenu">
                <a id="top-menu-about" href="http://ir.ppdai.com" target="_blank">INVESTORS</a>
            </li>
        </ul>
    </div>
</div>

<!--头部结束-->


<!--<div class="w100per maintop"></div>-->
<div class="my-frame">
    <!-- 子导航-->

    <div>
        <ul style="border: medium none ! important; width: 1049px;" class="breadcrumb">
            <li><a href="index.jsp">首页</a> <span class="divider">&gt;</span></li>
            <li class="active">我的账户</li>

        </ul>
    </div>
    <div class="clearfix">
        <!-- 左菜单栏-->
        <!-- 左菜单栏-->
        <!-- 左菜单栏 start -->
        <div class="my-f-left fl">
            <div class="my-f-l-nav my-f-l-nav-sd">我的账户</div>
            <ul class="my-f-l-list">
                <li><a href="account.jsp" >我的账户首页</a></li>

            </ul>

            <div class="my-f-l-nav ">资金管理</div>
            <ul class="my-f-l-list">
                <li><a href="QueryServlet?action=list">资金记录</a></li>
                <li><a href="FundServlet?action=myfund" {4}>我的基金</a></li>
            </ul>


        </div>
        <!-- 左菜单栏 end -->

        <div class="my-f-right c666666 fr">
            <div class="userInfo">
                <a href="//www.ppdai.com/info/userface" target="_blank" class="userInfo_userHeader">
                    <img src="//static.ppdai.com/app_themes/images/head/nophoto_80.gif" />
                </a>

                <div class="userInfo_content">
                    <div class="userInfo_userName">
                        <h5>
                            您好， ${sessionScope.username}
                        </h5>
                    </div>

                    <br />


                </div>


            </div>
            <div class="stressBox">
                <div class="my-bor-frame col_2 last">
                    <div class="my-bor-gobidfr bgf8">
                        <div class="my-ac-ctTitle clearfix"><span class="fs18 fl">借款额度</span></div>
                    </div>
                    <div class="accountLimit">
                        <span class="text">可用额度</span>
                        <span class="airBubble">
                            <img src="//www.ppdaicdn.com/2014/img/question.png" />
                            <div class="tag">
                                <div class="arrow">
                                    <em></em><span></span>
                                </div>
                                目前可申请借款的额度，不可提现
                            </div>
                        </span>
                        <span class="money gray w115"><span class="symbol">¥</span>0.00</span>

                        <span class="text split">总额度</span>可用余额
                        <span class="airBubble">
                                <img src="//www.ppdaicdn.com/2014/img/question.png" />
                                <div class="tag">
                                    <div class="arrow">
                                        <em></em><span></span>
                                    </div>
                                    所有借款还清后，最多可借的额度，不可提现
                                </div>
                            </span>
                        <span class="text black fs18 arial">¥ 0</span>
                        <div class="buttons">
                            <a href="borrowing.jsp" target="_self" class="borrowButton">立 即 借 款</a>
                        </div>

                    </div>
                </div>
                <div class="my-bor-frame col_2 first">
                    <div class="my-bor-gobidfr bgf8">
                        <div class="my-ac-ctTitle clearfix"><span class="fs18 fl">账户余额</span></div>
                    </div>
                    <div class="accountLimit" style="padding-top: 3px;">
                        <span class="text" style="margin-top: 8px;">可用余额</span>
                        <span class="airBubble" style="margin-top: 8px;">
                            <img src="//www.ppdaicdn.com/2014/img/question.png" />
                            <div class="tag">
                                <div class="arrow">
                                    <em></em><span></span>
                                </div>
                                可用于还款，提现
                            </div>
                        </span>
                        <span class="money gray w170" style="margin-top: 0;"><span class="symbol">¥</span>${requestScope.remaining}</span>

                        <div class="buttons" style="height: 45px; padding-top: 0;">
                            <a href="withdraw.jsp" target="_blank" class="moneyButton">提现</a>
                            <a href="recharge.jsp" target="_blank" class="moneyButton">充值</a>
                        </div>
                    </div>
                </div>

            </div>




            <input type="hidden" id="IsFinishSwitchBorrowLoan" value="0" />





        </div>
    </div>
</div>

</body>
</html>
