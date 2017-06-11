/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.facens.models.interfaces;

import com.jme3.asset.AssetManager;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Eduardo
 */
public abstract class DrummerAbstract {
    
    public static final long TIME_MAX_SECONDS = 1000;
    public static final long TIME_MIN_SECONDS = 500;
    
    private boolean initial  = false;
    private long timeInitial = 0l;
    private int direction    = 1;
    private String name      = "";

    public DrummerAbstract(String name) {
        this.setName(name);
    }    
    
    public boolean isInitial() {
        return initial;
    }

    public void setInitial(boolean initial) {
        this.initial = initial;
    }

    public long getTimeInitial() {
        return timeInitial;
    }

    public void setTimeInitial(long timeInitial) {
        this.timeInitial = timeInitial;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
    
    public void objTranslation(Spatial obj,float x, float y, float z) {
        obj.setLocalTranslation(x, y, z);
    }
    
    public void objRotate(Spatial obj, float x, float y, float z) {
        obj.rotate(x, y, z);
    }
    
    public abstract void criarObjeto(ColorRGBA color, Node rootNode, AssetManager assetManager); 
    public abstract void movimentaObjeto(Node rootNode, float tpf);
    
}
