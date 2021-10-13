package com.lancoo;

public class ChangeAbility {
    public static int change(int in){
        int flag;
        switch (in){
            case 1:
                flag=3;
                break;
            case 2:
                flag=2;
                break;
            default:
                flag=1;
        }
        return flag;
    }
}
