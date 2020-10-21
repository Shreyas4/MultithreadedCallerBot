package com.orum.sundial.workers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orum.sundial.SundialApplication;
import com.orum.sundial.request.PhoneCallRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.StringTokenizer;

public class Requestor extends Thread {

    ObjectMapper objectMapper = new ObjectMapper();
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders httpHeaders = new HttpHeaders();
    String API_SERVER_URL = "http://localhost:4830/call";
    private int workerId;

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
    }

    public void run(){
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        try {
            lock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lock() {
            while(!SundialApplication.PHONE_NUMBERS_QUEUE.isEmpty()) {
                try {
                    if (SundialApplication.PHONE_NO_STATUS_MAP.get(SundialApplication.PHONE_NUMBERS_QUEUE.peek()).equals("idle")
                            || SundialApplication.PHONE_NO_STATUS_MAP.get(SundialApplication.PHONE_NUMBERS_QUEUE.peek()).equals("completed")) {
                        String phoneNumber = "";
                        synchronized (SundialApplication.PHONE_NUMBERS_QUEUE) {
                            phoneNumber = SundialApplication.PHONE_NUMBERS_QUEUE.poll();
                        }

                        PhoneCallRequest phoneCallRequestOb = new PhoneCallRequest(phoneNumber);
                        HttpEntity<String> request = new HttpEntity<>(objectMapper.writeValueAsString(phoneCallRequestOb), httpHeaders);

                        String phoneCallResponseString = restTemplate.postForObject(API_SERVER_URL, request, String.class);
                        assert phoneCallResponseString != null;

                        StringTokenizer responseStringTokenizer = new StringTokenizer(phoneCallResponseString, ":}");

                        String idFieldIdentifier = responseStringTokenizer.nextToken();
                        String id = responseStringTokenizer.nextToken();

                        synchronized (SundialApplication.ID_PHONE_NO_MAP) {
                            SundialApplication.ID_PHONE_NO_MAP.put(id, phoneNumber);
                        }
//                        System.out.println("Worker "+workerId+" has dialled phone number: "+phoneNumber+" at "+System.currentTimeMillis());
                        while (!SundialApplication.PHONE_NO_STATUS_MAP.get(phoneNumber).equals("completed"));
//                    Thread.sleep(10);
                    }
                } catch (Exception e) {

                }
            }

    }
}
