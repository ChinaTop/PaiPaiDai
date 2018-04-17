package com.ppdai.Servlet;

import com.ppdai.Dao.*;
import com.ppdai.POJO.Fund;
import com.ppdai.POJO.Image;
import com.ppdai.POJO.Loan;
import com.ppdai.POJO.Query;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 8534582089996380417L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        String action=request.getParameter("action");
        HttpSession session=request.getSession();

        AdminDao dao=new AdminDao();

        if ("login".equals(action)){
            String administrator=request.getParameter("administrator");
            String adminpassword=request.getParameter("adminpassword");
            if (adminpassword.equals(dao.find(administrator))){
                session.setAttribute("administrator",administrator);
                request.getRequestDispatcher("adminindex.jsp").forward(request,response);
            }else{
                out.println("<span style='color:red'>错误的管理员号或密码!</span>");
                response.setHeader("refresh","2;url=admin.jsp");
            }
        }else if("logout".equals(action)){
            if(session.getAttribute("administrator")!=null){
                session.removeAttribute("administrator");
                session.invalidate();
                response.setHeader("refresh","1;url=admin.jsp");
            }
        }

        else if("approve".equals(action)){
            String username = request.getParameter("username");

            String spagesize=request.getParameter("pagesize");
            spagesize=(spagesize==null||"".equals(spagesize))?"10":spagesize;
            Integer pagesize=Integer.parseInt(spagesize);

            String spageno = request.getParameter("pageno");
            spageno = (spageno == null || "".equals(spageno)) ? "1" : spageno;
            Integer pageno = Integer.parseInt(spageno);

            String time=request.getParameter("Time");
            time=(time==null||"".equals(time))?"7":time;

            Date now=new Date();
            Date date=new Date();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(now);
            if(time.equals("3")){
                calendar.add(Calendar.DATE,-3);
            }else if(time.equals("7")){
                calendar.add(Calendar.DATE,-7);
            }else{
                calendar.add(Calendar.DATE,-10);
            }
            date=calendar.getTime();
            System.out.println(date);

            Integer maxpage = dao.getMaxPage(pagesize, username,date);
            pageno = (pageno == 0) ? 1 : pageno; // 起始页
            pageno = (pageno > maxpage) ? maxpage : pageno; // 结束页
            Integer page = (pageno - 1) * pagesize;

            List<Loan> loanlist = dao.finds(page, pagesize,username,date);
            request.setAttribute("loanlist", loanlist);
            request.setAttribute("pageno", pageno);
            request.setAttribute("pagesize", pagesize);
            request.setAttribute("maxpage", maxpage);
            request.getRequestDispatcher("approve.jsp").forward(request, response);

        }

        else if("ifapprove".equals(action)){
            Integer loanid=Integer.parseInt(request.getParameter("loanid"));

            System.out.println(loanid);

            Double loanamount=Double.parseDouble(request.getParameter("loanamount"));
            Double repayment=Double.parseDouble(request.getParameter("repayment"));
            Integer loantime=Integer.parseInt(request.getParameter("loantime"));
            Date date=new Date();

            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String datestr=sdf.format(date);

            ImageDao imgdao=new ImageDao();
            String usernameurl=imgdao.getUrl(loanid);

            System.out.println(usernameurl);

            String username = request.getParameter("username");
            request.setAttribute("loanid",loanid);
            request.setAttribute("username", username);
            request.setAttribute("datestr",datestr);
            request.setAttribute("loantime", loantime);
            request.setAttribute("loanamount", loanamount);
            request.setAttribute("repayment", repayment);
            request.setAttribute("usernameurl", usernameurl);
            request.getRequestDispatcher("ifapprove.jsp").forward(request,response);
        }

        else if("gothrough".equals(action)){
            LoanDao loandao=new LoanDao();
            UsersDao usersdao=new UsersDao();
            String username = request.getParameter("username");
            Integer loanid=Integer.parseInt(request.getParameter("loanid"));
            System.out.println(loanid);
            Double loanamount=Double.parseDouble(request.getParameter("loanamount"));
            Double repayment=Double.parseDouble(request.getParameter("repayment"));
            Integer loantime=Integer.parseInt(request.getParameter("loantime"));
            String sloandate = request.getParameter("loandate");
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date loandate= null;
            try {
                loandate = sdf.parse(sloandate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            Double remaining=usersdao.remaincheck(username)+loanamount;

            usersdao.remainingChange(username,remaining);
            loandao.loanRecord(username,remaining,loanamount,loandate);
            int count=loandao.gothrough(loanid);
            if(count>0){
                out.println("<span style='color:red'>审核通过!</span>");
                response.setHeader("refresh","2;url=AdminServlet?action=approve");
            }
        }


        else if("notthrough".equals(action)){
            Integer loanid=Integer.parseInt(request.getParameter("loanid"));
            ImageDao imagedao=new ImageDao();
            imagedao.removeImage(loanid);
            LoanDao loandao=new LoanDao();
            int count=loandao.removeLoan(loanid);
            if(count>0){
                out.println("<span style='color:red'>审核完成，不通过!</span>");
                response.setHeader("refresh","2;url=AdminServlet?action=approve");
            }
        }

        else if("list".equals(action)){
            String username = request.getParameter("username");

            String spagesize=request.getParameter("pagesize");
            spagesize=(spagesize==null||"".equals(spagesize))?"10":spagesize;
            Integer pagesize=Integer.parseInt(spagesize);

            String spageno = request.getParameter("pageno");
            spageno = (spageno == null || "".equals(spageno)) ? "1" : spageno;
            Integer pageno = Integer.parseInt(spageno);

            String time=request.getParameter("time");
            time=(time==null||"".equals(time))?"7":time;

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
            Integer maxpage = dao.getMaxPage2(pagesize, username,date);
            pageno = (pageno == 0) ? 1 : pageno; // 起始页
            pageno = (pageno > maxpage) ? maxpage : pageno; // 结束页
            Integer page = (pageno - 1) * pagesize;


            String sort=request.getParameter("sort");
            sort=(sort==null||"".equals(sort))?"username":sort;

            String order=request.getParameter("order");
            order=(order==null||"".equals(order))?"asc":order;

            System.out.println(sort);
            System.out.println(order);
            List<Query> querylist = dao.findQuery(page, pagesize,username,sort,order,date);
            request.setAttribute("username",username);
            request.setAttribute("time",time);
            request.setAttribute("querylist", querylist);
            request.setAttribute("pageno", pageno);
            request.setAttribute("pagesize", pagesize);
            request.setAttribute("maxpage", maxpage);
            request.setAttribute("sort", sort);
            request.setAttribute("order", order);
            request.getRequestDispatcher("adminquery.jsp").forward(request, response);
        }

        else if("display".equals(action)){
            FundDao funddao=new FundDao();
            List<Fund> funds=funddao.display();
            request.setAttribute("funds",funds);
            request.getRequestDispatcher("display.jsp").forward(request, response);
        }


        else if("xiajia".equals(action)){
            Integer fundid=Integer.parseInt(request.getParameter("fundid"));
            FundDao funddao=new FundDao();
            int count=funddao.xiajia(fundid);
            if(count>0){
                out.println("<span style='color:red'>下架成功</span>");
                response.setHeader("refresh","2;url=AdminServlet?action=display");
            }
        }


        else if("shangjia".equals(action)){
            Integer fundid=Integer.parseInt(request.getParameter("fundid"));
            FundDao funddao=new FundDao();
            int count=funddao.shangjia(fundid);
            if(count>0){
                out.println("<span style='color:red'>上架成功</span>");
                response.setHeader("refresh","2;url=AdminServlet?action=display");
            }
        }



        else if("add".equals(action)){
            Date investdate=null;
            String name=request.getParameter("name");
            String risklevel=request.getParameter("risklevel");
            Double rate=Double.parseDouble(request.getParameter("rate"));
            Double investamount=Double.parseDouble(request.getParameter("investamount"));
            String sdate=request.getParameter("investdate");
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            try {
                investdate=sdf.parse(sdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Integer deadline=Integer.parseInt(request.getParameter("deadline"));
            Integer investman=Integer.parseInt(request.getParameter("investman"));
            Integer ifdisplay=Integer.parseInt(request.getParameter("ifdisplay"));
            FundDao funddao=new FundDao();
            int count=funddao.add(name,risklevel,rate,investamount,investdate,deadline,investman,ifdisplay);
            if(count>0){
                out.println("<span style='color:red'>添加成功！</span>");
                response.setHeader("refresh","2;url=AdminServlet?action=display");
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
