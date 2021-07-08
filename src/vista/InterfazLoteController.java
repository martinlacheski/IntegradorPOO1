package vista;

import java.net.URL;
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
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;
import controlador.ControladoraCuadro;
import controlador.ControladoraLote;
import controlador.ControladoraProductor;
import modelo.Cuadro;
import modelo.Lote;
import modelo.Productor;

public class InterfazLoteController implements Initializable {

    ControladoraProductor controladoraProductor;
    ControladoraLote controladoraLote;
    ControladoraCuadro controladoraCuadro;

    //Necesitamos modificar por el tipo de valor que se va a alojar
    //por defecto private TableView<?> XXXXXXX;
    @FXML
    private TableView<Lote> tablaLotes;

    @FXML
    private TableView<Cuadro> tablaCuadros;

    @FXML
    private TextArea txtDireccionLote;

    //Necesitamos modificar por el tipo de valor que se va a alojar
    //por defecto private ComboBox<?> XXXXXXX;
    @FXML
    private ComboBox<Productor> cmbProductor;

    @FXML
    private Button btnNuevoLote;

    @FXML
    private Button btnAgregarLote;

    @FXML
    private Button btnCancelarLote;

    @FXML
    private TextArea txtNombreLote;

    @FXML
    private Button btnNuevoCuadro;

    @FXML
    private Button btnAgregarCuadro;

    @FXML
    private Button btnCuadroCancelar;

    @FXML
    private TextField txtNombreCuadro;

    //Inicializamos variables de contexto
    private ContextMenu cmOpcionesLote;
    private ContextMenu cmOpcionesCuadro;

    //Inicializamos variables Objeto para uso interno
    private Productor productorSeleccionado;
    private Lote loteSeleccionado;
    private Lote loteGuardado;
    private Cuadro cuadroSeleccionado;

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
        tablaLotes.getItems().clear();
        tablaLotes.getColumns().clear();

        //Obtenemos el listado de elementos
        // asociamos los datos
        List<Lote> lotes = this.controladoraLote.listarLotes();

        //Se tiene que pasar el OBSERVABLE a un array
        ObservableList<Lote> data = FXCollections.observableArrayList(lotes);

