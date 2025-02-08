package in.co.rays.project_3.dto;

import java.util.Date;

public class SupplierDTO extends BaseDTO{
	private String name;
	private String category;
	private Date registrationdate;
	private int paymentterm;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getRegistrationdate() {
		return registrationdate;
	}
	public void setRegistrationdate(Date registrationdate) {
		this.registrationdate = registrationdate;
	}
	public int getPaymentterm() {
		return paymentterm;
	}
	public void setPaymentterm(int paymentterm) {
		this.paymentterm = paymentterm;
	}
	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return null;
	}
		
	

}
