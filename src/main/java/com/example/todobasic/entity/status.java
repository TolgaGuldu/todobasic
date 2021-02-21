package com.example.todobasic.entity;

public enum status {
    //Tamamlandı
    COMPLETED("Completed"),


    //Yapılacak
    TO_BE_HELD("to be Held"),

    //Ertelendi
    DEFERRED("Deferred"),


    //İptal edildi
    CANCELLED("Cancelled");

    private final String statusText;


    public String getStatusText() {
        return statusText;
    }


    status(String statusText) {
        this.statusText = statusText;
    }

}
