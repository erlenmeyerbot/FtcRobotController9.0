package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HangBot extends DroneBot {

    public HangBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
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


