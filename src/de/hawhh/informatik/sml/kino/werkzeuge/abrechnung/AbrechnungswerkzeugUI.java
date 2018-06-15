package de.hawhh.informatik.sml.kino.werkzeuge.abrechnung;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

class AbrechnungswerkzeugUI {

	private Label _preisLabel;
	private Button _abschlussButton;
	private Button _abbrechenButton;
	private Button _bezahlenButton;
	private Pane _hauptPanel;
	private TextField _textField;
	private final Stage _primaryStage;
	private boolean _properly;

	/**
	 * Initialisiert die UI.
	 */
	AbrechnungswerkzeugUI() {
		_abschlussButton = new Button("Kauf Abschliessen");
		_bezahlenButton = new Button("Bezahlen");
		_abbrechenButton = new Button("Kauf Abbrechen");	
		_preisLabel = new Label();
		_textField = new TextField();
		_hauptPanel = init();
		_primaryStage = new Stage();
		_properly = true;

	}

	private Pane init() {

		BorderPane hauptPane = new BorderPane();

		hauptPane.setTop(_preisLabel);
		hauptPane.getTop().resize(200, 20);

		FlowPane bezahlPane = initFlowPane(_textField, _bezahlenButton);
		FlowPane buttonPane = initFlowPane(_abschlussButton, _abbrechenButton);

		hauptPane.setCenter(bezahlPane);
		hauptPane.setBottom(buttonPane);
		return hauptPane;
	}

	Label get_preisLabel() {
		return _preisLabel;
	}

	void set_preisLabel(Label _preisLabel) {
		this._preisLabel = _preisLabel;
	}

	Button get_abschlussButton() {
		return _abschlussButton;
	}

	void set_abschlussButton(Button _abschlussButton) {
		this._abschlussButton = _abschlussButton;
	}

	Button get_abbrechenButton() {
		return _abbrechenButton;
	}

	void set_abbrechenButton(Button _abbrechenButton) {
		this._abbrechenButton = _abbrechenButton;
	}

	Button get_bezahlenButton() {
		return _bezahlenButton;
	}

	void set_bezahlenButton(Button _bezahlenButton) {
		this._bezahlenButton = _bezahlenButton;
	}

	Pane get_hauptPanel() {
		return _hauptPanel;
	}

	void set_hauptPanel(Pane _hauptPanel) {
		this._hauptPanel = _hauptPanel;
	}

	TextField get_textField() {
		return _textField;
	}

	void set_textField(TextField _textField) {
		this._textField = _textField;
	}

	public Stage get_primaryStage() {
		return _primaryStage;
	}

	void zeigeAn() {		
		Scene scene = new Scene(_hauptPanel, 250, 250);
		_primaryStage.setOnCloseRequest(evt -> {
		        // prevent window from closing
		        evt.consume();
		        showAreYouSureWindow();
		});
		_primaryStage.setScene(scene);		
		_primaryStage.initModality(Modality.APPLICATION_MODAL);
		_primaryStage.showAndWait();

	}

	private FlowPane initFlowPane(Node... args) {
		FlowPane ret = new FlowPane();
		ret.setAlignment(Pos.CENTER_RIGHT);
		ret.setHgap(10);
		ret.setPadding(new Insets(10));
		ret.getChildren().addAll(args);
		return ret;
	}

	void showErrorWindowWrongInput() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Ungülgtige Eingabe");
		alert.setHeaderText(null);
		alert.setContentText("Bitte nur positive Zahlen eingeben!");
		alert.showAndWait();	
	

	}

	 void showMoreMoneyWindow() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Nicht genug gezahlt");
		alert.setHeaderText(null);
		alert.setContentText("Der Kunde muss noch mehr Geld bezahlen!");
		alert.showAndWait();		
		
	}

	 void showFinishedWindow() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Wechselgeld");
		alert.setHeaderText(null);
		alert.setContentText("Bitte nicht das Wechselgeld vergessen!");
		alert.showAndWait();		
		_primaryStage.close();	
	

	}

	 void showAreYouSureWindow() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Abbrechen");
		alert.setHeaderText(null);
		alert.setContentText(
				"Sicher, die Transaktion abzubrechen? Der Kunde muss sein bisher gezahltes Geld zurückbekommen!");
		System.out.println(alert.getButtonTypes().get(1).getText());
		Optional<ButtonType> result = alert.showAndWait();	
		if( result.get() == ButtonType.OK) {
			_properly = false;
			close();
		}
	}

	void close() {
		this._primaryStage.close();
	}
	
	public boolean properly() {
		return _properly;
	}

	

}
