<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/3/20
  Time: 16:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>borrowing</title>
    <link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="CSS/bootstrap.min.css">
</head>
<body>

<c:if test="${empty sessionScope.username}">
    <c:redirect url="login.jsp"/>
</c:if>

<div class="container">
    <div class="form-group">
        <h2>拍拍网信贷</h2>
    </div>
</div>
<div class="container">

    <form action="BorrowServlet" method="post" name="f">

        <input type="hidden" name="action" value="add">

        <div class="form-group">
            <label class="col-form-label" for="username">姓名:</label>
            <input id="username" name="username" class="form-control" type="text" value="${sessionScope.username}">
        </div>

        <div class="form-group">
            <label class="col-form-label" for="loanamount">借款金额:</label>
            <%--<input type="text" name="loanamount" id="loanamount" class="form-control">--%>
            <select class="form-control" name="loanamount" id="loanamount">
                <option>1000</option>
                <option>5000</option>
                <option>10000</option>
            </select>
        </div>

        <div class="form-group">
            <label class="col-form-label" for="loandate">借款日期:</label>
            <%--<input type="date" name="loandate" id="loandate" class="form-control">--%>
            <input type="text" id="loandate" name="loandate" class="form-control">
        </div>

        <div class="form-group">
            <label class="col-form-label" for="loantime">借款期限:</label>
            <%--<input type="text" name="loantime" id="loantime" class="form-control"  value="30">--%>

            <select class="form-control" name="loantime" id="loantime">
                <option value="3">90</option>
                <option value="6">180</option>
                <option value="12">360</option>
            </select>
        </div>

        <div class="form-group">
            <label class="col-form-label" for="repayment">利息:</label>
            <%--<input type="text" name="repayment" id="repayment" class="form-control" value="0.03" readonly>--%>
            <input type="text"  value="0.03" readonly id="repayment" class="form-control" name="repayment">
        </div>
        <button type="submit" class="btn btn-primary">下一步</button>
    </form>
</div>
<br><br><br>
<div class="container">
    <a href="index.jsp">返回首页</a>
</div>

</body>
<script src="js/jquery-3.3.1.min.js"></script>
<SCRIPT language=JavaScript>
    //调用函数时间显示在文本框中
    function timeYear() {
        var tmpDate = new Date();
        document.getElementById("loandate").value = tmpDate.getFullYear() + "-" + (tmpDate.getMonth() + 1) + "-" + tmpDate.getDate();
    }

    setInterval("timeYear()", 1000);

    $("#loantime").change(()=>{
        var loantime=$("#loantime").val();
        if(loantime.trim()==3){
            $("#repayment").val(0.03);
        }else if(loantime.trim()==6){
            $("#repayment").val(0.06);
        }else if(loantime.trim()==12){
            $("#repayment").val(0.12);
        }
    })
</SCRIPT>
</html>
