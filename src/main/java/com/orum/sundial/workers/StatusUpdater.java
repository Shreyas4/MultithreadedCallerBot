package com.orum.sundial.workers;

import com.orum.sundial.SundialApplication;

public class StatusUpdater extends Thread {
    String phoneId;
    String status;

    public void setPhoneId(String phoneId) {
        this.phoneId = phoneId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void run() {
        boolean passed = false;
        while (!passed) {
            synchronized (this) {
                try {
                    SundialApplication.PHONE_NO_STATUS_MAP.put(
                            SundialApplication.ID_PHONE_NO_MAP.get(phoneId), status);
//                    System.out.println(SundialApplication.PHONE_NO_STATUS_MAP);
                    passed = true;
                } catch (Exception ignored) {
                }
            }
        }
    }
}
