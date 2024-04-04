package mandatories.unused;

import java.awt.MenuItem;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import YourClassType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ObservableDoubleValue;
import javafx.beans.value.WritableDoubleValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.ConstraintsBase;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;
import mandatories.project.PDU;
import mandatories.project.Project;
import mandatories.project.ProjectGet;
import java.lang.Iterable;
import mandatories.unused.DataCollector;

public class FmxlController {

    @FXML
    private ComboBox<String> combobox_speed;

    @FXML
    private Label lbl_grpah_units;

    @FXML
    private CategoryAxis graph_x_time_axis;

    @FXML
    private ScrollPane scroll_pane;

    @FXML
    private TableColumn<gui_model_record, String> col_ip;
    @FXML
    private TableColumn<gui_model_record, String> col_dscr;
    @FXML
    private TableColumn<gui_model_record, Double> col_inbw;
    @FXML
    private TableColumn<gui_model_record, Double> col_outbw;
    @FXML
    private TableColumn<gui_model_record, Double> col_data_in;
    @FXML
    private TableColumn<gui_model_record, Double> col_data_out;
    @FXML
    private TableColumn<gui_model_record, Double> col_max_speed;
    @FXML
    private TableColumn<gui_model_record, Double> col_datarate_in;
    @FXML
    private TableColumn<gui_model_record, Double> col_datarate_out;
    @FXML
    private TableColumn<gui_model_record, Double> col_totalbw;
    @FXML
    private TableColumn<gui_model_record, Double> col_totaldata;
    @FXML
    private TableColumn<gui_model_record, Double> col_totalrate;

    @FXML
    private VBox topl_level_vbox;

    @FXML
    private TableView<gui_model_record> record_table;

    @FXML
    private SplitPane splitpane_monitor;

    @FXML
    private LineChart<?, ?> graph;

    @FXML
    private NumberAxis graph_y_number_axis;

    @FXML
    private MenuBar menubar;

    @FXML
    private AnchorPane monitor_right_pane;

    @FXML
    private HBox hbox_middle;

    @FXML
    private AnchorPane monitor_left_pane;

    @FXML
    private Tab monitor_tab;

    @FXML
    private TabPane tab_pane;

    @FXML
    private DatePicker end_date;

    @FXML
    private DatePicker start_date;

    @FXML
    private Button btn_get_records;

    @FXML
    private Button btn_device_add;

    @FXML
    private Button btn_start_recording;

    @FXML
    private TextField start_time_hrs;

    @FXML
    private TextField end_time_hrs;

    @FXML
    private TextField end_time_mins;

    @FXML
    private TextField start_time_mins;

    @FXML
    private TextField txt_device_port;

    @FXML
    private TableView<gui_model_device> table_devices;

    @FXML
    private TableColumn<gui_model_device, String> col_device_ip;

    @FXML
    private TextField txt_device_ip;

    @FXML
    private TextField txt_record_ip;

    @FXML
    private TextField txt_record_port;

    @FXML
    private TableColumn<gui_model_device, Integer> col_device_port;

    @FXML
    private TableColumn<gui_model_device, String> col_device_community;

    @FXML
    private TableColumn<gui_model_device, Long> col_device_speed;

    @FXML
    private Label lbl_outbw;

    @FXML
    private Label lbl_inbw;

    @FXML
    private ComboBox<String> combobox_ports;

    @FXML
    private ComboBox<String> combobox_interfaces;

    @FXML
    private ComboBox<String> combobox_ips;

    @FXML
    private Button btn_refresh_ips;

    @FXML
    private Button btn_refresh_interfaces;

    @FXML
    private Button btn_edit_dscr;

    @FXML
    private Button btn_interface_list;
    @FXML
    private Button btn_generate_pdf;

    @FXML
    private Button btn_port_table;

    @FXML
    private Button btn_remove_devices;

    @FXML
    private Button btn_generate_last_day;

    @FXML
    private Button btn_generate_last_week;

    @FXML
    private Button btn_generate_last_month;

    @FXML
    private Button btn_minimise;

    @FXML
    private TextField txt_device_community;

    @FXML
    private TextField txt_device_usr_dscr;

    @FXML
    private TableColumn<gui_model_device, String> col_device_description;

    @FXML
    private TableColumn<gui_model_device, String> col_usr_dscr;

    @FXML
    private TableColumn<gui_model_device, String> col_device_status;

    @FXML
    private Label lbl_current_device;

    @FXML
    private Label lbl_inspeed;

    @FXML
    private Label lbl_outspeed;

    @FXML
    private TilePane tilepane;

    @FXML
    private Tab tab_monitor;
    @FXML
    private Tab tab_reports;
    @FXML
    private Tab tab_devices;
    @FXML
    private Tab tab_about;

    @FXML
    private TextField txt_snmpget_ip;

    @FXML
    private TextField txt_snmpget_oid;

    @FXML
    private TextField txt_snmpget_community;

    @FXML
    private TextArea txt_snmpget_output;

    @FXML
    private Button btn_snmpget;

    private GridPane grid_devices = new GridPane();

    private DatabaseType database;;

