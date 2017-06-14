/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.facens.model.objects;

import br.facens.models.interfaces.DrummerAbstract;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Quaternion;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Eduardo
 */
public class PratoDireito extends DrummerAbstract {

    public PratoDireito(String name) {
        super(name);
    }

    @Override
    public void criarObjeto(ColorRGBA color, Node rootNode, AssetManager assetManager) {
        Spatial pratoDireito = assetManager.loadModel("Models/Circle.030.mesh.j3o");

        this.setObjeto(pratoDireito);
        
        Material boxMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        boxMat.setColor("Ambient", color); 
        boxMat.setBoolean("UseMaterialColors", true); 
        pratoDireito.setMaterial(boxMat);
        pratoDireito.setName(this.getName());
        pratoDireito.rotate(0, 0, -0.4f);
        pratoDireito.setLocalTranslation(-5f, 0, 0);
        
        rootNode.attachChild(pratoDireito);
    }
    
    @Override
    public void movimentaObjeto(Node rootNode, float tpf) {
        Spatial pratoDireito = rootNode.getChild(this.getName());
        Quaternion rotation  = pratoDireito.getLocalRotation();
        
        long time = System.currentTimeMillis() - this.getTimeInitial();
        
        if (time < this.TIME_MIN_SECONDS) {
            pratoDireito.rotate(0, 0, tpf * this.getDirection());
        }
        
        if (time >= this.TIME_MIN_SECONDS) {
            if (!this.isInitial()) {
                this.setInitial(true);
                this.setDirection( this.getDirection() * (-1) );
            }
            pratoDireito.rotate(0, 0, tpf * this.getDirection());
        }
        
        if (time > this.TIME_MAX_SECONDS) {
            this.setInitial(false);
            this.setTimeInitial(System.currentTimeMillis());
            this.setDirection( this.getDirection() * (-1) );
        }
        
        pratoDireito.rotate(0, 0, tpf * this.getDirection());
    }

}
