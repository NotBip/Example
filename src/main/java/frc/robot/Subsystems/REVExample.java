package frc.robot.Subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class REVExample extends SubsystemBase {

    // Initializing the motors. 
    private SparkMax motor1 = new SparkMax(1, MotorType.kBrushless);  

    // Initalizing motor configs
    private SparkMaxConfig motor1Config = new SparkMaxConfig(); 

    // Initializing the encoder for the motors (KEEP IN MIND THE DIFFERENCE BETWEEN ABSOLUTE AND RELATIVE ENCODER)
    private RelativeEncoder motor1Encoder; 

    // The controller used for setpoints and PID
    private SparkClosedLoopController motor1Controller;


    public REVExample() { 
        // Get the motor encoders and assign them to their respective relative encoder. 
        motor1Encoder = motor1.getEncoder(); 

        // Get the motor loop controllers and assign them to their respective motor
        motor1Controller = motor1.getClosedLoopController(); 

        // CONFIGURING MOTOR 1
        motor1Config.closedLoop
            .feedbackSensor(FeedbackSensor.kPrimaryEncoder) // Setting what type of encoder we are using
            .outputRange(-1, 1) // Min and Max output so now the motor can only go from -1 to 1
            .pid(0.01, 0, 0);  // PID setup for the motor


        // APPLYING THE CONFIGURATIONS TO THE MOTORS
        motor1.configure(motor1Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters); 
        // this.setDefaultCommand(stopAllMotors());
    }

    public void spinMotor1(double speed) { 
        SmartDashboard.putNumber("Motor 1 Speed", speed); 
        motor1.set(speed);
    }

    public Command setPositionMotor1(double position) { 
        return run(() -> motor1Controller.setReference(position, ControlType.kPosition)); 
         
    }


    public void stopMotor1() { 
        motor1.set(0);
        SmartDashboard.putNumber("Motor 1 Speed", 0); 
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Motor 1 Speed", motor1.get()); 

        SmartDashboard.putNumber("Motor 1 Encoder", motor1Encoder.getPosition()); 
    }

    
}
