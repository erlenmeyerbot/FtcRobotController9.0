package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HangBot extends DroneBot {
    public DcMotor motor = null;

    public int slidePosition = 0;

    public boolean isDown = true;
    public int pussyniggafag = 0;

    public final double endPosition = 300;

    private ElapsedTime buttonTimer = new ElapsedTime();

    public HangBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);
        motor = hwMap.get(DcMotorEx.class, "hang");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setPower(0);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        motor.setTargetPosition(0);


    }

    public void hang (boolean tittynigga, boolean pussynigga, boolean assnigga){
        motor.setTargetPosition(pussyniggafag);
        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        if (tittynigga) {
            motor.setPower(0.3);
            pussyniggafag = 1500;
        }
        else if (pussynigga) {
            motor.setPower(0.3);
            pussyniggafag = 0;
        }
        else if (assnigga) {
            motor.setPower(0.3);
            pussyniggafag += 20;
        }
        else {
            motor.setPower(0);
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


