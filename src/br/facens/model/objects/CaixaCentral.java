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
public class CaixaCentral extends DrummerAbstract {

    public CaixaCentral(String name) {
        super(name);
    }

    @Override
    public void criarObjeto(ColorRGBA color, Node rootNode, AssetManager assetManager) {
        Spatial caixaDireita = assetManager.loadModel("Models/Circle.mesh.j3o");

        Material boxMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        boxMat.setColor("Ambient", color);
        boxMat.setBoolean("UseMaterialColors", true);
        caixaDireita.setMaterial(boxMat);
        caixaDireita.setName(this.getName());

        caixaDireita.scale(0.75f);
        caixaDireita.rotate(0, 0, 0f);
        caixaDireita.setLocalTranslation(-2.25f, 2.5f, -1.0f);

        rootNode.attachChild(caixaDireita);
    }

    @Override
    public void movimentaObjeto(Node rootNode, float tpf) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void controlUpdate(float tpf) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
