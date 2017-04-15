package com.teamacronymcoders.base.registrysystem.entity;

public class UpdateInfo {
    private int trackingRange = 64;
    private int updateFrequency = 1;
    private boolean sendVelocityUpdates = true;

    public int getTrackingRange() {
        return trackingRange;
    }

    public void setTrackingRange(int trackingRange) {
        this.trackingRange = trackingRange;
    }

    public int getUpdateFrequency() {
        return updateFrequency;
    }

    public void setUpdateFrequency(int updateFrequency) {
        this.updateFrequency = updateFrequency;
    }

    public boolean isSendVelocityUpdates() {
        return sendVelocityUpdates;
    }

    public void setSendVelocityUpdates(boolean sendVelocityUpdates) {
        this.sendVelocityUpdates = sendVelocityUpdates;
    }
}
