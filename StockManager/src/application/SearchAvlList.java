package application;

import javafx.beans.property.SimpleStringProperty;

public class SearchAvlList {
	
	public SimpleStringProperty searchAvlCode,searchAvlName,searchAvlPP,searchAvlMRP,searchAvlColour,searchAvlSize,
	searchAvlQty,searchAvlCatg;
	
	public SearchAvlList(String searchAvlCode, String searchAvlName,String searchAvlPP,String searchAvlMRP,
			String searchAvlColour, String searchAvlSize,String searchAvlQtyLeft,String searchAvlCatg)
	{
		this.searchAvlCode = new SimpleStringProperty(searchAvlCode);
		this.searchAvlName = new SimpleStringProperty(searchAvlName);
		this.searchAvlPP = new SimpleStringProperty(searchAvlPP);
		this.searchAvlMRP = new SimpleStringProperty(searchAvlMRP);
		this.searchAvlQty = new SimpleStringProperty(searchAvlQtyLeft);
		this.searchAvlColour = new SimpleStringProperty(searchAvlColour);
		this.searchAvlCatg = new SimpleStringProperty(searchAvlCatg);
		this.searchAvlSize = new SimpleStringProperty(searchAvlSize);
	}
	
	public SimpleStringProperty getSearchAvlCode() {
		return searchAvlCode;
	}




	public void setSearchAvlCode(SimpleStringProperty searchAvlCode) {
		this.searchAvlCode = searchAvlCode;
	}




	public SimpleStringProperty getSearchAvlName() {
		return searchAvlName;
	}




	public void setSearchAvlName(SimpleStringProperty searchAvlName) {
		this.searchAvlName = searchAvlName;
	}




	public SimpleStringProperty getSearchAvlPP() {
		return searchAvlPP;
	}




	public void setSearchAvlPP(SimpleStringProperty searchAvlPP) {
		this.searchAvlPP = searchAvlPP;
	}




	public SimpleStringProperty getSearchAvlMRP() {
		return searchAvlMRP;
	}




	public void setSearchAvlMRP(SimpleStringProperty searchAvlMRP) {
		this.searchAvlMRP = searchAvlMRP;
	}




	public SimpleStringProperty getSearchAvlQty() {
		return searchAvlQty;
	}




	public void setSearchAvlQty(SimpleStringProperty searchAvlQty) {
		this.searchAvlQty = searchAvlQty;
	}




	public SimpleStringProperty getSearchAvlColour() {
		return searchAvlColour;
	}




	public void setSearchAvlColour(SimpleStringProperty searchAvlColour) {
		this.searchAvlColour = searchAvlColour;
	}




	public SimpleStringProperty getSearchAvlCatg() {
		return searchAvlCatg;
	}




	public void setSearchAvlCatg(SimpleStringProperty searchAvlCatg) {
		this.searchAvlCatg = searchAvlCatg;
	}




	public SimpleStringProperty getSearchAvlSize() {
		return searchAvlSize;
	}




	public void setSearchAvlSize(SimpleStringProperty searchAvlSize) {
		this.searchAvlSize = searchAvlSize;
	}




	public SimpleStringProperty searchAvlCodeProperty(){return searchAvlCode;}
	
	public SimpleStringProperty searchAvlNameProperty(){return searchAvlName;}
	
	public SimpleStringProperty searchAvlMRPProperty(){return searchAvlMRP;}
	
	public SimpleStringProperty searchAvlColourProperty(){return searchAvlColour;}
	
	public SimpleStringProperty searchAvlQtyProperty(){return searchAvlQty;}
	
	public SimpleStringProperty searchAvlCatgProperty(){return searchAvlCatg;}
	
	public SimpleStringProperty searchAvlSizeProperty(){return searchAvlSize;}
	
	public SimpleStringProperty searchAvlPPProperty(){return searchAvlPP;}

}
