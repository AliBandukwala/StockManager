package controllers;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.controlsfx.control.textfield.TextFields;

import application.CustSearchList;
import application.DataBase;
import application.SearchAvlList;
import application.SearchSoldList;
import application.SoldBillDtlsList;
import application.TodaySaleList;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

public class MainController {
	
	private ObservableList<SoldBillDtlsList> BillDetailList;
	private ObservableList<TodaySaleList> TodaySaleDetailList;
	private ObservableList<SearchAvlList> SearchAvlArrayList;
	private ObservableList<SearchSoldList> SearchSoldArrayList;
	private ObservableList<CustSearchList> CustSearchArrayList;
	private List<String> SoldProdQtyComboList = FXCollections.observableArrayList();
	private List<String> SoldProdColourComboList = FXCollections.observableArrayList();
	int oldBillNo = 0, total = 0;
	String billIdText = null, Qty = null;
	
	@FXML
	private TextField ProdNameTxt, ProdCodeTxt, ProdPurPriceTxt, ProdMRPTxt, ProdColorTxt, ProdQntTxt,
	SoldProdID,SoldProdName,SoldProdCode,SoldProdPrice,SoldProdCustName,SoldProdCustNum,
	CusNameTxt,CusNumTxt,CustSearchTxt,
	SearchProdTxt,SearchColourTxt,BillSearchTxt;
	
	@FXML
	private DatePicker ProdPurDateDP,
	SoldProdDateDP,
	CusBDayDP,CustSearchBDayDP,
	SearchMainDateDP,SearchEndDateDP;
	
	@FXML
	public ComboBox<String> ProdCatComboBox = new ComboBox<String>();
	public ComboBox<String> ProdSizeComboBox = new ComboBox<String>();
	public ComboBox<String> SoldProdColourComboBox = new ComboBox<String>();
	public ComboBox<String> SoldProdQtyComboBox = new ComboBox<String>();
	public ComboBox<String> SearchCatgComboBox = new ComboBox<String>();
	
	@FXML
	Button TdSlBtn = new Button();
	
	@FXML
	TableView<SearchAvlList> SearchStockProdTbl;
	@FXML
	TableView<SearchSoldList> SearchSoldProdTbl;
	@FXML
	TableView<CustSearchList> CustomerTbl;
	@FXML
	TableView<SoldBillDtlsList>SoldProdTable;
	@FXML
	TableView<TodaySaleList>TodaySaleTbl;
	
	@FXML
	private TableColumn<SoldBillDtlsList,String> TblClmSoldProdCode;
	@FXML
	private TableColumn<SoldBillDtlsList,String> TblClmSoldProdName;
	@FXML
	private TableColumn<SoldBillDtlsList,String> TblClmSoldProdPrice;
	@FXML
	private TableColumn<SoldBillDtlsList,String> TblClmSoldProdColour;
	@FXML
	private TableColumn<SoldBillDtlsList,String> TblClmSoldProdQty;
	@FXML
	private TableColumn<SoldBillDtlsList,String> TblClmSoldSellingDate;
	@FXML
	private TableColumn<SoldBillDtlsList,String> TblClmSoldCustName;
	@FXML
	private TableColumn<SoldBillDtlsList,String> TblClmSoldCustNum;
	@FXML
	private TableColumn<SoldBillDtlsList,String> TblClmSoldProdID;
	
	@FXML
	private TableColumn<TodaySaleList,String> TblClmTdSlCode;
	@FXML
	private TableColumn<TodaySaleList,String> TblClmTdSlName;
	@FXML
	private TableColumn<TodaySaleList,String> TblClmTdSlSP;
	@FXML
	private TableColumn<TodaySaleList,String> TblClmTdSlMRP;
	@FXML
	private TableColumn<TodaySaleList,String> TblClmTdSlDisc;
	@FXML
	private TableColumn<TodaySaleList,String> TblClmTdSlColour;
	@FXML
	private TableColumn<TodaySaleList,String> TblClmTdSlQty;
	@FXML
	private TableColumn<TodaySaleList,String> TblClmTdSlCustName;
	
	@FXML
	private TableColumn<SearchAvlList,String> SearchSoldClmDate;
	@FXML
	private TableColumn<SearchAvlList,String> SearchSoldClmProdCode;
	@FXML
	private TableColumn<SearchAvlList,String> SearchSoldClmProdName;
	@FXML
	private TableColumn<SearchAvlList,String> SearchSoldClmSP;
	@FXML
	private TableColumn<SearchAvlList,String> SearchSoldClmMRP;
	@FXML
	private TableColumn<SearchAvlList,String> SearchSoldClmDisc;
	@FXML
	private TableColumn<SearchAvlList,String> SearchSoldClmColour;
	@FXML
	private TableColumn<SearchAvlList,String> SearchSoldClmQty;
	@FXML
	private TableColumn<SearchAvlList,String> SearchSoldClmCustName;
	
	@FXML
	private TableColumn<SearchAvlList,String> SearchAvlCode;
	@FXML
	private TableColumn<SearchAvlList,String> SearchAvlName;
	@FXML
	private TableColumn<SearchAvlList,String> SearchAvlPP;
	@FXML
	private TableColumn<SearchAvlList,String> SearchAvlMRP;
	@FXML
	private TableColumn<SearchAvlList,String> SearchAvlColour;
	@FXML
	private TableColumn<SearchAvlList,String> SearchAvlQty;
	@FXML
	private TableColumn<SearchAvlList,String> SearchAvlSize;
	@FXML
	private TableColumn<SearchAvlList,String> SearchAvlCatg;
	@FXML
	private TableColumn<SearchAvlList,String> SearchAvlID;
	
	@FXML
	private TableColumn<CustSearchList,String> CustSearchSr;
	@FXML
	private TableColumn<CustSearchList,String> CustNameClm;
	@FXML
	private TableColumn<CustSearchList,String> CustNumbClm;
	@FXML
	private TableColumn<CustSearchList,String> CustBDayClm;
	
	
	@FXML
	private Label NewCustLabel,
	CustInvalidLbl,
	TdSlTotalLabel;
	
