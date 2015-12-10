package angrybirds;

import Vue.Vue;
import Vue.VueEnnemie;
import Vue.VueOiseau;
import controller.ControllerEnnemie;
import controller.ControllerOiseau;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.AffineTransform;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Model;
import model.ModeleEnnemie;
import model.ModeleOiseau;

/**
 *
 * Classe effectuant toutes les actions n�cessaires au fonctionnement du jeu
 *
 * @author Thibaut
 *
 */
@SuppressWarnings("serial")
public class Jeu extends JPanel {

    protected JFrame f;
    Random r = new Random();
    boolean elastiqueTire = false;
    double t = 0.0;
    VueOiseau o = null;
   
    private ArrayList<Vue> objetsVue = new ArrayList<>();
    ArrayList<VueEnnemie> ennemimort= null;
    ArrayList<Coordonne> trace = new ArrayList<>();
    Courbe courbeSuivie = new Courbe(this);
    int rayonLancer = 75;

    /**
     *
     * Initialise le jeu et ajoute les Listeners
     *
     * @param nbEnnemis
     */
    public Jeu(int nbEnnemis) {
        VueEnnemie ennemitmp = null;
        for (int i = 0; i < nbEnnemis; i++) {
            ModeleEnnemie modeleEnnemie = new ModeleEnnemie(50);
            ControllerEnnemie controllerEnnemie = new ControllerEnnemie();
            VueEnnemie vueEnnemie = new VueEnnemie(modeleEnnemie, controllerEnnemie);
            modeleEnnemie.addObserver(vueEnnemie);

            for (Vue e : objetsVue) {
                if (collision(e, ennemitmp)) {
                    ennemitmp = vueEnnemie;
                }


            }

            if (ennemitmp == null) {
                ennemitmp = vueEnnemie;
            }

            addEnnemi(vueEnnemie);
        }
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent arg0) {
                if (!((ModeleOiseau) o.getModel()).estLance() && elastiqueTire) {
                    lancerOiseau();

                    try {
                        jouerSon("bird.wav");
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {

                if (!elastiqueTire) {
                    elastiqueTire = true;
                    try {
                        jouerSon("slingshot.wav");
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e1) {
                        e1.printStackTrace();
                    }
                }

                ModeleOiseau o2 = (ModeleOiseau) o.getModel();


                if (e.getX() >= o2.getCoInit().x - rayonLancer && e.getX() < o2.getCoInit().x + rayonLancer && !o2.estLance()) {
                    /*for (int i = 0; i < o.px.length; i++) {
                     o.px[i] += e.getX() - o.getCo().x;

                     //   o.px2[i] += e.getX() - o.getCo().x;
                     }*/
                    o2.getCo().setX(e.getX());
                    repaint();
                }
                if (e.getY() >= o2.getCoInit().y - rayonLancer && e.getY() < o2.getCoInit().y + rayonLancer && !o2.estLance()) {
                    /* for (int i = 0; i < o.px.length; i++) {
                     //o.py[i] += e.getY() - o.co.y;

                     // o.py2[i] += e.getY() - o.co.y;
                     }*/
                    o2.getCo().setY(e.getY());
                    repaint();
                }

                courbeSuivie.updateCoordOiseau();
                courbeSuivie.updateCoordMilieu(o2.getCoInit().y - (o2.getCo().y - o2.getCoInit().y) * 10);
                if ((o2.getCoInit().y - o2.getCo().y) > 0) {
                    courbeSuivie.updateCoordFin(
                            new Coordonne(o2.getCoInit().x - (o2.getCo().x - o2.getCoInit().x) * 15, 475 + (o2.getCoInit().y - o2.getCo().y) * 5));
                } else {
                    courbeSuivie.updateCoordFin(new Coordonne(o2.getCoInit().x - (o2.getCo().x - o2.getCoInit().x) * 15, 475 - (o2.getCoInit().y - o2.getCo().y) * 5));
                }
            }
        });

        nouveauLancer();
    }

    /**
     * IHM creant le fond et les ennemis
     *
     * @param g le graphique du jeu
     */
    public void paintComponent(Graphics g) {
        ModeleOiseau o2 = (ModeleOiseau) o.getModel();
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g.drawImage(new ImageIcon(Main.class.getResource("fond2.png")).getImage(), 0, 0, null);
        g.drawImage(new ImageIcon(Main.class.getResource("slingshot.png")).getImage(), o2.getCoInit().x + 10, 410, null);

        g2d.setColor(Color.red);
        Iterator<Coordonne> iterator = trace.iterator();
        while(iterator.hasNext()){
            Coordonne c = iterator.next();
            g.fillOval(c.x, c.y, o2.getTaille() / 5, o2.getTaille() / 5);
        }
        

        AffineTransform old = g2d.getTransform();
        AffineTransform trans = new AffineTransform();
        trans.rotate(Math.toRadians(o2.getDirectionY() * 25), o2.getCo().x + o2.getTaille() / 2, o2.getCo().y + o2.getTaille() / 2);

        g2d.transform(trans);

        // Ce qui pivotera

        o.paintComponent(this, g2d);

        g2d.setTransform(old);

        // Ce qui ne pivotera pas

        for (Vue e : getObjetsScene()) {
            if(e instanceof VueOiseau == false){
                ((VueEnnemie)e).paintComponent(g);
            }
        }

        g2d.drawImage(new ImageIcon(Main.class.getResource("slingshot2.png")).getImage(), o2.getCoInit().x + 10, 410, null);
        g2d.drawImage(new ImageIcon(Main.class.getResource("caisse.png")).getImage(), 0, 480, null);
    }

