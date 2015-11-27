/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tron2;

import environment.Direction;
import environment.Environment;
import grid.Grid;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author BBC132
 */
class Background extends Environment implements CellDataProviderIntf, MoveValidatorIntf {

    private Grid grid;
    private Grid grid2;
    private Car car1;
    private Barrier myBarrier;
    private int BarrierX = 10; 
    private int BarrierY = 15; 
    int x = 1;
    int y = 1;
    private ArrayList<Barrier> barriers;
        

    public Background() {
        grid = new Grid(150, 100, 10, 10, new Point(0, 0), Color.black);
//        grid2 = new Grid(2, 9, 100, 64, new Point(0, 0), Color.blue);
        car1 = new Car(Direction.RIGHT, grid, x, y, this );
        myBarrier = new Barrier(BarrierX, BarrierY, Color.MAGENTA, this);
        barriers = new ArrayList<>();
        barriers.add(new Barrier(0,0, Color.GREEN, this));
        barriers.add(new Barrier(2,1, Color.GREEN, this));
        barriers.add(new Barrier(9,4, Color.GREEN, this));
        barriers.add(new Barrier(6,3, Color.GREEN, this));
        barriers.add(new Barrier(4,7, Color.GREEN, this));
 
    }

    @Override
    public void initializeEnvironment() {

    }
    int counter;
    double moveDelay = 0;
    double moveDelayLimit = 2;
    

    @Override
    public void timerTaskHandler() {
        if (car1 != null) {
            if (moveDelay >= moveDelayLimit) {
                            car1.move();
                            moveDelay = 0;

            } else {
                moveDelay++;
            }
            
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
//        System.out.println("Key Event " + e.getKeyChar());
//        System.out.println("Key Event " + e.getKeyCode());
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            car1.setDirection(Direction.LEFT);
            car1.move();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            car1.setDirection(Direction.RIGHT);
            car1.move();

        
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            car1.setDirection(Direction.UP);
            car1.move();

        
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            car1.setDirection(Direction.DOWN);
            car1.move();

        
        }
        
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            System.out.println("GO LEFT!");
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            System.out.println("GO RIGHT!");
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            System.out.println("GO UP!");
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            System.out.println("GO DOWN!");
        }
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        System.out.println("mouse click at " + e.getPoint());
        System.out.println("mouse click in cell " + grid.getCellLocationFromSystemCoordinate(e.getPoint()));

    }
    int e = 1;
    @Override
    public void paintEnvironment(Graphics graphics) {
        if (grid != null) {
            grid.paintComponent(graphics);
//            grid2.paintComponent(graphics);
        }
        if (e == 1) {
            if (car1 != null) {
            car1.draw(graphics);
        } 
        }
        
        if (barriers != null) {
            for (int i = 0; i < barriers.size(); i++) {
                barriers.get(i).draw(graphics);
            }
  
//            myBarrier.draw(graphics);
        }
    }
    
    
//<editor-fold defaultstate="collapsed" desc="CellDataProviderIntf">
    
    @Override
    public int getCellWidth() {
        return grid.getCellWidth();
    }
    
    @Override
    public int getCellHeight() {
        return grid.getCellHeight();
    }
    
    @Override
    public int getSystemCoordX(int x, int y) {
        return grid.getCellSystemCoordinate(x, y).x;
    }
    
    @Override
    public int getSystemCoordY(int x, int y) {
        return grid.getCellSystemCoordinate(x, y).y;
        
    }
//</editor-fold>

//<editor-fold defaultstate="collapsed" desc="ValidateMove">
    @Override
    public Point validateMove(Point proposedLocation) {
        if (BarrierX == proposedLocation.x ) {
            if (BarrierY == proposedLocation.y) {
                 e = 2;
            }
        }
        
        if  (proposedLocation.x < 0){
            if (proposedLocation.y > grid.getRows()/2 ){
                    proposedLocation.x++;
                    proposedLocation.y--;
                
            } else{
                proposedLocation.x++;
                proposedLocation.y++;
             }

            System.out.println("OUT OF BOUNDS");
        }
            return proposedLocation;
    }
//</editor-fold>
}
