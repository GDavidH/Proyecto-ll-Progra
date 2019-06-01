package visual;

import data.XMLFigureManager;
import domain.Figure;
import domain.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import org.jdom.JDOMException;
import threads.FigureThread;
import static visual.LandFrame.isSimulationRun;
import static visual.LandFrame.myFigureList;

public class LandFrame extends JFrame implements ActionListener {

    // Declaracion de Variables Globales
    public static boolean isInterrupted;//variable de interrupt
    public static boolean isSimulationRun;//variable de Simulation
    public static boolean isBarrier;//variable de barrera
    public static boolean isRevert;//variable de revert
    public static boolean isAnimated;//variable de animacion
    private final JButton btnCreate;
    private final JButton btnBarrier;
    private final JButton btnRevert;
    private final JButton btnSimulation;
    private final JButton btnInterrupt;
    private final JTextField txtValue;
    private final JTextField txtLanes;
    private final JComboBox cbSpeed;
    private final JButton btnBackground;
    public static ArrayList<Figure> myFigureList;
    public final JCheckBox checkBoxImage;
    public final ArrayList<Image> myArrayImage;
    int count20 = 0;

    //constructor LandFrame
    public LandFrame(ArrayList<Figure> myFigureList) throws IOException {
        super("Threads Competition");
        this.isInterrupted = false;
        this.isBarrier = false;
        this.isSimulationRun = false;
        this.myFigureList = myFigureList;
        myArrayImage = new ArrayList<>();

        // Se crea el campo de texto y se agrega al JFrame
        txtValue = new JTextField(5);
        txtValue.setBounds(210, 650, 100, 25);
        this.add(txtValue);

        txtLanes = new JTextField(5);
        txtLanes.setBounds(330, 650, 100, 25);
        this.add(txtLanes);

        // Se crea el combo box y se agrega al JFrame
        cbSpeed = new JComboBox();
        cbSpeed.addItem("Faster");
        cbSpeed.addItem("Fast");
        cbSpeed.addItem("Medium");
        cbSpeed.addItem("Slow");
        cbSpeed.setBounds(100, 650, 100, 30);
        this.add(cbSpeed);

        // BOTONES*************************************************************
        // Se crean los botones y se agregan al JFrame
        btnCreate = new JButton("Create");
        btnCreate.addActionListener(this);//escucha eventos
        btnCreate.setBounds(100, 600, 100, 40);
        btnCreate.setSize(100, 40);
        this.add(btnCreate);

        btnBarrier = new JButton("Barrier");
        btnBarrier.addActionListener(this);//escucha eventos
        btnBarrier.setBounds(220, 600, 100, 40);
        this.add(btnBarrier);

        btnRevert = new JButton("Revert");
        btnRevert.addActionListener(this);//escucha eventos
        btnRevert.setBounds(340, 600, 100, 40);
        this.add(btnRevert);

        btnSimulation = new JButton("Simulation");
        btnSimulation.addActionListener(this);//escucha eventos
        btnSimulation.setBounds(460, 600, 100, 40);
        this.add(btnSimulation);

        btnInterrupt = new JButton("Interrupt");
        btnInterrupt.addActionListener(this);//escucha eventos
        btnInterrupt.setBounds(460, 650, 100, 40);
        this.add(btnInterrupt);

        checkBoxImage = new JCheckBox();
        checkBoxImage.addActionListener(this);//escucha eventos
        checkBoxImage.setBounds(590, 600, 100, 40);
        this.add(checkBoxImage);

        btnBackground = new JButton("");
        btnBackground.addActionListener(this);//escucha eventos
        btnBackground.setBounds(360, 600, 100, 40);
        btnBackground.setBackground(new Color(100, 255, 100));
        this.add(btnBackground);

        //Guarda las imagenes en el ArrayList
        myArrayImage.add(ImageIO.read(getClass().getResourceAsStream("/image1.jpg")));
        myArrayImage.add(ImageIO.read(getClass().getResourceAsStream("/image2.jpg")));
        myArrayImage.add(ImageIO.read(getClass().getResourceAsStream("/image3.png")));
        myArrayImage.add(ImageIO.read(getClass().getResourceAsStream("/image4.png")));

        //Determina el tamaño del JFrame
        this.setSize(1367, 730);

        //Hace visible el JFrame
        this.setVisible(true);
    }

