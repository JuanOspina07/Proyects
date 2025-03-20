import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class AñadirLibrosEmpleado extends JFrame implements ActionListener {

    private JLabel ima,titulo,logo,espacio,categoria,lb,lb2;
    private JButton perfil,buscar,eliminar;
    private JMenuBar menuBar;
    private JComboBox<String> categoriaComboBox,categoriaComboBox2;
    private JTextField libroTextField,lib2;
    private JButton añadirLibroButton,leliminar;
    private Map<Integer, String> Categorias;
    private Map<String, String> librosPorCategoria;
    Map<String, Boolean> librosPrestados = new HashMap<>();
    Map<String, String> prestamos = new HashMap<>();
    Map<String, Integer> personasEnMora = new HashMap<>();
    private static Map<String, String> usuarios;

    public AñadirLibrosEmpleado(Map<String, String> librosPorCategoria) {
        this.Categorias = cargarCategorias();
        this.librosPorCategoria = librosPorCategoria;


        ImageIcon icon = new ImageIcon("imagenes/corona.png");
        setIconImage(icon.getImage());
        setTitle("Añadir y Eliminar Libro");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(238, 205, 136));
        Font font1 = new Font("Fira Code Retina", Font.BOLD, 40);
        Font font2 = new Font("Fira Code Retina", Font.BOLD, 20);
        Font font3 = new Font("Fira Code Retina", Font.BOLD, 50);
        Font font4 = new Font("Fira Code Retina", Font.BOLD, 70);
        ImageIcon coronita = new ImageIcon("imagenes/corona1.png");
        ImageIcon perf = new ImageIcon("imagenes/perfil.png");
        ImageIcon log = new ImageIcon("imagenes/libro.png");

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
        titulo =new JLabel("Añadir/Eliminar Libro");
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
        categoriaComboBox2 = new JComboBox<>();
        for (String categoria : Categorias.values()) {
            categoriaComboBox2.addItem(categoria);
        }
        categoriaComboBox2.setFont(font2);
        categoriaComboBox2.setBounds(600, 200, 300, 40);
        add(categoriaComboBox2);
        lb = new JLabel("Libro:");
        lb.setBounds(600, 250, 350, 70);
        lb.setFont(font1);
        lb.setForeground(new Color(170, 99, 56));
        add(lb);

        libroTextField = new JTextField();
        libroTextField.setBounds(600, 320, 300, 40);
        libroTextField.setFont(font1);
        libroTextField.setForeground(new Color(170, 99, 56));
        libroTextField.setBackground(new Color(255, 255, 255));
        libroTextField.setBorder(null);
        add(libroTextField);

        añadirLibroButton = new JButton("Añadir");
        añadirLibroButton.setBounds(600, 400, 300, 70);
        añadirLibroButton.setFont(font1);
        añadirLibroButton.setForeground(new Color(255, 255, 255));
        añadirLibroButton.setBackground(new Color(170, 99, 56));
        añadirLibroButton.setBorder(null);
        añadirLibroButton.addActionListener(this);
        add(añadirLibroButton);
        categoria = new JLabel("Categoria:");
        categoria.setBounds(1000, 120, 350, 70);
        categoria.setFont(font1);
        categoria.setForeground(new Color(170, 99, 56));
        add(categoria);

        categoriaComboBox = new JComboBox<>();
        for (String categoria : Categorias.values()) {
            categoriaComboBox.addItem(categoria);
        }
        categoriaComboBox.setFont(font2);
        categoriaComboBox.setBounds(1000, 200, 300, 40);
        add(categoriaComboBox);
        lb2 = new JLabel("Libro:");
        lb2.setBounds(1000, 250, 350, 70);
        lb2.setFont(font1);
        lb2.setForeground(new Color(170, 99, 56));
        add(lb2);

        lib2 = new JTextField();
        lib2.setBounds(1000, 320, 300, 40);
        lib2.setFont(font1);
        lib2.setForeground(new Color(170, 99, 56));
        lib2.setBackground(new Color(255, 255, 255));
        lib2.setBorder(null);
        add(lib2);
        eliminar = new JButton("Eliminar");
        eliminar.setBounds(1000, 400, 300, 70);
        eliminar.setFont(font1);
        eliminar.setForeground(new Color(255, 255, 255));
        eliminar.setBackground(new Color(170, 99, 56));
        eliminar.setBorder(null);
        eliminar.addActionListener(this);
        add(eliminar);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                guardarLibros();
                dispose();
            }
        });

        cargarLibrosPorCategoria();
        this.setBounds(0,0,1360,800);
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
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
        if (e.getSource() == añadirLibroButton) {
            String categoriaSeleccionada = (String) categoriaComboBox2.getSelectedItem();
            String libro = libroTextField.getText().trim();

            if (libro.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre de libro.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Añadir libro a la categoría
                librosPorCategoria.put(libro, categoriaSeleccionada);
                guardarLibros();
                JOptionPane.showMessageDialog(this, "Libro añadido a la categoría " + categoriaSeleccionada + " correctamente.");
            }
        }
        else if (e.getSource() == leliminar) {
            String categoriaSeleccionada = (String) categoriaComboBox.getSelectedItem();
            String libro = libroTextField.getText().trim();

            if (libro.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un nombre de libro.", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!librosPorCategoria.containsKey(libro)) {
                JOptionPane.showMessageDialog(this, "El libro no existe en la categoría.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                // Eliminar libro de la categoría
                librosPorCategoria.remove(libro);
                guardarLibros();
                JOptionPane.showMessageDialog(this, "Libro eliminado de la categoría " + categoriaSeleccionada + " correctamente.");
            }
        }
    }

    private Map<Integer, String> cargarCategorias() {
        Map<Integer, String> categorias = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("categorias.bin"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    int id = Integer.parseInt(parts[0]);
                    categorias.put(id, parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    private void guardarLibros() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("librosPorCategoria.bin"))) {
            oos.writeObject(librosPorCategoria);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static Map<String, String> cargarLibrosPorCategoria() {
        Map<String, String> librosPorCategoria = new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("librosPorCategoria.bin"))) {
            librosPorCategoria = (Map<String, String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return librosPorCategoria;
    }


    public static void main(String[] args) {
        Map<String, Boolean> librosPrestados = new HashMap<>();
        Map<String, String> prestamos = new HashMap<>();
        Map<String, String> usuarios = new HashMap<>();
        Map<String, String> librosPorCategoria = cargarLibrosPorCategoria();
        new AñadirLibrosEmpleado(librosPorCategoria);
    }

}
