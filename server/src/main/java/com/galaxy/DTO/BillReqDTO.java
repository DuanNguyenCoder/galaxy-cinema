package com.galaxy.DTO;

import java.util.Arrays;
import java.util.Map;

import lombok.Data;

@Data
public class BillReqDTO {

	private Map<String, ?> showtime;
	private Map<String, ?> tickets[];
	private long totalPrice;
	private Map<String, ?> comboFood[];

	@Override
	public String toString() {
		return "Bill{" +
				", showtime=" + showtime +
				", seat=" + Arrays.toString(tickets) +
				", totalPrice=" + totalPrice +
				'}';
	}
}
