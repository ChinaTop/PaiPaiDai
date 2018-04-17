<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <LINK rel="Bookmark" href="/favicon.ico" >
    <LINK rel="Shortcut Icon" href="/favicon.ico" />
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <script type="text/javascript" src="lib/PIE_IE678.js"></script>
    <![endif]-->
    <link href="CSS/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="CSS/H-ui.admin.css" rel="stylesheet" type="text/css" />
    <link href="skin/default/skin.css" rel="stylesheet" type="text/css" id="skin" />
    <link href="lib/Hui-iconfont/1.0.1/iconfont.css" rel="stylesheet" type="text/css" />
    <link href="CSS/style.css" rel="stylesheet" type="text/css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="http://lib.h-ui.net/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>拍拍贷后台管理系统</title>
    <meta name="keywords" content="H-ui.admin v2.3,H-ui网站后台模版,后台模版下载,后台管理系统模版,HTML后台模版下载">
    <meta name="description" content="H-ui.admin v2.3，是一款由国人开发的轻量级扁平化网站后台模板，完全免费开源的网站后台管理系统模版，适合中小型CMS后台系统。">
</head>
<body>
<header class="Hui-header cl"> <a class="Hui-logo l" title="H-ui.admin v2.3" href="/">拍拍贷后台管理系统</a></span>
    <nav class="mainnav cl" id="Hui-nav">
        <ul>
            <li class="dropDown dropDown_click"><a href="javascript:;" class="dropDown_A"><i class="Hui-iconfont">&#xe600;</i> 新增 <i class="Hui-iconfont">&#xe6d5;</i></a>
                <ul class="dropDown-menu radius box-shadow">
                    <li><a href="javascript:;" onclick="article_add('添加资讯','article-add.html')"><i class="Hui-iconfont">&#xe616;</i> 资讯</a></li>
                    <li><a href="javascript:;" onclick="picture_add('添加资讯','picture-add.html')"><i class="Hui-iconfont">&#xe613;</i> 图片</a></li>
                    <li><a href="javascript:;" onclick="product_add('添加资讯','product-add.html')"><i class="Hui-iconfont">&#xe620;</i> 产品</a></li>
                    <li><a href="javascript:;" onclick="member_add('添加用户','member-add.html','','510')"><i class="Hui-iconfont">&#xe60d;</i> 用户</a></li>
                </ul>
            </li>
        </ul>
    </nav>
    <ul class="Hui-userbar">
        <li>超级管理员</li>
        <li class="dropDown dropDown_hover"><a href="#" class="dropDown_A">${sessionScope.administrator} <i class="Hui-iconfont">&#xe6d5;</i></a>
            <ul class="dropDown-menu radius box-shadow">
                <li><a href="#">个人信息</a></li>
                <li><a href="#">切换账户</a></li>
                <li><a href="AdminServlet?action=logout">退出</a></li>
            </ul>
        </li>
        <li id="Hui-msg"> <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont" style="font-size:18px">&#xe68a;</i></a> </li>
        <li id="Hui-skin" class="dropDown right dropDown_hover"><a href="javascript:;" title="换肤"><i class="Hui-iconfont" style="font-size:18px">&#xe62a;</i></a>
            <ul class="dropDown-menu radius box-shadow">
                <li><a href="javascript:;" data-val="default" title="默认（黑色）">默认（黑色）</a></li>
                <li><a href="javascript:;" data-val="blue" title="蓝色">蓝色</a></li>
                <li><a href="javascript:;" data-val="green" title="绿色">绿色</a></li>
                <li><a href="javascript:;" data-val="red" title="红色">红色</a></li>
                <li><a href="javascript:;" data-val="yellow" title="黄色">黄色</a></li>
                <li><a href="javascript:;" data-val="orange" title="绿色">橙色</a></li>
            </ul>
        </li>
    </ul>
    <a aria-hidden="false" class="Hui-nav-toggle" href="#"></a> </header>
