package com.ppdai.Servlet;

import com.ppdai.Dao.LoanDao;
import com.ppdai.POJO.Loan;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BorrowServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        action = (action == null) ? "list" : action;
        LoanDao ld = new LoanDao();

        if ("add".equals(action)) {

            String usernames = request.getParameter("username");
            session.getAttribute("username");

            String loanamount = request.getParameter("loanamount");
            loanamount = (loanamount == null || "".equals(loanamount)) ? "0" : loanamount;
            Double loanamounts = Double.parseDouble(loanamount);

            Date loandates = new Date();
            String loandate = request.getParameter("loandate");
            if (loandate != null && !"".equals(loandate)) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    loandates = sdf.parse(loandate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            String loantime = request.getParameter("loantime");
            loantime = (loantime == null || "".equals(loantime)) ? "0" : loantime;
            Integer loantimes = Integer.parseInt(loantime);

            String repayment = request.getParameter("repayment");
            System.out.println(repayment);
            repayment = (repayment == null || "".equals(repayment)) ? "0" : repayment;
            Double repayments = Double.parseDouble(repayment);

            Loan loan = new Loan();
            loan.setUsername(usernames);
            loan.setLoanamount(loanamounts);
            loan.setLoandate(loandates);
            loan.setLoantime(loantimes);
            loan.setRepayment(repayments);
            ld.add(loan);
            //out.println("申请成功，等待管理员审批！");
//            response.setHeader("refresh", "0;url=upload.jsp");
            request.getRequestDispatcher("upload.jsp").forward(request,response);
        }

        out.flush();
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
