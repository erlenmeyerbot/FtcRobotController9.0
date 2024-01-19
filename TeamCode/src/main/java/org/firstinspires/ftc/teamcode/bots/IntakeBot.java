package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeBot extends LinearslideBot{

    public DcMotorEx intake = null;
    public CRServo topRollers = null;

    public IntakeBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap){
        super.init(ahwMap);
        intake = hwMap.get(DcMotorEx.class, "intake");
        topRollers = hwMap.get(CRServo.class, "rollerservo");
        intake.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void runMotor(double power)
    {
        intake.setPower(power);
        topRollers.setPower(power

        );
    }
}
