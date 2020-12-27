package com.ayriaplatform.apg;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.http.ResponseEntity;

/**
 * APG(Ayria payment gateway) example app
 *
 */
public class App {

    private final Properties properties;
    private ApgSdk apgSdk;

    public App() {
        InputStream is = App.class.getResourceAsStream("../../../project.properties");
        properties = new Properties();
        try {
            properties.load(is);
            apgSdk = new ApgSdk(properties.getProperty("WALLET_IDENTITY"), properties.getProperty("APG_API_KEY"), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    public ResponseEntity<AyriaPaymentV1DTO> createPayment(AyriaPaymentV1Command cmd) {
        return apgSdk.createPayment(cmd);
    }

    public ResponseEntity<AyriaPaymentV1DTO> getPayment(String referenceCode) {
        return apgSdk.getPayment(referenceCode);
    }

    public ResponseEntity<AyriaPaymentV1DTO> cancelPayment(AyriaPaymentV1CancelCommand cmd) {
        return apgSdk.cancelPayment(cmd);
    }

    public Long referralCode() {
        return apgSdk.getReferralCode();
    }

    public String apgEndpoint() {
        return apgSdk.apgEndpoint();
    }

    public boolean isProduction() {
        return apgSdk.isProduction();
    }

    public String payerMobile() {
        return properties.getProperty("PAYER_MOBILE_FOR_TEST");
    }

}
