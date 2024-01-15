package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Teleops;

public class LinearslideBot extends GyroBot{

    public DcMotorEx rightMotor = null;
    public DcMotorEx leftMotor = null;
    public int slidePosition = 0;

    public boolean isDown = true;

    public final double endPosition = 300;
    @Override
    public void init(HardwareMap ahwMap){
        super.init(ahwMap);
        rightMotor = hwMap.get(DcMotorEx.class, "slide right");
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        rightMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightMotor.setPower(0);

        leftMotor = hwMap.get(DcMotorEx.class, "slide left");
        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        leftMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftMotor.setPower(0);
    }

    public LinearslideBot(LinearOpMode opMode) {super(opMode);}

    public int getSlidePosition(){
        return rightMotor.getCurrentPosition();
    }

//    public void slideDown(boolean button) {
//        if (button && slidePosition>0){
//            rightMotor.setPower(-1);
//            leftMotor.setPower(1);
//            slidePosition--;
//        } else {
//            rightMotor.setPower(0);
//            leftMotor.setPower(0);
//        }
//    }

//    public void slideUp(boolean button) {
//        if (button && slidePosition<endPosition){
//            rightMotor.setPower(1);
//            leftMotor.setPower(-1);
//            slidePosition++;
//        } else {
//            rightMotor.setPower(0);
//            leftMotor.setPower(0);
//        }
//    }

    public void slideControl(float input) {
        opMode.telemetry.addData("nigga chink beaner gypsy", leftMotor.getCurrentPosition());
        opMode.telemetry.addData("target left", leftMotor.getTargetPosition());
        opMode.telemetry.addData("nigga chink beaner gypsy 2", rightMotor.getCurrentPosition());
        opMode.telemetry.addData("target right", rightMotor.getTargetPosition());
        opMode.telemetry.update();

        if ((rightMotor.getCurrentPosition() < 1900 && rightMotor.getCurrentPosition() >= 0) ||
                (rightMotor.getCurrentPosition() >= 1900 && input > 0) ||
                (rightMotor.getCurrentPosition() <= 5 && input < 0)) {
            leftMotor.setTargetPosition((int) (rightMotor.getCurrentPosition() - input * 200));

            rightMotor.setTargetPosition((int) (rightMotor.getCurrentPosition() - input * 200));


        } else if (rightMotor.getCurrentPosition() > 1900) {
            leftMotor.setTargetPosition(1900);

            rightMotor.setTargetPosition(1900);
        } else if (rightMotor.getCurrentPosition() < 0) {
            leftMotor.setTargetPosition(0);

            rightMotor.setTargetPosition(0);
        } else {
            leftMotor.setTargetPosition(leftMotor.getCurrentPosition());

            rightMotor.setTargetPosition(rightMotor.getCurrentPosition());
        }
        leftMotor.setPower(1);
        leftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightMotor.setPower(1);
        rightMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

//        if (rightMotor.getCurrentPosition() > 2000 && targetPosition < 0){
//            leftMotor.setPower(targetPosition);
//            rightMotor.setPower(targetPosition);
//        }
//        else if (rightMotor.getCurrentPosition() <= 5 && targetPosition > 0){
//            leftMotor.setPower(targetPosition);
//            rightMotor.setPower(targetPosition);
//        }
//        else if (rightMotor.getCurrentPosition() < 1900 && rightMotor.getCurrentPosition() > 5){
//            leftMotor.setPower(targetPosition);
//            rightMotor.setPower(targetPosition);
        }




}
