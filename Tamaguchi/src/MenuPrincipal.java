import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class MenuPrincipal extends JFrame implements ActionListener {
    private JButton nuevaPartidaButton, cargarPartidaButton;
    private JLabel fondo,rengo,nu,car;

    public MenuPrincipal() {
        ImageIcon reng = new ImageIcon("imagenes/fondo4.png");
        ImageIcon ne = new ImageIcon("imagenes/agg.png");
        ImageIcon ca = new ImageIcon("imagenes/ca.png");
        ImageIcon rengoku = new ImageIcon("imagenes/rengo7.png");
        setTitle("Menu Principal");
        setSize(1360, 750);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(250, 219, 178));
        rengo=new JLabel(rengoku);
        rengo.setBounds(550,280,300,250);
        rengo.setVisible(true);
        add(rengo);
        nu= new JLabel("Nueva Partida");
        nu.setBounds(640, 175, 200, 30);
        nu.setForeground(new Color(0,0,0));
        nu.setFont(new Font("Fira Code Retina", Font.BOLD, 20));
        nu.setBackground(new Color(230, 218, 218));
        add(nu);

        car = new JLabel("Cargar Partida");
        car.setBounds(640, 235, 200, 30);
        car.setForeground(new Color(0,0,0));
        car.setFont(new Font("Fira Code Retina", Font.BOLD, 20));
        car.setBackground(new Color(230, 218, 218));
        add(car);

        nuevaPartidaButton = new JButton(ne);
        nuevaPartidaButton.setBorder(null);
        nuevaPartidaButton.setBackground(new Color(230, 218, 218));
        nuevaPartidaButton.setBounds(590,170,40,40);
        nuevaPartidaButton.addActionListener(this);
        add(nuevaPartidaButton);

        cargarPartidaButton = new JButton(ca);
        cargarPartidaButton.setBorder(null);
        cargarPartidaButton.setBackground(new Color(230, 218, 218));
        cargarPartidaButton.setBounds(590,230,40,40);
        cargarPartidaButton.addActionListener(this);
        add(cargarPartidaButton);

        getContentPane().setBackground(new Color(250, 219, 178));
        fondo=new JLabel(reng);
        fondo.setBounds(0,0,1360,750);
        add(fondo);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nuevaPartidaButton) {
            // Crear una nueva partida
            Mascota pet = new Mascota(); // Asumiendo que tienes un constructor predeterminado en VirtualPet
            String filename = JOptionPane.showInputDialog(this, "Ingrese el nombre del archivo para guardar la nueva partida:");
            if (filename != null && !filename.trim().isEmpty()) {
                try {
                    Guardado.guardarJuego(pet, filename.trim() + ".bin");
                    MascotaVirtual game = new MascotaVirtual(pet, filename.trim() + ".bin");
                    game.setVisible(true);
                    this.dispose();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error al guardar la nueva partida.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nombre de archivo no válido.");
            }
        } else if (e.getSource() == cargarPartidaButton) {
            // Cargar una partida existente
            String filename = JOptionPane.showInputDialog(this, "Ingrese el nombre del archivo para cargar:");
            if (filename != null && !filename.trim().isEmpty()) {
                try {
                    Mascota pet = Guardado.cargarJuego(filename.trim() + ".bin");
                    MascotaVirtual game = new MascotaVirtual(pet, filename.trim() + ".bin");
                    game.setVisible(true);
                    this.dispose();
                } catch (IOException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(this, "Error al cargar la partida.");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Nombre de archivo no válido.");
            }
        }
    }

    public static void main(String[] args) {
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
    }
}
