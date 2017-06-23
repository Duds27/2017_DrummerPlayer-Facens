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
public class PratoEsquerdo extends DrummerAbstract {

    public PratoEsquerdo(String name) {
        super(name);
    }
    
    @Override
    public void criarObjeto(ColorRGBA color, Node rootNode, AssetManager assetManager) {
        
        //Spatial pratoEsquerdo  = assetManager.loadModel("Models/drums_current8-scene_node/Cymbal.001/Circle.030-entity/Circle.030-ogremesh");
       Spatial pratoEsquerdo  = assetManager.loadModel("Models/Circle.030.mesh.j3o");
        //Spatial troncoEsquerdo = assetManager.loadModel("Models/Circle.021.mesh.j3o");
       
  
        this.setObjeto(pratoEsquerdo);
        
        Material boxMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        boxMat.setColor("Ambient", color); 
        boxMat.setBoolean("UseMaterialColors", true); 
        pratoEsquerdo.setMaterial(boxMat);
        pratoEsquerdo.setName(this.getName());
        pratoEsquerdo.rotate(0, 0, -0.4f);
        
        pratoEsquerdo.setLocalTranslation(-0.5f, 1f, 0);
        
        /*Material boxMat1 = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        boxMat1.setColor("Ambient", ColorRGBA.LightGray);
        boxMat1.setBoolean("UseMaterialColors", true);
        troncoEsquerdo.setMaterial(boxMat1);
        troncoEsquerdo.setName(this.getName());
        troncoEsquerdo.scale(0.25f, 0.255f, 0.25f);
        
        troncoEsquerdo.setLocalTranslation(-0.75f, -1.25f, -1.5f);*/
                
        rootNode.attachChild(pratoEsquerdo);
        //rootNode.attachChild(troncoEsquerdo);
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

    @Override
    protected void controlUpdate(float tpf) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void controlRender(RenderManager rm, ViewPort vp) {
 //       throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
