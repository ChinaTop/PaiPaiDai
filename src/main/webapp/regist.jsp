<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>拍拍贷</title>
    <meta name="keywords" content="拍拍贷">
    <meta name="content" content="拍拍贷">
    <meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
    <link type="text/css" rel="stylesheet" href="CSS/login.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>

</head>
<body class="login_bj" >

<div class="zhuce_body">
    <div class="zhuce_kong">
        <div class="zc">
            <div class="bj_bai">
                <h3>欢迎注册</h3>
                <form action="UsersServlet" method="post" name="f">
                    <input type="hidden" name="action" value="save">
                    <input name="username" type="text" class="kuang_txt username" placeholder="用户名" id="i1">
                    <small></small>
                    <input name="password" type="password" class="kuang_txt password"  placeholder="密码">
                    <input name="id" type="text" class="kuang_txt id" placeholder="身份证号" id="i3">
                    <small></small>
                    <input name="bankid" type="text" class="kuang_txt bankid" placeholder="银行卡号" id="i4">
                    <small></small>
                    <div class ="form-group row">
                    <input name="checkcode" type="text" id="checkcode" placeholder="验证码" class="col-sm-2">
                    <img src="randomCodeServlet"/>
                    </div>
                    <div>
                        <input name="" type="checkbox" value=""><span>已阅读并同意<a href="#" target="_blank"><span class="lan">《拍拍贷投资理财协议协议》</span></a></span>
                    </div>
                    <input name="注册" type="submit" class="btn_zhuce" value="注册">

                </form>
            </div>
            <div class="bj_right">
                <p>使用以下账号直接登录</p>
                <a href="#" class="zhuce_qq">QQ注册</a>
                <a href="#" class="zhuce_wb">微博注册</a>
                <a href="#" class="zhuce_wx">微信注册</a>
                <p>已有账号？<a href="login.html">立即登录</a></p>

            </div>
        </div>
    </div>

</div>

<script src="js/jquery-3.3.1.min.js"></script>
<script src="js/my.js"></script>

</body>
</html>
