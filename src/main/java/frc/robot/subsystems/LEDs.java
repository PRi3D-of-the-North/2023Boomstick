package frc.robot.subsystems;
import com.ctre.phoenix.led.CANdle;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDs extends SubsystemBase {

    private final com.ctre.phoenix.led.CANdle LEDStrip = new CANdle(1);//CanID

    public void SetAll(int r, int g, int b){
        if (r >255){r = 255;}
        if (r <0){r = 0;}
        if (g >255){g = 255;}
        if (g <0){g = 0;}
        if (b >255){b = 255;}
        if (b <0){b = 0;}
        LEDStrip.setLEDs(r,g,b);
    }

    public void RED(){
        LEDStrip.setLEDs(255,0,0);
    }

    public void GREEN(){
        LEDStrip.setLEDs(0,255,0);
    }

    public void BLUE(){
        LEDStrip.setLEDs(0,0,255);
    }

    public void PURPLE(){
        LEDStrip.setLEDs(255,0,255);
    }

    public void WHITE(){
        LEDStrip.setLEDs(255,255,255);
    }

    public void ORANGE(){
        LEDStrip.setLEDs(255,140,0);
    }

    public void YELLOW(){
        LEDStrip.setLEDs(255,255,0);
    }
}