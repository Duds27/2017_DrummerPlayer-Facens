package mygame;

import br.facens.custom.controls.RotatePratoControl;
import br.facens.model.objects.BateriaCompleta;
import br.facens.model.objects.Bumbo;
import br.facens.model.objects.CaixaCentral;
import br.facens.model.objects.CaixaDireita;
import br.facens.model.objects.CaixaEsquerda;
import br.facens.model.objects.PratoDireito;
import br.facens.model.objects.PratoEsquerdo;
import br.facens.models.config.KeyBinding;
import br.facens.models.interfaces.DrummerAbstract;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.app.SimpleApplication;
import com.jme3.asset.plugins.ZipLocator;
import com.jme3.audio.AudioData.DataType;
import com.jme3.audio.AudioNode;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.collision.shapes.CollisionShape;
import com.jme3.bullet.collision.shapes.GImpactCollisionShape;
import com.jme3.bullet.control.CharacterControl;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.bullet.util.CollisionShapeFactory;
import com.jme3.export.binary.BinaryImporter;
import com.jme3.input.KeyInput;
import com.jme3.input.MouseInput;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.input.controls.MouseButtonTrigger;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Matrix3f;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Geometry;
import com.jme3.scene.Mesh;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Box;
import java.io.File;
import java.io.IOException;
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
public class Main extends SimpleApplication implements AnimEventListener {

    private boolean isRunning;
    private List<Spatial> listaObj;

    private AudioNode audio_gun;
    private AudioNode audio_nature;

    private DrummerAbstract pratoEsquerdo;
    private DrummerAbstract pratoDireito;
    private DrummerAbstract caixaDireita;
    private DrummerAbstract caixaCentral;
    private DrummerAbstract caixaEsquerda;
    private DrummerAbstract bumbo;
    private DrummerAbstract bateriaCompleta;
    
    private boolean mexerPratoEsquerdo;
    private boolean PratoEsquerdoAux = false;
    
    private long tempoInicialPratoEsquerdo;
    
    
    private AnimChannel channel;
    private AnimControl control;
    

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

    public DrummerAbstract getCaixaDireita() {
        return caixaDireita;
    }

    public void setCaixaDireita(DrummerAbstract caixaDireita) {
        this.caixaDireita = caixaDireita;
    }

    public DrummerAbstract getCaixaCentral() {
        return caixaCentral;
    }

    public void setCaixaCentral(DrummerAbstract caixaCentral) {
        this.caixaCentral = caixaCentral;
    }

    public DrummerAbstract getCaixaEsquerda() {
        return caixaEsquerda;
    }

    public void setCaixaEsquerda(DrummerAbstract caixaEsquerda) {
        this.caixaEsquerda = caixaEsquerda;
    }

    public DrummerAbstract getBumbo() {
        return bumbo;
    }

    public void setBumbo(DrummerAbstract bumbo) {
        this.bumbo = bumbo;
    }
    
    public DrummerAbstract getBateriaCompleta() {
        return bateriaCompleta;
    }

    public void setBateriaCompleta(DrummerAbstract bateriaCompleta) {
        this.bateriaCompleta = bateriaCompleta;
    }    
    
    public boolean isMexerPratoEsquerdo() {
        return mexerPratoEsquerdo;
    }

    public void setMexerPratoEsquerdo(boolean mexerPratoEsquerdo) {
        this.mexerPratoEsquerdo = mexerPratoEsquerdo;
    }

    public long getTempoInicialPratoEsquerdo() {
        return tempoInicialPratoEsquerdo;
    }

    public void setTempoInicialPratoEsquerdo(long tempoInicialPratoEsquerdo) {
        this.tempoInicialPratoEsquerdo = tempoInicialPratoEsquerdo;
    }
    
    
    public static Main app;
    public static void main(String[] args) {
        app = new Main();
        app.showSettings = false;
        app.start();
    }

