
package com.lbriceno.dragonsflight.model.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class Outbound extends RealmObject {

    @SerializedName("airline")
    @Expose
    private String airline;
    @SerializedName("airlineImage")
    @Expose
    private String airlineImage;
    @SerializedName("arrivalDate")
    @Expose
    private String arrivalDate;
    @SerializedName("arrivalTime")
    @Expose
    private String arrivalTime;
    @SerializedName("departureDate")
    @Expose
    private String departureDate;
    @SerializedName("departureTime")
    @Expose
    private String departureTime;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("origin")
    @Expose
    private String origin;

    /**
     * 
     * @return
     *     The airline
     */
    public String getAirline() {
        return airline;
    }

    /**
     * 
     * @param airline
     *     The airline
     */
    public void setAirline(String airline) {
        this.airline = airline;
    }

    /**
     * 
     * @return
     *     The airlineImage
     */
    public String getAirlineImage() {
        return airlineImage;
    }

    /**
     * 
     * @param airlineImage
     *     The airlineImage
     */
    public void setAirlineImage(String airlineImage) {
        this.airlineImage = airlineImage;
    }

    /**
     * 
     * @return
     *     The arrivalDate
     */
    public String getArrivalDate() {
        return arrivalDate;
    }

    /**
     * 
     * @param arrivalDate
     *     The arrivalDate
     */
    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * 
     * @return
     *     The arrivalTime
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * 
     * @param arrivalTime
     *     The arrivalTime
     */
    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * 
     * @return
     *     The departureDate
     */
    public String getDepartureDate() {
        return departureDate;
    }

    /**
     * 
     * @param departureDate
     *     The departureDate
     */
    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * 
     * @return
     *     The departureTime
     */
    public String getDepartureTime() {
        return departureTime;
    }

    /**
     * 
     * @param departureTime
     *     The departureTime
     */
    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * 
     * @return
     *     The destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     * 
     * @param destination
     *     The destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     * 
     * @return
     *     The origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * 
     * @param origin
     *     The origin
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

}