<aside class="Hui-aside">
    <input runat="server" id="divScrollValue" type="hidden" value="" />
    <div class="menu_dropdown bk_2">
        <dl id="menu-article">
            <dt><i class="Hui-iconfont">&#xe616;</i> 贷款业务查询<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a href="AdminServlet?action=list" href="javascript:void(0)">业务查询</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-picture">
            <dt><i class="Hui-iconfont">&#xe613;</i> 贷款业务审批<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a href="AdminServlet?action=approve" href="javascript:void(0)">业务审批</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-product">
            <dt><i class="Hui-iconfont">&#xe620;</i> 产品管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a href="AdminServlet?action=display" href="javascript:void(0)">基金管理</a></li>
                </ul>
            </dd>
        </dl>
        <!--<dl id="menu-page">
            <dt><i class="Hui-iconfont">&#xe626;</i> 页面管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="page-home.html" href="javascript:void(0)">首页管理</a></li>
                    <li><a _href="page-flinks.html" href="javascript:void(0)">友情链接</a></li>
                </ul>
            </dd>
        </dl>-->
        <dl id="menu-comments">
            <dt><i class="Hui-iconfont">&#xe622;</i> 评论管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="http://h-ui.duoshuo.com/admin/" href="javascript:;">评论列表</a></li>
                    <li><a _href="feedback-list.html" href="javascript:void(0)">意见反馈</a></li>
                </ul>
            </dd>
        </dl>
        <!--<dl id="menu-order">
            <dt><i class="Hui-iconfont">&#xe63a;</i> 财务管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="order-list.html" href="javascript:void(0)">订单列表</a></li>
                    <li><a _href="recharge-list.html" href="javascript:void(0)">充值管理</a></li>
                    <li><a _href="invoice-list.html" href="javascript:void(0)">发票管理</a></li>
                </ul>
            </dd>
        </dl>-->
        <dl id="menu-member">
            <dt><i class="Hui-iconfont">&#xe60d;</i> 会员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="member-list.html" href="javascript:;">会员列表</a></li>
                    <li><a _href="member-del.html" href="javascript:;">删除的会员</a></li>
                    <li><a _href="member-level.html" href="javascript:;">等级管理</a></li>
                    <li><a _href="member-scoreoperation.html" href="javascript:;">积分管理</a></li>
                    <li><a _href="member-record-browse.html" href="javascript:void(0)">浏览记录</a></li>
                    <li><a _href="member-record-download.html" href="javascript:void(0)">下载记录</a></li>
                    <li><a _href="member-record-share.html" href="javascript:void(0)">分享记录</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-admin">
            <dt><i class="Hui-iconfont">&#xe62d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="admin-role.html" href="javascript:void(0)">角色管理</a></li>
                    <li><a _href="admin-permission.html" href="javascript:void(0)">权限管理</a></li>
                    <li><a _href="admin-list.html" href="javascript:void(0)">管理员列表</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-tongji">
            <dt><i class="Hui-iconfont">&#xe61a;</i> 系统统计<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="charts-1.html" href="javascript:void(0)">折线图</a></li>
                    <li><a _href="charts-2.html" href="javascript:void(0)">时间轴折线图</a></li>
                    <li><a _href="charts-3.html" href="javascript:void(0)">区域图</a></li>
                    <li><a _href="charts-4.html" href="javascript:void(0)">柱状图</a></li>
                    <li><a _href="charts-5.html" href="javascript:void(0)">饼状图</a></li>
                    <li><a _href="charts-6.html" href="javascript:void(0)">3D柱状图</a></li>
                    <li><a _href="charts-7.html" href="javascript:void(0)">3D饼状图</a></li>
                </ul>
            </dd>
        </dl>
        <dl id="menu-system">
            <dt><i class="Hui-iconfont">&#xe62e;</i> 系统管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
            <dd>
                <ul>
                    <li><a _href="system-base.html" href="javascript:void(0)">系统设置</a></li>
                    <li><a _href="system-category.html" href="javascript:void(0)">栏目管理</a></li>
                    <li><a _href="system-data.html" href="javascript:void(0)">数据字典</a></li>
                    <li><a _href="system-shielding.html" href="javascript:void(0)">屏蔽词</a></li>
                    <li><a _href="system-log.html" href="javascript:void(0)">系统日志</a></li>
                </ul>
            </dd>
        </dl>
    </div>
