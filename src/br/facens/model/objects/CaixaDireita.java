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
import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;

/**
 *
 * @author Eduardo
 */
public class CaixaDireita extends DrummerAbstract {

    public CaixaDireita(String name) {
        super(name);
    }
    
    @Override
    public void criarObjeto(ColorRGBA color, Node rootNode, AssetManager assetManager) {
        Spatial caixaDireita = assetManager.loadModel("Models/Circle.018.mesh.j3o");

        Material boxMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        boxMat.setColor("Ambient", color);
        boxMat.setBoolean("UseMaterialColors", true);
        caixaDireita.setMaterial(boxMat);
        caixaDireita.setName(this.getName());

        //caixaDireita.scale(0.75f);
        //caixaDireita.rotate(0, 0, 0f);
        //caixaDireita.setLocalTranslation(-4.1f, 2.5f, -1.5f);
        
        caixaDireita.scale(1.310106f, 1.558843f, 1.309964f);
        caixaDireita.rotate(1.4197099f, -85.260635f, -5.4986f);
        caixaDireita.setLocalTranslation(2.812693f, 5.506758f, -0.080744f);
        

        rootNode.attachChild(caixaDireita);
    }

    @Override
    public void movimentaObjeto(Node rootNode, float tpf) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void controlUpdate(float tpf) {
     //   throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
