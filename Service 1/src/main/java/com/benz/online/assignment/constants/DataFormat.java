package com.benz.online.assignment.constants;

public enum DataFormat {
    CSV("CSV"),
    XML("XML");

        private String str;

    DataFormat(String str){
            this.str = str;
        }

        public String getStr() {
            return str;
        }
}
