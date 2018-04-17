package com.ppdai.POJO;

import java.io.Serializable;
import java.util.Date;

public class Loan implements Serializable {
    private static final long serialVersionUID = -4662729414652723085L;
    private Integer loanid;
    private String username;
    private Double loanamount;
    private Date loandate;
    private Integer loantime;
    private Double repayment;
    private Integer ifcheched;

    public Loan() {
    }

    public Integer getLoanid() {
        return loanid;
    }

    public void setLoanid(Integer loanid) {
        this.loanid = loanid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getLoanamount() {
        return loanamount;
    }

    public void setLoanamount(Double loanamount) {
        this.loanamount = loanamount;
    }

    public Date getLoandate() {
        return loandate;
    }

    public void setLoandate(Date loandate) {
        this.loandate = loandate;
    }

    public Integer getLoantime() {
        return loantime;
    }

    public void setLoantime(Integer loantime) {
        this.loantime = loantime;
    }

    public Double getRepayment() {
        return repayment;
    }

    public void setRepayment(Double repayment) {
        this.repayment = repayment;
    }


    public void setIfcheched(Integer ifcheched) {
        this.ifcheched = ifcheched;
    }

    public Integer getIfcheched() {
        return ifcheched;
    }

}
