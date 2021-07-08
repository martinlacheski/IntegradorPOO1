package vista;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import controlador.ControladoraCosecha;
import controlador.ControladoraCuadro;
import controlador.ControladoraDetalleCosecha;
import controlador.ControladoraEmpleado;
import controlador.ControladoraLote;
import controlador.ControladoraProductor;
import modelo.Cosecha;
import modelo.Cuadro;
import modelo.DetalleCosecha;
import modelo.Empleado;
import modelo.Lote;
import modelo.Productor;

public class InterfazCosechaController implements Initializable {

    //Creo variables de los controladores
    ControladoraCosecha controladoraCosecha;
    ControladoraDetalleCosecha controladoraDetalleCosecha;
    ControladoraProductor controladoraProductor;
    ControladoraEmpleado controladoraEmpleado;
    ControladoraLote controladoraLote;
    ControladoraCuadro controladoraCuadro;

    @FXML
    private TextField txtCosechaID;

    @FXML
    private TextField txtDetalleCosechaID;

    //Necesitamos modificar por el tipo de valor que se va a alojar
    @FXML
    private TableView<Cosecha> tablaCosecha;

    @FXML
    private TableView<DetalleCosecha> tablaDetalle;

    @FXML
    private ComboBox<Productor> cmbProductor;

    @FXML
    private ComboBox<Lote> cmbLote;

    @FXML
    private ComboBox<Empleado> cmbEmpleado;

    @FXML
    private ComboBox<Cuadro> cmbCuadro;

    @FXML
    private Button btnCosechaNuevo;

    @FXML
    private Button btnCosechaAgregar;

    @FXML
    private Button btnCosechaCancelar;

    @FXML
    private DatePicker dateFecha;

    @FXML
    private TextField txtCampo;

    @FXML
    private TextField txtSecadero;

    @FXML
    private TextField txtDiferencia;

    @FXML
    private Button btnDetalleNuevo;

    @FXML
    private Button btnDetalleAgregar;

    @FXML
    private Button btnDetalleCancelar;

    @FXML
    private TextField txtKilos;

    //Inicializamos variables de contexto
    private ContextMenu cmOpcionesCosecha;
    private ContextMenu cmOpcionesDetalle;

    //Inicializamos variables Objeto para uso interno
    private Productor productorSeleccionado;
    private Lote loteSeleccionado;
    private Cosecha cosechaSeleccionado;
    private Cosecha cosechaGuardado;
    private DetalleCosecha detalleSeleccionado;
    private Empleado empleadoSeleccionado;
    private Cuadro cuadroSeleccionado;
    private double kilosCosechaCampo = 0;

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
        cmbLote.getItems().clear();

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
        //Seteamos los valores del listado

        cmbLote.setItems(data);

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

    public void listarCuadros() {
        controladoraCuadro = new ControladoraCuadro();

        //Limpiamos los items y columnas para asegurarnos
        cmbCuadro.getItems().clear();

        //asignamos a la variable el Productor seleccionado
        loteSeleccionado = cmbLote.getValue();

        //Obtenemos el listado de elementos
        // asociamos los datos
        List<Cuadro> cuadros = this.controladoraCuadro.listarCuadros();
        //Filtramos los lotes que corresponden al Productor seleccionado
        List<Cuadro> listaFiltrada = new ArrayList<Cuadro>();
        for (Cuadro cuadro : cuadros) {
            if (cuadro.getLote().getIdLote() == loteSeleccionado.getIdLote()) {
                listaFiltrada.add(cuadro);
            }
        }
        //Se tiene que pasar el OBSERVABLE a un array
        ObservableList<Cuadro> data = FXCollections.observableArrayList(listaFiltrada);
        //Seteamos los valores del listado
        cmbCuadro.setItems(data);
    }

    public void limpiarCosecha() {
        dateFecha.setValue(LocalDate.now());

        cmbProductor.getItems().remove(0, cmbProductor.getItems().size());
        cmbProductor.getItems().clear();
        listarProductores();
        cmbLote.getItems().remove(0, cmbLote.getItems().size());
        cmbLote.getItems().clear();
        txtCampo.setText("0");
        txtSecadero.setText("0");
        txtDiferencia.setText("0");
        txtCosechaID.setText("");
    }

    public void limpiarDetalle() {
        cmbEmpleado.getItems().clear();
        listarEmpleados();
        cmbCuadro.getItems().clear();
        listarCuadros();
        txtKilos.setText("0");
        txtDetalleCosechaID.setText("");
    }

