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
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Eduardo
 */
public class BateriaCompleta extends DrummerAbstract {

    public BateriaCompleta(String name) {
        super(name);
    }



    @Override
    public void movimentaObjeto(Node rootNode, float tpf) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void controlUpdate(float tpf) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void criarObjeto(Node rootNode, AssetManager assetManager) {
        Spatial bateriaCompleta = assetManager.loadModel("Models/drums_current8.j3o");

        this.setObjeto(bateriaCompleta);
        
        Material boxMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        boxMat.setColor("Ambient", ColorRGBA.Black); 
        boxMat.setBoolean("UseMaterialColors", true); 
        
        bateriaCompleta.scale(0.5f, 0.5f, 0.5f);
        
        bateriaCompleta.setMaterial(boxMat);
        bateriaCompleta.setName(this.getName());
        bateriaCompleta.rotate(0, 0, -0.4f);
        bateriaCompleta.setLocalTranslation(-5f, 0, 0);
        
        rootNode.attachChild(bateriaCompleta);
    }
    
}
