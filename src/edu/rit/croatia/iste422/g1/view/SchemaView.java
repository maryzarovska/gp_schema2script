package edu.rit.croatia.iste422.g1.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import edu.rit.croatia.iste422.g1.model.Table;
import edu.rit.croatia.iste422.g1.model.TableRow;

import edu.rit.croatia.iste422.g1.view.SchemaView;

/**
 * SchemaView is responsible for initializing and displaying the UI of the
 * Schema-to-Script
 * application. It utilizes JavaFX and FXML for rendering the user interface.
 * 
 * @author Mariia Zarovska, Orest Brukhal, Mattia Vela
 * @version 2.8
 */
public class SchemaView extends Application {

    // Attributes

    private StackPane stackPane;
    private Map<String, Node> loadedFXML = new HashMap<>();

    @FXML
    public MenuItem openFileMenu;

    @FXML
    public MenuItem saveFileMenu;

    @FXML
    public MenuItem generateMySQLCodeButton;

    @FXML
    public MenuItem generateOracleCodeButton;

    @FXML
    public MenuItem generatePostgreSQLCodeButton;

    @FXML
    private Button editTable1;

    @FXML
    private Button deleteTable1;

    @FXML
    private Button editTable2;

    @FXML
    private Button deleteTable2;

    @FXML
    private Button editTable3;

    @FXML
    private Button deleteTable3;

    @FXML
    private Button editTable4;

    @FXML
    private Button deleteTable4;

    @FXML
    private Button setFKButton;

    @FXML
    private TextField header1;

    @FXML
    private TextField header2;

    @FXML
    private TextField header3;

    @FXML
    private TextField header4;

    @FXML
    private TextField relations1TF;

    @FXML
    private TextField relations2TF;

    @FXML
    private TextField relations3TF;

    @FXML
    private TextField relations4TF;

    private ArrayList<TextField> headers;
    private ArrayList<TextField> relations;

    @FXML
    private TableView<TableRow> student;

    @FXML
    private TableView<TableRow> instructor;

    @FXML
    private TableView<TableRow> course;

    @FXML
    private TableView<TableRow> enrollment;

    private List<TableView<TableRow>> tableViews;

    @FXML
    private TableColumn<TableRow, String> tableView1name;

    @FXML
    private TableColumn<TableRow, String> tableView1type;

    @FXML
    private TableColumn<TableRow, String> tableView2name;

    @FXML
    private TableColumn<TableRow, String> tableView2type;

    @FXML
    private TableColumn<TableRow, String> tableView3name;

    @FXML
    private TableColumn<TableRow, String> tableView3type;

    @FXML
    private TableColumn<TableRow, String> tableView4name;

    @FXML
    private TableColumn<TableRow, String> tableView4type;

    private ArrayList<TableColumn<TableRow, String>> tableColumns;

    @FXML
    private TextArea generatedCodeTA;

    @FXML
    private TableView<TableRow> table1;

    @FXML
    private TableView<TableRow> table2;

    @FXML
    private TableView<TableRow> table3;

    @FXML
    private TableView<TableRow> table4;

    @FXML
    private TableColumn<TableRow, String> name1;

    @FXML
    private TableColumn<TableRow, String> type1;

    @FXML
    private TableColumn<TableRow, String> name2;

    @FXML
    private TableColumn<TableRow, String> type2;

    @FXML
    private TableColumn<TableRow, String> name3;

    @FXML
    private TableColumn<TableRow, String> type3;

    @FXML
    private TableColumn<TableRow, String> name4;

    @FXML
    private TableColumn<TableRow, String> type4;

    @FXML
    private VBox tableContainer;

    private static final Logger logger = LogManager.getLogger(SchemaView.class);

    @Override
    public void start(Stage primaryStage) throws Exception {
        /**
         * Initializes and displays the main application window.
         * 
         * @param primaryStage the primary stage for this application
         * @throws Exception if an error occurs during UI initialization
         */
        Stage stage = primaryStage;
        stackPane = new StackPane();

        loadFXML("");
        Scene scene = new Scene(stackPane, 1446, 775);
        stage.setScene(scene);
        stage.show();

        initView();
    }

