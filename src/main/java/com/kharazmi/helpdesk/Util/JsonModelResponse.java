package com.kharazmi.helpdesk.Util;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonModelResponse {
    @JsonProperty("response")
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

}
