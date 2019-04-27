package com.kharazmi.helpdesk.Model;

import javax.persistence.*;

@Entity
@Table(name = "EmailVerfy", schema = "HelpDeskV2", catalog = "")
public class EmailVerfyModel {
    private int id;
    private int userId;
    private String token;

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "UserID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "Token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailVerfyModel that = (EmailVerfyModel) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (token != null ? !token.equals(that.token) : that.token != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (token != null ? token.hashCode() : 0);
        return result;
    }
}
