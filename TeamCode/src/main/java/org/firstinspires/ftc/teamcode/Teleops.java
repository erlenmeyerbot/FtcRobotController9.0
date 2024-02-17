package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bots.HangBot;

@TeleOp(name = "Drive")
public class Teleops extends LinearOpMode {
    private HangBot robot = new HangBot(this);

    public float slideInput = 0;

    public boolean intakeTog = false; //false = down, true = up
    public ElapsedTime intakeTimer = new ElapsedTime();

    public float spinTog = 0;
    public ElapsedTime spinTimer = new ElapsedTime();

    public float releaseManual = 0.7f;
    public ElapsedTime manualTimer = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        robot.isAuto = false;

        robot.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){

            if (gamepad1.dpad_down)
            {
                slideInput = 1;
            }
            else if (gamepad1.dpad_up)
            {
                slideInput = -1;
            }
            else
            {
                slideInput = 0;
            }

            robot.slideControl(slideInput);

            robot.driveByHandFieldCentric(gamepad1.left_stick_x, gamepad1.left_stick_y,
                    gamepad1.right_stick_x, gamepad1.left_stick_button, gamepad2.left_stick_x,
                    gamepad2.left_stick_y, 0, gamepad2.left_stick_button);

            robot.resetAngle(gamepad2.a);

            if (gamepad1.a && intakeTimer.time() > 0.5)
            {
                intakeTog = !intakeTog;
                intakeTimer.reset();

                if (intakeTog)
                {
                    robot.positionIntake(0.7);
                    robot.outtake.setPosition(0.7);
                    releaseManual = 0.7f;
                }
                else if (!intakeTog)
                {
                    robot.positionIntake(0.1);
                    robot.outtake.setPosition(0.408);
                }
            }

            if (gamepad1.b && spinTimer.time() > 0.5)
            {
                if (spinTog == 0)
                {
                    spinTog = 1;
                }
                else if (spinTog == 1)
                {
                    spinTog = -0.2f;
                }
                else if (spinTog < -0.1)
                {
                    spinTog = 0;
                }

                spinTimer.reset();
            }

            if (gamepad1.x && manualTimer.time() > 0.5)
            {
                releaseManual += 0.01;
                robot.outtake.setPosition(releaseManual);
                manualTimer.reset();
            }

            if (gamepad1.y && manualTimer.time() > 0.5)
            {
                releaseManual -= 0.01;
                robot.outtake.setPosition(releaseManual);
                manualTimer.reset();
            }

            robot.intakeSpin.setPower(spinTog);

            robot.opMode.telemetry.update();
            robot.onLoop(0, "manual drive");

        }
        robot.close();
    }

}
