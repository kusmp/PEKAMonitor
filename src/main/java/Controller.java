import data.Success;
import data.Times;
import data.Vehicles;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML Label infoLabel;
    @FXML TableView table;
    @FXML javafx.scene.control.TableColumn LineNumberColumn;
    @FXML javafx.scene.control.TableColumn TimeColumn;
    @FXML javafx.scene.control.TableColumn DestinationColumn;

    @FXML

     public void initialize(URL location, ResourceBundle resources){
        infoLabel.setWrapText(true);
        try {
            infoLabel.setText(Receiver.readMessage());
        } catch(IOException e){e.printStackTrace();}
       LineNumberColumn.setCellValueFactory(new PropertyValueFactory<Times, String>("line"));
       DestinationColumn.setCellValueFactory(new PropertyValueFactory<Times, String>("direction"));
       TimeColumn.setCellValueFactory(new PropertyValueFactory<Times, String>("minutes"));
       ObservableList<Times> obList = fillTable();
       table.setItems(obList);
    }

    public ObservableList<Times> fillTable(){
        Vehicles vehObj = null;
        List<Times> listOfVehicles = new ArrayList<Times>();
        try{
        vehObj = Receiver.sendRequest();
        } catch(IOException e){ e.printStackTrace(); }
        Success success = vehObj.getSuccess();
        Times[] times = success.getTimes();
        for(Times time : times){
            String minutes = time.getMinutes();
            if(checkIfOnStop(time).equals("IsOnStop")){
                minutes = time.getMinutes();
                time.setMinutes("Właśnie teraz!");
            }
            else{
            minutes = time.getMinutes();
            if(minutes.equals("0")){
                time.setMinutes("<1 min.");
            }
            else{
            time.setMinutes(minutes + " min.");
            }
            }
            //System.out.println(dest.getDirection());
            listOfVehicles.add(time);
        }
        ObservableList<Times> column = FXCollections.observableArrayList(listOfVehicles);
        return column;

    }

    public String checkIfOnStop(Times time){
        if(time.getOnStopPoint().equals("true")){
         return "IsOnStop";
        } else return "IsNotOnStop";
    }

}
