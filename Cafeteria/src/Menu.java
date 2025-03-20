import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Menu extends JFrame implements ActionListener {
    private JLabel menu1;
    private JButton devolver;
    public Menu(){
        setLayout(null);
        setTitle("Menu Cafeteria Jurak");
        ImageIcon icon = new ImageIcon("imagenes/JK.png");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setIconImage(icon.getImage());
        ImageIcon men=new ImageIcon("imagenes/menuu.png");
        ImageIcon dev=new ImageIcon("imagenes/devolver.png");
        devolver=new JButton(dev);
        devolver.setBounds(10,75,64,64);
        devolver.setBackground(new Color(255,255,255));
        devolver.setBorder(null);
        add(devolver);
        devolver.addActionListener(this);
        menu1=new JLabel(men);
        menu1.setBounds(0,0,1360,745);
        add(menu1);
    }
    public void actionPerformed(ActionEvent e){
      if(e.getSource()==devolver){
          Bienvenida bienvenida=new Bienvenida();
          bienvenida.setVisible(true);
          bienvenida.setBounds(0,0,1360,800);
          bienvenida.setResizable(false);
          bienvenida.setLocationRelativeTo(null);
          this.setVisible(false);
      }
    }
    public static void main(String[]args){
        Menu menu=new Menu();
        menu.setBounds(0,0,1360,800);
        menu.setVisible(true);
        menu.setResizable(false);
        menu.setLocationRelativeTo(null);
    }
}