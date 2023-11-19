package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.bots.GyroBot;

@TeleOp(name = "TeleOp Drive")
public class Teleops extends LinearOpMode {
    private GyroBot robot = new GyroBot(this);

    @Override
    public void runOpMode() throws InterruptedException {
        robot.isAuto = false;
        robot.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
            robot.driveByHandFieldCentric(gamepad1.left_stick_x, gamepad1.left_stick_y,
                    gamepad1.right_stick_x, gamepad1.left_stick_button, gamepad2.left_stick_x,
                    gamepad2.left_stick_y, 0, gamepad2.left_stick_button);

            robot.opMode.telemetry.update();
            robot.onLoop(0, "manual drive");
        }
    }

}
