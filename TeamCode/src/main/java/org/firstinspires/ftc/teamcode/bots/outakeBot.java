package org.firstinspires.ftc.teamcode.bots;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class outakeBot extends BotBot{
    public Servo servoLeft;
    public Servo servoRight;
    public outakeBot(LinearOpMode opMode) {
        super(opMode);
    }
    public void init(HardwareMap ahwMap){
        super.init(ahwMap);
        servoLeft = hwMap.get(Servo.class, "Outtake servo left");
        servoRight = hwMap.get(Servo.class, "Outtake servo right");
    }
    public void open(boolean input){
        if (input){
            servoLeft.setPosition(0.64);
            servoRight.setPosition(0.64);
        }
    }
    public void close (boolean input){
        if(input){
            servoLeft.setPosition(0.4);
            servoRight.setPosition(0.4);
        }
    }
}
