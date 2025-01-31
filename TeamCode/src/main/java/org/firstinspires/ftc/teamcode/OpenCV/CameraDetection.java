///*
// * Copyright (c) 2021 OpenFTC Team
// *
// * Permission is hereby granted, free of charge, to any person obtaining a copy
// * of this software and associated documentation files (the "Software"), to deal
// * in the Software without restriction, including without limitation the rights
// * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
// * copies of the Software, and to permit persons to whom the Software is
// * furnished to do so, subject to the following conditions:
// *
// * The above copyright notice and this permission notice shall be included in all
// * copies or substantial portions of the Software.
// * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
// * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
// * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
// * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
// * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
// * SOFTWARE.
// */
//
//package org.firstinspires.ftc.teamcode.OpenCV;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
//import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
//import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
//import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
//import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
//import org.firstinspires.ftc.teamcode.bots.GyroBot;
//import org.openftc.apriltag.AprilTagDetection;
//import org.openftc.easyopencv.OpenCvCamera;
//import org.openftc.easyopencv.OpenCvCameraFactory;
//import org.openftc.easyopencv.OpenCvCameraRotation;
//
//import java.util.ArrayList;
//
//@Autonomous
//public class CameraDetection extends GyroBot
//{
//    OpenCvCamera camera;
//    AprilTagDetectionPipeline aprilTagDetectionPipeline;
//
//    static final double FEET_PER_METER = 3.28084;
//
//    // Lens intrinsics
//    // UNITS ARE PIXELS
//    // NOTE: this calibration is for the C920 webcam at 800x448.
//    // You will need to do your own calibration for other configurations!
//    double fx = 578.272;
//    double fy = 578.272;
//    double cx = 402.145;
//    double cy = 221.506;
//
//    // UNITS ARE METERS
//    double tagsize = 0.166;
//
////    int ID_TAG_OF_INTEREST = 18; // Tag ID 18 from the 36h11 family
//    //Tag IDs of Back Drop
//    int leftLeft = 4;
//    int leftMiddle = 5;
//    int leftRight = 6;
//    int rightLeft = 1;
//    int rightMiddle = 2;
//    int rightRight = 3;
//
//    AprilTagDetection tagOfInterest = null;
//
//    public CameraDetection(LinearOpMode opMode) {
//        super(opMode);
//    }
//
//    @Override
//    public void init(HardwareMap ahwMap){
//        super.init(ahwMap);
//        hwMap = ahwMap;
//    }
//
//    public void runOpMode() {
//        int cameraMonitorViewId = hwMap.appContext.getResources().getIdentifier(
//                "cameraMonitorViewId", "id", hwMap.appContext.getPackageName());
//        camera = OpenCvCameraFactory.getInstance().createWebcam(
//                hwMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
//        aprilTagDetectionPipeline = new AprilTagDetectionPipeline(tagsize, fx, fy, cx, cy);
//
//        camera.setPipeline(aprilTagDetectionPipeline);
//        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
//            @Override
//            public void onOpened() {
//                camera.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
//            }
//
//            @Override
//            public void onError(int errorCode) {
//
//            }
//        });
//
//        //telemetry.setMsTransmissionInterval(50);
//
//        /*
//         * The INIT-loop:
//         * This REPLACES waitForStart!
//         */
//    }
//
//    public int detect() {
//        ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();
//
//        if(currentDetections.size() != 0)
//        {
//            boolean tagFound = false;
//
//            for(AprilTagDetection tag : currentDetections)
//            {
//                if(tag.id == leftLeft || tag.id == leftMiddle || tag.id == leftRight || tag.id == rightLeft || tag.id == rightMiddle || tag.id == rightRight)
//                {
//                    tagOfInterest = tag;
//                    tagFound = true;
//                    break;
//                }
//            }
//
//            if(tagFound)
//            {
//                opMode.telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
//                tagToTelemetry(tagOfInterest);
//            }
//            else
//            {
//                opMode.telemetry.addLine("Don't see tag of interest :(");
//
//                if(tagOfInterest == null)
//                {
//                    opMode.telemetry.addLine("(The tag has never been seen)");
//                }
//                else
//                {
//                    opMode.telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
//                    tagToTelemetry(tagOfInterest);
//                }
//            }
//
//        }
//        else
//        {
//            opMode.telemetry.addLine("Don't see tag of interest :(");
//
//            if(tagOfInterest == null)
//            {
//                opMode.telemetry.addLine("(The tag has never been seen)");
//            }
//            else
//            {
//                opMode.telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
//                tagToTelemetry(tagOfInterest);
//            }
//
//        }
//
//        opMode.telemetry.update();
//        sleep(20);
//
//        return tagOfInterest.id;
//    }
//
////        while (!isStarted() && !isStopRequested())
////        {
////            ArrayList<AprilTagDetection> currentDetections = aprilTagDetectionPipeline.getLatestDetections();
////
////            if(currentDetections.size() != 0)
////            {
////                boolean tagFound = false;
////
////                for(AprilTagDetection tag : currentDetections)
////                {
////                    if(tag.id == leftLeft || tag.id == leftMiddle || tag.id == leftRight || tag.id == rightLeft || tag.id == rightMiddle || tag.id == rightRight)
////                    {
////                        tagOfInterest = tag;
////                        tagFound = true;
////                        break;
////                    }
////                }
////
////                if(tagFound)
////                {
////                    telemetry.addLine("Tag of interest is in sight!\n\nLocation data:");
////                    tagToTelemetry(tagOfInterest);
////                }
////                else
////                {
////                    telemetry.addLine("Don't see tag of interest :(");
////
////                    if(tagOfInterest == null)
////                    {
////                        telemetry.addLine("(The tag has never been seen)");
////                    }
////                    else
////                    {
////                        telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
////                        tagToTelemetry(tagOfInterest);
////                    }
////                }
////
////            }
////            else
////            {
////                telemetry.addLine("Don't see tag of interest :(");
////
////                if(tagOfInterest == null)
////                {
////                    telemetry.addLine("(The tag has never been seen)");
////                }
////                else
////                {
////                    telemetry.addLine("\nBut we HAVE seen the tag before; last seen at:");
////                    tagToTelemetry(tagOfInterest);
////                }
////
////            }
////
////            telemetry.update();
////            sleep(20);
////        }
////
////        /*
////         * The START command just came in: now work off the latest snapshot acquired
////         * during the init loop.
////         */
////
////        /* Update the telemetry */
////        if(tagOfInterest != null)
////        {
////            telemetry.addLine("Tag snapshot:\n");
////            tagToTelemetry(tagOfInterest);
////            telemetry.update();
////        }
////        else
////        {
////            telemetry.addLine("No tag snapshot available, it was never sighted during the init loop :(");
////            telemetry.update();
////        }
////
////        /* Actually do something useful */
////        if(tagOfInterest == null || tagOfInterest.id == leftLeft){
////            //left backdrop left code
////        } else if (tagOfInterest.id == leftMiddle) {
////            // left backdrop middle code
////        } else if (tagOfInterest.id == leftRight) {
////            // left backdrop right code
////        } else if (tagOfInterest.id == leftMiddle) {
////            // right backdrop left code
////        } else if (tagOfInterest.id == leftMiddle) {
////            // right backdrop middle code
////        } else if (tagOfInterest.id == leftMiddle) {
////            // right backdrop right code
////        }
////
////        /* You wouldn't have this in your autonomous, this is just to prevent the sample from ending */
////        while (opModeIsActive()) {sleep(20);}
////    }
////
//    void tagToTelemetry(AprilTagDetection detection)
//    {
//        Orientation rot = Orientation.getOrientation(detection.pose.R, AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);
//
////        opMode.telemetry.addLine(String.format("\nDetected tag ID=%d", detection.id));
////        opMode.telemetry.addLine(String.format("Translation X: %.2f feet", detection.pose.x*FEET_PER_METER));
////        opMode.telemetry.addLine(String.format("Translation Y: %.2f feet", detection.pose.y*FEET_PER_METER));
////        telemetry.addLine(String.format("Translation Z: %.2f feet", detection.pose.z*FEET_PER_METER));
////        telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", rot.firstAngle));
////        telemetry.addLine(String.format("Rotation Pitch: %.2f degrees", rot.secondAngle));
////        telemetry.addLine(String.format("Rotation Roll: %.2f degrees", rot.thirdAngle));
//    }
//}