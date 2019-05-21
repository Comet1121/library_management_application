package library_management_application.Item;



import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ItemUI extends Application {
	private TextField txfName = new TextField();
	private TextField txfUnitPrice = new TextField();
	private TextField txfQuantity = new TextField();
	private TableColumn<Item, Integer> colID = new TableColumn<>("Item ID");
	private TableColumn<Item, String> colName = new TableColumn<>("Item Name");
	private TableColumn<Item, Integer> colUnitPrice = new TableColumn<>("Unit Price");
	private TableColumn<Item, Integer> colQuantity = new TableColumn<>("Quantity");
	private TableView<Item> tblItem = new  TableView<>();
	private Item update;
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage st) throws Exception {
		Scene sc = new Scene(getRoot());
		st.setScene(sc);
		st.setTitle("Item Form");
		st.show();
	}
	public BorderPane getRoot() {
		Button btnSave = new Button("Save");
		btnSave.setOnAction(e->save());
		Button btnUpdate = new Button("Update");
		btnUpdate.setOnAction(e->update());

		HBox input = new HBox(
				new Label("Item Name"), txfName, 
				new Label("Unit Price"), txfUnitPrice,
				new Label("Quantity"), txfQuantity,
				btnSave, btnUpdate);

		colID.setCellValueFactory(new PropertyValueFactory<>("id"));
		colName.setCellValueFactory(new PropertyValueFactory<>("name"));
		colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

		tblItem.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tblItem.getColumns().addAll( colID, colName, colUnitPrice, colQuantity);

		tblItem.setOnMouseClicked(e->{
			if (e.getButton().equals(MouseButton.PRIMARY) && e.getClickCount() > 1) {
				selectUpdate();
			}
		});
		
		BorderPane root = new BorderPane();
		root.setTop(input);
		root.setCenter(tblItem);
		loadItem();
		return root;
	}
	private void selectUpdate() {
		update = tblItem.getSelectionModel().getSelectedItem();
		if (update != null) {
			txfName.setText(update.getName());
			txfQuantity.setText(update.getQuantity()+"");
			txfUnitPrice.setText(update.getUnitPrice()+"");
			txfName.selectAll();
			txfName.requestFocus();
		}
	}
	private void update() {		
		Item i = isValidItem();
		System.out.println("i:"+i);
		System.out.println("Update: " + update);
		if (update != null && i != null ) {
			System.out.println("update");
			update.setName(i.getName());
			update.setUnitPrice(i.getUnitPrice());
			update.setQuantity(i.getQuantity());
			if (update.update()) {
				new Alert(AlertType.INFORMATION, "Iteam updated!", ButtonType.OK)
				.showAndWait();
			}else {
				new Alert(AlertType.WARNING, "Iteam cannot be updated!", ButtonType.OK)
				.showAndWait();
			}
			update = null;
			tblItem.refresh();
		}
	}
	private void save() {
		Item newItem = isValidItem();
		if (newItem != null) {
			if (newItem.insert()) {
				tblItem.getItems().add(newItem);
				new Alert(AlertType.INFORMATION, "Iteam saved!", ButtonType.OK)
				.showAndWait();
			}else {
				new Alert(AlertType.WARNING, "Iteam cannot be saved!", ButtonType.OK)
				.showAndWait();
			}
		}
	}
	private Item isValidItem() {
		Item i = null;
		String name = txfName.getText();
		String price = txfUnitPrice.getText();
		String quantity = txfQuantity.getText();
		String pattern = "\\d+\\.?\\d*";
		
		if (name.length()<1) {
			new Alert(AlertType.WARNING, "Name should not be empty..", ButtonType.OK)
			.showAndWait();
			txfName.requestFocus();
			txfName.selectAll();
		}else if (!price.matches(pattern)) {
			new Alert(AlertType.WARNING, "Price should be a numeric value..", ButtonType.OK)
			.showAndWait();
			txfUnitPrice.requestFocus();
			txfUnitPrice.selectAll();
		}else if (!quantity.matches(pattern)) {
			new Alert(AlertType.WARNING, "Quantity should be a numeric value..", ButtonType.OK)
			.showAndWait();
			txfQuantity.requestFocus();
			txfQuantity.selectAll();
		}else {
			i = new Item(0, name, Double.parseDouble(price), Double.parseDouble(quantity));
		}
		return i;
	}
	private void loadItem() {
		tblItem.getItems().addAll(Item.getAllItem());
	}
}
