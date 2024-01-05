package org.firstinspires.ftc.teamcode.bots;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ScoringBot extends IntakeBot{
    public Servo servoLeft;
    public Servo servoRight;

    double close = 0.4;
    double open = 0.64;

    public ScoringBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap){
        super.init(ahwMap);
        servoLeft = hwMap.get(Servo.class, "left servo");
        servoRight = hwMap.get(Servo.class, "right servo");

    }
    public void closeArm(boolean button){
        if(button) {
            servoLeft.setPosition(close);
            servoRight.setPosition(close);
        }
    }

    public void openArm(boolean button){
        if(button) {
            servoLeft.setPosition(open);
            servoRight.setPosition(open);
        }
    }
}
