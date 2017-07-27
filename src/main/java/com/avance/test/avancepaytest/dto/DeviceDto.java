package com.avance.test.avancepaytest.dto;

import java.sql.Timestamp;

/**
 * Created by a.kuci on 7/27/2017.
 */
public class DeviceDto {

    private Long id;

    private String name;

    private long locationNumber;

    private Timestamp insertedDateTime;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLocationNumber() {
        return locationNumber;
    }

    public void setLocationNumber(long locationNumber) {
        this.locationNumber = locationNumber;
    }

    public Timestamp getInsertedDateTime() {
        return insertedDateTime;
    }

    public void setInsertedDateTime(Timestamp insertedDateTime) {
        this.insertedDateTime = insertedDateTime;
    }

}
