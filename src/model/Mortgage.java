package model;

public class Mortgage {
	public Mortgage(){
		
	}
	
	public double calculatePayment(String a, String i, String p) throws Exception{
		double monthlyInterestRate = 0;
		double principle = 0;
		int amortizationPeriod = 0;
		
		// Do any conversion necessary
		monthlyInterestRate = Double.parseDouble(i);
		principle = Double.parseDouble(p);
		amortizationPeriod = Integer.parseInt(a);
		
		// Calculations
		
		// Convert given percent to a proper decimal
		monthlyInterestRate = monthlyInterestRate/12; 
		monthlyInterestRate = monthlyInterestRate/100;
		amortizationPeriod = amortizationPeriod*12;
		
		
		double interest = (monthlyInterestRate * principle);//r*A/[1 - (1+r)-n]
		double divisor = Math.pow((1+monthlyInterestRate), (amortizationPeriod)); // amortization given to us is in years
		divisor = 1/divisor;
		divisor = 1-divisor;
		
		interest = (interest/divisor); // final calculation
		
		return interest;
	}

}
