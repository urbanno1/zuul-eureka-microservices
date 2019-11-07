package com.monycell.dbservice.dbservice.model;

import javax.persistence.*;

@Entity
@Table(name = "quotes")
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer quoteId;

    @Column(name = "quote")
    private String quote;

    @Column(name = "user_name")
    private String username;

    public Quote() {
    }

    public Quote(String username, String quote) {
        this.quote = quote;
        this.username = username;
    }

    public Integer getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Integer quoteId) {
        this.quoteId = quoteId;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
