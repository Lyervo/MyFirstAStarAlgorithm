/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package learn;

/**
 *
 * @author Timot
 */
public class Tile 
{
    private int x,y;
    private boolean solid;
    private Tile parent;
    private int id;
    public Tile getParent() {
        return parent;
    }

    public void setParent(Tile parent) {
        this.parent = parent;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getF() {
        return f;
    }

    public void setF(int f) {
        this.f = f;
    }
    
    private int h,g,f;
    
    public Tile(int x,int y,boolean solid,int id)
    {
        this.x = x;
        this.y = y;
        this.solid = solid;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }
    
    public void printTile()
    {
        if(solid)
        {
            System.out.print(" x");
        }else
        {
            System.out.print(" 0");
        }
    }
    
    public void calculateH(int x,int y,Player player)
    {
        int hx = this.x - x;
        if(hx<0)
        {
            hx*=-1;
        }
        int hy = this.y - y;
        if(hy<0)
        {
            hy*=-1;
        }
        h = hx + hy;
        System.out.println("The h cost of tile "+this.x+" "+this.y+" is "+h);
        
        int gx = this.x - player.getX();
        if(gx<0)
        {
            gx*=-1;
        }
        
        int gy = this.y - player.getY();
        if(gy<0)
        {
            gy*=-1;
        }
        g = (gx+gy)*10;
        f = g+h;
        
    }
}
