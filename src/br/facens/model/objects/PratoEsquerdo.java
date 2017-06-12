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
public class PratoEsquerdo extends DrummerAbstract {

    public PratoEsquerdo(String name) {
        super(name);
    }
    
    @Override
    public void criarObjeto(ColorRGBA color, Node rootNode, AssetManager assetManager) {
        Spatial pratoEsquerdo = assetManager.loadModel("Models/Circle.030.mesh.j3o");
        
        Material boxMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        boxMat.setColor("Ambient", color); 
        boxMat.setBoolean("UseMaterialColors", true); 
        pratoEsquerdo.setMaterial(boxMat);
        pratoEsquerdo.setName(this.getName());
        pratoEsquerdo.rotate(0, 0, -0.4f);
        
        pratoEsquerdo.setLocalTranslation(0, -1f, 0);
        
        rootNode.attachChild(pratoEsquerdo);
    }

    @Override
    public void movimentaObjeto(Node rootNode, float tpf) {
        Spatial pratoEsquerdo = rootNode.getChild(this.getName());
        Quaternion rotation   = pratoEsquerdo.getLocalRotation();
        
        long time = System.currentTimeMillis() - this.getTimeInitial();
        
        if (time < this.TIME_MIN_SECONDS) {
            pratoEsquerdo.rotate(0, 0, tpf * this.getDirection());
        }
        
        if (time >= this.TIME_MIN_SECONDS) {
            if (!this.isInitial()) {
                this.setInitial(true);
                this.setDirection( this.getDirection() * (-1));
            }
            this.objRotate(pratoEsquerdo, 0, 0, tpf * this.getDirection());
        }
        
        if (time > this.TIME_MAX_SECONDS) {
            this.setInitial(false);
            this.setTimeInitial(System.currentTimeMillis());
            this.setDirection(this.getDirection() * (-1));
        }

        this.objRotate(pratoEsquerdo, 0, 0, tpf * this.getDirection());
    }
    
}
