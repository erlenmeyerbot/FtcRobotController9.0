package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bots.HangBot;

@TeleOp(name = "Pov niggers in paris")
public class TuneIntakeAndOuttake extends LinearOpMode {
    private HangBot robot = new HangBot(this);

    public float slideInput = 0;

    public ElapsedTime intakeTimer = new ElapsedTime();

    public float spinTog = 0;
    public ElapsedTime spinTimer = new ElapsedTime();

    public float releaseManual = 1f;
    public float releaseManual2 = 1f;
    public float releaseManual3 = 0.5f;
    public ElapsedTime manualTimer = new ElapsedTime();

    @Override
    public void runOpMode() throws InterruptedException {

        robot.isAuto = false;

        robot.init(hardwareMap);

        releaseManual = 0.72f;
        releaseManual2 = 0.874f;
        releaseManual3 = 0.44f;

        robot.positionIntake(releaseManual, releaseManual2);
        robot.outtake.setPosition(releaseManual3);

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

            if (gamepad1.right_trigger < 0.5)
            {
                robot.slideControl(slideInput);
            }
            else {
                robot.slideControlJailbroken(1);
            }

            robot.driveByHandFieldCentric(gamepad1.left_stick_x, gamepad1.left_stick_y,
                    gamepad1.right_stick_x, gamepad1.left_stick_button, gamepad2.left_stick_x,
                    gamepad2.left_stick_y, 0, gamepad2.left_stick_button);

            robot.resetAngle(gamepad1.dpad_left);

            if (gamepad1.a && intakeTimer.time() > 0.5)
            {
                intakeTimer.reset();
                releaseManual = 1f;
                releaseManual2 = 0.924f;
                releaseManual3 = 0.68f;
            }
            if (gamepad1.x && intakeTimer.time() > 0.5)
            {
                intakeTimer.reset();
                releaseManual = 0.84f;
                releaseManual2 = 0.922f;
                releaseManual3 = 0.68f;
            }

            if (gamepad1.y && intakeTimer.time() > 0.5)
            {
                intakeTimer.reset();
                releaseManual = 0.72f;
                releaseManual2 = 0.874f;
                releaseManual3 = 0.34f;
            }

            if (gamepad2.dpad_up && manualTimer.time() > 0.5)
            {
                manualTimer.reset();
                releaseManual3 += 0.02;
            }
            if (gamepad2.dpad_down && manualTimer.time() > 0.5)
            {
                manualTimer.reset();
                releaseManual3 -= 0.02;
            }

            if (manualTimer.time() > 0.5)
            {
                if (gamepad2.right_trigger > 0.5) {
                    releaseManual += 0.02f;
                    manualTimer.reset();
                }
                if (gamepad2.left_trigger > 0.5) {
                    releaseManual -= 0.02f;
                    manualTimer.reset();
                }
            }

            if (manualTimer.time() > 0.5)
            {
                if (gamepad2.right_bumper) {
                    releaseManual2 += 0.002f;
                    manualTimer.reset();
                }
                if (gamepad2.left_bumper) {
                    releaseManual2 -= 0.002f;
                    manualTimer.reset();
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

            robot.launchDrone(gamepad1.left_trigger > 0.5);
            robot.intakeSpin.setPower(spinTog);

            robot.opMode.telemetry.addData("intake first hinge: ", releaseManual);
            robot.opMode.telemetry.addData("intake second hinge: ", releaseManual2);
            robot.opMode.telemetry.addData("outtake: ", releaseManual3);

            robot.opMode.telemetry.update();
            robot.positionIntake(releaseManual, releaseManual2);
            robot.outtake.setPosition(releaseManual3);
            //robot.onLoop(0, "manual drive");

        }
        robot.close();
    }

}
