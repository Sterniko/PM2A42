package de.hawhh.informatik.sml.kino.fachwerte;

//CONSTRUCTORS
/**
 * Klasse zur Präsentation von int in der Form eines Geldbetrags, der EE,CC
 * entspricht, also EUROBETRAG, CENTBETRAG. Die Repräsentation wird als String
 * dargeboten und bietet zudem grundlegende mathematische Funktionen an
 * 
 * @author Sternke
 *
 */
public class Geldbetrag {
	private int eurocents;

	/**
	 * Konstruktor eines leeren Geldbetrages. Hier wird per default ein "00,00"
	 * erzeugt.
	 * @ensure das Objekt ist benutzbar und repräsentiert "00,00".
	 */
	public Geldbetrag() {
		eurocents = 0;
	}

	/**
	 * Konstruktor eines Geldbetrages mit Hilfe eines Integer-Objektes
	 * @param geldbetrag das Integer Objekt des Geldbetrages
	 * @require der Integer ist nicht null
	 * @require der Wert ist nicht negativ
	 * @ensure der Wert des Geldbetrages entspricht dem des Integers
	 */
	public Geldbetrag(Integer geldbetrag) {
		assert (geldbetrag != null) : "Vorbedingung Verletzt nicht null";
		if(geldbetrag.intValue()<0) throw new NumberFormatException("Negativer Input");
		eurocents = geldbetrag.intValue();
	}

	/**
	 * Konstruktor eines Geldbetrages, der dem Betrag in Eurocents entspricht
	 * @param eurocents
	 * @require der Geldbetrag ist nicht negativ
	 * @ensure ein Objekt, das dem Geldbetrag entspricht
	 */
	public Geldbetrag(int eurocents) {
		assert (eurocents >= 0) : "Vorbedingung verletzt geldbetrag nicht negativ";
		if(eurocents < 0) throw new NumberFormatException("Negativer Input");
		this.eurocents = eurocents;
	}

	/**
	 * Konstruktor um einen Geldbetrag aus einem String zu erzeugen. Der String muss
	 * der Form einer Zahl entsprechen oder einer Kommazahl. Es wird maximal die
	 * zweite Nachkommastelle berücksichtigt.
	 * 
	 * @param geldbetrag String-Repräsenation des Geldbetrages
	 * @require geldbetrag nicht null und geldbetrag hat die geforderte Form.
	 * @ensure ein Geldbetrag-Objekt, welches dem String enspricht
	 */
	public Geldbetrag(String geldbetrag) {
		assert (geldbetrag != null) : "Vorbedingung verletzt geldbetrag nicht null";
		try {
			Integer i = Integer.parseInt(geldbetrag);
			eurocents = i.intValue();
		} catch (NumberFormatException e) {
			try {
				geldbetrag = geldbetrag.replaceAll(",", ".");
				Double d = Double.parseDouble(geldbetrag);
				d = d * 100;
				if(d<0) throw new NumberFormatException("Negativer Input");
				eurocents = d.intValue();
				
			} catch (NumberFormatException ex) {				
				assert (false) : "Vorbedingung verletzt -> Geldbetrag entspricht nicht Format";
				throw ex;
			}
		}
	}

	

	// MAIN STUFF
	/**
	 * String repräsentation des Geldbetrages in der Form "EE..,CC".
	 * @return ein String, der dem Geldbetrag entspricht
	 */
	public String getStringrepräsentation() {
		return getStringRepräsentation(this.eurocents);
	}

	private static String getStringRepräsentation(int eurocents) {
		char[] cs = Integer.valueOf(eurocents).toString().toCharArray();
		String CC = "";
		String EE = "";
		// "normale länge : letzte 2 einträge
		// sind cent, der rest euro
		if (cs.length >= 3) {
			CC += cs[cs.length - 2];
			CC += cs[cs.length - 1];
			for (int i = 0; i < cs.length - 2; i++) {
				EE += cs[i];
			}
			// falls nur zwei einträge vorhanden,
			// sind beide cent
		} else if (cs.length == 2) {
			EE = "00";
			for (int j = 0; j < cs.length; j++) {
				CC += cs[j];
			}
		} else {
			// nur ein Eintrag
			EE = "00";
			CC = "0" + cs[0];
		}

		// zusammenfügen
		return EE + "," + CC;
	}

