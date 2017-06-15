package mygame;

import br.facens.model.objects.PratoDireito;
import br.facens.model.objects.PratoEsquerdo;
import br.facens.models.config.KeyBinding;
import br.facens.models.interfaces.DrummerAbstract;
import com.jme3.app.SimpleApplication;
import com.jme3.audio.AudioData.DataType;
import com.jme3.audio.AudioNode;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.java.games.input.Component;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    private boolean isRunning;
    private List<Spatial> listaObj;

    private AudioNode audio_gun;
    private AudioNode audio_nature;

    private DrummerAbstract pratoEsquerdo;
    private DrummerAbstract pratoDireito;
    
    private boolean mexerPratoEsquerdo;

    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }

    public DrummerAbstract getPratoEsquerdo() {
        return pratoEsquerdo;
    }

    public List<Spatial> getListaObj() {
        return listaObj;
    }

    public void setListaObj(List<Spatial> listaObj) {
        this.listaObj = listaObj;
    }

    public AudioNode getAudio_gun() {
        return audio_gun;
    }

    public void setAudio_gun(AudioNode audio_gun) {
        this.audio_gun = audio_gun;
    }

    public AudioNode getAudio_nature() {
        return audio_nature;
    }

    public void setAudio_nature(AudioNode audio_nature) {
        this.audio_nature = audio_nature;
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

    public boolean isMexerPratoEsquerdo() {
        return mexerPratoEsquerdo;
    }

    public void setMexerPratoEsquerdo(boolean mexerPratoEsquerdo) {
        this.mexerPratoEsquerdo = mexerPratoEsquerdo;
    }
    
    public static void main(String[] args) {
        Main app = new Main();
        app.showSettings = false;
        app.start();
    }

    @Override
    public void simpleInitApp() {

        this.setIsRunning(true);
        this.setMexerPratoEsquerdo(false);

        Spatial model = assetManager.loadModel("Models/drums_current8.j3o");
        model.scale(0.5f, 0.5f, 0.5f);

        //Models/drums_current8-scene_node/Cymbal.001/Circle.030-entity/Circle.030-ogremesh
        this.buildDrummer();

        this.setListaObj(new ArrayList<Spatial>());
        listaObj.add(this.getPratoDireito().getObjeto());
        listaObj.add(this.getPratoEsquerdo().getObjeto());

        //criarCaixaEsquerda();
        //criarPratoPequeno();
        //criarPedestalEsquerdo();
        //criarPedestalDireito();
        //Spatial pratoPequeno = rootNode.getChild("Prato_Pequeno");
        //pratoPequeno.setLocalTranslation(0, -1, 0);
        //rootNode.attachChild(model);
        /**
         * A white ambient light source.
         */
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White);
        rootNode.addLight(ambient);

        //KeyBinding keys = new KeyBinding(inputManager, speed, listaObj);
        initKeys(); // load my custom keybinding
        initAudio();
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code

        listener.setLocation(cam.getLocation());
        listener.setRotation(cam.getRotation());
        
        if (this.isMexerPratoEsquerdo()) {
            this.getPratoEsquerdo().movimentaObjeto(rootNode, tpf);
        }
        
        //this.getPratoEsquerdo().movimentaObjeto(rootNode, tpf);
        //this.getPratoDireito().movimentaObjeto(rootNode, tpf);
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    /**
     * Custom Keybinding: Map named actions to inputs.
     */
    private void initKeys() {
        // You can map one or several inputs to one named action
        inputManager.addMapping("Pause", new KeyTrigger(KeyInput.KEY_P));
        inputManager.addMapping("Sair", new KeyTrigger(KeyInput.KEY_Q));
        inputManager.addMapping("Rotate_Left", new KeyTrigger(KeyInput.KEY_L),
                new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping("Rotate_Right", new KeyTrigger(keyInput.KEY_R),
                new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        inputManager.addMapping("Prato_Esquerdo_Batida", new KeyTrigger(KeyInput.KEY_F));
        // Add the names to the action listener.
        inputManager.addListener(actionListener, "Pause", "Sair");
        inputManager.addListener(analogListener, "Rotate_Left", "Rotate_Right");
        inputManager.addListener(actionListener, "Prato_Esquerdo_Batida");
    }

    /**
     * We create two audio nodes.
     */
    private void initAudio() {
        /* gun shot sound is to be triggered by a mouse click. */
        audio_gun = new AudioNode(assetManager, "Sounds/prato-agudo.wav", DataType.Buffer);
        audio_gun.setPositional(false);
        audio_gun.setLooping(false);
        audio_gun.setVolume(2);
        rootNode.attachChild(audio_gun);

        /* nature sound - keeps playing in a loop. */
        audio_nature = new AudioNode(assetManager, "Sounds/pedal.wav", DataType.Stream);
        audio_nature.setLooping(true);  // activate continuous playing
        audio_nature.setPositional(false);
        audio_nature.setVolume(3);
        rootNode.attachChild(audio_nature);
        audio_nature.play(); // play continuously!
    }

    private ActionListener actionListener = new ActionListener() {
        public void onAction(String name, boolean keyPressed, float tpf) {
            if (name.equals("Pause") && !keyPressed) {
                setIsRunning(!isIsRunning());
                System.out.println("Tecla P foi pressionada");
            }

            if (name.equals("Sair") && !keyPressed) {
                System.exit(0);
                System.out.println("Tecla Q foi pressionada");
            }
            
            if (name.equals("Prato_Esquerdo_Batida") && !keyPressed) {
                audio_gun.playInstance();; // play each instance once!
                setMexerPratoEsquerdo(true);
            }

        }
    };

    private AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float value, float tpf) {
            if (isIsRunning()) {
                if (name.equals("Rotate_Left")) {
                    rootNode.rotate(0, value * speed * (-1), 0);
                    System.out.println("Rotação Esquerda");
                }

                if (name.equals("Rotate_Right")) {
                    rootNode.rotate(0, (value * speed), 0);
                    System.out.println("Rotação Direita");
                }

            }
        }
    };

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
