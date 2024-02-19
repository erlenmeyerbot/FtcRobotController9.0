package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImpl;

public class DroneBot extends ScoringBot{
    public Servo droneServo;
    public DroneBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwMap){
        super.init(ahwMap);
        droneServo = hwMap.get(ServoImpl.class, "drone servo");
    }

    public void launchDrone(boolean input){
        if(input){
            droneServo.setPosition(0.2);
        }
    }
}
