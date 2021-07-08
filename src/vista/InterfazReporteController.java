package vista;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;

import controlador.ControladoraCosecha;
import controlador.ControladoraCuadro;
import controlador.ControladoraDetalleCosecha;
import controlador.ControladoraEmpleado;
import controlador.ControladoraLote;
import controlador.ControladoraProductor;
import java.time.LocalDate;
import javafx.scene.control.Alert;
import modelo.Cosecha;
import modelo.DetalleCosecha;
import modelo.Empleado;
import modelo.Lote;
import modelo.Productor;

public class InterfazReporteController implements Initializable {

    //Creo variables de los controladores
    ControladoraCosecha controladoraCosecha;
    ControladoraDetalleCosecha controladoraDetalleCosecha;
    ControladoraProductor controladoraProductor;
    ControladoraEmpleado controladoraEmpleado;
    ControladoraLote controladoraLote;
    ControladoraCuadro controladoraCuadro;

    Productor productorSeleccionado;
    Empleado empleadoSeleccionado;

    @FXML
    private TableView<Lote> tablaLote;

    @FXML
    private TableView<DetalleCosecha> tablaCosecha;

    @FXML
    private ComboBox<Productor> cmbProductor;

    @FXML
    private ComboBox<Empleado> cmbEmpleado;

    @FXML
    private DatePicker dateInicio;

    @FXML
    private DatePicker dateFin;

    @FXML
    private Button btnBuscar;

    @FXML
    private CheckBox chkFechas;

    @FXML
    private TextField txtTotal;

    public void listarProductores() {
        controladoraProductor = new ControladoraProductor();

        //Limpiamos los items y columnas para asegurarnos
        cmbProductor.getItems().clear();

        //Obtenemos el listado de elementos
        // asociamos los datos
        List<Productor> productores = this.controladoraProductor.listarProductores();
        //Se tiene que pasar el OBSERVABLE a un array
        ObservableList<Productor> data = FXCollections.observableArrayList(productores);
        //Seteamos los valores del listado
        cmbProductor.setItems(data);
        //ver de visualizar solo RAZON SOCIAL
    }

    public void listarLotes() {
        controladoraLote = new ControladoraLote();

        //Limpiamos los items y columnas para asegurarnos
        tablaLote.getItems().clear();
        tablaLote.getColumns().clear();

        //asignamos a la variable el Productor seleccionado
        productorSeleccionado = cmbProductor.getValue();

        //Obtenemos el listado de elementos
        // asociamos los datos
        List<Lote> lotes = this.controladoraLote.listarLotes();
        //Filtramos los lotes que corresponden al Productor seleccionado
        List<Lote> listaFiltrada = new ArrayList<Lote>();
        for (Lote lote : lotes) {
            if (lote.getProductor().getLegajo() == productorSeleccionado.getLegajo()) {
                listaFiltrada.add(lote);
            }
        }

        //Se tiene que pasar el OBSERVABLE a un array
        ObservableList<Lote> data = FXCollections.observableArrayList(listaFiltrada);

        //Seteamos los valores de las columnas
        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("idLote"));

        TableColumn loteCol = new TableColumn("Nombre Lote");
        loteCol.setCellValueFactory(new PropertyValueFactory("nombreLote"));

        TableColumn direccionCol = new TableColumn("Direccion");
        direccionCol.setCellValueFactory(new PropertyValueFactory("direccionLote"));

        TableColumn estadoCol = new TableColumn("Estado");
        estadoCol.setCellValueFactory(new PropertyValueFactory("estado"));

