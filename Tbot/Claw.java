package Tbot;

public class Claw extends Base {
    Servo      thumb    = null;
    Servo      finger   = null;

    thumb           = hardwareMap.get(Servo.class, "thumb");
    finger          = hardwareMap.get(Servo.class, "finger");
        
    void close() {
        finger.setPosition(0); // calibrate
        thumb.setPosition(0); // calibrate
    }

    void open() {
        finger.setPosition(0); // calibrate
        thumb.setPosition(0); // calibrate
    }

    boolean clawClosed() {
        if (finger.getPosition() <= 0 && thumb.getPosition() <= 0) { // calibrate
            return true;
        } else {
            return false;
        }
    }

    void teleOp() {
        if (gamepad1.right_trigger > 0.1) {
            if (claw.clawClosed()) {
                claw.open();
            } else {
                claw.close();
            }
        }
    }
}
