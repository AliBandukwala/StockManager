package application;

import javafx.beans.property.SimpleStringProperty;

public class CustSearchList {

	public SimpleStringProperty custSearchName, custSearchNumb, custSearchBDay;
	
	public CustSearchList(String custSearchName, String custSearchNumb,String custSearchBDay)
	{
		this.custSearchName = new SimpleStringProperty(custSearchName);
		this.custSearchNumb = new SimpleStringProperty(custSearchNumb);
		this.custSearchBDay = new SimpleStringProperty(custSearchBDay);
	}

	public SimpleStringProperty getCustSearchName() {
		return custSearchName;
	}

	public void setCustSearchName(SimpleStringProperty custSearchName) {
		this.custSearchName = custSearchName;
	}

	public SimpleStringProperty getCustSearchNumb() {
		return custSearchNumb;
	}

	public void setCustSearchNumb(SimpleStringProperty custSearchNumb) {
		this.custSearchNumb = custSearchNumb;
	}

	public SimpleStringProperty getCustSearchBDay() {
		return custSearchBDay;
	}

	public void setCustSearchBDay(SimpleStringProperty custSearchBDay) {
		this.custSearchBDay = custSearchBDay;
	}
	
	
	public SimpleStringProperty custSearchNameProperty(){return custSearchName;}
	
	public SimpleStringProperty custSearchNumbProperty(){return custSearchNumb;}
	
	public SimpleStringProperty custSearchBDayProperty(){return custSearchBDay;}
}
