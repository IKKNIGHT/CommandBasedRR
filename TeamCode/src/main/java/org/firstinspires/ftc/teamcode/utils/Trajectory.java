package org.firstinspires.ftc.teamcode.utils;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;

import org.firstinspires.ftc.teamcode.MecanumDrive;

import java.util.List;

public class Trajectory {
    List<Object> myX,myY,myTheta;
    Pose2d startpose;
    double startheading;
    Vector2d startV;
    public Trajectory(List<Object> x, List<Object>y, List<Object>theta, MecanumDrive drive){
        if(x.isEmpty()|y.isEmpty()|theta.isEmpty()){
            throw new NullPointerException("X OR Y OR THETA IS EMPTY!!");
        }
        if(x.size()==y.size() && y.size()==theta.size()){
            for(int i = 0;i<x.size();i++){
                Double v = (Double)x.get(i)+(Double)y.get(i)+(Double)theta.get(i);
                if(!(v>=0|v<0)){
                    throw new ArrayStoreException("The x y or theta Array list does not have numbers in them");
                }
            }
        }else{
            throw new ArrayIndexOutOfBoundsException("One of the arrays (x,y,z) is bigger than the others");
        }
        myX = x;
        myY = y;
        myTheta = theta;
        startpose = drive.pose;
        startV = new Vector2d(drive.pose.position.x,drive.pose.position.y);
        startheading = startpose.heading.toDouble();
        // this checks for x y and theta are number lists arent empty and arent bigger than each other...
    }


    public List<Object> getX() {
        return myX;
    }

    public List<Object> getY() {
        return myY;
    }

    public List<Object> getTheta() {
        return myTheta;
    }
    public Pose2d getStartPose(){return startpose;}
    public Vector2d getStartVector(){return startV;}
    public double getStartheading(){return startheading;}

    public void reflect(){
        for(int i=0;i< myY.size();i++){
            myY.set(i,-1 * (double)myY.get(i));
            myTheta.set(i,-1*(double)myTheta.get(i));
        }
    }
    public void addPoint(double x,double y,double theta){
        myX.add(x);
        myY.add(y);
        myTheta.add(theta);
    }
    public String toString(){
        String x ="Arrays.asList(";
        String y ="Arrays.asList(";
        String theta ="Arrays.asList(";
        for (int i =0;i<myX.size();i++){
            x = x+","+myX.get(i);
        }
        for (int i =0;i<myY.size();i++){
            y = y+","+myY.get(i);
        }
        for (int i =0;i<myTheta.size();i++){
            theta = theta+","+myTheta.get(i);
        }
        x=x+")";
        y=y+")";
        theta=theta+")";
        return "["+x+" , "+y+" , "+theta+"]";
    }

}
