package com.orum.sundial.request;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneCallRequest {
    @JsonProperty("phone")
    private String phone;

    @JsonProperty("webhookURL")
    private String webhookURL = "http://localhost:8080/statusReceiver";

    public PhoneCallRequest(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
