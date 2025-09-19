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
    private SparkMax motor1 = new SparkMax(0, MotorType.kBrushless);  
    private SparkMax motor2 = new SparkMax(1, MotorType.kBrushless);  



    // Initalizing motor configs
    private SparkMaxConfig motor1Config = new SparkMaxConfig(); 
    private SparkMaxConfig motor2Config = new SparkMaxConfig(); 

    // Initializing the encoder for the motors (KEEP IN MIND THE DIFFERENCE BETWEEN ABSOLUTE AND RELATIVE ENCODER)
    private RelativeEncoder motor1Encoder; 
    private RelativeEncoder motor2Encoder; 

    // The controller used for setpoints and PID
    private SparkClosedLoopController motor1Controller;
    private SparkClosedLoopController motor2Controller;


    public REVExample() { 
        // Get the motor encoders and assign them to their respective relative encoder. 
        motor1Encoder = motor1.getEncoder(); 
        motor2Encoder = motor2.getEncoder();    

        // Get the motor loop controllers and assign them to their respective motor
        motor1Controller = motor1.getClosedLoopController(); 
        motor2Controller = motor2.getClosedLoopController(); 

        // CONFIGURING MOTOR 1
        motor1Config.closedLoop
            .feedbackSensor(FeedbackSensor.kPrimaryEncoder) // Setting what type of encoder we are using
            .outputRange(-1, 1) // Min and Max output so now the motor can only go from -1 to 1
            .pid(0, 0, 0);  // PID setup for the motor

        // CONFIGURING MOTOR 2
        motor2Config.closedLoop
            .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
            .outputRange(-1,1)
            .pid(0, 0, 0); 

        // APPLYING THE CONFIGURATIONS TO THE MOTORS
        motor1.configure(motor1Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters); 
        motor2.configure(motor2Config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        // this.setDefaultCommand(stopAllMotors());
    }

    public void spinMotor1(double speed) { 
        SmartDashboard.putNumber("Motor 1 Speed", speed); 
        motor1.set(speed);
    }

    public void spinMotor2(double speed) { 
        SmartDashboard.putNumber("Motor 2 Speed", speed); 
        motor2.set(speed);
    }

    public void setPositionMotor1(double position) { 
        motor1Controller.setReference(position, ControlType.kPosition); 
    }

    public void setPositionMotor2(double position) { 
        motor2Controller.setReference(position, ControlType.kPosition); 
    }

    public Command stopAllMotors() { 
        return this.runOnce(() -> stopAllMotors()); 
    }

    private void stopMotors() { 
        motor1.set(0);
        motor2.set(0);
        SmartDashboard.putNumber("Motor 1 Speed", 0); 
        SmartDashboard.putNumber("Motor 2 Speed", 0); 

    }

    public void stopMotor1() { 
        motor1.set(0);
        SmartDashboard.putNumber("Motor 1 Speed", 0); 
    }

    public void stopMotor2() { 
        motor2.set(0);
        SmartDashboard.putNumber("Motor 2 Speed", 0); 
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Motor 1 Speed", motor1.get()); 
        SmartDashboard.putNumber("Motor 2 Speed", motor2.get()); 

        SmartDashboard.putNumber("Motor 1 Encoder", motor1Encoder.getPosition()); 
        SmartDashboard.putNumber("Motor 2 Encoder", motor2Encoder.getPosition()); 
    }

    
}
