package com.kharazmi.helpdesk.Model;

import javax.persistence.*;

@Entity
@Table(name = "verifyphonenumber", schema = "HelpDeskV2", catalog = "")
public class VerifyphonenumberModel {
    private int id;
    private String phoneNumber;
    private int verifyCode;
    private int status;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "phoneNumber", nullable = false, length = 11)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "verifyCode", nullable = false)
    public int getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(int verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Basic
    @Column(name = "status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VerifyphonenumberModel that = (VerifyphonenumberModel) o;

        if (id != that.id) return false;
        if (verifyCode != that.verifyCode) return false;
        if (status != that.status) return false;
        if (phoneNumber != null ? !phoneNumber.equals(that.phoneNumber) : that.phoneNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (phoneNumber != null ? phoneNumber.hashCode() : 0);
        result = 31 * result + verifyCode;
        result = 31 * result + status;
        return result;
    }
}
