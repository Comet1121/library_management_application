package library_management_application.Item;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainUI extends Application{
	private BorderPane item = new ItemUI().getRoot();
	private BorderPane report = new ItemReport().getRoot();
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage st) throws Exception {
		Scene sc = new Scene(getRoot());
		st.setScene(sc);
		st.setTitle("Item Report");
		st.show();
	}
	public BorderPane getRoot() {
		Button btnItem = new Button("Item");
		Button btnReport = new Button("Report");
		
		BorderPane root = new BorderPane();
		root.setTop(new HBox(btnItem, btnReport));
		root.setCenter(item);
		
		btnItem.setOnAction(e->{
			root.setCenter(item);
			
		});
		btnReport.setOnAction(e->{
			root.setCenter(report);
		});
		return root;
	}
}
