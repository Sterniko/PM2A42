package de.hawhh.informatik.sml.kino.fachwerte;

import static org.junit.Assert.*;

import org.junit.Test;

public class GeldbetragTest {
	
	@Test
    public void testeKonstruktorenInt()
    {
        Geldbetrag geldbetrag = new Geldbetrag (3000);
        assertEquals(3000, geldbetrag.getGeldbetrag());
    }
	
	@Test
    public void testeKonstruktorenInteger()
    {
        Geldbetrag geldbetrag = new Geldbetrag ("3000");
        assertEquals(3000, geldbetrag.getGeldbetrag());
    }
	
	@Test
    public void testeKonstruktorenString()
    {
        Geldbetrag geldbetrag = new Geldbetrag ("30.00");
        assertEquals(3000, geldbetrag.getGeldbetrag());
    }
	
	@Test
    public void testeAddGeldbetrag()
    {
        Geldbetrag geldbetrag = new Geldbetrag (5000);
        Geldbetrag adder = new Geldbetrag(2000);
        geldbetrag.add(adder);
        assert(geldbetrag.getGeldbetrag() == 7000);
    }
	
	@Test
    public void testeAddInt()
    {
        Geldbetrag geldbetrag = new Geldbetrag (5000);
        int adder = 2000;
        geldbetrag.add(adder);
        assert(geldbetrag.getGeldbetrag() == 7000);
    }
	
	@Test
    public void testeAddInteger()
    {
        Geldbetrag geldbetrag = new Geldbetrag (5000);
        Integer adder = 2000;
        geldbetrag.add(adder);
        assert(geldbetrag.getGeldbetrag() == 7000);
    }
	
	@Test
    public void testeAddString()
    {
        Geldbetrag geldbetrag = new Geldbetrag (5000);
        String adder = "2000";
        geldbetrag.add(adder);
        assert(geldbetrag.getGeldbetrag() == 7000);
    }
	@Test
    public void testeSubGeldbetrag()
    {
        Geldbetrag geldbetrag = new Geldbetrag (5000);
        int subber = 2000;
        geldbetrag.sub(subber);
        assert(geldbetrag.getGeldbetrag() == 3000);
    }
	
	@Test
    public void testeSubInt()
    {
        Geldbetrag geldbetrag = new Geldbetrag (5000);
        int subber = 2000;
        geldbetrag.sub(subber);
        assert(geldbetrag.getGeldbetrag() == 3000);
    }
	
	@Test
    public void testeSubInteger()
    {
        Geldbetrag geldbetrag = new Geldbetrag (5000);
        Integer subber = 2000;
        geldbetrag.sub(subber);
        assert(geldbetrag.getGeldbetrag() == 3000);
    }
	@Test
    public void testeSubString()
    {
        Geldbetrag geldbetrag = new Geldbetrag (5000);
        String subber = "2000";
        geldbetrag.sub(subber);
        assert(geldbetrag.getGeldbetrag() == 3000);
    }
	

	@Test
    public void testeMultiplyInt()
    {
        Geldbetrag geldbetrag = new Geldbetrag (5000);
        int mult = 2000;
        geldbetrag.multiply(mult);
        assert(geldbetrag.getGeldbetrag() == 10000000);
    }


	//@require geldbetrag 1>geldbetrag2
	@Test
	public void testGeldbetragSubtraktionInt() {
		int compareTo = 650;
		
		Geldbetrag geldbetrag1 = new Geldbetrag (2000);
		Geldbetrag geldbetrag2 = new Geldbetrag (1350);
		
		int result = Geldbetrag.geldbetragSubtraktion(geldbetrag1, geldbetrag2).getGeldbetrag();
        assertTrue(result == compareTo);
  
	}
	
	@Test
	public void testGeldbetragSubtraktionString() {
		int compareTo = 650;
            
    	Geldbetrag geldbetrag1 = new Geldbetrag ("20.00");
    	Geldbetrag geldbetrag2 = new Geldbetrag ("13.50");
    
    	int result = Geldbetrag.geldbetragSubtraktion(geldbetrag1, geldbetrag2).getGeldbetrag();
        assertTrue(result == compareTo);
	}
	
	
	@Test
	public void testGeldbetragSubtraktionInteger() {
		int compareTo = 650;
            
        Geldbetrag geldbetrag1 = new Geldbetrag ("2000");
        Geldbetrag geldbetrag2 = new Geldbetrag ("1350");
        		
        int result = Geldbetrag.geldbetragDifferenz(geldbetrag1, geldbetrag2).getGeldbetrag();
         assertTrue(result == compareTo);
	}
	
