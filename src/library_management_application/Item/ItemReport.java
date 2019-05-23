package library_management_application.Item;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ItemReport extends Application{
	private ComboBox<String> cboReport = new ComboBox<>();
	private TextField txfInput = new TextField();
	private TableColumn<Item, Integer> colID = new TableColumn<>("Item ID");
	private TableColumn<Item, String> colName = new TableColumn<>("Item Name");
	private TableColumn<Item, Integer> colUnitPrice = new TableColumn<>("Unit Price");
	private TableColumn<Item, Integer> colQuantity = new TableColumn<>("Quantity");
	private TableView<Item> tblItem = new  TableView<>();
	@Override
	public void start(Stage st) throws Exception {
		Scene sc = new Scene(getRoot());
		st.setScene(sc);
		st.setTitle("Item Report");
		st.show();
	}
	public BorderPane getRoot() {
		cboReport.getItems().addAll("All", "Low Quantity", "Name");
		txfInput.setPromptText("Enter key word");
		Button btnReport = new Button("Report");

		btnReport.setOnAction(e->report());

		HBox input = new HBox(
				cboReport,
				txfInput,
				btnReport);

		colID.setCellValueFactory(new PropertyValueFactory<>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

		tblItem.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tblItem.getColumns().addAll( colID, colName, colUnitPrice, colQuantity);

		BorderPane root = new BorderPane();
		root.setTop(input);
		root.setCenter(tblItem);
		return root;
	}
	public void report() {
		String sql = "Select * from item ";
		String key = txfInput.getText();
		int choice = cboReport.getSelectionModel().getSelectedIndex();
		switch (choice) {
		case 1:
			sql += " where quantity < 10";
			break;
		case 2:
			sql += " where name like '%"+key+"%'";
			break;

		default:
			break;
		}
		tblItem.getItems().clear();
		tblItem.getItems().addAll(Item.getDataByQuery(sql));
	}
	public static void main(String[] args) {
		launch(args);
	}
}
