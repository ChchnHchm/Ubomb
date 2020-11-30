/*
 * Copyright (c) 2020. Laurent Réveillère
 */

package fr.ubx.poo.game;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import fr.ubx.poo.model.go.character.Box;
import fr.ubx.poo.model.go.character.Monster;
import fr.ubx.poo.model.go.character.Player;

public class Game {

    private final World world;
    private final Player player;
    private final String worldPath;
    public int initPlayerLives;
    private ArrayList<Monster> ListMonster;
    private ArrayList<Box> ListBox;
    public Game(String worldPath) {
        world = new WorldStatic();
        this.worldPath = worldPath;
        loadConfig(worldPath);
        Position positionPlayer = null;
        ArrayList<ArrayList<Position>> Lists = new ArrayList<>();
        Lists.add(new ArrayList<Position>());
        Lists.add(new ArrayList<Position>());
        ListMonster = new ArrayList<Monster>();
        ListBox = new ArrayList<Box>();
        try {
            positionPlayer = world.findPlayer();
            player = new Player(this, positionPlayer);
        } catch (PositionNotFoundException e) {
            System.err.println("Position not found : " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
        try {

            Lists = world.findObject();
            for (Position pos: Lists.get(0)) {
                ListMonster.add(new Monster(this,pos));
            }
            for (Position pos: Lists.get(1)) {
                ListBox.add(new Box(this,pos));
            }
        }catch (PositionNotFoundException e) {
            System.err.println("Position not found : " + e.getLocalizedMessage());
            throw new RuntimeException(e);
        }
    }

    public int getInitPlayerLives() {
        return initPlayerLives;
    }

    private void loadConfig(String path) {
        try (InputStream input = new FileInputStream(new File(path, "config.properties"))) {
            Properties prop = new Properties();
            // load the configuration file
            prop.load(input);
            initPlayerLives = Integer.parseInt(prop.getProperty("lives", "3"));
        } catch (IOException ex) {
            System.err.println("Error loading configuration");
        }
    }

    public World getWorld() {
        return world;
    }

    public Player getPlayer() {
        return this.player;
    }

    public ArrayList<Monster> getListMonster() {
        return ListMonster;
    }

    public ArrayList<Box> getListBox() {return ListBox;}
}
