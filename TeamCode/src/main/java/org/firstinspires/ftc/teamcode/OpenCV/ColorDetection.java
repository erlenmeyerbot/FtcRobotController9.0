package org.firstinspires.ftc.teamcode.OpenCV;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.openftc.easyopencv.OpenCvCamera;

//@Autonomous(name = "Color Detection")

public class ColorDetection extends LinearOpMode {
    OpenCvCamera camera;

    @Override
    public void runOpMode() throws InterruptedException {


    }

    public enum autoSide {
        RED,
        BLUE
    }

    public autoSide side = autoSide.RED;

    final int cameraWidth = 1280;
    final int cameraHeight = 720;
    final int offsetX = 0;
    final int offsetY = 120;
    final int spacing = 16;

//    public CameraBot(LinearOpMode opMode) {
//        super(opMode);
//    }

}
