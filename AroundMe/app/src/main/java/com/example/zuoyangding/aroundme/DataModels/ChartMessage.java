package com.example.zuoyangding.aroundme.DataModels;

/**
 * Created by zuoyangding on 4/3/17.
 */

public class ChartMessage {
        private String message;
        private String userId;

        public ChartMessage(){}
        public ChartMessage(String message, String userId) {
            this.message = message;
            this.userId = userId;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage (String message) {this.message = message;}


        public String getUid() {return this.userId;}
}
