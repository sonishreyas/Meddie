package com.example.forever.pid.helper;



public class Contact {

    //private variables
    int _id;
    String _fname;
    byte[] _img;
    String _mail;



    // Empty constructor
    public Contact(){

    }
    // constructor
    public Contact(int id, String fname, byte[] img , String mail){
        this._id = id;
        this._fname = fname;
        this._img = img;
        this._mail = mail;

    }

    // constructor
    public Contact(String fname, byte[] img , String mail){

        this._fname = fname;
        this._img = img;
        this._mail = mail;

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

    public String getMail() { return this._mail;}

    public void setMail(String mail){
        this._mail = mail;
    }


    public byte[] getImage(){
        return this._img;
    }



    public void setImage(byte[] b){
        this._img=b;
    }

}

