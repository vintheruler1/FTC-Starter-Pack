package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name="ServoScannerOpMode", group="Linear Opmode")
public class ServoScannerOpMode extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Servo servo;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize your servo here
        servo = hardwareMap.servo.get("servo");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // Run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // Get gamepad input to control servo position
            double servoPosition = gamepad1.left_stick_y;

            // Clip the servo position to valid range (0.0 to 1.0)
            servoPosition = Range.clip(servoPosition, 0.0, 1.0);

            // Set servo position
            servo.setPosition(servoPosition);

            // Display servo position on telemetry
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Servo", "Position: %.2f", servoPosition);
            telemetry.update();
        }
    }
}
