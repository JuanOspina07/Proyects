import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class MascotaVirtual extends JFrame implements ActionListener {
    private Mascota pet;
    private String saveFilename;
    private JLabel hambreLabel,muerteLabel, entrenarLabel, suciedadLabel, energiaLabel, nivelLabel, estadoLabel, fondo, rengo, rengo2, rengo3, rengo4, rengo5, subir, hambreBajaLabel, entrenoBajoLabel, suciedadAltaLabel, energiaBajaLabel;
    private JButton Comer,Entrenar,Bañar,Dormir;
    private int previousNivel;
    private Timer autoSaveTimer;


    public MascotaVirtual(Mascota pet, String saveFilename) {
        this.pet = pet;
        this.saveFilename = saveFilename;
        setTitle("Virtual Pet Game");
        setSize(1360, 750);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(250, 219, 178));
        previousNivel = pet.getNivel();
        ImageIcon reng = new ImageIcon("imagenes/fondo4.png");
        ImageIcon rengoku = new ImageIcon("imagenes/rengoku2.png");
        ImageIcon comer = new ImageIcon("imagenes/ca2.png");
        ImageIcon BañarRengoku = new ImageIcon("imagenes/ren4.png");
        ImageIcon bañar = new ImageIcon("imagenes/ducha.png");
        ImageIcon comerRengoku = new ImageIcon("imagenes/ren2.png");
        ImageIcon EntrenarRengoku = new ImageIcon("imagenes/ren3.png");
        ImageIcon espa = new ImageIcon("imagenes/espadas.png");
        ImageIcon dormir = new ImageIcon("imagenes/luna.png");
        ImageIcon DormirRengoku = new ImageIcon("imagenes/ren5.png");
        ImageIcon subirnivel = new ImageIcon("imagenes/subirn.png");
        ImageIcon hambreBaja = new ImageIcon("imagenes/ham.png");
        ImageIcon entrenoBajo = new ImageIcon("imagenes/end.png");
        ImageIcon suciedadAlta = new ImageIcon("imagenes/suc.png");
        ImageIcon energiaBaja = new ImageIcon("imagenes/dom.png");
        ImageIcon muerte = new ImageIcon("imagenes/rengokuuu.gif");



        rengo=new JLabel(rengoku);
        rengo.setBounds(550,230,300,300);
        rengo.setVisible(true);
        add(rengo);
        rengo2=new JLabel(comerRengoku);
        rengo2.setBounds(550,230,300,300);
        rengo2.setVisible(false);
        add(rengo2);
        rengo3=new JLabel(EntrenarRengoku);
        rengo3.setBounds(550,230,300,300);
        rengo3.setVisible(false);
        add(rengo3);
        rengo4=new JLabel(BañarRengoku);
        rengo4.setBounds(550,230,300,300);
        rengo4.setVisible(false);
        add(rengo4);
        rengo5=new JLabel(DormirRengoku);
        rengo5.setBounds(550,230,300,300);
        rengo5.setVisible(false);
        add(rengo5);
        muerteLabel = new JLabel(muerte);
        muerteLabel.setBounds(550, 200, 300, 300);
        muerteLabel.setVisible(false);
        add(muerteLabel);
        subir = new JLabel(subirnivel);
        subir.setBounds(550, 230, 300, 300);
        subir.setVisible(false);
        add(subir);
        hambreBajaLabel = new JLabel(hambreBaja);
        hambreBajaLabel.setBounds(550, 230, 300, 300);
        hambreBajaLabel.setVisible(false);
        add(hambreBajaLabel);

        entrenoBajoLabel = new JLabel(entrenoBajo);
        entrenoBajoLabel.setBounds(550, 230, 300, 300);
        entrenoBajoLabel.setVisible(false);
        add(entrenoBajoLabel);

        suciedadAltaLabel = new JLabel(suciedadAlta);
        suciedadAltaLabel.setBounds(550, 230, 300, 300);
        suciedadAltaLabel.setVisible(false);
        add(suciedadAltaLabel);

        energiaBajaLabel = new JLabel(energiaBaja);
        energiaBajaLabel.setBounds(550, 230, 300, 300);
        energiaBajaLabel.setVisible(false);
        add(energiaBajaLabel);

        hambreLabel = new JLabel( pet.getHambre()+"%" );
        hambreLabel.setBounds(565,200,60,50);
        hambreLabel.setFont(new Font("Fira Code Retina", Font.BOLD, 18));
        hambreLabel.setForeground(new Color(0,0,0));
        add(hambreLabel);
        entrenarLabel = new JLabel( pet.getEntreno()+"%");
        entrenarLabel.setBounds(645,200,60,50);
        entrenarLabel.setFont(new Font("Fira Code Retina", Font.BOLD, 18));
        entrenarLabel.setForeground(new Color(0,0,0));
        add(entrenarLabel);
        suciedadLabel = new JLabel( pet.getSucio()+"%");
        suciedadLabel.setBounds(725,200,60,50);
        suciedadLabel.setFont(new Font("Fira Code Retina", Font.BOLD, 18));
        suciedadLabel.setForeground(new Color(0,0,0));
        add(suciedadLabel);
        energiaLabel = new JLabel(pet.getEnergia()+"%");
        energiaLabel.setBounds(805,200,60,50);
        energiaLabel.setFont(new Font("Fira Code Retina", Font.BOLD, 18));
        energiaLabel.setForeground(new Color(0,0,0));
        add(energiaLabel);
        nivelLabel = new JLabel("Nivel: " + pet.getNivel());
        nivelLabel.setBounds(540,480,100,50);
        nivelLabel.setFont(new Font("Fira Code Retina", Font.BOLD, 18));
        nivelLabel.setForeground(new Color(0,0,0));
        add(nivelLabel);


        estadoLabel = new JLabel((pet.vida() ? "Vivo" : "Muerto"));
        estadoLabel.setBounds(780,480,100,50);
        estadoLabel.setFont(new Font("Fira Code Retina", Font.BOLD, 18));
        estadoLabel.setForeground(new Color(0,0,0));
        add(estadoLabel);

        Comer = new JButton(comer);
        Comer.setBorder(null);
        Comer.setBackground(new Color(230, 218, 218));
        Comer.setBounds(560,170,40,40);
        add(Comer);
        Comer.addActionListener(this);
        Entrenar = new JButton(espa);
        Entrenar.setBorder(null);
        Entrenar.setBackground(new Color(230,218,218));
        Entrenar.setBounds(640,170,40,40);
        add(Entrenar);
        Entrenar.addActionListener(this);
        Bañar = new JButton(bañar);
        Bañar.setBackground(new Color(230,218,218));
        Bañar.setBorder(null);
        Bañar.setBounds(720,170,40,40);
        add(Bañar);
        Bañar.addActionListener(this);
        Dormir = new JButton(dormir);
        Dormir.setBorder(null);
        Dormir.setBackground(new Color(230,218,218));
        Dormir.setBounds(800,170,40,40);
        add(Dormir);
        Dormir.addActionListener(this);
        fondo=new JLabel(reng);
        fondo.setBounds(0,0,1360,750);
        add(fondo);

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1500);
                    pet.actualizarEstados();
                    SwingUtilities.invokeLater(this::actualizarLabels);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        autoSaveTimer = new Timer(30000, e -> guardarProgreso());
        autoSaveTimer.start();
    }


    private void guardarProgreso() {
        try {
            Guardado.guardarJuego(pet, saveFilename);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el progreso de la partida.");
        }
    }


    private void comerMascota() {
        pet.comer();
        actualizarLabels();
    }

    private void entrenoMascota() {
        pet.entreno();
        actualizarLabels();
    }

    private void bañarMascota() {
        pet.bañar();
        actualizarLabels();
    }

    private void dormirMascota() {
        pet.dormir();
        actualizarLabels();
    }

    private void guardarJuego() {
        String filename = JOptionPane.showInputDialog(this, "Ingrese el nombre del archivo para guardar:");
        if (filename != null && !filename.trim().isEmpty()) {
            try {
                Guardado.guardarJuego(pet, filename.trim() + ".bin");
                JOptionPane.showMessageDialog(this, "Partida guardada exitosamente.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error al guardar la partida.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nombre de archivo no válido.");
        }
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==Comer){
            rengo.setVisible(false);
            rengo2.setVisible(true);
            rengo3.setVisible(false);
            rengo4.setVisible(false);
            rengo5.setVisible(false);
            comerMascota();
        }
        if(e.getSource()==Entrenar){
            rengo.setVisible(false);
            rengo2.setVisible(false);
            rengo3.setVisible(true);
            rengo4.setVisible(false);
            rengo5.setVisible(false);
            entrenoMascota();
        }
        if(e.getSource()==Bañar){
            rengo.setVisible(false);
            rengo2.setVisible(false);
            rengo3.setVisible(false);
            rengo4.setVisible(true);
            rengo5.setVisible(false);
            bañarMascota();
        }
        if(e.getSource()==Dormir){
            rengo.setVisible(false);
            rengo2.setVisible(false);
            rengo3.setVisible(false);
            rengo4.setVisible(false);
            rengo5.setVisible(true);
            dormirMascota();
        }

    }

    private void cargarJuego() {
        String filename = JOptionPane.showInputDialog(this, "Ingrese el nombre del archivo para cargar:");
        if (filename != null && !filename.trim().isEmpty()) {
            try {
                pet = Guardado.cargarJuego(filename.trim() + ".bin");
                actualizarLabels();
                JOptionPane.showMessageDialog(this, "Partida cargada exitosamente.");
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Error al cargar la partida.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Nombre de archivo no válido.");
        }
    }

    private void actualizarLabels() {
        hambreLabel.setText(pet.getHambre()+"%");
        entrenarLabel.setText( pet.getEntreno()+"%");
        suciedadLabel.setText( pet.getSucio()+"%");
        energiaLabel.setText(pet.getEnergia()+"%");
        nivelLabel.setText("Nivel: " + pet.getNivel());
        estadoLabel.setText( (pet.vida() ? "Vivo" : "Muerto"));
        hambreBajaLabel.setVisible(pet.getHambre() > 80);
        entrenoBajoLabel.setVisible(pet.getEntreno() < 20);
        suciedadAltaLabel.setVisible(pet.getSucio() > 80);
        energiaBajaLabel.setVisible(pet.getEnergia() < 20);
        if (!pet.vida()) {
            muerteLabel.setVisible(true);
            rengo.setVisible(false);
            rengo2.setVisible(false);
            rengo3.setVisible(false);
            rengo4.setVisible(false);
            rengo5.setVisible(false);
            subir.setVisible(false);
            hambreBajaLabel.setVisible(false);
            entrenoBajoLabel.setVisible(false);
            suciedadAltaLabel.setVisible(false);
            energiaBajaLabel.setVisible(false);
        } else {
            muerteLabel.setVisible(false);
            if (pet.getNivel() > previousNivel) {
                subir.setVisible(true);
                rengo2.setVisible(false);
                rengo3.setVisible(false);
                rengo4.setVisible(false);
                rengo5.setVisible(false);
                Timer timer3 = new Timer(1000, u -> subir.setVisible(false));
                Timer timer4= new Timer(1500, u -> rengo.setVisible(true));
                timer3.setRepeats(false);
                timer3.start();
                timer4.setRepeats(false);
                timer4.start();
            }
            if (pet.getHambre() > 80) {
                hambreBajaLabel.setVisible(true);
                rengo.setVisible(false);
                rengo2.setVisible(false);
                rengo3.setVisible(false);
                rengo4.setVisible(false);
                rengo5.setVisible(false);
            } else if (pet.getEntreno() < 20) {
                entrenoBajoLabel.setVisible(true);
                rengo.setVisible(false);
                rengo2.setVisible(false);
                rengo3.setVisible(false);
                rengo4.setVisible(false);
                rengo5.setVisible(false);
            } else if (pet.getSucio() > 80) {
                suciedadAltaLabel.setVisible(true);
                rengo.setVisible(false);
                rengo2.setVisible(false);
                rengo3.setVisible(false);
                rengo4.setVisible(false);
                rengo5.setVisible(false);
            } else if (pet.getEnergia() < 20) {
                energiaBajaLabel.setVisible(true);
                rengo.setVisible(false);
                rengo2.setVisible(false);
                rengo3.setVisible(false);
                rengo4.setVisible(false);
                rengo5.setVisible(false);
            } else {
                hambreBajaLabel.setVisible(false);
                entrenoBajoLabel.setVisible(false);
                suciedadAltaLabel.setVisible(false);
                energiaBajaLabel.setVisible(false);
            }
        }
        previousNivel = pet.getNivel();

    }

    public static void main(String[] args) {

        Mascota pet = new Mascota(); // o cargar una partida guardada
        String saveFilename = "partida.bin"; // nombre del archivo para guardar la partida
        MascotaVirtual game = new MascotaVirtual(pet, saveFilename);
        game.setVisible(true);

    }
}
