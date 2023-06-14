package com.kenzie.statics.shippingboxes;

import java.util.UUID;

public class TrackedBox {

    // PARTICIPANTS: DO NOT CHANGE THE FIELDS boxId or trackingId below. These fields should not be static.
    private String boxId;
    private String trackingId;

    public TrackedBox(String boxId) {
        this.boxId = boxId;
        this.trackingId = createTrackingId();
    }

    public String getTrackingId() {
        return trackingId;
    }

    public String getBoxId() {
        return boxId;
    }

    public String createTrackingId() {
        UUID randomUUID = UUID.randomUUID();
        String randomId = randomUUID.toString();
        return boxId  + randomId;
    }
}