    /**
     * gere la collision entre 2 entites
     *
     * @param e1 une entite
     * @param e2 une entite
     * @return si il touche ou pas
     */
    public boolean collision(Vue v1, Vue v2) {
        if (v1 == null) {
            System.out.println("V1NUL");
            System.exit(1);
        } else if (v2 == null) {
            System.out.println("V2NUL");
            System.exit(1);
        }
        Model e1 = v1.getModel();
        Model e2 = v2.getModel();

        Coordonne co1 = e1.getCo();
        Coordonne co2 = e2.getCo();

        
        return co1.getX() < co2.getX() + e2.getTaille()
                && co1.getX() + e1.getTaille() > co2.getX()
                && co1.getY() < co2.getY() + e2.getTaille()
                && co1.getY() + e1.getTaille() > co2.getY();
    }

    /**
     * timer du jeu, retire les ennemis si touche et le lance de l'oiseau
     *
     */
    public void lancerOiseau() {
        final ModeleOiseau o2 = (ModeleOiseau) o.getModel();
        elastiqueTire = true;
        ((ModeleOiseau) o.getModel()).setLance(true);

        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            // animation du jeu
            public void run() {
                Coordonne coordSuivante = courbeSuivie.coordSuivante(t);
                Coordonne co = o2.getCo();
                co.setX(coordSuivante.getX());
                co.setY(coordSuivante.getY());

                for (Vue e : objetsVue) {
                    if (e instanceof VueOiseau == false) {
                        if (collision(o, e)) {
                            System.out.println(o.model.getCo());
                            System.out.println(e.model.getCo());
                            System.out.println("test");
                           
                            this.cancel(); 
                            nouveauLancer();
                            if(ennemimort != null){
                                ennemimort = new ArrayList<>();
                                ennemimort.add((VueEnnemie) e);
                            }
                        }
                    }
                }

                if (ennemimort != null) {
                    getEnnemis().remove(ennemimort);
                    ennemimort = null;
                    nouveauLancer();
                }

                if (o2.getCo().y > 475) {
                    this.cancel();
                    nouveauLancer();
                }

                if (o2.getCo().x > 800 || o2.getCo().x < -o2.getTaille() || o2.getCo().y > 700) {
                    this.cancel();
                    nouveauLancer();
                }

                t += 0.005;
                if (t * 2 >= 7) {
                    this.cancel();
                    nouveauLancer();
                }

                if ((o2.getCo().x % 10) <= 3) {
                    trace.add(new Coordonne(o2.getCo().x + o2.getTaille() / 2, o2.getCo().y + o2.getTaille() / 2));
                }

                o2.setDirectionY(courbeSuivie.directionBec(t));

                repaint();
            }
        };

        timer.scheduleAtFixedRate(task, 0, 10);

    }

    /**
     * lit le son du jeu
     *
     */
    public void jouerSon(String nomFichier)
            throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        URL url = Main.class.getResource(nomFichier);
        final Clip clip = AudioSystem.getClip();
        try (AudioInputStream audioIn = AudioSystem.getAudioInputStream(url)) {
            clip.open(audioIn);
        }
        clip.start();
    }

    /**
     *
     * Reinisialise l'oiseau et effectue un nouveau lancer
     *
     */
    public void nouveauLancer() {
        t = 0.0;
        ModeleEnnemie modeleEnnemie = new ModeleEnnemie(50);
        ControllerEnnemie controllerEnnemie = new ControllerEnnemie();
        VueEnnemie vueEnnemie = new VueEnnemie(modeleEnnemie, controllerEnnemie);
        modeleEnnemie.addObserver(vueEnnemie);


        elastiqueTire = false;
        trace = new ArrayList<>();


        // creation de l'oiseau
        // model
        ModeleOiseau modeleOiseau = new ModeleOiseau();

        // controller
        ControllerOiseau oiseauControleur = new ControllerOiseau(modeleOiseau);

        // vue
        VueOiseau vueOiseau = new VueOiseau(modeleOiseau, oiseauControleur);
        o = vueOiseau;
        // enregistre le notifieur
        modeleOiseau.addObserver(vueOiseau);

    }

    public ArrayList<Vue> getEnnemis() {
        return objetsVue;
    }

    public void addEnnemi(Vue vue) {
        objetsVue.add(vue);
    }

    public void setEnnemis(ArrayList<Vue> ennemis) {
        this.objetsVue = ennemis;
    }

    public ArrayList<Vue> getObjetsScene() {
        return objetsVue;
    }
}
