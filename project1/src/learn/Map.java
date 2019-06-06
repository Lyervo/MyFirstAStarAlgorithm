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
public class Map 
{
    ArrayList<Tile> tiles;
    public Map(ArrayList<Tile> tiles)
    {
        this.tiles = new ArrayList<Tile>(tiles);
    }
    
    public Tile getTile(int x,int y)
    {
        for(int i=0;i<tiles.size();i++)
        {
            if(tiles.get(i).getX()==x&&tiles.get(i).getY()==y)
            {
                return tiles.get(i);
            }
        }
        return null;
    }
    
    
}
