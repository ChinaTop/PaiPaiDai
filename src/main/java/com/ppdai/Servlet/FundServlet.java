package com.ppdai.Servlet;

import com.ppdai.Dao.FundDao;
import com.ppdai.Dao.MyFundDao;
import com.ppdai.Dao.UsersDao;
import com.ppdai.POJO.Fund;
import com.ppdai.POJO.Myfund;

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

public class FundServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        HttpSession session=request.getSession();
        if(session.getAttribute("username")==null||session.getAttribute("username").equals("")){
            request.getRequestDispatcher("login.jsp").forward(request,response);
        }
        String action = request.getParameter("action");
        action = (action == null) ? "find" : action;
        FundDao fundao=new FundDao();
        UsersDao usersdao=new UsersDao();
        //基金显示与筛选查询
        if("find".equals(action)){
            String risklevel=request.getParameter("risklevel");
            risklevel=(risklevel==null||"".equals(risklevel))?"保守型":risklevel;

            String rate=request.getParameter("rate");
            rate=(rate==null||"".equals(rate))?"00-00":rate;
            Integer rate1=Integer.parseInt(rate.substring(0,rate.indexOf("-")));
            Integer rate2=Integer.parseInt(rate.substring(rate.indexOf("-")+1));

            String investamount=request.getParameter("investamount");
            investamount=(investamount==null||"".equals(investamount))?"00-00":investamount;
            Integer investamount1=Integer.parseInt(investamount.substring(0,investamount.indexOf("-")));
            Integer investamount2=Integer.parseInt(investamount.substring(investamount.indexOf("-")+1));

            String spageno = request.getParameter("pageno");
            spageno = (spageno == null || "".equals(spageno)) ? "1" : spageno;
            Integer pageno = Integer.parseInt(spageno);

            Integer maxpage = fundao.getMaxPage(risklevel, rate1, rate2,investamount1,investamount2);
            pageno = (pageno == 0) ? 1 : pageno; // 起始页
            pageno = (pageno > maxpage) ? maxpage : pageno; // 结束页
            Integer page=(pageno-1)*5;

            String sort = request.getParameter("sort");
            sort = (sort == null || "".equals(sort)) ? "rate" : sort;
            String order = request.getParameter("order");
            order = (order == null || "".equals(order)) ? "asc" : order;

            List<Fund> funds=fundao.findfund(page,sort,order,risklevel,rate1,rate2,investamount1,investamount2);
            Integer count=fundao.getCount(risklevel, rate1, rate2,investamount1,investamount2);
            request.setAttribute("funds",funds);
            request.setAttribute("pageno", pageno);
            request.setAttribute("maxpage", maxpage);
            request.setAttribute("sort", sort);
            request.setAttribute("order", order);
            request.setAttribute("risklevel",risklevel);
            request.setAttribute("rate",rate);
            request.setAttribute("investamount",investamount);
            request.setAttribute("count",count);
            request.getRequestDispatcher("paipaidai.jsp").forward(request, response);
        }
        //购买基金
        else if("buyfund".equals(action)){
            MyFundDao myfunddao=new MyFundDao();
            Date date=new Date();
            Integer fundid=Integer.parseInt(request.getParameter("fundid"));
            Integer investman=Integer.parseInt(request.getParameter("investman"));
            Double  investamount=Double.parseDouble(request.getParameter("investamount"));
            Double  rate=Double.parseDouble(request.getParameter("rate"));
            Integer deadline=Integer.parseInt(request.getParameter("deadline"));
            String  username= (String) session.getAttribute("username");
            String  name=request.getParameter("name");
            Double  remain=usersdao.remaincheck(username);
            Double  remaining=remain-investamount;
            if(remaining<0){
                out.println("<span style='color:red'>您的余额不足，无法购买!</span>");
                response.setHeader("refresh","2;url=index.jsp");
            }else{
                Integer count1=usersdao.remainingChange(username,remaining);
                Integer count2=fundao.fundRecord(investman,fundid);
                Integer count3=myfunddao.add(name,username,rate,investamount,date,deadline);
                Integer count4=fundao.fundRecord2(username,name,remaining,investamount,date);
                if(count4>0){
                    out.println("<span style='color:red'>恭喜您购买成功!</span>");
                    response.setHeader("refresh","2;url=FundServlet");
                }
            }
        }


        else if("myfund".equals(action)){
            Date now=new Date();
            Calendar calendar=Calendar.getInstance();
            MyFundDao myfunddao=new MyFundDao();
            String username= (String) session.getAttribute("username");
            List<Myfund>smyfunds=myfunddao.findMyFund(username);
            for (Myfund mf:smyfunds) {
                calendar.setTime(mf.getBuydate());
                calendar.add(Calendar.MONTH,mf.getDeadline());
                Date date=calendar.getTime();
                int i=date.compareTo(now);
                if(i<=0){
                    myfunddao.finished(mf.getMyfundid());
                }
            }
            List<Myfund>myfunds=myfunddao.findMyFund(username);
            request.setAttribute("myfunds",myfunds);
            request.getRequestDispatcher("myfund.jsp").forward(request,response);
        }



        else if("myfundback".equals(action)){
            Date date=new Date();
            String username= (String) session.getAttribute("username");
            Double remain=usersdao.remaincheck(username);
            Integer myfundid=Integer.parseInt(request.getParameter("myfundid"));
            Double rate=Double.parseDouble(request.getParameter("rate"));
            Double investamount=Double.parseDouble(request.getParameter("investamount"));
            String name=request.getParameter("name");
            Integer deadline=Integer.parseInt(request.getParameter("deadline"));
            Double backmoney=investamount*rate*deadline/1200+investamount;
            Double remaining=remain+backmoney;
            usersdao.remainingChange(username,remaining);
            MyFundDao myfunddao=new MyFundDao();
            myfunddao.myFundRecord(username,name,remaining,backmoney,date);
            int count=myfunddao.removeMyFund(myfundid);
            if(count>0){
                out.println("<span style='color:red'>恭喜您成功收回此基金!</span>");
                response.setHeader("refresh","2;url=FundServlet?action=myfund");
            }
        }

    }








    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
