package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.utils.Trajectory;

import java.util.Arrays;
import java.util.List;

public class PathGenerator extends LinearOpMode {
    boolean generating = true;


    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Trajectory trajectory = new Trajectory(Arrays.asList(drive.pose.position.x),Arrays.asList(drive.pose.position.y),Arrays.asList(drive.pose.heading.toDouble()),drive);
        Path path = new Path(drive);
        FtcDashboard dashboard = FtcDashboard.getInstance();
        TelemetryPacket packet = new TelemetryPacket();
        waitForStart();
        while(opModeIsActive()){
            telemetry.addLine("DS NOT SUPPORTED USE DASH BOARD URL : (192.168.43.1/dash)");
            telemetry.update();
            packet.put(trajectory.toString(),null);
            drive.updatePoseEstimate();
            packet.put("USE B TO START GENERATING" , null);
            if(gamepad1.b){
                generating = true;
            }
            if (generating){
                dashboard.clearTelemetry();
                packet.put("Y TO ADD POINT" , null);
                packet.put("X TO STOP GENERATING" , null);
                if (gamepad1.y|gamepad2.y){

                   // create a waypoint
                   trajectory.addPoint(drive.pose.position.x,drive.pose.position.y,drive.pose.heading.toDouble());
                }

                if(gamepad1.x|gamepad2.x){
                    generating = false;
                }
            }else {
                dashboard.clearTelemetry();
                packet.put("B TO GO BACK AND TO START GENERATING" , null);
                path.goToStartPose(trajectory);
                path.followTrajectory(trajectory);
                if(gamepad1.b){
                    path.goToStartPose(trajectory);
                    generating = true;
                }
            }
            dashboard.sendTelemetryPacket(packet);
        }
        if (isStopRequested()){
            packet.put(trajectory.toString(),null);
            dashboard.sendTelemetryPacket(packet);
        }
    }
}
