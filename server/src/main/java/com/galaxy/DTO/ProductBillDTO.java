package com.galaxy.DTO;

import lombok.Data;

@Data
public class ProductBillDTO {

	private int productID;
	private int quantity;
	private String details;

	public String show() {
		return this.productID + "_" + this.quantity + "_" + this.details;
	}
}
