package application;

import javafx.beans.property.SimpleStringProperty;

public class TodaySaleList {
	
	public SimpleStringProperty saleProdCode,saleProdName,saleProdSP,saleProdMRP,saleProdDisc,saleProdColour,
	saleProdQty,saleProdCustName;
	
	public TodaySaleList(String saleProdCode, String saleProdName, String saleProdSP, String saleProdMRP, String saleProdDisc
			, String saleProdColour, String saleProdQty,String saleProdCustName){
		this.saleProdCode = new SimpleStringProperty(saleProdCode);
		this.saleProdName = new SimpleStringProperty(saleProdName);
		this.saleProdSP = new SimpleStringProperty(saleProdSP);
		this.saleProdMRP = new SimpleStringProperty(saleProdMRP);
		this.saleProdDisc = new SimpleStringProperty(saleProdDisc);
		this.saleProdColour = new SimpleStringProperty(saleProdColour);
		this.saleProdQty = new SimpleStringProperty(saleProdQty);
		this.saleProdCustName = new SimpleStringProperty(saleProdCustName);
	}

	public SimpleStringProperty getSaleProdCode() {
		return saleProdCode;
	}

	public void setSaleProdCode(SimpleStringProperty saleProdCode) {
		this.saleProdCode = saleProdCode;
	}

	public SimpleStringProperty getSaleProdName() {
		return saleProdName;
	}

	public void setSaleProdName(SimpleStringProperty saleProdName) {
		this.saleProdName = saleProdName;
	}

	public SimpleStringProperty getSaleProdSP() {
		return saleProdSP;
	}

	public void setSaleProdSP(SimpleStringProperty saleProdSP) {
		this.saleProdSP = saleProdSP;
	}

	public SimpleStringProperty getSaleProdMRP() {
		return saleProdMRP;
	}

	public void setSaleProdMRP(SimpleStringProperty saleProdMRP) {
		this.saleProdMRP = saleProdMRP;
	}

	public SimpleStringProperty getSaleProdDisc() {
		return saleProdDisc;
	}

	public void setSaleProdDisc(SimpleStringProperty saleProdDisc) {
		this.saleProdDisc = saleProdDisc;
	}

	public SimpleStringProperty getSaleProdColour() {
		return saleProdColour;
	}

	public void setSaleProdColour(SimpleStringProperty saleProdColour) {
		this.saleProdColour = saleProdColour;
	}

	public SimpleStringProperty getSaleProdQty() {
		return saleProdQty;
	}

	public void setSaleProdQty(SimpleStringProperty saleProdQty) {
		this.saleProdQty = saleProdQty;
	}

	public SimpleStringProperty getSaleProdCustName() {
		return saleProdCustName;
	}

	public void setSaleProdCustName(SimpleStringProperty saleProdCustName) {
		this.saleProdCustName = saleProdCustName;
	}
	
	public SimpleStringProperty saleProdCodeProperty(){return saleProdCode;}
	
	public SimpleStringProperty saleProdNameProperty(){return saleProdName;}
	
	public SimpleStringProperty saleProdSPProperty(){return saleProdSP;}
	
	public SimpleStringProperty saleProdMRPProperty(){return saleProdMRP;}
	
	public SimpleStringProperty saleProdDiscProperty(){return saleProdDisc;}
	
	public SimpleStringProperty saleProdColourProperty(){return saleProdColour;}
	
	public SimpleStringProperty saleProdQtyProperty(){return saleProdQty;}
	
	public SimpleStringProperty saleProdCustNameProperty(){return saleProdCustName;}

}
