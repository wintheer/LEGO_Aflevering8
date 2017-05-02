
import lejos.nxt.*;
import lejos.util.Delay;
/*
 * A simple behavior control program Avoid, 
 * Figure 9.3 in Jones, Flynn, and Seiger: 
 * "Mobile Robots, Inspiration to Implementation", 
 * Second Edition, 1999.
 */
public class AvoidFigure9_3 {
 	    
    public static void main(String [] args)  throws Exception {

		
    	int power1 = 70, power2 = 71, ms = 500;
    	UltrasonicSensor sonar = new UltrasonicSensor(SensorPort.S3);
    	int frontDistance, leftDistance, rightDistance;
    	int stopThreshold = 30;
    	
    	// ESCAPE button listener makes it possible to exit the program
    	// at any time.
    	Button.ESCAPE.addButtonListener(new ButtonListener() {
    	      public void buttonPressed(Button b) {
    	    	  System.exit(1);
    	      }

    	      public void buttonReleased(Button b) {
    	      }
    	});
    	
    	LCD.drawString("Avoid 9.3", 0, 0);
    	Button.waitForAnyPress();
	    	      
        while ( true )
        {
            // Go forward
            Car.forward(power1, power2);
	    	
            // Monitor the distance in front of the car and stop
            // when an object gets to close
            frontDistance = sonar.getDistance();
            while ( frontDistance > stopThreshold )
            {
            	frontDistance = sonar.getDistance();
            }
            Car.stop();
	    	
            // Get the distance to the left
            Car.forward(0, power2);
            Delay.msDelay(ms);
            leftDistance = sonar.getDistance();
    	
            // Get the distance to the right
            Car.backward(0, power2);
            Delay.msDelay(ms);
            Car.forward(power1, 0);
            Delay.msDelay(ms);
            rightDistance = sonar.getDistance();
	    	
            if (leftDistance < stopThreshold && rightDistance < stopThreshold && frontDistance < stopThreshold) {
            	MotorPort.C.controlMotor(80, 1);
				MotorPort.A.controlMotor(80, 2);
            	Delay.msDelay(1200);
            }
            
            // Turn in the direction with most space in front of the car
            else if ( leftDistance > rightDistance ){
            	Car.backward(power1, 0);
            	Delay.msDelay(ms);
            	Car.forward(0, power2);
            	Delay.msDelay(ms);
            }		   		   
        }
    }
}






	

