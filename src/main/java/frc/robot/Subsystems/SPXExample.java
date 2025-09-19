package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SPXExample extends SubsystemBase {
    
    private VictorSPX motor1 = new VictorSPX(0); 
    private VictorSPX motor2 = new VictorSPX(1); 

    public SPXExample() { 
    }

    public void spinMotor1(double speed) { 
        motor1.set(VictorSPXControlMode.PercentOutput, speed);
    }

    public void spinMotor2(double speed) { 
        motor2.set(VictorSPXControlMode.PercentOutput, speed);
    }

    public void stopMotor1() { 
        motor1.set(VictorSPXControlMode.PercentOutput, 0);
    }

    public void stopMotor2() { 
        motor2.set(VictorSPXControlMode.PercentOutput, 0);
    }

    public void stopAllMotors() { 
        stopMotor1();
        stopMotor2();
    }


}
