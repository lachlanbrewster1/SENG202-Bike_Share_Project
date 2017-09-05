package seng202.team7;

import com.thoughtworks.xstream.mapper.Mapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Trip data controller to control raw data viewing of trip data
 * @author Aidan Smith asm142
 * Last updated 29/08/17
 */

public class TripDataViewerController implements Initializable {

    @FXML
    private TableView<Trip> tripDataTable;

    @FXML private TableColumn<Trip, String> startColumn;
    @FXML private TableColumn<Trip, String> endColumn;
    @FXML private TableColumn<Trip, String> durationColumn;
    @FXML private TableColumn<Trip, String> genderColumn;
    @FXML private TableColumn<Trip, String> userTypeColumn;
    @FXML private ComboBox<String> startStationCB;
    @FXML private ComboBox<String> endStationCB;
    @FXML private ComboBox<String> genderCB;
    @FXML private ComboBox<String> userTypeCB;

    private Station s1 = new Station(231,"5th ave", "CitiBike", 2387.987, 384.98);
    private Station s2 = new Station(3241,"34 square", "Bike Shah", 2387.987, 384.98);

    private ObservableList<Trip> tripList
            = FXCollections.observableArrayList(
            new Trip(s1,s2,4345,"2015-10-01 00:22:42","2015-10-01 00:38:42", "Customer", 1990, "M", "test"),
            new Trip(s2,s1,4345,"2015-10-01 00:20:42","2015-10-01 00:29:42", "Subscriber", 1934, "F", "test")
    );

    private ObservableList<Trip> filteredTripList = FXCollections.observableArrayList(tripList);
    /**
     * Initialises the data within the table to the data provide by xxx
     * @param url Not sure what this is
     * @param rb Not sure what this is either
     */
    public void initialize(URL url, ResourceBundle rb) {
        startStationCB.getItems().addAll("5th ave","34 square","None");
        endStationCB.getItems().addAll("5th ave","34 square","None");
        startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
        endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        userTypeColumn.setCellValueFactory(new PropertyValueFactory<>("userType"));
        tripDataTable.setItems(filteredTripList); //need a method to get Arraylist of retailer objects
    }

    /**
     * Called whenever a filter combobox is changed to filter all the loaded data again
     */
    public void filter() {
        filteredTripList.clear();
        String startSelection = startStationCB.getValue();
        String endSelection = endStationCB.getValue();
        String genderSelection = genderCB.getValue();
        String userTypeSelection = userTypeCB.getValue();
        for (Trip trip : tripList) {
            if ((trip.getStart().equals(startSelection) || startSelection == null || startSelection.equals("None"))
                    && (trip.getEnd().equals(endSelection) || endSelection == null || endSelection.equals("None"))
                    && (trip.getGender().equals(genderSelection) || genderSelection == null || genderSelection.equals("None"))
                    && (trip.getUserType().equals(userTypeSelection) || userTypeSelection == null || userTypeSelection.equals("None"))
                    ) {
                filteredTripList.add(trip);
            }
        }
    }
}
