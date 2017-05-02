
import lejos.nxt.*;
import lejos.util.Delay;
/*
 * Escape behavior
 */

class Escape extends Thread
{
    private SharedCar car = new SharedCar();

	private int power = 70, ms = 500;
	TouchSensor left = new TouchSensor(SensorPort.S1);
	TouchSensor right= new TouchSensor(SensorPort.S2);

    public Escape(SharedCar car)
    {
       this.car = car;		    	
    }
    
	public void run() 
    {				       
        while (true)
        {
	    	// Check if any sensors are pressed
	    	// and react accordingly
        	boolean leftPressed = left.isPressed();
        	boolean rightPressed = right.isPressed();
        	
        	if (leftPressed == false && rightPressed == false  )
	    	{
	    		car.noCommand();
	    	}
        	
        	//if there's an obstacle in front, back up and turn left
        	if (leftPressed && rightPressed){
        		car.backward(power, power);
    	    	Delay.msDelay(ms);
    	    	car.forward(0, power);
    	    	Delay.msDelay(ms);
        	}
        	
        	//if to the left, turn right
        	else if (leftPressed){
        		car.forward(power, 0);
    	    	Delay.msDelay(ms);
        	}
        	//and vice versa
        	else if (rightPressed){
        		car.forward(0, power);
    	    	Delay.msDelay(ms);
        	}
        	
        }
    }
}

