package com.android.findmyvaccine;

public class vaccine {
    String city_name,center,vaccine_name,free_type,status,address;
    String age;
    String time;

    public vaccine(String CN,String cen,String Vname,String type,String mstatus,String age1,String time1,String add){
        city_name = CN;
        center = cen;
        vaccine_name = Vname;
        free_type = type;
        status = mstatus;
        age = age1;
        time = time1;
        address = add;
    }


    public String getCity_name(){
        return city_name;
    }

    public String getCenter(){
        return center;
    }

    public String getVaccine_name(){
        return vaccine_name;
    }

    public String getFree_type(){
        return free_type;
    }

    public String getStatus(){
        return status;
    }

    public String getage(){
        return age;
    }

    public String getTime(){
        return time;
    }

    public String getAddress(){
        return address;
    }

}
