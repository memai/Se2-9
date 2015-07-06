package de.uni_hamburg.informatik.swt.se2.kino.fachwerte;

import static org.junit.Assert.*;


import org.junit.Test;

public class GeldbetragTest
{
    Geldbetrag _betrag1;
    Geldbetrag _betrag2;
    
    public GeldbetragTest()
    {
        _betrag1 = Geldbetrag.get(100);
        _betrag2 = Geldbetrag.get(250);
        
    }
    
    @Test
    public void testEquals()
    {
        assertTrue(_betrag1.equals(Geldbetrag.get(100)));
        assertTrue(_betrag1.equals(_betrag1));
        
        assertFalse(_betrag1.equals(_betrag2));
    }
    
    @Test
    public void testGetString() 
    {
        Geldbetrag betrag = Geldbetrag.get(99); 
        String fail = "";
        try 
        {
            betrag = Geldbetrag.parseString("100,"); 
        }
        catch (StringToGeldbetragException e)
        {
            System.out.println("Falscher String 100,");
        }
        assertEquals(betrag, Geldbetrag.get(10000)); 
        
        try 
        {
            betrag = Geldbetrag.parseString("100"); 
        }
        catch (StringToGeldbetragException e)
        {
            System.out.println("Falscher String 100");
        }
        assertFalse(betrag.equals(Geldbetrag.get(99)));

        assertEquals(betrag, Geldbetrag.get(100));
        try 
        {
            betrag = Geldbetrag.parseString("100fg"); 
        }
        catch (StringToGeldbetragException e)
        {
            fail = "Falscher String 100fg";
        }
        assertEquals(fail, "Falscher String 100fg"); 

        try 
        {
            betrag = Geldbetrag.parseString("100,23"); 
        }
        catch (StringToGeldbetragException e)
        {
            System.out.println("Falscher String 100,23");
        }
        assertEquals(betrag, Geldbetrag.get(10023)); 

        try 
        {
            betrag = Geldbetrag.parseString("10000000"); 
        }
        catch (StringToGeldbetragException e)
        {
            fail = "zu viele Vorkommastellen";
        }
        assertEquals(fail, "zu viele Vorkommastellen"); 
        
        try 
        {
            betrag = Geldbetrag.parseString("5,235"); 
        }
        catch (StringToGeldbetragException e)
        {
            fail = "zu viele Nachkommastellen";
        }
        assertEquals(fail, "zu viele Nachkommastellen"); 
        
        
    }
    
    @Test(expected = StringToGeldbetragException.class)
    public void testeUngueltigerString() throws StringToGeldbetragException
    {
        Geldbetrag.parseString("5,235"); 
    }

    
    
    @Test
    public void testGetFormatiertenString()
    {
        assertEquals(_betrag1.getFormatiertenString(), "1,00");
        assertEquals(_betrag2.getFormatiertenString(), "2,50");
    }

    @Test
    public void testAddiere()
    {
        assertEquals(_betrag1.addiere(_betrag2), Geldbetrag.get(350));
        assertEquals(_betrag1.addiere(_betrag1), Geldbetrag.get(200));
        assertEquals(_betrag2.addiere(_betrag2), Geldbetrag.get(500));
    }

    @Test
    public void testSubtrahiere()
    {
        assertEquals(_betrag2.subtrahiere(_betrag2), Geldbetrag.get(0));
        assertEquals(_betrag2.subtrahiere(_betrag1), Geldbetrag.get(150));
    }

    @Test
    public void testBerechneVielfaches()
    {
        assertEquals(_betrag1.berechneVielfaches(5), Geldbetrag.get(500));
        assertEquals(_betrag2.berechneVielfaches(2), Geldbetrag.get(500));
        //expected -Test 
    }
    
    @Test
    public void testIstGroesserAls()
    {
        assertTrue(_betrag2.istGroesserAls(_betrag1));
        assertFalse(_betrag1.istGroesserAls(_betrag1));
        assertFalse(_betrag1.istGroesserAls(_betrag2));
    }



}
