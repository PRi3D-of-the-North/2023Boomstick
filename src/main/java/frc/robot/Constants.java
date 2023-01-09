package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final int LEFT_FRONT_DRIVE = 1, // DRIVETRAIN MOTORS
                          LEFT_REAR_DRIVE = 2,
                          RIGHT_FRONT_DRIVE = 3,
                          RIGHT_REAR_DRIVE = 4,
                          LEFT_FRONT_STEER = 5,
                          LEFT_REAR_STEER = 6,
                          RIGHT_FRONT_STEER = 7,
                          RIGHT_REAR_STEER = 8;
  
  public static final int ELEVATOR = 10, // SPARK MAX
                          ARM = 9;

  public static final int ARM_FORWARD_LIMIT = 0, // DIO
                          ARM_REAR_LIMIT = 1;

  public static final int CLAW_FORWARD = 9, // Pistons
                          CLAW_REVERSE = 8;

  public static final int LEFT_FRONT_ENCODER = 15, // CANCODERS
                          LEFT_REAR_ENCODER = 16,
                          RIGHT_FRONT_ENCODER = 17,
                          RIGHT_REAR_ENCODER = 18;

  public static final int MAX_COUNTS_PER_REV = 42;
  public static final double EPSILON = 0.0001;
  
  // The left-to-right distance between the drivetrain wheels. Should be measured from center to center.
  public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.37; // Measure and set trackwidth
  // The front-to-back distance between the drivetrain wheels. Should be measured from center to center.
  public static final double DRIVETRAIN_WHEELBASE_METERS = 0.623; // Measure and set wheelbase

  public static final double LEFT_FRONT_STEER_OFFSET = -Math.toRadians(98.35); // Measure and set front left steer offset
  public static final double LEFT_REAR_STEER_OFFSET = -Math.toRadians(221.13); // Measure and set front right steer offset
  public static final double RIGHT_FRONT_STEER_OFFSET = -Math.toRadians(279.48); // Measure and set back left steer offset
  public static final double RIGHT_REAR_STEER_OFFSET = -Math.toRadians(180.52); // Measure and set back right steer offset
}
