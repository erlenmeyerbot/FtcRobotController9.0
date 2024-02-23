package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bots.FSMBot;

@TeleOp(name = "Drive")
public class Teleops extends LinearOpMode {
    private FSMBot robot = new FSMBot(this);
    private boolean hangReleased = false;

    public boolean lowerIntake = false;

    public float slideInput = 0;

    public boolean intakeTog = false; //false = down, true = up
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

//        releaseManual = 0.72f;
//        releaseManual2 = 0.25f;
//        releaseManual3 = 0.4f;
//
        robot.positionIntake(robot.INTAKE_ARM_INIT, robot.INTAKE_HINGE_INIT);
        robot.outtake.setPosition(robot.OUTTAKE_INIT);

        waitForStart();
        while(opModeIsActive()){

//            if (gamepad1.dpad_down)
//            {
//                slideInput = 1;
//            }
//            else if (gamepad1.dpad_up)
//            {
//                slideInput = -1;
//            }
//            else
//            {
//                slideInput = 0;
//            }
//
//            if (gamepad1.right_trigger < 0.5)
//            {
//                robot.slideControl(slideInput);
//            }
//            else {
//                robot.slideControlJailbroken(1);
//            }

            robot.driveByHandFieldCentric(gamepad1.left_stick_x, gamepad1.left_stick_y,
                    gamepad1.right_stick_x, gamepad1.left_stick_button, gamepad2.left_stick_x,
                    gamepad2.left_stick_y, gamepad2.right_stick_x, gamepad2.left_stick_button);

            robot.hangUp(gamepad2.dpad_up);
            robot.hangDown(gamepad2.dpad_down);


            robot.resetAngle(gamepad1.dpad_left);

            robot.setIntake(gamepad1.a, false);
            robot.startIntaking(gamepad1.b);
            robot.reverseIntaking(gamepad1.y);
            robot.setStartTransfer(gamepad1.x);
            robot.setSlideHeight(gamepad1.dpad_up, gamepad1.dpad_down);
            if (gamepad1.right_trigger > 0) {
                robot.slideUp(true);
            }
            if (gamepad1.left_trigger > 0) {
                robot.slideDown(true);
            }

//            if (gamepad1.a && intakeTimer.time() > 0.5)
//            {
//                intakeTimer.reset();
//                releaseManual = 1f;
//                releaseManual2 = 0.67f;
//                releaseManual3 = 0.68f;
//            }
//            if (gamepad1.x && intakeTimer.time() > 0.5)
//            {
//                intakeTimer.reset();
//                releaseManual = 0.84f;
//                releaseManual2 = 0.922f;
//                releaseManual3 = 0.68f;
//            }
//
//            if (gamepad1.y && intakeTimer.time() > 0.5)
//            {
//                intakeTimer.reset();
//                releaseManual = 0.76f;
//                releaseManual2 = 0.2f;
//                releaseManual3 = 0.4f;
//            }

            robot.launchDrone(gamepad2.left_trigger > 0.5);
//            robot.intakeSpin.setPower(spinTog);

//            robot.opMode.telemetry.addData("intakeDown", robot.intakeDown);
//            robot.opMode.telemetry.addData("intakeSpin", robot.rollIntake);
//            robot.opMode.telemetry.addData("slidePosition", robot.slidePosition);
//            robot.opMode.telemetry.addData("slideHeight", robot.slideHeight);
//            robot.opMode.telemetry.addData("slideTarget", robot.slideTarget);
//            robot.opMode.telemetry.addData("rightSlidePosition", robot.rightMotor.getCurrentPosition());
//            robot.opMode.telemetry.addData("slideTarget", robot.leftMotor.getCurrentPosition());
//
//            robot.opMode.telemetry.addData("state", robot.currentState);

            robot.opMode.telemetry.update();
//            robot.positionIntake(releaseManual, releaseManual2);
//            robot.outtake.setPosition(releaseManual3);
            robot.onLoop(0, "manual drive");

        }
        robot.close();
    }

}
