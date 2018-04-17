package com.ppdai.Servlet;
import com.ppdai.Dao.UsersDao;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class UsersServlet extends HttpServlet {
    private static final long serialVersionUID = -1399343332800583848L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String action=request.getParameter("action");
        HttpSession session=request.getSession();

        UsersDao dao=new UsersDao();

        if ("login".equals(action)){
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            if (password.equals(dao.find(username))){
                String bankid=dao.getBankId(username);
                session.setAttribute("username",username);
                session.setAttribute("bankid",bankid);
                request.getRequestDispatcher("indexsccucess.jsp").forward(request,response);
            }else{
                out.println("<span style='color:red'>错误的用户名或密码!</span>");
                response.setHeader("refresh","2;url=login.jsp");
            }
        }else if("logout".equals(action)){
            if(session.getAttribute("username")!=null){
                session.removeAttribute("username");
                session.removeAttribute("bankid");
                session.invalidate();
                response.setHeader("refresh","1;url=index.jsp");
            }
        }


        else if("saveAjax".equals(action)){
            String username=request.getParameter("username");
            if (username==null||username.trim().length()==0) {
                out.println("注册失败");
            }else{
                String password = request.getParameter("password");
                String id = request.getParameter("id");
                String bankid =request.getParameter("bankid");
                List usernamelist=dao.usernamematch();
                if(usernamelist.contains(username)){
                    out.println("此用户名已存在");
                }else{
                    int count = dao.add(username, password, id,bankid);
                    if (count > 0) {
                        out.println("注册成功");
                        response.setHeader("refresh","2;url=login.jsp");
                    } else {
                        out.println("注册失败,");
                    }
                }
            }
            //response.setHeader("refresh","2;url=regist.jsp");
        }


        else if("save".equals(action)){
            String username=request.getParameter("username");
            if (username==null||username.trim().length()==0) {
                out.println("注册失败");
            }else{
                String password = request.getParameter("password");
                String id = request.getParameter("id");
                String bankid =request.getParameter("bankid");
                int count = dao.add(username, password, id,bankid);
                if (count > 0) {
                    out.println("注册成功");
                    response.setHeader("refresh","2;url=login.jsp");
                } else {
                    out.println("注册失败,");
                }
            }
        }
        out.flush();
        out.close();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
