package com.orum.sundial.workers;

import java.util.ArrayList;
import java.util.Arrays;

import static com.orum.sundial.SundialApplication.PHONE_NUMBERS_QUEUE;
import static com.orum.sundial.SundialApplication.PHONE_NO_STATUS_MAP;

public class DataPopulator {
    //dummy data can be replaced by a data source
    static ArrayList<String> phNos = new ArrayList<>(Arrays.asList("13018040009", "19842068287", "15512459377", "19362072765", "18582210308", "13018040009", "19842068287", "15512459377", "19362072765"));
    public static void populateData(){
        PHONE_NUMBERS_QUEUE.addAll(phNos);
        for(String phoneNumber: phNos)
            PHONE_NO_STATUS_MAP.put(phoneNumber, "idle");
    }
}