	//geldbetrag 1>geldbetrag2 nicht zwingend
	@Test
	public void testGeldbetragDifferenzInteger() {
		int compareTo = 650;
            
        Geldbetrag geldbetrag1 = new Geldbetrag ("2000");
        Geldbetrag geldbetrag2 = new Geldbetrag ("1350");
        		
        int result = Geldbetrag.geldbetragSubtraktion(geldbetrag1, geldbetrag2).getGeldbetrag();
         assertTrue(result == compareTo);
	}
	
	@Test
	public void testGeldbetragDifferenzInt() {
		int compareTo = 650;
		
		Geldbetrag geldbetrag1 = new Geldbetrag (2000);
		Geldbetrag geldbetrag2 = new Geldbetrag (1350);
		
		int result = Geldbetrag.geldbetragDifferenz(geldbetrag1, geldbetrag2).getGeldbetrag();
        assertTrue(result == compareTo);
  
	}
	
	@Test
	public void testGeldbetragDifferenzString() {
		int compareTo = 650;
            
    	Geldbetrag geldbetrag1 = new Geldbetrag ("20.00");
    	Geldbetrag geldbetrag2 = new Geldbetrag ("13.50");
    
    	int result = Geldbetrag.geldbetragDifferenz(geldbetrag1, geldbetrag2).getGeldbetrag();
        assertTrue(result == compareTo);
	}
	

	@Test
	public void testGeldbetragAdditionInt() {
		int compareTo = 3350;
		
		Geldbetrag geldbetrag1 = new Geldbetrag (2000);
		Geldbetrag geldbetrag2 = new Geldbetrag (1350);
		
		int result = Geldbetrag.geldbetragAddition(geldbetrag1, geldbetrag2).getGeldbetrag();
        assertTrue(result == compareTo);
  
	}
	
	@Test
	public void testGeldbetragAdditionString() {
		int compareTo = 3350;
            
    	Geldbetrag geldbetrag1 = new Geldbetrag ("20.00");
    	Geldbetrag geldbetrag2 = new Geldbetrag ("13.50");
    
    	int result = Geldbetrag.geldbetragAddition(geldbetrag1, geldbetrag2).getGeldbetrag();
        assertTrue(result == compareTo);
	}
	
	
	@Test
	public void testGeldbetragAdditionInteger() {
		int compareTo = 3350;
            
        Geldbetrag geldbetrag1 = new Geldbetrag ("2000");
        Geldbetrag geldbetrag2 = new Geldbetrag ("1350");
        		
        int result = Geldbetrag.geldbetragAddition(geldbetrag1, geldbetrag2).getGeldbetrag();
         assertTrue(result == compareTo);
	}
	
	@Test
	public void testKonvertireZuStringInt() {
		String gb = Geldbetrag.konvertireZuString(30);	
		Geldbetrag geldbetrag = new Geldbetrag(21);
		geldbetrag.add(gb);
		assertTrue(51 == geldbetrag.getGeldbetrag());
		
		geldbetrag.sub(gb);
		assertTrue(21 == geldbetrag.getGeldbetrag());
	}
	
	@Test
	public void testKonvertireZuStringInteger() {
		
	}
	  
		 @Test
		    public void testEqualsUndHashCode()
		    {
			 	Geldbetrag gb1 = new Geldbetrag (3);
			 	Geldbetrag gb2 = new Geldbetrag (3);
		        assertEquals(gb1, gb2);
		        assertTrue(gb1.hashCode() == gb2.hashCode());

		        Geldbetrag gb3 = new Geldbetrag (21443);
		        assertFalse(gb1.equals(gb3));
		        assertFalse(gb1.hashCode() == gb3.hashCode());

		        Geldbetrag gb4 = new Geldbetrag (32);
		        assertFalse(gb1.equals(gb4));
		        assertFalse(gb1.hashCode() == gb4.hashCode());
		    }


}
