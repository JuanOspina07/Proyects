import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Bienvenida extends JFrame implements ActionListener {
    private JLabel ima1, ima2, ima3, logo, text1, text2, text3, text4, text5, cu1;
    private JButton ordenar, menu;
    public int mesa;
    public Bienvenida() {
        setLayout(null);
        setTitle("Cafeteria Jurak");
        ImageIcon icon = new ImageIcon("imagenes/JK.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 255, 255));
        ImageIcon vaso1 = new ImageIcon("imagenes/cafe5.png");
        ImageIcon cora = new ImageIcon("imagenes/JK.png");
        ImageIcon log = new ImageIcon("imagenes/JK2.png");
        ImageIcon cur = new ImageIcon("imagenes/curvas.png");
        logo = new JLabel(log);
        logo.setBounds(20, 20, 60, 60);
        add(logo);
        ima2 = new JLabel(cora);
        ima2.setBounds(150, 280, 128, 128);
        add(ima2);
        ima3 = new JLabel(cora);
        ima3.setBounds(415, 280, 128, 128);
        add(ima3);
        ima1 = new JLabel(vaso1);
        ima1.setBounds(90, 90, 512, 512);
        add(ima1);
        text1 = new JLabel("Ven y disfruta");
        text1.setBounds(727, 20, 600, 400);
        text1.setFont(new Font("serif", 3, 75));
        text1.setForeground(new Color(161, 130, 98));
        add(text1);
        text2 = new JLabel("de muchas de");
        text2.setBounds(727, 120, 600, 400);
        text2.setFont(new Font("serif", 3, 75));
        text2.setForeground(new Color(161, 130, 98));
        add(text2);
        text3 = new JLabel("nuestras delicias");
        text3.setBounds(727, 220, 700, 400);
        text3.setFont(new Font("serif", 3, 75));
        text3.setForeground(new Color(161, 130, 98));
        add(text3);
        text4 = new JLabel("Gaseosas, jugos, papitas fritas y muchos mas...");
        text4.setBounds(727, 300, 800, 400);
        text4.setFont(new Font("Andale Mono", 3, 20));
        text4.setForeground(new Color(191, 144, 111));
        add(text4);
        text5 = new JLabel(" Cafeteria Jurak");
        text5.setBounds(90, 0, 400, 100);
        text5.setFont(new Font("Serif", 3, 50));
        text5.setForeground(new Color(73, 64, 75));
        add(text5);
        ordenar = new JButton("Mesas");
        ordenar.setBackground(new Color(153, 93, 45));
        ordenar.setFont(new Font("serif", 3, 30));
        ordenar.setForeground(new Color(255, 255, 255));
        ordenar.setBounds(380, 600, 150, 50);
        ordenar.setBorder(null);
        add(ordenar);
        ordenar.addActionListener(this);
        menu = new JButton("Menu");
        menu.setBackground(new Color(153, 93, 45));
        menu.setFont(new Font("serif", 3, 30));
        menu.setForeground(new Color(255, 255, 255));
        menu.setBounds(830, 600, 150, 50);
        menu.setBorder(null);
        add(menu);
        menu.addActionListener(this);
        cu1 = new JLabel(cur);
        cu1.setBounds(0, 0, 1360, 800);
        add(cu1);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == menu) {
            Menu menu = new Menu();
            menu.setBounds(0, 0, 1360, 800);
            menu.setVisible(true);
            menu.setResizable(false);
            menu.setLocationRelativeTo(null);
            this.setVisible(false);
        }
        if (e.getSource() == ordenar) {
            mesa = Integer.parseInt(JOptionPane.showInputDialog(null, "¿Cuantas mesas deseas?", "Mesas", JOptionPane.PLAIN_MESSAGE));
            Mesas mesas = new Mesas(mesa);
            mesas.setBounds(0, 0, 1360, 800);
            mesas.setVisible(true);
            mesas.setResizable(false);
            mesas.setLocationRelativeTo(null);
            this.setVisible(false);
        }
    }
    public static void main(String[] args) {
        Bienvenida bienvenida = new Bienvenida();
        bienvenida.setVisible(true);
        bienvenida.setBounds(0, 0, 1360, 800);
        bienvenida.setResizable(false);
        bienvenida.setLocationRelativeTo(null);
    }
}