package com.orum.sundial.controller;

import com.orum.sundial.SundialApplication;
import com.orum.sundial.response.PhoneCallStatusResponse;
import com.orum.sundial.workers.DataPopulator;
import com.orum.sundial.workers.Requestor;
import com.orum.sundial.workers.StatusUpdater;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class SundialController {

    @GetMapping(value="/makeCalls")
    public void makeCalls() {

        Requestor requestor1 = new Requestor();
        requestor1.setWorkerId(1);

        Requestor requestor2 = new Requestor();
        requestor2.setWorkerId(2);

        Requestor requestor3 = new Requestor();
        requestor3.setWorkerId(3);

        requestor1.start();
        requestor2.start();
        requestor3.start();
    }

    @PostMapping(value="/statusReceiver", consumes = "application/json", produces = "application/json")
    public void updatePhoneCallStatus(@RequestBody PhoneCallStatusResponse phoneCallStatusResponse) {

        StatusUpdater statusUpdater = new StatusUpdater();
        statusUpdater.setPhoneId(phoneCallStatusResponse.getId());
        statusUpdater.setStatus(phoneCallStatusResponse.getStatus());

        statusUpdater.start();
    }

    @GetMapping(value = "/populateData", produces = "application/json")
    public void populateData() {
        DataPopulator.populateData();
    }

    @GetMapping(value = "/getData", produces = "application/json")
    public Map<String, String> getData() {
        return SundialApplication.PHONE_NO_STATUS_MAP;
    }

}
