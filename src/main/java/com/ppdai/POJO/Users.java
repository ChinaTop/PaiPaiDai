package com.ppdai.POJO;

public class Users {
    private String username;
    private String password;
    private String id;
    private String bankid;
    private Double remaining;

    public Users(){}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setBankid(String bankid) {
        this.bankid = bankid;
    }

    public String getBankid() {
        return bankid;
    }

    public void setRemaining(Double remaining) {
        this.remaining = remaining;
    }

    public Double getRemaining() {
        return remaining;
    }

}

