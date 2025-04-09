package com.example.project;


//DO NOT DELETE ANY METHODS BELOW
public class Grid{
    private Sprite[][] grid;
    private int size;

    public Grid(int size) { //initialize and create a grid with all DOT objects
        this.size=size;
        this.grid = new Sprite[size][size];
        for(int i=0; i<grid.length;i++){
            for(int j=0; j<grid[0].length;j++){
                grid[i][j]=new Dot(i, j);// creates a 2d array with both the length and width being int size; makes every slot dot
            }
        }
    }

 
    public Sprite[][] getGrid(){return grid;}



    public void placeSprite(Sprite s){ //place sprite in new spot
            grid[(size-(s.getY()+1))][s.getX()]=s;
    }

    public void placeSprite(Sprite s, String direction) { //place sprite in a new spot based on direction
    
            int row = s.getRow(size);
            int col =s.getCol(size);
            if(direction.equals("w")){  //puts a dot in sprite's current position
                grid[row+1][col]=new Dot(0,0);
            }
            if(direction.equals("d")){ 
                grid[row][col-1]=new Dot(0,0);            
            }
            if(direction.equals("a")){
                grid[row][col+1]=new Dot(0,0);
            }
            if(direction.equals("s")){
                grid[row-1][col]=new Dot(0,0);
            }


            grid[size-(s.getY()+1)][s.getX()]=s;//places sprite in new position
        
    
    }


    public void display() { //print out the current grid to the screen 
        for(int i=0; i<grid.length;i++){
            for(int j=0; j<grid[0].length;j++){
                System.out.print(grid[i][j].getImage());
            }
            System.out.println();
        }

    }
    
    public void gameover(){ //use this method to display a loss
        for(int i=0; i<grid.length;i++){
            for(int j=0; j<grid[0].length;j++){
                if(!grid[i][j].getImage().equals("🦄")){//replaces all sprites with a skull except for player
                grid[i][j]=new Sprite(i, j);
                grid[i][j].setImage("💀");
                }
            }
        }
    }

    public void win(){ //use this method to display a win 
        for(int i=0; i<grid.length;i++){
            for(int j=0; j<grid[0].length;j++){
                if(!grid[i][j].getImage().equals("🦄")){ //replaces all sprites with a rainbow except for player
                grid[i][j]=new Sprite(i, j);
                grid[i][j].setImage("🌈");
            }
            }
        }
    }

    
}