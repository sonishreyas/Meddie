package com.example.forever.pid.helper;



public class Contact {

    //private variables
    int _id;
    String _fname;
    byte[] _img;



    // Empty constructor
    public Contact(){

    }
    // constructor
    public Contact(int id, String fname, byte[] img){
        this._id = id;
        this._fname = fname;
        this._img = img;

    }

    // constructor
    public Contact(String fname, byte[] img){

        this._fname = fname;
        this._img = img;

    }


    public int getID(){
        return this._id;
    }

    public void setID(int id){
        this._id = id;
    }


    public String getFName(){
        return this._fname;
    }


    public void setFName(String fname){
        this._fname = fname;
    }


    public byte[] getImage(){
        return this._img;
    }



    public void setImage(byte[] b){
        this._img=b;
    }

}

