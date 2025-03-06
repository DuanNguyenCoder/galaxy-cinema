package com.galaxy.services;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.galaxy.DTO.BillReqDTO;
import com.galaxy.entities.Combo;
import com.galaxy.entities.Customer;
import com.galaxy.entities.Invoice;
import com.galaxy.entities.OrderFilm;
import com.galaxy.entities.OrderFood;
import com.galaxy.entities.Showtime;
import com.galaxy.enums.ResponseTitle;
import com.galaxy.payoads.ApiResponse;
import com.galaxy.repositories.ComboRepo;
import com.galaxy.repositories.InvoiceRepo;
import com.galaxy.repositories.OrderFoodRepo;
import com.galaxy.repositories.ShowtimeRepo;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class InvoiceService {

	@Autowired
	InvoiceRepo invoiceRepo;

	@Autowired
	private OrderFilmService orderFilmSer;
	@Autowired
	private CustomerService customerSer;

	@Autowired
	private ShowtimeRepo showtimeRepo;

	@Autowired
	private ComboRepo comboRepo;

	@Autowired
	private OrderFoodRepo orderFoodRepo;

	public Invoice createInvoice(BillReqDTO billReqDTO, Customer customer) {

		// create invoice
		Invoice invoice = new Invoice();
		invoice.setCustomer(customer);
		invoice.setStatus("PENDING");
		invoice.setTotal(billReqDTO.getTotalPrice());
		invoice.setDayCreateBill(LocalDateTime.now());
		this.save(invoice);

		Map<String, Object> map = new HashMap<>();
		map.put("invoice", invoice);
		// create order film
		Showtime showtime = showtimeRepo.findById((int) billReqDTO.getShowtime().get("id")).get();
		for (Map<String, ?> ticket : billReqDTO.getTickets()) {
			OrderFilm orderFilm = new OrderFilm();
			orderFilm.setInvoice(invoice);
			orderFilm.setMovie(showtime);
			orderFilm.setSeat(ticket.get("seat").toString());
			orderFilm.setType(ticket.get("type").toString());
			orderFilmSer.save(orderFilm);
			map.put("orderFilm", orderFilm);
		}
		// create order food
		for (Map<String, ?> combofood : billReqDTO.getComboFood()) {
			OrderFood orderFood = new OrderFood();
			orderFood.setInvoice(invoice);
			Combo combo = comboRepo.findByNameContaining(combofood.get("name").toString());
			orderFood.setCombo(combo);
			orderFood.setQuantity((int) combofood.get("quantity"));
			orderFoodRepo.save(orderFood);
			map.put("orderFood", orderFood);
		}
		return invoice;

	}

	public void updateStatusInvoice(int invoiceID) {
		Invoice invoice = invoiceRepo.findById(invoiceID).get();
		invoice.setStatus("PAID");
		invoiceRepo.save(invoice);
	}

	public ResponseEntity<ApiResponse<?>> createStripePayment(BillReqDTO billReqDTO, Customer customer) {
		try {
			Invoice invoice = createInvoice(billReqDTO, customer);
			SessionCreateParams params = SessionCreateParams.builder()
					.setMode(SessionCreateParams.Mode.PAYMENT)
					.setSuccessUrl("http://localhost:4200/#/payment_infor?session_id={CHECKOUT_SESSION_ID}") // URL khi
																												// thanh
																												// toán
																												// thành
																												// công
					.setCancelUrl("http://localhost:4200/#/payment_infor?session_id={CHECKOUT_SESSION_ID}") // URL khi
																											// hủy
																											// thanh
																											// toán
					.putMetadata("orderId", String.valueOf(invoice.getId()))
					.addLineItem(SessionCreateParams.LineItem.builder()
							.setQuantity(1L)
							.setPriceData(SessionCreateParams.LineItem.PriceData.builder()
									.setCurrency("vnd")
									.setUnitAmount(billReqDTO.getTotalPrice())
									.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
											.setName("Hóa đơn của bạn")
											.build())
									.build())
							.build())
					.build();

			Session session = Session.create(params);

			return ResponseEntity.ok().body(
					new ApiResponse<>(ResponseTitle.SUCCESS, "Payment intent created", 200, session.getUrl()));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(
					new ApiResponse<>(ResponseTitle.ERROR, "Error: " + e.getMessage(), 400, null));
		}
	}

	public void save(Invoice invoice) {
		invoiceRepo.save(invoice);
	}

}
