package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class DroneBot extends ScoringBot{

    public Servo droneServo = null;

    public DroneBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap){
        super.init(ahwMap);
        droneServo = hwMap.get(Servo.class, "drone servo");
    }

    public void launchDrone(boolean input){
        if(input){
            droneServo.setPosition(1);
        }
    }
}
