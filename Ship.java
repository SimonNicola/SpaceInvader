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
public class Ship {

    private Point2D pocicion;
    private int width = 7;
    private int height = 2;
    private Bullet bullets[];
    private static int max_bullets = 3;
    private String cartoon[] = {
        " ⢀⣀⣾⣷⣀⡀ ",
        " ⣿⣿⣿⣿⣿⣿ "
    };

    public Ship() {
        this.pocicion = new Point2D();
        this.bullets = new Bullet[Ship.max_bullets];
    }

    ;

    public Ship(Point2D pocicion) {
        this.pocicion = pocicion;
        this.bullets = new Bullet[Ship.max_bullets];

    }

    public Ship(int x, int y) {
        this.pocicion = new Point2D(x, y);
        this.bullets = new Bullet[Ship.max_bullets];

    }

    /**
     * @return the pocicion
     */
    public Point2D getPocicion() {
        return pocicion;
    }

    /**
     * @param pocicion the pocicion to set
     */
    public void setPocicion(Point2D pocicion) {
        this.pocicion = pocicion;
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
     * @return the bullets
     */
    public Bullet[] getBullets() {
        return bullets;
    }

    /**
     * @param bullets the bullets to set
     */
    public void setBullets(Bullet[] bullets) {
        this.bullets = bullets;
    }

    public void movehorizontal(int inc, int min, int max) {
        //comprueba si esta dentro de la pocicion sigue añadideno
        if (this.pocicion.getX() + inc >= min && this.pocicion.getX() + inc + this.getWidth() > max) {
            this.pocicion.addx(inc);
        }
    }

    public void paint(Screen s) {
        char c;
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                c = this.cartoon[i].charAt(j);
                s.setCharacter(this.pocicion.getX() + j, this.pocicion.getY() + i,
                        new TextCharacter(c, TextColor.ANSI.WHITE,
                                TextColor.ANSI.BLACK));
            }
        }
        for (int i = 0; i < this.bullets.length; i++) {
            if (this.bullets[i] != null) {
                this.bullets[i].paint(s);
            }
        }
    }

    public void shoot() { //se crea una balla en el tipo de ship 
        this.bullets[0] = new Bullet(
                this.pocicion.getX() + this.width / 2,
                this.pocicion.getY() - 0, 5); //la bala tiene que subir dos en height
    }

    public void movebullets() {
        for (int i = 0; i < this.bullets.length; i++) {
            if (this.bullets[i].getPocicion().getY() <= 0) {
                this.bullets[i] = null;
            }
            if (this.bullets[i] != null) {
                this.bullets[i].movevertical(-1, 0, Game.Rows);
            }
        }
    }

}
