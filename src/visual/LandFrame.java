package visual;

import data.XMLFigureManager;
import domain.Figure;
import domain.Point;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import org.jdom.JDOMException;
import threads.FigureThread;

public class LandFrame extends JFrame implements ActionListener {

    public static boolean isInterrupted;//variable de interrupt
    public static boolean isBarrier;//variable de barrera
    
    // Declaracion de Variables
    private final JButton btnCreate;
    private final JButton btnBarrier;
    private final JButton btnRevert;
    private final JButton btnSimulation;
    private final JButton btnInterrupt;
    private final JTextField txtValue;
    private final JComboBox cbSpeed;
    private final JButton btnBackground;
    public static  ArrayList<Figure> myFigureList;
    public ArrayList<Figure> squareList = new ArrayList();

    //constructor LandFrame
    public LandFrame(ArrayList<Figure> myFigureList) {
        super("Threads Competition");
        this.isInterrupted = false;
        this.isBarrier = false;
        this.myFigureList = myFigureList;

        // Se crea el campo de texto y se agrega al JFrame
        txtValue = new JTextField(5);
        txtValue.setBounds(210, 650, 100, 25);
        this.add(txtValue);

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
        
        btnInterrupt = new JButton("Interrup");
        btnInterrupt.addActionListener(this);//escucha eventos
        btnInterrupt.setBounds(460, 650, 100, 40);
        this.add(btnInterrupt);

        btnBackground = new JButton("");
        btnBackground.addActionListener(this);//escucha eventos
        btnBackground.setBounds(360, 600, 100, 40);
        btnBackground.setBackground(new Color(100, 255, 100));
        this.add(btnBackground);

        //Determina el tamaño del JFrame
        this.setSize(1367, 730);

        //Hace visible el JFrame
        this.setVisible(true);
    }
    
    //Sobre carga del Constructor
    public LandFrame() {

        this.btnCreate = new JButton();
        this.btnBarrier = new JButton();
        this.btnRevert = new JButton();
        this.btnSimulation = new JButton();
        this.btnInterrupt = new JButton();

        this.txtValue = new JTextField();
        this.cbSpeed = new JComboBox();
        this.btnBackground = new JButton();
    }

    //Metodo que se implementa de JFrame que pinta en pantalla
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
        
        for (Figure myFigure : myFigureList) {

            if (myFigure.getIdentification().equalsIgnoreCase("Square")) {
                g.setColor(Color.cyan);
                g.fillRect(myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(),
                        myFigure.getLength(), myFigure.getWidth());
            }

            if (myFigure.getIdentification().equalsIgnoreCase("Oval")) {
                g.setColor(Color.MAGENTA);
                g.fillOval(myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(),
                        myFigure.getLength(), myFigure.getWidth());
            }

            if (myFigure.getIdentification().equalsIgnoreCase("Arc")) {
                g.setColor(Color.yellow);
                g.fillArc(myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(),
                        myFigure.getLength(), myFigure.getWidth(), 50, 300);
            }

            if (myFigure.getIdentification().equalsIgnoreCase("Round")) {
                g.setColor(Color.red);
                g.fillRoundRect(myFigure.getPointPosition().getX(), myFigure.getPointPosition().getY(),
                        50, myFigure.getWidth(), 40, 20);
            }
        } //end for
    }

    //Repinta en todo momento el JFrame
    public void paintAgain() {
        repaint();
    }

    //Escucha los eventos de los botones
    @Override
    public void actionPerformed(ActionEvent e) {

        //identificar la fuente del evento
        if (e.getSource() == btnRevert) {

        }
        //accion boton de la barrera
        if (e.getSource() == btnBarrier) {
            if(isBarrier){
                isBarrier=false;    
            }else{
                isBarrier=true;
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

        if (e.getSource() == btnCreate) {

            squareList.clear();
            ArrayList<Figure> figures = new ArrayList();
            try {
                XMLFigureManager manager = new XMLFigureManager("./data/Threads Competition.xml");

                //Obtiene el dato que se ingrese en txtValue
                int severalTimes = Integer.parseInt(txtValue.getText());

                for (int i = 0; i < severalTimes; i++) {
                    int random = (int) (Math.random() * 10 + 1);
                    int p1 = 50 * random;

                    if (cbSpeed.getSelectedItem().toString().equalsIgnoreCase("Faster")) {
                        Figure square = new Figure("Square", new Point(0, p1), 25, 25);
                        figures.add(square);
                        manager.insertFigure(square);

                    } else if (cbSpeed.getSelectedItem().toString().equalsIgnoreCase("Fast")) {
                        Figure oval = new Figure("Oval", new Point(0, p1), 25, 25);
                        figures.add(oval);
                        manager.insertFigure(oval);

                    } else if (cbSpeed.getSelectedItem().toString().equalsIgnoreCase("Medium")) {
                        Figure arc = new Figure("Arc", new Point(0, p1), 25, 25);
                        figures.add(arc);
                        manager.insertFigure(arc);

                    } else if (cbSpeed.getSelectedItem().toString().equalsIgnoreCase("Slow")) {
                        Figure round = new Figure("Round", new Point(0, p1), 25, 25);
                        figures.add(round);
                        manager.insertFigure(round);
                    }
                }

                //agrega las figuras al Array de Figuras
                for (int i = 0; i < severalTimes; i++) {
                    myFigureList.add(figures.get(i));
                    squareList.add(figures.get(i));
                }

                //thread for controlling the movement of the figures
                Figure mys;
                int aux = 0;
                FigureThread figureThread = null;

                for (Figure figure : squareList) {

                    mys = figure;

                    // Si el random es igual al anterior duerme unos segundos el Thread
                    if (aux == figure.getPointPosition().getY()) {
                        if (mys.getIdentification().equalsIgnoreCase("Square")) {
                            figureThread.sleep(200);
                        }
                    }

                    if (mys.getIdentification().equalsIgnoreCase("Oval")) {
                        figureThread.sleep(300);
                    }

                    if (mys.getIdentification().equalsIgnoreCase("Arc")) {
                        figureThread.sleep(400);
                    }

                    if (mys.getIdentification().equalsIgnoreCase("Round")) {
                        figureThread.sleep(500);
                    }

                    //Inicia los threads
                    if (mys.getIdentification().equalsIgnoreCase("Square")) {
                        figureThread = new FigureThread(mys, 20, figure.getPointPosition().getY());
                        figureThread.start();
                    }

                    if (mys.getIdentification().equalsIgnoreCase("Oval")) {
                        figureThread = new FigureThread(mys, 30, figure.getPointPosition().getY());
                        figureThread.start();
                    }

                    if (mys.getIdentification().equalsIgnoreCase("Arc")) {
                        figureThread = new FigureThread(mys, 40, figure.getPointPosition().getY());
                        figureThread.start();
                    }

                    if (mys.getIdentification().equalsIgnoreCase("Round")) {
                        figureThread = new FigureThread(mys, 60, figure.getPointPosition().getY());
                        figureThread.start();
                    }

                    aux = mys.getPointPosition().getY();
                }

                if (e.getSource() == btnBarrier) {

                }

                if (e.getSource() == btnSimulation) {

                }

            } catch (JDOMException | IOException | InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
