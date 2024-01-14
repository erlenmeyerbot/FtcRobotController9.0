package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.bots.ColorDetectionBot;
import org.firstinspires.ftc.teamcode.bots.OdometryBot;
import org.openftc.easyopencv.OpenCvCamera;

@Autonomous(name = "AutoCloserLeft", group = "Auto")
public class AutoCloserLeft extends LinearOpMode {
    protected OdometryBot odometryBot = new OdometryBot(this);
    private OpenCvCamera controlHubCam;
    @Override
    public void runOpMode() throws InterruptedException {

        odometryBot.isAuto = true;
        odometryBot.init(hardwareMap);
        ColorDetectionBot colorDetectionBot = new ColorDetectionBot(this);
        controlHubCam = colorDetectionBot.initOpenCV(hardwareMap, controlHubCam);

        FtcDashboard dashboard = FtcDashboard.getInstance();
        telemetry = new MultipleTelemetry(telemetry, dashboard.getTelemetry());
        FtcDashboard.getInstance().startCameraStream(controlHubCam, 30);

        waitForStart();
        int position = colorDetectionBot.detect(); //call method from ColorDetection to know left/middle/right
        telemetry.addData("Position of pixel", position);
        telemetry.update();

        if (position == 1) {
            odometryBot.driveToCoordinate(15000, -60000, 0, 1000, 0.5, false);
            odometryBot.waitForCoordinateDrive();

        } else if (position == 2) {
            odometryBot.driveToCoordinate(15000, -60000, 0, 1000, 0.5, false);
            odometryBot.waitForCoordinateDrive();
        } else if (position == 3) {
            odometryBot.driveToCoordinate(15000, -60000, 0, 1000, 0.5, false);
            odometryBot.waitForCoordinateDrive();
        }

        controlHubCam.stopStreaming();
    }
}
