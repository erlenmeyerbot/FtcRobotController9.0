package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class IntakeBot extends LinearslideBot{

    public DcMotor linearSlide = null;
    public CRServo intakeSpin = null;
    public Servo left = null;
    public Servo right = null;

    public IntakeBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);

        linearSlide = hwMap.get(DcMotorEx.class, "horSlide");
        linearSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        linearSlide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linearSlide.setPower(0);

        intakeSpin = hwMap.get(CRServo.class, "intake");
        left = hwMap.get(ServoImplEx.class, "intakeLeft");
        right = hwMap.get(ServoImplEx.class, "intakeRight");
    }

    public void positionIntake(double position)
    {
        left.setPosition(position);
        right.setPosition(1 - position);
    }
}
