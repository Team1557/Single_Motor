/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DrivebaseContainer;
import frc.robot.subsystems.MecanumDriveSub;
import frc.robot.subsystems.TankDrive;

public class TurnToAngle extends Command {
  private double angleToTurnTo;
  
  private XboxController driveCon = Robot.driveController;
  private AHRS gyro = Robot.navXGyro;
  
  private DrivebaseContainer drivebase = Robot.drivebaseContainer;

  public TurnToAngle(double angle) {
    angleToTurnTo = angle;

    requires(drivebase);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    TankDrive.Drive(0, MecanumDriveSub.turnSpeed(gyro, false, angleToTurnTo));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drivebase.Stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    drivebase.Stop();
  }
}
