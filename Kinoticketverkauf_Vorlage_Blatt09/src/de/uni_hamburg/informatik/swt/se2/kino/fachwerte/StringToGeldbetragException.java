package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

/**
 * Eine GeldbetragToStringException signalisiert, dass das Umwandeln eines
 * eingegebenen Strings in einen Geldbetrag fehlgeschlagen ist.
 * 
 * @author SE2-Team
 * @version SoSe 2015
 */
public class StringToGeldbetragException extends Exception
{

    private static final long serialVersionUID = 1L;

    /**
     * Initilaisert eine neue Exception mit der übergebenen Fehlermeldung.
     * 
     * @param message Eine Fehlerbeschreibung.
     */
    public StringToGeldbetragException(String message)
    {
        super(message);
    }
}
