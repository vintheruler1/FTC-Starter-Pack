---
sidebar: auto
---

# FTC Documentation

::: warning
This is not a full fledged documentation. Please check out the official FTC documentation [here.](https://ftctechnh.github.io/ftc_app/doc/javadoc/index.html)
:::

::: tip
We recommend that you have your Android Studios project already up and running.
:::

::: tip
There will be an example file linked on the github and at the bottom of this page.
:::


## TeleOp File

- Type: `Class`

- Description: `Create the file to allow you to do TeleOp so that way the files appear on the Driver Hub/Phone.`

```java
// Assuming file name is Main

package [...]

import [...]

@TeleOp(name = "TeleOpMain", group = "TeleOp") // Name is what appears on the Driver Hub/Phone. Group is Autonomous or TeleOp
public class Main extends LinearOpMode {

    // Define your motors, variables, etc, there

    @Override
    public void runOpMode() throws InterruptedException {

        // What ever is in here will be done when Init is pressed

        initRobot();

        waitForStart();

        while (opModeIsActive()) {

            // Here you can do stuff with game pad, motor power, etc

        }


    }

}

```


## Hardware Map
- Type: `Class`

- Description: `HardwareMap provides a means of retrieving runtime HardwareDevice instances according to the names with which the corresponding physical devices were associated during robot configuration.`

- Phone/Driver Hub: `3 Dots -> Configure Robot -> Edit -> Hub -> Control Hub` You can now add motors, sensors, servos, etc.

### Motors

```java
import com.qualcomm.robotcore.hardware.DcMotor; // import hardware -> DcMotor

private DcMotor frontLeftMotor = null; // Define this in the class which extends Linear_OpMode

frontLeftMotor = hardwareMap.get(DcMotor.class, "front_left"); // initRobot()
```

### Servos
```java
import com.qualcomm.robotcore.hardware.CRServo; // import hardware -> CRServo

private static servoOne CRServo = null; // Define this in the class which extends Linear_OpMode

frontLeftMotor = hardwareMap.get(CRServo.class, "one"); // initRobot()
```

### IMU

```java
import com.qualcomm.hardware.bosch.BNO055IMU;

BNO055IMU imu;

imu = hardwareMap.get(BNO055IMU.class, "imu"); // hardwareMap

BNO055IMU.Parameters parameters = new BNO055IMU.Parameters(); // define param
parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES; // use degrees instead of radians 
imu.initialize(parameters); // init the gyro

```