    @Override
    public void simpleInitApp() {

        viewPort.setBackgroundColor(ColorRGBA.LightGray);
        
        this.setIsRunning(true);
        this.setMexerPratoEsquerdo(false);

        Node model = (Node) assetManager.loadModel("Models/drums_current8.j3o");
        model.scale(0.5f, 0.5f, 0.5f);
        model.setName("Battery");
        model.setLocalTranslation(0, 0, 0);
                
        Spatial plan = assetManager.loadModel("Models/Plane.002.mesh.j3o");
        plan.scale(-0.5f, -0.5f, -0.5f); 
        plan.setLocalTranslation(0, 0, 0);
       
        rootNode.attachChild(plan);

        this.buildDrummer();
/*
        Node node = (Node) this.getPratoEsquerdo().getObjeto();
        
        control = model.getControl(AnimControl.class);
        control.addListener(this);
    
        for (String anim : control.getAnimationNames())
            System.out.println(anim); 
    
    
        channel = control.createChannel();
        channel.setAnim("Walk",0.005f);
        */
        this.setListaObj(new ArrayList<Spatial>());
        listaObj.add(this.getPratoDireito().getObjeto());
        listaObj.add(this.getPratoEsquerdo().getObjeto());
        listaObj.add(this.getCaixaDireita().getObjeto());
        listaObj.add(this.getCaixaCentral().getObjeto());
        listaObj.add(this.getCaixaEsquerda().getObjeto());
        listaObj.add(this.getBumbo().getObjeto());
        //istaObj.add(this.getBateriaCompleta().getObjeto());
        
        
        //listaObj.add(model);
        rootNode.attachChild(model);
  
        


         
        
        
        
        
        /**
         * A white ambient light source.
         */
        AmbientLight ambient = new AmbientLight();
        ambient.setColor(ColorRGBA.White);
        rootNode.addLight(ambient);
        
        DirectionalLight dl = new DirectionalLight();
        dl.setColor(ColorRGBA.White);
        dl.setDirection(new Vector3f(2.8f, -2.8f, -2.8f).normalizeLocal());
        rootNode.addLight(dl);

        
        
        //KeyBinding keys = new KeyBinding(inputManager, speed, listaObj);
        initKeys(); // load my custom keybinding
        initAudio();
        
    }

    
           
    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code

        listener.setLocation(cam.getLocation());
        listener.setRotation(cam.getRotation());
        
        
        
        // Rotina de movimentação do prato esquerdo
        
        if (this.isMexerPratoEsquerdo()) {
            
            long tempoAux = System.currentTimeMillis();
                
            System.out.println("Tempo: " + Math.abs(tempoAux - tempoInicialPratoEsquerdo));
                
            if ( Math.abs(tempoAux - tempoInicialPratoEsquerdo) > 2000 ) {
                PratoEsquerdoAux = false;                
                System.out.println("3 segundos! " + Math.abs(tempoAux - tempoInicialPratoEsquerdo));
                this.setMexerPratoEsquerdo(false);
                //RotatePratoControl.getInstance().setHabilitarRotate(false);
                this.getPratoEsquerdo().getObjeto().rotate(0, 0, tpf);
            }
            
            if ( Math.abs(tempoAux - tempoInicialPratoEsquerdo) <= 2000 ) {
                this.getPratoEsquerdo().movimentaObjeto(rootNode, tpf);
            }
            
        }
        
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
        inputManager.addMapping("Rotate_Left", new KeyTrigger(KeyInput.KEY_LEFT), new MouseButtonTrigger(MouseInput.BUTTON_LEFT));
        inputManager.addMapping("Rotate_Right", new KeyTrigger(KeyInput.KEY_RIGHT), new MouseButtonTrigger(MouseInput.BUTTON_RIGHT));
        inputManager.addMapping("Rotate_Up", new KeyTrigger(KeyInput.KEY_UP));
        inputManager.addMapping("Rotate_Down", new KeyTrigger(KeyInput.KEY_DOWN));
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
                
                audio_gun.playInstance(); // play each instance once!
                