	// MATHS

	// OBJECT MATH-METHODS

	/**
	 * addiert einen Geldbetrag auf diesen auf
	 * @param der Geldbetrag, der auf diesen addiert werden soll
	 * @require betrag nicht null
	 * @require betrag ist valide
	 * @ensure der Wert des Geldbetrages ist gleich old + betrag
	 */
	public void add(Geldbetrag betrag) {
		assert (betrag != null) : "Vorbedingung verletzt betrag nicht null";
		assert (betrag.getValidity()) : "Vorbedingung verletzt betrag nicht valide";
		add(betrag.getGeldbetrag());
	}

	/**
	 * addiert eine Anzal von int auf diesen Geldbetrag auf. Dies symbolisiert
	 * Cents, z.b. würde 14 einem Geldbetrag von 14 cent, bzw dem Betrag "00,14"
	 * entsprechen.
	 * @param der Geldbetrag, der auf diesen addiert werden soll
	 * @require betrag ist größer gleich 0;
	 * @ensure der Wert des Geldbetrages ist gleich old + betrag
	 */
	public void add(int betrag) {
		assert (betrag >= 0) : "Vorbedingung verletzt betrag kleiner als 0";
		eurocents += betrag;
	}

	/**
	 * addiert ein Integer Objekt auf diesen Geldbetrag auf. Diese Methode entspricht add(int). De symbolisiert
	 * Cents, z.b. würde 14 einem Geldbetrag von 14 cent, bzw dem Betrag "00,14"
	 * entsprechen.
	 * @param der Geldbetrag, der auf diesen addiert werden soll
	 * @require betrag nicht null
	 * @require betrag ist größer gleich 0;
	 * @ensure der Wert des Geldbetrages ist gleich old + betrag
	 */
	public void add(Integer betrag) {
		assert (betrag != null) : "Vorbedingung verletzt betrag nicht null";
		add(betrag.intValue());
	}

	/**
	 * erlaubt es, einen String, der einen Geldbetrag repräsentiert auf diesen Geldbertag zu addieren. Der String muss allerdings der Form "EE,CC",
	 * "EE.CC" oder "CCCC" entsprechen um gültig zu sein
	 * @param geldbetrag String repräsentation des zu addierenden Geldbetrages
	 * @require geldbetrag nicht null
	 * @require die Form des geldbetrages entspricht der geforderten
	 * @ensure der Wert von diesem entspricht old + betrag
	 */
	public void add(String geldbetrag) {
		Geldbetrag addBetrag = new Geldbetrag(geldbetrag);
		add(addBetrag);
	}

	/**
	 * Zieht einen Geldbetrag von diesem ab
	 * @param geldbetrag der abzuziehende Betrag
	 * @require geldbetrag nicht null
	 * @require geldbetrag ist valide
	 * @require geldbetrag nicht größer als dieser
	 * @ensure Wert von diesem entspricht old - geldbetrag
	 */
	public void sub(Geldbetrag geldbetrag) {
		assert (geldbetrag != null) : "Vorbedingung verletzt geldbetrag nicht null";
		assert (geldbetrag.getValidity()) : "Vorbedingung verletzt geldbetrag nicht valide";
		assert (geldbetrag.getGeldbetrag() >= eurocents) : "Vorbedingung verletzt geldbetrag größer als dieser";
		sub(geldbetrag.getGeldbetrag());
	}

	/**
	 * Zieht einen Geldbetrag von diesem in der Form int entspricht Eurocent ab
	 *  z.b. würde 14 einem Geldbetrag von 14 cent, bzw dem Betrag "00,14" entsprechen
	 * @param betrag der Betrag in Eurocent der abzuziehen ist
	 * @require betrag nicht negativ
	 * @require betrag nicht größer als dieser
	 * @ensure Wert von diesem entspricht old - betrag
	 */
	public void sub(int betrag) {
		assert (betrag >= 0) : "Vorbedingung verletzt betrag nicht negativ";
		assert (betrag <= eurocents) : "Vorbedingung verletzt betrag größer als dieser";
		eurocents -= betrag;
	}

