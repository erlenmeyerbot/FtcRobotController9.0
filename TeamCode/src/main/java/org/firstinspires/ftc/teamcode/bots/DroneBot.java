package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class DroneBot extends BotBot{

    public Servo launcher = null;
    private final double launch = 0.13;
    private final double retract = 0.03;

    public DroneBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap){
        super.init(ahwMap);
        launcher = hwMap.get(Servo.class, "drone");
    }

    public void launchDrone(boolean right_bumper){
        if(right_bumper) {
            launcher.setPosition(launch);
        }
    }

    public void retractDrone(boolean left_bumper){
        if(left_bumper) {
            launcher.setPosition(retract);
        }
    }
}
