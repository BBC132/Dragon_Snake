/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tron2;

import environment.Direction;
import grid.Grid;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Color;

/**
 *
 * @author BBC132
 */
public class Car {
    private int x;
    private int y;
    private Color color;
    private final MoveValidatorIntf validator;
    
    public Car(Direction direction, Grid grid, int x, int y, MoveValidatorIntf validator){
        this.direction = direction;
        this.grid = grid;
        this.x = x;
        this.y = y;
        this.validator = validator;
       

        
        //creat the Car body
        body = new ArrayList<>();
//        body.add(new Point(5,5));
//        body.add(new Point(5,6));
//        body.add(new Point(5,7));
//        body.add(new Point(4,5));
        body.add(new Point(x,y));
        
    }
    private Direction direction;
    private ArrayList<Point> body;
    private Grid grid;
    private Color bodyColor = Color.BLUE;
    
    public void stop(){
        body = null;
    }
    
    public void move() {
        //Make a copy of the current head location
        Point newHead = new Point(getHead());
        if (body != null) {
            
        
        if (getDirection() == Direction.LEFT) {
            newHead.x--;
            
        }        
        if (getDirection() == Direction.RIGHT) {
            newHead.x++;
            
        }        
        if (getDirection() == Direction.UP) {
            newHead.y--;
            
        }        
        if (getDirection() == Direction.DOWN) {
            newHead.y++;
            
        }
        
        body.add(HEAD_POSITION, validator.validateMove(newHead));
//        body.remove(body.size() -1);
        }
        
    }
    private static final int HEAD_POSITION = 0;
    
    public Point getHead(){
        return body.get(0);
    }
    
    public void draw(Graphics graphics) {
        
        for (int i = 0; i < body.size(); i++) {
//            System.out.println("body location = " +body.get(i).toString());
            graphics.setColor(bodyColor);
            graphics.fillRect(grid.getCellSystemCoordinate(body.get(i)).x, grid.getCellSystemCoordinate(body.get(i)).y, grid.getCellWidth(), grid.getCellHeight());
        }
        
    }

    /**
     * @return the direction
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(Color color) {
        this.color = color;
    }
        
    
    
}
