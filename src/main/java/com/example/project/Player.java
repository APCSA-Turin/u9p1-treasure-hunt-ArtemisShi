package com.example.project;

//DO NOT DELETE ANY METHODS BELOW
public class Player extends Sprite {
    private int treasureCount;
    private int numLives;
    private boolean win;

    public Player(int x, int y) { //set treasureCount = 0 and numLives = 2 
        super(x, y);
    treasureCount=0;
    numLives=2;
    setImage("🦄"); 
    }


    public int getTreasureCount(){return treasureCount;}
    public int getLives(){return numLives;}
    public boolean getWin(){return win;}

    @Override
    public String getCoords(){ //for the game display
        return "Player:"+super.getCoords();
    }

    @Override
    public String getRowCol(int size){ //for the game display
    return "Player:"+super.getRowCol(size);
    }

    //method for enemy to use
    public void setLives(){
    numLives--;
    }

  
    //move method should override parent class, sprite
    @Override
    public void move(String direction) { //move the (x,y) coordinates of the player
        if(direction.equals("w")){ //keyboard inputs creates the movements w-up d-right a-left s-down
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


    public void interact(int size, String direction, int numTreasures, Object obj) { // interact with an object in the position you are moving to 
    //numTreasures is the total treasures at the beginning of the game
    if(isValid(size, direction)){//makes sure you can't go out of bounds
    if(obj instanceof Treasure&& !(obj instanceof Trophy)){ //makes sure that the trophy can only be collected if all the treasures are
        treasureCount++; //increases treasure
    }
    if(obj instanceof Enemy){ //if a player hits enemy then a life will be decreased
        numLives--;
    }
    if(obj instanceof Trophy&&treasureCount>=numTreasures){//you can only get it if you got all treasures  
        win=true;
    }
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
        return true; //if all parameters are false then boolean is true
    }


   
}



