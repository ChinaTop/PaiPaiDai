package com.ppdai.POJO;

import java.util.Date;

public class Myfund {
    private Integer myfundid;
    private String name;
    private Double rate;
    private Double investamount;
    private Date buydate;
    private Integer deadline;
    private Integer iffinish;


    public Myfund(){}

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Integer getDeadline() {
        return deadline;
    }

    public void setInvestamount(Double investamount) {
        this.investamount = investamount;
    }

    public Double getRate() {
        return rate;
    }

    public void setDeadline(Integer deadline) {
        this.deadline = deadline;
    }

    public Double getInvestamount() {
        return investamount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setBuydate(Date buydate) {
        this.buydate = buydate;
    }

    public Date getBuydate() {
        return buydate;
    }

    public void setIffinish(Integer iffinish) {
        this.iffinish = iffinish;
    }

    public Integer getIffinish() {
        return iffinish;
    }

    public void setMyfundid(Integer myfundid) {
        this.myfundid = myfundid;
    }

    public Integer getMyfundid() {
        return myfundid;
    }
}