    /**
     * Initializes UI components for SchemaView and sets default instructions.
     */
    private void initView() {
        logger.info("Initializing components for SchemaView...");

        headers = new ArrayList<>();
        headers.add(header1);
        headers.add(header2);
        headers.add(header3);
        headers.add(header4);

        relations = new ArrayList<>();
        relations.add(relations1TF);
        relations.add(relations2TF);
        relations.add(relations3TF);
        relations.add(relations4TF);

        tableViews = new ArrayList<>();
        tableViews.add(student);
        tableViews.add(instructor);
        tableViews.add(course);
        tableViews.add(enrollment);

        tableColumns = new ArrayList<>();
        tableColumns.add(tableView1name);
        tableColumns.add(tableView1type);

        tableColumns.add(tableView2name);
        tableColumns.add(tableView2type);

        tableColumns.add(tableView3name);
        tableColumns.add(tableView3type);

        tableColumns.add(tableView4name);
        tableColumns.add(tableView4type);

        tableView1name.setEditable(true);

        generatedCodeTA.setText("1. Open configuration file by clicking \"File\" -> \"Open\".\n" +
                "2. Press \"Generate\" button to generate MySQL script.");

        logger.info("Initialized components for SchemaView successfully");

    }

    /**
     * Loads a specific .fxml file and displays its UI.
     * 
     * @param fileName the name of the FXML file to load
     */
    private void loadFXML(String fileName) {

        logger.info("Loading FXML");
        try {
            URL resource = getClass().getResource("/edu/rit/croatia/iste422/g1/view/project_ui.fxml");

            if (resource != null) {
                FXMLLoader loader = new FXMLLoader(resource);

                loader.setController(this);

                Parent root = loader.load();

                loadedFXML.put(fileName, root);
                stackPane.getChildren().add(root);
                if (logger.isInfoEnabled()) {
                    logger.info(stackPane.getChildren().toString());
                }
                root.setVisible(true);
            }

        } catch (Exception e) {
            /**
             * Displays an error alert if the FXML file fails to load.
             */
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Error loading FXML file " + fileName);
            alert.showAndWait();
        }
        logger.info("Loaded FXML successfully");
    }

