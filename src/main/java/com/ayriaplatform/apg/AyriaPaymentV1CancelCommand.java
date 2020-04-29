package com.ayriaplatform.apg;

public class AyriaPaymentV1CancelCommand {

    private String referenceCode;       // Required
    private String cancelDescription;   // Required

    public AyriaPaymentV1CancelCommand(String referenceCode, String cancelDescription) {
        this.referenceCode = referenceCode;
        this.cancelDescription = cancelDescription;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getCancelDescription() {
        return cancelDescription;
    }

    public void setCancelDescription(String cancelDescription) {
        this.cancelDescription = cancelDescription;
    }

}