	/**
	 * Zieht einen Geldbetrag von diesem in der Form Integer Wert entspricht Eurocent ab
	 *  z.b. würde 14 einem Geldbetrag von 14 cent, bzw dem Betrag "00,14" entsprechen
	 * @param betrag der Betrag in Eurocent der abzuziehen ist
	 * @require Betrag nicht null
	 * @require betrag nicht negativ
	 * @require betrag nicht größer als dieser
	 * @ensure Wert von diesem entspricht old - betrag
	 */
	public void sub(Integer betrag) {
		assert (betrag != null) : "Vorbedingung verletzt betrag nicht null";
		sub(betrag.intValue());
	}

	/**
	 * Zieht einen Wert in der Form eines Strings.
	 * Der String muss allerdings der Form "EE,CC", "EE.CC" oder "CCCC" entsprechen,
	 * um gültig zu sein
	 * @param geldbetrag der abzuziehende Betrag
	 * @require geldbetrag nicht null
	 * @require die Form des Geldbetrages entspricht der geforderten
	 * @require geldbetrag nicht größer als dieser
	 * @ensure Wert von diesem entspricht old - geldbetrag
	 */
	public void sub(String geldbetrag) {
		assert (geldbetrag != null) : "Vorbedingung verletzt geldbetrag nicht null";
		Geldbetrag sub = new Geldbetrag(geldbetrag);
		sub(sub.getGeldbetrag());
	}

	/**
	 * multipliziert den Betrag von diesem mit einer zahl
	 * @param multiplier die zahl zum multiplizieren
	 * @require die zahl ist größer als 0
	 *@ensure die zahl entspricht dem Wert mal multiplier,achtung bei zu großen zahlen 
	 */
	public void multiply(int multiplier) {
		assert (multiplier > 0) : "Vordbedingung verletzt multiplier zu klein";
		eurocents *= multiplier;
	}

	// CLASS MATH-METHODS
	/**
	 * Gibt ein neues Geldbetragsobjekt zurück, welches dem ersten Geldbetrag minus
	 * dem zweiten entspicht. Diese Methode entspricht dem mathematischen a-b.
	 * 
	 * @param a der Geldbetrag, von dem Abgezogen werden soll
	 * @param b der Geldbetrag, der abzuziehen ist
	 * @return Ein neues Geldbetragsobjekt, welches a-b enspricht
	 * @require a nicht null
	 * @require a ist valide, also nicht negativ
	 * @require b nicht null
	 * @require b ist valide, also nicht negativ
	 * @ensure der Betrag enspricht a - b;
	 */
	public static Geldbetrag geldbetragSubtraktion(Geldbetrag a, Geldbetrag b) {
		assert (a != null) : "Vorbedingung verletzt a nicht null";
		assert (b != null) : "Vorbedingung verletzt b nicht null";
		assert (a.getValidity()) : "Vorbedingung verletzt a nicht valide";
		assert (b.getValidity()) : "Vorbedingung verletzt b nicht valide";
		assert (a.getGeldbetrag() >= b.getGeldbetrag()) : "Vorbedingung verletzt a größer b";
		return new Geldbetrag(a.getGeldbetrag() - b.getGeldbetrag());
	}

	/**
	 * Gibt die positive Differenz zweier Geldbeträge in der Form eines Geldbetrages
	 * zurück, entspricht dem mathematischen |a-b|
	 * @param a der erste Geldbetrag
	 * @param b der zweite Geldbetrag
	 * @return ein neuer geldbetrag, welcher der Differenz beider Beträge entspricht
	 * @require a ist nicht null
	 * @require a ist valide
	 * @require b ist nicht null
	 * @require b ist valide
	 * @ensure ein neues Geldbetragsobjekt, das der Differenz entspricht
	 */
	public static Geldbetrag geldbetragDifferenz(Geldbetrag a, Geldbetrag b) {
		int neuerBetrag;
		if (a.getGeldbetrag() > b.getGeldbetrag()) {
			neuerBetrag = a.getGeldbetrag() - b.getGeldbetrag();
		} else {
			neuerBetrag = b.getGeldbetrag() - a.getGeldbetrag();
		}
		return new Geldbetrag(neuerBetrag);
	}

