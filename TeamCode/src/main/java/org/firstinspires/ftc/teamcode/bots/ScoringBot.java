package org.firstinspires.ftc.teamcode.bots;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ScoringBot extends BotBot{
    Servo scoringArm = null;
    private final double close = 0.16; //UNTESTEDD VALUES
    private final double open = 0.06; // UNTESTED VAL

    public ScoringBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap){
        super.init(ahwMap);
        scoringArm = hwMap.get(Servo.class, "scoring arm");
    }
    public void closeArm(boolean button){
        if(button) {
            scoringArm.setPosition(close);
        }
    }

    public void openArm(boolean button){
        if(button) {
            scoringArm.setPosition(open);
        }
    }
}
