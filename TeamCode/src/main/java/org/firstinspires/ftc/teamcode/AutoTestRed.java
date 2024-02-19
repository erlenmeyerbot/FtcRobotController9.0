package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.ColorDetectionBot;
import org.firstinspires.ftc.teamcode.bots.OdometryBot;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "AutoTestRed", group = "Auto")
public class AutoTestRed extends LinearOpMode {
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

//        odometryBot.driveToCoordinate(0, -60000, 0, 1000, 0.5, false);
//        odometryBot.waitForCoordinateDrive();
        while (opModeIsActive()) {
            telemetry.addData("right: ", odometryBot.intake.getCurrentPosition());
            telemetry.addData("left: ", odometryBot.rightFront.getCurrentPosition());
            telemetry.addData("horizontal: ", odometryBot.rightRear.getCurrentPosition());
            telemetry.addData("x: ", odometryBot.xBlue);
            telemetry.addData("y: ", odometryBot.yBlue);
            telemetry.addData("angle: ", odometryBot.thetaDEG);
            telemetry.update();
            odometryBot.sleep(0);
        }

        controlHubCam.stopStreaming();
    }
}