        //Seteamos los valores del listado
        tablaLote.setItems(data);
        tablaLote.getColumns().addAll(idCol, loteCol, direccionCol, estadoCol);

    }

    public void listarEmpleados() {
        controladoraEmpleado = new ControladoraEmpleado();

        //Limpiamos los items y columnas para asegurarnos
        cmbEmpleado.getItems().clear();

        //Obtenemos el listado de elementos
        // asociamos los datos
        List<Empleado> empleados = this.controladoraEmpleado.listarEmpleados();
        //Se tiene que pasar el OBSERVABLE a un array
        ObservableList<Empleado> data = FXCollections.observableArrayList(empleados);
        //Seteamos los valores del listado

        cmbEmpleado.setItems(data);
        //ver de visualizar solo RAZON SOCIAL
    }

    public void listarCosechasEmpleado() {
        controladoraDetalleCosecha = new ControladoraDetalleCosecha();
        controladoraCosecha = new ControladoraCosecha();
        //asignamos a la variable el Empleado seleccionado
        empleadoSeleccionado = cmbEmpleado.getValue();

        //Limpiamos los items y columnas para asegurarnos
        tablaCosecha.getItems().clear();
        tablaCosecha.getColumns().clear();

        //Obtenemos el listado de elementos
        // asociamos los datos
        List<DetalleCosecha> cosechas = this.controladoraDetalleCosecha.listarDetalleCosecha();

        //Variabla de Kilos Cosechados
        Double total = 0.0;
        //Filtramos los DetalleCosechas que corresponden al Empleado seleccionado
        List<DetalleCosecha> listaFiltrada = new ArrayList<DetalleCosecha>();

        if (this.chkFechas.isSelected() == true) {
            //for
            List<Cosecha> cosechaEmpleado = this.controladoraCosecha.listarCosechas();
            for (Cosecha cosecha : cosechaEmpleado) {
                LocalDate fecha = cosecha.getFechaCosecha();
                //if
                if (cosecha.getFechaCosecha().isAfter(dateInicio.getValue())
                        && (cosecha.getFechaCosecha().isBefore(dateFin.getValue()))) {
                    for (DetalleCosecha detalle : cosechas) {
                        if (detalle.getCosecha().getIdCosecha() == cosecha.getIdCosecha()) {
                            if (detalle.getEmpleado().getLegajo() == empleadoSeleccionado.getLegajo()) {
                                total = total + detalle.getKgsEmpleado();
                                listaFiltrada.add(detalle);
                            }
                        }
                    }
                }
            }
        } else {
            for (DetalleCosecha detalle : cosechas) {
                try {
                    if (detalle.getEmpleado().getLegajo() == empleadoSeleccionado.getLegajo()) {
                        total = total + detalle.getKgsEmpleado();
                        listaFiltrada.add(detalle);
                    }
                } catch (Exception ex) {
                }
            }
        }
        //Asignamos al TXT el total de cosecha de Empleado
        txtTotal.setText(String.valueOf(total));

        //Se tiene que pasar el OBSERVABLE a un array
        ObservableList<DetalleCosecha> data = FXCollections.observableArrayList(listaFiltrada);

        //Seteamos los valores de las columnas
        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("idDetalleCosecha"));

        TableColumn cosechaCol = new TableColumn("Cosecha");
        cosechaCol.setCellValueFactory(new PropertyValueFactory("cosecha"));

        TableColumn cuadroCol = new TableColumn("Cuadro");
        cuadroCol.setCellValueFactory(new PropertyValueFactory("cuadro"));

        TableColumn kilosCol = new TableColumn("Kilos");
        kilosCol.setCellValueFactory(new PropertyValueFactory("kgsEmpleado"));

        //Seteamos los valores del listado
        tablaCosecha.setItems(data);
        tablaCosecha.getColumns().addAll(idCol, cosechaCol, cuadroCol, kilosCol);
    }

    @FXML
    void cmbProductorAction(ActionEvent event) {
        listarLotes();
    }

    @FXML
    void cmbEmpleadoAction(ActionEvent event) {
        listarCosechasEmpleado();
    }

    @FXML
    void chkFechasAction(ActionEvent event) {
        if (this.chkFechas.isSelected() == true) {
            //Limpiamos los items y columnas para asegurarnos
            tablaCosecha.getItems().clear();
            tablaCosecha.getColumns().clear();
            txtTotal.setText("");
            dateInicio.setDisable(false);
            dateFin.setDisable(false);
            btnBuscar.setDisable(false);
            dateInicio.setValue(LocalDate.now());
            dateFin.setValue(LocalDate.now());
        } else {
            //Limpiamos los items y columnas para asegurarnos
            tablaCosecha.getItems().clear();
            tablaCosecha.getColumns().clear();
            listarEmpleados();
            txtTotal.setText("");
            dateInicio.setDisable(true);
            dateFin.setDisable(true);
            btnBuscar.setDisable(true);
        }
    }

    @FXML
    void btnBuscarAction(ActionEvent event) {
        if ((dateInicio.getValue().isAfter(dateFin.getValue()))) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText(null);
            a.setContentText("La fecha de inicio debe ser anterior que la de fin");
            a.show();
        } else {
            listarCosechasEmpleado();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listarProductores();
        listarEmpleados();
    }

}
