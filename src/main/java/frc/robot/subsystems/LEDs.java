package frc.robot.subsystems;
import com.ctre.phoenix.led.CANdle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LEDs extends SubsystemBase {

    private final com.ctre.phoenix.led.CANdle LEDStrip = new CANdle(0);//CanID
    private int r, g, b;

    public void SetAll(int r, int g, int b){
        LEDStrip.setLEDs(r,g,b);
    }

    public void ChangeLEDS(int rChange, int gChange, int bChange){
        this.r = this.r + rChange;
        this.g = this.g + gChange;
        this.b = this.b + bChange;

        if (this.r > 255){
            this.r = 255;
        }
        if (this.g > 255){
            this.g = 255;
        }
        if (this.b > 255){
            this.b = 255;
        }
        if (this.r < 0){
            this.r = 0;
        }
        if (this.g < 0){
            this.g = 0;
        }
        if (this.b < 0){
            this.b = 0;
        }
        SetAll(this.r, this.g, this.b);
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