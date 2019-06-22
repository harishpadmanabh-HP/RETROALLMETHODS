package com.hp.hp.retroallmethods.Model;

import java.util.List;

public class DealsModel {
    /**
     * status : true
     * deals : [{"d_id":3,"d_name":"Deal 3 ","d_details":"deal 3 testing","d_image":"8HlrhoEaXWGNgmzsd.png","d_amount":15,"d_start":"2018-08-13 18:30:00","d_stop":"2018-08-14 18:30:00","d_status":1},{"d_id":2,"d_name":"Deal 2","d_details":"","d_image":"qUwsm5Zhu4KM2jXWi.png","d_amount":12,"d_start":"0000-00-00 00:00:00","d_stop":"0000-00-00 00:00:00","d_status":1},{"d_id":1,"d_name":"Tetst ","d_details":"Test descriptioonnnn","d_image":"k0HdLoWqcjPfiCam2.png","d_amount":10,"d_start":"2018-08-12 18:30:00","d_stop":"2018-08-13 18:30:00","d_status":1}]
     * message : Latest Deals!
     */

    private boolean status;
    private String message;
    private List<DealsBean> deals;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DealsBean> getDeals() {
        return deals;
    }

    public void setDeals(List<DealsBean> deals) {
        this.deals = deals;
    }

    public static class DealsBean {
        /**
         * d_id : 3
         * d_name : Deal 3
         * d_details : deal 3 testing
         * d_image : 8HlrhoEaXWGNgmzsd.png
         * d_amount : 15
         * d_start : 2018-08-13 18:30:00
         * d_stop : 2018-08-14 18:30:00
         * d_status : 1
         */

        private int d_id;
        private String d_name;
        private String d_details;
        private String d_image;
        private int d_amount;
        private String d_start;
        private String d_stop;
        private int d_status;

        public int getD_id() {
            return d_id;
        }

        public void setD_id(int d_id) {
            this.d_id = d_id;
        }

        public String getD_name() {
            return d_name;
        }

        public void setD_name(String d_name) {
            this.d_name = d_name;
        }

        public String getD_details() {
            return d_details;
        }

        public void setD_details(String d_details) {
            this.d_details = d_details;
        }

        public String getD_image() {
            return d_image;
        }

        public void setD_image(String d_image) {
            this.d_image = d_image;
        }

        public int getD_amount() {
            return d_amount;
        }

        public void setD_amount(int d_amount) {
            this.d_amount = d_amount;
        }

        public String getD_start() {
            return d_start;
        }

        public void setD_start(String d_start) {
            this.d_start = d_start;
        }

        public String getD_stop() {
            return d_stop;
        }

        public void setD_stop(String d_stop) {
            this.d_stop = d_stop;
        }

        public int getD_status() {
            return d_status;
        }

        public void setD_status(int d_status) {
            this.d_status = d_status;
        }
    }
}
