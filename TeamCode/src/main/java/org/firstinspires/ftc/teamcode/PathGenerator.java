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
        Trajectory trajectory = new Trajectory(Arrays.asList(drive.pose.position.x),Arrays.asList(drive.pose.position.y),Arrays.asList(drive.pose.heading.imag));
        FtcDashboard dashboard = FtcDashboard.getInstance();
        TelemetryPacket packet = new TelemetryPacket();
        waitForStart();
        while(opModeIsActive()){
            drive.updatePoseEstimate();
            packet.put("USE B TO START GENERATING" , null);
            packet.put("X : " , drive.pose.position.x);
            packet.put("Y : " , drive.pose.position.y);
            packet.put("Theta : " , drive.pose.heading.imag);
            if (generating){
                if (gamepad1.y|gamepad2.y){
                   // create a waypoint
                   trajectory.addPoint(drive.pose.position.x,drive.pose.position.y,drive.pose.heading.imag);
                }
                packet.put(trajectory.toString(),null);
                if(gamepad1.x|gamepad2.x){
                    generating = false;
                }
            }else {
                if (gamepad1.a){

                }
            }
            dashboard.sendTelemetryPacket(packet);
        }
    }
}
