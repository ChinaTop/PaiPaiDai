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
<br><br>
<div>
    <form action="QueryServlet" method="post" name="f">
        <input type="hidden" name="action" value="recharge">
        <div class="form-group">
            <label class="col-form-label" for="username">用户名</label>
            <input type="text" name="username" id="username" placeholder="用户名" class="form-control" value="${username}">
        </div>

        <div class="form-group">
            <label class="col-form-label" for="rechargeamount">充值金额</label>
            <input type="text" name="rechargeamount" id="rechargeamount" placeholder="充值金额" class="form-control">
        </div>

        <div class="form-group">
            <label class="col-form-label" for="bankid">银行卡号</label>
            <input type="text" name="bankid" id="bankid" placeholder="银行卡号" class="form-control" value="${bankid}">
        </div>

        <button type="submit" class="btn btn-success btn-block">确认</button>
    </form>
</div>

</body>
</html>

