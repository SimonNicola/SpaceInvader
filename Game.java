/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.juego;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {

    private Terminal terminal;
    private Screen screen;
    private boolean exit_key;
    private Bullet bala;
    private Ship ship;
    private Wall wall;

    public static int Columns = 80;
    public static int Rows = 80;

    public Game() {
        this.exit_key = false;
        try {
            this.terminal = new DefaultTerminalFactory().createTerminal();
            this.screen = new TerminalScreen(this.terminal);
            this.screen.setCursorPosition(null);
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        bala = new Bullet(40, 12);
        ship = new Ship(40, 60);
        wall = new Wall(40, 40);

    }

    public void loop() {
        int x = 0;
        int y = 0;
        try {
            screen.startScreen();
            screen.clear();
            while (!this.exit_key) {
                x = (int) Math.random() * 80;
                y = (int) Math.random() * 80;
                process_input();
                update();
                paint(screen);
                
                try {
                    Thread.sleep((1 / 60) * 1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            screen.close();
            terminal.close();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void update() {
      //  this.ship.movebullets();
    }

    private void paint(Screen s) {
        try {
            TerminalSize terminalSize = s.getTerminalSize();
            for (int column = 0; column < terminalSize.getColumns(); column++) {
                for (int row = 0; row < terminalSize.getRows(); row++) {
                    s.setCharacter(column, row, new TextCharacter(' ', TextColor.ANSI.DEFAULT, TextColor.ANSI.DEFAULT));
                }
            }
            //limpia pantalla
            screen.clear();
            this.ship.paint(this.screen);
            this.wall.paint(this.screen);
            //refresca la pantalla
            screen.refresh();           
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void process_input() {
        KeyStroke keyStroke = null;
        try {
            keyStroke = screen.pollInput();
        } catch (IOException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        //depende de la llave pasara una cosa o t
        if (keyStroke != null) {
            if (keyStroke.getKeyType() == KeyType.Escape) {
                this.exit_key = true;
            }
            if (keyStroke.getKeyType() == KeyType.ArrowUp) {
                this.bala.movevertical(-1, 0, Game.Rows);

            }
            if (keyStroke.getKeyType() == KeyType.ArrowDown) {
                this.bala.movevertical(1, 0, Game.Rows);
            }
            if (keyStroke.getKeyType() == KeyType.ArrowLeft) {
                this.ship.movehorizontal(-1, 0, Game.Columns);

            }
            if (keyStroke.getKeyType() == KeyType.ArrowRight) {
                this.ship.movehorizontal(1, 0, Game.Columns);
            }
            if (keyStroke.getKeyType() == KeyType.Enter) {
                this.ship.shoot();
            }

        }

    }

}
