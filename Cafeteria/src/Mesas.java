import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Mesas extends JFrame implements ActionListener {
    private JMenuBar menubar;
    private JButton estadistica, devolver;
    private Map<String, JButton> m;
    private Map<String, Pedido> pedidosAbiertos = new HashMap<>();
    private Map<String, Integer> ventasPorMesa = new HashMap<>();
    private Map<String, Integer> cantidadVendidaPorProducto = new HashMap<>();
    private int totalVentas;
    public Mesas(int mesa) {
        super("Sala de la Cafetería Jurak");
        ImageIcon icon = new ImageIcon("imagenes/JK.png");
        setIconImage(icon.getImage());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        menubar = new JMenuBar();
        menubar.setBackground(new Color(153, 93, 45));
        setJMenuBar(menubar);
        setLayout(new BorderLayout());
        ImageIcon dev = new ImageIcon("imagenes/devolver.png");
        devolver = new JButton(dev);
        devolver.setBounds(50, 600, 32, 32);
        devolver.setBackground(new Color(153, 93, 45));
        devolver.addActionListener(this);
        devolver.setBorder(null);
        menubar.add(devolver);
        estadistica = new JButton("Estadisticas De Las Ventas");
        estadistica.setBorder(null);
        estadistica.setFont(new Font("Serif", 3, 20));
        estadistica.setBackground(new Color(153, 93, 45));
        estadistica.setForeground(new Color(255, 255, 255));
        estadistica.addActionListener(this);
        menubar.add(estadistica);
        JPanel n = new JPanel();
        n.setBackground(new Color(255, 255, 255));
        n.setLayout(new GridLayout(5, 5));
        m = new HashMap<>();
        for (int i = 0; i < mesa; i++) {
            ImageIcon mesita = new ImageIcon("imagenes/cafeteria2.png");
            JButton mesaButton = new JButton(mesita);
            mesaButton.setBackground(new Color(255, 255, 255));
            mesaButton.setBorder(null);
            mesaButton.addActionListener(this);
            mesaButton.setToolTipText("Mesa " + (i + 1)); // Establecer el texto completo como tooltip
            m.put("Mesa " + (i + 1), mesaButton);
            n.add(mesaButton);
        }
        add(n, BorderLayout.CENTER);
    }
    public Map<String, Integer> getVentasPorMesa() {
        return ventasPorMesa;
    }
    public void mostrarEstadisticas(int totalVentas, int promedio) {
        Map.Entry<String, Integer> productoMasVendido = null;
        for (Map.Entry<String, Integer> entry : cantidadVendidaPorProducto.entrySet()){
            if (productoMasVendido == null || entry.getValue() > productoMasVendido.getValue()) {
                productoMasVendido= entry;
            }
        }
        Map<String, Integer> ventasOrdenadas = new TreeMap<>(ventasPorMesa);//Ordenar las mesas a la hora de mostrar las estadisticas
        StringBuilder estadisticas = new StringBuilder();
        for (Map.Entry<String, Integer> entry : ventasOrdenadas.entrySet()) {
            estadisticas.append(entry.getKey()+": $"+entry.getValue()+"\n");
        }
        estadisticas.append("Total de ventas de todas las mesas: $"+totalVentas+"\n");
        estadisticas.append("Promedio de ventas por mesa: $"+promedio+"\n");
        estadisticas.append("Producto más vendido: "+productoMasVendido+"\n");
        JOptionPane.showMessageDialog(this, estadisticas.toString(), "Estadísticas de Ventas", JOptionPane.INFORMATION_MESSAGE);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == estadistica) {
            totalVentas = 0;
            for (int venta : ventasPorMesa.values()) {
                totalVentas += venta;
            }
            int promedio = totalVentas / m.size();
            mostrarEstadisticas(totalVentas, promedio);
        }
        if (e.getSource() == devolver) {
            Bienvenida bienvenida = new Bienvenida();
            bienvenida.setVisible(true);
            bienvenida.setBounds(0, 0, 1360, 800);
            bienvenida.setResizable(false);
            bienvenida.setLocationRelativeTo(null);
            this.setVisible(false);
        }
        for (String mesa : m.keySet()) {//recorre cada clave
            if (e.getSource() == m.get(mesa)) {
                if (!pedidosAbiertos.containsKey(mesa)) {//verifica si el mapa no contiene la clave mesa
                    Pedido pedido = new Pedido(mesa, this);//no hay un pedido abierto para esta mesa y se crea uno nuevo.
                    pedidosAbiertos.put(mesa, pedido);//Se agrega el pedido recién creado al mapa utilizando el nombre de la mesa como clave.
                }
                Pedido pedido = pedidosAbiertos.get(mesa);
                pedido.setBounds(0, 0, 1360, 800);
                pedido.setVisible(true);
                pedido.setResizable(false);
                pedido.setLocationRelativeTo(null);
            }
        }
    }
    public Map<String, Integer> CantidadVendidaPorProducto() {
        return cantidadVendidaPorProducto;
    }
    public static void main(String[] args) {
        Bienvenida bienvenida = new Bienvenida();
        int mesa = bienvenida.mesa;
        Mesas mesas = new Mesas(mesa);
        mesas.setBounds(0, 0, 1535, 840);
        mesas.setVisible(true);
        mesas.setResizable(false);
        mesas.setLocationRelativeTo(null);
    }
}