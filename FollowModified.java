
import lejos.nxt.*;
import lejos.util.Delay;
/*
 * Follow behavior , inspired by p. 304 in
 * Jones, Flynn, and Seiger: 
 * "Mobile Robots, Inspiration to Implementation", 
 * Second Edition, 1999.
 */

class FollowModified extends Thread
{
    private SharedCar car = new SharedCar();

	private int power = 70, ms = 500;
	private MotorPort lightMotor = MotorPort.B;
	LightSensor light = new LightSensor(SensorPort.S4);
	
	int frontLight, leftLight, rightLight, delta;
	int lightThreshold;
	int extraThreshold = 5;
	
    public FollowModified(SharedCar car)
    {
       this.car = car;	
       lightThreshold = light.getLightValue();
    }
    
	public void run() 
    {				       
        while (true)
        {
	    	// Monitor the light in front of the car and start to follow
	    	// the light if light level is above the threshold
        	frontLight = light.getLightValue();
	    	while ( frontLight <= lightThreshold )
	    	{
	    		car.noCommand();
	    		frontLight = light.getLightValue();
	    	}
	    	
	    	// Follow light as long as the light level is above the threshold
	    	while ( frontLight > lightThreshold )
	    	{
	    		car.forward(0,0);
	    		// Get the light to the left
	    		lightMotor.controlMotor(60, 1);
	    		Delay.msDelay(ms);
	    		leftLight = light.getLightValue();
	    		
	    		// Get the light to the right
	    		lightMotor.controlMotor(60, 2);
	    		Delay.msDelay(ms);
	    		lightMotor.controlMotor(60, 2);
	    		Delay.msDelay(ms);
	    		rightLight = light.getLightValue();
	    		
	    		// Turn back to start position
	    		lightMotor.controlMotor(60, 1);
	    		Delay.msDelay(ms);
	    		lightMotor.controlMotor(0,1);
	    	
	    		// Follow light for a while
	    		delta = leftLight-rightLight;
	    		car.forward(power-delta, power+delta);
	    		Delay.msDelay(ms);
    		
	    		frontLight = light.getLightValue();
	    	}
	    	
	    	car.stop();
	    	Delay.msDelay(ms);
 			
        }
    }
}

