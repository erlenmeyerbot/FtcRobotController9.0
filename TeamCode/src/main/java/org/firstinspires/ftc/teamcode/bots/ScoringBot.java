package org.firstinspires.ftc.teamcode.bots;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ScoringBot extends IntakeBot {
    public Servo servoLeft;
    public Servo servoRight;
    public Servo hingeLeft;
    public Servo hingeRight;

    double closeright = 0.2;
    double openright = 0.64;
    double offset = -0.1;

    public ScoringBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);

        servoLeft = hwMap.get(Servo.class, "left servo");
        servoRight = hwMap.get(Servo.class, "right servo");
        hingeRight = hwMap.get(Servo.class, "right hinge");
        hingeLeft = hwMap.get(Servo.class, "left hinge");

        hingeLeft.setPosition(.1);
        hingeRight.setPosition(.9);
        servoLeft.setPosition(closeright);
        servoRight.setPosition(openright);
    }

    public void armLeft(boolean button) {
        if (button) {
            servoLeft.setPosition(openright - (offset * 2));
        } else {
            servoLeft.setPosition(closeright - (offset * 2));
        }
    }

    public void armRight(boolean button) {
        if (button) {
            servoRight.setPosition(closeright + offset);
        } else {
            servoRight.setPosition(openright + offset);
        }
    }

    public void hingeControl(boolean input) {
        double start = 0.5;
        if (input) {
            hingeRight.setPosition(start + 0.4);
            hingeLeft.setPosition(start - .04);
        } else {
            hingeLeft.setPosition(start + 0.5);
            hingeRight.setPosition(start - 0.5);
        }
    }
//    public boolean isClawOpen(){
//        if(servoRight.getPosition()==)
//    }
}
