import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
public class Pedido extends JFrame implements ActionListener {
    private JLabel mesaLabel, logo, pareja, pro, can;
    private JComboBox<String> productoCombo;
    private JButton agregar,eliminar,cobrar;
    private JTextArea pedido;
    private String[] productos = {"Gaseosa", "Jugos Naturales", "Cafe", "Papitas Fritas", "Hamburguesa", "Pizza", "Cupcakes", "Helados", "Tortas"};
    private JTextField cantidades;
    private Map<String, Integer> preciosProductos;
    private Map<String, Map<String, Integer>> pedidosPorMesa;
    private Map<String, Integer> totalCobradoPorMesa;
    private Mesas mesas;
    public Pedido(String mesa, Mesas mesas) {
        setLayout(null);
        setTitle("Gestión de Pedido - " + mesa);
        ImageIcon icon = new ImageIcon("imagenes/JK.png");
        setIconImage(icon.getImage());
        getContentPane().setBackground(new Color(255, 255, 255));
        this.mesas = mesas; // Guarda la referencia a la instancia de Mesas
        ImageIcon logui = new ImageIcon("imagenes/JK2.png");
        ImageIcon pare = new ImageIcon("imagenes/pare2.png");
        mesaLabel = new JLabel(mesa);
        mesaLabel.setBounds(100, 15, 400, 60);
        mesaLabel.setFont(new Font("Serif", Font.BOLD, 60));
        mesaLabel.setBackground(new Color(255, 255, 255));
        mesaLabel.setForeground(new Color(73, 64, 75));
        add(mesaLabel);
        logo = new JLabel(logui);
        logo.setBounds(20, 20, 60, 60);
        add(logo);
        pareja = new JLabel(pare);
        pareja.setBounds(150, 100, 500, 640);
        add(pareja);
        pro = new JLabel("Productos");
        pro.setBounds(800, 40, 200, 60);
        pro.setFont(new Font("serif", 3, 40));
        pro.setForeground(new Color(161, 130, 98));
        add(pro);
        can = new JLabel("Cantidad");
        can.setBounds(1100, 40, 200, 60);
        can.setFont(new Font("serif", 3, 40));
        can.setForeground(new Color(161, 130, 98));
        add(can);
        productoCombo = new JComboBox<>(productos);
        productoCombo.setBounds(800, 110, 200, 100);
        productoCombo.setBackground(new Color(191, 144, 111));
        productoCombo.setFont(new Font("Serif", 3, 30));
        productoCombo.setForeground(new Color(255, 255, 255));
        add(productoCombo);
        cantidades = new JTextField();
        cantidades.setBounds(1100, 110, 200, 100);
        cantidades.setBackground(new Color(191, 144, 111));
        cantidades.setFont(new Font("Serif", 3, 40));
        cantidades.setForeground(new Color(255, 255, 255));
        add(cantidades);
        agregar = new JButton("Agregar");
        agregar.setBounds(800, 260, 200, 100);
        agregar.setBackground(new Color(191, 144, 111));
        agregar.setFont(new Font("Serif", 3, 40));
        agregar.setForeground(new Color(255, 255, 255));
        agregar.addActionListener(this);
        add(agregar);
        eliminar = new JButton("Eliminar");
        eliminar.setBounds(1100, 260, 200, 100);
        eliminar.setBackground(new Color(191, 144, 111));
        eliminar.setFont(new Font("Serif", 3, 40));
        eliminar.setForeground(new Color(255, 255, 255));
        eliminar.addActionListener(this);
        add(eliminar);
        cobrar = new JButton("Cobrar");
        cobrar.setBounds(950, 620, 200, 100);
        cobrar.setBackground(new Color(153, 93, 45));
        cobrar.setFont(new Font("Serif", 3, 40));
        cobrar.setForeground(new Color(255, 255, 255));
        cobrar.addActionListener(this);
        add(cobrar);
        pedido = new JTextArea();
        pedido.setFont(new Font("Serif", 3, 20));
        pedido.setBackground(new Color(191, 144, 111));
        pedido.setForeground(new Color(255, 255, 255));
        pedido.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(pedido);
        scrollPane.setBounds(950, 410, 200, 160);
        add(scrollPane);
        preciosProductos = new HashMap<>();
        preciosProductos.put("Gaseosa", 4000);
        preciosProductos.put("Jugos Naturales", 7000);
        preciosProductos.put("Cafe", 7000);
        preciosProductos.put("Papitas Fritas", 5000);
        preciosProductos.put("Hamburguesa", 25000);
        preciosProductos.put("Pizza", 35000);
        preciosProductos.put("Cupcakes", 5000);
        preciosProductos.put("Helados", 3000);
        preciosProductos.put("Tortas", 6000);
        pedidosPorMesa = new HashMap<>();
        totalCobradoPorMesa = new HashMap<>();
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == agregar) {
            String producto = (String) productoCombo.getSelectedItem();
            int cantidad = Integer.parseInt(cantidades.getText());
            Map<String, Integer> pedidoActual = pedidosPorMesa.getOrDefault(mesaLabel.getText(), new HashMap<>()); //Obtenemos el mapa de pedidos para esa mesa o creamos uno nuevo
            int cantidadExistente = pedidoActual.getOrDefault(producto, 0); // Actualizamos la cantidad de productos en el pedido actual
            pedidoActual.put(producto, cantidad + cantidadExistente);
            pedidosPorMesa.put(mesaLabel.getText(), pedidoActual);// Actualizamos el mapa de pedidos por mesa
            actualizarPedido(pedidoActual);// Actualizamos el pedido en el JTextArea
        } else if (e.getSource() == eliminar) {
            pedidosPorMesa.remove(mesaLabel.getText());
            pedido.setText("");
            JOptionPane.showMessageDialog(null,"Pedido eliminado correctamente de la "+mesaLabel.getText());
        } else if (e.getSource() == cobrar) {
            int totalPedido = 0;
            Map<String, Integer> pedidoActual = pedidosPorMesa.get(mesaLabel.getText());
            if (pedidoActual != null) {
                for (Map.Entry<String, Integer> entry : pedidoActual.entrySet()) {
                    String producto = entry.getKey();
                    Integer cantidad = entry.getValue();
                    Integer precio = preciosProductos.getOrDefault(producto, 0);//Se obtiene el precio del producto del mapa
                    totalPedido += precio * cantidad;
                    mesas.CantidadVendidaPorProducto().put(producto, mesas.CantidadVendidaPorProducto().getOrDefault(producto, 0) + cantidad);//Se utiliza getOrDefault para obtener la cantidad vendida actual del producto
                }
                totalCobradoPorMesa.put(mesaLabel.getText(), totalCobradoPorMesa.getOrDefault(mesaLabel.getText(), 0) + totalPedido);// Actualiza el total cobrado por mesa
                Map<String, Integer> ventasPorMesa = mesas.getVentasPorMesa();// Obtener el mapa de ventas por mesa de Mesas y lo actualiza
                ventasPorMesa.put(mesaLabel.getText(), totalCobradoPorMesa.get(mesaLabel.getText())); // Actualiza el valor en ventasPorMesa
                JOptionPane.showMessageDialog(this, "Pedido cobrado. Total a cobrar: $" + totalPedido); // Mostrar el total a cobrar y reiniciar el pedido
                pedido.setText("");
                pedidosPorMesa.remove(mesaLabel.getText());
            }
        }
    }
    private void actualizarPedido(Map<String, Integer> pedidoActual) {
        pedido.setText("");
        for (Map.Entry<String, Integer> entry : pedidoActual.entrySet()) {
            String producto = entry.getKey();
            int cantidad = entry.getValue();
            pedido.append(producto + " x" + cantidad + "\n");
        }
    }
    public static void main(String[] args) {
        Pedido pedido = new Pedido("Mesa ", new Mesas(0));
        pedido.setBounds(0, 0, 1360, 800);
        pedido.setVisible(true);
        pedido.setResizable(false);
        pedido.setLocationRelativeTo(null);
    }
}