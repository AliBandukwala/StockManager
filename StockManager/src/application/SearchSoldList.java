package application;

import javafx.beans.property.SimpleStringProperty;

public class SearchSoldList {
	
	public SimpleStringProperty searchSoldDate,searchSoldSP,searchSoldQty,searchSoldCode,searchSoldName,searchSoldMRP,
	searchSoldColour,searchSoldDisc,searchSoldCustName;
	
	public SearchSoldList(String searchSoldDate,String searchSoldSP,String searchSoldQty,String searchSoldCode,
			String searchSoldName,String searchSoldMRP,String searchSoldColour,String searchSoldDisc,String searchSoldCustName)
	{
		this.searchSoldDate = new SimpleStringProperty(searchSoldDate);
		this.searchSoldCode = new SimpleStringProperty(searchSoldCode);
		this.searchSoldName = new SimpleStringProperty(searchSoldName);
		this.searchSoldSP = new SimpleStringProperty(searchSoldSP);
		this.searchSoldMRP = new SimpleStringProperty(searchSoldMRP);
		this.searchSoldDisc = new SimpleStringProperty(searchSoldDisc);
		this.searchSoldColour = new SimpleStringProperty(searchSoldColour);
		this.searchSoldQty = new SimpleStringProperty(searchSoldQty);
		this.searchSoldCustName = new SimpleStringProperty(searchSoldCustName);
	}
	
    public SimpleStringProperty getSearchSoldDate() {
		return searchSoldDate;
	}



	public void setSearchSoldDate(SimpleStringProperty searchSoldDate) {
		this.searchSoldDate = searchSoldDate;
	}



	public SimpleStringProperty getSearchSoldCode() {
		return searchSoldCode;
	}



	public void setSearchSoldCode(SimpleStringProperty searchSoldCode) {
		this.searchSoldCode = searchSoldCode;
	}



	public SimpleStringProperty getSearchSoldName() {
		return searchSoldName;
	}



	public void setSearchSoldName(SimpleStringProperty searchSoldName) {
		this.searchSoldName = searchSoldName;
	}



	public SimpleStringProperty getSearchSoldSP() {
		return searchSoldSP;
	}



	public void setSearchSoldSP(SimpleStringProperty searchSoldSP) {
		this.searchSoldSP = searchSoldSP;
	}



	public SimpleStringProperty getSearchSoldMRP() {
		return searchSoldMRP;
	}



	public void setSearchSoldMRP(SimpleStringProperty searchSoldMRP) {
		this.searchSoldMRP = searchSoldMRP;
	}



	public SimpleStringProperty getSearchSoldDisc() {
		return searchSoldDisc;
	}



	public void setSearchSoldDisc(SimpleStringProperty searchSoldDisc) {
		this.searchSoldDisc = searchSoldDisc;
	}



	public SimpleStringProperty getSearchSoldColour() {
		return searchSoldColour;
	}



	public void setSearchSoldColour(SimpleStringProperty searchSoldColour) {
		this.searchSoldColour = searchSoldColour;
	}



	public SimpleStringProperty getSearchSoldQty() {
		return searchSoldQty;
	}



	public void setSearchSoldQty(SimpleStringProperty searchSoldQty) {
		this.searchSoldQty = searchSoldQty;
	}



	public SimpleStringProperty getSearchSoldCustName() {
		return searchSoldCustName;
	}



	public void setSearchSoldCustName(SimpleStringProperty searchSoldCustName) {
		this.searchSoldCustName = searchSoldCustName;
	}



	public SimpleStringProperty searchSoldDateProperty(){return searchSoldDate;}
	
    public SimpleStringProperty searchSoldCodeProperty(){return searchSoldCode;}
	
	public SimpleStringProperty searchSoldNameProperty(){return searchSoldName;}
	
	public SimpleStringProperty searchSoldSPProperty(){return searchSoldSP;}
	
	public SimpleStringProperty searchSoldMRPProperty(){return searchSoldMRP;}
	
	public SimpleStringProperty searchSoldDiscProperty(){return searchSoldDisc;}
	
	public SimpleStringProperty searchSoldColourProperty(){return searchSoldColour;}
	
	public SimpleStringProperty searchSoldQtyProperty(){return searchSoldQty;}
	
	public SimpleStringProperty searchSoldCustNameProperty(){return searchSoldCustName;}
	
}
