/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tugasakhir1.Location;

/**
 *
 * @author hanif
 */
public class location {
    private String district,subDistrict,Location;
    
   
    public location(String district,String sub){
    this.district = district;
    this.subDistrict = sub;
    }
    public void setFinalLocation(String locationFinal){
    this.Location = locationFinal;
        
    }
    public String getFinalLocation(){
    return Location;
    }
    public String getDistrict(){
        return district;
    }
    public String getSubDistrict(){
        return subDistrict;
    }
}
