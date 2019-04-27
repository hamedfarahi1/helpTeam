package com.kharazmi.helpdesk.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;


//this is for cannot login ticket.
@Entity
@Table(name = "Ticket", schema = "HelpDeskV2", catalog = "")
public class TicketModel {
    private int id;
    private String senderEmail;
    private String text;

    @Id
    @Column(name = "Id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SenderEmail", nullable = false, length = 20)
    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    @Basic
    @Column(name = "Text", nullable = false, length = 100)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TicketModel ticketModel = (TicketModel) o;
        return id == ticketModel.id &&
                Objects.equals(senderEmail, ticketModel.senderEmail) &&
                Objects.equals(text, ticketModel.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, senderEmail, text);
    }

}
