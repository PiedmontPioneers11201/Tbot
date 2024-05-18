package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.*;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.*;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.*;


/*
 * !!! ATTENTION !!!
 * 
 * The classes Claw and Arm both need calibration to specific positions, as well as proper configuration.
 * Expansion Hub DcMotors should be named appropriatly (shoulder, elbowMotor, wristMotor), and Servos (thumb, finger)
 * 
 * Delete when calibrated.
 */

@TeleOp(name="TbotMainDrive", group ="Concept")
public class TbotMainDrive extends LinearOpMode
{
    DcMotor    motors[]  = new DcMotor[3];

    DcMotor    frontLeftDrive   = null;
    DcMotor    frontRightDrive  = null;
    DcMotor    backDrive        = null;
    
    //Servo      thumb            = null;
    //Servo      finger           = null;

    double frontLeftDrivePower = 0;
    double frontRightDrivePower = 0;
    double backDrivePower = 0;
    
    public double driveScale = 1;
    public double turnScale = -1;

    final double MIN_DRIVE_SCALE = 0.5;

    public class Claw {
        public void close() {
            finger.setPosition(0); // calibrate
            thumb.setPosition(0); // calibrate
        }

        public void open() {
            finger.setPosition(0); // calibrate
            thumb.setPosition(0); // calibrate
        }

        public boolean clawClosed() {
            if (finger.getPosition() <= 0 && thumb.getPosition() <= 0) { // calibrate
                return true;
            } else {
                return false;
            }
        }
    }

    public class Arm {

        boolean stowed = false;

        public class Shoulder {
            public DcMotor  shoulderMotor;
            double speedScale = 1;
            public Shoulder() {
                shoulderMotor = hardwareMap.get(DcMotor.class, "shoulderMotor");
            }
            final int ENCODER_CONSTANT = 100;
            public void opModeActivity() {
                shoulderMotor.setPower(gamepad2.left_stick_y*speedScale);
            }

            public float getAngle() {
                return shoulderMotor.getCurrentPosition()/ENCODER_CONSTANT;
            }
        }

        public class Elbow {
            public DcMotor  elbowMotor = null;
            double speedScale = 1;
            public Elbow() {
                elbowMotor = hardwareMap.get(DcMotor.class, "elbowMotor");
            }
            final int ENCODER_CONSTANT = 100;
            public void opModeActivity() {
                elbowMotor.setPower(gamepad2.right_stick_y*speedScale);
            }

            public float getAngle() {
                return elbowMotor.getCurrentPosition()/ENCODER_CONSTANT;
            }
        }

        public class Wrist {
            public DcMotor  wristMotor = null;
            double speedScale = 1;
            public Wrist() {
                wristMotor = hardwareMap.get(DcMotor.class, "wristMotor");
            }
            final int ENCODER_CONSTANT = 100;
            
            public void opModeActivity() {
                wristMotor.setPower(gamepad2.right_stick_x*speedScale);
            }

            public float getAngle() {
                return wristMotor.getCurrentPosition()/ENCODER_CONSTANT;
            }
        }

        public void stowArm() {
            if (!stowed) {
                
            }
        }
        

        Shoulder shoulder = new Shoulder();
        Elbow    elbow    = new Elbow();
        Wrist    wrist    = new Wrist();
    }

    void initRobot() {
        frontLeftDrive  = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        backDrive       = hardwareMap.get(DcMotor.class, "backDrive");

        thumb           = hardwareMap.get(Servo.class, "thumb");
        finger          = hardwareMap.get(Servo.class, "finger");
        
        frontLeftDrivePower = 0;
        frontRightDrivePower = 0;
        backDrivePower = 0;
    }

    
    @Override public void runOpMode() throws InterruptedException
    {
        initRobot();

        Claw claw = new Claw();
        Arm arm = new Arm();

        waitForStart();
        

        while (opModeIsActive())
        {
            double driveInput = gamepad1.left_stick_y;
            double turnInput  = gamepad1.right_stick_x;
            
            double scale = 1;
            double driveScale = (gamepad1.right_bumper || gamepad1.left_bumper)?1.0:MIN_DRIVE_SCALE;
            double turnScale  = (gamepad1.right_bumper || gamepad1.left_bumper)?1.0:MIN_DRIVE_SCALE;
            
            if (gamepad1.right_trigger >= 0.1 || gamepad1.left_trigger >= 0.1) {
                driveScale = MIN_DRIVE_SCALE-(gamepad1.right_trigger+gamepad1.left_trigger)/4;
                turnScale  = MIN_DRIVE_SCALE-(gamepad1.right_trigger+gamepad1.left_trigger)/4;
            }

            int direction = (driveInput>=0)?-1:1;
            
            frontLeftDrivePower = driveScale*driveInput+(turnScale*turnInput*direction);
            frontRightDrivePower = -driveScale*driveInput+(turnScale*turnInput*direction);
            backDrivePower = driveScale*driveInput;



            if (gamepad1.right_trigger > 0.1) {
                if (claw.clawClosed()) {
                    claw.open();
                } else {
                    claw.close();
                }
            }

            arm.shoulder.opModeActivity();
            arm.elbow.opModeActivity();
            arm.wrist.opModeActivity();
            
            frontLeftDrive.setPower(frontLeftDrivePower);
            frontRightDrive.setPower(frontRightDrivePower);
            backDrive.setPower(backDrivePower);
            
            telemetry.addData("frontLeftDrive Power", frontLeftDrivePower);
            telemetry.addData("frontRightDrive Power", frontRightDrivePower);
            telemetry.addData("backDrive Power", backDrivePower);
            
            telemetry.addData("scale", scale);
            
            telemetry.addData("shoulder", arm.shoulder.getAngle());
            telemetry.addData("elbow", arm.elbow.getAngle());
            
            telemetry.update();
        }
    }
}
