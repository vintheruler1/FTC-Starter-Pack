---
sidebar: auto
---

# Tutorial using Android Studios

::: tip
This also works with any Java built IDE from IntelliJ (if you don't know what this is, just use Android Studios :))
:::

## Prerequisites

You will need to have Android Studio installed on your computer. You can download it [here](https://developer.android.com/studio).

Java 8 is also required. You can download it [here](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html).

### Downloading the Pack

First, you will need to download the starter pack. You can do this by clicking the green button that says "Clone or download" and then click "Download ZIP".

<img src="https://github.com/vintheruler1/FTC-Starter-Pack/blob/main/images/github_clone.png?raw=true" width=35% height=35%>

Then, you will need to unzip the file. You can do this by right clicking the file and clicking "Extract All".

### Opening using Android Studios

You can now open the project in Android Studio. You can do this by opening Android Studio and clicking "Open an existing Android Studio project". Then, you will need to find the folder that you just unzipped and click "Open".

Congratulations! You have now installed the starter pack!

Make sure at the top of the file exporer where it says `Android`, switch it to `Project Files`. Then open the folder containing the Starter Pack.

## Configuration

Configuring your Android Studio's project.

### Auto Imports

Why Auto Imports? It makes life easier knowing that it will automatically import libraries that you will be using.

- Steps:

1. Settings

a. `Navigate to the Settings.`

b. `macOS -> Android Studios (next to apple logo) -> Settings`

b.`Windows -> Settings`

<img src="https://github.com/vintheruler1/FTC-Starter-Pack/blob/main/images/macos_settings_android_studios.png?raw=true" width=50% height=50% alttext="macos">

2. Turning it on 

a.`Editor -> General -> Auto Import`

b. `Checkmark ->  Add unambiguous imports on the fly.`

3. Make sure to click `Apply` and then `Ok`.

<img src="https://github.com/vintheruler1/FTC-Starter-Pack/blob/main/images/autoimport.png?raw=true" width=90% height=90%>

### Team Code Folder

Navigate to the following folder path here
`TeamCode.java.org.firstinspires.ftc.teamcode`

Congratulations! You have reached your TeamCode folder where you can now add files. 

We highly suggest creating 2 folders, one that says TeleOp and another oen that says Autonomous.

### Javadoc Reference

Please check out the official FTC documentation [here.](https://ftctechnh.github.io/ftc_app/doc/javadoc/index.html)

### FTC Sample OpModes

Navigate to the folder path here
`FtcRobotController.java.org.firstinspires.ftc.robotcontroller.external.samples`
Located in there are some sample OpModes that are in there. To use them, look for the line of code that says `@Disabled` and uncomment it (make it look like `//@Disabled`)


## Creating your first TeleOp (opMode)

### Creating the file

Right click on the TeleOp folder which we created earlier, click `New -> Class` and name it `OpMode`. Finally, where it says `Superclass`, type in `com.qualcomm.robotcore.eventloop.opmode.LinearOpMode`

At the top of the file, there will be a line of code that says `package org.firstinspires.ftc.teamcode.TeleOp;`. DO NOT TOUCH THIS.

Next, there should be a line defining the Class that extends LinearOpMode. Congratulations, we are ready to start defining and coding!

### Coding

Outside of the `public class ....`, add the this line of code `@TeleOp(name = "TeleOpMain", group = "TeleOp")`.

Lets define some motors and the IMU!


```java
package org.firstinspires.ftc.teamcode.TeleOp;

import [...]

@TeleOp(name = "TeleOpMain", group = "TeleOp")
public class OpMode extends LinearOpMode {

    private DcMotor topLeftMotor;
    private Gyroscope imu;

    @Override
    public void runOpMode() {

        imu = hardwareMap.get(Gryscope.class, "imu")
        topLeftMotor = hardwareMap.get(DcMotor.class, "topLeft")

        telemetry.addData("Status", "Initalized") // Basically print statements
        telemetry.update();

        waitForStart(); // wait for driver to press PLAY

        while (opModeIsActive()) { // Run until driver presses STOP

            telemetry.addData("Status", "Running");
            telemetry.update();

        }

    }
}

```

### Explanation

Whew, that was a lot. Lets get to explaning. 

At the start of the op mode there is an annotation that occurs before the class definition. This annotation states that this is a tele-operated (i.e., driver controlled) op mode:

`@TeleOp`. You can do the same for Autonomous to `@Autonomous`

```java
public class OpMode extends LinearOpMode {
```

You can see from the sample code that an op mode is defined as a Java class. In this example, the op mode name is called MyFIRSTJavaOpMode and it inherits characteristics from the LinearOpMode class.


```java
private DcMotor topLeftMotor;
private Gyroscope imu;
```

Here, 2 private member variables are created. These will hold references to the two configured devices that should be linked to the Driver Hub/Phone configuration file for the control hub.

```java
@Override
public void runOpMode() {
```

There is an overridden method called runOpMode. Every op mode of type LinearOpMode must implement this method. This method gets called when a user selects and runs the op mode.

```java
imu = hardwareMap.get(Gyroscope.class, "imu");
motorTest = hardwareMap.get(DcMotor.class, "motorTest");
digitalTouch = hardwareMap.get(DigitalChannel.class, "digitalTouch");
sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
servoTest = hardwareMap.get(Servo.class, "servoTest");
```

The hardwareMap object is available to use in the runOpMode method. It is an object of type HardwareMap class.

Note that when you attempt to retrieve a reference to a specific device in your op mode, the name that you specify as the second argument of the HardwareMap.get method must match the name used to define the device in your configuration file. For example, if you created a configuration file that had a DC motor named topLeft, then you must use this same name (it is case sensitive) to retrieve this motor from the hardwareMap object. If the names do not match, the op mode will throw an exception indicating that it cannot find the device.

```java
telemetry.addData("Status", "Initialized");
telemetry.update();
// Wait for the game to start (driver presses PLAY)
waitForStart();
```

Telemetry is essentially a print statement that shows up on the driver hub.

`waitForStart();` waits for the driver to press play

```java
// run until the end of the match (driver presses STOP)
while (opModeIsActive()) {
    telemetry.addData("Status", "Running");
    telemetry.update();

}
```

After a start command has been received, the op mode enters a while loop and keeps iterating in this loop until the op mode is no longer active (i.e., until the user pushes the stop button on the Driver Station):

### Uploading the code

Using a USB A to USB C cable, plug in the A port to the Laptop and then the C cable to the control hub which must be powered to a battery.

<img src="https://github.com/FIRST-Tech-Challenge/ftcdocs/raw/main/docs/source/programming_resources/tutorial_specific/android_studio/creating_op_modes/images/controlHubUSBConnected.jpg">

Please plug it into the USB C port!!

<img src="https://github.com/FIRST-Tech-Challenge/ftcdocs/raw/main/docs/source/programming_resources/tutorial_specific/android_studio/creating_op_modes/images/typeC.jpg">

Now at the top middle, it should say `TeamCode` and `Control Hub v1.0` or something around those lines.

Click the green and white arrow button to the right of `TeamCode`. It should be uploading to the Control Hub if it starts blinking blue.

Now, on your Phone/Driver Hub, wait about a minute and it should work. You can now pick your Tele Ops.

If you run the file, it should print out the telemetry.


::: warning

Common Errors:

joe amma

:::