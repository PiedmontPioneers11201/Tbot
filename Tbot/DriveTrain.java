package Tbot;

public class DriveTrain extends Base{
    DcMotor    motors[]  = new DcMotor[3];

    DcMotor    frontLeftDrive   = null;
    DcMotor    frontRightDrive  = null;
    DcMotor    backDrive        = null;
    
    double frontLeftDrivePower = 0;
    double frontRightDrivePower = 0;
    double backDrivePower = 0;
        
    double driveScale = 1;
    double turnScale = -1;

    frontLeftDrive  = hardwareMap.get(DcMotor.class, "frontLeftDrive");
    frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
    backDrive       = hardwareMap.get(DcMotor.class, "backDrive");
        
    frontLeftDrivePower = 0;
    frontRightDrivePower = 0;
    backDrivePower = 0;

    public void teleOp() {
        public double driveInput = gamepad1.left_stick_y;
        public double turnInput  = gamepad1.right_stick_x;

        frontLeftDrivePower = driveScale*driveInput+turnScale*turnInput;
        frontRightDrivePower = driveScale*driveInput-turnScale*turnInput;
        backDrivePower = driveScale*driveInput;
            
        public double scale = 1;
        
        if (gamepad1.dpad_up) {
            driveScale = (driveScale==0.2)?1:0.2;
            turnScale = (turnScale==-0.2)?-1:-0.2;
        }
        
        scale=Math.abs(frontLeftDrivePower)>scale?Math.abs(frontLeftDrivePower):scale;
        scale=Math.abs(frontRightDrivePower)>scale?Math.abs(frontRightDrivePower):scale;
        scale=Math.abs(backDrivePower)>scale?Math.abs(backDrivePower):scale;

        frontLeftDrive.setPower(frontLeftDrivePower);
        frontRightDrive.setPower(frontRightDrivePower);
        backDrive.setPower(backDrivePower);
            
        telemetry.addData("frontLeftDrive: Power: %.2f, Position %.2f °", frontLeftDrivePower*100,frontLeftDrive.getPosition());
        telemetry.addData("frontRightDrive: Power: %.2f, Position %.2f °", frontRightDrivePower*100,frontRightDrive.getPosition());
        telemetry.addData("backDrive: Power: %.2f, Position %.2f °", backDrivePower*100,backDrive.getPosition());
            
        telemetry.addData("scale", scale);
    }
}
