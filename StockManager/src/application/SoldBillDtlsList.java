package application;

import javafx.beans.property.SimpleStringProperty;

public class SoldBillDtlsList {
	
	public SimpleStringProperty prodCode,prodName,prodPrice,prodColour,prodQty,sellingDate,custName,custNum,prodID;
	
	public SoldBillDtlsList(String prodCode,String prodName, String prodPrice, String prodColour, String prodQty,
			String sellingDate, String custName,String custNum,String prodID)
	{
		this.prodCode=new SimpleStringProperty(prodCode);
		this.prodName=new SimpleStringProperty(prodName);
		this.prodPrice=new SimpleStringProperty(prodPrice);
		this.prodColour=new SimpleStringProperty(prodColour);
		this.prodQty=new SimpleStringProperty(prodQty);
		this.sellingDate=new SimpleStringProperty(sellingDate);
		this.custName=new SimpleStringProperty(custName);
		this.custNum=new SimpleStringProperty(custNum);
		this.prodID=new SimpleStringProperty(prodID);
	}

	
	public SimpleStringProperty getProdCode() {
		return prodCode;
	}


	public void setProdCode(SimpleStringProperty prodCode) {
		this.prodCode = prodCode;
	}


	public SimpleStringProperty getProdName() {
		return prodName;
	}


	public void setProdName(SimpleStringProperty prodName) {
		this.prodName = prodName;
	}


	public SimpleStringProperty getProdPrice() {
		return prodPrice;
	}


	public void setProdPrice(SimpleStringProperty prodPrice) {
		this.prodPrice = prodPrice;
	}


	public SimpleStringProperty getProdColour() {
		return prodColour;
	}


	public void setProdColour(SimpleStringProperty prodColour) {
		this.prodColour = prodColour;
	}


	public SimpleStringProperty getProdQty() {
		return prodQty;
	}


	public void setProdQty(SimpleStringProperty prodQty) {
		this.prodQty = prodQty;
	}


	public SimpleStringProperty getSellingDate() {
		return sellingDate;
	}


	public void setSellingDate(SimpleStringProperty sellingDate) {
		this.sellingDate = sellingDate;
	}


	public SimpleStringProperty getCustName() {
		return custName;
	}


	public void setCustName(SimpleStringProperty custName) {
		this.custName = custName;
	}


	public SimpleStringProperty getCustNum() {
		return custNum;
	}


	public void setCustNum(SimpleStringProperty custNum) {
		this.custNum = custNum;
	}

	public SimpleStringProperty getProdID() {
		return prodID;
	}


	public void setProdID(SimpleStringProperty prodID) {
		this.prodID = prodID;
	}


	public SimpleStringProperty prodCodeProperty(){return prodCode;}
	
	public SimpleStringProperty prodNameProperty(){return prodName;}
	
	public SimpleStringProperty prodPriceProperty(){return prodPrice;}
	
	public SimpleStringProperty prodColourProperty(){return prodColour;}
	
	public SimpleStringProperty prodQtyProperty(){return prodQty;}
	
	public SimpleStringProperty sellingDateProperty(){return sellingDate;}
	
	public SimpleStringProperty custNameProperty(){return custName;}
	
	public SimpleStringProperty custNumProperty(){return custNum;}
	
	public SimpleStringProperty prodIDProperty(){return prodID;}

}
