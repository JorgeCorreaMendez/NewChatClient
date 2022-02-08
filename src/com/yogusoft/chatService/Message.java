package com.yogusoft.chatService;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message implements Serializable {
    private String username;
    private String message;
    private Date dateMessage;

    public Message(String username, String message, Date dateMessage) {
        this.username = username;
        this.message = message;
        this.dateMessage = dateMessage;
    }

    public String toString() {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");

        return "<" + username + ">[" + formatter.format(dateMessage) + "]<" + message + ">";
    }
}
