package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import frc.robot.Constants;

public class Elevator {
    private CANSparkMax motor = new CANSparkMax(
            Constants.ELevator_Extend,
            CANSparkMax.MotorType.kBrushless);
    RelativeEncoder encoder = motor.getEncoder();
    SparkMaxPIDController pid = motor.getPIDController();

    double length;
    
    public static final double P = 0.1, I = 0, D = 0;

    public Elevator(){
        encoder.setPositionConversionFactor(TURN_TO_Inch);
        pid.setP(P);
        pid.setI(I);
        pid.setD(D);
    }

    public void SetLength(double Length){
        this.length = Length;

        pid.setReference(Length, CANSparkMax.ControlType.kPosition);
    }

    public void Zero(double Length) {
        encoder.setPosition(Length);
    }
}

