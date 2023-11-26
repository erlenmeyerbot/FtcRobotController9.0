package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.bots.DroneBot;
import org.firstinspires.ftc.teamcode.bots.GyroBot;
import org.firstinspires.ftc.teamcode.bots.IntakeBot;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Chonkers in beijing go left")
public class blindAuto extends LinearOpMode {
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
        robot.driveByHandFieldCentric(strafe, forward, turn, false, 0, 0, 0, false);
    }

}
