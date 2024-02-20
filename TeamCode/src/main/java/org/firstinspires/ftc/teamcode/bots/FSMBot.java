package org.firstinspires.ftc.teamcode.bots;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class FSMBot extends DroneBot{
    private ElapsedTime timeSince = new ElapsedTime();
    private ElapsedTime timer2 = new ElapsedTime();
    private ElapsedTime timer3 = new ElapsedTime();
    private ElapsedTime timer4 = new ElapsedTime();
    private ElapsedTime timer5 = new ElapsedTime();
    private ElapsedTime timer6 = new ElapsedTime();
    private ElapsedTime timer7 = new ElapsedTime();


    public final float INTAKE_ARM_INIT = 0.52f;
    public final float INTAKE_HINGE_INIT = 0.25f;
    public final float OUTTAKE_INIT = 0.4f;
    public final float INTAKE_ARM_TRANSFER = 0.53f;
    public final float INTAKE_HINGE_TRANSFER = 0.14f;
    public final float OUTTAKE_TRANSFER = 0.4f;
    public final float INTAKE_ARM_DOWN = 0.9f;
    public final float INTAKE_HINGE_DOWN = 0.46f;
    public final float OUTTAKE_DOWN = 0.4f;
    public final float OUTTAKE_SCORING = 0.67f;
    public final float INTAKE_ARM_DRIVE = 0.52f;
    public final float INTAKE_HINGE_DRIVE = 0.12f;
    public final float OUTTAKE_DRIVE = 0.72f;

    public static final int BASE_SLIDE_HEIGHT = 750;


    public enum gameState {
        INIT_READY,
        INTAKE_READY,
        INTAKE,
        TRANSFER_READY,
        TRANSFER,
        TRANSFER_COMPLETE,
        LINEAR_SLIDE_UP,
        HINGE_DOWN,
        SCORING,
        MOVE_AWAY_FROM_BOARD,
        LINEAR_SLIDE_DOWN,
        DRIVE,
        HANG_UP,
        HANG_DOWN,
        HANG;
    }

    public boolean auto;
    public boolean intakeDown = false;
    public boolean rollIntake = false;
    public boolean reverseIntake = false;
    public boolean startTransfer = false;

    public int slidePosition = 0;
    public int slideHeight = 0;
    public final int maxSlideHeight = 8;

    public boolean slideUp = false;
    public boolean hingeDown = false;


    public gameState currentState = gameState.INIT_READY;

    public FSMBot(LinearOpMode opMode) {
        super(opMode);
    }

    @Override
    public void init(HardwareMap ahwmap){
        super.init(ahwmap);
        timeSince.reset();
        auto = isAuto;
    }

    public void waitForState(gameState goalState) {
        telemetry.addData("Waiting for state ", goalState.toString());
        while (opMode.opModeIsActive() && currentState != goalState) {
            telemetry.addData("Current state: ", currentState);
            telemetry.update();
            sleep(50);
        }
        telemetry.addData("state reached: ", goalState);
        telemetry.update();
        sleep(100);
    }

    public void setIntake(boolean button1, boolean button2) {
        if (button1 || button2 && timer3.milliseconds() > 500) {
            intakeDown = true;
            currentState = gameState.INTAKE_READY;
            timer3.reset();
        }
    }

    public void drive(boolean button) {
        currentState = gameState.DRIVE;
    }


    public void startIntaking(boolean button1) {
        if (button1 && timer4.milliseconds() > 200) {
            rollIntake = !rollIntake;
            if (rollIntake){
                intakeSpin.setPower(.7);
            } else {
                intakeSpin.setPower(0);
            }
            timer4.reset();
        }
    }

    public void stopIntaking(boolean button1) {
        if (button1) {
            intakeSpin.setPower(0);
            rollIntake = false;
        }
    }

    public void reverseIntaking(boolean button) {
        if (button && timer6.milliseconds() > 200) {
            reverseIntake = !reverseIntake;
            if (reverseIntake){
                intakeSpin.setPower(-02);
            } else {
                intakeSpin.setPower(0);
            }
            timer6.reset();
        }
    }

    public void setStartTransfer(boolean button) {
        startTransfer = button;
    }



    public void setSlideHeight(boolean increaseHeight, boolean decreaseHeight) {

        if (timer5.milliseconds() > 200){
            if (increaseHeight) {
                slidePosition++;
            }
            if (decreaseHeight) {
                slidePosition--;
            }
            if (slidePosition < 0) {
                slidePosition = 0;
            }
            if (slidePosition > maxSlideHeight) {
                slidePosition = maxSlideHeight;
            }
            slideHeight = slidePosition * 300 + BASE_SLIDE_HEIGHT;
            if (currentState != gameState.LINEAR_SLIDE_UP) {
                //move slide to slideHeight
            }
            timer5.reset();
        }
    }

    public void slideUp(boolean up) {
        if (up) {
            slideUp = true;
        }
        currentState = gameState.LINEAR_SLIDE_UP;
    }

    public void slideDown(boolean down) {
        if (down) {
            slideUp = false;
        }
        currentState = gameState.LINEAR_SLIDE_DOWN;
    }

    protected void onTick() {
        super.onTick();
        if (isAuto) {
            switch (currentState) {
                case INIT_READY:
                    positionIntake(INTAKE_ARM_INIT, INTAKE_HINGE_INIT);
                    outtake.setPosition(OUTTAKE_INIT);
                    //set drone position
                    currentState = gameState.INTAKE_READY;
                    break;
                case INTAKE_READY:
                    if (intakeDown) {
                        positionIntake(INTAKE_ARM_DOWN, INTAKE_HINGE_DOWN);
                        outtake.setPosition(OUTTAKE_DOWN);
                        currentState = gameState.INTAKE;
                    }
                    break;
                case INTAKE:
                    if (startTransfer) {
                        stopIntaking(true);
                        positionIntake(INTAKE_ARM_TRANSFER, INTAKE_HINGE_TRANSFER);
                        outtake.setPosition(OUTTAKE_TRANSFER);
                        timer2.reset();
                        currentState = gameState.TRANSFER;
                    }
                    break;
                case TRANSFER:
                    if (timer2.milliseconds() > 1000) {
                        //start outrolling intake
                        intakeSpin.setPower(-0.8);
                        currentState = gameState.TRANSFER_COMPLETE;
                    }
                    break;
                case TRANSFER_COMPLETE:
                    if (timer2.milliseconds() > 2000) {
                        intakeSpin.setPower(0);
                        positionIntake(INTAKE_ARM_DRIVE, INTAKE_HINGE_DRIVE);
                        currentState = gameState.LINEAR_SLIDE_UP;
                    }
                    break;
                case LINEAR_SLIDE_UP:
                    if (slideUp) {
                        //go to slideHeight
                        slideTarget = slideHeight;
                        currentState = gameState.HINGE_DOWN;
                    }
                    break;
                case HINGE_DOWN:
                    //move outtake down
                    outtake.setPosition(OUTTAKE_SCORING);
                    currentState = gameState.SCORING;
                    break;
                case SCORING:
                    slideTarget = slideHeight;
                    if (!slideUp) {
                        currentState = gameState.LINEAR_SLIDE_DOWN;
                    }
                    break;
                case LINEAR_SLIDE_DOWN:
                    slideTarget = 10;
                    currentState = gameState.DRIVE;
                case DRIVE:
                    //set intake position
                    positionIntake(INTAKE_ARM_DRIVE, INTAKE_HINGE_DRIVE);
                    //move linear slide down
                    slideTarget = 10;
                    //set hinge position
                    outtake.setPosition(OUTTAKE_DRIVE);
                    break;
                case HANG_UP:
                    //move hang up to height
                    currentState = gameState.DRIVE;
                    break;
                case HANG_DOWN:
                    //move hang down to lowest position
                    currentState = gameState.DRIVE;
                    break;
                case HANG:
                    //hang
                    //balance slides
                    currentState = gameState.DRIVE;
                    break;
            }
        }
    }

}
