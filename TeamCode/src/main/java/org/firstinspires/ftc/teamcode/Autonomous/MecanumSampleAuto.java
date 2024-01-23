package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="MecanumAutoOpMode", group="Example")
public class MecanumSampleAuto extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeft, frontRight, rearLeft, rearRight;

    // Constants for motor encoder counts
    private static final int COUNTS_PER_REV = 1120;
    private static final double DRIVE_POWER = 0.5;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize your motors here
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        rearLeft = hardwareMap.dcMotor.get("rearLeft");
        rearRight = hardwareMap.dcMotor.get("rearRight");

        // Set motor directions
        frontLeft.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        rearLeft.setDirection(DcMotor.Direction.FORWARD);
        rearRight.setDirection(DcMotor.Direction.REVERSE);

        // Reset motor encoders
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rearRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // Set motor run mode to RUN_USING_ENCODER
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();
        runtime.reset();

        // Drive forward for a certain distance (in encoder counts)
        driveForward(500);

        // Stop the robot
        stopRobot();
    }

    private void driveForward(int targetCounts) {
        // Calculate target motor positions
        int frontLeftTarget = frontLeft.getCurrentPosition() + targetCounts;
        int frontRightTarget = frontRight.getCurrentPosition() + targetCounts;
        int rearLeftTarget = rearLeft.getCurrentPosition() + targetCounts;
        int rearRightTarget = rearRight.getCurrentPosition() + targetCounts;

        // Set target positions for the motors
        frontLeft.setTargetPosition(frontLeftTarget);
        frontRight.setTargetPosition(frontRightTarget);
        rearLeft.setTargetPosition(rearLeftTarget);
        rearRight.setTargetPosition(rearRightTarget);

        // Set motor run mode to RUN_TO_POSITION
        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rearRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Set motor powers to drive forward
        frontLeft.setPower(DRIVE_POWER);
        frontRight.setPower(DRIVE_POWER);
        rearLeft.setPower(DRIVE_POWER);
        rearRight.setPower(DRIVE_POWER);

        // Wait until all motors reach the target position
        while (opModeIsActive() && (frontLeft.isBusy() || frontRight.isBusy() || rearLeft.isBusy() || rearRight.isBusy())) {
            telemetry.addData("Status", "Driving forward...");
            telemetry.update();
        }

        // Stop the motors
        stopRobot();

        // Set motor run mode back to RUN_USING_ENCODER
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void stopRobot() {
        // Stop all motors
        frontLeft.setPower(0);
        frontRight.setPower(0);
        rearLeft.setPower(0);
        rearRight.setPower(0);

        // Reset motor run modes
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rearRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}
