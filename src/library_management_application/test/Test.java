package library_management_application.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {
    public static void main(String[] args) {
        //launch(args);
        String last_id = "BE_9999";
        String id = "";

        int num_id = Integer.parseInt(last_id.substring(3));
        System.out.println(num_id);
        String s_id = "";
        //id = (""+id_type.charAt(0)).toUpperCase();
        if(num_id >= 9999){
            int char_value =  (int)last_id.charAt(1)+1;
            char c = (char) char_value;
            s_id =(""+ last_id.charAt(0))+c+"_0001";
        }
        else if(num_id >= 100){
            s_id = last_id.substring(0,3)+"0"+(num_id+1);
        }
        else if(num_id >= 10){
            s_id = last_id.substring(0,3)+"00"+(num_id+1);
        }
        System.out.println(s_id);
//    }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/library_management_application/test/testUi.fxml"));

        Scene sc = new Scene(root);
        primaryStage.setScene(sc);
        primaryStage.show();
    }
}
