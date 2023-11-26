package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class IntakeBot extends BotBot{

    public DcMotorEx intake = null;

    public IntakeBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap){
        super.init(ahwMap);
        intake = hwMap.get(DcMotorEx.class, "intake");
    }

    public void runMotor(double power){
        intake.setPower(power);
    }
}
