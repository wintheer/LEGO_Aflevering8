
/*
 * Cruise behavior, p. 303 in
 * Jones, Flynn, and Seiger: 
 * "Mobile Robots, Inspiration to Implementation", 
 * Second Edition, 1999.
 */

class Cruise extends Thread
{
    private SharedCar car;
    private int power1 = 70, power2 = 71;
    
    public Cruise(SharedCar car)
    {
    	this.car = car;
    }
    
	public void run() 
    {				       
        while (true)
        {
        	/*  Drive forward */
			car.forward(power1, power2); 
        }
    }
}
	

