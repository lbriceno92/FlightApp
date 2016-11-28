package com.lbriceno.dragonsflight.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Flight extends RealmObject{

    @PrimaryKey
    private Long id;
    private Inbound inbound;
    private Outbound outbound;
    private Double price;
    private String currency;

    /**
     * 
     * @return
     *     The inbound
     */
    public Inbound getInbound() {
        return inbound;
    }

    /**
     * 
     * @param inbound
     *     The inbound
     */
    public void setInbound(Inbound inbound) {
        this.inbound = inbound;
    }

    /**
     * 
     * @return
     *     The outbound
     */
    public Outbound getOutbound() {
        return outbound;
    }

    /**
     * 
     * @param outbound
     *     The outbound
     */
    public void setOutbound(Outbound outbound) {
        this.outbound = outbound;
    }

    /**
     * 
     * @return
     *     The price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 
     * @param price
     *     The price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 
     * @return
     *     The currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * 
     * @param currency
     *     The currency
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
