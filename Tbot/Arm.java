package Tbot;

public class Arm extends Base{
    
    boolean stowed = false;

    public class Shoulder {
        DcMotor  shoulderMotor = null;
        shoulderMotor = hardwareMap.get(DcMotor.class, "shoulderMotor");
        final double ENCODER_CONSTANT = 100;

        void goTo(float targetAngle) {
            double currentPosition=shoulderMotor.getCurrentPosition();
            double targetPostion=currentPosition-targetAngle*

            shoulderMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            shoulderMotor.setTargetPosition(targetPostion);
            shoulderMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            shoulderMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            shoulderMotor.setPower(0.2);
        }

        void teleOp() {
            shoulderMotor.setPower(gamepad2.left_stick_y);
            telemetry.addData("Shoulder: Power: %0.2f %, Position: %0.1f °",shoulderMotor.getPower()*100,shoulderMotor.getPosition());
        }

        public float getAngle() {
            return shoulderMotor.getCurrentPosition()/ENCODER_CONSTANT;
        }
    }

    public class Elbow {
        DcMotor  elbowMotor = null;
        elbowMotor = hardwareMap.get(DcMotor.class, "elbowMotor");
        final double ENCODER_CONSTANT = 100;

        void goTo(float targetAngle) {
            double currentPosition=shoulderMotor.getCurrentPosition();
            double targetPostion=currentPosition-targetAngle*

            elbowMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            elbowMotor.setTargetPosition(targetPostion);
            elbowMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            elbowMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            elbowMotor.setPower(0.2);
        }

        void teleOp() {
            elbowMotor.setPower(gamepad2.right_stick_y);
            telemetry.addData("Elbow: Power: %0.2f %, Position: %0.1f °",elbowMotor.getPower()*100,elbowMotor.getPosition());
        }

        float getAngle() {
            return elbowMotor.getCurrentPosition()/ENCODER_CONSTANT;
        }
    }

    public class Wrist {
        DcMotor  wristMotor = null;
        wristMotor = hardwareMap.get(DcMotor.class, "wristMotor");
        final double ENCODER_CONSTANT = 100;

        void goTo(float targetAngle) {
            double currentPosition=shoulderMotor.getCurrentPosition();
            double targetPostion=currentPosition-targetAngle*

            wristMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

            wristMotor.setTargetPosition(targetPostion);
            wristMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            wristMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            wristMotor.setPower(0.2);
        }

        void teleOp() {
            wristMotor.setPower(gamepad2.left_stick_x);
            telemetry.addData("Wrist: Power: %0.2f %, Position: %0.1f °",wristMotor.getPower()*100,wristMotor.getPosition());
        }

        float getAngle() {
            return wristMotor.getCurrentPosition()/ENCODER_CONSTANT;
        }
    }
    
    Shoulder shoulderMotor = new Shoulder();
    Elbow    elbowMotor    = new Elbow();
    Wrist    wristMotor    = new Wrist();

    public void stowArm() {
        if (!stowed) {
            shoulderMotor.goTo(100); // calibrate
            wristMotor.goTo(100); // calibrate
            elbowMotor.goTo(100); // calibrate
        }
    }
}