package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix3f;
import com.jme3.math.Quaternion;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.java.games.input.Component;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    private boolean initial = false;
    private long timeInitial = 0l;
    private static final long TIME_MAX_SECONDS = 1000;
    private static final long TIME_MIN_SECONDS = 500;
    private int direction = 1;
    
    public static void main(String[] args) {
        Main app = new Main();
        app.showSettings = false;
        app.start();
    }

    @Override
    public void simpleInitApp() {
        
        Spatial model = assetManager.loadModel("Models/drums_current8.j3o");
        model.scale(0.5f, 0.5f, 0.5f);
        
        //Models/drums_current8-scene_node/Cymbal.001/Circle.030-entity/Circle.030-ogremesh
        
        Spatial pratoEsquerdo = assetManager.loadModel("Models/Circle.030.mesh.j3o");
        
        
        Material boxMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        boxMat.setColor("Ambient", ColorRGBA.Yellow); 
        boxMat.setBoolean("UseMaterialColors", true); 
        pratoEsquerdo.setMaterial(boxMat);
        pratoEsquerdo.setName("Prato_Esquerdo");
        pratoEsquerdo.rotate(0, 0, -0.4f);
        

        //rootNode.attachChild(model);
        rootNode.attachChild(pratoEsquerdo);
        
        /** A white ambient light source. */ 
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White);
        rootNode.addLight(ambient);         
        
        timeInitial = System.currentTimeMillis();
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
        
                
        movimentaPratoEsquerdo(tpf);
        
        /*System.out.println("X: " + rotation.getX() + 
                " Y: " + rotation.getY() + 
                " Z: " + rotation.getZ() + 
                " W: " + rotation.getW() +
                " TPF: " + tpf);*/
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    
    private void movimentaPratoEsquerdo (float tpf) {
        Spatial pratoEsquerdo = rootNode.getChild("Prato_Esquerdo");
        Quaternion rotation   = pratoEsquerdo.getLocalRotation();
        
        long time = System.currentTimeMillis() - timeInitial;
        
        if (time < TIME_MIN_SECONDS) {
            pratoEsquerdo.rotate(0, 0, tpf * direction);
        }
        
        if (time >= TIME_MIN_SECONDS) {
            if (!initial) {
                initial = true;
                direction *= (-1);
            }
            pratoEsquerdo.rotate(0, 0, tpf * direction);
        }
        
        if (time > TIME_MAX_SECONDS) {
            initial = false;
            timeInitial = System.currentTimeMillis();
            direction *= (-1);
        }
        
        pratoEsquerdo.rotate(0, 0, tpf * direction);        
    }
    
}
