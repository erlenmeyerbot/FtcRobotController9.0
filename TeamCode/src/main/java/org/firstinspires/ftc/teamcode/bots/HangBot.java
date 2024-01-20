package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HangBot extends DroneBot {
    public DcMotor motor = null;
    public Servo holdServo = null;

    public final double releasePos = 0.5;
    public final double startPos = 1;

    public boolean isDown = true;
    public int position = 0;

    public final double endPosition = 300;

    private ElapsedTime buttonTimer = new ElapsedTime();

    public HangBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        motor = hwMap.get(DcMotorEx.class, "slide");
        holdServo = hwMap.get(Servo.class, "hangservo");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setPower(0);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setTargetPosition(0);


        holdServo.setPosition(startPos);
    }

    public void hang (boolean highest, boolean lowest, boolean moveUp){
        if (highest) {
            motor.setPower(0.3);
            position = 1500;
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setTargetPosition(position);
        }
        else if (lowest) {
            motor.setPower(0.3);
            position = 0;
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setTargetPosition(position);
        }
        else if (moveUp) {
            motor.setPower(0.3);
            position += 20;
            motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            motor.setTargetPosition(position);
        }
        else {
            motor.setPower(0);
        }
    }

    public void releaseHang (boolean release)
    {
        if (release)
        {
            holdServo.setPosition(releasePos);
        }
    }
//    public void slideDownTape(boolean button) {
//        if (button && slidePosition > 0) {
//            motor.setPower(1);
//            slidePosition--;
//        }
//        //motor power vals are untested
//        else {
//            motor.setPower(0);
//        }
//    }
//
//    public void slideUpTape(boolean button) {
//        if (button && slidePosition < endPosition) {
//            motor.setPower(-1);
//            slidePosition++;
//        } else {
//            motor.setPower(0);
//        }
//    }
}


