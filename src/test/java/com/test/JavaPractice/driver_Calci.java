package Scenarios4;

import org.testng.annotations.Test;


public class driver_Calci {
	
	@Test
	public void testCalculator()
	{
	Simple_Calculator c1= new Simple_Calculator();
	
	System.out.println(c1.Add(20, 10));
	System.out.println(c1.Sub(20, 10));
	System.out.println(c1.ktog(10));
	
	
	Scientific_Calculator c2= new Scientific_Calculator();
	
	System.out.println(c2.Add(20, 10));
	System.out.println(c2.Sub(20, 10));
	System.out.println(c2.pow(2, 3));
	
	}
	

	

}