                if (!PratoEsquerdoAux) {
                    setMexerPratoEsquerdo(true);
                    //RotatePratoControl.getInstance().setHabilitarRotate(true);
                    
                    //audio_gun.playInstance(); // play each instance once!
                    tempoInicialPratoEsquerdo = System.currentTimeMillis();
                    PratoEsquerdoAux          = true;
                }                
            }
            
            
            if (name.equals("Walk") && !keyPressed) {
            if (!channel.getAnimationName().equals("Walk")) {
                 channel.setAnim("Walk", 1f);
                 channel.setLoopMode(LoopMode.Loop);
               }

            }




          if (name.equals("Pull") && !keyPressed) {
            if (!channel.getAnimationName().equals("pull")) {
              channel.setAnim("pull", 0.50f);
              channel.setLoopMode(LoopMode.Loop);
            }
          }

        }
    };

    private AnalogListener analogListener = new AnalogListener() {
        public void onAnalog(String name, float value, float tpf) {
            if (isIsRunning()) {
                if (name.equals("Rotate_Left")) {
                    rootNode.rotate(0, value * speed * 3 * (-1), 0);
                    System.out.println("Rotação Esquerda");
                }

                if (name.equals("Rotate_Right")) {
                    rootNode.rotate(0, (value * speed * 3), 0);
                    System.out.println("Rotação Direita");
                }
                
                if (name.equals("Rotate_Up")) {
                    rootNode.rotate(0, 0, (value * speed * 3));
                    System.out.println("Rotação Cima");
                }

                if (name.equals("Rotate_Down")) {
                    rootNode.rotate(0, 0, value * speed * 3 * (-1));
                    System.out.println("Rotação Baixo");
                }
                
            }
        }
    };
    
    
    
    
    private void buildDrummer() {

        // Instancia as classes dos objetos da bateria
        this.setPratoEsquerdo(new PratoEsquerdo("Prato_Esquerdo"));
        this.setPratoDireito(new PratoDireito("Prato_Direito"));
        this.setCaixaDireita(new CaixaDireita("Caixa_Direita"));
        this.setCaixaCentral(new CaixaCentral("Caixa_Central"));
        this.setCaixaEsquerda(new CaixaEsquerda("Caixa_Esquerda"));
        this.setBumbo(new Bumbo("Bumbo"));
        //this.setBateriaCompleta(new BateriaCompleta("Bateria_Completa"));
        
        
        // Inicializa os objetos da bateria
        this.getPratoEsquerdo().criarObjeto(ColorRGBA.Yellow, rootNode, assetManager);
        this.getPratoDireito().criarObjeto(ColorRGBA.Yellow, rootNode, assetManager);
        this.getCaixaDireita().criarObjeto(ColorRGBA.LightGray, rootNode, assetManager);
        this.getCaixaCentral().criarObjeto(ColorRGBA.Brown, rootNode, assetManager);
        this.getCaixaEsquerda().criarObjeto(ColorRGBA.Orange, rootNode, assetManager);
        this.getBumbo().criarObjeto(ColorRGBA.White, rootNode, assetManager);
        //this.getBateriaCompleta().criarObjeto(ColorRGBA.Blue, rootNode, assetManager);

        this.getPratoEsquerdo().setTimeInitial(System.currentTimeMillis());
        this.getPratoDireito().setTimeInitial(System.currentTimeMillis());
        this.getCaixaDireita().setTimeInitial(System.currentTimeMillis());
        this.getCaixaCentral().setTimeInitial(System.currentTimeMillis());
        this.getCaixaEsquerda().setTimeInitial(System.currentTimeMillis());
        this.getBumbo().setTimeInitial(System.currentTimeMillis());
        //this.getBateriaCompleta().setTimeInitial(System.currentTimeMillis());
     
        
        

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

    @Override
    public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName) {
        if (animName.equals("Walk")) {
            channel.setAnim("pull", 0.0f);
            channel.setLoopMode(LoopMode.DontLoop);
            channel.setSpeed(1f);
        }  

        if (animName.equals("pull")) {
            channel.setAnim("Walk", 0.0f);
            channel.setLoopMode(LoopMode.DontLoop);
            channel.setSpeed(1f);
        }
    }

    @Override
    public void onAnimChange(AnimControl control, AnimChannel channel, String animName) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
