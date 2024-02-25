package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.ColorDetectionBot;
import org.firstinspires.ftc.teamcode.bots.FSMBot;
import org.firstinspires.ftc.teamcode.bots.FourWheelDriveBot;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "AutoCloserRed", group = "Auto")
public class AutoCloserRed extends LinearOpMode {

    //protected OdometryBot odometryBot = new OdometryBot(this);
    protected FSMBot robot = new FSMBot(this);
    public boolean parkLeft = true;

    private OpenCvCamera controlHubCam;
    @Override
    public void runOpMode() throws InterruptedException {
        robot.isAuto = true;
        robot.init(hardwareMap);

        robot.positionIntake(robot.INTAKE_ARM_INIT, robot.INTAKE_HINGE_INIT);
        robot.outtake.setPosition(robot.OUTTAKE_INIT);

        ColorDetectionBot colorDetectionBot = new ColorDetectionBot(this);
        controlHubCam = colorDetectionBot.initOpenCV(hardwareMap, controlHubCam, true);

        int position = colorDetectionBot.detect(); //call method from ColorDetection to know left/middle/right

        while (!opModeIsActive()) {
            position = colorDetectionBot.detect();
            telemetry.addData("Position of pixel", position);
            telemetry.update();
            if (gamepad1.dpad_right) {
                parkLeft = false;
            }
            if (gamepad1.dpad_left) {
                parkLeft = true;
            }
            telemetry.addData("park left:", parkLeft);
        }

        waitForStart();
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        FtcDashboard.getInstance().startCameraStream(controlHubCam, 30);

        telemetry.addData("Position of pixel", position);
        telemetry.update();

        robot.isAuto = true;

        if (position == 1) {
            //move robot from behind truss
            robot.driveToCoordinate(-22000, -25000, 0, 1500, 0.3, true);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //lower intake
            robot.positionIntake(0.65, 0.45);
            robot.sleep(250);
            robot.setIntake(true, true);
            //drive to dropping position
            robot.driveToCoordinate(-23000, -55000, -90, 1500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);
            robot.driveToCoordinate(-18000, -55000, -90, 500, 0.1, true);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //outtake purple pixel
            robot.intakeSpin.setPower(-0.1);
            robot.sleep(2500);
            robot.intakeSpin.setPower(0);

            //move away from purple pixel
            robot.driveToCoordinate(-28000, -55000, -90, 500, 0.3, true);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //go to scoring
            robot.currentState = FSMBot.gameState.DRIVE;
            robot.driveToCoordinate(-65000, -57500, -90, 1500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);
            robot.driveToCoordinate(-65000, -57500, -90, 500, 0.1, true);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //slide up
            robot.slideHeight = 650;
            robot.slideUp(true);
            robot.sleep(250);

            //move in to score
//            robot.driveToCoordinate(-75000, -37000, -90, 500, 0.3, true);
//            robot.waitForCoordinateDrive();
//            robot.sleep(500);
            robot.driveStraightByTime(FourWheelDriveBot.DIRECTION_BACKWARD, 2000, 0.2);
//            robot.sleep(250);

            //slide up
            robot.slideHeight = 850;
            robot.slideUp(true);
            robot.sleep(250);

            //move out from scoring
            robot.driveToCoordinate(-58000, -59000, -90, 500, 0.3, true);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //slide down
            robot.slideHeight = 0;
            robot.slideDown(true);
//            robot.sleep(500);

            //park
            if (parkLeft) {
                robot.driveToCoordinate(-70000, -85000, -180, 1000, 0.3, true);
                robot.waitForCoordinateDrive();
//                robot.sleep(500);
                robot.driveToCoordinate(-82000, -85000, -180, 500, 0.3, true);
                robot.waitForCoordinateDrive();
                robot.outtake.setPosition(robot.OUTTAKE_INIT);
                robot.sleep(1000);
            } else {
                robot.driveToCoordinate(-65000, -8000, -180, 1000, 0.3, true);
                robot.waitForCoordinateDrive();
//                robot.sleep(500);
                robot.driveToCoordinate(-67000, -8000, -180, 500, 0.1, true);
                robot.waitForCoordinateDrive();
                robot.outtake.setPosition(robot.OUTTAKE_INIT);
                robot.sleep(1000);
            }
        } else if (position == 2) {
            //lower intake
            robot.positionIntake(0.65, 0.45);
            robot.sleep(250);
            robot.setIntake(true, true);
            robot.driveToCoordinate(-15000, -8000, 0, 1500, 0.3, true);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);
            robot.driveToCoordinate(-10000, -33000, 0, 1500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);
            robot.driveToCoordinate(-10000, -33000, 0, 500, 0.1, true);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //outtake purple pixel
            robot.intakeSpin.setPower(-0.1);
            robot.sleep(2500);
            robot.intakeSpin.setPower(0);

            //move away from purple pixel
            robot.driveToCoordinate(-10000, -20000, 0, 500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //go to scoring
            robot.currentState = FSMBot.gameState.DRIVE;
            robot.driveToCoordinate(-65000, -49500, -90, 1500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);
            robot.driveToCoordinate(-65000, -49500, -90, 500, 0.1, true);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //slide up
            robot.slideHeight = 630;
            robot.slideUp(true);
            robot.sleep(250);

            //move in to score
            robot.driveStraightByTime(FourWheelDriveBot.DIRECTION_BACKWARD, 3000, 0.25);
//            robot.sleep(500);

            //slide up
            robot.slideHeight = 850;
            robot.slideUp(true);
            robot.sleep(250);

            //move out from scoring
            robot.driveToCoordinate(-65000, -50000, -90, 500, 0.3, true);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //slide down
            robot.slideHeight = 0;
            robot.slideDown(true);
//            robot.sleep(500);

            //park
            if (parkLeft) {
                robot.driveToCoordinate(-65000, -85000, -180, 1000, 0.3, true);
                robot.waitForCoordinateDrive();
//                robot.sleep(500);
                robot.driveToCoordinate(-82000, -85000, -180, 500, 0.3, true);
                robot.waitForCoordinateDrive();
                robot.outtake.setPosition(robot.OUTTAKE_INIT);
                robot.sleep(1000);
            } else {
                robot.driveToCoordinate(-65000, -8000, -180, 1000, 0.3, true);
                robot.waitForCoordinateDrive();
//                robot.sleep(500);
                robot.driveToCoordinate(-67000, -8000, -180, 500, 0.1, true);
                robot.waitForCoordinateDrive();
                robot.outtake.setPosition(robot.OUTTAKE_INIT);
                robot.sleep(1000);
            }
        } else {
            //lower intake
            robot.positionIntake(0.65, 0.45);
            robot.sleep(250);
            robot.setIntake(true, true);

            //drive to ready position
            robot.driveToCoordinate(-22000, -25000, 0, 500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //drive to score purple pixel
            robot.driveToCoordinate(-22000, -25000, 0, 500, 0.1, true);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //outtake purple pixel
            robot.intakeSpin.setPower(-0.1);
            robot.sleep(2500);
            robot.intakeSpin.setPower(0);

            //move away from purple pixel
            robot.driveToCoordinate(-23000, -10000, 0, 500, 0.3, true);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //go to scoring
            robot.currentState = FSMBot.gameState.DRIVE;
            robot.driveToCoordinate(-65000, -38500, -90, 1500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);
            robot.driveToCoordinate(-65000, -38500, -90, 500, 0.1, true);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //slide up
            robot.slideHeight = 650;
            robot.slideUp(true);
            robot.sleep(250);

            //move in to score
            robot.driveStraightByTime(FourWheelDriveBot.DIRECTION_BACKWARD, 3000, 0.2);

            //slide up
            robot.slideHeight = 850;
            robot.slideUp(true);
            robot.sleep(250);

            //move out from scoring
            robot.driveToCoordinate(-65000, -41000, -90, 500, 0.3, true);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //slide down
            robot.slideHeight = 0;
            robot.slideDown(true);
//            robot.sleep(500);

            //park
            if (parkLeft) {
                robot.driveToCoordinate(-65000, -85000, -180, 1000, 0.3, true);
                robot.waitForCoordinateDrive();
//                robot.sleep(500);
                robot.driveToCoordinate(-82000, -85000, -180, 500, 0.1, true);
                robot.waitForCoordinateDrive();
                robot.outtake.setPosition(robot.OUTTAKE_INIT);
                robot.sleep(1000);
            } else {
                robot.driveToCoordinate(-65000, -8000, -180, 1000, 0.3, true);
                robot.waitForCoordinateDrive();
//                robot.sleep(500);
                robot.driveToCoordinate(-67000, -8000, -180, 500, 0.1, true);
                robot.waitForCoordinateDrive();
                robot.outtake.setPosition(robot.OUTTAKE_INIT);
                robot.sleep(1000);
            }

        }
        robot.currentState = FSMBot.gameState.LINEAR_SLIDE_COMPLETELY_DOWN;
        robot.sleep(500);
    }
}
