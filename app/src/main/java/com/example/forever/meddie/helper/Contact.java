package com.example.forever.meddie.helper;



public class Contact {

    //private variables
    int _id;
    String _fname;
    String email;
    byte[] _img;



    // Empty constructor
    public Contact(){

    }
    // constructor
    public Contact( String fname, byte[] img,String email){

        this._fname = fname;
        this._img = img;
        this.email=email;

    }

    // constructor
    public Contact(String fname, byte[] img){

        this._fname = fname;
        this._img = img;

    }

    public void setEmail(String email){ this.email = email;}

    public String getEmail(){return this.email;}

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