    private boolean chequearCosecha() {
        var chequearCosecha = true;
        // Cargo en variables los datos obtenidos de la interfaz
        Productor productor = productorSeleccionado;
        Lote lote = loteSeleccionado;
        if (productor == null) {
            chequearCosecha = false;
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Revise el Productor, debe seleccionar uno");
            a.show();
        }
        if (lote == null) {
            chequearCosecha = false;
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Revise el Lote, debe seleccionar uno");
            a.show();
        }
        if (dateFecha.getValue() == null) {
            chequearCosecha = false;
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Revise la Fecha");
            a.show();
            dateFecha.requestFocus();
        }
        if (dateFecha.getValue().equals("")) {
            chequearCosecha = false;
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Revise la Fecha");
            a.show();
            dateFecha.requestFocus();
        }
        try {
            Double kilosSecadero = Double.parseDouble(txtSecadero.getText());
            if (kilosSecadero == 0) {
                chequearCosecha = false;
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Debe ingresar el kilo obtenido en Secadero");
                a.show();
            }
        } catch (Exception ex) {
            chequearCosecha = false;
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Debe ingresar el kilo obtenido en Secadero");
            a.show();
        }
        return chequearCosecha;
    }

    private boolean chequearDetalle() {
        var chequearDetalle = true;
        //Cargo en variables los datos obtenidos de la interfaz       
        Productor productor = productorSeleccionado;
        //Empleado empleado = empleadoSeleccionado;
        Empleado empleado = cmbEmpleado.getValue();
        //Cuadro cuadro = cuadroSeleccionado;
        Cuadro cuadro = cmbCuadro.getValue();
        Lote lote = cmbLote.getValue();

        if (chequearCosecha() == true) {
            if (empleado == null) {
                chequearDetalle = false;
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Revise el Empleado, debe seleccionar uno");
                a.show();
            }
            if (cuadro == null) {
                chequearDetalle = false;
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Revise el Cuadro, debe seleccionar uno");
                a.show();
            }
            try {
                Double kilos = Double.parseDouble(txtKilos.getText());
            } catch (Exception ex) {
                chequearDetalle = false;
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setHeaderText("Revise el Kilo por empleado, debe ingresar uno");
                a.show();
            }
        }
        return chequearDetalle;
    }

    ///ACA ESTA EL CORE
    public void actualizarKgCosechas() {
        controladoraCosecha = new ControladoraCosecha();
        controladoraDetalleCosecha = new ControladoraDetalleCosecha();
        //Actualizamos los kilos de las cosechas por cada cosecha
        List<Cosecha> cosechas = this.controladoraCosecha.listarCosechas();
        List<DetalleCosecha> detalleCosechas = this.controladoraDetalleCosecha.listarDetalleCosecha();
        double kilosTotales;
        for (Cosecha cosecha : cosechas) {
            kilosTotales = 0;
            for (DetalleCosecha detalleCosecha : detalleCosechas) {
                if (detalleCosecha.getCosecha().getIdCosecha() == cosecha.getIdCosecha()) {
                    kilosTotales = kilosTotales + detalleCosecha.getKgsEmpleado();
                }
            }

            int id = cosecha.getIdCosecha();
            LocalDate fecha = cosecha.getFechaCosecha();
            Lote lote = cosecha.getLote();
            //kilosCosechaCampo = kilosTotales;
            Double secadero = cosecha.getKgsSecadero();
            Double diferencia = kilosTotales - secadero;
            this.controladoraCosecha.actualizarKgsCosecha(id, fecha, lote, kilosTotales, secadero, diferencia);
        }
        if (!txtCosechaID.getText().isEmpty()) {
           int id = Integer.parseInt(txtCosechaID.getText());
           List<Cosecha> cosechaActual = this.controladoraCosecha.listarCosechas();
           for (Cosecha cosecha : cosechaActual) {
                if (cosecha.getIdCosecha() == id) {
                    cosechaSeleccionado = cosecha;
                    break;
                }
            } 
           txtCampo.setText(String.valueOf(cosechaSeleccionado.getKgsCampo()));
           txtSecadero.setText(String.valueOf(cosechaSeleccionado.getKgsSecadero()));
           txtDiferencia.setText(String.valueOf(cosechaSeleccionado.getKgsCosecha()));
        }
    }

