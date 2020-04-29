package com.ayriaplatform.apg;

import java.math.BigDecimal;

public class AyriaPaymentV1DTO {

    private BigDecimal amount;
    private Long referralCode;
    private String payerMobile;
    private Long trackingNumber;
    private String referenceCode;
    private String payeeName;
    private String payerName;
    private String description;
    private String extraData;
    private String paymentNumber;
    private boolean paid;
    private boolean canceled;
    private String createdDate;
    private String paymentUrl;
    private String cancelDescription;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(Long referralCode) {
        this.referralCode = referralCode;
    }

    public String getPayerMobile() {
        return payerMobile;
    }

    public void setPayerMobile(String payerMobile) {
        this.payerMobile = payerMobile;
    }

    public Long getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(Long trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getPayerName() {
        return payerName;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public String getCancelDescription() {
        return cancelDescription;
    }

    public void setCancelDescription(String cancelDescription) {
        this.cancelDescription = cancelDescription;
    }

}
