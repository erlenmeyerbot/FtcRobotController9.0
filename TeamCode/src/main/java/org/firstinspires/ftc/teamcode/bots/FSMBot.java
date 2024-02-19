package org.firstinspires.ftc.teamcode.bots;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class FSMBot extends HangBot{
    private ElapsedTime timeSince = new ElapsedTime();
    private ElapsedTime timer3 = new ElapsedTime();

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
        if (button1 || button2 && timer3.milliseconds() > 300) {
            intakeDown = !intakeDown;
            if (intakeDown) {
                currentState = gameState.INTAKE_READY;
            } else {
                currentState = gameState.DRIVE;
            }
            timer3.reset();
        }
    }

    public void startIntaking(boolean button1) {
        if (button1) {
            rollIntake = true;
        }
    }

    public void stopIntaking(boolean button1) {
        if (button1) {
            rollIntake = false;
        }
    }

    public void setSlideHeight(boolean increaseHeight, boolean decreaseHeight) {
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
        slideHeight = slidePosition*300 + 600;
        if (currentState != gameState.LINEAR_SLIDE_UP) {
            //move slide to slideHeight
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
        if (isAuto) {
            switch (currentState) {
                case INIT_READY:
                    //set intake position
                    //set outake position
                    //set drone position
                    currentState = gameState.INTAKE_READY;
                    break;
                case INTAKE_READY:
                    if (intakeDown) {
                        //intake down
                        //outake in receiving position
                        currentState = gameState.INTAKE;
                    }
                    break;
                case INTAKE:
                    if (!startTransfer) {
                        if (rollIntake) {
                            //spin intake
                        }
                    }
                    if (startTransfer) {
                        currentState = gameState.TRANSFER_READY;
                    }
                    break;
                case TRANSFER_READY:
                    //set intake up
                    //set outake position
                    currentState = gameState.TRANSFER;
                    break;
                case TRANSFER:
                    //start outrolling intake
                    currentState = gameState.TRANSFER_COMPLETE;
                    break;
                case TRANSFER_COMPLETE:
                    currentState = gameState.LINEAR_SLIDE_UP;
                    break;
                case LINEAR_SLIDE_UP:
                    if (slideUp) {
                        //go to slideHeight
                        currentState = gameState.HINGE_DOWN;
                    }
                    break;
                case HINGE_DOWN:
                    //move outtake down
                    currentState = gameState.SCORING;
                    break;
                case SCORING:
                    if (!slideUp) {
                        currentState = gameState.LINEAR_SLIDE_DOWN;
                    }
                    break;
                case LINEAR_SLIDE_DOWN:
                    //move linear slide down
                    //set hinge position
                    //set intake position
                    break;
                case DRIVE:
                    //nothing
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
