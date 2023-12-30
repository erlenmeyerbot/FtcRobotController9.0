package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.OpenCV.CameraDetection;
import org.firstinspires.ftc.teamcode.OpenCV.ColorDetection;
import org.firstinspires.ftc.teamcode.bots.GyroBot;

@Autonomous(name = "BlindAutoFurtherLeft")
public class BlindAutoFurtherLeft extends LinearOpMode {
    private ColorDetection robot = new ColorDetection(this);

    @Override
    public void runOpMode() {


        robot.isAuto = false;
        robot.init(hardwareMap);

        waitForStart();

        int pos = robot.detect();

        autoDrive(0, -1, 0);

        sleep(1320);

        autoDrive(0, 0, 0);

        sleep(1000);

        autoDrive(0, 1, 0);

        sleep(100);

        autoDrive(0, 0, 0);

        sleep(1000);

        autoDrive(-1, 0, 0);

        sleep(4500);

        autoDrive(0, 0, 0);

        robot.close();
    }

    public void autoDrive(double strafe, double forward, double turn) { //1 = right and backwards
        robot.driveByHandFieldCentric(strafe, forward, turn, false, 0, 0, 0, false);
    }

}