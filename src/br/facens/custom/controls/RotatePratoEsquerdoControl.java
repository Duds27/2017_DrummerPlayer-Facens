/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.facens.custom.controls;

import br.facens.models.interfaces.DrummerAbstract;
import com.jme3.asset.AssetManager;
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.Control;
import mygame.Main;

/**
 *
 * @author Eduardo
 */
public class RotatePratoEsquerdoControl extends DrummerAbstract {
    private float speed = 1;
    private Node rootNode;
    private AssetManager assetManager;

    
    public RotatePratoEsquerdoControl(String name) {
        super(name);
    }
    
    public RotatePratoEsquerdoControl() {}    
    
    public void setHabilitarRotate(boolean enable) {
       System.out.println("Hi! -> " + enable);
       this.enabled = enable;
    }

    @Override
    protected void controlUpdate(float tpf) {
        if (Main.getApp().isMexerPratoEsquerdo()) {        
            movimentaObjeto(this.rootNode, tpf);        
        }
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Control cloneForSpatial(Spatial spatial) {
        this.enabled = false;
        RotatePratoEsquerdoControl control = new RotatePratoEsquerdoControl();        
        control.setSpeed(speed);
        control.setSpatial(spatial);
        return control;
    }

    

    @Override
    public void criarObjeto(Node rootNode, AssetManager assetManager) {
        this.setRootNode(rootNode);
        this.setAssetManager(assetManager);
    }
    
    @Override
    public void movimentaObjeto(Node rootNode, float tpf) {
        
        long time = System.currentTimeMillis() - this.getTimeInitial();
        
        if (time < this.TIME_MIN_SECONDS) {
            spatial.rotate(0, 0, tpf * this.getDirection());
        }
        
        if (time >= this.TIME_MIN_SECONDS) {
            if (!this.isInitial()) {
                this.setInitial(true);
                this.setDirection( this.getDirection() * (-1));
            }
            this.objRotate(spatial, 0, 0, tpf * this.getDirection());
        }
        
        if (time > this.TIME_MAX_SECONDS) {
            this.setInitial(false);
            this.setTimeInitial(System.currentTimeMillis());
            this.setDirection(this.getDirection() * (-1));
        }

        this.objRotate(spatial, 0, 0, tpf * this.getDirection());
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
    
    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    public AssetManager getAssetManager() {
        return assetManager;
    }

    public void setAssetManager(AssetManager assetManager) {
        this.assetManager = assetManager;
    }
    
}
