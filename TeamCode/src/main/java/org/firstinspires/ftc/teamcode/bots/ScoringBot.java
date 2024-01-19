package org.firstinspires.ftc.teamcode.bots;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ScoringBot extends IntakeBot {
    public Servo servoLeft;
    public Servo servoRight;
    public Servo hingeLeft;
    public Servo hingeRight;

    public final double initPos = 0.45;

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

        hingeLeft.setPosition(initPos);
        hingeRight.setPosition(1 - initPos);
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

    public double getHingeRight (){
        return hingeRight.getPosition();
    }

    public double getHingeLeft(){
        return hingeLeft.getPosition();
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
        if (input) {//first input
            hingeRight.setPosition(.25);
            hingeLeft.setPosition(.75);
        } else {//second input
            hingeLeft.setPosition(initPos);
            hingeRight.setPosition(1 - initPos);//these positions
        }

    }
//    public boolean isClawOpen(){
//        if(servoRight.getPosition()==)
//    }
}
