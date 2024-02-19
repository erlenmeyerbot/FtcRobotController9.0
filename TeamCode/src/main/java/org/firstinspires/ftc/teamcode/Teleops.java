package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bots.HangBot;

@TeleOp(name = "Drive")
public class Teleops extends LinearOpMode {
    private HangBot robot = new HangBot(this);
    private boolean hangReleased = false;

    public boolean lowerIntake = false;

    public float slideInput = 0;

    private ElapsedTime timer = new ElapsedTime();
    private ElapsedTime pt = new ElapsedTime();
    private ElapsedTime lower = new ElapsedTime();

    private int step = 0;

    public boolean intaking = false;

    private double intakePower = 1;
    private ElapsedTime niggat = new ElapsedTime();
    private ElapsedTime chigga = new ElapsedTime();
    private ElapsedTime digga = new ElapsedTime();
    private boolean togLeft = false;
    private boolean togRight = false;

    private boolean togHinge = false;

    private ElapsedTime hangTime = new ElapsedTime();
    private boolean togHang = false;


    @Override
    public void runOpMode() throws InterruptedException {

        robot.isAuto = false;

        robot.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){

            if (!lowerIntake && gamepad2.dpad_right)
            {
                switch (step)
                {
                    case 0:
                        lower.reset();
                        robot.intakeHold.setPwmEnable();
                        robot.intakeHold.setPosition(0.3f);
                        step += 1;
                    case 1:
                        if (lower.time() > 0.5)
                        {
                            robot.intakeHold.setPwmDisable();
                            step += 1;
                            lower.reset();
                        }
                    case 2:
                        if (lower.time() > 0.5)
                        {
                            robot.intake.setPower(-1);
                            step += 1;
                            lower.reset();
                        }
                    default:
                        if (lower.time() > 0.5)
                        {
                            robot.intake.setPower(0);
                            step += 1;
                            lower.reset();
                            lowerIntake = true;
                        }
                }
            }

            /*if (gamepad1.x && timer.time() > 0.3)
            {
                if (intakePower == 0){
                    intake.runMotor(0.5);
                    intakePower = 1;
                }
                else if (intakePower == 1){
                    intake.runMotor(0);
                    intakePower = 2;
                }
                else if (intakePower == 2){
                    intake.runMotor(-0.3);
                    intakePower = 0;
                }
                timer.reset();
            }*/

            if (gamepad1.x && timer.time() > 0.3)
            {

                robot.runMotor(1*intakePower);

                intakePower = intakePower * -1;
                timer.reset();
            }

            if (gamepad1.y && timer.time() > 0.3)
            {
                robot.runMotor(0);
                timer.reset();
            }

            if (gamepad2.y)
            {
                robot.runMotor(0);
            }

            robot.runServo(-gamepad1.left_trigger + gamepad1.right_trigger);
            if (gamepad2.right_trigger > 0.5 && pt.time() > 0.3)
            {
                robot.killServo(false);

                robot.setPos(intaking);

                intaking = !intaking;
                pt.reset();
            }

            if (gamepad2.left_trigger > 0.5){
                robot.killServo(true);
            }

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

            robot.launchDrone(gamepad2.right_bumper);
            robot.retractDrone(gamepad2.left_bumper);

            if (gamepad1.left_bumper == true && niggat.time() > 0.3) {
                togLeft = !togLeft;
                robot.releasePixels(togLeft);
                niggat.reset();
            }

            robot.resetAngle(gamepad2.a);


            if (gamepad1.b == true && digga.time() > 0.3) {
                togHinge = !togHinge;
                robot.hingeControl(togHinge);
                digga.reset();
                /*if (robot.getSlidePosition()>1000&&togRight==false&&togLeft==false){
                    robot.hingeControl(false);
                }*/
            }
            robot.opMode.telemetry.addData("right hinge position", robot.getHingeRight());
            robot.opMode.telemetry.addData("left hinge position", robot.getHingeLeft());
            robot.opMode.telemetry.update();


            if (hangReleased) robot.hang(gamepad2.dpad_up, gamepad2.dpad_down, gamepad2.b);

            robot.releaseHang(gamepad2.dpad_left);
            if (gamepad2.dpad_left) hangReleased = true;

            robot.opMode.telemetry.addData("left", togLeft);
            robot.opMode.telemetry.addData("right", togRight);

            robot.opMode.telemetry.update();
            robot.onLoop(0, "manual drive");

        }
        robot.close();
    }

}
