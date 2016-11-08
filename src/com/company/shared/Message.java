package com.company.shared;

import java.io.Serializable;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Coded by Ferry on 8-11-2016.
 */
public class Message implements Serializable {
    private User sender;
    private List<User> receivers;
    private String text;
    private Date date;

    public Message(User sender, List<User> receivers, String text)
    {
        date = new Date();

        this.sender = sender;
        this.receivers = receivers;
        this.text = text;
    }

    public User getSender()
    {
        return sender;
    }

    public List<User> getReceivers()
    {
        return Collections.unmodifiableList(receivers);
    }

    public String getText()
    {
        return text;
    }

    public Date getDate()
    {
        return date;
    }

    @Override
    public String toString()
    {
        return "Message{" +
                "sender=" + sender +
                ", receivers=" + receivers +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
