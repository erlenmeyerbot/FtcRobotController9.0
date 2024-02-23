package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.ColorDetectionBot;
import org.firstinspires.ftc.teamcode.bots.FSMBot;
import org.firstinspires.ftc.teamcode.bots.FourWheelDriveBot;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "AutoFurtherBlue", group = "Auto")
public class AutoFurtherBlue extends LinearOpMode {

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
        controlHubCam = colorDetectionBot.initOpenCV(hardwareMap, controlHubCam, false);

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
            robot.driveToCoordinate(-22000, -25000, 0, 1500, 0.3, false);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //lower intake
            robot.positionIntake(0.65, 0.45);
//            robot.sleep(500);
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
            robot.driveToCoordinate(-28000, -55000, -90, 500, 0.1, false);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //prepare to drive through stage door
            robot.driveToCoordinate(-28000, -94000, -90, 1500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);
            robot.currentState = FSMBot.gameState.DRIVE;

            //drive through stage door
            robot.driveToCoordinate(125000, -94000, -90, 1500, 0.25, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);
            robot.driveToCoordinate(125000, -94000, 90, 1500, 0.2, true);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //drive to scoring
            robot.driveToCoordinate(130000, -46500, 90, 1500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);
            robot.driveToCoordinate(130000, -46500, 90, 500, 0.1, true);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //slide up
            robot.slideHeight = 900;
            robot.slideUp(true);
//            robot.sleep(500);

            //move in to score
//            robot.driveToCoordinate(67500, -39000, 90, 500, 0.3, true);
//            robot.waitForCoordinateDrive();
//            robot.sleep(1500);
            robot.driveStraightByTime(FourWheelDriveBot.DIRECTION_BACKWARD, 1300, 0.3);

            //slide up
//            robot.slideHeight = 950;
//            robot.slideUp(true);
//            robot.sleep(250);

            //move out from scoring
            robot.driveToCoordinate(138000, -49000, 90, 500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(250);

            //slide down
//            robot.slideHeight = 0;
            robot.slideDown(true);
//            robot.sleep(500);

            //park
            robot.driveToCoordinate(135000, -55000, 180, 500, 0.3, true);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            robot.outtake.setPosition(robot.OUTTAKE_INIT);
            robot.currentState = FSMBot.gameState.LINEAR_SLIDE_COMPLETELY_DOWN;
            robot.sleep(500);
        } else if (position == 2) {
            //lower intake
            robot.setIntake(true, true);
            robot.driveToCoordinate(0, -38000, 0, 1500, 0.1, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);
            robot.driveToCoordinate(0, -38000, 0, 500, 0.1, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //outtake purple pixel
            robot.intakeSpin.setPower(-0.1);
            robot.sleep(2000);
            robot.intakeSpin.setPower(0);

            //move away from purple pixel
            robot.driveToCoordinate(0, -20000, 0, 500, 0.1, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //go to scoring
            robot.currentState = FSMBot.gameState.DRIVE;
            robot.driveToCoordinate(50000, -48700, 0, 1500, 0.2, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);
            robot.driveToCoordinate(58000, -48700, 90, 500, 0.1, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //slide up
            robot.slideHeight = 615;
            robot.slideUp(true);
            robot.sleep(500);

            //move in to score
            robot.driveStraightByTime(FourWheelDriveBot.DIRECTION_BACKWARD, 3000, 0.3);

//            robot.driveToCoordinate(67500, -50000, 90, 500, 0.3, true);
//            robot.waitForCoordinateDrive();
//            robot.sleep(1500);

            //slide up
            robot.slideHeight = 700;
            robot.slideUp(true);
            robot.sleep(500);

            //move out from scoring
            robot.driveToCoordinate(58000, -48700, 90, 500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //slide down
            robot.slideHeight = 0;
            robot.slideDown(true);
            robot.sleep(500);

            //park
            if (parkLeft) {
                robot.driveToCoordinate(58000, -8000, 180, 1000, 0.3, true);
                robot.waitForCoordinateDrive();
                robot.sleep(500);
                robot.driveToCoordinate(58000, -8000, 180, 500, 0.1, true);
                robot.waitForCoordinateDrive();
                robot.sleep(500);
                robot.outtake.setPosition(robot.OUTTAKE_INIT);
                robot.sleep(1000);
            } else {
                robot.driveToCoordinate(58000, -85000, 180, 1000, 0.3, true);
                robot.waitForCoordinateDrive();
                robot.sleep(500);
                robot.driveToCoordinate(70000, -85000, 180, 500, 0.1, true);
                robot.waitForCoordinateDrive();
                robot.sleep(500);
                robot.outtake.setPosition(robot.OUTTAKE_INIT);
                robot.sleep(1000);
            }
            robot.outtake.setPosition(robot.OUTTAKE_INIT);
            robot.sleep(1000);
        } else {
            //drive to ready position
            robot.driveToCoordinate(30000, -60000, 90, 500, 0.2, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //lower intake
            robot.setIntake(true, true);

            //drive to score purple pixel
            robot.driveToCoordinate(10000, -60000, 90, 500, 0.1, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //outtake purple pixel
            robot.intakeSpin.setPower(-0.1);
            robot.sleep(2000);
            robot.intakeSpin.setPower(0);

            //move away from purple pixel
            robot.driveToCoordinate(30000, -60000, 90, 500, 0.1, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //go to scoring
            robot.currentState = FSMBot.gameState.DRIVE;
            robot.driveToCoordinate(50000, -54000, 90, 1500, 0.2, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);
            robot.driveToCoordinate(58000, -54000, 90, 500, 0.1, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //slide up
            robot.slideHeight = 650;
            robot.slideUp(true);
            robot.sleep(500);

            //move in to score
//            robot.driveToCoordinate(67500, -54000, 90, 500, 0.3, true);
//            robot.waitForCoordinateDrive();
//            robot.sleep(1500);
            robot.driveStraightByTime(FourWheelDriveBot.DIRECTION_BACKWARD, 3000, 0.3);

            //slide up
            robot.slideHeight = 700;
            robot.slideUp(true);
            robot.sleep(500);

            //move out from scoring
            robot.driveToCoordinate(58000, -54000, 90, 500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //slide down
            robot.slideHeight = 0;
            robot.slideDown(true);
            robot.sleep(500);

            //park
            if (parkLeft) {
                robot.driveToCoordinate(58000, -8000, 180, 1000, 0.3, true);
                robot.waitForCoordinateDrive();
                robot.sleep(500);
                robot.driveToCoordinate(58000, -8000, 180, 500, 0.1, true);
                robot.waitForCoordinateDrive();
                robot.sleep(500);
                robot.outtake.setPosition(robot.OUTTAKE_INIT);
                robot.sleep(1000);
            } else {
                robot.driveToCoordinate(58000, -85000, 180, 1000, 0.3, true);
                robot.waitForCoordinateDrive();
                robot.sleep(500);
                robot.driveToCoordinate(70000, -85000, 180, 500, 0.1, true);
                robot.waitForCoordinateDrive();
                robot.sleep(500);
                robot.outtake.setPosition(robot.OUTTAKE_INIT);
                robot.sleep(1000);
            }
        }
        robot.currentState = FSMBot.gameState.LINEAR_SLIDE_COMPLETELY_DOWN;
        robot.sleep(500);
    }
}