	@FXML
	private Button SoldProdNewCustBtn;
	
	
	@FXML
	public void initialize(){
		
		DataBase db = new DataBase();
		//db.updateOrDeleteData("DELETE FROM year");
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd");
		Date c = new Date(cal.getTime().getTime());
		Date dt = null;
		
		try{
			ResultSet r = db.getData("SELECT Date FROM year");
		    if(r != null){
		    	while(r.next()){
				dt = r.getDate(1);
				}
		    }
		    if(c.after(dt)){
		    	cal.set(Calendar.MONTH, 2);
		    	cal.set(Calendar.DAY_OF_MONTH,31);
		    	cal.add(Calendar.YEAR, 1);
		    	boolean upd = db.updateOrDeleteData("UPDATE year SET Date = '"+df.format(cal.getTime())+"'");
		    	if(upd)
		    		db.updateOrDeleteData("UPDATE year SET BillNo1 = 0, BillNo2=0");
		    }
		}catch(Exception e){e.printStackTrace();}
		
		BillDetailList = FXCollections.observableArrayList();
		TodaySaleDetailList = FXCollections.observableArrayList();
		
		ProdCatComboBox.setItems(FXCollections.observableArrayList("School Bag","Trolley Bag","Duffle","Ladies Purse","Others"));
		ProdCatComboBox.setValue("School Bag");
		ProdSizeComboBox.setItems(FXCollections.observableArrayList("KG","1-5","6-12","20","22","24"));
		SearchCatgComboBox.setItems(FXCollections.observableArrayList("School Bag","Trolley Bag","Duffle","Ladies Purse","Others"));
		ProdCatComboBox.setValue("School Bag");
		
		String date = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate local = LocalDate.parse(date,format);
		ProdPurDateDP.setValue(local);
		SoldProdDateDP.setValue(local);
		
		TdSlBtn.requestFocus();
		TodaySaleTbl.setItems(null);
		ResultSet rs = db.getData("SELECT sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, c.CusName "
				+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
				+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
				+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
				+ "WHERE bill1.CurDate = CURRENT_DATE");
		try {
			if(rs != null){
				while(rs.next()){
				String SP = rs.getString(1);
				String pQty = rs.getString(2);
				String Code = rs.getString(3);
				String pName = rs.getString(4);
				String pMRP = rs.getString(5);
				String pColour = rs.getString(6);
				String cusName = rs.getString(7);
				String Discnt = String.valueOf(((Integer.parseInt(pMRP)-Integer.parseInt(SP))/Integer.parseInt(pMRP))*100);
				
				TodaySaleDetailList.add(new TodaySaleList(Code,pName,SP,pMRP,Discnt,pColour,pQty,cusName));
				}
				
				TblClmTdSlCode.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdCode"));
				TblClmTdSlName.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdName"));
				TblClmTdSlSP.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdSP"));
				TblClmTdSlMRP.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdMRP"));
				TblClmTdSlDisc.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdDisc"));
				TblClmTdSlColour.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdColour"));
				TblClmTdSlQty.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdQty"));
				TblClmTdSlCustName.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdCustName"));
				
				TodaySaleTbl.setItems(TodaySaleDetailList);
				int total = 0;
				for(TodaySaleList SP : TodaySaleTbl.getItems()){
					total = total + Integer.parseInt(SP.getSaleProdSP().get());
				}
				TdSlTotalLabel.setText("Total = "+total);
			  }
			} catch (SQLException e) {e.printStackTrace();}
		
		TdSlBtn.setOnKeyTyped(new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				if(event.isControlDown() && event.getCode() == KeyCode.B){
					TodaySaleDetailList.removeAll(TodaySaleDetailList);
					TodaySaleTbl.setItems(null);
					DataBase db = new DataBase();
					ResultSet rs = db.getData("SELECT sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, c.CusName "
				+ "FROM sell2 JOIN bill2 ON bill2.BillID = sell2.BillID "
				+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
				+ "JOIN customer AS c ON c.CusNum = bill2.CusNum "
				+ "WHERE bill2.CurDate = CURRENT_DATE");
					
					try{
						if(rs != null){
							while(rs.next()){
								String SP = rs.getString(1);
								String pQty = rs.getString(2);
								String Code = rs.getString(3);
								String pName = rs.getString(4);
								String pMRP = rs.getString(5);
								String pColour = rs.getString(6);
								String cusName = rs.getString(7);
								String Discnt = String.valueOf(((Integer.parseInt(pMRP)-Integer.parseInt(SP))/Integer.parseInt(pMRP))*100);
								
								TodaySaleDetailList.add(new TodaySaleList(Code,pName,SP,pMRP,Discnt,pColour,pQty,cusName));
							}
							TblClmTdSlCode.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdCode"));
							TblClmTdSlName.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdName"));
							TblClmTdSlSP.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdSP"));
							TblClmTdSlMRP.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdMRP"));
							TblClmTdSlDisc.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdDisc"));
							TblClmTdSlColour.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdColour"));
							TblClmTdSlQty.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdQty"));
							TblClmTdSlCustName.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdCustName"));
							
							TodaySaleTbl.setItems(TodaySaleDetailList);
							int total = 0;
							for(TodaySaleList SP : TodaySaleTbl.getItems()){
								total = total + Integer.parseInt(SP.getSaleProdSP().get());
							}
							TdSlTotalLabel.setText("Total = "+total);
						}else{System.out.println("RS is null");}
					}catch(Exception e){e.printStackTrace();}
				}
				
				//************************************************************************************************
				else if(event.isControlDown() && event.getCode() == KeyCode.A){
					TodaySaleDetailList.removeAll(TodaySaleDetailList);
					TodaySaleTbl.setItems(null);
					ResultSet rs = db.getData("SELECT sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, c.CusName "
							+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
							+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
							+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
							+ "WHERE bill1.CurDate = CURRENT_DATE");
					try {
						if(rs != null){
							while(rs.next()){
							String SP = rs.getString(1);
							String pQty = rs.getString(2);
							String Code = rs.getString(3);
							String pName = rs.getString(4);
							String pMRP = rs.getString(5);
							String pColour = rs.getString(6);
							String cusName = rs.getString(7);
							String Discnt = String.valueOf(((Integer.parseInt(pMRP)-Integer.parseInt(SP))/Integer.parseInt(pMRP))*100);
							
							TodaySaleDetailList.add(new TodaySaleList(Code,pName,SP,pMRP,Discnt,pColour,pQty,cusName));
							}
							
							TblClmTdSlCode.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdCode"));
							TblClmTdSlName.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdName"));
							TblClmTdSlSP.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdSP"));
							TblClmTdSlMRP.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdMRP"));
							TblClmTdSlDisc.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdDisc"));
							TblClmTdSlColour.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdColour"));
							TblClmTdSlQty.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdQty"));
							TblClmTdSlCustName.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdCustName"));
							
							TodaySaleTbl.setItems(TodaySaleDetailList);
							int total = 0;
							for(TodaySaleList SP : TodaySaleTbl.getItems()){
								total = total + Integer.parseInt(SP.getSaleProdSP().get());
							}
							TdSlTotalLabel.setText("Total = "+total);
						  }
						} catch (SQLException e) {e.printStackTrace();}
				}
			}
		});
		
		ArrayList<String> ProdCodeList = new ArrayList<String>();
		ArrayList<String> CustNumbList = new ArrayList<String>();
		ArrayList<String> searchTxtList = new ArrayList<String>();
		
		SoldProdCode.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(SoldProdCode.getText().length()==1){
			    	ResultSet prodCodeSet = db.getData("SELECT ProdCode FROM products WHERE ProdCode like '" 
			   	      +SoldProdCode.getText()+"%'");
			   		ProdCodeList.clear();
				    try{
				    	while(prodCodeSet.next()){
					    String prodCode = prodCodeSet.getString(1);
					    ProdCodeList.add(prodCode);
					    }
				        TextFields.bindAutoCompletion(SoldProdCode, ProdCodeList);
				    }catch(Exception e){e.printStackTrace();}
				}
	              if(SoldProdCode.getText().length()>=3){
	            	SoldProdColourComboList.removeAll(SoldProdColourComboList);
	          		SoldProdColourComboBox.setValue(null);
	          		SoldProdQtyComboList.removeAll(SoldProdQtyComboList);
	          		SoldProdQtyComboBox.setValue(null);
	          		
	                ResultSet prodDtls = db.getData("SELECT ProdID,ProdName,Colour,MRP,Quantity FROM products WHERE ProdCode = '"+SoldProdCode.getText()+"'");
	                try{
	                	if(prodDtls.next()){
	                		SoldProdID.setText(prodDtls.getString(1));
	                		SoldProdName.setText(prodDtls.getString(2));
	                		SoldProdColourComboList.add(prodDtls.getString(3));
	                		SoldProdColourComboBox.setItems((ObservableList<String>) SoldProdColourComboList);
	                		SoldProdPrice.setText(prodDtls.getString(4));
	                		for(int i = 0;i<=Integer.parseInt(prodDtls.getString(5));i++){
	                			SoldProdQtyComboList.add(String.valueOf(i));
	                		}
	                		if(SoldProdQtyComboList.size() == 1){SoldProdQtyComboBox.setPromptText("O.O.S");}
	                		else{SoldProdQtyComboBox.setItems((ObservableList<String>)SoldProdQtyComboList);}
	                      }
	                	else{SoldProdName.setText("*No Product available*");}
	                }catch(Exception e){e.printStackTrace();}	
	            }else{SoldProdName.setText("");}
			}
			
		});
			SoldProdCustNum.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue) {
		      if(SoldProdCustNum.getText().length()==3){
		    	ResultSet custNumb = db.getData("SELECT CusNum FROM customer WHERE CusNum like '" 
		    	  +SoldProdCustNum.getText()+"%'");
		    	CustNumbList.clear();
		    	try{
				    while(custNumb.next()){
				    	String CusNum = custNumb.getString(1);
				        CustNumbList.add(CusNum);
					}
					TextFields.bindAutoCompletion(SoldProdCustNum, CustNumbList);
				}catch(Exception e){e.printStackTrace();}
		    }
		    if(SoldProdCustNum.getText().length()==10){
		    	ResultSet CusName = db.getData("SELECT CusName FROM customer WHERE CusNum = '"+SoldProdCustNum.getText()+"'");
		    	try {
					if(CusName.next()){
						SoldProdCustName.setText(CusName.getString(1));
					}
					else
					{SoldProdCustName.setText("");NewCustLabel.setVisible(true);SoldProdNewCustBtn.setVisible(true);}
				} catch (SQLException e) {e.printStackTrace();}
		    }
		    else{SoldProdCustName.setText("");NewCustLabel.setVisible(false);SoldProdNewCustBtn.setVisible(false);}
		 }
	});
			
	SearchProdTxt.textProperty().addListener(new ChangeListener<String>(){
		@Override
		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				ResultSet srCode = db.getData("SELECT ProdCode FROM products WHERE ProdCode like '"
				+SearchProdTxt.getText()+"%'");
				searchTxtList.clear();
			    try {
			    	while(srCode.next()){
					String code = srCode.getString(1);
					searchTxtList.add(code);
					}
			    TextFields.bindAutoCompletion(SearchProdTxt, searchTxtList);
				} catch (SQLException e) {e.printStackTrace();}
			}
		else if(SearchProdTxt.getText().length()>5){
			ResultSet srName = db.getData("SELECT ProdName FROM products WHERE ProdName like '"
					+SearchProdTxt.getText()+"%'");
			searchTxtList.clear();
		    try {
		    	while(srName.next()){
				String name = srName.getString(1);
				searchTxtList.add(name);
				}
		    TextFields.bindAutoCompletion(SearchProdTxt, searchTxtList);
			} catch (SQLException e) {e.printStackTrace();}
		}
		else{searchTxtList.clear(); TextFields.bindAutoCompletion(SearchProdTxt, searchTxtList);}
	  }
	});
	
			
	SoldProdTable.setRowFactory(new Callback<TableView<SoldBillDtlsList>,TableRow<SoldBillDtlsList>>(){
		@Override
		public TableRow<SoldBillDtlsList> call(TableView<SoldBillDtlsList> param) {
		final TableRow<SoldBillDtlsList> row = new TableRow<>();
		final ContextMenu rowMenu = new ContextMenu();
		MenuItem removeRowItem = new MenuItem("Remove"); 
		removeRowItem.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				SoldProdTable.getItems().remove(row.getItem());
				}	
			});
		rowMenu.getItems().add(removeRowItem);
		row.contextMenuProperty().bind(Bindings.when(Bindings.isNotNull(row.itemProperty())).then(rowMenu)
				.otherwise((ContextMenu)null));
		return row;
	    }		
	  });
	
	SearchStockProdTbl.setRowFactory(new Callback<TableView<SearchAvlList>,TableRow<SearchAvlList>>(){
		@Override
		public TableRow<SearchAvlList> call(TableView<SearchAvlList> param) {
		final TableRow<SearchAvlList> row = new TableRow<>();
		final ContextMenu rowMenu = new ContextMenu();
		MenuItem updateMenu = new MenuItem("Update");
		MenuItem deleteMenu = new MenuItem("Delete");
		
		TextField code,name,pp,mrp,colour,qty,size,catg;
		code = new TextField(); name = new TextField(); pp = new TextField(); mrp = new TextField();
		colour = new TextField(); qty = new TextField(); size = new TextField(); catg = new TextField();
		
		updateMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				
				final Stage stage = new Stage();
				code.setPromptText("Code"); name.setPromptText("Name"); pp.setPromptText("Prch. Price");
				mrp.setPromptText("MRP");colour.setPromptText("Colour"); qty.setPromptText("Qty Avalable");
				size.setPromptText("Size"); catg.setPromptText("Category");
				
				code.setText(row.getItem().getSearchAvlCode().get().toString());
				name.setText(row.getItem().getSearchAvlName().get().toString());
				pp.setText(row.getItem().getSearchAvlPP().get().toString());
				mrp.setText(row.getItem().getSearchAvlMRP().get().toString());
				colour.setText(row.getItem().getSearchAvlColour().get().toString());
				qty.setText(row.getItem().getSearchAvlQty().get().toString());
				size.setText(row.getItem().getSearchAvlSize().get().toString());
				catg.setText(row.getItem().getSearchAvlCatg().get().toString());
				
				TextField[] txtArray = {code,name,pp,mrp,colour,qty,size,catg};
				
				Button updateBtn = new Button("Update");
				updateBtn.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						 DataBase db = new DataBase();
						 Object ob[] = {code.getText().toString(),name.getText().toString(),pp.getText().toString(),
									mrp.getText().toString(),colour.getText().toString(),size.getText().toString(),
									Integer.parseInt(qty.getText().toString()),catg.getText().toString()};
						 
						 boolean updated = db.updateOrDeleteData("UPDATE products SET ProdCode=?,ProdName=?,PurchasePrice=?,"
						 		+ " MRP=?,Colour=?,ProdSize=?, Quantity=?, Category=?"
						 		+ " WHERE ProdCode = '"+code.getText().toString()+"' AND Colour = '"
						 		+colour.getText().toString()+"'",ob);
						 if(updated){
							 stage.close();
							 SearchAvlArrayList.removeAll(SearchAvlArrayList);
							 SearchStockProdTbl.setItems(null);
						 }
							
						 
					}
					
				});
				
				DialogCreator(stage,"Update Product",updateBtn,txtArray,null,300,600);
			}	
		});
		deleteMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				final Stage stage = new Stage();
				Button deleteBtn = new Button("Delete");
				deleteBtn.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						DataBase dbs = new DataBase();
						boolean deleted = dbs.updateOrDeleteData("DELETE FROM products WHERE ProdCode = '"
						        +row.getItem().getSearchAvlCode().get().toString()+"' AND Colour = '"
								+row.getItem().getSearchAvlColour().get().toString()+"'");
						if(deleted){
							SearchStockProdTbl.getItems().remove(row.getItem());
							stage.close();
						}
					}
				});
				
				DialogCreator(stage,"Are you Sure you want to Delete this?",deleteBtn,null,null,300,150);
			}
		});
		rowMenu.getItems().addAll(updateMenu,deleteMenu);
		row.contextMenuProperty().bind(Bindings.when(Bindings.isNotNull(row.itemProperty())).then(rowMenu)
				.otherwise((ContextMenu)null));
		return row;
	    }		
	  });
	
	CustomerTbl.setRowFactory(new Callback<TableView<CustSearchList>,TableRow<CustSearchList>>(){
		@Override
		public TableRow<CustSearchList> call(TableView<CustSearchList> param) {
		final TableRow<CustSearchList> row = new TableRow<>();
		final ContextMenu rowMenu = new ContextMenu();
		MenuItem updateMenu = new MenuItem("Update");
		MenuItem deleteMenu = new MenuItem("Delete");
		
		TextField Name,Numb;
		//DatePicker BDayDP = new DatePicker();
		Name = new TextField(); Numb = new TextField();
		
		updateMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				
				final Stage stage = new Stage();
				Name.setPromptText("Name"); Numb.setPromptText("Phone No."); //BDayDP.setPromptText("B'Day");
				
				Name.setText(row.getItem().getCustSearchName().get().toString());
				Numb.setText(row.getItem().getCustSearchNumb().get().toString());
				TextField[] txtArray = {Name,Numb};
				
				
				//Object ob1[] = {Name.getText().toString(),Numb.getText().toString(),BDayDP.getValue().toString()};
				
				Button updateBtn = new Button("Update");
				updateBtn.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						 DataBase db = new DataBase();
						 Object ob[] = {Name.getText().toString(),Numb.getText().toString()};
						 
						 boolean updated = db.updateOrDeleteData("UPDATE customer SET CusName=?,CusNum=?"
						 		+ " WHERE CusNum = '"+Numb.getText().toString()+"'",ob);
						 if(updated)
							 stage.close();	     
					}
				});

				DialogCreator(stage,"Update Product",updateBtn,txtArray,null,200,250);
			}	
		});
		deleteMenu.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				final Stage stage = new Stage();
				Button deleteBtn = new Button("Delete");
				deleteBtn.setOnAction(new EventHandler<ActionEvent>(){
					@Override
					public void handle(ActionEvent event) {
						DataBase dbs = new DataBase();
						boolean deleted = dbs.updateOrDeleteData("DELETE FROM customer WHERE CusNum = '"
						        +row.getItem().getCustSearchNumb().get().toString()+"'");
						if(deleted){
							SearchStockProdTbl.getItems().remove(row.getItem());
							stage.close();
						}
					}
				});
				
				DialogCreator(stage,"Are you Sure you want to Delete?",deleteBtn,null,null,300,150);
			}
		});
		rowMenu.getItems().addAll(updateMenu,deleteMenu);
		row.contextMenuProperty().bind(Bindings.when(Bindings.isNotNull(row.itemProperty())).then(rowMenu)
				.otherwise((ContextMenu)null));
		return row;
	    }		
	  });
	
	}

	
	public void addProd(ActionEvent event){
		 DataBase db = new DataBase();
		 
		 Object ob[] = {ProdNameTxt.getText(),  ProdCodeTxt.getText(), ProdPurPriceTxt.getText(), ProdMRPTxt.getText(),
				 ProdPurDateDP.getValue().toString(),ProdColorTxt.getText(), ProdSizeComboBox.getValue(), ProdQntTxt.getText(),
				 ProdCatComboBox.getValue()};
		 
		boolean Inserted = db.Insert("INSERT INTO products (ProdName, ProdCode, PurchasePrice, MRP, PurchaseDate, Colour, "
				+ "ProdSize, Quantity, Category)"
		 		+ " values(?,?,?,?,?,?,?,?,?)", ob);
		if(Inserted){
			 ProdNameTxt.setText(""); ProdCodeTxt.setText(""); ProdPurPriceTxt.setText(""); ProdMRPTxt.setText(""); ProdColorTxt.setText("");
			 ProdColorTxt.setText(""); ProdQntTxt.setText("");  ProdSizeComboBox.setValue("");}
		}
	
	
	public void AddToCart(ActionEvent event){
		
		int SP = Integer.parseInt(SoldProdPrice.getText().toString()) * 
				   Integer.parseInt(SoldProdQtyComboBox.getValue().toString());  
		
		BillDetailList.add(new SoldBillDtlsList(SoldProdCode.getText().toString(),SoldProdName.getText().toString(),
				String.valueOf(SP),SoldProdColourComboBox.getValue().toString(),
				SoldProdQtyComboBox.getValue().toString(),SoldProdDateDP.getValue().toString(),SoldProdCustName.getText().toString(),
				SoldProdCustNum.getText().toString(),SoldProdID.getText().toString()));
		
		TblClmSoldProdCode.setCellValueFactory(new PropertyValueFactory<SoldBillDtlsList,String>("prodCode"));
		TblClmSoldProdName.setCellValueFactory(new PropertyValueFactory<SoldBillDtlsList,String>("prodName"));
		TblClmSoldProdPrice.setCellValueFactory(new PropertyValueFactory<SoldBillDtlsList,String>("prodPrice"));
		TblClmSoldProdColour.setCellValueFactory(new PropertyValueFactory<SoldBillDtlsList,String>("prodColour"));
		TblClmSoldProdQty.setCellValueFactory(new PropertyValueFactory<SoldBillDtlsList,String>("prodQty"));
		TblClmSoldSellingDate.setCellValueFactory(new PropertyValueFactory<SoldBillDtlsList,String>("sellingDate"));
		TblClmSoldCustName.setCellValueFactory(new PropertyValueFactory<SoldBillDtlsList,String>("custName"));
		TblClmSoldCustNum.setCellValueFactory(new PropertyValueFactory<SoldBillDtlsList,String>("custNum"));
		TblClmSoldProdID.setCellValueFactory(new PropertyValueFactory<SoldBillDtlsList,String>("prodID"));
		
		SoldProdTable.setItems(BillDetailList);
		SoldProdCode.setText(""); SoldProdName.setText(""); SoldProdPrice.setText("");SoldProdID.setText("");
	}
	
	
	public void SoldProd(MouseEvent event){
		if(event.isControlDown()){
			DataBase db = new DataBase();
			ResultSet Rs = null;
			
			if(!SoldProdTable.getItems().isEmpty()){
			
			for(SoldBillDtlsList item : SoldProdTable.getItems()){
				total = total + Integer.parseInt(item.getProdPrice().get());
			}
			try{
				Rs = db.getData("SELECT BillNo2 FROM year");
			    if(Rs != null){
			    	while(Rs.next()){
					oldBillNo = Rs.getInt(1);
					}
			    }
			    
			    Rs = db.getData("SELECT MAX(BillID) FROM bill2");
			    if(Rs != null){
			    	while(Rs.next()){
			    		billIdText = Rs.getString(1);
			    		}
			    	}
			 }
			catch(Exception e){e.printStackTrace();}
			
			Stage stage = new Stage();
			VBox Invoice = new VBox();
			Invoice.setAlignment(Pos.CENTER);
			
			Label Name = new Label("H.S. BANDUKWALA & Sons");
			Name.setStyle("-fx-font-size:12;-fx-font-weight:bold;");
			Name.setPadding(new Insets(30,0,5,0));
			Label Add = new Label("119 Madhavhill,Waghawadi road,Bhavnagar");
			Add.setStyle("-fx-font-size:09;");
			Label GSTIN = new Label("GSTIN/UIN : 24ADUPB6788P1ZK");
			GSTIN.setStyle("-fx-font-size:09;");
			Label stateCode = new Label("State: Gujarat, Code: 24");
			stateCode.setStyle("-fx-font-size:10;");
			Label line = new Label("----------------------------------------------");
			line.setPadding(new Insets(0,10,0,10));
			line.setPrefSize(250, 5);
			line.setMaxSize(250, 5);
			Label Head = new Label("Bill of Supply/Cash Memo**");
			Head.setStyle("-fx-font-size:11;-fx-font-weight:bold;");
			Head.setPadding(new Insets(15,0,10,0));
			
			HBox extraDtls1 = new HBox();
			extraDtls1.setAlignment(Pos.BASELINE_CENTER);
			Label BillID = new Label("BillID : "+billIdText);
			BillID.setStyle("-fx-font-size:10;");
			BillID.setPadding(new Insets(0,0,0,0));
			Label BillNo = new Label("BillNo : "+String.valueOf(oldBillNo+1));
			BillNo.setStyle("-fx-font-size:10;");
			BillNo.setPadding(new Insets(0,0,0,70));
			extraDtls1.getChildren().addAll(BillID,BillNo);
			
			HBox extraDtls2 = new HBox();
			extraDtls2.setAlignment(Pos.BASELINE_CENTER);
			Label Date = new Label("Date : "+SoldProdDateDP.getValue().toString());
			Date.setStyle("-fx-font-size:10;");
			Date.setPadding(new Insets(0,0,0,0));
			Label CustName = new Label("Buyer : "+SoldProdCustName.getText().toString());
			CustName.setStyle("-fx-font-size:10;");
			CustName.setPadding(new Insets(0,0,0,30));
			extraDtls2.getChildren().addAll(Date,CustName);
			
			HBox Titles = new HBox();
			Label name = new Label("Prod.");
			name.setStyle("-fx-font-size:10;");
			name.setPadding(new Insets(10,0,0,20));
			name.setPrefWidth(150);
			name.setMaxWidth(150);
			Label qty = new Label("Qty.");
			qty.setStyle("-fx-font-size:10;");
			qty.setPadding(new Insets(10,0,0,5));
			qty.setPrefWidth(130);
			qty.setMaxWidth(130);
			Label amt = new Label("Amount");
			amt.setStyle("-fx-font-size:10;");
			amt.setPadding(new Insets(10,0,0,0));
			amt.setPrefWidth(100);
			amt.setMaxWidth(100);
			Titles.getChildren().addAll(name,qty,amt);
			
			Invoice.getChildren().addAll(Name,Add,GSTIN,stateCode,line,Head,extraDtls1,extraDtls2,Titles);
			
			for(SoldBillDtlsList detail : SoldProdTable.getItems()){
				Label pName = new Label(detail.prodName.get());
				pName.setStyle("-fx-font-size:08;");
				pName.setPadding(new Insets(0,0,0,20));
				pName.setPrefWidth(150);
				pName.setMaxWidth(150);
				pName.setStyle("-fx-font-weight:bold;");
				Label pQty = new Label(detail.prodQty.get());
				pQty.setStyle("-fx-font-size:08;");
				pQty.setPadding(new Insets(0,0,0,15));
				pQty.setPrefWidth(130);
				pQty.setMaxWidth(130);
				pQty.setStyle("-fx-font-weight:bold;");
				Label pAmt = new Label(detail.prodPrice.get());
				pAmt.setStyle("-fx-font-size:08;");
				pAmt.setPrefWidth(100);
				pAmt.setMaxWidth(100);
				pAmt.setStyle("-fx-font-weight:bold;");
				
				HBox hBox = new HBox();
				hBox.getChildren().addAll(pName,pQty,pAmt);
				Invoice.getChildren().addAll(hBox);
			}
			
			Label Total = new Label("Total = "+total+" INR");
			Total.setStyle("-fx-font-size:10;-fx-font-weight:bold;");
			Total.setPadding(new Insets(10,10,7,150));
			Label line2 = new Label("----------------------------------------------");
			line2.setPadding(new Insets(0,10,0,10));
			line2.setPrefSize(250, 5);
			line2.setMaxSize(250, 5);
			Label text1 = new Label("We are registered under composition tax system.");
			text1.setStyle("-fx-font-size:09;");
			Label text2 = new Label("No additional tax levied.");
			text2.setStyle("-fx-font-size:09;");
			Label visit = new Label("Visit Again!");
			visit.setStyle("-fx-font-size:10;");
			visit.setPadding(new Insets(10,0,10,0));
			Label thanks = new Label("Thank You!");
			thanks.setStyle("-fx-font-size:10;");
			
			Invoice.getChildren().addAll(Total,line2,text1,text2,visit,thanks);
			
			VBox MainBox = new VBox();
			MainBox.setSpacing(10);
			HBox buttonBox = new HBox();
			buttonBox.setAlignment(Pos.BASELINE_CENTER);
			buttonBox.setSpacing(90);
			
			Button print = new Button("SELL & PRINT");
			print.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					boolean printed = Print(Invoice);
					if(printed){
						boolean BillNoUpdate = true, insertedBill = true, insertedSell = true, QtyUpdate = true;
						String prdQty = null;
						
						Object obBill[] = {oldBillNo+1,SoldProdCustNum.getText().toString(),SoldProdDateDP.getValue().toString(),total};
						insertedBill = db.Insert("INSERT INTO bill2 (BillNo,CusNum,CurDate,Total) values(?,?,?,?)",obBill);
						
						BillNoUpdate = db.updateOrDeleteData("UPDATE year SET BillNo2 = BillNo2 + 1");
						
						for(SoldBillDtlsList sellRows : SoldProdTable.getItems()){
							Object obSell[] = {billIdText,sellRows.getProdID().get(),sellRows.getProdPrice().get(),
									           sellRows.getProdQty().get()};
							
							ResultSet oldQty = db.getData("SELECT Quantity FROM products WHERE ProdID = "+sellRows.getProdID().get());
							try {
								while(oldQty.next()){
									prdQty = oldQty.getString(1);
								}
								String updQty = String.valueOf(Integer.parseInt(prdQty)-Integer.parseInt(sellRows.getProdQty().get())); 
								QtyUpdate = db.updateOrDeleteData("UPDATE products SET Quantity = "+updQty+" WHERE ProdID = "+sellRows.getProdID().get());
							} catch (SQLException e) {e.printStackTrace();}
							
							insertedSell = db.Insert("INSERT INTO sell2 (BillID,ProdID,SellPrice,Qty) values(?,?,?,?)", obSell);
						}
						if(insertedBill && insertedSell && QtyUpdate && BillNoUpdate){
						    stage.close();
							BillDetailList.removeAll(BillDetailList);
						    SoldProdCustName.setText("");
						    SoldProdCustNum.setText("");
						    SoldProdTable.setItems(null);
						}
					}
				}	
			});
			
			Button cancel = new Button("Cancel");
			cancel.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {stage.close();}});
			
			buttonBox.getChildren().addAll(cancel,print);
			MainBox.getChildren().addAll(Invoice,buttonBox);
			
			stage.setScene(new Scene(MainBox,250,BillDetailList.size()*25+400));
			stage.show();
			
			TodaySaleDetailList.removeAll(TodaySaleDetailList);
			
			Rs = db.getData("SELECT sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour,c.CusName "
					+ "FROM sell2 JOIN bill2 ON bill2.BillID = sell2.BillID "
					+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
					+ "JOIN customer AS c ON c.CusNum = bill2.CusNum "
					+ "WHERE bill2.CurDate = CURRENT_DATE");
			try {
				if(Rs != null){
					while(Rs.next()){
					String SP = Rs.getString(1);
					Qty = Rs.getString(2);
					String Code = Rs.getString(3);
					String pName = Rs.getString(4);
					String pMRP = Rs.getString(5);
					String pColour = Rs.getString(6);
					String cusName = Rs.getString(7);
					String Discnt = String.valueOf(((Integer.parseInt(pMRP)-Integer.parseInt(SP))/Integer.parseInt(pMRP))*100);
					
					TodaySaleDetailList.add(new TodaySaleList(Code,pName,SP,pMRP,Discnt,pColour,Qty,cusName));
				   }
					
					TblClmTdSlCode.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdCode"));
					TblClmTdSlName.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdName"));
					TblClmTdSlSP.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdSP"));
					TblClmTdSlMRP.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdMRP"));
					TblClmTdSlDisc.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdDisc"));
					TblClmTdSlColour.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdColour"));
					TblClmTdSlQty.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdQty"));
					TblClmTdSlCustName.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdCustName"));
					
					TodaySaleTbl.setItems(null);
					TodaySaleTbl.setItems(TodaySaleDetailList);
					
					int totalAmt = 0;
					for(TodaySaleList SP : TodaySaleTbl.getItems()){
						totalAmt = totalAmt + Integer.parseInt(SP.getSaleProdSP().get());
					}
					TdSlTotalLabel.setText("Total = "+totalAmt);

				  }
				} catch (SQLException e) {e.printStackTrace();}
			}
		}
		
		else{
			DataBase db = new DataBase();
			ResultSet Rs = null;
			
			if(!SoldProdTable.getItems().isEmpty()){
			
			for(SoldBillDtlsList item : SoldProdTable.getItems()){
				total = total + Integer.parseInt(item.getProdPrice().get());
			}
			try{
				Rs = db.getData("SELECT BillNo1 FROM year");
			    if(Rs != null){
			    	while(Rs.next()){
					oldBillNo = Rs.getInt(1);
					}
			    }
			    
			    Rs = db.getData("SELECT MAX(BillID) FROM bill1");
			    if(Rs != null){
			    	while(Rs.next()){
			    		billIdText = Rs.getString(1);
			    		}
			    	}
			 }
			catch(Exception e){e.printStackTrace();}
			
			Stage stage = new Stage();
			VBox Invoice = new VBox();
			Invoice.setAlignment(Pos.CENTER);
			
			Label Name = new Label("H.S. BANDUKWALA & Sons");
			Name.setStyle("-fx-font-size:12;-fx-font-weight:bold;");
			Name.setPadding(new Insets(30,0,5,0));
			Label Add = new Label("119 Madhavhill,Waghawadi road,Bhavnagar");
			Add.setStyle("-fx-font-size:09;");
			Label GSTIN = new Label("GSTIN/UIN : 24ADUPB6788P1ZK");
			GSTIN.setStyle("-fx-font-size:09;");
			Label stateCode = new Label("State: Gujarat, Code: 24");
			stateCode.setStyle("-fx-font-size:10;");
			Label line = new Label("----------------------------------------------");
			line.setPadding(new Insets(0,10,0,10));
			line.setPrefSize(250, 5);
			line.setMaxSize(250, 5);
			Label Head = new Label("Bill of Supply/Cash Memo");
			Head.setStyle("-fx-font-size:11;-fx-font-weight:bold;");
			Head.setPadding(new Insets(15,0,10,0));
			
			HBox extraDtls1 = new HBox();
			extraDtls1.setAlignment(Pos.BASELINE_CENTER);
			Label BillID = new Label("BillID : "+billIdText);
			BillID.setStyle("-fx-font-size:10;");
			BillID.setPadding(new Insets(0,0,0,0));
			Label BillNo = new Label("BillNo : "+String.valueOf(oldBillNo+1));
			BillNo.setStyle("-fx-font-size:10;");
			BillNo.setPadding(new Insets(0,0,0,70));
			extraDtls1.getChildren().addAll(BillID,BillNo);
			
			HBox extraDtls2 = new HBox();
			extraDtls2.setAlignment(Pos.BASELINE_CENTER);
			Label Date = new Label("Date : "+SoldProdDateDP.getValue().toString());
			Date.setStyle("-fx-font-size:10;");
			Date.setPadding(new Insets(0,0,0,0));
			Label CustName = new Label("Buyer : "+SoldProdCustName.getText().toString());
			CustName.setStyle("-fx-font-size:10;");
			CustName.setPadding(new Insets(0,0,0,30));
			extraDtls2.getChildren().addAll(Date,CustName);
			
			HBox Titles = new HBox();
			Label name = new Label("Prod.");
			name.setStyle("-fx-font-size:10;");
			name.setPadding(new Insets(10,0,0,20));
			name.setPrefWidth(150);
			name.setMaxWidth(150);
			Label qty = new Label("Qty.");
			qty.setStyle("-fx-font-size:10;");
			qty.setPadding(new Insets(10,0,0,5));
			qty.setPrefWidth(130);
			qty.setMaxWidth(130);
			Label amt = new Label("Amount");
			amt.setStyle("-fx-font-size:10;");
			amt.setPadding(new Insets(10,0,0,0));
			amt.setPrefWidth(100);
			amt.setMaxWidth(100);
			Titles.getChildren().addAll(name,qty,amt);
			
			Invoice.getChildren().addAll(Name,Add,GSTIN,stateCode,line,Head,extraDtls1,extraDtls2,Titles);
			
			for(SoldBillDtlsList detail : SoldProdTable.getItems()){
				Label pName = new Label(detail.prodName.get());
				pName.setStyle("-fx-font-size:08;");
				pName.setPadding(new Insets(0,0,0,20));
				pName.setPrefWidth(150);
				pName.setMaxWidth(150);
				pName.setStyle("-fx-font-weight:bold;");
				Label pQty = new Label(detail.prodQty.get());
				pQty.setStyle("-fx-font-size:08;");
				pQty.setPadding(new Insets(0,0,0,15));
				pQty.setPrefWidth(130);
				pQty.setMaxWidth(130);
				pQty.setStyle("-fx-font-weight:bold;");
				Label pAmt = new Label(detail.prodPrice.get());
				pAmt.setStyle("-fx-font-size:08;");
				pAmt.setPrefWidth(100);
				pAmt.setMaxWidth(100);
				pAmt.setStyle("-fx-font-weight:bold;");
				
				HBox hBox = new HBox();
				hBox.getChildren().addAll(pName,pQty,pAmt);
				Invoice.getChildren().addAll(hBox);
			}
			
			Label Total = new Label("Total = "+total+" INR");
			Total.setStyle("-fx-font-size:10;-fx-font-weight:bold;");
			Total.setPadding(new Insets(10,10,7,150));
			Label line2 = new Label("----------------------------------------------");
			line2.setPadding(new Insets(0,10,0,10));
			line2.setPrefSize(250, 5);
			line2.setMaxSize(250, 5);
			Label text1 = new Label("We are registered under composition tax system.");
			text1.setStyle("-fx-font-size:09;");
			Label text2 = new Label("No additional tax levied.");
			text2.setStyle("-fx-font-size:09;");
			Label visit = new Label("Visit Again!");
			visit.setStyle("-fx-font-size:10;");
			visit.setPadding(new Insets(10,0,10,0));
			Label thanks = new Label("Thank You!");
			thanks.setStyle("-fx-font-size:10;");
			
			Invoice.getChildren().addAll(Total,line2,text1,text2,visit,thanks);
			
			VBox MainBox = new VBox();
			MainBox.setSpacing(10);
			HBox buttonBox = new HBox();
			buttonBox.setAlignment(Pos.BASELINE_CENTER);
			buttonBox.setSpacing(90);
			
			Button print = new Button("SELL & PRINT");
			print.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {
					boolean printed = Print(Invoice);
					if(printed){
						boolean BillNoUpdate = true, insertedBill = true, insertedSell = true, QtyUpdate = true;
						String prdQty = null;
						
						Object obBill[] = {oldBillNo+1,SoldProdCustNum.getText().toString(),SoldProdDateDP.getValue().toString(),total};
						insertedBill = db.Insert("INSERT INTO bill1 (BillNo,CusNum,CurDate,Total) values(?,?,?,?)",obBill);
						
						BillNoUpdate = db.updateOrDeleteData("UPDATE year SET BillNo1 = BillNo1 + 1");
						
						for(SoldBillDtlsList sellRows : SoldProdTable.getItems()){
							Object obSell[] = {billIdText,sellRows.getProdID().get(),sellRows.getProdPrice().get(),
									           sellRows.getProdQty().get()};
							
							ResultSet oldQty = db.getData("SELECT Quantity FROM products WHERE ProdID = "+sellRows.getProdID().get());
							try {
								while(oldQty.next()){
									prdQty = oldQty.getString(1);
								}
								String updQty = String.valueOf(Integer.parseInt(prdQty)-Integer.parseInt(sellRows.getProdQty().get())); 
								QtyUpdate = db.updateOrDeleteData("UPDATE products SET Quantity = "+updQty+" WHERE ProdID = "+sellRows.getProdID().get());
							} catch (SQLException e) {e.printStackTrace();}
							
							insertedSell = db.Insert("INSERT INTO sell1 (BillID,ProdID,SellPrice,Qty) values(?,?,?,?)", obSell);
						}
						if(insertedBill && insertedSell && QtyUpdate && BillNoUpdate){
						    stage.close();
							BillDetailList.removeAll(BillDetailList);
						    SoldProdCustName.setText("");
						    SoldProdCustNum.setText("");
						    SoldProdTable.setItems(null);
						}
					}
				}	
			});
			
			Button cancel = new Button("Cancel");
			cancel.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {stage.close();}});
			
			buttonBox.getChildren().addAll(cancel,print);
			MainBox.getChildren().addAll(Invoice,buttonBox);
			
			stage.setScene(new Scene(MainBox,250,BillDetailList.size()*25+400));
			stage.show();
			
			TodaySaleDetailList.removeAll(TodaySaleDetailList);
			
			Rs = db.getData("SELECT sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour,c.CusName "
					+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
					+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
					+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
					+ "WHERE bill1.CurDate = CURRENT_DATE");
			try {
				if(Rs != null){
					while(Rs.next()){
					String SP = Rs.getString(1);
					Qty = Rs.getString(2);
					String Code = Rs.getString(3);
					String pName = Rs.getString(4);
					String pMRP = Rs.getString(5);
					String pColour = Rs.getString(6);
					String cusName = Rs.getString(7);
					String Discnt = String.valueOf(((Integer.parseInt(pMRP)-Integer.parseInt(SP))/Integer.parseInt(pMRP))*100);
					
					TodaySaleDetailList.add(new TodaySaleList(Code,pName,SP,pMRP,Discnt,pColour,Qty,cusName));
				   }
					
					TblClmTdSlCode.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdCode"));
					TblClmTdSlName.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdName"));
					TblClmTdSlSP.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdSP"));
					TblClmTdSlMRP.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdMRP"));
					TblClmTdSlDisc.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdDisc"));
					TblClmTdSlColour.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdColour"));
					TblClmTdSlQty.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdQty"));
					TblClmTdSlCustName.setCellValueFactory(new PropertyValueFactory<TodaySaleList,String>("saleProdCustName"));
					
					TodaySaleTbl.setItems(null);
					TodaySaleTbl.setItems(TodaySaleDetailList);
					
					int totalAmt = 0;
					for(TodaySaleList SP : TodaySaleTbl.getItems()){
						totalAmt = totalAmt + Integer.parseInt(SP.getSaleProdSP().get());
					}
					TdSlTotalLabel.setText("Total = "+totalAmt);

				  }
				} catch (SQLException e) {e.printStackTrace();}
			}
		}
	}
		
	public void ProdAddNewCust(ActionEvent event){
		
		final Stage st = new Stage();
		TextField addCusName = new TextField();
		addCusName.setPromptText("Customer's Name");
		TextField addCusNumb = new TextField();
		addCusNumb.setText(SoldProdCustNum.getText().toString());
		addCusNumb.setPromptText("Customer's Number");
		DatePicker addCusBDayDP = new DatePicker();
		addCusBDayDP.setPromptText("Birth Day");
		
		TextField[] txts = {addCusName,addCusNumb};
		
		Button add = new Button("Add");
		add.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				DataBase db = new DataBase();
				Object ob[] = {addCusName.getText().toString(),addCusNumb.getText().toString(),
						addCusBDayDP.getValue().toString()};
				boolean custInserted = db.Insert("INSERT INTO customer (CusName,CusNum,CusBDay) values(?,?,?)", ob);
				if(custInserted){
					SoldProdCustName.setText(addCusName.getText().toString());
					SoldProdCustNum.setText(addCusNumb.getText().toString());
					st.close();
				}
			}
		});
		
		DialogCreator(st,"Add new Customer",add,txts,addCusBDayDP,350,300);
	}

	public void addCust(ActionEvent event){
		DataBase db = new DataBase();
		
		Object ob[] = {CusNameTxt.getText(), CusNumTxt.getText(), CusBDayDP.getValue().toString()};
		
		boolean Inserted = db.Insert("INSERT INTO customer (CusName,CusNum,CusBDay) values(?,?,?)", ob);
		if(Inserted){CusNameTxt.setText(""); CusNumTxt.setText("");}
	}
	
	
	public void CustSearch(ActionEvent event){
		DataBase db = new DataBase();
		if(CustSearchArrayList != null)
			CustSearchArrayList.removeAll(CustSearchArrayList);
		
		if(CustSearchTxt.getText().equalsIgnoreCase("All Customers")){
			ResultSet rs = db.getData("SELECT CusName,CusNum,CusBDay FROM customer");
			CustSearchRs(rs);
			CustInvalidLbl.setVisible(false);
			}
		else if(CustSearchTxt.getText().length() >= 1 && (CustSearchTxt.getText().startsWith("9") || 
				CustSearchTxt.getText().startsWith("8") || CustSearchTxt.getText().startsWith("7") || 
				CustSearchTxt.getText().startsWith("+")))
		{
			ResultSet rs = db.getData("SELECT CusName,CusNum,CusBDay FROM customer WHERE CusNum like '"+CustSearchTxt.getText()
			+"%'");
			CustSearchRs(rs);
			CustInvalidLbl.setVisible(false);
		}
		else if(CustSearchTxt.getText().length() >= 2 && !CustSearchTxt.getText().equalsIgnoreCase("All Customers"))
		{
			ResultSet rs = db.getData("SELECT CusName,CusNum,CusBDay FROM customer WHERE CusName like '"
		                               +CustSearchTxt.getText()+"%'");
			CustSearchRs(rs);
			CustInvalidLbl.setVisible(false);
		}
		else if(CustSearchBDayDP != null){
			if(CustSearchTxt != null)
				CustSearchTxt.setText("");
			ResultSet rs = db.getData("SELECT CusName,CusNum,CusBDay FROM customer WHERE CusBDay = '"
		    +CustSearchBDayDP.getValue()+"'");
			CustSearchRs(rs);
			CustInvalidLbl.setVisible(false);
		}
		
		else{
			CustInvalidLbl.setVisible(true);
		    if(CustSearchArrayList != null)
		    	CustomerTbl.getItems().removeAll(CustSearchArrayList);
		}
	}
	
	
	public void search(MouseEvent event){
		if(event.isControlDown()){
			DataBase db = new DataBase();
			ResultSet rs = null, rs2 = null;
			
			if(!SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() && 
					SearchCatgComboBox.getValue() == null && SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() == null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize,Quantity,Category FROM products "
					+ "WHERE ProdCode like '"+SearchProdTxt.getText().toString()+"%'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdCode like '"+SearchProdTxt.getText().toString()+"%'");
				}
				
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize,Quantity,Category FROM products "
						+ "WHERE ProdName like '"+SearchProdTxt.getText().toString()+"%'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdName like '"+SearchProdTxt.getText().toString()+"%'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() == null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"' AND Colour = '"+SearchColourTxt.getText()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"' AND Colour = '"+SearchColourTxt.getText()+"'");
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() == null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"' AND Category = '"+SearchCatgComboBox.getValue()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"' AND Category = '"+SearchCatgComboBox.getValue()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() == null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"' AND Colour = '"+SearchColourTxt.getText()
					+"' AND Category = '"+SearchCatgComboBox.getValue()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"' AND Colour = '"+SearchColourTxt.getText()
						+"' AND Category = '"+SearchCatgComboBox.getValue()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			
			else if(SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null && SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() == null){
				
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE Colour = '"+SearchColourTxt.getText().toString()+"'");
				
				SearchAvlProd(rs);
				//#########################################
			}
			
			else if(SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null && SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() == null){
				
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE Category = '"+SearchCatgComboBox.getValue().toString()+"'");
				
				SearchAvlProd(rs);
				//#########################################
			}
			
			else if(SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() == null){
				
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE Colour = '"+SearchColourTxt.getText().toString()+"' AND Category = '"+SearchCatgComboBox.getValue()+"'");
				
				SearchAvlProd(rs);
				//#########################################
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() != null && 
					SearchEndDateDP.getValue() == null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate >= '"+SearchMainDateDP.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate >= '"+SearchMainDateDP.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() != null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate <= '"+SearchEndDateDP.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate <= '"+SearchEndDateDP.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() != null && 
					SearchEndDateDP.getValue() != null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND Bill2.CurDate <= '"+SearchEndDateDP.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND Bill2.CurDate <= '"+SearchEndDateDP.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() != null && 
					SearchEndDateDP.getValue() == null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()+"'");
				}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null&& SearchMainDateDP.getValue() != null && 
					SearchEndDateDP.getValue() == null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() != null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate <= '"+SearchEndDateDP.getValue()
						+ "' AND p.Colour = "+SearchColourTxt.getText()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate <= '"+SearchEndDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() != null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate <= '"+SearchEndDateDP.getValue()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate <= '"+SearchEndDateDP.getValue()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null&& SearchMainDateDP.getValue() != null && 
					SearchEndDateDP.getValue() == null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() != null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate <= '"+SearchEndDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate <= '"+SearchEndDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue());
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null&& SearchMainDateDP.getValue() != null && 
					SearchEndDateDP.getValue() != null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND Bill2.CurDate <= '"+SearchEndDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND Bill2.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND Bill2.CurDate <= '"+SearchEndDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			
			else if(!BillSearchTxt.getText().isEmpty() && SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() == null){
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE Bill2.BillID = '"+BillSearchTxt.getText().toString()+"'");
				
				SearchSoldProd(rs2);
			}
			
			else if(!BillSearchTxt.getText().isEmpty() && SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() != null && 
					SearchEndDateDP.getValue() == null){
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE Bill2.BillID = '"+BillSearchTxt.getText().toString()
						+"' AND Bill2.CurDate >= '"+SearchMainDateDP.getValue()+"'");
				
				SearchSoldProd(rs2);
			}
			
			else if(!BillSearchTxt.getText().isEmpty() && SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() != null){
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE Bill2.BillID = '"+BillSearchTxt.getText().toString()
						+"' AND Bill2.CurDate <= '"+SearchEndDateDP.getValue()+"'");
				
				SearchSoldProd(rs2);
			}
			
			else if(!BillSearchTxt.getText().isEmpty() && SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() != null && 
					SearchEndDateDP.getValue() != null){
				rs2 = db.getData("SELECT Bill2.CurDate, sell2.SellPrice, sell2.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell2 JOIN bill2 ON Bill2.BillID = sell2.BillID "
						+ "JOIN products AS p ON sell2.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = Bill2.CusNum "
						+ "WHERE Bill2.BillID = '"+BillSearchTxt.getText().toString()
						+"' AND Bill2.CurDate >= '"+SearchMainDateDP.getValue()
						+"' AND Bill2.CurDate <= '"+SearchEndDateDP.getValue()+"'");
				
				SearchSoldProd(rs2);
			}
			
			else{ return; }
		}
		
		//ELSE IF***************************************************************************************************
		else if(event.isAltDown()){
			
		}
		
		//ELSE******************************************************************************************************
		else{
			DataBase db = new DataBase();
			ResultSet rs = null, rs2 = null;
			
			if(!SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() && 
					SearchCatgComboBox.getValue() == null && SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() == null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize,Quantity,Category FROM products "
					+ "WHERE ProdCode like '"+SearchProdTxt.getText().toString()+"%'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdCode like '"+SearchProdTxt.getText().toString()+"%'");
				}
				
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize,Quantity,Category FROM products "
						+ "WHERE ProdName like '"+SearchProdTxt.getText().toString()+"%'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdName like '"+SearchProdTxt.getText().toString()+"%'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() == null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"' AND Colour = '"+SearchColourTxt.getText()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"' AND Colour = '"+SearchColourTxt.getText()+"'");
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() == null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"' AND Category = '"+SearchCatgComboBox.getValue()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"' AND Category = '"+SearchCatgComboBox.getValue()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() == null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"' AND Colour = '"+SearchColourTxt.getText()
					+"' AND Category = '"+SearchCatgComboBox.getValue()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"' AND Colour = '"+SearchColourTxt.getText()
						+"' AND Category = '"+SearchCatgComboBox.getValue()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			
			else if(SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null && SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() == null){
				
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE Colour = '"+SearchColourTxt.getText().toString()+"'");
				
				SearchAvlProd(rs);
				//#########################################
			}
			
			else if(SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null && SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() == null){
				
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE Category = '"+SearchCatgComboBox.getValue().toString()+"'");
				
				SearchAvlProd(rs);
				//#########################################
			}
			
			else if(SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() == null){
				
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE Colour = '"+SearchColourTxt.getText().toString()+"' AND Category = '"+SearchCatgComboBox.getValue()+"'");
				
				SearchAvlProd(rs);
				//#########################################
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() != null && 
					SearchEndDateDP.getValue() == null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate >= '"+SearchMainDateDP.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate >= '"+SearchMainDateDP.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() != null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate <= '"+SearchEndDateDP.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate <= '"+SearchEndDateDP.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() != null && 
					SearchEndDateDP.getValue() != null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND bill1.CurDate <= '"+SearchEndDateDP.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND bill1.CurDate <= '"+SearchEndDateDP.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() != null && 
					SearchEndDateDP.getValue() == null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()+"'");
				}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null&& SearchMainDateDP.getValue() != null && 
					SearchEndDateDP.getValue() == null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() != null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate <= '"+SearchEndDateDP.getValue()
						+ "' AND p.Colour = "+SearchColourTxt.getText()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate <= '"+SearchEndDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() != null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate <= '"+SearchEndDateDP.getValue()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate <= '"+SearchEndDateDP.getValue()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null&& SearchMainDateDP.getValue() != null && 
					SearchEndDateDP.getValue() == null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() != null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate <= '"+SearchEndDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate <= '"+SearchEndDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue());
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			else if(!SearchProdTxt.getText().toString().isEmpty() && !SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() != null&& SearchMainDateDP.getValue() != null && 
					SearchEndDateDP.getValue() != null){
				if(SearchProdTxt.getText().length()>=2 && SearchProdTxt.getText().length()<=5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
					+ "WHERE ProdCode = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdCode = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND bill1.CurDate <= '"+SearchEndDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
				}
			
		    	else if(SearchProdTxt.getText().length()>5){
				rs = db.getData("SELECT ProdCode,ProdName,PurchasePrice,MRP,Colour,ProdSize, Quantity, Category FROM products "
						+ "WHERE ProdName = '"+SearchProdTxt.getText().toString()+"'");
				
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE p.ProdName = '"+SearchProdTxt.getText().toString()
						+ "' AND bill1.CurDate >= '"+SearchMainDateDP.getValue()
						+ "' AND bill1.CurDate <= '"+SearchEndDateDP.getValue()
						+ "' AND p.Colour = '"+SearchColourTxt.getText()
						+ "' AND p.Category = '"+SearchCatgComboBox.getValue()+"'");
		    	}
				
				SearchAvlProd(rs);
				SearchSoldProd(rs2);
			}
			
			
			else if(!BillSearchTxt.getText().isEmpty() && SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() == null){
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE bill1.BillID = '"+BillSearchTxt.getText().toString()+"'");
				
				SearchSoldProd(rs2);
			}
			
			else if(!BillSearchTxt.getText().isEmpty() && SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() != null && 
					SearchEndDateDP.getValue() == null){
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE bill1.BillID = '"+BillSearchTxt.getText().toString()
						+"' AND bill1.CurDate >= '"+SearchMainDateDP.getValue()+"'");
				
				SearchSoldProd(rs2);
			}
			
			else if(!BillSearchTxt.getText().isEmpty() && SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() == null && 
					SearchEndDateDP.getValue() != null){
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE bill1.BillID = '"+BillSearchTxt.getText().toString()
						+"' AND bill1.CurDate <= '"+SearchEndDateDP.getValue()+"'");
				
				SearchSoldProd(rs2);
			}
			
			else if(!BillSearchTxt.getText().isEmpty() && SearchProdTxt.getText().toString().isEmpty() && SearchColourTxt.getText().isEmpty() &&
					SearchCatgComboBox.getValue() == null&& SearchMainDateDP.getValue() != null && 
					SearchEndDateDP.getValue() != null){
				rs2 = db.getData("SELECT bill1.CurDate, sell1.SellPrice, sell1.Qty, p.ProdCode, p.ProdName, p.MRP, p.Colour, p.Quantity, c.CusName "
						+ "FROM sell1 JOIN bill1 ON bill1.BillID = sell1.BillID "
						+ "JOIN products AS p ON sell1.ProdID = p.ProdID "
						+ "JOIN customer AS c ON c.CusNum = bill1.CusNum "
						+ "WHERE bill1.BillID = '"+BillSearchTxt.getText().toString()
						+"' AND bill1.CurDate >= '"+SearchMainDateDP.getValue()
						+"' AND bill1.CurDate <= '"+SearchEndDateDP.getValue()+"'");
				
				SearchSoldProd(rs2);
			}
			
			else{ return; }
		}
	}
	
	public void SearchAvlProd(ResultSet rs){
		try{
			SearchAvlArrayList = FXCollections.observableArrayList();
			SearchAvlArrayList.removeAll(SearchAvlArrayList);
			if(rs != null){
				while(rs.next()){
					SearchAvlArrayList.add(new SearchAvlList(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
							rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
				}
				SearchAvlCode.setCellValueFactory(new PropertyValueFactory<SearchAvlList,String>("searchAvlCode"));
				SearchAvlName.setCellValueFactory(new PropertyValueFactory<SearchAvlList,String>("searchAvlName"));
				SearchAvlPP.setCellValueFactory(new PropertyValueFactory<SearchAvlList,String>("searchAvlPP"));
				SearchAvlMRP.setCellValueFactory(new PropertyValueFactory<SearchAvlList,String>("searchAvlMRP"));
				SearchAvlColour.setCellValueFactory(new PropertyValueFactory<SearchAvlList,String>("searchAvlColour"));
				SearchAvlQty.setCellValueFactory(new PropertyValueFactory<SearchAvlList,String>("searchAvlQty"));
				SearchAvlSize.setCellValueFactory(new PropertyValueFactory<SearchAvlList,String>("searchAvlSize"));
				SearchAvlCatg.setCellValueFactory(new PropertyValueFactory<SearchAvlList,String>("searchAvlCatg"));
				
				if(SearchStockProdTbl != null)
					SearchStockProdTbl.setItems(SearchAvlArrayList);
			}
			rs.close();
		}
		catch(Exception e){e.printStackTrace();}
	}
	
	public void SearchSoldProd(ResultSet rs){
		try{
			SearchSoldArrayList = FXCollections.observableArrayList();
			SearchSoldArrayList.removeAll(SearchSoldArrayList);
			if(rs != null){
				while(rs.next()){
				SearchSoldArrayList.add(new SearchSoldList(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),
						rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9)));
				}
			    SearchSoldClmDate.setCellValueFactory(new PropertyValueFactory<SearchAvlList,String>("searchSoldDate"));
			    SearchSoldClmProdCode.setCellValueFactory(new PropertyValueFactory<SearchAvlList,String>("searchSoldCode"));
			    SearchSoldClmProdName.setCellValueFactory(new PropertyValueFactory<SearchAvlList,String>("searchSoldName"));
			    SearchSoldClmSP.setCellValueFactory(new PropertyValueFactory<SearchAvlList,String>("searchSoldSP"));
			    SearchSoldClmMRP.setCellValueFactory(new PropertyValueFactory<SearchAvlList,String>("searchSoldMRP"));
			    SearchSoldClmDisc.setCellValueFactory(new PropertyValueFactory<SearchAvlList,String>("searchSoldDisc"));
			    SearchSoldClmColour.setCellValueFactory(new PropertyValueFactory<SearchAvlList,String>("searchSoldColour"));
			    SearchSoldClmQty.setCellValueFactory(new PropertyValueFactory<SearchAvlList,String>("searchSoldQty"));
			    SearchSoldClmCustName.setCellValueFactory(new PropertyValueFactory<SearchAvlList,String>("searchSoldCustName"));
			    
			    if(SearchSoldProdTbl != null)
			    	SearchSoldProdTbl.setItems(SearchSoldArrayList);
			}
			rs.close();
		}
		catch(Exception e){e.printStackTrace();}
	}
	
	public void CustSearchRs(ResultSet rs){
		try{
			CustSearchArrayList = FXCollections.observableArrayList();
		    CustSearchArrayList.removeAll(CustSearchArrayList);
		    if(rs != null){
		    	while(rs.next()){
		    		CustSearchArrayList.add(new CustSearchList(rs.getString(1),rs.getString(2),rs.getString(3)));
		    	}
		    	CustNameClm.setCellValueFactory(new PropertyValueFactory<CustSearchList,String>("custSearchName"));
		    	CustNumbClm.setCellValueFactory(new PropertyValueFactory<CustSearchList,String>("custSearchNumb"));
		    	CustBDayClm.setCellValueFactory(new PropertyValueFactory<CustSearchList,String>("custSearchBDay"));
		    	
		    	if(CustomerTbl != null)
		    		CustomerTbl.setItems(CustSearchArrayList);
		    }
		    rs.close();
		}
		catch(Exception e){e.printStackTrace();}
		
	}
	
	 public void DialogCreator(final Stage stage,String Title,Button button,TextField[] textfields,DatePicker date,long width,long height){
		
		    int i = 0;
		    stage.initModality(Modality.APPLICATION_MODAL);
			Label title = new Label(Title);
		    title.setAlignment(Pos.TOP_CENTER);
		    title.setStyle("-fx-font-size:16;");
			
	     	VBox vBoxDtls = new VBox();
			vBoxDtls.setSpacing(20.0);
			
			if(textfields != null){
				for(i=0; i < textfields.length; i++)
				{
					textfields[i].setPrefSize(150.0, 30.0);
					textfields[i].setMaxSize(150.0, 30.0);
					textfields[i].setStyle("-fx-font-size:12;");
					
					if(textfields != null)
						vBoxDtls.getChildren().add(textfields[i]);
				}
			}
			if(date != null){
				date = new DatePicker();
			    date.setPrefSize(100.0, 30.0);
			    date.setMaxSize(100.0, 30.0);
		      } 
			if(date != null)
				vBoxDtls.getChildren().add(date);
			
			button.setPrefSize(70, 30);
		    button.setStyle("-fx-font-size:14;");
			
			Button cancel = new Button("Cancel");
			cancel.setStyle("-fx-font-size:13;");
			cancel.setPrefSize(60, 30);
			cancel.setOnAction(new EventHandler<ActionEvent>(){
				@Override
				public void handle(ActionEvent event) {stage.close();}
			});
			
			HBox hBox = new HBox();
			hBox.setAlignment(Pos.BOTTOM_CENTER);
			hBox.setSpacing(30.0);
			hBox.getChildren().addAll(button,cancel);
			
			VBox vBox = new VBox();
			vBox.setSpacing(30.0);
			vBox.setPadding(new Insets(20,20,20,20));
			vBox.getChildren().addAll(title,vBoxDtls,hBox);
			
			stage.setScene(new Scene(vBox,width,height));
			stage.show();
	}
	 
	 public boolean Print(Node node){
		 boolean printed = true;
		 PrinterJob job = PrinterJob.createPrinterJob();
		 if(job != null){
			 printed = job.printPage(node);
			 if(printed){
				 job.endJob();
			 }
		 }
		 return printed;
	 }
}
