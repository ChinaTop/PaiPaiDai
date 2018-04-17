$("#i1").blur(()=>{
    var username=$("#i1").val()
     console.log(username)
    $.post("Use rsServlet?action=saveAjax", {username: username}, (data) => {
        if(data.trim() == '此用户名已存在'){
            console.log(data)
            $("#i1").val(data)
            $("#i1").css({"color":"red","fontSize":"18px"})
        }
    })
})


$("#i1").click(()=>{
    console.log("click")
})
