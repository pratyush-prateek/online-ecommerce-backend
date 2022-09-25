package com.ecommerce.api.core.order.models;


import org.springframework.data.mongodb.core.mapping.Document;

@Document("cancellation-requests")
public class CancellationRequest {
    private String orderId;
    private String cancellationReason;
    private Boolean cancellationSuccessful;

    public Boolean getCancellationSuccessful() {
        return cancellationSuccessful;
    }

    public void setCancellationSuccessful(Boolean cancellationSuccessful) {
        this.cancellationSuccessful = cancellationSuccessful;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }
}
