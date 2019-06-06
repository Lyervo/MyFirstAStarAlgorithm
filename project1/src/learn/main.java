/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn;

import java.util.ArrayList;

/**
 *
 * @author Timot
 */
public class main 
{
    public static void main(String[] args)
    {
        Player player = new Player (4,1);
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        
        
        
        
        //change y and x in the for loop value to adjust your map size, for example y=8 will give your map a y value of 7
        int id = 1;
        for(int y=1;y<8;y++)
        {
            for(int x=1;x<8;x++)
            {
                if(x>1&&x<7&&y==4)//use the if statements to generate tiles as you need, true means that the tile is solid therefore unpassable
                {
                    tiles.add(new Tile(x,y,true,id));
                }else
                {
                    tiles.add(new Tile(x,y,false,id));
                }
                id++;
            }
            
        }
        
        System.out.println("Tile initialized with total of "+tiles.size()+" number of tiles");
        
        
        
        ArrayList<Tile> tilesF = new ArrayList<Tile>();
        
        //calculate F value for every tile
        for(int i=0;i<tiles.size();i++)
        {
            tilesF.add(calculateF(tiles.get(i),player,4,7));
        }
        
        //set up the map with the generated tiles
        Map map = new Map(tilesF);
        
        //start the pathfinding procedure
        getDirection(map,player,4,7);
        
    }
    
    public static ArrayList<String> getDirection(Map map,Player player,int desX,int desY)
    {
        //initialize lists
        
        ArrayList<Tile> closeList = new ArrayList<Tile>();
        ArrayList<Tile> openList = new ArrayList<Tile>();
        
        
        //obtain player pos
        int orgX = player.getX();
        int orgY = player.getY();
        
        //scan for surrounding tiles around the player and add them to the openList
        
        if(map.getTile(orgX+1, orgY)!=null) //only add if the tile exists
        {
            openList.add(map.getTile(orgX+1, orgY));
        }
        
        if(map.getTile(orgX-1, orgY)!=null)
        {
            openList.add(map.getTile(orgX-1, orgY));
        }
        
        if(map.getTile(orgX, orgY+1)!=null)
        {
            openList.add(map.getTile(orgX, orgY+1));
        }
        
        if(map.getTile(orgX, orgY-1)!=null)
        {
            openList.add(map.getTile(orgX, orgY-1));
        }
        
        //obatain the tile with the least F value in the openList,check if it is solid or not
        
        int min = 9999;
        
        int index = 0;//tells which index has the least F value
        
        for(int i=0;i<openList.size();i++)
        {
            if(!openList.get(i).isSolid()&&openList.get(i).getF()<min)
            {
                min = openList.get(i).getF();
                index = i;
            }
        }
        
        System.out.println("The tile with the least F value is Tile "+openList.get(index).getId());
        
        boolean found = false;
        
        //begin the loop until the path is found
        
        while(!found)
        {
            //scan the surrounding tiles of the tile with the least F value and 
            //if it is not in the closeList and set their parents to the current tile with the least F value
            if(map.getTile(openList.get(index).getX()+1, openList.get(index).getY())!=null&&
                !closeList.contains(map.getTile(openList.get(index).getX()+1, openList.get(index).getY())))
            {
                map.getTile(openList.get(index).getX()+1, openList.get(index).getY()).setParent(map.getTile(openList.get(index).getX(), openList.get(index).getY()));
                openList.add(map.getTile(openList.get(index).getX()+1, openList.get(index).getY()));
            }
            
            if(map.getTile(openList.get(index).getX()-1, openList.get(index).getY())!=null&&
                !closeList.contains(map.getTile(openList.get(index).getX()-1, openList.get(index).getY())))
            {
                map.getTile(openList.get(index).getX()-1, openList.get(index).getY()).setParent(map.getTile(openList.get(index).getX(), openList.get(index).getY()));
                openList.add(map.getTile(openList.get(index).getX()-1, openList.get(index).getY()));
            }
            
            if(map.getTile(openList.get(index).getX(), openList.get(index).getY()+1)!=null&&
                !closeList.contains(map.getTile(openList.get(index).getX(), openList.get(index).getY()+1)))
            {
                map.getTile(openList.get(index).getX(), openList.get(index).getY()+1).setParent(map.getTile(openList.get(index).getX(), openList.get(index).getY()));
                openList.add(map.getTile(openList.get(index).getX(), openList.get(index).getY()+1));
            }
            
            if(map.getTile(openList.get(index).getX(), openList.get(index).getY()-1)!=null&&
                !closeList.contains(map.getTile(openList.get(index).getX(), openList.get(index).getY()-1)))
            {
                map.getTile(openList.get(index).getX(), openList.get(index).getY()-1).setParent(map.getTile(openList.get(index).getX(), openList.get(index).getY()));
                openList.add(map.getTile(openList.get(index).getX(), openList.get(index).getY()-1));
            }
            
            //add the current tile with the least F value into the closeList and remove it from the openList
            closeList.add(map.getTile(openList.get(index).getX(),openList.get(index).getY()));
            openList.remove(index);
            
            
            //now find the new Tile with the least F value again and reset index and min
            min = 9999;
            for(int i=0;i<openList.size();i++)
            {
                if(!openList.get(i).isSolid()&&openList.get(i).getF()<min)
                {
                    min = openList.get(i).getF();
                    index = i;
                }
            }
            
            
            
            //check if the select tile is the destination tile
            if(openList.get(index).getX()==desX&&openList.get(index).getY()==desY)
            {
                found = true;//break the loop
            }
            
            
        }
        
        System.out.println("Path found");
        
        //get the list of tiles for the path by obtaining their parents, then print out all of them
        ArrayList<Tile> dir = new ArrayList<Tile>();
        
        dir.add(map.getTile(openList.get(index).getX(), openList.get(index).getY()));
        
        Tile parent = dir.get(0).getParent();
        
        int d = 0;
        
        while(parent!=null)
        {
            dir.add(map.getTile(parent.getX(), parent.getY()));
            d++;
            System.out.println("Parent "+parent.getId());
            parent = dir.get(d).getParent();
        }
        for(int i=0;i<dir.size();i++)
        {
            System.out.println("Tile "+dir.get(i).getId());
        }
        
        
        
        
        return null;
    }
    
    
    public static Tile calculateF(Tile t,Player player,int desX,int desY)
    {
        //calculate h and g value, if the result value is negative, turn it into a positive number
        int hx = t.getX() - desX;
        if(hx<0)
        {
            hx*=-1;
        }
        int hy = t.getY() - desY;
        if(hy<0)
        {
            hy*=-1;
        }
        int h = hy + hx;
        
        int gx = t.getX() - player.getX();
        if(gx<0)
        {
            gx*=-1;
        }
        int gy = t.getY() - player.getY();
        if(gy<0)
        {
            gy*=-1;
        }
        int g = gx + gy;
        
        int f = g +h;
        
        t.setF(f);
        
        
        System.out.println("Tile "+t.getId()+" has a f value of "+t.getF());
        
        return t;
    }
    
    
    
}
