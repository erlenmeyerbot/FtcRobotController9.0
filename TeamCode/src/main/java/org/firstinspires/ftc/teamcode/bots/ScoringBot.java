package org.firstinspires.ftc.teamcode.bots;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

public class ScoringBot extends IntakeBot {

    public ScoringBot(LinearOpMode opMode) {
        super(opMode);
    }

    public Servo outtake;

    @Override
    public void init(HardwareMap ahwMap) {
        super.init(ahwMap);

        outtake = hwMap.get(ServoImplEx.class, "outtake");
    }
//    public boolean isClawOpen(){
//        if(servoRight.getPosition()==)
//    }
}
