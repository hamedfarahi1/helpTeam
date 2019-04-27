package com.kharazmi.helpdesk.Util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonModel {


    public String getApiKey() {
        return apiKey;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getInformationType() {
        return informationType;
    }

    public String getName() {
        return name;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getUserName() {
        return userName;
    }

    public String getCellPhoneNumber() {
        return cellPhoneNumber;
    }

    public String getEnableStatus() {
        return enableStatus;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public String getPoint() {
        return point;
    }

    public String getStatus() {
        return status;
    }

    public String getSenfid() {
        return senfid;
    }

    public String getCode() {
        return code;
    }

    public String getToken() {
        return token;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setInformationType(String informationType) {
        this.informationType = informationType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setCellPhoneNumber(String cellPhoneNumber) {
        this.cellPhoneNumber = cellPhoneNumber;
    }

    public void setEnableStatus(String enableStatus) {
        this.enableStatus = enableStatus;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSenfid(String senfid) {
        this.senfid = senfid;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    @JsonProperty("apiKey")
    String apiKey;
    @JsonProperty("username")
    String username;
    @JsonProperty("password")
    String password;
    @JsonProperty("birthDate")
    String birthDate;
    @JsonProperty("informationType")
    String informationType;
    @JsonProperty("name")
    String name;
    @JsonProperty("familyName")
    String familyName;
    @JsonProperty("phoneNumber")
    String phoneNumber;
    @JsonProperty("userName")
    String userName;
    @JsonProperty("cellPhoneNumber")
    String cellPhoneNumber;
    @JsonProperty("enableStatus")
    String enableStatus;
    @JsonProperty("fullAddress")
    String fullAddress;
    @JsonProperty("point")
    String point;
    @JsonProperty("status")
    String status;
    @JsonProperty("senfid")
    String senfid;
    @JsonProperty("code")
    String code;
    @JsonProperty("token")
    String token;
    @JsonProperty("captcha")
    String captcha;





}
