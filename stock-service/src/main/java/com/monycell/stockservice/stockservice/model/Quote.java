package com.monycell.stockservice.stockservice.model;

import java.math.BigDecimal;

public class Quote {
    private String quote;
    private BigDecimal price;

    public Quote() {
    }

    public Quote(String quote, BigDecimal price) {
        this.quote = quote;
        this.price = price;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}
