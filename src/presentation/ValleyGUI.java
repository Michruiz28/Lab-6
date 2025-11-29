package presentation;
import domain.*;
import domain.Valley;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ValleyGUI extends JFrame implements Serializable{
    public static final int SIDE=20;

    public final int SIZE;
    private JButton ticTacButton;
    private JPanel  controlPanel;
    private PhotoValley photo;
    private Valley theValley;
    private JMenu menu;
    private JMenuBar menuBar;
    private JMenuItem menuNuevo;
    private JMenuItem menuAbrir;
    private JMenuItem menuGuardar;
    private JMenuItem menuImportar;
    private JMenuItem menuExportar;
    private JMenuItem menuSalir;

    private void prepareElementsMenu(){
        menuBar = new JMenuBar();
        menu = new JMenu ( "Menu");
        menuNuevo = new JMenuItem("Nuevo");
        menuAbrir = new JMenuItem("Abrir");
        menuGuardar = new JMenuItem("Guardar");
        menuImportar = new JMenuItem("Importar");
        menuExportar = new JMenuItem("Exportar");
        menuSalir = new JMenuItem("Salir");
        menu.add(menuNuevo);
        menu.addSeparator();
        menu.add(menuAbrir);
        menu.add(menuGuardar);
        menu.addSeparator();
        menu.add(menuImportar);
        menu.add(menuExportar);
        menu.addSeparator();
        menu.add(menuSalir);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }
    private ValleyGUI() {
        theValley=new Valley();
        SIZE=theValley.getSize();
        prepareElements();
        prepareActions();
    }
    
    private void prepareElements() {
        setTitle("Schelling Valley");
        prepareElementsMenu();
        photo=new PhotoValley(this);
        ticTacButton=new JButton("Tic-tac");
        setLayout(new BorderLayout());
        add(photo,BorderLayout.CENTER);
        add(ticTacButton,BorderLayout.SOUTH);
        pack();
        setSize(new Dimension(SIDE*SIZE+15,SIDE*SIZE+100)); 
                setLocationRelativeTo(null);
        setResizable(false);
        photo.repaint();
    }

    private void prepareActions(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);       
        ticTacButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e) {
                    ticTacButtonAction();
                }
            });
        prepareActionsMenu();

    }
    private void prepareActionsMenu(){
        menuNuevo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                theValley = new Valley();
                optionNew();
            }
        });
        
        menuAbrir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                optionOpen();
            }
        });

        menuGuardar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                optionSave();
            }
        });

        menuImportar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                optionImport();
            }
        });

        menuExportar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                optionExport();
            }
        });

        menuSalir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                optionExit();
            }
        });
    }

<<<<<<< HEAD
    private void optionOpen(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Abrir Valley");
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try {
                theValley.abrir(selectedFile);
            } catch (ValleyException e){
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void optionExport(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Exportar Valley");
        int result = fileChooser.showSaveDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            try{
                theValley.exportar(selectedFile);
            } catch(ValleyException e ){
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void optionExit(){
        int response = JOptionPane.showConfirmDialog(this, "¿Está seguro que desea salir?", "Confirmar salida", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void optionNew(){
    }
    private void optionImport(){
    }
    private void optionSave(){
=======
    private void optionNew() {
        try {
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "¿Está seguro de crear un nuevo valle?\nSe perderá la simulación actual.",
                        "Nuevo Valle",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirm != JOptionPane.YES_OPTION) {
                    return;
                }

            theValley.nuevo();

            // Actualizar visualización
            photo.repaint();

            JOptionPane.showMessageDialog(
                    this,
                    "Nuevo valle creado exitosamente",
                    "Nuevo Valle",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (ValleyException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Error al crear nuevo valle:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void optionSave(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Valle");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Archivos Valley (*.valley)", "valley"
        ));

        int result = fileChooser.showSaveDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();

                // Asegurar extensión .valley
                if (!file.getName().toLowerCase().endsWith(".valley")) {
                    file = new File(file.getAbsolutePath() + ".valley");
                }

                theValley.guardar(file);

                JOptionPane.showMessageDialog(
                        this,
                        "El valle se ha guardado correctamente.",
                        "Guardado exitoso",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (ValleyException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error al guardar el valle:\n" + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void optionImport(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Importar Valle");
        fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(
                "Archivos de texto (*.txt)", "txt"
        ));

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File file = fileChooser.getSelectedFile();

                // Importar el valle desde el archivo
                Valley.importar(file);

                // Actualizar visualización
                photo.repaint();

                JOptionPane.showMessageDialog(
                        this,
                        "Valle importado exitosamente",
                        "Importación exitosa",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (ValleyException e) {
                JOptionPane.showMessageDialog(
                        this,
                        "Error al importar:\n" + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
>>>>>>> ramaKata
    }

    private void ticTacButtonAction() {
        theValley.ticTac();
        photo.repaint();
    }

    public Valley getTheValley(){
        return theValley;
    }
    
    public static void main(String[] args) {
        ValleyGUI cg=new ValleyGUI();
        cg.setVisible(true);
    }  
}

class PhotoValley extends JPanel{
    private ValleyGUI gui;

    public PhotoValley(ValleyGUI gui) {
        this.gui=gui;
        setBackground(Color.white);
        setPreferredSize(new Dimension(gui.SIDE*gui.SIZE+10, gui.SIDE*gui.SIZE+10));         
    }

    public void paintComponent(Graphics g){
        Valley theValley=gui.getTheValley();
        super.paintComponent(g);
         
        for (int c=0;c<=theValley.getSize();c++){
            g.drawLine(c*gui.SIDE,0,c*gui.SIDE,theValley.getSize()*gui.SIDE);
        }
        for (int f=0;f<=theValley.getSize();f++){
            g.drawLine(0,f*gui.SIDE,theValley.getSize()*gui.SIDE,f*gui.SIDE);
        }       
        for (int f=0;f<theValley.getSize();f++){
            for(int c=0;c<theValley.getSize();c++){
                if (theValley.getUnit(f,c)!=null){
                    g.setColor(theValley.getUnit(f,c).getColor());
                    if (theValley.getUnit(f,c).shape()==Unit.SQUARE){                  
                        g.fillRoundRect(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2,2,2);   
                    }else {
                        g.fillOval(gui.SIDE*c+1,gui.SIDE*f+1,gui.SIDE-2,gui.SIDE-2);
                    }
                    if (theValley.getUnit(f,c).isAnimal()){
                        g.setColor(Color.red);
                        if (((Animal)theValley.getUnit(f,c)).getEnergy()>=50){
                            g.drawString("u",gui.SIDE*c+6,gui.SIDE*f+15);
                        } else {
                            g.drawString("~",gui.SIDE*c+6,gui.SIDE*f+17);
                        }
                    }    
                }
            }
        }
    }
}