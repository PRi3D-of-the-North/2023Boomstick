package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import frc.robot.Constants;
public class Arm {
    private CANSparkMax motor = new CANSparkMax(
            Constants.ELevator_Extend,
            CANSparkMax.MotorType.kBrushless);
    RelativeEncoder encoder = motor.getEncoder();
    SparkMaxPIDController pid = motor.getPIDController();

    double angle;

    public static final double P = 0.1, I = 0, D = 0;

    public Arm(){
        encoder.setPositionConversionFactor(TURN_TO_Inch);
        pid.setP(P);
        pid.setI(I);
        pid.setD(D);
    }

    public void SetAngle(double Angle){
        this.angle = Angle;

        pid.setReference(Angle, CANSparkMax.ControlType.kPosition);
    }

    public void Zero(double Angle) {
        encoder.setPosition(Angle);
    }
}
