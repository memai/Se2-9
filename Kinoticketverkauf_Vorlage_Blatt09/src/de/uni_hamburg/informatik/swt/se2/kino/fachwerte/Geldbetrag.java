/**
 * 
 */
package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import java.util.regex.Pattern;

/**
 * Geldbeträge mit Euro und Cent Anteil
 * 
 * @author Ms X
 *
 */
public class Geldbetrag
{
    private final int _eurocent;
    private String _formatierterString;

    /**
     * Gibt einen neuen Geldbetrag aus einem Integerwert zurück. 
     * Der Eingabewert wird als centbetrag interpretier (z.B. get(300) -> 3,00€)
     * 
     * @param eurocent ein ganzzahliger positiver Wert
     * @return ein Geldbetrag, der dem Eingabewert als centbetrag entsprich
     * 
     * @require eurocent >= 0
     */
    public static Geldbetrag get(int eurocent)
    {
        assert eurocent >= 0 : "Vorbedingung verletzt: Wert kleiner als Null";

        return new Geldbetrag(eurocent);
    }

    /**
     * Gibt einen neuen Geldbetrag aus einem String zurueck.
     * Der String muss entweder aus Zahlen bestehen oder ein Komma und bis zu zwei Nachkommastellen
     * enthalten. Mit Komma 5 Vorkommastellen moeglich
     * Z.B. "300" wird als "3,00€" interpretiert, 
     * "3,00" als "3,00€"
     * "3," als "3,00€"
     * 
     * @param betrag der umzuwandelnde Betrag
     * @return ein Geldbetrag, der dem Eingabewert enspricht (ohne Komma als Cent!)
     * 
     * @require betrag != null
     */
    public static Geldbetrag parseString(String betrag)
            throws StringToGeldbetragException
    {
        assert betrag != null : "Vorbedingung verletzt: null";
        int eurocent;

        //regulärer ausdruck, der bis zu 7 Vorkomma- und  
        //und bis zu 2 Nachkommastellen erlaubt; insgesamt aber nicht mehr als 7 Ziffern
        //http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_04_007.htm#mj26fc5cf60311afbddd72295cdd646a48
        if (!Pattern.matches("[0-9]{1,5}[,]?[0-9]?[0-9]?", betrag))
        {
            throw new StringToGeldbetragException("Err");
        }

        if (betrag.contains(","))
        {
            String[] teile = betrag.split(",");

            eurocent = Integer.parseInt(teile[0]) * 100;
            if (teile.length == 2)
            {
                eurocent += Integer.parseInt(teile[1]);
            }
        }
        else
        {
            eurocent = Integer.parseInt(betrag);
        }
        return new Geldbetrag(eurocent);
    }

    /**
     * Erzeugt einen Geldbetrag, wobei der übergebene Integer wert als Eurocent 
     * interpretiert wird. (300 -> 3,00€ etc.)
     * 
     * @param eurocent der gewuenschte Wert in Eurocent
     * 
     * @require eurocent >= null
     */
    private Geldbetrag(int eurocent)
    {
        assert eurocent >= 0 : "Vorbedingung verletzt: Wert negativ";

        _eurocent = eurocent;
    }

    /**
     * Gibt den Geldbetrag in Eurocent zurueck (fuer Berechnungen etc.)
     * 
     * @return den Geldbetrag als Integer.
     */
    private int getGeldbetrag()
    {
        return _eurocent;
    }

    /**
     * Gibt einen formatierten String mit einem Komma und zwei Nachkommastellen zurueck
     * 
     * @return einen String der Form "EE,CC" 
     */
    public String getFormatiertenString()
    {
        if (_formatierterString == null)
        {
            erzeugeFormatiertenString();
        }
        return _formatierterString;
    }

    /**
     * Erzeugt einen formatierten String mit einem Komma und zwei Nachkommastellen
     */
    private void erzeugeFormatiertenString()
    {
        String cent;
        if ((_eurocent % 100) < 10)
        {
            if ((_eurocent % 100) == 0)
            {
                cent = "00";
            }
            else
            {
                cent = "0" + (_eurocent % 100);
            }
        }
        else
        {
            cent = "" + (_eurocent % 100);
        }

        _formatierterString = "" + (_eurocent / 100) + "," + cent;

    }

    /**
     * Addiert einen Geldbetrag zum gegebenen und gibt einen neuen zurueck 
     * 
     * @param betrag
     * @return Geldbetrag summe
     * 
     * @require betrag != null
     */
    public Geldbetrag addiere(Geldbetrag betrag)
    {
        return get(_eurocent + betrag.getGeldbetrag());
    }

    /**
     * Subtrahiert den uebergebenen Geldbetrag vom gegebenen.
     * Da keine negativen Betraege moeglich sind, muss der uebergebene Betrag kleiner sein
     * 
     * @param betrag
     * @return
     * 
     * @require !betrag.istGroesserAls(this)
     */
    public Geldbetrag subtrahiere(Geldbetrag betrag)
    {
        assert !betrag.istGroesserAls(this) : "Vorbedingung verletzt";

        return get(_eurocent - betrag.getGeldbetrag());
    }

    /**
     * Multipliziert den Geldbetrag mit einem ganzzahligen, positiven Faktor.
     * 
     * @param faktor
     * @return Das Produkt als neuer Geldbetrag
     * 
     * @require faktor >= 0
     */
    public Geldbetrag berechneVielfaches(int faktor)
    {
        assert faktor >= 0 : "Vorbedingung verletzt";

        return get(_eurocent * faktor);
    }

    /**
     * 
     * @param betrag
     * @return
     * 
     * @require betrag != null
     */
    public boolean istGroesserAls(Geldbetrag betrag)
    {
        assert betrag != null : "Vorbedingung verletzt: null";
        return _eurocent > betrag.getGeldbetrag();
    }

    /**
     * @require o != null
     */
    public boolean equals(Object o)
    {
        assert o != null : "Vorbedingung verletzt: null";

        return (o instanceof Geldbetrag)
                && ((Geldbetrag) o).getGeldbetrag() == _eurocent;

    }

    public int hashcode()
    {
        return _eurocent;
    }

}
