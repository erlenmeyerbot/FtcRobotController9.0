package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "Chonkers in beijing go right")
public class blindAutoOtherSide extends LinearOpMode {
    private GyroBot robot = new GyroBot(this);
    @Override
    public void runOpMode() {

        robot.isAuto = false;
        robot.init(hardwareMap);

        waitForStart();

        autoDrive(0, -1, 0);

        sleep(1300);

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
        robot.driveByHandFieldCentric(-strafe, -forward, -turn, false, 0, 0, 0, false);
    }

}
