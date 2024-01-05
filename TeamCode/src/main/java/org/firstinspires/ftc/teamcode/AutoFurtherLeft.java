package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.OpenCV.ColorDetection;
import org.firstinspires.ftc.teamcode.bots.OdometryBot;

@Autonomous(name = "AutoFurtherLeft", group = "Auto")
public class AutoFurtherLeft extends LinearOpMode {
    protected OdometryBot odometryBot = new OdometryBot(this);

    @Override
    public void runOpMode() throws InterruptedException {
        odometryBot.isAuto = true;
        odometryBot.init(hardwareMap);
        waitForStart();
        ColorDetection colorDetectionBot = new ColorDetection();

        double position = colorDetectionBot.getX(); //call method from ColorDetection to know left/middle/right

        telemetry.addData("Position of pixel", position);
        telemetry.update();
        waitForStart();

    }
}
