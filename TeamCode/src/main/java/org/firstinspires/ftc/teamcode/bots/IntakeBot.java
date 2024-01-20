package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class IntakeBot extends LinearslideBot{

    public DcMotorEx intake = null;
    public CRServo topRollers = null;
    public ServoImplEx intakeHold = null;

    public float startPos = 0.3f;
    public float endPos = 0.8f;

    public IntakeBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap){
        super.init(ahwMap);
        intake = hwMap.get(DcMotorEx.class, "intake");
        topRollers = hwMap.get(CRServo.class, "rollerservo");
        intakeHold = hwMap.get(ServoImplEx.class, "intakeHold");
        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intakeHold.setPosition(startPos);
        intakeHold.setPwmDisable();
    }

    public void runMotor(double power)
    {
        intake.setPower(power);
    }

    public void runServo(double power)
    {
        topRollers.setPower(power);
    }

    public void setPos(boolean pos)
    {
        if (pos)
        {
            intakeHold.setPosition(startPos);
        }
        else if (!pos)
        {
            intakeHold.setPosition(endPos);
        }
    }

    public void killServo(boolean kill)
    {
        if (!kill) {
            intakeHold.setPwmEnable();
        } else {
            intakeHold.setPwmDisable();
        }
    }
}
