package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import org.firstinspires.ftc.teamcode.utils.Trajectory;

import java.util.Arrays;

public class BasicAuto extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Trajectory truss = new Trajectory(Arrays.asList(0,10,30),Arrays.asList(0,10,30),Arrays.asList(Math.toRadians(0),Math.toRadians(10),Math.toRadians(30)),drive);
        while (opModeInInit()){
            if(gamepad1.x){
                // change side
                truss.reflect();
            }
        }
        Path path = new Path(drive);
        waitForStart();
        path.followTrajectory(truss);

    }
}
