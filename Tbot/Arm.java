package Tbot;

public class Arm extends Basic{
    
    boolean stowed = false;

    public class Shoulder {
        public DcMotor  shoulder = null;
        shoulder = hardwareMap.get(DcMotor.class, "shoulder");
        final double ENCODER_CONSTANT = 100;

        public void goTo(float targetAngle) {
            double currentPosition=shoulder.getCurrentPosition();
            double targetPostion=currentPosition-targetAngle*

            shoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            shoulder.setTargetPosition(targetPostion);
            shoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            shoulder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            shoulder.setPower(0.2);
        }

        public void teleOp() {
            shoulder.setPower(gamepad2.left_stick_y);
            telemetry.addData("Shoulder: Power: %0.2f %, Position: %0.1f °",shoulder.getPower()*100,shoulder.getPosition());
        }

        public float getAngle() {
            return shoulder.getCurrentPosition()/ENCODER_CONSTANT;
        }
    }

    public class Elbow {
        public DcMotor  elbow = null;
        elbow = hardwareMap.get(DcMotor.class, "elbow");
        final double ENCODER_CONSTANT = 100;

        public void goTo(float targetAngle) {
            double currentPosition=shoulder.getCurrentPosition();
            double targetPostion=currentPosition-targetAngle*

            elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            elbow.setTargetPosition(targetPostion);
            elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elbow.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            elbow.setPower(0.2);
        }

        public void teleOp() {
            elbow.setPower(gamepad2.right_stick_y);
            telemetry.addData("Elbow: Power: %0.2f %, Position: %0.1f °",elbow.getPower()*100,elbow.getPosition());
        }

        public float getAngle() {
            return elbow.getCurrentPosition()/ENCODER_CONSTANT;
        }
    }

    public class Wrist {
        public DcMotor  wrist = null;
        wrist = hardwareMap.get(DcMotor.class, "wrist");
        final double ENCODER_CONSTANT = 100;

        public void goTo(float targetAngle) {
            double currentPosition=shoulder.getCurrentPosition();
            double targetPostion=currentPosition-targetAngle*

            wrist.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            wrist.setTargetPosition(targetPostion);
            wrist.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            wrist.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            wrist.setPower(0.2);
        }

        public void teleOp() {
            wrist.setPower(gamepad2.left_stick_x);
            telemetry.addData("Wrist: Power: %0.2f %, Position: %0.1f °",wrist.getPower()*100,wrist.getPosition());
        }

        public float getAngle() {
            return wrist.getCurrentPosition()/ENCODER_CONSTANT;
        }
    }
    
    Shoulder shoulder = new Shoulder();
    Elbow    elbow    = new Elbow();
    Wrist    wrist    = new Wrist();

    public void stowArm() {
        if (!stowed) {
            shoulder.goTo(100); // calibrate
            wrist.goTo(100); // calibrate
            elbow.goTo(100); // calibrate
        }
    }
}