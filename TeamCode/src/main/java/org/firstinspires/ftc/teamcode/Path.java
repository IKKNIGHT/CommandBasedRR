package org.firstinspires.ftc.teamcode;


import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.utils.Trajectory;

import java.util.Arrays;

public class Path {
    MecanumDrive drive;
    public Path(MecanumDrive mecanumDrive){
        drive = mecanumDrive;
    }
    public void followTrajectory(Trajectory traj){
        drive.updatePoseEstimate();
        for(int i =0;i <traj.getX().size();i++){
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(new Vector2d((double)traj.getX().get(i), (double)traj.getY().get(i)), (double)traj.getTheta().get(i))
                            .build());
            drive.updatePoseEstimate();
        }
    }
    public void goToStartPose(Trajectory traj){
        drive.updatePoseEstimate();
        for(int i = traj.getX().size();i >=0;i--){
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .strafeToLinearHeading(new Vector2d((double)traj.getX().get(i),(double)traj.getY().get(i)),(double)traj.getTheta().get(i))
                            .build());
            drive.updatePoseEstimate();
        }
    }


}
