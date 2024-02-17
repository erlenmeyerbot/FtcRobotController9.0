package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.ColorDetectionBot;
import org.firstinspires.ftc.teamcode.bots.OdometryBot;
import org.firstinspires.ftc.teamcode.bots.ScoringBot;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "AutoCloserLeftBLUE", group = "Auto")
public class AutoCloserLeft_BLUE extends LinearOpMode {
    protected OdometryBot odometryBot = new OdometryBot(this);
    protected ScoringBot linearSlideBot = new ScoringBot(this);

    private OpenCvCamera controlHubCam;
    @Override
    public void runOpMode() throws InterruptedException {

        odometryBot.isAuto = true;
        odometryBot.init(hardwareMap);
        linearSlideBot.isAuto = true;
        linearSlideBot.init(hardwareMap);
        ColorDetectionBot colorDetectionBot = new ColorDetectionBot(this);
        controlHubCam = colorDetectionBot.initOpenCV(hardwareMap, controlHubCam, false);

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
        //linearSlideBot.hingeControl(false);
        //linearSlideBot.releasePixels(true);

        if (position == 1) {
            odometryBot.driveToCoordinate(-21500, 40000, 0, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);


            odometryBot.driveToCoordinate(-18500, 30000, 0, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            odometryBot.driveToCoordinate(-60000, 48000, 0, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            //start dropping
            odometryBot.driveToCoordinate(-55000, 48000, -75, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            odometryBot.driveToCoordinate(-55000, 46000, -90, 500, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            //linearSlideBot.hingeControl(true);
            linearSlideBot.autoSlide(800, 0.5);
            linearSlideBot.sleep(2000);

            //get closer to backdrop
            odometryBot.driveToCoordinate(-59000, 46000, -90, 500, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(300);

            //linearSlideBot.releasePixels(false);
            linearSlideBot.sleep(1000);

            //linearSlideBot.releasePixels(true);
            //linearSlideBot.hingeControl(false);
            linearSlideBot.sleep(500);

            odometryBot.driveToCoordinate(-55000, 48000, -90, 500, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);
            linearSlideBot.autoSlide(0, 0.5);

            odometryBot.driveToCoordinate(-60000, 48000, 0, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);
        } else if (position == 2) {
            odometryBot.driveToCoordinate(0, 48000, 0, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            odometryBot.driveToCoordinate(0, 30000, 0, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            odometryBot.driveToCoordinate(-60000, 48000, 0, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);


            //start dropping
            odometryBot.driveToCoordinate(-55000, 48000, -75, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            odometryBot.driveToCoordinate(-55000, 56000, -90, 500, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            //linearSlideBot.hingeControl(true);
            linearSlideBot.autoSlide(800, 0.5);
            linearSlideBot.sleep(2000);

            //get closer to backdrop
            odometryBot.driveToCoordinate(-59000, 56000, -90, 500, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(300);

            //linearSlideBot.releasePixels(false);
            linearSlideBot.sleep(1000);

            //linearSlideBot.releasePixels(true);
            //linearSlideBot.hingeControl(false);
            linearSlideBot.sleep(500);

            odometryBot.driveToCoordinate(-55000, 48000, -90, 500, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);
            linearSlideBot.autoSlide(0, 0.5);

            odometryBot.driveToCoordinate(-60000, 48000, 0, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);
        } else if (position == 3) {
            odometryBot.driveToCoordinate(0, 30000, 0, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            odometryBot.driveToCoordinate(6500, 49000, 90, 1000, 0.2, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            odometryBot.driveToCoordinate(-60000, 48000, 0, 1000, 0.2, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            //possible alternative code for scoring the purple pixel
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


            //start dropping
            odometryBot.driveToCoordinate(-55000, 48000, -75, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            odometryBot.driveToCoordinate(-55000, 66000, -90, 500, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);

            //linearSlideBot.hingeControl(true);
            linearSlideBot.autoSlide(800, 0.5);
            linearSlideBot.sleep(2000);

            //get closer to backdrop
            odometryBot.driveToCoordinate(-59000, 66000, -90, 500, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(300);

            //linearSlideBot.releasePixels(false);
            linearSlideBot.sleep(1000);

            //linearSlideBot.releasePixels(true);
            //linearSlideBot.hingeControl(false);
            linearSlideBot.sleep(500);

            odometryBot.driveToCoordinate(-55000, 48000, -90, 500, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);
            linearSlideBot.autoSlide(0, 0.5);

            odometryBot.driveToCoordinate(-60000, 48000, 0, 1000, 0.3, true);
            odometryBot.waitForCoordinateDrive();
            odometryBot.sleep(500);
        }

//        controlHubCam.stopStreaming();
    }
}
