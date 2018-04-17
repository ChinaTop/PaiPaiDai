package com.ppdai.POJO;

import java.util.Date;

public class Fund {
    private Integer fundid;
    private String name;
    private String risklevel;
    private Double rate;
    private Double investamount;
    private Date investdate;
    private Integer deadline;
    private Integer investman;
    private Integer ifdisplay;

    public Fund(){

    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public Date getInvestdate() {
        return investdate;
    }

    public void setFundid(Integer fundid) {
        this.fundid = fundid;
    }

    public Double getInvestamount() {
        return investamount;
    }

    public void setInvestamount(Double investamount) {
        this.investamount = investamount;
    }

    public Double getRate() {
        return rate;
    }

    public void setInvestdate(Date investdate) {
        this.investdate = investdate;
    }

    public Integer getDeadline() {
        return deadline;
    }

    public void setInvestman(Integer investman) {
        this.investman = investman;
    }

    public Integer getInvestman() {
        return investman;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getFundid() {
        return fundid;
    }

    public void setRisklevel(String risklevel) {
        this.risklevel = risklevel;
    }

    public String getRisklevel() {
        return risklevel;
    }

    public void setIfdisplay(Integer ifdisplay) {
        this.ifdisplay = ifdisplay;
    }

    public Integer getIfdisplay() {
        return ifdisplay;
    }
}
