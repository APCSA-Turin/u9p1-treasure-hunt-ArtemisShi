package com.example.project;
import java.util.Scanner;

public class Game{
    private Grid grid;
    private Player player;
    private Enemy[] enemies;
    private Treasure[] treasures;
    private Trophy trophy;
    private int size; 

    public Game(int size){ //the constructor should call initialize() and play()
        initialize(size);
        play();
    }


    //getter methods
    public Grid getGrid(){
        return grid;
    }
    public Player getPlayer(){
        return player;
    }
    public int getEnemyCount(){
        int count=0;
        for(int i=0; i<enemies.length;i++){
            count++;
        }
        return count;
    }
    public int getTreasureCount(){
        int count=0;
        for(int i=0; i<treasures.length;i++){
            count++;
        }
        return count;
    }
    public Trophy getTrophy(){
        return trophy;
    }
    public int getSize(){
        return size;
    }

    public static void clearScreen() { //do not modify
        try {
            final String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                // Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Unix-based (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play(){ //write your game logic here
        int playCount=0;
        int enCount=0;
        Scanner scanner = new Scanner(System.in);
     boolean check=true;
     System.out.println("Input q if you want to quit");
     String dir=scanner.nextLine(); //This is so the while parameter works
     if(dir.equals("q")){
        System.out.println("Game end");
     }
     else{
     System.out.println("Game start");
     }
        while(check&&!dir.equals("q")){// if the game ends or if the player quits then the game ends
            for(int i=0; i<enemies.length;i++){
                enemies[i].setInt();
            }
            dir=scanner.nextLine();
            int x=player.getX();
            int y=player.getY();
            if(dir.equals("w")){  //direction is needed for interact to work
                y++;
            }
            if(dir.equals("d")){
                x++;
            }
            if(dir.equals("a")){
                x--;
            }
            if(dir.equals("s")){
                y--;
            }
          
            if(player.isValid(size, dir)){//This is so that the player doesn't cause an error going out of bounds
            int count=0;
            player.interact(size,dir,getTreasureCount(),grid.getGrid()[size-(y+1)][x]); 
            if(grid.getGrid()[size-(y+1)][x] instanceof Enemy){
                Enemy[] place =new Enemy[enemies.length-1];//this creates a new enemy list without the killed enemy
                for(int j=0;j<enemies.length;j++){
                    if(!enemies[j].equals(grid.getGrid()[size-(y+1)][x])){
                        place[count]=enemies[j];
                        count++;
                    }
                }
                enemies=place;
                grid.placeSprite(new Dot(size-(y+1),x));//a dot replaces enemy
                
            }
            player.move(dir);
            grid.placeSprite(player,dir); //player movement
            playCount++;

            }

            if(playCount>=enCount){
            for(int i=0; i<enemies.length;i++){// all enemies move after player moves
                int count =0;//this is for creating a new enemy list without an enemy when it attacks
            if(player.getRow(size)>enemies[i].getRow(size)){//depending on the location of the player, the enemy gets closer
                if(enemies[i].isValid(size, "s")){//only when enemy movement is valid
                enemies[i].interact(size,grid.getGrid()[size-1-(enemies[i].getY()-1)][enemies[i].getX()]);
                if(enemies[i].getTouch()){//if the enemy hits the player
                    grid.placeSprite(new Dot(enemies[i].getX(),enemies[i].getY()));//a dot replaces enemy
                    Enemy[] place =new Enemy[enemies.length-1];//this creates a new enemy list without the killed enemy
                    for(int j=0;j<enemies.length;j++){
                        if(!enemies[j].equals(enemies[i])){
                            place[count]=enemies[j];
                            count++;
                        }
                    }
                    enemies=place;
                }
                else{
                if(enemies[i].getInt()&&!enemies[i].getTouch()){// moves as long as the enemy is going towards a treasure
                enemies[i].move("s");
                grid.placeSprite(enemies[i],"s");
                }
            }
                }
            }
            else{
                if(player.getRow(size)<enemies[i].getRow(size)){
                    if(enemies[i].isValid(size, "w")){
                        enemies[i].interact(size,grid.getGrid()[size-1-(enemies[i].getY()+1)][enemies[i].getX()]);
                        if(enemies[i].getTouch()){
                            grid.placeSprite(new Dot(enemies[i].getX(),enemies[i].getY()));
                            Enemy[] place =new Enemy[enemies.length-1];
                            for(int j=0;j<enemies.length;j++){
                                if(!enemies[j].equals(enemies[i])){
                                    place[count]=enemies[j];
                                    count++;
                                }
                            }
                            enemies=place;
                        }
                        else{
                        if(enemies[i].getInt()&&!enemies[i].getTouch()){
                    enemies[i].move("w");
                    grid.placeSprite(enemies[i],"w");
                        }
                    }
                    }
                }
            else{
                if(player.getCol(size)>enemies[i].getCol(size)){
                    if(enemies[i].isValid(size, "d")){
                        enemies[i].interact(size,grid.getGrid()[size-1-(enemies[i].getY())][enemies[i].getX()+1]);
                        if(enemies[i].getTouch()){
                            grid.placeSprite(new Dot(enemies[i].getX(),enemies[i].getY()));
                            Enemy[] place =new Enemy[enemies.length-1];
                            for(int j=0;j<enemies.length;j++){
                                if(!enemies[j].equals(enemies[i])){
                                    place[count]=enemies[j];
                                    count++;
                                }
                            }
                            enemies=place;

                        }
                        else{
                        if(enemies[i].getInt()&&!enemies[i].getTouch()){
                    enemies[i].move("d");
                    grid.placeSprite(enemies[i],"d");
                        }
                    }
                    }
                }
                else{
                    if(enemies[i].isValid(size, "a")){
                        enemies[i].interact(size,grid.getGrid()[size-1-(enemies[i].getY())][enemies[i].getX()-1]);
                        if(enemies[i].getTouch()){
                            grid.placeSprite(new Dot(enemies[i].getX(),enemies[i].getY()));
                            Enemy[] place =new Enemy[enemies.length-1];
                            for(int j=0;j<enemies.length;j++){
                                if(!enemies[j].equals(enemies[i])){
                                    place[count]=enemies[j];
                                    count++;
                                }
                            }
                            enemies=place;
                        }
                        else{
                        if(enemies[i].getInt()&&!enemies[i].getTouch()){
                    enemies[i].move("a");
                    grid.placeSprite(enemies[i],"a"); 
                    }
                }
                }
                }
            }
        }
        enCount++;
    }
            }
            try {
                Thread.sleep(100); // Wait for 1/10 seconds
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        
            clearScreen(); // Clear the screen at the beggining of the while loop
            grid.display();//This is the display everyone sees
            System.out.println(player.getCoords());
            System.out.println(player.getRowCol(size));
            System.out.println("Treasure collected: "+ player.getTreasureCount());
            System.out.println("Lives remaining: "+ player.getLives());
            System.out.println("Enemy Count: "+ getEnemyCount());
            System.out.println("Enter w,a,s,d to move and if you want to quit enter q");

            if(player.getLives()<=0){//checks lives
                check=false;
            }

            if(player.getWin()==true){//checks if you won
                check=false;
            }
            }

            if(player.getLives()<=0){//final screen for losing
                grid.gameover();
                grid.display();
                System.out.println("YOU LOSE!");
            }
           
            if(player.getWin()==true){//final screen for winning
                grid.win();
                grid.display();
                System.out.println("YOU WIN!");
            }
        }
     
    
    public void initialize(int size){

        //to test, create a player, trophy, grid, treasure, and enemies. Then call placeSprite() to put them on the grid
        Grid g1 = new Grid(size);
        Player p1 = new Player(0, 0);
        this.size=size;
        this.player=p1;
        this.grid=g1;
        treasures=new Treasure[2];
        enemies= new Enemy[2];


   Trophy t1 =new Trophy(0, 9); //this is for testing
   Enemy e1 = new Enemy(9, 3);
   Enemy e2 = new Enemy(6, 9);
   Treasure tt1= new Treasure(1, 2);
   Treasure tt2= new Treasure(5, 3);
   treasures[0]=tt1;
   treasures[1]=tt2;
   enemies[0]=e1;
        enemies[1]=e2;
   g1.placeSprite(p1);
   g1.placeSprite(t1);
   g1.placeSprite(e1);
   g1.placeSprite(e2);
   g1.placeSprite(tt1);
   g1.placeSprite(tt2);
g1.display();
    }

    public static void main(String[] args) {
        Game G1=new Game(10);

    }
}