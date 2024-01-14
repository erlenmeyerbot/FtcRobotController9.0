package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bots.HangBot;

@TeleOp(name = "Drive")
public class Teleops extends LinearOpMode {
    private HangBot robot = new HangBot(this);

    private ElapsedTime timer = new ElapsedTime();
    private double intakePower = 1;
    @Override
    public void runOpMode() throws InterruptedException {

        robot.isAuto = false;

        robot.init(hardwareMap);

        waitForStart();
        while(opModeIsActive()){
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
                robot.runMotor(intakePower);
                intakePower = intakePower * -1;
                timer.reset();
            }

            if (gamepad1.y && timer.time() > 0.3)
            {
                robot.runMotor(0);
                timer.reset();
            }

            robot.slideControl(gamepad2.right_stick_y);

            robot.driveByHandFieldCentric(gamepad1.left_stick_x, gamepad1.left_stick_y,
                    gamepad1.right_stick_x*1.7, gamepad1.left_stick_button, gamepad2.left_stick_x,
                    gamepad2.left_stick_y, 0, gamepad2.left_stick_button);

            robot.launchDrone(gamepad2.right_bumper);
            robot.retractDrone(gamepad2.left_bumper);
            robot.openArm(gamepad1.left_bumper);
            robot.closeArm(gamepad1.right_bumper);
            robot.slideUpTape(gamepad2.a);
            robot.slideDownTape(gamepad2.b);

            robot.opMode.telemetry.update();
            robot.onLoop(0, "manual drive");
        }
        robot.close();
    }

}
