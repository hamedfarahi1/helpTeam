package com.kharazmi.helpdesk.Util;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

public class UserJsonModelForget {
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("familyName")
    private String familyName;
    @JsonProperty("userName")
    private String userName;
    @JsonProperty("password")
    private String password;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @JsonProperty("status")
    private int status;
    @JsonProperty("lastLogin")
    private Timestamp lastLogin;
    @JsonProperty("verifyNum")
    private String verify;
    @JsonProperty("QuestionAnswer1")
    private String QuestionAnswer1;
    @JsonProperty("QuestionAnswer2")
    private String QuestionAnswer2;
    @JsonProperty("QuestionAnswer3")
    private String QuestionAnswer3;
    @JsonProperty("QuestionOneId")
    private String QuestionOneId;
    @JsonProperty("QuestionTwoId")
    private String QuestionTwoId;
    @JsonProperty("QuestionThreeId")
    private String QuestionThreeId;
    @JsonProperty("verificationCode")
    private String verificationCode;
    @JsonProperty("ticketText")
    private String ticketText;



    public String getFirstName() {
        return firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getStatus() {
        return status;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public String getVerify() {
        return verify;
    }

    public String getQuestionAnswer1() {
        return QuestionAnswer1;
    }

    public String getQuestionAnswer2() {
        return QuestionAnswer2;
    }

    public String getQuestionAnswer3() {
        return QuestionAnswer3;
    }

    public String getQuestionOneId() {
        return QuestionThreeId;
    }

    public String getQuestionTwoId() {
        return QuestionThreeId;
    }

    public String getQuestionThreeId() {
        return QuestionThreeId;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public String getTicketText() {
        return ticketText;
    }
}
