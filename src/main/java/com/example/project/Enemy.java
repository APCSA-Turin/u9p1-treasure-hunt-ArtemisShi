package com.example.project;

//Enemy only need constructor and getCoords() getRowCol()
public class Enemy extends Sprite { //child  of Sprite
    private boolean interact=true;
    private boolean touch=false;
    
    public Enemy(int x, int y) {
        super(x, y);
        setImage( "🦂");
    }

    public boolean getInt(){
        return this.interact;
    }
    public void setInt(){
        this.interact=true;
    }
    public boolean getTouch(){
        return touch;
    }


    //the methods below should override the super class 
@Override
    public String getCoords(){ //returns "Enemy:"+coordinates
        return "Enemy:"+super.getCoords();
    }


    public String getRowCol(int size){ //return "Enemy:"+row col
    return "Enemy:"+super.getRowCol(size); 
    }

    public void move(String direction) { //move the (x,y) coordinates of the player
        if(direction.equals("w")){ //keyboard inputs creates the movements
            setY(getY()+1);
        }
        if(direction.equals("d")){ 
            setX(getX()+1);
        }
        if(direction.equals("a")){
            setX(getX()-1);
        }
        if(direction.equals("s")){
            setY(getY()-1);
        }
    }

    public boolean isValid(int size, String direction){ //check grid boundaries
        if(direction.equals("w")&&getY()+1>size-1){  // w and d reach the max borders while a and s reach the min borders
            return false;
        }
        if(direction.equals("d")&&getX()+1>size-1){
           return false;
        }
        if(direction.equals("a")&&getX()-1<0){
            return false;        
        }
        if(direction.equals("s")&&getY()-1<0){
            return false;        
        }
        return true;
    }


    public void interact(int size, Object obj) { // interact with an object in the position enemy is moving to 
        if(obj instanceof Treasure|| obj instanceof Trophy|| obj instanceof Enemy){ //makes sure that treasures can't be touched by enemy
        interact=false;
        }
        if(obj instanceof Player){ //if enemy hits the player then a life will be decreased
            ((Player) obj).setLives();
            touch=true; //this is so that the enemy is also dead
        }
        }
        
}