        //Seteamos los valores de las columnas
        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("idLote"));

        TableColumn productorCol = new TableColumn("Productor");
        productorCol.setCellValueFactory(new PropertyValueFactory("productor"));

        TableColumn nombreCol = new TableColumn("Lote");
        nombreCol.setCellValueFactory(new PropertyValueFactory("nombreLote"));

        TableColumn direccionCol = new TableColumn("Direccion");
        direccionCol.setCellValueFactory(new PropertyValueFactory("direccionLote"));

        //Seteamos los valores del listado
        tablaLotes.setItems(data);
        tablaLotes.getColumns().addAll(idCol, productorCol, nombreCol, direccionCol);
    }

    public void listarCuadros() {
        controladoraCuadro = new ControladoraCuadro();

        //Limpiamos los items y columnas para asegurarnos
        tablaCuadros.getItems().clear();
        tablaCuadros.getColumns().clear();

        //Obtenemos el listado de elementos
        // asociamos los datos
        List<Cuadro> cuadros = this.controladoraCuadro.listarCuadros();
        //Filtramos los cuadros que pertenecen al Lote seleccionado
        List<Cuadro> listaFiltrada = new ArrayList<Cuadro>();
        for (Cuadro cuadro : cuadros) {
            if (cuadro.getLote().getIdLote() == loteGuardado.getIdLote()) {
                listaFiltrada.add(cuadro);
            }
        }

        //Se tiene que pasar el OBSERVABLE a un array
        ObservableList<Cuadro> data = FXCollections.observableArrayList(listaFiltrada);

        //Seteamos los valores de las columnas
        TableColumn idCol = new TableColumn("ID");
        idCol.setCellValueFactory(new PropertyValueFactory("idCuadro"));

        TableColumn nombreCol = new TableColumn("Nombre");
        nombreCol.setCellValueFactory(new PropertyValueFactory("nombreCuadro"));

        //Seteamos los valores del listado
        tablaCuadros.setItems(data);
        tablaCuadros.getColumns().addAll(idCol, nombreCol);
    }

    public void limpiarLote() {
        cmbProductor.getItems().clear();
        listarProductores();
        txtNombreLote.setText("");
        txtDireccionLote.setText("");
    }

    public void limpiarCuadro() {
        txtNombreCuadro.setText("");
    }

    private boolean chequearLote() {
        var chequearLote = true;
        // Cargo en variables los datos obtenidos de la interfaz       
        Productor productor = cmbProductor.getValue();
        String nombre = txtNombreLote.getText();
        String direccion = txtDireccionLote.getText();

        if (cmbProductor.getValue() == null) {
            chequearLote = false;
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Revise el Productor");
            a.show();
            cmbProductor.requestFocus();
        }
        if (nombre.isBlank()) {
            chequearLote = false;
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Revise el nombre del Lote");
            a.show();
            txtNombreLote.requestFocus();
        }
        if (direccion.isBlank()) {
            chequearLote = false;
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Revise la dirección del Lote");
            a.show();
            txtDireccionLote.requestFocus();
        }
        return chequearLote;
    }

    private boolean chequearCuadro() {
        var chequearCuadro = true;
        // Cargo en variables los datos obtenidos de la interfaz       
        Lote lote = loteSeleccionado;
        String nombre = txtNombreCuadro.getText();

        if (lote == null) {
            chequearCuadro = false;
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Revise el Lote, debe hacer click en la tabla Lotes");
            a.show();
        }
        if (nombre.isBlank()) {
            chequearCuadro = false;
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Revise el nombre del Cuadro");
            a.show();
            txtNombreCuadro.requestFocus();
        }
        return chequearCuadro;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listarProductores();
        listarLotes();

        //TABLA LOTES
        //Generamos el menu de contexto CLICK DERECHO
        cmOpcionesLote = new ContextMenu();
        MenuItem miEditarLote = new MenuItem("Editar");
        MenuItem miEliminarLote = new MenuItem("Eliminar");
        cmOpcionesLote.getItems().addAll(miEditarLote, miEliminarLote);

        //Evento EDITAR
        miEditarLote.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tablaLotes.getSelectionModel().getSelectedIndex();
                loteSeleccionado = tablaLotes.getItems().get(index);
                //cmbProductor.setItems(loteSeleccionado.getProductor().getLegajo());
                cmbProductor.setValue(loteSeleccionado.getProductor());
                txtNombreLote.setText(loteSeleccionado.getNombreLote());
                txtDireccionLote.setText(loteSeleccionado.getDireccionLote());
                btnCancelarLote.setDisable(false);
                btnNuevoLote.setDisable(true);
            }
        });

        //Evento ELIMINAR
        miEliminarLote.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tablaLotes.getSelectionModel().getSelectedIndex();
                loteSeleccionado = tablaLotes.getItems().get(index);
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Confirmación");
                a.setHeaderText(null);
                a.setContentText("Estás seguro de eliminar el Lote: " + loteSeleccionado.getNombreLote() + "?");
                a.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = a.showAndWait();
                if (result.get() == ButtonType.OK) {
                    try {
                        controladoraLote = new ControladoraLote();
                        controladoraLote.eliminarLote(loteSeleccionado.getIdLote(),
                                loteSeleccionado.getProductor(), loteSeleccionado.getNombreLote(),
                                loteSeleccionado.getDireccionLote());
                        // create a alert
                        a = new Alert(Alert.AlertType.INFORMATION);
                        a.setTitle("Exito");
                        a.setHeaderText(null);
                        a.setContentText("Lote eliminado correctamente");
                        a.show();
                        limpiarLote();
                        cmbProductor.requestFocus();
                        listarLotes();
                    } catch (Exception ex) {
                        a = new Alert(Alert.AlertType.ERROR);
                        a.setTitle("Error");
                        a.setHeaderText(null);
                        a.setContentText("Ocurrió un error al eliminar el Lote");
                        a.show();
                    }
                }
            }
        });
        tablaLotes.setContextMenu(cmOpcionesLote);

        ///TABLA CUADROS
        //Generamos el menu de contexto CLICK DERECHO
        cmOpcionesCuadro = new ContextMenu();
        MenuItem miEditarCuadro = new MenuItem("Editar");
        MenuItem miEliminarCuadro = new MenuItem("Eliminar");
        cmOpcionesCuadro.getItems().addAll(miEditarCuadro, miEliminarCuadro);

        //EVENTO EDITAR
        miEditarCuadro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tablaCuadros.getSelectionModel().getSelectedIndex();
                cuadroSeleccionado = tablaCuadros.getItems().get(index);
                txtNombreCuadro.setText(cuadroSeleccionado.getNombreCuadro());
                btnCuadroCancelar.setDisable(false);
                btnNuevoCuadro.setDisable(true);
            }
        });

        //EVENTO ELIMINAR
        miEliminarCuadro.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int index = tablaCuadros.getSelectionModel().getSelectedIndex();
                cuadroSeleccionado = tablaCuadros.getItems().get(index);
                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setTitle("Confirmación");
                a.setHeaderText(null);
                a.setContentText("Estás seguro de eliminar el Cuadro: " + cuadroSeleccionado.getNombreCuadro() + "?");
                a.initStyle(StageStyle.UTILITY);
                Optional<ButtonType> result = a.showAndWait();
                if (result.get() == ButtonType.OK) {
                    try {
                        controladoraCuadro = new ControladoraCuadro();
                        controladoraCuadro.eliminarCuadro(cuadroSeleccionado.getIdCuadro(),
                                cuadroSeleccionado.getLote(), cuadroSeleccionado.getNombreCuadro());
                        // create a alert
                        a = new Alert(Alert.AlertType.INFORMATION);
                        a.setTitle("Exito");
                        a.setHeaderText(null);
                        a.setContentText("Cuadro eliminado correctamente");
                        a.show();
                        limpiarCuadro();
                        txtNombreCuadro.requestFocus();
                        txtNombreCuadro.selectAll();
                        listarCuadros();
                    } catch (Exception ex) {
                        a = new Alert(Alert.AlertType.ERROR);
                        a.setTitle("Error");
                        a.setHeaderText(null);
                        a.setContentText("Ocurrió un error al eliminar el Cuadro");
                        a.show();
                    }
                }
            }
        });
        tablaCuadros.setContextMenu(cmOpcionesCuadro);
    }

    @FXML
    void clickTablaLotes(MouseEvent event) {
        int index = tablaLotes.getSelectionModel().getSelectedIndex();
        loteSeleccionado = tablaLotes.getItems().get(index);
        loteGuardado = tablaLotes.getItems().get(index);
        //cmbProductor.setItems(loteSeleccionado.getProductor().getLegajo());
        cmbProductor.setValue(loteSeleccionado.getProductor());
        txtNombreLote.setText(loteSeleccionado.getNombreLote());
        txtDireccionLote.setText(loteSeleccionado.getDireccionLote());
        System.out.println(loteGuardado);
        listarCuadros();
    }

    @FXML
    void btnNuevoCuadroAction(ActionEvent event) {
        limpiarCuadro();
        cuadroSeleccionado = null;
    }

    @FXML
    void btnNuevoLoteAction(ActionEvent event) {
        limpiarLote();
        loteGuardado = null;
        loteSeleccionado = null;
    }

    @FXML
    void btnAgregarLoteAction(ActionEvent event) {
        if (loteSeleccionado == null) {
            if (chequearLote() == true) {
                // Cargo en variables los datos obtenidos de la interfaz       
                Productor productor = cmbProductor.getValue();
                String nombreLote = txtNombreLote.getText();
                String direccionLote = txtDireccionLote.getText();
                try {
                    //Invocamos al metodo de la controladora para dar de alta un nuevo Empleado
                    controladoraLote = new ControladoraLote();
                    controladoraLote.altaLote(productor, nombreLote, direccionLote);

                    // create a alert
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Exito");
                    a.setHeaderText(null);
                    a.setContentText("Lote registrado correctamente");
                    a.show();
                    limpiarLote();
                    tablaLotes.getItems().clear();
                    cmbProductor.requestFocus();
                    listarLotes();
                } catch (Exception ex) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText(null);
                    a.setContentText("Ocurrió un error al registrar el Lote");
                    a.show();
                }
            }
        } else {
            if (chequearLote() == true) {
                Productor productor = cmbProductor.getValue();
                String nombreLote = txtNombreLote.getText();
                String direccionLote = txtDireccionLote.getText();

                try {
                    controladoraLote = new ControladoraLote();
                    controladoraLote.actualizarLote(loteSeleccionado.getIdLote(), productor, nombreLote, direccionLote);
                    // create a alert
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Exito");
                    a.setHeaderText(null);
                    a.setContentText("Lote actualizado correctamente");
                    a.show();
                    limpiarLote();
                    tablaLotes.getItems().clear();
                    cmbProductor.requestFocus();
                    btnCancelarLote.setDisable(true);
                    btnNuevoLote.setDisable(false);
                    listarLotes();

                } catch (Exception ex) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText(null);
                    a.setContentText("Ocurrió un error al registrar el Lote");
                    a.show();
                }
            }
        }

    }

    @FXML
    void btnAgregarCuadroAction(ActionEvent event) {
        if (loteGuardado == null) {
            // create a alert
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Error");
            a.setHeaderText(null);
            a.setContentText("Debe seleccionar un Lote para poder cargar un cuadro");
            a.show();

        } else if (cuadroSeleccionado == null) {
            //Invocamos al metodo de la controladora para dar de alta un nuevo Cuadro

            // Cargo en variables los datos obtenidos de la interfaz       
            String nombreCuadro = txtNombreCuadro.getText();
            try {
                controladoraCuadro = new ControladoraCuadro();
                controladoraCuadro.altaCuadro(loteGuardado, nombreCuadro);
                // create a alert
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setTitle("Exito");
                a.setHeaderText(null);
                a.setContentText("Cuadro registrado correctamente");
                a.show();
                limpiarCuadro();
                listarCuadros();
            } catch (Exception ex) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText(null);
                a.setContentText("Ocurrió un error al registrar el Cuadro");
                a.show();
            }

        } else {
            if (chequearCuadro() == true) {
                String nombreCuadro = txtNombreCuadro.getText();
                try {
                    controladoraCuadro = new ControladoraCuadro();
                    controladoraCuadro.actualizarCuadro(cuadroSeleccionado.getIdCuadro(), loteGuardado, nombreCuadro);
                    // create a alert
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setTitle("Exito");
                    a.setHeaderText(null);
                    a.setContentText("Cuadro actualizado correctamente");
                    a.show();
                    limpiarCuadro();
                    btnCuadroCancelar.setDisable(true);
                    btnNuevoCuadro.setDisable(false);
                    listarCuadros();

                } catch (Exception ex) {
                    Alert a = new Alert(Alert.AlertType.ERROR);
                    a.setTitle("Error");
                    a.setHeaderText(null);
                    a.setContentText("Ocurrió un error al registrar el Cuadro");
                    a.show();
                }
            }
        }
    }

    @FXML
    void btnCancelarCuadroAction(ActionEvent event) {
        limpiarCuadro();
        btnCuadroCancelar.setDisable(true);
        btnNuevoCuadro.setDisable(false);
    }

    @FXML
    void btnCancelarLoteAction(ActionEvent event) {
        limpiarLote();
        cmbProductor.requestFocus();
        btnCancelarLote.setDisable(true);
        btnNuevoLote.setDisable(false);
    }

}
