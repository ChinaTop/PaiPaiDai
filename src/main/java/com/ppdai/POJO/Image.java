package com.ppdai.POJO;

import java.io.Serializable;

public class Image implements Serializable{

    private static final long serialVersionUID = 3722583917375721511L;
    private Integer loanid;
    private String usernameurl;

    public Image() {
    }

    public Integer getLoanid() {
        return loanid;
    }

    public void setLoanid(Integer loanid) {
        this.loanid = loanid;
    }

    public String getUsernameurl() {
        return usernameurl;
    }

    public void setUsernameurl(String usernameurl) {
        this.usernameurl = usernameurl;
    }
}
