package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class IntakeBot extends LinearslideBot{

    public CRServo intakeSpin = null;
    public Servo leftFirst = null;
    public Servo rightFirst = null;
    public Servo rightSecond = null;

    public IntakeBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap){
        super.init(ahwMap);
        intakeSpin = hwMap.get(CRServo.class, "intake");

        leftFirst = hwMap.get(ServoImplEx.class, "intakeLeft");
        rightFirst = hwMap.get(ServoImplEx.class, "intakeRight");

        rightSecond = hwMap.get(ServoImplEx.class, "intakeRight2");
    }

    public void positionIntake(double position, double position2)
    {
        leftFirst.setPosition(position - 0.03);
        rightFirst.setPosition(1 - position);

        rightSecond.setPosition(position2);
    }
}
