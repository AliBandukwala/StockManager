package application;

public class Customers {
	
	String CusName, CusNum;
	int CusID;
	
	public Customers(int CusID, String CusName, String CusNum){
		this.CusID=CusID;
		this.CusName=CusName;
		this.CusNum=CusNum;
		
	}

	public String getCusName() {
		return CusName;
	}

	public void setCusName(String cusName) {
		CusName = cusName;
	}

	public String getCusNum() {
		return CusNum;
	}

	public void setCusNum(String cusNum) {
		CusNum = cusNum;
	}

	public int getCusID() {
		return CusID;
	}

	public void setCusID(int cusID) {
		CusID = cusID;
	}
	
	
	
}
