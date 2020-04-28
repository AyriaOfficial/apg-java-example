package com.ayriaplatform.apg;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * APG(Ayria payment gateway) example app
 *
 */
public class App {

    private final Properties properties;
    private final RestTemplate restTemplate;

    public App() {
        InputStream is = App.class.getResourceAsStream("../../../project.properties");
        properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        restTemplate.getMessageConverters().add(new GsonHttpMessageConverter());
    }

    public ResponseEntity<String> createPayment(AyriaPaymentV1Command cmd) {
        return restTemplate.postForEntity(f("%s/create", apgEndpoint()), cmd, String.class);
    }

    public ResponseEntity<AyriaPaymentV1DTO> getPayment(String referenceCode) {
        return restTemplate.getForEntity(f("%s/get/%s", apgEndpoint(), referenceCode), AyriaPaymentV1DTO.class);
    }

    public Long referralCode() {
        return Long.valueOf(properties.getProperty(Constants.REFERRAL_CODE));
    }

    public String apgEndpoint() {
        return properties.getProperty(Constants.APG_ENDPOINT);
    }

    public String payerMobile() {
        return properties.getProperty(Constants.PAYER_MOBILE_FOR_TEST);
    }

    private String f(String format, Object... args) {
        return String.format(format, args);
    }

}
