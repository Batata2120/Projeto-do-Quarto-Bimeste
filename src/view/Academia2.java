package view;

import dao.*;
import objetos.*;
import principal.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Academia2 extends JFrame {

    private JPanel mainPanel;
    private JLabel titleLabel;
    private JButton clientesButton;
    private JButton funcionariosButton;
    private JButton aparelhosButton;
    private JButton parceriasButton;
    private JButton sobreNosButton;

    public Academia2() {
        setTitle("AcademiaGE");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        mainPanel.setBackground(SystemColor.activeCaption);
        mainPanel.setLayout(new GridBagLayout());

        titleLabel = new JLabel("Academia");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.insets = new Insets(10, 10, 20, 10);
        titleConstraints.gridwidth = 4;
        titleConstraints.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, titleConstraints);

        clientesButton = new JButton("Clientes");
        clientesButton.setForeground(new Color(0, 0, 0));
        clientesButton.setBackground(new Color(192, 192, 192));
        clientesButton.addActionListener(e-> {
        	
				cadastro telacadastro = new cadastro();
				telacadastro.setVisible(true);
				
			
        });
		

        clientesButton.setBackground(SystemColor.controlShadow);
        	
      
    
        clientesButton.setPreferredSize(new Dimension(200, 100));
        GridBagConstraints buttonConstraintsClientes = new GridBagConstraints();
        buttonConstraintsClientes.insets = new Insets(10, 10, 10, 10);
        buttonConstraintsClientes.fill = GridBagConstraints.BOTH;
        buttonConstraintsClientes.gridx = 0;
        buttonConstraintsClientes.gridy = 1;
        buttonConstraintsClientes.gridwidth = 2;
        mainPanel.add(clientesButton, buttonConstraintsClientes);

        funcionariosButton = new JButton("Funcionários");
        funcionariosButton.setBackground(Color.LIGHT_GRAY);
        funcionariosButton.addActionListener(e -> {
        	CadastroFuncionario telacadastro1 = new CadastroFuncionario();
			telacadastro1.setVisible(true);
           
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
        aparelhosButton.setBackground(Color.LIGHT_GRAY);
        aparelhosButton.addActionListener(e -> {
        	cadastroA telacadastro2 = new cadastroA();
			telacadastro2.setVisible(true);
           
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
        parceriasButton.setBackground(Color.LIGHT_GRAY);
        parceriasButton.addActionListener(e -> {
        	CadastroLojaSuplemento telacadastro3 = new CadastroLojaSuplemento();
			telacadastro3.setVisible(true);
           
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
        sobreNosButton.setBackground(Color.LIGHT_GRAY);
        sobreNosButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                exibirTelaSobreNos();
            }
        });
        GridBagConstraints buttonConstraintsSobreNos = new GridBagConstraints();
        buttonConstraintsSobreNos.insets = new Insets(10, 10, 10, 10);
        buttonConstraintsSobreNos.fill = GridBagConstraints.BOTH;
        buttonConstraintsSobreNos.gridx = 0;
        buttonConstraintsSobreNos.gridy = 3;
        buttonConstraintsSobreNos.gridwidth = 4;
        mainPanel.add(sobreNosButton, buttonConstraintsSobreNos);

        getContentPane().add(mainPanel);
    }

    private void exibirTelaSobreNos() {
        JFrame telaSobreNos = new JFrame("ACADEMIAGE");
        telaSobreNos.setSize(400, 300);
        telaSobreNos.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("ACADEMIAGE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        String[] sobreNosText = {
                "Ambiente climatizado",
                "Grande quantidade e variedade de equipamentos",
                "Alerta sobre a programação",
                "Central de atendimento",
                "Professores capacitados"
        };

        for (String text : sobreNosText) {
            JLabel label = new JLabel(text);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(label);
        }

        telaSobreNos.getContentPane().add(panel);
        telaSobreNos.setVisible(true);
    }
 
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Academia2 academia = new Academia2();
            academia.setVisible(true);
        });
    }

        
    }

