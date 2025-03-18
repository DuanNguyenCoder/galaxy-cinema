package com.galaxy.controllers;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.galaxy.DTO.BillReqDTO;
import com.galaxy.entities.Customer;
import com.galaxy.enums.ResponseTitle;
import com.galaxy.payoads.ApiResponse;
import com.galaxy.services.CustomerService;
import com.galaxy.services.InvoiceService;
import com.galaxy.services.QRcodeService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

@RestController
@RequestMapping("api/order")
public class PaymentController {

    @Autowired
    private InvoiceService invoiceSer;

    @Autowired
    private QRcodeService qrcodeSer;
    @Autowired
    private CustomerService customerService;

    @PostMapping("/create_payment")
    public ResponseEntity<ApiResponse<?>> createStripePayment(@RequestBody BillReqDTO billReqDTO,
            @RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Invalid Token");
        }
        String token = authHeader.substring(7);
        Map<String, Object> customer = customerService.getProfile(token);
        Customer findCustomer = customerService.getCustomerById(Integer.parseInt(customer.get("id").toString()));
        return invoiceSer.createStripePayment(billReqDTO, findCustomer);
    }

    @GetMapping("/check-payment")
    public ResponseEntity<ApiResponse<?>> checkPayment(@RequestParam("session_id") String sessionId)
            throws StripeException {

        Session session = Session.retrieve(sessionId);

        if ("paid".equals(session.getPaymentStatus())) {
            // change status invoice
            int invoiceID = Integer.parseInt(session.getMetadata().get("orderId"));
            String email = session.getMetadata().get("email");
            CompletableFuture.runAsync(() -> {
                qrcodeSer.generateAndSendQR(email, String.valueOf(invoiceID));

            });
            invoiceSer.updateStatusInvoice(invoiceID);
            return ResponseEntity.ok().body(new ApiResponse<>(ResponseTitle.SUCCESS, "Thanh toán thành công!", 200));
        } else {
            return ResponseEntity.ok().body(new ApiResponse<>(ResponseTitle.ERROR, "Thanh toán chưa hoàn tất!", 400));
        }
    }

}
