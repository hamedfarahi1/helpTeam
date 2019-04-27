package com.kharazmi.helpdesk.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "User", schema = "HelpDeskV2", catalog = "")
public class UserModel {
    private int id;
    private String firstName;
    private String familyName;
    private String userName;
    private String password;
    private String email;
    private String phoneNumber;
    private int status;
    private Timestamp lastLogin;
    private String answerOne;
    private String answerTwo;
    private String answerThree;
    private int questionOneId;
    private int questionTwoId;
    private int questionThreeId;
    private String loginCaptchaText;
    private String authCode;
    private byte isEmailVerify;

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FirstName", nullable = false, length = 20)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "FamilyName", nullable = false, length = 20)
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @Basic
    @Column(name = "UserName", nullable = false, length = 20)
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Basic
    @Column(name = "Password", nullable = false, length = 200)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "Email", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "PhoneNumber", nullable = false, length = 20)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Basic
    @Column(name = "Status", nullable = false)
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Basic
    @Column(name = "LastLogin", nullable = false)
    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return id == userModel.id &&
                status == userModel.status &&
                Objects.equals(firstName, userModel.firstName) &&
                Objects.equals(familyName, userModel.familyName) &&
                Objects.equals(userName, userModel.userName) &&
                Objects.equals(password, userModel.password) &&
                Objects.equals(email, userModel.email) &&
                Objects.equals(phoneNumber, userModel.phoneNumber) &&
                Objects.equals(lastLogin, userModel.lastLogin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, familyName, userName, password, email, phoneNumber, status, lastLogin);
    }

    @Basic
    @Column(name = "AnswerOne")
    public String getAnswerOne() {
        return answerOne;
    }

    public void setAnswerOne(String answerOne) {
        this.answerOne = answerOne;
    }

    @Basic
    @Column(name = "AnswerTwo")
    public String getAnswerTwo() {
        return answerTwo;
    }

    public void setAnswerTwo(String answerTwo) {
        this.answerTwo = answerTwo;
    }

    @Basic
    @Column(name = "AnswerThree")
    public String getAnswerThree() {
        return answerThree;
    }

    public void setAnswerThree(String answerThree) {
        this.answerThree = answerThree;
    }

    @Basic
    @Column(name = "QuestionOneID")
    public int getQuestionOneId() {
        return questionOneId;
    }

    public void setQuestionOneId(int questionOneId) {
        this.questionOneId = questionOneId;
    }

    @Basic
    @Column(name = "QuestionTwoID")
    public int getQuestionTwoId() {
        return questionTwoId;
    }

    public void setQuestionTwoId(int questionTwoId) {
        this.questionTwoId = questionTwoId;
    }

    @Basic
    @Column(name = "QuestionThreeID")
    public int getQuestionThreeId() {
        return questionThreeId;
    }

    public void setQuestionThreeId(int questionThreeId) {
        this.questionThreeId = questionThreeId;
    }

    @Basic
    @Column(name = "LoginCaptchaText")
    public String getLoginCaptchaText() {
        return loginCaptchaText;
    }

    public void setLoginCaptchaText(String loginCaptchaText) {
        this.loginCaptchaText = loginCaptchaText;
    }

    @Basic
    @Column(name = "AuthCode")
    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    @Basic
    @Column(name = "isEmailVerify")
    public byte getIsEmailVerify() {
        return isEmailVerify;
    }

    public void setIsEmailVerify(byte isEmailVerify) {
        this.isEmailVerify = isEmailVerify;
    }
}
