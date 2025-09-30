// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Subsystems.REVExample;
import frc.robot.Subsystems.SPXExample;

public class RobotContainer {

  private REVExample revExample = new REVExample();  // Example for rev motors
  private SPXExample spxExample = new SPXExample();  // Example for spxMotors

  private final CommandXboxController controller = new CommandXboxController(0); 

  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {

    // Binding motor 1 spin to controller right trigger. 
    controller
    .axisGreaterThan(3, .1) // making sure trigger value greater than .1 before going any further (DEADBAND)
      .whileTrue( // while the trigger is pressed
        new InstantCommand(() -> revExample.spinMotor1(controller.getRightTriggerAxis())) // run this command while getting the value of the trigger
        ).onFalse(new InstantCommand( () -> revExample.stopMotor1())); 
    
    controller.b().whileTrue(revExample.setPositionMotor1(60));
    
  }


  public Command getAutonomousCommand() {
    return null;
  }
}
