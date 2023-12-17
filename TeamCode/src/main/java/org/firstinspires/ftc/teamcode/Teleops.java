package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.bots.DroneBot;
import org.firstinspires.ftc.teamcode.bots.GyroBot;
import org.firstinspires.ftc.teamcode.bots.IntakeBot;
import org.firstinspires.ftc.teamcode.bots.LinearslideBot;
import org.firstinspires.ftc.teamcode.bots.ScoringBot;

@TeleOp(name = "nigger Drive")
public class Teleops extends LinearOpMode {
    private GyroBot robot = new GyroBot(this);
    private DroneBot droneLauncher = new DroneBot(this);
    private IntakeBot intake = new IntakeBot(this);
    private ScoringBot scoringArm = new ScoringBot(this);
    private LinearslideBot nigger = new LinearslideBot(this);
    private ElapsedTime timer = new ElapsedTime();
    private int intakePower = 1;
    @Override
    public void runOpMode() throws InterruptedException {

        robot.isAuto = false;
        robot.init(hardwareMap);
        droneLauncher.init(hardwareMap);
        intake.init(hardwareMap);
        nigger.init(hardwareMap);
        scoringArm.init(hardwareMap);

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
                intake.runMotor(0.5 * intakePower);
                intakePower = intakePower * -1;
                timer.reset();
            }

            if (gamepad1.y && timer.time() > 0.3)
            {
                intake.runMotor(0);
                timer.reset();
            }

            nigger.slideControl(gamepad2.left_stick_y);

            robot.driveByHandFieldCentric(gamepad1.left_stick_x, gamepad1.left_stick_y,
                    gamepad1.right_stick_x*1.7, gamepad1.left_stick_button, gamepad2.left_stick_x,
                    gamepad2.left_stick_y, 0, gamepad2.left_stick_button);


            scoringArm.openArm(gamepad1.b);
            scoringArm.closeArm(gamepad1.a);

            droneLauncher.launchDrone(gamepad1.right_bumper);
            droneLauncher.retractDrone(gamepad1.left_bumper);

            robot.opMode.telemetry.update();
            robot.onLoop(0, "manual drive");
        }
        robot.close();
    }

}
