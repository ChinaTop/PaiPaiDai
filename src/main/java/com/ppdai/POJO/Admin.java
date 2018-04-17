package com.ppdai.POJO;

import java.io.Serializable;

public class Admin implements Serializable {
    private static final long serialVersionUID = -3779447168882970127L;

    private String administrator;
    private String adminpassword;

    public Admin() {
    }

    public String getAdministrator() {
        return administrator;
    }

    public void setAdministrator(String administrator) {
        administrator = administrator;
    }

    public String getAdminpassword() {
        return adminpassword;
    }

    public void setAdminpassword(String adminpassword) {
        this.adminpassword = adminpassword;
    }
}
