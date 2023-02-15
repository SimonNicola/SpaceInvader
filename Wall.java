/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juego;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

/**
 *
 * @author DAWTarde
 */
public class Wall {

    private Point2D posicion;
    private int width = 7;
    private int height = 2;
    private char cartoon[][] = {
        {'"', '"', '"', '"', '"', '"', '"'},
        {'"', '"', '"', '"', '"', '"', '"'}
    };

    /**
     * @return the posicion
     */
    public Point2D getPosicion() {
        return posicion;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the cartoon
     */
    public char[][] getCartoon() {
        return cartoon;
    }

    /**
     * @param cartoon the cartoon to set
     */
    public void setCartoon(char[][] cartoon) {
        this.cartoon = cartoon;
    }

    public Wall() {
        this.posicion = new Point2D();
    }

    public Wall(Point2D pocicion) {
        this.posicion = pocicion;

    }

    public Wall(int x, int y) {
        this.posicion = new Point2D(x, y);
    }

    public void paint(Screen s) {
        char c;
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                c = this.cartoon[i][j];
                s.setCharacter(this.posicion.getX() + j, this.posicion.getY() + i,
                        new TextCharacter(c, TextColor.ANSI.WHITE,
                                TextColor.ANSI.BLACK));
            }

        }
    }

    public boolean collsion(Bullet b) {
        return collisionX(b) && collisionY(b);
    }

    private boolean collisionY(Bullet b) {
        boolean collision = false;
        /* if (this.posicion.getY() + this.height >= b.getPocicion().getY() && 
                this.posicion.getY() <= b.getPocicion().getY()) {
            collision = true; */
        if (this.posicion.getY() <= b.getPocicion().getY() && this.posicion.getY() <= b.getPocicion().getY()) {
            collision = true;
        }
        return collision;
    }

    private boolean collisionX(Bullet b) {
        boolean collision = false;
        if (this.posicion.getX() <= b.getPocicion().getX()
                && this.posicion.getX() + this.width <= b.getPocicion().getX()) {
            collision = true;
        }
        return collision;
    }

}