    public void listarCosechas() {
        controladoraCosecha = new ControladoraCosecha();

        //Limpiamos los items y columnas para asegurarnos
        tablaCosecha.getItems().clear();
        tablaCosecha.getColumns().clear();

        //Obtenemos el listado de elementos
        // asociamos los datos
        List<Cosecha> cosechas = this.controladoraCosecha.listarCosechas();

        //Se tiene que pasar el OBSERVABLE a un array
        ObservableList<Cosecha> data = FXCollections.observableArrayList(cosechas);

        //Seteamos los valores de las columnas
        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("idCosecha"));

        TableColumn fechaCol = new TableColumn("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory("fechaCosecha"));

        TableColumn loteCol = new TableColumn("Lote");
        loteCol.setCellValueFactory(new PropertyValueFactory("lote"));

        TableColumn campoCol = new TableColumn("Kg Campo");
        campoCol.setCellValueFactory(new PropertyValueFactory("kgsCampo"));

        TableColumn secaderoCol = new TableColumn("Kg");
        secaderoCol.setCellValueFactory(new PropertyValueFactory("kgsSecadero"));

        TableColumn difCol = new TableColumn("Diferencia");
        difCol.setCellValueFactory(new PropertyValueFactory("kgsCosecha"));

        //Seteamos los valores del listado
        tablaCosecha.setItems(data);
        tablaCosecha.getColumns().addAll(idCol, fechaCol, loteCol, campoCol, secaderoCol, difCol);
        int cosechaID = tablaCosecha.getItems().size();
        cosechaGuardado = tablaCosecha.getItems().get(cosechaID - 1);
    }

    private void listarDetalle() {
        controladoraDetalleCosecha = new ControladoraDetalleCosecha();
        //Limpiamos los items y columnas para asegurarnos
        tablaDetalle.getItems().clear();
        tablaDetalle.getColumns().clear();

        //Obtenemos el listado de elementos
        // asociamos los datos
        List<DetalleCosecha> detalleCosechas = this.controladoraDetalleCosecha.listarDetalleCosecha();
        //Filtramos los cuadros que pertenecen al Lote seleccionado
        List<DetalleCosecha> listaFiltrada = new ArrayList<DetalleCosecha>();
        for (DetalleCosecha detalleCosecha : detalleCosechas) {
            if (detalleCosecha.getCosecha().getIdCosecha() == cosechaSeleccionado.getIdCosecha()) {
                listaFiltrada.add(detalleCosecha);
            }
        }

        //Se tiene que pasar el OBSERVABLE a un array
        ObservableList<DetalleCosecha> data = FXCollections.observableArrayList(listaFiltrada);

        //Seteamos los valores de las columnas
        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("idDetalleCosecha"));

        TableColumn empleadoCol = new TableColumn("Empleado");
        empleadoCol.setCellValueFactory(new PropertyValueFactory("empleado"));

        TableColumn cuadroCol = new TableColumn("Cuadro");
        cuadroCol.setCellValueFactory(new PropertyValueFactory("cuadro"));

        TableColumn kilosCol = new TableColumn("Kilos");
        kilosCol.setCellValueFactory(new PropertyValueFactory("kgsEmpleado"));

        //Seteamos los valores del listado
        tablaDetalle.setItems(data);
        tablaDetalle.getColumns().addAll(idCol, empleadoCol, cuadroCol, kilosCol);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        actualizarKgCosechas();
        //Asignamos NOW a la fecha por defecto
        dateFecha.setValue(LocalDate.now());
        //Asingamos valores por defecto a los txt de kilos
        txtCampo.setText("0");
        txtSecadero.setText("0");
        txtDiferencia.setText("0");

        //Listamos cosechas
        listarCosechas();
        //Cargamos los combobox de productores y empleados
        listarProductores();
        listarEmpleados();

        //TABLA COSECHAS
        //Generamos el menu de contexto CLICK DERECHO
        cmOpcionesCosecha = new ContextMenu();
        MenuItem miEditarCosecha = new MenuItem("Editar");
        MenuItem miEliminarCosecha = new MenuItem("Eliminar");
        cmOpcionesCosecha.getItems().addAll(miEditarCosecha, miEliminarCosecha);

