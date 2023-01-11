package frc.robot.tools;
import com.ctre.phoenix.led.CANdle;

import frc.robot.Constants;

public class LEDs {
    private final CANdle LEDStrip = new CANdle(Constants.CANDLE); // CANID

    public void green(){
        LEDStrip.setLEDs(0, 255, 0);
    }
}