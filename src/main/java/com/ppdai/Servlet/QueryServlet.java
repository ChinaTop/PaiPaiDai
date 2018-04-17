package com.ppdai.Servlet;

import com.ppdai.Dao.QueryDao;
import com.ppdai.Dao.UsersDao;
import com.ppdai.POJO.Query;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class QueryServlet extends HttpServlet {


    private static final long serialVersionUID = -5650607885680482059L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        action = (action == null)? "list" : action;
        QueryDao dao = new QueryDao();
        if ("list".equals(action)){
            String type = request.getParameter("type");
            System.out.println(type);

            String spagesize=request.getParameter("pagesize");
            spagesize=(spagesize==null||"".equals(spagesize))?"10":spagesize;
            Integer pagesize=Integer.parseInt(spagesize);

            String spageno = request.getParameter("pageno");
            spageno = (spageno == null || "".equals(spageno)) ? "1" : spageno;
            Integer pageno = Integer.parseInt(spageno);

            String time=request.getParameter("time");
            time=(time==null||"".equals(time))?"7":time;

            String username= (String) session.getAttribute("username");

            Date now=new Date();
            Date date=new Date();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(now);
            if(time.equals("3")){
                calendar.add(Calendar.DATE,-3);
            }else if(time.equals("7")){
                calendar.add(Calendar.DATE,-7);
            }else if(time.equals("30")){
                calendar.add(Calendar.MONTH,-1);
            }else if(time.equals("90")){
                calendar.add(Calendar.MONTH,-3);
            }else{
                calendar.add(Calendar.MONTH,-6);
            }
            date=calendar.getTime();
            System.out.println(date);
            Integer maxpage = dao.getMaxPage(pagesize, type,date,username);
            pageno = (pageno == 0) ? 1 : pageno; // 起始页
            pageno = (pageno > maxpage) ? maxpage : pageno; // 结束页
            Integer page = (pageno - 1) * pagesize;

            System.out.println(maxpage);

            String sort=request.getParameter("sort");
            sort=(sort==null||"".equals(sort))?"querydate":sort;

            String order=request.getParameter("order");
            order=(order==null||"".equals(order))?"asc":order;

            List<Query> querylist = dao.finds(page, pagesize,sort,order,type,date,username);
            request.setAttribute("querylist", querylist);
            // 存储分页参数: 页面中使用
            request.setAttribute("type",type);
            request.setAttribute("time",time);
            request.setAttribute("pageno", pageno);
            request.setAttribute("pagesize", pagesize);
            request.setAttribute("maxpage", maxpage);
            request.setAttribute("sort", sort);
            request.setAttribute("order", order);
            request.getRequestDispatcher("capital.jsp").forward(request, response);
        }
        //查看账户余额
        else if("remain".equals(action)) {
            UsersDao userdao=new UsersDao();
            Double remaining = 0.00;
            String username = (String) session.getAttribute("username");
            remaining = userdao.remaincheck(username);
            request.setAttribute("remaining", remaining);
            request.getRequestDispatcher("account.jsp").forward(request,response);
        }

        else if("recharge".equals(action)) {
            Double rechargeamount = Double.parseDouble(request.getParameter("rechargeamount"));
            UsersDao userdao = new UsersDao();
            String username = (String) session.getAttribute("username");
            Double remaining = userdao.remaincheck(username);
            Integer count = dao.recharge(username, rechargeamount, remaining);
            if (count > 0) {
                out.println("<span style='color:black'>充值成功!</span>");
                response.setHeader("refresh", "2;url=QueryServlet?action=remain");
            }
        }

        else if("withdraw".equals(action)){
            Double withdrawamount = Double.parseDouble(request.getParameter("withdrawamount"));
            UsersDao userdao = new UsersDao();
            String username = (String) session.getAttribute("username");
            Double remaining = userdao.remaincheck(username);
            if(remaining>=withdrawamount){
            Integer count = dao.withdraw(username, withdrawamount, remaining);
            if (count > 0) {
                out.println("<span style='color:black'>提现成功!</span>");
                response.setHeader("refresh", "2;url=QueryServlet?action=remain");
                }
            }else{
                out.println("<span style='color:red'>账户余额不足，提现失败!</span>");
                response.setHeader("refresh", "2;url=QueryServlet?action=remain");
            }
        }


        out.flush();
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}


