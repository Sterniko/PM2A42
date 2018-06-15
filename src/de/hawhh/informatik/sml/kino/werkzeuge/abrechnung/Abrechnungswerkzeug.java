package de.hawhh.informatik.sml.kino.werkzeuge.abrechnung;

import de.hawhh.informatik.sml.kino.fachwerte.Geldbetrag;
import de.hawhh.informatik.sml.kino.werkzeuge.ObservableSubwerkzeug;
import de.hawhh.informatik.sml.kino.werkzeuge.abrechnung.AbrechnungswerkzeugUI;
import javafx.scene.control.Button;

public class Abrechnungswerkzeug extends ObservableSubwerkzeug {
	private Geldbetrag _preis;
	private AbrechnungswerkzeugUI _uiVerk;
	private boolean payed;

	public Abrechnungswerkzeug() {
		payed = false;
		_uiVerk = new AbrechnungswerkzeugUI();
	}

	public void aktivieren() {
		_uiVerk.get_preisLabel().setText(preisTextPos(_preis));
		gibFunktion();
		_uiVerk.zeigeAn();
	}

	public Geldbetrag get_preis() {
		return _preis;
	}

	public void set_preis(Geldbetrag _preis) {
		this._preis = _preis;
	}

	public AbrechnungswerkzeugUI get_uiVerk() {
		return _uiVerk;
	}

	public void set_uiVerk(AbrechnungswerkzeugUI _uiVerk) {
		this._uiVerk = _uiVerk;
	}

	private void gibFunktion() {
		Button b = _uiVerk.get_abschlussButton();

		b.setOnAction(e -> {
			System.out.println("You clicked the Abschluss-button");
			if (hasPaidEnough()) {
				_uiVerk.showFinishedWindow();
			} else {
				_uiVerk.showMoreMoneyWindow();
			}
		});

		_uiVerk.get_abbrechenButton().setOnAction(e -> {
			System.out.println("You clicked the Abbrechen-button");
			_uiVerk.showAreYouSureWindow();
		});

		_uiVerk.get_bezahlenButton().setOnAction(e -> {
			System.out.println("You clicked the bezahlen-button");
			calculateChange();
			_uiVerk.get_textField().clear();
		});

	}

	private void calculateChange() {
		System.out.println(_uiVerk.get_textField().getText());
		try {
			Geldbetrag payment = new Geldbetrag(_uiVerk.get_textField().getText());

			System.out.println(payment);
			if (payment.getValidity()) {
				if (payment.getGeldbetrag() >= _preis.getGeldbetrag()) {

					Geldbetrag diff = Geldbetrag.geldbetragDifferenz(_preis, payment);
					_uiVerk.get_preisLabel().setText(preisTextNeg(diff));
					_uiVerk.get_bezahlenButton().setDisable(true);
					_preis = diff;
					payed = true;

				} else {
					_preis.sub(payment);
					_uiVerk.get_preisLabel().setText(preisTextPos(_preis));
				}
			} else {
				_uiVerk.showErrorWindowWrongInput();
			}
		} catch (NumberFormatException e) {
			_uiVerk.showErrorWindowWrongInput();
		}
	}

	private String preisTextPos(Geldbetrag preis) {
		return new String("Der Preis beträgt: " + preis.getStringrepräsentation() + " Euro");
	}

	private String preisTextNeg(Geldbetrag diff) {
		return new String("Das Wechselgeld beträgt: " + diff.getStringrepräsentation() + " Euro");
	}

	private boolean hasPaidEnough() {
		return payed;
	}

	public boolean properly() {
		return _uiVerk.properly();
	}

}