</aside>
<div class="dislpayArrow"><a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a></div>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active"><span title="我的桌面" data-href="welcome.html">我的桌面</span><em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group"><a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a><a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a></div>
    </div>

    <form action="AdminServlet" method="post" name="f">
        <div class="my-f-right fr">
            <div class="my-f-r-main c666666">
                <div class="my-f-r-m-tab-search clearfix" style="margin-top:0;">
                    <div class="my-f-r-m-tab-sf fl">
                        <input type="hidden" name="action" value="approve">
                        <label>用户名</label>
                        <input type="text" name="username">
                        <label>时间：</label>
                        <select name="Time" id="Time">
                            <option value="3">三天以内</option>
                            <option selected="selected" value="7">一周以内</option>
                            <option value="10">十天以内</option>
                        </select>
                        <%--<input class="my-f-r-m-tab-btn" type="button" value="查询" id="btOk" onclick=" ClickOK() " />--%>
                        <button type="submit" class="btn btn-primary">查询</button>
                    </div>
                </div>
            </div>
            <table cellpadding="0" cellspacing="0" class="receivetab c666666">
                <thead>
                <tr>
                    <td><a href="AdminServlet?pageno=${pageno}&pagesize=${pagesize}">用户名</a></td>
                    <td><a href="AdminServlet?pageno=${pageno}&pagesize=${pagesize}">借款金额</a></td>
                    <td><a href="AdminServlet?pageno=${pageno}&pagesize=${pagesize}">借款期限</a></td>
                    <td><a href="AdminServlet?pageno=${pageno}&pagesize=${pagesize}">借款利率</a></td>
                    <td><a href="AdminServlet?pageno=${pageno}&pagesize=${pagesize}">借款日期</a></td>
                    <td width="200">审核</td>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="loan" items="${requestScope.loanlist}" varStatus="row">
                    <tr>
                        <td>${loan.username}</td>
                        <td>${loan.loanamount}</td>
                        <td>${loan.loantime}</td>
                        <td>${loan.repayment}</td>
                        <td><fmt:formatDate value="${loan.loandate}" pattern="yyyy-MM-dd"/></td>
                        <td>
                            <button type="button" onclick="ifapprove(${loan.loanid},${loan.loantime},${loan.loanamount},${loan.repayment},'${loan.username}')" class="btn-primary">
                            审核
                            </button>
                        </td>
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
                        <a href="QueryServlet?pageno=1&pagesize=${pagesize}">首页</a>
                        &nbsp;&nbsp;
                        <a href="QueryServlet?pageno=${pageno-1}&pagesize=${pagesize}">上一页</a>
                        &nbsp;&nbsp;
                        <a href="QueryServlet?pageno=${pageno+1}&pagesize=${pagesize}">下一页</a>
                        &nbsp;&nbsp;
                        <a href="QueryServlet?pageno=${maxpage}&pagesize=${pagesize}">尾页</a>
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






</section>
<script src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/1.9.3/layer.js"></script>
<script type="text/javascript" src="js/H-ui.js"></script>
<script type="text/javascript" src="js/H-ui.admin.js"></script>
<script type="text/javascript">


    function cargo() {
        var pagesize = document.getElementById("pagesize").value
        var pageno = document.getElementById("pageno").value
        location.href = "AdminServlet?pageno=" + pageno + "&pagesize=" + pagesize
    }

    function ifapprove(loanid,loantime,loanamount,repayment,username) {

        window.location.href="AdminServlet?action=ifapprove&loanid="+loanid+"&loantime="+loantime+"&loanamount="+loanamount+"&repayment="+repayment+"&username="+username
    }


    /*资讯-添加*/
    function article_add(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    /*图片-添加*/
    function picture_add(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    /*产品-添加*/
    function product_add(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    /*用户-添加*/
    function member_add(title,url,w,h){
        layer_show(title,url,w,h);
    }
</script>
<script type="text/javascript">
    var _hmt = _hmt || [];
    (function() {
        var hm = document.createElement("script");
        hm.src = "//hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
        var s = document.getElementsByTagName("script")[0];
        s.parentNode.insertBefore(hm, s)})();
    var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
    document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F080836300300be57b7f34f4b3e97d911' type='text/javascript'%3E%3C/script%3E"));
</script>
</body>
</html>