        //Evento EDITAR
        miEditarCosecha.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tablaCosecha.getSelectionModel().getSelectedIndex();
                cosechaSeleccionado = tablaCosecha.getItems().get(index);
                txtCosechaID.setText(String.valueOf(cosechaSeleccionado.getIdCosecha()));
                productorSeleccionado = cosechaSeleccionado.getLote().getProductor();
                cmbProductor.setValue(cosechaSeleccionado.getLote().getProductor());
                cmbLote.setValue(cosechaSeleccionado.getLote());
                txtCampo.setText(String.valueOf(cosechaSeleccionado.getKgsCampo()));
                txtSecadero.setText(String.valueOf(cosechaSeleccionado.getKgsSecadero()));
                txtDiferencia.setText(String.valueOf(cosechaSeleccionado.getKgsCosecha()));
                btnCosechaCancelar.setDisable(false);
                btnCosechaNuevo.setDisable(true);
            }
        });

        //Evento ELIMINAR
        miEliminarCosecha.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tablaCosecha.getSelectionModel().getSelectedIndex();
                cosechaSeleccionado = tablaCosecha.getItems().get(index);
                txtCosechaID.setText(String.valueOf(cosechaSeleccionado.getIdCosecha()));
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Confirmación");
                a.setHeaderText(null);
                a.setContentText("Estás seguro de eliminar la Cosecha: " + cosechaSeleccionado.getLote() + "?");
                a.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = a.showAndWait();
                if (result.get() == ButtonType.OK) {
                    try {
                        controladoraCosecha = new ControladoraCosecha();
                        controladoraCosecha.eliminarCosecha(Integer.parseInt(txtCosechaID.getText()),
                                cosechaSeleccionado.getFechaCosecha(), cosechaSeleccionado.getLote(), cosechaSeleccionado.getKgsCampo(),
                                cosechaSeleccionado.getKgsSecadero(), cosechaSeleccionado.getKgsCosecha());
                        // create a alert
                        a = new Alert(Alert.AlertType.INFORMATION);
                        a.setTitle("Exito");
                        a.setHeaderText(null);
                        a.setContentText("Cosecha eliminada correctamente");
                        a.show();
                        txtCosechaID.setText("");
                        limpiarCosecha();
                        cmbProductor.requestFocus();
                        //Actualizamos el kilaje de la cosecha
                        actualizarKgCosechas();
                        listarCosechas();
                        listarProductores();
                        listarEmpleados();
                    } catch (Exception ex) {
                        a = new Alert(Alert.AlertType.ERROR);
                        a.setTitle("Error");
                        a.setHeaderText(null);
                        a.setContentText("Ocurrió un error al eliminar la Cosecha");
                        a.show();
                    }
                }
            }
        });
        tablaCosecha.setContextMenu(cmOpcionesCosecha);

        ///TABLA DETALLE COSECHA
        //Generamos el menu de contexto CLICK DERECHO
        cmOpcionesDetalle = new ContextMenu();
        MenuItem miEditarDetalle = new MenuItem("Editar");
        MenuItem miEliminarDetalle = new MenuItem("Eliminar");
        cmOpcionesDetalle.getItems().addAll(miEditarDetalle, miEliminarDetalle);

        //EVENTO EDITAR
        miEditarDetalle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tablaDetalle.getSelectionModel().getSelectedIndex();
                detalleSeleccionado = tablaDetalle.getItems().get(index);
                txtDetalleCosechaID.setText(String.valueOf(detalleSeleccionado.getIdDetalleCosecha()));
                cmbEmpleado.setValue(detalleSeleccionado.getEmpleado());
                cmbCuadro.setValue(detalleSeleccionado.getCuadro());
                txtKilos.setText(String.valueOf(detalleSeleccionado.getKgsEmpleado()));
                btnDetalleCancelar.setDisable(false);
                btnDetalleNuevo.setDisable(true);
            }
        });

        //EVENTO ELIMINAR
        miEliminarDetalle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tablaDetalle.getSelectionModel().getSelectedIndex();
                detalleSeleccionado = tablaDetalle.getItems().get(index);
                txtDetalleCosechaID.setText(String.valueOf(detalleSeleccionado.getIdDetalleCosecha()));
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Confirmación");
                a.setHeaderText(null);
                a.setContentText("Estás seguro de eliminar el Detalle de Cosecha: "
                        + detalleSeleccionado.getCuadro() + "?");
                a.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = a.showAndWait();
                if (result.get() == ButtonType.OK) {
                    try {
                        controladoraDetalleCosecha = new ControladoraDetalleCosecha();
                        Double kilos = Double.parseDouble(txtKilos.getText());
                        controladoraDetalleCosecha.eliminarDetalleCosecha(Integer.parseInt(txtDetalleCosechaID.getText()),
                                cosechaSeleccionado, cmbCuadro.getValue(), cmbEmpleado.getValue(), kilos);
                        //Actualizo el total de kilos de la cosecha
                        actualizarKgCosechas();
                        // create a alert
                        a = new Alert(Alert.AlertType.INFORMATION);
                        a.setTitle("Exito");
                        a.setHeaderText(null);
                        a.setContentText("Detalle eliminado correctamente");
                        a.show();
                        limpiarDetalle();
                        cmbEmpleado.requestFocus();
                        listarCosechas();
                        listarDetalle();
                        listarProductores();
                        listarEmpleados();
                        //listarDetalle();
                    } catch (Exception ex) {
                        a = new Alert(Alert.AlertType.ERROR);
                        a.setTitle("Error");
                        a.setHeaderText(null);
                        a.setContentText("Ocurrió un error al eliminar el Detalle");
                        a.show();
                    }
                }
            }
        });
        tablaDetalle.setContextMenu(cmOpcionesDetalle);

    }

    @FXML
    void cmbProductorAction(ActionEvent event) {
        try {
            listarLotes();
        } catch (Exception ex) {

        }
    }

    @FXML
    void cmbLoteAction(ActionEvent event) {
        try {
            listarCuadros();
        } catch (Exception ex) {

        }
    }

    @FXML
    void tablaCosechaClic(MouseEvent event) {
        int index = tablaCosecha.getSelectionModel().getSelectedIndex();
        cosechaSeleccionado = tablaCosecha.getItems().get(index);
        txtCosechaID.setText(String.valueOf(cosechaSeleccionado.getIdCosecha()));
        productorSeleccionado = cosechaSeleccionado.getLote().getProductor();
        cmbProductor.setValue(cosechaSeleccionado.getLote().getProductor());
        cmbLote.setValue(cosechaSeleccionado.getLote());
        txtCampo.setText(String.valueOf(cosechaSeleccionado.getKgsCampo()));
        txtSecadero.setText(String.valueOf(cosechaSeleccionado.getKgsSecadero()));
        txtDiferencia.setText(String.valueOf(cosechaSeleccionado.getKgsCosecha()));
        listarDetalle();
    }

    @FXML
    void tablaDetalleClic(MouseEvent event) {
        int index = tablaDetalle.getSelectionModel().getSelectedIndex();
        detalleSeleccionado = tablaDetalle.getItems().get(index);
        txtDetalleCosechaID.setText(String.valueOf(detalleSeleccionado.getIdDetalleCosecha()));
        cmbEmpleado.setValue(detalleSeleccionado.getEmpleado());
        cmbCuadro.setValue(detalleSeleccionado.getCuadro());
        txtKilos.setText(String.valueOf(detalleSeleccionado.getKgsEmpleado()));
    }

    @FXML
    void btnCosechaNuevoAction(ActionEvent event) {
        limpiarCosecha();
    }

    @FXML
    void btnCosechaAgregarAction(ActionEvent event) {
        if (cosechaSeleccionado == null) {
            if (chequearCosecha() == true) {
                // Cargo en variables los datos obtenidos de la interfaz
                LocalDate fecha = dateFecha.getValue();
                Lote lote = cmbLote.getValue();
                Double kgsCampo = Double.parseDouble(txtCampo.getText());
                Double kgsSecadero = Double.parseDouble(txtCampo.getText());
                Double kgsCosecha = kgsCampo - kgsSecadero;

                try {
                    controladoraCosecha = new ControladoraCosecha();
                    //Invocamos al metodo de la controladora para dar de alta una nueva cosecha
                    controladoraCosecha.altaCosecha(fecha, lote, kgsCampo,
                            kgsSecadero, kgsCosecha);

                    // create a alert
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Exito");
                    a.setHeaderText(null);
                    a.setContentText("Cosecha registrada correctamente");
                    a.show();
                    limpiarCosecha();
                    listarProductores();
                    listarCosechas();
                    cmbProductor.requestFocus();
                    actualizarKgCosechas();
                } catch (Exception ex) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText(null);
                    a.setContentText("Ocurrió un error al registrar la Cosecha");
                    a.show();
                }

            }
        } else {
            if (chequearCosecha() == true) {
                // Cargo en variables los datos obtenidos de la interfaz
                LocalDate fecha = dateFecha.getValue();
                Lote lote = cmbLote.getValue();
                Double kgsCampo = Double.parseDouble(txtCampo.getText());
                Double kgsSecadero = Double.parseDouble(txtSecadero.getText());
                Double kgsDiferencia = Double.parseDouble(txtDiferencia.getText());
                try {
                    controladoraCosecha = new ControladoraCosecha();
                    //Invocamos al metodo de la controladora para actualiar una cosecha
                    controladoraCosecha.actualizarCosecha(Integer.parseInt(txtCosechaID.getText()),
                            fecha, lote, kgsCampo, kgsSecadero, kgsDiferencia);

                    // create a alert
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Exito");
                    a.setHeaderText(null);
                    a.setContentText("Cosecha actualizada correctamente");
                    a.show();
                    limpiarCosecha();
                    listarProductores();
                    cmbProductor.requestFocus();
                    //Actualizamos el kilaje de la cosecha
                    actualizarKgCosechas();
                    listarCosechas();
                } catch (Exception ex) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText(null);
                    a.setContentText("Ocurrió un error al registrar la Cosecha");
                    a.show();
                }
            }
        }
        btnCosechaCancelar.setDisable(true);
        btnCosechaNuevo.setDisable(false);
    }

    @FXML
    void btnCosechaCancelar(ActionEvent event) {
        limpiarCosecha();
        cmbProductor.requestFocus();
        btnCosechaCancelar.setDisable(true);
        btnCosechaNuevo.setDisable(false);
    }

    @FXML
    void btnDetalleNuevoAction(ActionEvent event) {
        limpiarDetalle();
    }

    @FXML
    void btnDetalleAgregarAction(ActionEvent event) {
        if (cosechaSeleccionado == null) {
            // create a alert
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText(null);
            a.setContentText("Debe seleccionar una Cosecha para poder cargar un detalle");
            a.show();
        } else if (detalleSeleccionado == null) {
            if (chequearDetalle() == true) {
                // Cargo en variables los datos obtenidos de la interfaz       
                Cuadro cuadro = cmbCuadro.getValue();
                Empleado empleado = cmbEmpleado.getValue();
                Double kilos = Double.parseDouble(txtKilos.getText());
                try {
                    controladoraDetalleCosecha = new ControladoraDetalleCosecha();
                    //Invocamos al metodo de la controladora para dar de alta un nuevo Detalle Cosecha
                    controladoraDetalleCosecha.altaDetalleCosecha(cosechaSeleccionado, cuadro, empleado, kilos);
                    // create a alert
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Exito");
                    a.setHeaderText(null);
                    a.setContentText("Detalle registrado correctamente");
                    a.show();
                    limpiarDetalle();
                    //Actualizamos el kilaje de la cosecha
                    actualizarKgCosechas();
                    listarCosechas();
                    listarDetalle();
                    listarEmpleados();
                    listarCuadros();
                } catch (Exception ex) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText(null);
                    a.setContentText("Ocurrió un error al registrar el Detalle");
                    a.show();
                }
            }
        } else {
            if (chequearDetalle() == true) {
                // Cargo en variables los datos obtenidos de la interfaz 
                Cuadro cuadro = cmbCuadro.getValue();
                Empleado empleado = cmbEmpleado.getValue();
                Double kilos = Double.parseDouble(txtKilos.getText());
                try {
                    controladoraDetalleCosecha = new ControladoraDetalleCosecha();
                    //Invocamos al metodo de la controladora para actualizar un Detalle Cosecha
                    controladoraDetalleCosecha.actualizarDetalleCosecha(Integer.parseInt(txtDetalleCosechaID.getText()),
                            cosechaSeleccionado, cmbCuadro.getValue(), cmbEmpleado.getValue(), kilos);
                    // create a alert
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Exito");
                    a.setHeaderText(null);
                    a.setContentText("Detalle de Cosecha actualizado correctamente");
                    a.show();
                    limpiarDetalle();
                    cmbEmpleado.requestFocus();
                    //Actualizamos el kilaje de la cosecha
                    actualizarKgCosechas();
                    listarCosechas();
                    listarDetalle();
                    listarEmpleados();
                    listarCuadros();

                } catch (Exception ex) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText(null);
                    a.setContentText("Ocurrió un error al actualizar la cosecha");
                    a.show();
                }
            }
        }
        btnDetalleCancelar.setDisable(true);
        btnDetalleNuevo.setDisable(false);
    }

    @FXML
    void btnDetalleCancelarAction(ActionEvent event) {
        limpiarDetalle();
        btnDetalleCancelar.setDisable(true);
        btnDetalleNuevo.setDisable(false);
    }

    @FXML
    void txtSecaderoAction(InputMethodEvent event) {
        //Actualizamos el kilaje de la cosecha
        actualizarKgCosechas();
    }

}
