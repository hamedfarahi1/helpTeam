package com.kharazmi.helpdesk.Model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Admin", schema = "HelpDeskV2", catalog = "")
public class AdminModel {
    private String firstName;
    private String familyName;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private int id;
    private int roleId;

    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FirstName", nullable = true, length = 20)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "FamilyName", nullable = true, length = 20)
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @Basic
    @Column(name = "UserName", nullable = false, length = 30)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "Password", nullable = false, length = 30)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Email", nullable = false, length = 30)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PhoneNumber", nullable = false, length = 15)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdminModel that = (AdminModel) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(familyName, that.familyName) &&
                Objects.equals(userName, that.userName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, familyName, userName, password, email, phoneNumber, id);
    }

    @Basic
    @Column(name = "RoleID")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }
}
