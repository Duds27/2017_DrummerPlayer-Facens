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
public class Bumbo extends DrummerAbstract {

    public Bumbo(String name) {
        super(name);
    }

    @Override
    public void criarObjeto(ColorRGBA color, Node rootNode, AssetManager assetManager) {
        Spatial bumbo = assetManager.loadModel("Models/Cylinder.mesh.j3o");

        Material boxMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        boxMat.setColor("Ambient", color);
        boxMat.setBoolean("UseMaterialColors", true);
        bumbo.setMaterial(boxMat);
        bumbo.setName(this.getName());

        //bumbo.scale(0.75f);
        bumbo.setLocalScale(1, 1, 1);
        bumbo.rotate(0, 0, 0f);
        bumbo.setLocalTranslation(-0.5f, 2.5f, -1.5f);

        rootNode.attachChild(bumbo);
    }

    @Override
    public void movimentaObjeto(Node rootNode, float tpf) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void controlUpdate(float tpf) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
