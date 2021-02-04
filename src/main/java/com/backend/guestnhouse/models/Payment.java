package com.backend.guestnhouse.models;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Payment {

	@Id
	private String id;
	
	private int tax;
	
	private float totalprice;
	
	private Date paymentDate;
	
	private boolean isRefunded;
	
	private Date canceldate;
	
	private int refundpaid;
	
	private Date created;
	
	private Date modified;
	
	private int archived;
	
	private int housefees;
	
	@DBRef
	private Promocode promocode;
	
	@DBRef
	private Booking booking;

	public Payment() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public float getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public boolean isRefunded() {
		return isRefunded;
	}

	public void setRefunded(boolean isRefunded) {
		this.isRefunded = isRefunded;
	}

	public Date getCanceldate() {
		return canceldate;
	}

	public void setCanceldate(Date canceldate) {
		this.canceldate = canceldate;
	}

	public int getRefundpaid() {
		return refundpaid;
	}

	public void setRefundpaid(int refundpaid) {
		this.refundpaid = refundpaid;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public int getArchived() {
		return archived;
	}

	public void setArchived(int archived) {
		this.archived = archived;
	}

	public int getHousefees() {
		return housefees;
	}

	public void setHousefees(int housefees) {
		this.housefees = housefees;
	}

	public Promocode getPromocode() {
		return promocode;
	}

	public void setPromocode(Promocode promocode) {
		this.promocode = promocode;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
	
	
	
}