	/**
	 * Ermöglicht es zwei Geldbeträge aufeinander zu addieren
	 * @param a der erste Geldbetrag
	 * @param b der zweite Geldbetrag
	 * @return ein Geldbetragobjekt, welches dem Wert von a+b entspricht
	 * @require a nicht null
	 * @require a ist valide
	 * @require b nicht null
	 * @require b ist valide
	 * @ensure das Objekt enspricht a+b
	 */
	public static Geldbetrag geldbetragAddition(Geldbetrag a, Geldbetrag b) {
		assert (a != null) : "Vorbedingung verletzt a nicht null";
		assert (b != null) : "Vorbedingung verletzt b nicht null";
		assert (a.getValidity()) : "Vorbedingung verletzt a nicht valide";
		assert (b.getValidity()) : "Vorbedingung verletzt b nicht valide";
		return new Geldbetrag(a.getGeldbetrag() + b.getGeldbetrag());
	}

	/**
	 * Multipliziert einen gegeben Geldbetrag um eine gegebene Zahl und gibt einen neuen Geldbetrag zurück
	 * @param a der Geldbetrag zum multiplizieren
	 * @param multiplier die Zahl, mit der multipliziert wird
	 * @return ein neuer Geldbetrag, der a*multiplier entspricht
	 * @require a nicht null
	 * @require a ist valide
	 * @require multiplier nicht negativ
	 *@ensure ein gültiger Geldbetrag der a*multiplier entspricht
	 */
	public static Geldbetrag geldbetragMultiply(Geldbetrag a, int multiplier) {
		assert(a != null): "Vorbedingung verletzt a nicht null";
		assert(multiplier>=0): "Vorbedingung verletzt multiplier nicht negativ";
		return new Geldbetrag(a.getGeldbetrag()*multiplier);
	}

	// UTILITY
	/**
	 * Erlaubt das Ändern des Wertes des Geldbetrages
	 * @param geldbetrag der neue Geldbetrag
	 * @require der Geldbetrag darf nicht negativ sein
	 * @ensure der neue Wert entspricht dem gegebenen
	 */
	public void setGeldbetrag(int geldbetrag) {
		assert (geldbetrag >= 0) : "Vorbedingung verletzt geldbetrag nicht negativ";
		this.eurocents = geldbetrag;
	}

	/**
	 * Gibt den momentanen Geldbetrag zurück
	 * @return den Wert des Geldbetrages in Eurocents
	 */
	public int getGeldbetrag() {
		return eurocents;
	}

	/**
	 * Gibt an, ob der Geldbetrag valide ist, also nicht negativ
	 * @return false wenn der Geldbetrag kleiner ist, als 0. Sonst true
	 */
	public boolean getValidity() {
		return eurocents >= 0;
	}

	/**
	 * Konvertiert ein Integer Objekt zu einem String, welcher der Geldbetrag-Form entspricht
	 * @param betrag das Integer Objekt zum konvertieren
	 * @require betrag nicht null
	 * @require betrag nicht negativ 
	 * @return ein String der der Gelddbetrag-Form entspricht mit dem gewünschten Betrag
	 * @ensure String genügt der Geldbetrag-Form
	 * @ensure String hat gleichen Wert wie der Input-Betrag
	 */
	public static String konvertireZuString(Integer betrag) {
		assert (betrag != null) : "Vorbedingung verletzt betrag nicht null";
		return konvertiereZuString(betrag.intValue());
	}

	/**
	 * Konvertiert einnen int-Wert in einen String, der der Geldbetrag-Form entspricht
	 * @param betrag der dargestellt werden soll
	 * @return String in der Geldbetrag-Form
	 * @require betrag nicht negativ
	 * @ensure String genügt der Geldbetrag-Form
	 * @ensure String hat gleichen Wert wie der Input-Betrag
	 */
	public static String konvertiereZuString(int betrag) {
		assert (betrag >= 0) : "Vorbedingung verletzt betrag zu klein";
		return getStringRepräsentation(betrag);
	}

	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (this == other)
			return true;
		if (!(other instanceof Geldbetrag))
			return false;
		Geldbetrag betrag = (Geldbetrag) other;
		return betrag.eurocents == eurocents;
	}

	@Override
	public String toString() {
		return "Geldbetrag Eurocents: " + eurocents + " präsentation: " + getStringRepräsentation(eurocents);
	}

	@Override
	public int hashCode() {
		return getStringRepräsentation(eurocents).hashCode();
	}
}
