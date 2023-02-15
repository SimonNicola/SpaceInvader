/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juego;

import com.googlecode.lanterna.Symbols;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.screen.Screen;

/**
 *
 * @author DAWTarde
 */
public class Bullet {

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

    private Point2D pocicion;
    private TextColor color;
    private TextColor backgroundcolor;
    private TextCharacter bulletsymbol;
    private static int max_counter = 24;
    private int counter = max_counter;

    public Bullet(int par, int par1, int par2) {
    }

    public Bullet(Point2D pocicion) {
        this.pocicion = pocicion;
    }

    public Bullet(int x, int y) {
        this.pocicion = new Point2D(x, y);
        this.init();
    }

    private void init() {
        this.color = TextColor.ANSI.GREEN;
        this.backgroundcolor = TextColor.ANSI.BLACK;

        this.bulletsymbol = TextCharacter.fromCharacter(
                Symbols.ARROW_UP //cambia el simbolo de la bala a una flecha
        )[0].withForegroundColor(this.color).withBackgroundColor(this.backgroundcolor);
    }

    public void paint(Screen s) {
        s.setCharacter(this.getPocicion().getX(),
                this.getPocicion().getY(),
                this.bulletsymbol);
    }

    public void movevertical(int icry, int miny, int maxy) {
        this.counter--;
        if (this.counter == 0) {
            this.counter=max_counter;
            //comprueba si esta dentro de la pocicion sigue añadideno
            if (this.getPocicion().getY() + icry >= miny && this.getPocicion().getY() + icry < maxy) {
                this.getPocicion().addy(icry);
            }
        }
    }
}
