package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class LinearslideBot extends GyroBot{

    public DcMotorEx rightMotor = null;
    public DcMotorEx leftMotor = null;
    public int slidePosition = 0;

    public boolean isDown = true;

    public final double endPosition = 300;
    @Override
    public void init(HardwareMap ahwMap){
        super.init(ahwMap);
        rightMotor = hwMap.get(DcMotorEx.class, "slide");
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setPower(0);

        leftMotor = hwMap.get(DcMotorEx.class, "slide");
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor.setPower(0);
    }

    public LinearslideBot(LinearOpMode opMode) {super(opMode);}

    public void slideDown(boolean button) {
        if (button && slidePosition>0){
            rightMotor.setPower(-1);
            leftMotor.setPower(1);
            slidePosition--;
        } else {
            rightMotor.setPower(0);
            leftMotor.setPower(0);
        }
    }

    public void slideUp(boolean button) {
        if (button && slidePosition<endPosition){
            rightMotor.setPower(1);
            leftMotor.setPower(-1);
            slidePosition++;
        } else {
            rightMotor.setPower(0);
            leftMotor.setPower(0);
        }
    }

    public void slideControl(float targetPosition) {

        if (rightMotor.getCurrentPosition() > 2000 && targetPosition < 0){
            leftMotor.setPower(targetPosition);
            rightMotor.setPower(targetPosition);
        }
        else if (rightMotor.getCurrentPosition() <= 5 && targetPosition > 0){
            leftMotor.setPower(targetPosition);
            rightMotor.setPower(targetPosition);
        }
        else if (rightMotor.getCurrentPosition() < 1900 && rightMotor.getCurrentPosition() > 5){
            leftMotor.setPower(targetPosition);
            rightMotor.setPower(targetPosition);
        }
    }



}
