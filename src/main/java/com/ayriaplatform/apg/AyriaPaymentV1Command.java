package com.ayriaplatform.apg;

import java.math.BigDecimal;

public class AyriaPaymentV1Command {

    private Long referralCode;  // Required
    private BigDecimal amount;  // Required
    private String payerMobile; // Required
    private String payerName;
    private String description;
    private String paymentNumber;
    private String extraData;

    public AyriaPaymentV1Command() {
    }

    public AyriaPaymentV1Command(Long referralCode, BigDecimal amount, String payerMobile) {
        this.referralCode = referralCode;
        this.amount = amount;
        this.payerMobile = payerMobile;
    }

    public Long getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(Long referralCode) {
        this.referralCode = referralCode;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPayerMobile() {
        return payerMobile;
    }

    public void setPayerMobile(String payerMobile) {
        this.payerMobile = payerMobile;
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

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

}
