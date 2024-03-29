package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.Arrays;
import java.util.List;

public class PathGenerator extends LinearOpMode {
    boolean generating = true;
    List<Double> x= Arrays.asList();
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        FtcDashboard dashboard = FtcDashboard.getInstance();
        TelemetryPacket packet = new TelemetryPacket();
        waitForStart();
        while(opModeIsActive()){
            if (generating){
                packet.put("X : " , drive.pose.position.x);
                packet.put("Y : " , drive.pose.position.y);
                packet.put("Theta : " , drive.pose.heading.real);
                dashboard.sendTelemetryPacket(packet);
            }
        }
    }
}
