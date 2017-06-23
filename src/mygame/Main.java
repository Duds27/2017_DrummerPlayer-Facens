package mygame;

import br.facens.models.interfaces.DrummerAbstract;
import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
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
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import com.jme3.scene.Node;
import com.jme3.system.AppSettings;

/**
 * This is the Main Class of your Game. You should only do initialization here.
 * Move your Logic into AppStates or Controls
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    private boolean isRunning;

    private AudioNode audio_prato;
    private AudioNode audio_pedal;
    private AudioNode audio_caixa_esquerda;
    private AudioNode audio_caixa_direita;
    private AudioNode audio_caixa_central;
    private AudioNode audio_nature;

    private DrummerAbstract bateriaCompleta;
    
    private boolean mexerPratoEsquerdo;
    private boolean PratoEsquerdoAux = false;
    
    private boolean mexerPratoDireito;
    private boolean PratoDireitoAux = false;
    
    private boolean mexerPedal;
    private boolean PedalAux = false;
    
    private boolean mexerCaixaEsquerda;
    private boolean CaixaEsquerdaAux = false;
    
    private boolean mexerCaixaDireita;
    private boolean CaixaDireitaAux = false;
    
    private boolean mexerCaixaCentral;
    private boolean CaixaCentralAux = false;
    
    private long tempoInicialPratoEsquerdo;
    private long tempoInicialPratoDireito;
    private long tempoInicialPedal;
    private long tempoInicialCaixaEsquerda;
    private long tempoInicialCaixaDireita;
    private long tempoInicialCaixaCentral;
    
    
    private AnimChannel channel;
    private AnimControl control;
    

    
    private static Main app;
    public static void main(String[] args) {
        AppSettings settings = new AppSettings(true);
        settings.setFullscreen(true);
        settings.setResolution(1366, 768);
        app = new Main();     
        app.setShowSettings(false);
        app.setSettings(settings);
        app.start();
    }

    @Override
    public void simpleInitApp() {

        viewPort.setBackgroundColor(ColorRGBA.LightGray);
        
        this.setIsRunning(true);
        this.setMexerPratoEsquerdo(false);
        this.setMexerPratoDireito(false);
        this.setMexerPedal(false);
        this.setMexerCaixaEsquerda(false);
        this.setMexerCaixaDireita(false);
        this.setMexerCaixaCentral(false);

        
        this.buildDrummer();

        
        
        initKeys(); // load my custom keybinding
        initAudio();
        initLight();
        
    }

    public void initLight() {
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
    }
    
           
    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code

        listener.setLocation(cam.getLocation());
        listener.setRotation(cam.getRotation());
        
        // Rotina de movimentação do prato esquerdo        
        if (this.isMexerPratoEsquerdo()) {            
            long tempoAux = System.currentTimeMillis();
                
            //System.out.println("Tempo: " + Math.abs(tempoAux - tempoInicialPratoEsquerdo));
                
            if ( Math.abs(tempoAux - tempoInicialPratoEsquerdo) > 2000 ) {
                PratoEsquerdoAux = false;                
                System.out.println("3 segundos! " + Math.abs(tempoAux - tempoInicialPratoEsquerdo));
                this.setMexerPratoEsquerdo(false);
            }            
        }
        
        // Rotina de movimentação do prato direito        
        if (this.isMexerPratoDireito()) {
            long tempoAux = System.currentTimeMillis();
                
            //System.out.println("Tempo: " + Math.abs(tempoAux - tempoInicialPratoDireito));
                
            if ( Math.abs(tempoAux - tempoInicialPratoDireito) > 2000 ) {
                PratoDireitoAux = false;                
                System.out.println("3 segundos! " + Math.abs(tempoAux - tempoInicialPratoDireito));
                this.setMexerPratoDireito(false);
            }            
        }
        
        
        // Rotina de movimentação do pedal
        if (this.isMexerPedal()) {
            long tempoAux = System.currentTimeMillis();
                
            //System.out.println("Tempo: " + Math.abs(tempoAux - tempoInicialPedal));
                
            if ( Math.abs(tempoAux - tempoInicialPedal) > 2000 ) {
                PedalAux = false;                
                System.out.println("3 segundos! " + Math.abs(tempoAux - tempoInicialPedal));
                this.setMexerPedal(false);
            }            
        }
        
        // Rotina de movimentação da caixa esquerda
        if (this.isMexerCaixaEsquerda()) {
            long tempoAux = System.currentTimeMillis();
                
            //System.out.println("Tempo: " + Math.abs(tempoAux - tempoInicialCaixaEsquerda));
                
            if ( Math.abs(tempoAux - tempoInicialCaixaEsquerda) > 2000 ) {
                CaixaEsquerdaAux = false;                
                System.out.println("3 segundos! " + Math.abs(tempoAux - tempoInicialCaixaEsquerda));
                this.setMexerCaixaEsquerda(false);
            }            
        }
        
        // Rotina de movimentação da caixa direita
        if (this.isMexerCaixaDireita()) {
            long tempoAux = System.currentTimeMillis();
                
            //System.out.println("Tempo: " + Math.abs(tempoAux - tempoInicialCaixaDireita));
                
            if ( Math.abs(tempoAux - tempoInicialCaixaDireita) > 2000 ) {
                CaixaDireitaAux = false;                
                System.out.println("3 segundos! " + Math.abs(tempoAux - tempoInicialCaixaDireita));
                this.setMexerCaixaDireita(false);
            }            
        }
        
        // Rotina de movimentação da caixa central
        if (this.isMexerCaixaCentral()) {
            long tempoAux = System.currentTimeMillis();
                
            //System.out.println("Tempo: " + Math.abs(tempoAux - tempoInicialCaixaCentral));
                
            if ( Math.abs(tempoAux - tempoInicialCaixaCentral) > 2000 ) {
                CaixaCentralAux = false;                
                System.out.println("3 segundos! " + Math.abs(tempoAux - tempoInicialCaixaCentral));
                this.setMexerCaixaCentral(false);
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
        inputManager.addMapping("Prato_Esquerdo_Batida", new KeyTrigger(KeyInput.KEY_R));
        inputManager.addMapping("Prato_Direito_Batida", new KeyTrigger(KeyInput.KEY_Y));
        inputManager.addMapping("Pedal_Batida", new KeyTrigger(KeyInput.KEY_V));
        inputManager.addMapping("Caixa_Esquerda_Batida", new KeyTrigger(KeyInput.KEY_F));
        inputManager.addMapping("Caixa_Direita_Batida", new KeyTrigger(KeyInput.KEY_H));
        inputManager.addMapping("Caixa_Central_Batida", new KeyTrigger(KeyInput.KEY_G));
        
        // Add the names to the action listener.
        inputManager.addListener(actionListener, "Pause", "Sair");
        inputManager.addListener(analogListener, "Rotate_Left", "Rotate_Right");
        inputManager.addListener(actionListener, "Prato_Esquerdo_Batida");
        inputManager.addListener(actionListener, "Prato_Direito_Batida");
        inputManager.addListener(actionListener, "Pedal_Batida");
        inputManager.addListener(actionListener, "Caixa_Esquerda_Batida");
        inputManager.addListener(actionListener, "Caixa_Direita_Batida");
        inputManager.addListener(actionListener, "Caixa_Central_Batida");

    }

    /**
     * We create two audio nodes.
     */
    private void initAudio() {
        /* gun shot sound is to be triggered by a mouse click. */
        audio_prato = new AudioNode(assetManager, "Sounds/prato-agudo.wav", DataType.Buffer);
        audio_prato.setPositional(false);
        audio_prato.setLooping(false);
        audio_prato.setVolume(2);
        rootNode.attachChild(audio_prato);
        
        audio_pedal = new AudioNode(assetManager, "Sounds/pedal.wav", DataType.Buffer);
        audio_pedal.setPositional(false);
        audio_pedal.setLooping(false);
        audio_pedal.setVolume(2);
        rootNode.attachChild(audio_pedal);
        
        
        audio_caixa_esquerda = new AudioNode(assetManager, "Sounds/caixa-1.wav", DataType.Buffer);
        audio_caixa_esquerda.setPositional(false);
        audio_caixa_esquerda.setLooping(false);
        audio_caixa_esquerda.setVolume(2);
        rootNode.attachChild(audio_caixa_esquerda);
        
        audio_caixa_direita = new AudioNode(assetManager, "Sounds/caixa-3.wav", DataType.Buffer);
        audio_caixa_direita.setPositional(false);
        audio_caixa_direita.setLooping(false);
        audio_caixa_direita.setVolume(2);
        rootNode.attachChild(audio_caixa_direita);
        
        audio_caixa_central = new AudioNode(assetManager, "Sounds/caixa-2.wav", DataType.Buffer);
        audio_caixa_central.setPositional(false);
        audio_caixa_central.setLooping(false);
        audio_caixa_central.setVolume(2);
        rootNode.attachChild(audio_caixa_central);
        
        

        /* nature sound - keeps playing in a loop. */
       /* audio_nature = new AudioNode(assetManager, "Sounds/pedal.wav", DataType.Stream);
        audio_nature.setLooping(true);  // activate continuous playing
        audio_nature.setPositional(false);
        audio_nature.setVolume(3);
        rootNode.attachChild(audio_nature);
        audio_nature.play(); // play continuously!*/
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
                audio_prato.playInstance(); // play each instance once!                
                if (!PratoEsquerdoAux) {
                    setMexerPratoEsquerdo(true);                    
                    //audio_gun.playInstance(); // play each instance once!
                    tempoInicialPratoEsquerdo = System.currentTimeMillis();
                    PratoEsquerdoAux          = true;
                }                
            }
            
            if (name.equals("Prato_Direito_Batida") && !keyPressed) {                
                audio_prato.playInstance(); // play each instance once!                
                if (!PratoDireitoAux) {
                    setMexerPratoDireito(true);                    
                    //audio_gun.playInstance(); // play each instance once!
                    tempoInicialPratoDireito = System.currentTimeMillis();
                    PratoDireitoAux          = true;
                }                
            }
            
            if (name.equals("Pedal_Batida") && !keyPressed) {                
                audio_pedal.playInstance(); // play each instance once!                
                if (!PedalAux) {
                    setMexerPedal(true);                    
                    //audio_gun.playInstance(); // play each instance once!
                    tempoInicialPedal = System.currentTimeMillis();
                    PedalAux          = true;
                }                
            }
            
            if (name.equals("Caixa_Esquerda_Batida") && !keyPressed) {                
                audio_caixa_esquerda.playInstance(); // play each instance once!                
                if (!CaixaEsquerdaAux) {
                    setMexerCaixaEsquerda(true);
                    //audio_gun.playInstance(); // play each instance once!
                    tempoInicialCaixaEsquerda = System.currentTimeMillis();
                    CaixaEsquerdaAux          = true;
                }                
            }
            
            if (name.equals("Caixa_Direita_Batida") && !keyPressed) {                
                audio_caixa_direita.playInstance(); // play each instance once!                
                if (!CaixaDireitaAux) {
                    setMexerCaixaDireita(true);
                    //audio_gun.playInstance(); // play each instance once!
                    tempoInicialCaixaDireita = System.currentTimeMillis();
                    CaixaDireitaAux          = true;
                }                
            }
            
            if (name.equals("Caixa_Central_Batida") && !keyPressed) {                
                audio_caixa_central.playInstance(); // play each instance once!                
                if (!CaixaCentralAux) {
                    setMexerCaixaCentral(true);
                    //audio_gun.playInstance(); // play each instance once!
                    tempoInicialCaixaCentral = System.currentTimeMillis();
                    CaixaCentralAux          = true;
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
        
        Node model = (Node) assetManager.loadModel("Models/drums_current8.j3o");
        model.scale(0.5f, 0.5f, 0.5f);
        model.setName("Battery");
        model.setLocalTranslation(0, 0, 0);
        
        rootNode.attachChild(model);

    }

   
    
    
    public boolean isIsRunning() {
        return isRunning;
    }

    public void setIsRunning(boolean isRunning) {
        this.isRunning = isRunning;
    }    

    public AudioNode getAudio_Prato() {
        return audio_prato;
    }

    public void setAudio_Prato(AudioNode audio_prato) {
        this.audio_prato = audio_prato;
    }

    public AudioNode getAudio_nature() {
        return audio_nature;
    }

    public void setAudio_nature(AudioNode audio_nature) {
        this.audio_nature = audio_nature;
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

    public boolean isPratoEsquerdoAux() {
        return PratoEsquerdoAux;
    }

    public void setPratoEsquerdoAux(boolean PratoEsquerdoAux) {
        this.PratoEsquerdoAux = PratoEsquerdoAux;
    }

    public boolean isMexerPratoDireito() {
        return mexerPratoDireito;
    }

    public void setMexerPratoDireito(boolean mexerPratoDireito) {
        this.mexerPratoDireito = mexerPratoDireito;
    }

    public boolean isPratoDireitoAux() {
        return PratoDireitoAux;
    }

    public void setPratoDireitoAux(boolean PratoDireitoAux) {
        this.PratoDireitoAux = PratoDireitoAux;
    }

    public long getTempoInicialPratoDireito() {
        return tempoInicialPratoDireito;
    }

    public void setTempoInicialPratoDireito(long tempoInicialPratoDireito) {
        this.tempoInicialPratoDireito = tempoInicialPratoDireito;
    }

    public AnimChannel getChannel() {
        return channel;
    }

    public void setChannel(AnimChannel channel) {
        this.channel = channel;
    }

    public AnimControl getControl() {
        return control;
    }

    public void setControl(AnimControl control) {
        this.control = control;
    }

    public static Main getApp() {
        return app;
    }

    public static void setApp(Main aApp) {
        app = aApp;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public AnalogListener getAnalogListener() {
        return analogListener;
    }

    public void setAnalogListener(AnalogListener analogListener) {
        this.analogListener = analogListener;
    }

    public AudioNode getAudio_prato() {
        return audio_prato;
    }

    public void setAudio_prato(AudioNode audio_prato) {
        this.audio_prato = audio_prato;
    }

    public AudioNode getAudio_pedal() {
        return audio_pedal;
    }

    public void setAudio_pedal(AudioNode audio_pedal) {
        this.audio_pedal = audio_pedal;
    }

    public boolean isMexerPedal() {
        return mexerPedal;
    }

    public void setMexerPedal(boolean mexerPedal) {
        this.mexerPedal = mexerPedal;
    }

    public boolean isPedalAux() {
        return PedalAux;
    }

    public void setPedalAux(boolean PedalAux) {
        this.PedalAux = PedalAux;
    }

    public long getTempoInicialPedal() {
        return tempoInicialPedal;
    }

    public void setTempoInicialPedal(long tempoInicialPedal) {
        this.tempoInicialPedal = tempoInicialPedal;
    }

    public AudioNode getAudio_caixa_esquerda() {
        return audio_caixa_esquerda;
    }

    public void setAudio_caixa_esquerda(AudioNode audio_caixa_esquerda) {
        this.audio_caixa_esquerda = audio_caixa_esquerda;
    }

    public boolean isMexerCaixaEsquerda() {
        return mexerCaixaEsquerda;
    }

    public void setMexerCaixaEsquerda(boolean mexerCaixaEsquerda) {
        this.mexerCaixaEsquerda = mexerCaixaEsquerda;
    }

    public boolean isCaixaEsquerdaAux() {
        return CaixaEsquerdaAux;
    }

    public void setCaixaEsquerdaAux(boolean CaixaEsquerdaAux) {
        this.CaixaEsquerdaAux = CaixaEsquerdaAux;
    }

    public long getTempoInicialCaixaEsquerda() {
        return tempoInicialCaixaEsquerda;
    }

    public void setTempoInicialCaixaEsquerda(long tempoInicialCaixaEsquerda) {
        this.tempoInicialCaixaEsquerda = tempoInicialCaixaEsquerda;
    }

    public AudioNode getAudio_caixa_direita() {
        return audio_caixa_direita;
    }

    public void setAudio_caixa_direita(AudioNode audio_caixa_direita) {
        this.audio_caixa_direita = audio_caixa_direita;
    }

    public boolean isMexerCaixaDireita() {
        return mexerCaixaDireita;
    }

    public void setMexerCaixaDireita(boolean mexerCaixaDireita) {
        this.mexerCaixaDireita = mexerCaixaDireita;
    }

    public boolean isCaixaDireitaAux() {
        return CaixaDireitaAux;
    }

    public void setCaixaDireitaAux(boolean CaixaDireitaAux) {
        this.CaixaDireitaAux = CaixaDireitaAux;
    }

    public long getTempoInicialCaixaDireita() {
        return tempoInicialCaixaDireita;
    }

    public void setTempoInicialCaixaDireita(long tempoInicialCaixaDireita) {
        this.tempoInicialCaixaDireita = tempoInicialCaixaDireita;
    }

    public AudioNode getAudio_caixa_central() {
        return audio_caixa_central;
    }

    public void setAudio_caixa_central(AudioNode audio_caixa_central) {
        this.audio_caixa_central = audio_caixa_central;
    }

    public boolean isMexerCaixaCentral() {
        return mexerCaixaCentral;
    }

    public void setMexerCaixaCentral(boolean mexerCaixaCentral) {
        this.mexerCaixaCentral = mexerCaixaCentral;
    }

    public boolean isCaixaCentralAux() {
        return CaixaCentralAux;
    }

    public void setCaixaCentralAux(boolean CaixaCentralAux) {
        this.CaixaCentralAux = CaixaCentralAux;
    }

    public long getTempoInicialCaixaCentral() {
        return tempoInicialCaixaCentral;
    }

    public void setTempoInicialCaixaCentral(long tempoInicialCaixaCentral) {
        this.tempoInicialCaixaCentral = tempoInicialCaixaCentral;
    }
    
    
}
