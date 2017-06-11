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

    private static final long TIME_MAX_SECONDS = 1000;
    private static final long TIME_MIN_SECONDS = 500;
    
    
    private boolean initialPratoEsquerdo  = false;
    private long timeInitialPratoEsquerdo = 0l;
    private int directionPratoEsquerdo    = 1;
    
    private boolean initialPratoDireito  = false;
    private long timeInitialPratoDireito = 0l;
    private int directionPratoDireito    = 1;
    
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
        
        criarPratoEsquerdo();
        criarPratoDireito();
        

        //rootNode.attachChild(model);
                
        /** A white ambient light source. */ 
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White);
        rootNode.addLight(ambient);         
        
        timeInitialPratoEsquerdo = System.currentTimeMillis();
        timeInitialPratoDireito = System.currentTimeMillis();
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
        
                
        movimentaPratoEsquerdo(tpf);
        movimentaPratoDireito(tpf);
        
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
    
    
    
    
    private void criarPratoEsquerdo() {
        Spatial pratoEsquerdo = assetManager.loadModel("Models/Circle.030.mesh.j3o");
        
        Material boxMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        boxMat.setColor("Ambient", ColorRGBA.Yellow); 
        boxMat.setBoolean("UseMaterialColors", true); 
        pratoEsquerdo.setMaterial(boxMat);
        pratoEsquerdo.setName("Prato_Esquerdo");
        pratoEsquerdo.rotate(0, 0, -0.4f);
        
        rootNode.attachChild(pratoEsquerdo);

    }
    
    private void criarPratoDireito() {
        Spatial pratoDireito = assetManager.loadModel("Models/Circle.030.mesh.j3o");
        
        Material boxMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        boxMat.setColor("Ambient", ColorRGBA.Yellow); 
        boxMat.setBoolean("UseMaterialColors", true); 
        pratoDireito.setMaterial(boxMat);
        pratoDireito.setName("Prato_Direito");
        pratoDireito.rotate(0, 0, -0.4f);
        pratoDireito.setLocalTranslation(-5f, 0, 0);
        
        rootNode.attachChild(pratoDireito);

    }
    
    
    private void movimentaPratoEsquerdo (float tpf) {
        Spatial pratoEsquerdo = rootNode.getChild("Prato_Esquerdo");
        Quaternion rotation   = pratoEsquerdo.getLocalRotation();
        
        long time = System.currentTimeMillis() - timeInitialPratoEsquerdo;
        
        if (time < TIME_MIN_SECONDS) {
            pratoEsquerdo.rotate(0, 0, tpf * directionPratoEsquerdo);
        }
        
        if (time >= TIME_MIN_SECONDS) {
            if (!initialPratoEsquerdo) {
                initialPratoEsquerdo    = true;
                directionPratoEsquerdo *= (-1);
            }
            pratoEsquerdo.rotate(0, 0, tpf * directionPratoEsquerdo);
        }
        
        if (time > TIME_MAX_SECONDS) {
            initialPratoEsquerdo     = false;
            timeInitialPratoEsquerdo = System.currentTimeMillis();
            directionPratoEsquerdo  *= (-1);
        }
        
        pratoEsquerdo.rotate(0, 0, tpf * directionPratoEsquerdo);
    }
    
    
    
    private void movimentaPratoDireito (float tpf) {
        Spatial pratoDireito = rootNode.getChild("Prato_Direito");
        Quaternion rotation  = pratoDireito.getLocalRotation();
        
        long time = System.currentTimeMillis() - timeInitialPratoDireito;
        
        if (time < TIME_MIN_SECONDS) {
            pratoDireito.rotate(0, 0, tpf * directionPratoDireito);
        }
        
        if (time >= TIME_MIN_SECONDS) {
            if (!initialPratoDireito) {
                initialPratoDireito    = true;
                directionPratoDireito *= (-1);
            }
            pratoDireito.rotate(0, 0, tpf * directionPratoDireito);
        }
        
        if (time > TIME_MAX_SECONDS) {
            initialPratoDireito     = false;
            timeInitialPratoDireito = System.currentTimeMillis();
            directionPratoDireito  *= (-1);
        }
        
        pratoDireito.rotate(0, 0, tpf * directionPratoDireito);
    }
    
}