    /**
     * Displays a success alert with a custom message.
     * 
     * @param message the success message to display
     */
    public void showSuccess(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Displays an error alert with a custom message.
     * 
     * @param message the error message to display
     */
    public void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Retrieves the "Open File" menu item.
     * 
     * @return the MenuItem for opening files
     */
    public MenuItem getOpenFileButton() {
        return openFileMenu;
    }

    /**
     * Retrieves the "Save File" menu item.
     * 
     * @return the MenuItem for saving files
     */
    public MenuItem getSaveFileButton() {
        return saveFileMenu;
    }

    /**
     * Retrieves the button for generating code.
     * 
     * @return the Button for generating code
     */
    public MenuItem getGenerateButton() {
        return generateMySQLCodeButton;
    }

    /**
     * Retrieves the button for generating Oracle-specific code.
     * 
     * @return the Button for generating Oracle-specific code
     */
    public MenuItem getOracleGenerateButton() {
        return generateOracleCodeButton;
    }

    /**
     * Retrieves the button for generating Oracle-specific code.
     * 
     * @return the Button for generating Oracle-specific code
     */
    public MenuItem getPostgreSQLGenerateButton() {
        return generatePostgreSQLCodeButton;
    }

    /**
     * Retrieves the button for setting foreign keys.
     * 
     * @return the Button for setting foreign keys
     */
    public Button getSetFKButton() {
        return setFKButton;
    }

    /**
     * Retrieves an array of buttons for deleting tables.
     * 
     * @return an array of Buttons for deleting tables
     */
    public Button[] getDeleteButtons() {
        return new Button[] { deleteTable1, deleteTable2, deleteTable3, deleteTable4 };
    }

    /**
     * Retrieves an array of buttons for editing tables.
     * 
     * @return an array of Buttons for editing tables
     */
    public Button[] getEditButtons() {
        return new Button[] { editTable1, editTable2, editTable3, editTable4 };
    }

    /**
     * Retrieves a list of TableView objects.
     * 
     * @return a List of TableView objects
     */
    public List<TableView<TableRow>> getTableViews() {
        return tableViews;
    }

    /**
     * Updates the output view by setting the generated script in the text area.
     * 
     * @param script the generated script to display
     */
    public void setOutputView(String script) {
        logger.info("Setting the Output view...");
        generatedCodeTA.setText(script);
        logger.info("Successfully set the Output view...");
    }

    /**
     * Populates the input view with table data and relations.
     * 
     * @param tables the list of Table objects containing schema data
     */
    public void setInputView(List<Table> tables) {
        logger.info("Getting the Input view...");

        for (int i = 0; i < 4; i++) {
            tableViews.get(i).getItems().clear();
            relations.get(i).clear();
            headers.get(i).clear();
        }
        for (int tableCount = 0; tableCount < tables.size(); tableCount++) {
            // Fill the headers (table names)

            headers.get(tableCount).setText(tables.get(tableCount).getName());
            logger.debug("Filled header name {}", headers.get(tableCount).getText());

            // Fill the attributes (columns)
            if (tables.get(tableCount).getAttributes()[0][0] != null) {
                logger.info(tables.get(tableCount).getAttributes()[0][0]);
                tableViews.get(tableCount).setEditable(false);

                int columnStartIndex = tableCount * 2;
                tableColumns.get(columnStartIndex)
                        .setCellValueFactory(new PropertyValueFactory<>("name"));
                tableColumns.get(columnStartIndex).setCellFactory(TextFieldTableCell.forTableColumn());
                tableColumns.get(columnStartIndex + 1)
                        .setCellValueFactory(new PropertyValueFactory<>("type"));
                tableColumns.get(columnStartIndex + 1).setCellFactory(TextFieldTableCell.forTableColumn());

                ObservableList<TableRow> list = FXCollections.observableArrayList();
                for (String[] row : tables.get(tableCount).getAttributes()) {
                    list.add(new TableRow(row[0], row[1]));
                    logger.debug("Filled row for table \"{}\": {}, {}", headers.get(tableCount).getText(), row[0],
                            row[1]);
                }

                tableViews.get(tableCount).setItems(list);
            }

            // Fill the relations (foreign keys and related foreign keys)
            relations.get(tableCount).clear();
            StringBuilder relationText = new StringBuilder();
            List<String> foreignKeys = tables.get(tableCount).getForeignKeys();
            List<String> relatedForeignKeys = tables.get(tableCount).getRelatedForeignKeys();
            List<String> relatedTables = tables.get(tableCount).getRelatedTables();

            List<String> formattedRelations = new ArrayList<>();

            // Iterate through foreign keys, related foreign keys, and related tables in
            // pairs
            int size = Math.min(foreignKeys.size(), Math.min(relatedForeignKeys.size(), relatedTables.size()));
            for (int i = 0; i < size; i++) {
                String filled = "Filled FK relation: ";
                String fk = foreignKeys.get(i);
                String relatedFk = relatedForeignKeys.get(i);
                String relatedTable = relatedTables.get(i);

                if (fk != null && relatedFk != null) {
                    formattedRelations.add(fk + " relates to " + relatedFk + " (" + relatedTable + "); ");
                    logger.debug("{}{} relates to {} ({})", filled, fk, relatedFk, relatedTable);
                } else if (fk != null) {
                    formattedRelations.add(fk + " has no matching related foreign key; ");
                    logger.debug("{}{} has no matching related foreign key", filled, fk);
                } else if (relatedFk != null) {
                    formattedRelations.add(relatedFk + " has no matching foreign key; ");
                    logger.debug("{}{} has no matching foreign key", filled, relatedFk);
                }
            }

            relationText.append(String.join("\n", formattedRelations));
            relations.get(tableCount).setText(relationText.toString().trim());
        }

        logger.info("Got the Input View successfully");
    }

    public void clearFK() {
        relations2TF.setText("");
        header2.setText("");
    }
}