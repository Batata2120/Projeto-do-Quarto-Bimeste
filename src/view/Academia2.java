package view;
import javax.swing.*;
import java.awt.*;

public class Academia2 extends JFrame {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JButton clientesButton;
    private JButton funcionariosButton;
    private JButton aparelhosButton;
    private JButton parceriasButton;
    private JButton sobreNosButton;

    public Academia2() {
        setTitle("Academia");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setLayout(new GridBagLayout());

        titleLabel = new JLabel("Academia");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.insets = new Insets(10, 10, 20, 10);
        titleConstraints.gridwidth = 4;
        titleConstraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, titleConstraints);

        clientesButton = new JButton("Clientes");
        clientesButton.addActionListener(e -> {
           
        });
        clientesButton.setPreferredSize(new Dimension(200, 100));
        GridBagConstraints buttonConstraintsClientes = new GridBagConstraints();
        buttonConstraintsClientes.insets = new Insets(10, 10, 10, 10);
        buttonConstraintsClientes.fill = GridBagConstraints.BOTH;
        buttonConstraintsClientes.gridx = 0;
        buttonConstraintsClientes.gridy = 1;
        buttonConstraintsClientes.gridwidth = 2;
        mainPanel.add(clientesButton, buttonConstraintsClientes);

        funcionariosButton = new JButton("Funcionários");
        funcionariosButton.addActionListener(e -> {
           
        });
        funcionariosButton.setPreferredSize(new Dimension(200, 100));
        GridBagConstraints buttonConstraintsFuncionarios = new GridBagConstraints();
        buttonConstraintsFuncionarios.insets = new Insets(10, 10, 10, 10);
        buttonConstraintsFuncionarios.fill = GridBagConstraints.BOTH;
        buttonConstraintsFuncionarios.gridx = 2;
        buttonConstraintsFuncionarios.gridy = 1;
        buttonConstraintsFuncionarios.gridwidth = 2;
        mainPanel.add(funcionariosButton, buttonConstraintsFuncionarios);

        aparelhosButton = new JButton("Aparelhos");
        aparelhosButton.addActionListener(e -> {
           
        });
        aparelhosButton.setPreferredSize(new Dimension(200, 100));
        GridBagConstraints buttonConstraintsAparelhos = new GridBagConstraints();
        buttonConstraintsAparelhos.insets = new Insets(10, 10, 10, 10);
        buttonConstraintsAparelhos.fill = GridBagConstraints.BOTH;
        buttonConstraintsAparelhos.gridx = 0;
        buttonConstraintsAparelhos.gridy = 2;
        buttonConstraintsAparelhos.gridwidth = 2;
        mainPanel.add(aparelhosButton, buttonConstraintsAparelhos);

        parceriasButton = new JButton("Parcerias");
        parceriasButton.addActionListener(e -> {
           
        });
        parceriasButton.setPreferredSize(new Dimension(200, 100));
        GridBagConstraints buttonConstraintsParcerias = new GridBagConstraints();
        buttonConstraintsParcerias.insets = new Insets(10, 10, 10, 10);
        buttonConstraintsParcerias.fill = GridBagConstraints.BOTH;
        buttonConstraintsParcerias.gridx = 2;
        buttonConstraintsParcerias.gridy = 2;
        buttonConstraintsParcerias.gridwidth = 2;
        mainPanel.add(parceriasButton, buttonConstraintsParcerias);

        sobreNosButton = new JButton("Sobre Nós");
        GridBagConstraints buttonConstraintsSobreNos = new GridBagConstraints();
        buttonConstraintsSobreNos.insets = new Insets(10, 10, 10,10);
        buttonConstraintsSobreNos.fill = GridBagConstraints.BOTH;
        buttonConstraintsSobreNos.gridx = 0;
        buttonConstraintsSobreNos.gridy = 3;
        buttonConstraintsSobreNos.gridwidth = 4;
        mainPanel.add(sobreNosButton, buttonConstraintsSobreNos);

        add(mainPanel);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Academia2 academia = new Academia2();
            academia.setVisible(true);
        });
    }

        
    }