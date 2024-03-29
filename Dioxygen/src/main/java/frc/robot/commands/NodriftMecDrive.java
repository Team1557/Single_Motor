/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.DrivebaseContainer;
import frc.robot.subsystems.MecanumDriveSub;

public class NodriftMecDrive extends Command {
  
  private DrivebaseContainer drivebase = Robot.drivebaseContainer;

  public NodriftMecDrive() {
    requires(drivebase);
  }
  
  private XboxController driveCon = Robot.driveController;
  private AHRS NavXGyro = Robot.navXGyro;

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    MecanumDriveSub.noDriftDrive(driveCon.getX(Hand.kLeft), driveCon.getY(Hand.kLeft), driveCon.getX(Hand.kRight), NavXGyro);
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
