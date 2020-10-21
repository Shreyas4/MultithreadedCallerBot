package com.orum.sundial.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PhoneCallStatusResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("status")
    private String status;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PhoneCallStatusResponse{" +
                "id=" + id +
                ", status='" + status + '\'' +
                '}';
    }


}
