import data.Success;
import data.Times;
import data.Vehicles;
import data.stops.Stops;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import static javafx.scene.input.KeyCode.T;

public class Controller implements Initializable {
    @FXML
    Label infoLabel;
    @FXML
    TableView table;
    @FXML
    javafx.scene.control.TableColumn LineNumberColumn;
    @FXML
    javafx.scene.control.TableColumn TimeColumn;
    @FXML
    javafx.scene.control.TableColumn DestinationColumn;
    @FXML
    javafx.scene.control.TableColumn StatusColumn;
    @FXML
    TextField SearchBar;

    List<String> searchItems;


    @FXML

    public void initialize(URL location, ResourceBundle resources) {
        infoLabel.setWrapText(true);
        try {
            infoLabel.setText(Receiver.readMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        LineNumberColumn.setCellValueFactory(new PropertyValueFactory<Times, String>("line"));
        DestinationColumn.setCellValueFactory(new PropertyValueFactory<Times, String>("direction"));
        TimeColumn.setCellValueFactory(new PropertyValueFactory<Times, String>("minutes"));
        StatusColumn.setCellValueFactory(new PropertyValueFactory<Times, String>("status"));
        ObservableList<Times> obList = fillTable();
        table.setItems(obList);


        SearchBar.textProperty().addListener((obs, oldText, newText) -> {
            System.out.println("Text changed from " + oldText + " to " + newText);
            try {
               // Receiver.getStopPoints(newText)
                fillSearchBar(newText);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    public List<String> fillSearchBar(String pattern) throws IOException {
        List<String> names = new ArrayList<>();
        Stops stops = Receiver.getStopPoints(pattern);
        data.stops.Success[] success = stops.getSuccess();
        for (data.stops.Success scs : success) {
            names.add(scs.getName());
        }
        String[] items = new String[names.size()];
        items = names.toArray(items);
        TextFields.bindAutoCompletion(SearchBar, names);
        return names;
    }

    public ObservableList<Times> fillTable() {
        Vehicles vehObj = null;
        List<Times> listOfVehicles = new ArrayList<Times>();
        try {
            vehObj = Receiver.sendRequest();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Success success = vehObj.getSuccess();
        Times[] times = success.getTimes();
        for (Times time : times) {
            String minutes = time.getMinutes();
            if (checkIfOnStop(time).equals("IsOnStop")) {
                minutes = time.getMinutes();
                time.setMinutes("Właśnie teraz!");
            } else {
                minutes = time.getMinutes();
                if (minutes.equals("0")) {
                    time.setMinutes("<1 min.");
                } else {
                    time.setMinutes(minutes + " min.");
                }
            }
            //System.out.println(dest.getDirection());
            listOfVehicles.add(time);
        }
        ObservableList<Times> column = FXCollections.observableArrayList(listOfVehicles);
        return column;

    }

    private ChangeListener<Boolean> focusListener = new ChangeListener<Boolean>() {
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if (newValue) {
                System.out.println("Jestem");
            }
        }
    };


    public String checkIfOnStop(Times time) {
        if (time.getOnStopPoint().equals("true")) {
            return "IsOnStop";
        } else return "IsNotOnStop";
    }

}
