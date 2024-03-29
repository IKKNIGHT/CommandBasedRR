package org.firstinspires.ftc.teamcode;


import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.utils.Trajectory;

public class Path {

    public void followTrajectory(Trajectory traj,MecanumDrive drive){
        drive.updatePoseEstimate();
        for(int i =0;i <traj.getX().size();i++){
            Actions.runBlocking(
                    drive.actionBuilder(drive.pose)
                            .splineTo(new Vector2d((double)traj.getX().get(i), (double)traj.getY().get(i)), (double)traj.getTheta().get(i) + 0.00001)
                            .build());
            drive.updatePoseEstimate();
        }
    }

}
