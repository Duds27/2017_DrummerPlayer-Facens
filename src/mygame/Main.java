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

    private boolean initialPratoPequeno  = false;
    private long timeInitialPratoPequeno = 0l;
    private int directionPratoPequeno    = 1;
    
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
    
        criarCaixaEsquerda();
        
        
        criarPratoPequeno();
        criarPratoEsquerdo();
        criarPratoDireito();
        
        criarPedestalEsquerdo();
        criarPedestalDireito();
        
        Spatial pratoPequeno = rootNode.getChild("Prato_Pequeno");
        pratoPequeno.setLocalTranslation(0, -1, 0);
        
        
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
        //movimentaPratoPequeno(tpf);
        
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
    
    
    private void criarCaixaEsquerda() {
    
        Spatial caixaEsquerda = assetManager.loadModel("Models/Circle.012.mesh.j3o");
        
        Material boxMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        boxMat.setColor("Ambient", ColorRGBA.Brown); 
        boxMat.setBoolean("UseMaterialColors", true); 
        caixaEsquerda.setMaterial(boxMat);
        caixaEsquerda.setName("Caixa_Esquerda");

        caixaEsquerda.scale(0.5f);
        caixaEsquerda.rotate(0, 0, 15f);
        caixaEsquerda.setLocalTranslation(-1f, -0.5f, -1);
        
        
        rootNode.attachChild(caixaEsquerda);
    
    }
    
    
    
    
    
    
    private void criarPratoPequeno() {
        Spatial pratoPequeno = assetManager.loadModel("Models/Circle.032.mesh.j3o");
        
        Material boxMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        boxMat.setColor("Ambient", ColorRGBA.Yellow); 
        boxMat.setBoolean("UseMaterialColors", true); 
        pratoPequeno.setMaterial(boxMat);
        pratoPequeno.setName("Prato_Pequeno");
        pratoPequeno.rotate(0, 0, -0.4f);
        
        rootNode.attachChild(pratoPequeno);
    }
    
    
    private void criarPedestalEsquerdo() {
        Spatial pedestalPratoEsquerdo = assetManager.loadModel("Models/Circle.023.mesh.j3o");
        pedestalPratoEsquerdo.scale(0.1f);
        
        Material boxMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        boxMat.setColor("Ambient", ColorRGBA.LightGray); 
        boxMat.setBoolean("UseMaterialColors", true); 
        pedestalPratoEsquerdo.setMaterial(boxMat);
        pedestalPratoEsquerdo.setName("Pedestal_Prato_Esquerdo");
        pedestalPratoEsquerdo.rotate(0, 0, -0.4f);
        
        pedestalPratoEsquerdo.setLocalTranslation(-0.4f, -0.8f, 0);
        
        rootNode.attachChild(pedestalPratoEsquerdo);
    }
    
    
    private void criarPedestalDireito() {
        Spatial pedestalPratoDireito = assetManager.loadModel("Models/Circle.023.mesh.j3o");
        pedestalPratoDireito.scale(0.1f);
        
        Material boxMat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        boxMat.setColor("Ambient", ColorRGBA.LightGray); 
        boxMat.setBoolean("UseMaterialColors", true); 
        pedestalPratoDireito.setMaterial(boxMat);
        pedestalPratoDireito.setName("Pedestal_Prato_Esquerdo");
        pedestalPratoDireito.rotate(0, 0, -0.4f);
        
        pedestalPratoDireito.setLocalTranslation(-5.0f, -1f, 0);
        
        rootNode.attachChild(pedestalPratoDireito);
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
    
    private void movimentaPratoPequeno (float tpf) {
        Spatial pratoPequeno = rootNode.getChild("Prato_Pequeno");
        
        long time = System.currentTimeMillis() - timeInitialPratoPequeno;
        
        if (time < TIME_MIN_SECONDS) {
            pratoPequeno.setLocalTranslation(0, tpf * directionPratoPequeno, 0);
        }
        
        if (time >= TIME_MIN_SECONDS) {
            if (!initialPratoPequeno) {
                initialPratoPequeno    = true;
                directionPratoPequeno *= (-1);
            }
            pratoPequeno.setLocalTranslation(0, tpf * directionPratoPequeno, 0);
        }
        
        if (time > TIME_MAX_SECONDS) {
            initialPratoPequeno     = false;
            timeInitialPratoPequeno = System.currentTimeMillis();
            directionPratoPequeno  *= (-1);
        }
        
        pratoPequeno.rotate(0, tpf * directionPratoPequeno, 0);
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
