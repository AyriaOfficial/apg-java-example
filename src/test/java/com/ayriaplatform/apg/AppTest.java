package com.ayriaplatform.apg;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;

/**
 * Unit test for App
 */
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
public class AppTest {

    private final App app = new App();
    public static String latestReferenceCode;

    @Test
    @Order(1)
    public void test001Properties() {
        assertNotNull(app.referralCode(), "Referral code should not be null.");
        assertNotNull(app.apgEndpoint(), "APG endpoint should not be null.");
        assertNotNull(app.payerMobile(), "Payer mobile should not be null.");
    }

    @Test
    @Order(2)
    public void test002CreateInvalidCommand() {
        try {
            app.createPayment(new AyriaPaymentV1Command());
            fail("We should get BAD_REQUEST");
        } catch (HttpClientErrorException e) {
            assertTrue(e.getStatusCode().equals(HttpStatus.BAD_REQUEST), "Error code must be 400");
        }
    }

    @Test
    @Order(3)
    public void test003CreateValidCommand() {
        final AyriaPaymentV1Command cmd = new AyriaPaymentV1Command(app.referralCode(), new BigDecimal("1000"), app.payerMobile());
        final ResponseEntity<AyriaPaymentV1DTO> res = app.createPayment(cmd);
        assertTrue(res.getStatusCode().equals(HttpStatus.CREATED), "Status should be 201");
        latestReferenceCode = res.getBody().getReferenceCode();
        assertTrue(StringUtils.hasText(latestReferenceCode), "Reference code should not be empty");
    }

    @Test
    @Order(4)
    public void test004CreateValidCommandFull() {
        final AyriaPaymentV1Command cmd = new AyriaPaymentV1Command(app.referralCode(), new BigDecimal("1000"), app.payerMobile());
        cmd.setDescription("تراکنش تستی");
        cmd.setPayerName("رستم دستان");
        cmd.setPaymentNumber("123456");
        cmd.setExtraData("{\"product\": \"clock\"}");
        final ResponseEntity<AyriaPaymentV1DTO> res = app.createPayment(cmd);
        assertTrue(res.getStatusCode().equals(HttpStatus.CREATED), "Status should be 201");
        latestReferenceCode = res.getBody().getReferenceCode();
        assertTrue(StringUtils.hasText(latestReferenceCode), "Reference code should not be empty");
    }

    @Test
    @Order(5)
    public void test005GetPayment() {
        final ResponseEntity<AyriaPaymentV1DTO> res = app.getPayment(latestReferenceCode);
        assertTrue(res.getStatusCode().equals(HttpStatus.OK), "Status should be 200");
        final AyriaPaymentV1DTO dto = res.getBody();
        assertEquals(latestReferenceCode, dto.getReferenceCode());
        assertEquals(new BigDecimal("1000"), dto.getAmount());
        assertEquals(app.referralCode(), dto.getReferralCode());
        assertEquals(app.payerMobile(), dto.getPayerMobile());
        assertEquals("تراکنش تستی", dto.getDescription());
        assertEquals("رستم دستان", dto.getPayerName());
        assertEquals("123456", dto.getPaymentNumber());
        assertEquals("{\"product\": \"clock\"}", dto.getExtraData());
     // assertEquals("آیریا", dto.getPayeeName());  // Expected value depends to App#referralCode()
        assertFalse(dto.isPaid());
        assertFalse(dto.isCanceled());
        System.out.println(String.format("Payment created at %s, payment url is: %s", dto.getCreatedDate(), app.isProduction() ? dto.getPaymentUrl() : dto.getPaymentUrl().replaceAll("api.ayria.club", "dev.ayria.club")));
    }

    @Test
    @Order(6)
    public void test006CancelPayment() {
        final AyriaPaymentV1CancelCommand cmd = new AyriaPaymentV1CancelCommand(latestReferenceCode, "قیمت اشتباه");
        final ResponseEntity<AyriaPaymentV1DTO> res = app.cancelPayment(cmd);
        assertTrue(res.getStatusCode().equals(HttpStatus.OK), "Status should be 200");
        final AyriaPaymentV1DTO dto = res.getBody();
        assertTrue(dto.isCanceled());
        assertEquals("قیمت اشتباه", dto.getCancelDescription());
    }

    @Test
    @Order(7)
    public void test007CancelPaymentInvalid() {
        final AyriaPaymentV1CancelCommand cmd = new AyriaPaymentV1CancelCommand(latestReferenceCode, "قیمت اشتباه");
        try {
            app.cancelPayment(cmd);
            fail("Already canceled payment can't be canceled.");
        } catch (HttpClientErrorException e) {
            assertTrue(e.getStatusCode().equals(HttpStatus.BAD_REQUEST), "Status should be 400");
        }
    }

}
