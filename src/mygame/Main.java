package mygame;

import br.facens.model.objects.PratoDireito;
import br.facens.model.objects.PratoEsquerdo;
import br.facens.models.interfaces.DrummerAbstract;
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

    
    private DrummerAbstract pratoEsquerdo;
    private DrummerAbstract pratoDireito;
    
    
    public DrummerAbstract getPratoEsquerdo() {
        return pratoEsquerdo;
    }

    public void setPratoEsquerdo(DrummerAbstract pratoEsquerdo) {
        this.pratoEsquerdo = pratoEsquerdo;
    }

    public DrummerAbstract getPratoDireito() {
        return pratoDireito;
    }

    public void setPratoDireito(DrummerAbstract pratoDireito) {
        this.pratoDireito = pratoDireito;
    }
    
    
    
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
    
        
        this.buildDrummer();
        
        
        
        
        //criarCaixaEsquerda();
        //criarPratoPequeno();
        
        //criarPedestalEsquerdo();
        //criarPedestalDireito();
        
        //Spatial pratoPequeno = rootNode.getChild("Prato_Pequeno");
        //pratoPequeno.setLocalTranslation(0, -1, 0);
        
        
        //rootNode.attachChild(model);
                
        /** A white ambient light source. */ 
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White);
        rootNode.addLight(ambient);         
        
    }
    
    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
        
       this.getPratoEsquerdo().movimentaObjeto(rootNode, tpf);
       this.getPratoDireito().movimentaObjeto(rootNode, tpf);
        
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    
    
    
    
    private void buildDrummer() {

        // Instancia as classes dos objetos da bateria
        this.setPratoEsquerdo(new PratoEsquerdo("Prato_Esquerdo"));
        this.setPratoDireito(new PratoDireito("Prato_Direito"));
        
        // Inicializa os objetos da bateria
        this.getPratoEsquerdo().criarObjeto(ColorRGBA.Yellow, rootNode, assetManager);
        this.getPratoDireito().criarObjeto(ColorRGBA.Yellow, rootNode, assetManager);
        
        
        this.getPratoEsquerdo().setTimeInitial(System.currentTimeMillis());
        this.getPratoDireito().setTimeInitial(System.currentTimeMillis());
       
        
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
    
    
    
   /* private void movimentaPratoPequeno (float tpf) {
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
    */
    
}
