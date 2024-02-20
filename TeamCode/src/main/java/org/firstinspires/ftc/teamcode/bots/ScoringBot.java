package org.firstinspires.ftc.teamcode.bots;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ScoringBot extends OdometryBot {
    public Servo outtake;
    public Servo hingeLeft;
    public Servo hingeRight;

    public final double initPos = 0.45;
    public final double relPos = 0.8;

    double closed = 0.2;
    double open = 0.25;

    public ScoringBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);

        outtake = hwMap.get(Servo.class, "outtake");

//        outtake.setPosition(open);

    }

    public double getHingeRight (){
        return hingeRight.getPosition();
    }

    public double getHingeLeft(){
        return hingeLeft.getPosition();
    }

    public void releasePixels(boolean toggle) {
//        if (toggle) {
//            release.setPosition(closed);
//        } else {
//            release.setPosition(open);
//        }
    }

    public void hingeControl(boolean input) {
//        if (input) {//first input
//            hingeRight.setPosition(1 - relPos);
//            hingeLeft.setPosition(relPos);
//        } else {//second input
//            hingeRight.setPosition(1 - initPos);//these positions
//            hingeLeft.setPosition(initPos);
//        }

    }
//    public boolean isClawOpen(){
//        if(servoRight.getPosition()==)
//    }
}
