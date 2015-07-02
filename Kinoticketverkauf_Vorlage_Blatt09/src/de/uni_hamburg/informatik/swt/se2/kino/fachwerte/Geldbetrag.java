/**
 * 
 */
package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

/**
 * Geldbeträge mit Euro und Cent Anteil
 * 
 * @author Ms X
 *
 */
public class Geldbetrag
{
    private final int _eurocent;

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
     * enthalten. 
     * Z.B. "300" wird als "300,00€" interpretiert, "3,00" als "3,00€"
     * 
     * @param betrag der umzuwandelnde Betrag
     * @return ein Geldbetrag, der dem Eingabewert als Eurobetrag oder entsprich
     * 
     * @require betrag != null
     */
    public static Geldbetrag get(String betrag) //Fehlerbehandlung fehlt
    {
        assert betrag != null : "Vorbedingung verletzt: null";
        int eurocent;
        
        //wenn wir davon ausgehen, dass der String die richtige Form mit oder ohne Komma hat:
        if (betrag.contains(","))
        {
            String[] teile = betrag.split(",");
            eurocent = Integer.getInteger(teile[0])*100;
            eurocent += Integer.getInteger(teile[1]);
        }
        else
        {
            eurocent = Integer.getInteger(betrag)*100;
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
    
    private int getGeldbetrag()
    {
        return _eurocent;
    }
    
    
    /**
     * Erzeugt einen formatierten String mit einem Komma und zwei Nachkommastellen
     * 
     * @return einen String der Form "EE,CC" 
     */
    public String getFormatiertenString()
    {
        String cent;
        if ((_eurocent%100)<10)
        {
            if ((_eurocent%100)==0)
            {
                cent = "00";
            }
            else
            {
                cent = "0" + (_eurocent%100);
            }
        }
        else
        {
            cent = "" + (_eurocent%100);
        }
        
        return "" + (_eurocent/100) + "," + cent;
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
     * @require betrag != null
     */
    public boolean equals(Object o)
    {
        assert o != null : "Vorbedingung verletzt: null";
        
        return (o instanceof Geldbetrag) && ((Geldbetrag)o).getGeldbetrag() == _eurocent;
        
        
    }
    
    
    public int hashcode()
    {
        return _eurocent;
    }
    
}