    @FXML
    void initialize() {
        assert graph_x_time_axis != null
                : "fx:id=\"graph_x_time_axis\" was not injected: check your FXML file 'gui_fxml.fxml'.";
        assert graph_y_number_axis != null
                : "fx:id=\"graph_y_number_axis\" was not injected: check your FXML file 'gui_fxml.fxml'.";
        assert menubar != null : "fx:id=\"menubar\" was not injected: check your FXML file 'gui_fxml.fxml'.";
        assert monitor_right_pane != null
                : "fx:id=\"monitor_right_pane\" was not injected: check your FXML file 'gui_fxml.fxml'.";
        assert hbox_middle != null : "fx:id=\"hbox_middle\" was not injected: check your FXML file 'gui_fxml.fxml'.";
        assert monitor_left_pane != null
                : "fx:id=\"monitor_left_pane\" was not injected: check your FXML file 'gui_fxml.fxml'.";
        assert topl_level_vbox != null
                : "fx:id=\"topl_level_vbox\" was not injected: check your FXML file 'gui_fxml.fxml'.";
        assert monitor_tab != null : "fx:id=\"monitor_tab\" was not injected: check your FXML file 'gui_fxml.fxml'.";
        assert splitpane_monitor != null
                : "fx:id=\"splitpane_monitor\" was not injected: check your FXML file 'gui_fxml.fxml'.";
        assert tab_pane != null : "fx:id=\"tab_pane\" was not injected: check your FXML file 'gui_fxml.fxml'.";
        assert graph != null : "fx:id=\"graph\" was not injected: check your FXML file 'gui_fxml.fxml'.";

        creategraph();

        Database database;
        ((Database) database).check_tables();

        load_devices();

        create_table_records();
        create_table_devices();

        create_btn_record();
        create_btn_devices_add();
        create_btn_refresh_lists_ip();
        create_btn_refresh_lists_port();
        create_btn_generate_last();
        create_btn_export_pdf();
        create_btn_minimise();

        create_btn_snmpget();

        create_interface_list();

        initialise_date_format();

        create_graph_units_menu();
        create_btn_remove_device();
        create_btn_edit_description();
        make_grid();
        initialise_tab();
        start_device();
        start_recording();
    }

