package com.example.project;

public class Sprite {
    private int x, y;
    public String image; //image is so that the sprites have a visible appearance

    public Sprite(int x, int y) {
        this.x = x;
        this.y = y;
  
    }

    //getters
    public int getX(){return x;}
    public int getY(){return y;}
    public String getImage(){ //this is so that display can show the sprite's image
        return image;
    }


    //setter method
    public void setX(int newX){x=newX;}
    public void setY(int newY){y=newY;}
    public void setImage(String im){ //this is so all sub classes can set their images
        image=im;
    }


    public String getCoords(){ //returns the coordinates of the sprite ->"(x,y)"
        return "("+x+","+y+")";
    }

    public String getRowCol(int size){ //returns the row and column of the sprite -> "[row][col]"
        return "["+(size-(y+1))+"]"+"["+x+"]";
    }

    public int getRow(int size){ //this specifically returns the row the sprite is in
        return size-(y+1);
    }
    public int getCol(int size){ //this returns the column
        return x;
    }

    public void move(String direction) { //you can leave this empty
        // Default behavior (can be overridden by subclasses)   
    }

    public void interact() { //you can leave this empty
        // Default behavior (can be overridden by subclasses)
    }



}
