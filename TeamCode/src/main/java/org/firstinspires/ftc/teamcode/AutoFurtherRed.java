package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.ColorDetectionBot;
import org.firstinspires.ftc.teamcode.bots.FSMBot;
import org.firstinspires.ftc.teamcode.bots.FourWheelDriveBot;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "AutoFurtherRed", group = "Auto")
public class AutoFurtherRed extends LinearOpMode {

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

        robot.sleep(7000);

        if (position == 1) {
            //lower intake
            robot.positionIntake(0.8, 0.35);
            robot.setIntake(true, true);

            //drive to ready position
            robot.driveToCoordinate(24000, -24000, 0, 500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //drive to score purple pixel
            robot.driveToCoordinate(26000, -24000, 0, 500, 0.1, true);
            robot.waitForCoordinateDrive();
            //outtake purple pixel
            robot.intakeSpin.setPower(-0.1);
            robot.sleep(2000);
            robot.intakeSpin.setPower(0);

            //move away from purple pixel
            robot.driveToCoordinate(10000, -6000, -90, 500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //move through truss
            robot.driveToCoordinate(-95000, -6000, -90, 500, 0.4, true);
            robot.waitForCoordinateDrive();

            //go to scoring
            robot.driveToCoordinate(-140000, -61500, -90, 1500, 0.2, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);
            robot.driveToCoordinate(-140000, -61500, -90, 500, 0.1, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //slide up
            robot.slideHeight = 800;
            robot.slideUp(true);
            robot.sleep(500);

            //move in to score
//            robot.driveToCoordinate(-75000, -37000, -90, 500, 0.3, true);
//            robot.waitForCoordinateDrive();
//            robot.sleep(500);
            robot.driveStraightByTime(FourWheelDriveBot.DIRECTION_BACKWARD, 3000, 0.2);
            robot.sleep(500);

            //move out from scoring
            robot.driveToCoordinate(-140000, -59000, -90, 500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //slide down
            robot.slideHeight = 0;
            robot.slideDown(true);
            robot.sleep(500);

            //park

            robot.driveToCoordinate(-150000, -44000, -180, 500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            robot.outtake.setPosition(robot.OUTTAKE_INIT);
            robot.sleep(1000);
        } else if (position == 2) {
            //lower intake
            robot.setIntake(true, true);
            robot.driveToCoordinate(5000, -34000, 0, 1500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(250);
            robot.driveToCoordinate(5000, -34000, 0, 500, 0.1, true);
            robot.waitForCoordinateDrive();

            //outtake purple pixel
            robot.intakeSpin.setPower(-0.1);
            robot.sleep(2500);
            robot.intakeSpin.setPower(0);

            //move away from purple pixel
            robot.currentState = FSMBot.gameState.DRIVE;
            robot.driveToCoordinate(5000, -6000, -90, 1500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);
            robot.driveToCoordinate(5000, -6000, -90, 500, 0.1, true);
            robot.waitForCoordinateDrive();

            //move through truss
            robot.driveToCoordinate(-95000, -6000, -90, 500, 0.3, true);
            robot.waitForCoordinateDrive();

            //go to scoring
            robot.driveToCoordinate(-140000, -46000, -90, 1500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);
            robot.driveToCoordinate(-140000, -46000, -90, 500, 0.1, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //slide up
            robot.slideHeight = 800;
            robot.slideUp(true);
            robot.sleep(500);

            //move in to score
            robot.driveStraightByTime(FourWheelDriveBot.DIRECTION_BACKWARD, 1700, 0.25);

//            robot.driveToCoordinate(67500, -50000, 90, 500, 0.3, true);
//            robot.waitForCoordinateDrive();
//            robot.sleep(1500);

            //slide down
            //move out from scoring
            robot.driveToCoordinate(-140000, -46000, -90, 500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            robot.slideHeight = 0;
            robot.slideDown(true);
            robot.sleep(500);

            //park
            robot.driveToCoordinate(-150000, -44000, -180, 500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            robot.outtake.setPosition(robot.OUTTAKE_INIT);
            robot.currentState = FSMBot.gameState.LINEAR_SLIDE_COMPLETELY_DOWN;
            robot.sleep(500);
        } else {
            //move robot from behind truss
            robot.driveToCoordinate(22000, -25000, 0, 1500, 0.3, false);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //lower intake
            robot.positionIntake(0.65, 0.45);
            robot.sleep(250);
            robot.setIntake(true, true);
            //drive to dropping position
            robot.driveToCoordinate(17000, -55000, 90, 1500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);
            robot.driveToCoordinate(17000, -55000, 90, 500, 0.2, true);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            // outtake purple pixel
            robot.intakeSpin.setPower(-0.1);
            robot.sleep(2250);
            robot.intakeSpin.setPower(0);

            //move away from purple pixel
            robot.driveToCoordinate(28000, -55000, 90, 500, 0.2, false);
            robot.waitForCoordinateDrive();
//            robot.sleep(500);

            //prepare to drive truss
            robot.currentState = FSMBot.gameState.DRIVE;
            robot.driveToCoordinate(28000, -6000, -90, 500, 0.2, true);
            robot.waitForCoordinateDrive();
//            robot.driveToCoordinate(-28000, -94000, 90, 300, 0.1, true)
//            robot.waitForCoordinateDrive();

            //drive through truss
            robot.driveToCoordinate(-95000, -6000, -90, 500, 0.4, true);
            robot.waitForCoordinateDrive();

            //go to scoring
            robot.driveToCoordinate(-140000, -37000, -90, 1500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);
            robot.driveToCoordinate(-140000, -37000, -90, 500, 0.1, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //slide up
            robot.slideHeight = 800;
            robot.slideUp(true);
            robot.sleep(500);

            //move in to score
//            robot.driveToCoordinate(67500, -54000, 90, 500, 0.3, true);
//            robot.waitForCoordinateDrive();
//            robot.sleep(1500);
            robot.driveStraightByTime(FourWheelDriveBot.DIRECTION_BACKWARD, 1500, 0.3);

            //move out from scoring
            robot.driveToCoordinate(-140000, -44000, -90, 500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);

            //slide down
            robot.slideHeight = 0;
            robot.slideDown(true);
            robot.sleep(500);

            //park
            robot.driveToCoordinate(-150000, -44000, -180, 500, 0.3, true);
            robot.waitForCoordinateDrive();
            robot.sleep(500);
            robot.outtake.setPosition(robot.OUTTAKE_INIT);
            robot.currentState = FSMBot.gameState.LINEAR_SLIDE_COMPLETELY_DOWN;
            robot.sleep(1000);
        }
    }
}