    //Sobre carga del Constructor
    public LandFrame() {

        //Inicialización de las variables
        this.btnCreate = new JButton();
        this.btnBarrier = new JButton();
        this.btnRevert = new JButton();
        this.btnSimulation = new JButton();
        this.btnInterrupt = new JButton();
        this.txtValue = new JTextField();
        this.cbSpeed = new JComboBox();
        this.btnBackground = new JButton();
        this.txtLanes = new JTextField();
        this.checkBoxImage = new JCheckBox();
        this.myArrayImage = new ArrayList<>();
    }

    //Metodo que se implementa de JFrame 
    //Pinta en pantalla
    @Override
    public void paint(Graphics g) {

        super.paint(g);

        //Pinta los cuadros: la pista
        int y = 50;
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 0) {
                g.setColor(Color.BLUE);
            } else {
                g.setColor(Color.gray);
            }
            g.fillRect(60, y, 1250, 50);
            y += 50;
        }

        // Pinta la linea de meta y el paso del tren
        int y2 = 50;
        for (int i = 0; i < 20; i++) {
            if (i % 2 == 0) {
                g.setColor(Color.BLACK);
                g.fillRect(1200, y2, 25, 25);
                g.setColor(Color.white);
                g.fillRect(1225, y2, 25, 25);

            } else {
                g.setColor(Color.white);
                g.fillRect(1200, y2, 25, 25);

                g.setColor(Color.BLACK);
                g.fillRect(1225, y2, 25, 25);
            }

            g.setColor(Color.BLACK);
            g.drawLine(605, 50, 605, y2);
            g.drawLine(624, 50, 624, y2);

            y2 += 25;
            g.setColor(Color.white);
            g.fillRect(600, y2 - 25, 30, 25);
            g.setColor(Color.BLACK);
            g.drawLine(600, y2 - 25, 630, y2 - 25);
        }

        //iterate over all figures
        //Pinta todas las figuras que existen dentro de myFigureList
        switch (count20) {
            case 0:
                Simulation();
                count20 += 1;
                break;
            case 40:
                count20 = 0;
                break;
            default:
                count20 += 1;
                break;
        }

        if (!isAnimated) {
            for (Figure myFigure : myFigureList) {

                //Pinta y actualiza el recorrido de las figuras tipo Square
                if (myFigure.getIdentification().equalsIgnoreCase("Square")) {
                    g.setColor(Color.cyan);
                    g.fillRect(myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(),
                            myFigure.getLength(), myFigure.getWidth());
                }

                //Pinta y actualiza el recorrido de las figuras tipo Oval
                if (myFigure.getIdentification().equalsIgnoreCase("Oval")) {
                    g.setColor(Color.MAGENTA);
                    g.fillOval(myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(),
                            myFigure.getLength(), myFigure.getWidth());
                }

                //Pinta y actualiza el recorrido de las figuras tipo Arc
                if (myFigure.getIdentification().equalsIgnoreCase("Arc")) {
                    g.setColor(Color.yellow);
                    g.fillArc(myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(),
                            myFigure.getLength() - 1, myFigure.getWidth(), 30, 310);
                }

                //Pinta y actualiza el recorrido de las figuras tipo Round
                if (myFigure.getIdentification().equalsIgnoreCase("Round")) {
                    g.setColor(Color.red);
                    g.fillRoundRect(myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(),
                            20, myFigure.getWidth(), 70, 20);
                }

            } //end for
        } else if (isAnimated) {
            for (Figure myFigure : myFigureList) {
                //Pinta y actualiza el recorrido de los square pero con Imagen
                if (myFigure.getIdentification().equalsIgnoreCase("Square")) {
                    g.drawImage(myArrayImage.get(0), myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(), 40, 25, null);
                }

                //Pinta y actualiza el recorrido de las figuras tipo Oval pero con Imagen
                if (myFigure.getIdentification().equalsIgnoreCase("Oval")) {
                    g.drawImage(myArrayImage.get(1), myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(), 40, 25, null);
                }

                //Pinta y actualiza el recorrido de las figuras tipo Arc pero con Imagen
                if (myFigure.getIdentification().equalsIgnoreCase("Arc")) {
                    g.drawImage(myArrayImage.get(3), myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(), 40, 25, null);
                }

                //Pinta y actualiza el recorrido de las figuras tipo Round pero con Imagen
                if (myFigure.getIdentification().equalsIgnoreCase("Round")) {
                    g.drawImage(myArrayImage.get(2), myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(), 40, 25, null);
                }
            } //end for
        }
    }// End Paint()

    //Repinta en todo momento el JFrame
    public void paintAgain() {
        repaint();
    }

    //Escucha los eventos de los botones
    @Override
    public void actionPerformed(ActionEvent e) {
        //identificar la fuente del evento
        if (e.getSource() == btnRevert) {
            if (isRevert) {
                isRevert = false;
            } else {
                isRevert = true;
            }
        }
        //accion boton de la barrera
        if (e.getSource() == btnBarrier) {
            if (isBarrier) {
                isBarrier = false;
            } else {
                isBarrier = true;
            }
        }

        //accion boton interrupt
        if (e.getSource() == btnInterrupt) {
            if (isInterrupted) {
                isInterrupted = false;
            } else {
                isInterrupted = true;
            }
        }

        // Realiza funcionalidad de checkBoxImage
        if (e.getSource() == checkBoxImage) {
            if (isAnimated) {
                isAnimated = false;
            } else {
                isAnimated = true;
            }
        }

        //funcionalidad boton Create
        //Crea todos los threads 
        if (e.getSource() == btnCreate) {

            ArrayList<Figure> figures = new ArrayList();
            try {
                //Se llama al documento xml
                XMLFigureManager manager = new XMLFigureManager("./data/Threads Competition.xml");
                //Obtiene el dato que se ingrese en txtValue
                int aux = 0;
                //Crea la cantidad de figuras que se especifiquen en el txtvalue
                for (int i = 0; i < Integer.parseInt(txtValue.getText()); i++) {
                    int random;
                    int lanes = 5;
                    if (txtLanes.getText().length() == 0) {
                        lanes = 10;
                    }
                    if (Integer.parseInt(txtLanes.getText()) <= 10 && Integer.parseInt(txtLanes.getText()) >= 5) {
                        lanes = Integer.parseInt(txtLanes.getText());
                    }

                    //Ciclo que comprueba que el random actual sea direfente al random anterior
                    do {
                        random = (int) (Math.random() * lanes + 1);
                    } while (aux == random);

                    //Genera la posicion para pintar las figuras en la pista
                    int position = 50 * random;

                    //Crea las figuras tipo Square
                    if (cbSpeed.getSelectedItem().toString().equalsIgnoreCase("Faster")) {
                        Figure square = new Figure("Square", new Point(60, position), 25, 25);
                        myFigureList.add(square);
                        figures.add(square);
                        manager.insertFigure(square);

                    } else //Crea las figuras tipo Oval
                    if (cbSpeed.getSelectedItem().toString().equalsIgnoreCase("Fast")) {
                        Figure oval = new Figure("Oval", new Point(60, position), 25, 25);
                        myFigureList.add(oval);
                        figures.add(oval);
                        manager.insertFigure(oval);

                    } else //Crea las figuras tipo Arc
                    if (cbSpeed.getSelectedItem().toString().equalsIgnoreCase("Medium")) {
                        Figure arc = new Figure("Arc", new Point(60, position), 25, 25);
                        myFigureList.add(arc);
                        figures.add(arc);
                        manager.insertFigure(arc);

                    } else //Crea las figuras tipo Round
                    if (cbSpeed.getSelectedItem().toString().equalsIgnoreCase("Slow")) {
                        Figure round = new Figure("Round", new Point(60, position), 25, 25);
                        myFigureList.add(round);
                        figures.add(round);
                        manager.insertFigure(round);
                    }
                    aux = random;
                }

                //thread for controlling the movement of the figures
                FigureThread figureThread = null;
                Figure figureAux = null;
                for (Figure figure : figures) {
                    // Si el random es igual al anterior duerme unos segundos el Thread y reescribe el x de la figura
                    if (figureAux != null && figure.getPointPosition().getX() >= figureAux.getPointPosition().getX() - 30) {
                        if (figure.getIdentification().equalsIgnoreCase("Square")) {
                            figureThread.sleep(270);
                        }
                        if (figure.getIdentification().equalsIgnoreCase("Oval")) {
                            figureThread.sleep(250);
                        }
                        if (figure.getIdentification().equalsIgnoreCase("Arc")) {
                            figureThread.sleep(360);
                        }
                        if (figure.getIdentification().equalsIgnoreCase("Round")) {
                            figureThread.sleep(350);
                        }
                    }

                    //Inicia los threads
                    //Inicia los Threads tipo Square
                    if (figure.getIdentification().equalsIgnoreCase("Square")) {
                        figureThread = new FigureThread(figure, 20, figure.getPointPosition().getY());
                        figureThread.start();
                    }

                    //Inicia los Threads tipo Oval
                    if (figure.getIdentification().equalsIgnoreCase("Oval")) {
                        figureThread = new FigureThread(figure, 30, figure.getPointPosition().getY());
                        figureThread.start();
                    }

                    //Inicia los Threads tipo Arc
                    if (figure.getIdentification().equalsIgnoreCase("Arc")) {
                        figureThread = new FigureThread(figure, 40, figure.getPointPosition().getY());
                        figureThread.start();
                    }

                    //Inicia los Threads tipo Round
                    if (figure.getIdentification().equalsIgnoreCase("Round")) {
                        figureThread = new FigureThread(figure, 60, figure.getPointPosition().getY());
                        figureThread.start();
                    }
                    figureAux = figure;
                }

                // Funcionalidad del Boton btnSimulacion
                //Crea threads aleatoriamente
//                            FigureThread.sleep(10000);
                //Atrapa exceptions
            } catch (InterruptedException | JDOMException | IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        if (e.getSource() == btnSimulation) {
            isSimulationRun = !isSimulationRun;
            count20 = 0;
        }
    }

    private void Simulation() {

        if (isSimulationRun) {
            ArrayList<Figure> figures1 = new ArrayList();

            //Obtiene el dato que se ingrese en txtValue
            int aux1 = 0;

            int randomCreate = (int) (Math.random() * 2 + 1);
            int randomFigure = (int) (Math.random() * 4 + 1);
            //Crea la cantidad de figuras que se especifiquen en el random
            for (int i = 0; i < randomCreate; i++) {
                int random;

                //Ciclo que comprueba que el random actual sea direfente al random anterior
                do {
                    random = (int) (Math.random() * 10 + 1);
                } while (aux1 == random);

                //Genera la posicion para pintar las figuras en la pista
                int position = 50 * random;

                //Crea las figuras tipo Square
                switch (randomFigure) {
                    case 1:
                        Figure square = new Figure("Square", new Point(60, position), 25, 25);
                        myFigureList.add(square);
                        figures1.add(square);
                        break;
                    //Crea las figuras tipo Oval
                    case 2:
                        Figure oval = new Figure("Oval", new Point(60, position), 25, 25);
                        myFigureList.add(oval);
                        figures1.add(oval);
                        break;
                    //Crea las figuras tipo Arc
                    case 3:
                        Figure arc = new Figure("Arc", new Point(60, position), 25, 25);
                        myFigureList.add(arc);
                        figures1.add(arc);
                        break;
                    //Crea las figuras tipo Round
                    case 4:
                        Figure round = new Figure("Round", new Point(60, position), 25, 25);
                        myFigureList.add(round);
                        figures1.add(round);
                        break;
                    default:
                        break;
                }
                aux1 = random;
            }

            //thread for controlling the movement of the figures
            FigureThread figureThread1 = null;

            Figure figureAux1 = null;
            for (Figure figure : figures1) {

                //Inicia los threads
                //Inicia los Threads tipo Square
                if (figure.getIdentification().equalsIgnoreCase("Square")) {
                    figureThread1 = new FigureThread(figure, 20, figure.getPointPosition().getY());
                    figureThread1.start();
                }

                //Inicia los Threads tipo Oval
                if (figure.getIdentification().equalsIgnoreCase("Oval")) {
                    figureThread1 = new FigureThread(figure, 30, figure.getPointPosition().getY());
                    figureThread1.start();
                }

                //Inicia los Threads tipo Arc
                if (figure.getIdentification().equalsIgnoreCase("Arc")) {
                    figureThread1 = new FigureThread(figure, 40, figure.getPointPosition().getY());
                    figureThread1.start();
                }

                //Inicia los Threads tipo Round
                if (figure.getIdentification().equalsIgnoreCase("Round")) {
                    figureThread1 = new FigureThread(figure, 60, figure.getPointPosition().getY());
                    figureThread1.start();
                }
                figureAux1 = figure;
            }
        }
    }
}
