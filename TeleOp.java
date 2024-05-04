import Tbot.Arm;
import Tbot.Claw;
import Tbot.DriveTrain;

public class TeleOp {
    @Override public void runOpMode() throws InterruptedException {
        Claw       claw       = new Claw();
        Arm        arm        = new Arm();
        DriveTrain driveTrain = new DriveTrain()

        waitForStart();

        while (opModeIsActive() && isStopRequested())
        {
            driveTrain.teleOp()

            arm.shoulder.teleOp();
            arm.elbow.teleOp();
            arm.wrist.teleOp();
        }
    }
}
