package Datentypen;

public class Hauptspeicher extends Produkt {
    int groesse;
    public String[] tabelleneintraege = { "Name", "Hersteller", "Groesse"};
    final static String PRODUKTTYP = "HAUPTSPEICHER";

    @Override
    public String[] getTabelleneintraege() {
        return tabelleneintraege;
    }

    @Override
    public String produktTyp() {
        return PRODUKTTYP;
    }
}
