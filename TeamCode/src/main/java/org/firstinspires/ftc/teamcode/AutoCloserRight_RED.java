package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.ColorDetectionBot;
import org.firstinspires.ftc.teamcode.bots.OdometryBot;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "AutoCloserRightRED", group = "Auto")
public class AutoCloserRight_RED extends LinearOpMode {
    protected OdometryBot odometryBot = new OdometryBot(this);
    private OpenCvCamera controlHubCam;
    @Override
    public void runOpMode() throws InterruptedException {

        odometryBot.isAuto = true;
        odometryBot.init(hardwareMap);
        ColorDetectionBot colorDetectionBot = new ColorDetectionBot(this);
        controlHubCam = colorDetectionBot.initOpenCV(hardwareMap, controlHubCam, true);

        int position = colorDetectionBot.detect(); //call method from ColorDetection to know left/middle/right

        while (!opModeIsActive()) {
            position = colorDetectionBot.detect();
            telemetry.addData("Position of pixel", position);
            telemetry.update();
        }

        waitForStart();
        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        FtcDashboard.getInstance().startCameraStream(controlHubCam, 30);

        telemetry.addData("Position of pixel", position);
        telemetry.update();

        odometryBot.driveToCoordinate(8000, 2000, 0, 1000, 0.3, true);
        odometryBot.waitForCoordinateDrive();
        odometryBot.sleep(500);

        if (position == 1) {
            odometryBot.driveToCoordinate(10000, 30000, 0, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            odometryBot.driveToCoordinate(3000, 49000, -90, 1000, 0.2, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            odometryBot.driveToCoordinate(69000, 48000, 0, 1000, 0.2, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

        } else if (position == 2) {
            odometryBot.driveToCoordinate(0, 48000, 0, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            odometryBot.driveToCoordinate(15000, 36000, 0, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            odometryBot.driveToCoordinate(69000, 48000, 0, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
        } else if (position == 3) {
            odometryBot.driveToCoordinate(30000, 40000, 0, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            odometryBot.driveToCoordinate(27500, 20000, 0, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            odometryBot.driveToCoordinate(69000, 48000, 0, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();

//            odometryBot.driveToCoordinate(0, 40000, 0, 1000, 0.3, true);
//            odometryBot.waitForCoordinateDrive();
//            odometryBot.sleep(500);
//
//            odometryBot.driveToCoordinate(0, 30000, 0, 1000, 0.3, true);
//            odometryBot.waitForCoordinateDrive();
//            odometryBot.sleep(500);
//
//            odometryBot.driveToCoordinate(-20000, 30000, 0, 1000, 0.3, true);
//            odometryBot.waitForCoordinateDrive();
//            odometryBot.sleep(500);
//
//            odometryBot.driveToCoordinate(-20000, 55000, 90, 1000, 0.3, true);
//            odometryBot.waitForCoordinateDrive();
//            odometryBot.sleep(500);
//
//            odometryBot.driveToCoordinate(21500, 55000, 90, 1000, 0.3, true);
//            odometryBot.waitForCoordinateDrive();
//            odometryBot.sleep(500);
//
//            odometryBot.driveToCoordinate(-60000, 30000, 0, 1000, 0.3, true);
//            odometryBot.waitForCoordinateDrive();
//            odometryBot.sleep(500);
        }

//        controlHubCam.stopStreaming();
    }
}
