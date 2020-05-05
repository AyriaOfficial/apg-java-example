package com.ayriaplatform.apg;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AyriaPaymentV1CallbackResource {

    @PostMapping("/callback")   // Note: You need to register your callback URL in https://ayriaplatform.com
    public void callback(@RequestBody AyriaPaymentV1DTO dto) {
        Assert.isTrue(dto.isPaid(), "Payment must be paid.");
        // Your domain logic
        // E.g. store it in database as paid payment.
    }

}
