package org.firstinspires.ftc.teamcode.utils;

import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Trajectory {
    List<Object> myX,myY,myTheta;
    public Trajectory(List<Object> x, List<Object>y, List<Object>theta){
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
        // this checks for x y and thata are number lists arent empty and arent bigger than each other...
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

    public void reflect(){
        for(int i=0;i< myY.size();i++){
            myY.set(i,-1 * (double)myY.get(i));
            myTheta.set(i,-1*(double)myTheta.get(i));
        }
    }
}
