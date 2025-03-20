import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AñadirCategoriaEmpleado extends JFrame implements ActionListener {
    private JLabel ima,titulo,logo,espacio,categoria;
    private JButton perfil,buscar,eliminar;
    private JMenuBar menuBar;
    private Map<Integer,String> Categorias;

    private  JTextField lib,lib2;
    Map<String, Boolean> librosPrestados = new HashMap<>();
    Map<String, String> prestamos = new HashMap<>();
    Map<String, Integer> personasEnMora = new HashMap<>();
    private static Map<String, String> usuarios;
    public AñadirCategoriaEmpleado(Map<String, String> usuarios){
        this.Categorias = new HashMap<>();
        this.usuarios=usuarios;
        ImageIcon icon = new ImageIcon("imagenes/corona.png");
        setIconImage(icon.getImage());
        setTitle("Añadir Categoria");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(238, 205, 136));
        Font font1 = new Font("Fira Code Retina", Font.BOLD, 40);
        Font font2 = new Font("Fira Code Retina", Font.BOLD, 20);
        Font font3 = new Font("Fira Code Retina", Font.BOLD, 50);
        Font font4 = new Font("Fira Code Retina", Font.BOLD, 70);
        ImageIcon coronita = new ImageIcon("imagenes/corona1.png");
        ImageIcon perf = new ImageIcon("imagenes/perfil.png");
        ImageIcon log = new ImageIcon("imagenes/Añadir.png");

        menuBar=new JMenuBar();
        menuBar.setBackground(new Color(170, 99, 56));
        menuBar.setBorder(null);
        menuBar.setBounds(0,0,50,400);
        setJMenuBar(menuBar);
        espacio =new JLabel("    ");
        espacio.setForeground(new Color(255,255,255));
        menuBar.add(espacio);
        logo = new JLabel(coronita);
        logo.setForeground(new Color(255,255,255));
        logo.setBorder(null);

        menuBar.add(logo);
        espacio =new JLabel("   ");
        menuBar.add(espacio);
        titulo =new JLabel("Añadir/Eliminar Categoria");
        titulo.setFont(font3);
        titulo.setForeground(new Color(255,255,255));
        menuBar.add(titulo);
        espacio =new JLabel("                                                                                                                          ");
        menuBar.add(espacio);
        perfil = new JButton(perf);
        perfil.setBounds(1260, 20, 80, 80);
        perfil.setBackground(new Color(170, 99, 56));
        perfil.setBorder(null);
        menuBar.add(perfil);
        perfil.addActionListener(this);
        ima=new JLabel(log);
        ima.setBounds(30,50,525,525);
        add(ima);
        categoria = new JLabel("Categoria:");
        categoria.setBounds(600, 120, 350, 70);
        categoria.setFont(font1);
        categoria.setForeground(new Color(170, 99, 56));
        add(categoria);

        lib = new JTextField();
        lib.setBounds(600, 200, 300, 40);
        lib.setFont(font1);
        lib.setForeground(new Color(170, 99, 56));
        lib.setBackground(new Color(255, 255, 255));
        lib.setBorder(null);
        add(lib);
        buscar = new JButton("Añadir");
        buscar.setBounds(600, 300, 300, 70);
        buscar.setFont(font1);
        buscar.setForeground(new Color(255, 255, 255));
        buscar.setBackground(new Color(170, 99, 56));
        buscar.setBorder(null);
        buscar.addActionListener(this);
        add(buscar);
        categoria = new JLabel("Categoria:");
        categoria.setBounds(1000, 120, 350, 70);
        categoria.setFont(font1);
        categoria.setForeground(new Color(170, 99, 56));
        add(categoria);

        lib2 = new JTextField();
        lib2.setBounds(1000, 200, 300, 40);
        lib2.setFont(font1);
        lib2.setForeground(new Color(170, 99, 56));
        lib2.setBackground(new Color(255, 255, 255));
        lib2.setBorder(null);
        add(lib2);
        eliminar = new JButton("Eliminar");
        eliminar.setBounds(1000, 300, 300, 70);
        eliminar.setFont(font1);
        eliminar.setForeground(new Color(255, 255, 255));
        eliminar.setBackground(new Color(170, 99, 56));
        eliminar.setBorder(null);
        eliminar.addActionListener(this);
        add(eliminar);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                guardarCategoria();
                dispose();
            }
        });

        cargarCategoria();
        this.setBounds(0,0,1360,800);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public void actionPerformed(ActionEvent e) {
        // Otras partes de tu código...
        if (e.getSource() == buscar) {
            String nombreCategoria = lib.getText().trim();

            // Verificar que la categoría no esté vacía
            if (nombreCategoria.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese una categoría válida.", "Error", JOptionPane.ERROR_MESSAGE);

            } else if (Categorias.containsValue(nombreCategoria)) {
                JOptionPane.showMessageDialog(this, "Ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int idCategoria = Categorias.size() + 1;
                Categorias.put(idCategoria, nombreCategoria);
                JOptionPane.showMessageDialog(this, "Categoría añadida correctamente.");
                // Guardar las categorías en el archivo
                guardarCategoria();

            }
        } if (e.getSource()==eliminar){
            String nombreca= lib2.getText().trim();

            if (nombreca.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre de Categoria.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!Categorias.containsValue(nombreca)) {
                JOptionPane.showMessageDialog(this, "La Categoria no existe.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Buscar el ID de la categoría a eliminar
                int idCategoria = 0;
                for (Map.Entry<Integer, String> entry : Categorias.entrySet()) {
                    if (entry.getValue().equals(nombreca)) {
                        idCategoria = entry.getKey();
                        break;
                    }
                }

                Categorias.remove(idCategoria); // Eliminar la categoría del mapa
                guardarCategoria(); // Guardar la lista actualizada en el archivo
                JOptionPane.showMessageDialog(this, "Categoria eliminada correctamente.");
                System.out.println("Categoria eliminada: " + nombreca);
            }
        }
        if (e.getSource() == perfil) {
            Font font2 = new Font("Fira Code Retina", Font.BOLD, 20);
            JPopupMenu menu = new JPopupMenu();
            JMenuItem opcion1 = new JMenuItem("Perfil Empleado");
            JMenuItem opcion2 = new JMenuItem("Salir");
            menu.setBackground(new Color(170, 99, 56));
            menu.setBorder(null);
            opcion1.setBorder(null);
            opcion1.setForeground(new Color(255, 255, 255));
            opcion1.setBackground(new Color(170, 99, 56));
            opcion1.setFont(font2);
            opcion2.setBorder(null);
            opcion2.setForeground(new Color(255, 255, 255));
            opcion2.setBackground(new Color(170, 99, 56));
            opcion2.setFont(font2);
            opcion2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    setVisible(false);
                    CuentaEmpleado m = new CuentaEmpleado(librosPrestados, prestamos, usuarios, personasEnMora);
                    m.setVisible(true);
                    m.setBounds(0, 0, 1360, 800);
                    m.setResizable(false);
                    m.setLocationRelativeTo(null);
                }
            });
            menu.add(opcion1);
            menu.add(opcion2);
            menu.show(perfil, 0, perfil.getHeight());
        }
    }
    private void guardarCategoria() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("categorias.bin"))) {
            for (Map.Entry<Integer, String> entry : Categorias.entrySet()) {
                writer.println(entry.getKey() + ":" + entry.getValue());
            }
            writer.flush(); // Forzar la escritura de los datos
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarCategoria() {
        File file = new File("categorias.bin");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    int id = Integer.parseInt(parts[0]);
                    Categorias.put(id, parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[]args){
        Map<String, Boolean> librosPrestados = new HashMap<>();
        Map<String, String> prestamos = new HashMap<>();
        Map<String, String> usuarios = new HashMap<>();
        new AñadirCategoriaEmpleado(usuarios);
    }
}