    public void create_btn_minimise() {

        Image minimise_icon_pic = new Image(FxmlController.class.getResourceAsStream("images/minimise_btn.png"));
        ImageView minimise_icon = new ImageView(minimise_icon_pic);
        minimise_icon.setFitHeight(40);
        minimise_icon.setFitWidth(70);
        btn_minimise.setText("");
        btn_minimise.setGraphic(minimise_icon);
        btn_minimise.setTooltip(new Tooltip("Minimise the application to System Tray"));
        btn_minimise.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Stage stg = (Stage) btn_minimise.getScene().getWindow();
                stg.hide();
            }
        });
    }

    public void create_graph_units_menu() {
        combobox_speed.setPromptText("Mbps");
        combobox_speed.getItems().add("Mbps");
        combobox_speed.getItems().add("MBps");
        combobox_speed.getItems().add("Kbps");
        combobox_speed.getItems().add("KBps");
        combobox_speed.getSelectionModel().selectFirst();
        combobox_speed.setOnHidden(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                System.out.println("The selected Item is " + combobox_speed.getValue());
                if (combobox_speed.getValue().equals("Mbps")) {
                    unit_convertor = 1;
                }
                if (combobox_speed.getValue().equals("MBps")) {
                    unit_convertor = 0.125;
                }
                if (combobox_speed.getValue().equals("Kbps")) {
                    unit_convertor = 1000;
                }
                if (combobox_speed.getValue().equals("KBps")) {
                    unit_convertor = 125;
                }
                stop();
                creategraph();
                updategraph();
                play();
            }
        });
    }

    void create_btn_snmpget() {
        btn_snmpget.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    String ip = txt_snmpget_ip.getText();
                    String oid = txt_snmpget_oid.getText();
                    String community = txt_snmpget_community.getText();
                    PDU speed_resp_from_get = ProjectGet.projectGet(ip, community, oid);
                    long value = ProjectGet.getPDUvalue(speed_resp_from_get);
                    txt_snmpget_output.setText("" + value);
                } catch (IOException ex) {
                    Logger.getLogger(FxmlController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void create_menus() {

    }

    private void makeHeaderWrappable(@SuppressWarnings("rawtypes") TableColumn col) {
        Label label = new Label(col.getText());
        label.setStyle("-fx-padding: 8px;");
        label.setWrapText(true);
        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        label.setMaxHeight(80);
        StackPane stack = new StackPane();
        stack.getChildren().add(label);
        stack.prefWidthProperty().bind(col.widthProperty().subtract(5));
        label.prefWidthProperty().bind(stack.prefWidthProperty());
        col.setGraphic(stack);
    }

    @SuppressWarnings("unchecked")
    void start_device() {
        DatabaseType database;
        List<gui_model_device> r = ((DatabaseType) database)
                .get_device_data("select * from " + ((DatabaseType) database).tablename_devces + ";");
        ((List<gui_model_device>) gui_model_device.deviceData).addAll(r);
        for (gui_model_device d : r) {
            ((WritableDoubleValue) d.speed).set(Math.floor((((ObservableDoubleValue) d.speed).get() * 100)) / 100);

        }
        System.out.println("no of devices d found" + ((List<String>) gui_model_device.deviceData).size());
        table_devices.getItems().setAll(r);
        add_images();
        btn_start_recording.setVisible(false);
    }

    void create_btn_export_pdf() {
        btn_generate_pdf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (((String) gui_model_record.recordData).isEmpty()) {
                    create_dialog("First create some data to be exported");
                } else {
                    ReportType report = (ReportType) report;
                    report.get_data(gui_model_record.recordData);

                    Object savedFile;
                    if (savedFile != null) {
                        System.out.println("path = :" + ((File) savedFile).getAbsolutePath());
                    } else {
                        System.out.println("File save cancelled");
                    }

                    if ((start_date_to_be_sent.equals(" 00:00:00")) || end_date_to_be_sent.equals(" 00:01:00")) {
                        DatabaseType database;
                        String dates[] = ((DatabaseType) database).get_max_min_dates();

                        System.out.println(" from database " + dates[0] + "" + dates[1]);

                        report.get_Date(dates[0], dates[1]);

                    } else {
                        System.out.println("from program" + start_date_to_be_sent + "" + end_date_to_be_sent);
                        report.get_Date(start_date_to_be_sent, end_date_to_be_sent);
                    }
                    report.generate_report(savedFile);
                }
            }
        });
    }

    void create_btn_generate_last() {

        btn_generate_last_day.setOnAction((ActionEvent event) -> {
            LocalDate selected_date = start_date.getValue();
            if (selected_date != null && !selected_date.toString().equals("")) {
                LocalDate set_date = selected_date.minusDays(1);
                end_date.setValue(set_date);
            }
        });

        btn_generate_last_week.setOnAction((ActionEvent event) -> {
            LocalDate selected_date = start_date.getValue();
            if (selected_date != null && !selected_date.toString().equals("")) {

                LocalDate set_date = selected_date.minusWeeks(1);
                end_date.setValue(set_date);
            }
        });

        btn_generate_last_month.setOnAction((ActionEvent event) -> {
            LocalDate selected_date = start_date.getValue();
            if (selected_date != null && !selected_date.toString().equals("")) {
                LocalDate set_date = selected_date.minusMonths(1);
                end_date.setValue(set_date);
            }
        });
    }

    void split_divider_handler() {
        splitpane_monitor.getDividerPositions();
    }

    void initialise_tab() {
    }

    void return_value(String s) {
        gui_model_device d = table_devices.getSelectionModel().getSelectedItem();
        d.setUser_description(s);
        DatabaseType database = (DatabaseType) this.database;
        database.remove_device(d);
        database.add_device(d);
        table_devices.getItems().setAll((gui_model_device[]) gui_model_device.deviceData);
        add_images();
    }

    String get_descr() {
        gui_model_device d = table_devices.getSelectionModel().getSelectedItem();
        String s = d.getUser_description();
        return s;
    }

    void create_btn_edit_description() {
        btn_edit_dscr.setOnAction((ActionEvent event) -> {

            System.out.println("");
            if (table_devices.getSelectionModel().getSelectedIndex() != -1) {

                Stage dialogStage = new Stage();
                dialogStage.initModality(Modality.WINDOW_MODAL);
                VBox vb = new VBox();
                vb.setAlignment(Pos.CENTER);
                vb.setPadding(new Insets(30));
                vb.setSpacing(10);
                Text msg = new Text();
                String dscr = get_descr();
                if (dscr.equals("")) {
                    dscr = " NO DESCRIPTION YET ";
                }
                msg.setText("Please enter the new description \n\nTHe old description is : " + dscr);
                TextField tf = new TextField();
                Button b = new Button("Modify");
                b.setOnAction((ActionEvent event1) -> {
                    String dscr1 = tf.getText();
                    return_value(dscr1);
                    dialogStage.close();
                });

                vb.getChildren().addAll(msg, tf, b);
                Scene scene = new Scene(vb);
                dialogStage.setScene(scene);
                dialogStage.show();

            } else {
                create_dialog("Please select a device first");
            }
        });

    }

    public void load_devices() {
        btn_start_recording.setOnAction(new EventHandler<ActionEvent>() {

            @SuppressWarnings("unchecked")
            @Override
            public void handle(ActionEvent event) {
                System.out.println("starting to load sadfsad");
                DatabaseType database;
                ArrayList<gui_model_device> r = (ArrayList<gui_model_device>) ((DatabaseType) database)
                        .get_device_data("select * from " + ((DatabaseType) database).tablename_devces + ";");

                ((ObservableList<gui_model_device>) gui_model_device.deviceData).setAll(r);
                table_devices.getItems().setAll(r);
                add_images();
                btn_start_recording.setVisible(false);
            }
        });
    }

    void create_btn_remove_device() {
        btn_remove_devices.setOnAction((ActionEvent e) -> {

            if (table_devices.getSelectionModel().getSelectedIndex() != -1) {
                gui_model_device d = table_devices.getSelectionModel().getSelectedItem();

                create_confirm_remove_device_dialog(d);
            } else {
                create_dialog("Please select a device first");
            }
        });
    }

    public void create_confirm_remove_device_dialog(gui_model_device d) {
        class ConstraintsBase {

            public void remove(Parent parent) {
            }
        }
    }

    ArrayList<String> interfaces, indexes;

   void create_interface_list(){
        combobox_interfaces.setPlaceholder(new Text("Click on Generate Interface List button first"));
        combobox_interfaces.setOpacity(0.2);
        
        btn_interface_list.setOnAction((ActionEvent e) -> {
            
            Object record;
            String oid = "";
            String ip1 = txt_device_ip.getText();
                        IPAddressValidator v = new IPAddressValidator();
                        if (!IPAddressValidator.validate(ip1)) {
                            create_dialog("Please enter a valid IP");
                        }
                        if (IPAddressValidator.validate(ip1)) {
                            String community = txt_device_community.getText();
                            if(community.equals("")){community="public";}
                            Object project;
                            interfaces = Walk_command.projectWalk(ip1, community, oid); // Fix the reference to Walk_command
                            System.out.println("");
                            combobox_interfaces.getItems().setAll(interfaces);
                            combobox_interfaces.setOpacity(1);
                        }
                            Object project;
                            indexes = Walk_command.snmpWalk(ip1, community, oid);
                        }
        });

   }

    void create_combobox_ip() {

    }

    void convert_port_nos_to_dscr() {
    }

    void create_btn_refresh_lists_ip() {
        combobox_ips.setPlaceholder(new Text("Click on Refresh button to generate the list"));
        combobox_ips.setOpacity(0.2);
        btn_refresh_ips.setOnAction((ActionEvent e) -> {
            System.out.println("refreshing lists");
            combobox_ips.setOpacity(1);
            ArrayList<String> ips = database.get_all_distinct("ip", "");
            System.out.println("got" + ips.size());
            ips.add(0, "ALL");
            combobox_ips.getItems().setAll(ips);
        });
    }

    ArrayList<String[]> port_dscr;

    void create_btn_refresh_lists_port() {
        combobox_ports.setPlaceholder(new Text("Click on Refresh button to generate the list"));
        combobox_ports.setOpacity(0.2);

        btn_refresh_interfaces.setOnAction((ActionEvent event) -> {
            String ip1 = combobox_ips.getValue();
            System.out.println("");
            if (ip1 == null || ip1.equals("null")) {
                create_dialog("Please Select a IP first");
            } else {
                ports.clear();
                combobox_ports.setOpacity(1);
                port_dscr = database.get_port_dscr(ip1);
                for (String[] s : port_dscr) {
                    int port = Integer.parseInt(s[0]);
                    ports.add(s[1]);
                }
                ports.add(0, "ALL");
                combobox_ports.getItems().setAll(ports);
            }
        });

    }

    ImageView img_device_on, img_device_off;

    public void create_images() {
        Image image_device_on = new Image(FxmlController.class.getResourceAsStream("images/port_on.png"));
        img_device_on = new ImageView(image_device_on);
        img_device_on.setFitHeight(75);
        img_device_on.setFitWidth(75);
        Image image_device_off = new Image(FxmlController.class.getResourceAsStream("images/port_off.png"));
        img_device_off = new ImageView(image_device_off);
        img_device_off.setFitHeight(75);
        img_device_off.setFitWidth(75);
    }

    public void make_grid() {
        ColumnConstraints colc1 = new ColumnConstraints();
        colc1.setMinWidth(100);
        ColumnConstraints colc2 = new ColumnConstraints();
        colc2.setMinWidth(100);
        ColumnConstraints colc3 = new ColumnConstraints();
        colc3.setMinWidth(100);

        grid_devices.getColumnConstraints().setAll(colc1, colc2, colc3);
        grid_devices.setGridLinesVisible(false);
        grid_devices.setPadding(new Insets(10, 10, 10, 10));
        grid_devices.setHgap(40);
        grid_devices.setVgap(10);
    }

    public Label create_lbl(gui_model_device device) {

        Image image_device_on = new Image(FxmlController.class.getResourceAsStream("images/port_on.png"));
        img_device_on = new ImageView(image_device_on);
        img_device_on.setFitHeight(75);
        img_device_on.setFitWidth(75);
        Image image_device_off = new Image(FxmlController.class.getResourceAsStream("images/port_off.png"));
        img_device_off = new ImageView(image_device_off);
        img_device_off.setFitHeight(75);
        img_device_off.setFitWidth(75);

        ImageView icon;
        if (device.getDevice_status().equals("up")) {
            icon = img_device_on;
        } else {
            icon = img_device_off;
        }
        String dscr = device.getUser_description();
        if (dscr.equals("")) {
            dscr = device.getUser_description();
        }
        String text = device.getIp() + "\n" + dscr;
        Label dev = new Label(text, icon);
        dev.setWrapText(true);
        dev.setFont(Font.font("Verdana", FontWeight.BLACK, 12));
        dev.setMinSize(150, 200);
        dev.setMaxSize(150, 200);

        dev.setAlignment(Pos.TOP_CENTER);
        dev.setContentDisplay(ContentDisplay.TOP);
        dev.setOnMouseClicked((MouseEvent e) -> {
            stop_effect();
            dev.getGraphic().setOpacity(1);
            int depth = 30; // Setting the uniform variable for the glow width and height
            DropShadow borderGlow = new DropShadow();
            borderGlow.setOffsetY(0f);
            borderGlow.setOffsetX(0f);
            borderGlow.setColor(Color.BLUE);
            borderGlow.setWidth(depth);
            borderGlow.setHeight(depth);
            borderGlow.setSpread(0.6);
            dev.getGraphic().setEffect(borderGlow);
            String current_ip_txt = device.getIp();
            String current_port_txt = "" + device.getPort();
            if (!(current_ip.equals(current_ip_txt) && current_port.equals(current_port_txt))) {
                current_ip = current_ip_txt;
                current_port = current_port_txt;
                String dscr1 = device.getUser_description();
                if (dscr1.equals("")) {
                    dscr1 = device.getUser_description();
                }
                String dev_details = current_ip + " : " + dscr1;
                lbl_current_device.setText(dev_details);
                stop();
                creategraph();
                updategraph();
                play();
            }
        });
        return dev;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    static ArrayList<Label> lbl_list = new ArrayList();

    public void stop_effect() {
        lbl_inspeed.setText("");
        lbl_outspeed.setText("");
        lbl_inbw.setText("");
        lbl_outbw.setText("");
        for (Label l : lbl_list) {

            l.getGraphic().setOpacity(0.7);
            l.getGraphic().setEffect(null);
        }
    }

    public void add_images(){
        //create_images();
        int col=0;int row=0;
        int col_limit=3;
        grid_devices.getChildren().clear();
        
        for(gui_model_device d: Iterable<gui_model_device>.gui_model_device.deviceData) {
            // Rest of the code
        }
        scroll_pane.setContent(grid_devices);
    }

    public void refresh_images() {

        for (Label l : FxmlController.lbl_list) {

            int gui_model_device;
            mandatories.unused.gui_model_device[] deviceData;
            for (gui_model_device d : deviceData) {
            }
        }
    }

    String where_clause;
    private final String pattern = "yyyy-MM-dd";

    ArrayList<String> ports = new ArrayList<>();
    ArrayList<String> ip = new ArrayList<>();

    String start_date_to_be_sent = "", end_date_to_be_sent = "";

    @SuppressWarnings("unchecked")
    void create_btn_record() {
        Object gui_javafx;
        try {

            btn_get_records.setOnAction((ActionEvent e) -> {
                String ip1 = combobox_ips.getValue();
                String where_ip;
                if (ip1 == null || ip1.equals("") || ip1.equals("ALL")) {
                    where_ip = "";
                } else {
                    where_ip = "R.ip='" + ip1 + "'";
                }
                String port_str = combobox_ports.getValue();
                if (port_dscr == null) {
                    port_str = "";
                } else {
                    if (port_str == null) {
                        port_str = "";
                    }
                    for (String s[] : port_dscr) {
                        if (s[1].equals(port_str)) {
                            port_str = s[0];
                        }
                    }
                }
                String where_port;
                if (port_str == null || port_str.equals("") || port_str.equals("ALL")) {
                    where_port = "";
                } else {
                    int port = Integer.parseInt(port_str);
                    where_port = "R.port=" + port;
                }
                String startdate = get_date(start_date);
                String starttime_str_hrs = start_time_hrs.getText();
                if (starttime_str_hrs.length() == 1) {
                    starttime_str_hrs = "0" + starttime_str_hrs;
                }
                String starttime_str_min = start_time_mins.getText();
                if (starttime_str_min.length() == 1) {
                    starttime_str_min = "0" + starttime_str_min;
                }
                if (starttime_str_min.equals("") && !starttime_str_hrs.equals("")) {
                    starttime_str_min = "00";
                }
                if (starttime_str_hrs.equals("") && !starttime_str_min.equals("")) {
                    starttime_str_hrs = "00";
                }
                String starttime = starttime_str_hrs + ":" + starttime_str_min + ":00";
                if (starttime.equals("::00")) {
                    starttime = "00:00:00";
                }
                if (startdate == null) {
                    startdate = "";
                }
                String start = startdate + " " + starttime;

                String enddate = get_date(end_date);

                String endtime_str_hrs = end_time_hrs.getText();
                if (endtime_str_hrs.length() == 1) {
                    endtime_str_hrs = "0" + endtime_str_hrs;
                }
                String endtime_str_min = end_time_mins.getText();
                if (endtime_str_min.length() == 1) {
                    endtime_str_min = "0" + endtime_str_min;
                }
                if (endtime_str_min.equals("") && !endtime_str_hrs.equals("")) {
                    endtime_str_min = "00";
                }
                if (endtime_str_hrs.equals("") && !endtime_str_min.equals("")) {
                    endtime_str_hrs = "00";
                }
                String endtime = endtime_str_hrs + ":" + endtime_str_min + ":00";
                if (endtime.equals("::00")) {
                    endtime = "00:00:00";
                }
                if (enddate == null) {
                    enddate = "";
                }
                String end = enddate + " " + endtime;

                try {
                    if (end_date.getValue() == null || start_date.getValue() == null) {
                        System.out.println("Null value from dates");
                    } else {
                        if (end_date.getValue().isBefore(start_date.getValue())) {
                            System.out.println("exchanged date");
                            String tmp = end;
                            end = start;
                            start = tmp;
                        }

                        if (end_date.getValue().isEqual(start_date.getValue())) {
                            if (starttime_str_hrs.equals("")) {
                                starttime_str_hrs = "00";
                            }
                            int start_hrs = Integer.parseInt(starttime_str_hrs);
                            if (starttime_str_min.equals("")) {
                                starttime_str_min = "00";
                            }
                            int start_mins = Integer.parseInt(starttime_str_min);

                            if (endtime_str_hrs.equals("")) {
                                endtime_str_hrs = "00";
                            }
                            int end_hrs = Integer.parseInt(endtime_str_hrs);
                            if (endtime_str_min.equals("")) {
                                endtime_str_min = "00";
                            }
                            int end_mins = Integer.parseInt(endtime_str_min);

                            System.out.println("start:" + start_hrs + ":" + start_mins);
                            System.out.println("end:" + end_hrs + ":" + end_mins);

                            if (start_hrs > end_hrs) {
                                System.out.println("exchanged date");
                                String tmp = end;
                                end = start;
                                start = tmp;
                            }
                            if (start_hrs == end_hrs) {
                                System.out.println("same");
                                if (start_mins > end_mins) {
                                    System.out.println("exchanged date");
                                    String tmp = end;
                                    end = start;
                                    start = tmp;
                                }
                            }
                        }
                    }
                } catch (Exception exp) {
                    System.out.println("\n\nDate error\n\n");
                    System.out.println("Error in creating correct date");
                    exp.printStackTrace(System.out);
                }

                String where_time;
                if (startdate.equals("") || enddate.equals("")) {
                    where_time = "";
                } else {
                    where_time = "R.record_date BETWEEN '" + start + "' and '" + end + "'";
                }

                start_date_to_be_sent = start;
                end_date_to_be_sent = end;

                String where_clause1 = "";
                if (!where_ip.equals("")) {
                    where_clause1 = where_clause1 + " where " + where_ip;
                }
                if (where_ip.equals("") && !where_port.equals("")) {
                    where_clause1 = where_clause1 + " where " + where_port;
                }
                if (!where_port.equals("") && !where_ip.equals("")) {
                    where_clause1 = where_clause1 + " and " + where_port;
                }
                if (where_port.equals("") && where_ip.equals("") && !where_time.equals("")) {
                    where_clause1 = where_clause1 + " where " + where_time;
                }
                if (!where_time.equals("")) {
                    if (!where_port.equals("") || !where_ip.equals("")) {
                        where_clause1 = where_clause1 + " and " + where_time;
                    }
                }
                ((ObservableList<RecordData>) gui_model_record.recordData).clear();
            });

        } catch (Exception e) {
            System.out.println("Error in creating query for database Fxml_Controller.java");
            e.printStackTrace(System.out);
        }
    }

    public static void create_dialog(String Message) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.setAlwaysOnTop(true);
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(30));
        vb.setSpacing(10);
        Text msg = new Text(Message);
        msg.setFont(new Font(18));
        Button b = new Button(" OK ");
        b.setMinSize(75, 50);
        b.setOnAction((ActionEvent event) -> {
            dialogStage.close();
        });
        Image image_warning = new Image(FxmlController.class.getResourceAsStream("images/Warning.png"));
        ImageView img_warning = new ImageView(image_warning);
        img_warning.setFitHeight(75);
        img_warning.setFitWidth(75);

        vb.getChildren().addAll(img_warning, msg, b);
        Scene scene = new Scene(vb);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @SuppressWarnings("unchecked")
    void create_btn_devices_add() {
        btn_device_add.setOnAction((ActionEvent e) -> {
            String ip1 = txt_device_ip.getText();
            String choice = combobox_interfaces.getValue();
            System.out.println("choice =" + choice);
            if (choice != null && !choice.equals("null")) {
                int index = interfaces.indexOf(choice);
                String port_string = indexes.get(index);
                int port = Integer.parseInt(port_string);
                String community = txt_device_community.getText();
                if (community.equals("")) {
                    community = "public";
                }
                String usr_dscr = txt_device_usr_dscr.getText();
                IPAddressValidator v = new IPAddressValidator();
                if (!IPAddressValidator.validate(ip1)) {
                    create_dialog("Please enter a Valid IP");
                }
                if (IPAddressValidator.validate(ip1)) {
                    boolean already_present = false;
                    for (gui_model_device d : new ArrayList<gui_model_device>()) {
                        if (d.getIp().equals(ip1) && Integer.toString(port).equals(Integer.toString(port))) {
                            already_present = true;
                        }
                    }
                    ((ObservableList<gui_model_device>) gui_model_device.deviceData).add(d);
                }
            } else {
                create_dialog("Please select a Interface first!");
            }
        });
    }

    void start_recording() {
        System.out.println("\nStarted recording\n");

        DataCollector.initialise_data_collection();
        for (gui_model_device d : new ArrayList<gui_model_device>()) {
            String ip_from_devices = d.getIp();
            String port = d.getPort();
            String community = d.getCommunity();
            System.out.println(
                    "Adding for monitoring" + ip_from_devices + ":" + port + " where community = " + community);
            add(ip_from_devices, port, community);
        }
        YourClassType project;
        ((YourClassType) project).start_data_collection();

        updategraph();
        play();
        add_images();
        System.out.println("creating images");
    }

    private void add(String ip_from_devices, String port, String community) {
    }

    String get_date(DatePicker dp) {
        LocalDate local_Date = dp.getValue();
        if (local_Date != null) {
            int day_num = local_Date.getDayOfMonth();
            int month_num = local_Date.getMonthValue();
            int year_num = local_Date.getYear();

            String day = checkDigit(day_num);
            String month = checkDigit(month_num);
            String year = checkDigit(year_num);

            String date = year + "-" + month + "-" + day;

            return date;
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public void initialise_date_format() {
        String date_pattern = "dd-MM-yyyy";
        @SuppressWarnings("rawtypes")
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(date_pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        start_date.setConverter(converter);
        start_date.setPromptText(date_pattern.toLowerCase());
        end_date.setConverter(converter);
        end_date.setPromptText(date_pattern.toLowerCase());
    }

    public String checkDigit(int number) {
        return number <= 9 ? "0" + number : String.valueOf(number);
    }

    @SuppressWarnings("rawtypes")
    static XYChart.Series out_series, inp_series;
    static CategoryAxis xAxis;
    static NumberAxis yAxis;

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void creategraph() {
        graph_x_time_axis.setLabel("Time");
        graph_y_number_axis.setLabel("Speed");
        graph_y_number_axis.setAnimated(false);
        graph_y_number_axis.setAutoRanging(true);
        graph.setAnimated(false);
        graph.setTitle("Graph");

        inp_series = new XYChart.Series();
        inp_series.setName("Incoming Speed");

        out_series = new XYChart.Series();
        out_series.setName("Outgoing speed");
        graph.getData().clear();
        graph.getData().setAll(inp_series, out_series);

    }

    private static Timeline animation;
    public static SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    public static String current_port = "";
    public static String current_ip = "";
    static double unit_convertor = 1;
    static Date last_shown = new Date();

    void set_num_for_animation() {
        last_shown = new Date();

        lbl_inbw.setText("");
        lbl_outbw.setText("");
        lbl_inspeed.setText("");
        lbl_outspeed.setText("");

    }

    public void updategraph() {
        set_num_for_animation();
        animation = new Timeline();

        System.out.println("update called");

        Object project;
        Object Datacollector;
        animation.getKeyFrames()
                .add(new KeyFrame(Duration.seconds(Datacollector.update_freq), (ActionEvent actionEvent) -> {

                    String date = sdf.format(new Date());
                    double inbw = 0, outbw = 0;
                    double in_speed = 0, out_speed = 0;
                    double graph_in_speed = 0, graph_out_speed = 0;
                    ArrayList<record> current_records = Datacollector.get_current_recordings();
                    System.out.println("size of records arraylist" + current_records.size());
                    boolean find = false;
                    for (record record : current_records) {
                        if (record.port.equals(current_port) && record.ip.equals(current_ip)) {
                            if (record.date.after(last_shown)) {
                                inbw = record.in_bw;
                                outbw = record.out_bw;
                                in_speed = record.data_datarate_in_mbps * 1024;
                                out_speed = record.data_datarate_out_mbps * 1024;
                                find = true;
                                System.out.println("found in records" + find);
                                last_shown = record.date;
                            }
                        }
                    }

                    System.out.println("speed(kbps) is in:" + in_speed + " outspeed " + out_speed);
                    System.out.println(" found in records " + find);

                    for (gui_model_device d : gui_model_device.deviceData) {
                        d.update_status();
                    }
                    refresh_images();
                    Double in_speed_copy = in_speed;
                    Double out_speed_copy = out_speed;
                    graph_in_speed = (in_speed_copy / 1024) * unit_convertor;
                    graph_out_speed = (out_speed_copy / 1024) * unit_convertor;

                    double[] pos = splitpane_monitor.getDividerPositions();
                    int size_of_x_axis = 8;

                    if (pos[0] < 0.4) {
                        size_of_x_axis = 15;
                    }
                    if (pos[0] < 0.2) {
                        size_of_x_axis = 20;
                    }

                    out_series.getData().add(new XYChart.Data(date, graph_out_speed));
                    if (out_series.getData().size() > size_of_x_axis) {
                        while (out_series.getData().size() != size_of_x_axis) {
                            out_series.getData().remove(0);
                        }
                    }
                    inp_series.getData().add(new XYChart.Data(date, graph_in_speed));
                    if (inp_series.getData().size() > size_of_x_axis) {
                        while (inp_series.getData().size() != size_of_x_axis) {
                            inp_series.getData().remove(0);
                        }
                    }

                    String units_in = "Kbps", units_out = "Kbps";
                    if (in_speed < 1) {
                        in_speed *= 1024;
                        units_in = "Bps";
                    }
                    if (out_speed < 1) {
                        out_speed *= 1024;
                        units_out = "Bps";
                    }
                    if (in_speed > 1024) {
                        in_speed /= 1024;
                        units_in = "Mbps";
                    }
                    if (out_speed > 1024) {
                        out_speed /= 1024;
                        units_out = "Mbps";
                    }
                    if (inbw < 0.001) {
                        inbw = 0.001;
                    }
                    if (outbw < 0.001) {
                        outbw = 0.001;
                    }

                    String units_from_combobox = combobox_speed.getValue();
                    if (units_from_combobox == null || units_from_combobox.equals("null")) {
                        units_from_combobox = "Mbps";
                    }

                    lbl_inbw.setText(String.format("%.3f", inbw) + " %");
                    lbl_outbw.setText(String.format("%.3f", outbw) + " %");
                    lbl_inspeed.setText(String.format("%.3f", graph_in_speed) + " " + units_from_combobox);
                    lbl_outspeed.setText(String.format("%.3f", graph_out_speed) + " " + units_from_combobox);
                    project.Datacollector.clear_copyArrayList(last_shown);
                }));
        animation.setCycleCount(Animation.INDEFINITE);

    }

    public static void play() {
        animation.play();
    }

    public void stop() {
        animation.pause();
    }

    public void create_table_records() {
        System.out.println("making records table");

        col_ip.setCellValueFactory(
                new PropertyValueFactory<>("ip"));
        col_ip.setText("IP\nAddress");
        makeHeaderWrappable(col_ip);

        col_dscr.setCellValueFactory(
                new PropertyValueFactory<>("dscr"));
        col_dscr.setText("Interface\nDescription");
        makeHeaderWrappable(col_dscr);

        col_inbw.setCellValueFactory(
                new PropertyValueFactory<>("inbw"));
        col_inbw.setText("Average\nIN B/W\n( % )");
        makeHeaderWrappable(col_inbw);

        col_outbw.setCellValueFactory(
                new PropertyValueFactory<>("outbw"));
        col_outbw.setText("Average\nOUT B/W\n( % )");
        makeHeaderWrappable(col_outbw);
        col_data_in.setCellValueFactory(
                new PropertyValueFactory<>("datain"));
        col_data_in.setText("Total \nData-IN\n( MB )");
        makeHeaderWrappable(col_data_in);

        col_data_out.setCellValueFactory(
                new PropertyValueFactory<>("dataout"));
        col_data_out.setText("Total\nData-OUT\n( MB )");
        makeHeaderWrappable(col_data_out);

        col_max_speed.setCellValueFactory(
                new PropertyValueFactory<>("speed"));
        col_max_speed.setText("Max\nSpeed\n( Mbps )");
        makeHeaderWrappable(col_max_speed);
        col_datarate_in.setCellValueFactory(
                new PropertyValueFactory<>("dataratein"));
        col_datarate_in.setText("Total IN\nData-Rate\n( Mbps )");
        makeHeaderWrappable(col_datarate_in);

        col_datarate_out.setCellValueFactory(
                new PropertyValueFactory<>("datarateout"));
        col_datarate_out.setText("Total OUT\nData-Rate\n( Mbps )");
        makeHeaderWrappable(col_datarate_out);

        col_totalbw.setCellValueFactory(
                new PropertyValueFactory<>("totalbw"));
        col_totalbw.setText("Total\nBandwidth\n( % )");
        makeHeaderWrappable(col_totalbw);

        col_totaldata.setCellValueFactory(
                new PropertyValueFactory<>("totaldata"));
        col_totaldata.setText("Total Data\nTransferred\n( MBs )");
        makeHeaderWrappable(col_totaldata);

        col_totalrate.setCellValueFactory(
                new PropertyValueFactory<>("totalrate"));
        col_totalrate.setText("Total\nData-Rate\n( Mbps )");
        makeHeaderWrappable(col_totalrate);
    }

    void create_table_devices() {
        col_device_ip.setCellValueFactory(
                new PropertyValueFactory<>("ip"));
        col_device_port.setCellValueFactory(
                new PropertyValueFactory<>("port"));
        col_device_community.setCellValueFactory(
                new PropertyValueFactory<>("community"));
        col_device_speed.setCellValueFactory(
                new PropertyValueFactory<>("speed"));
        col_device_description.setCellValueFactory(
                new PropertyValueFactory<>("snmp_description"));
        col_device_status.setCellValueFactory(
                new PropertyValueFactory<>("device_status"));
        col_usr_dscr.setCellValueFactory(
                new PropertyValueFactory<>("user_description"));
    }
}

class IPAddressValidator {

    private static Pattern pattern;
    private static Matcher matcher;

    private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public IPAddressValidator() {
        pattern = Pattern.compile(IPADDRESS_PATTERN);
    }

    public static boolean validate(final String ip) {
        matcher = pattern.matcher(ip);
        return matcher.matches();
    }
}