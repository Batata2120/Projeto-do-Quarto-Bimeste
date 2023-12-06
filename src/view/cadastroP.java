package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import dao.*;
import objetos.*;
import principal.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class cadastroP extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tabela;
    private List<Parceria> parceriaList;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    cadastroP frame = new cadastroP();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public cadastroP() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        tabela = new JTable();
        contentPane.add(new JScrollPane(tabela), BorderLayout.CENTER);

        JPanel panelBotoes = new JPanel(new GridLayout(1, 4));
        JButton adicionarButton = new JButton("Adicionar");
        JButton removerButton = new JButton("Remover");
        JButton editarButton = new JButton("Editar");
        JButton listarButton = new JButton("Listar");

        adicionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaAdicionar();
            }
        });

        removerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaRemover();
            }
        });

        editarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaEditar();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                displayParceriaData();
            }
        });

        panelBotoes.add(adicionarButton);
        panelBotoes.add(removerButton);
        panelBotoes.add(editarButton);
        panelBotoes.add(listarButton);

        contentPane.add(panelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void abrirTelaAdicionar() {
        JFrame telaAdicionar = new JFrame("Adicionar Parceria");
        telaAdicionar.setSize(400, 400);
        telaAdicionar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("CNPJ:"));
        JTextField cnpjTextField = new JTextField();
        panel.add(cnpjTextField);

        panel.add(new JLabel("Localização:"));
        JTextField localizacaoTextField = new JTextField();
        panel.add(localizacaoTextField);

        panel.add(new JLabel("Proprietário:"));
        JTextField proprietarioTextField = new JTextField();
        panel.add(proprietarioTextField);

        panel.add(new JLabel("Nome da Loja:"));
        JTextField nomeLojaTextField = new JTextField();
        panel.add(nomeLojaTextField);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cnpj = cnpjTextField.getText();
                String localizacao = localizacaoTextField.getText();
                String proprietario = proprietarioTextField.getText();
                String nomeLoja = nomeLojaTextField.getText();

                Parceria newParceria = new Parceria(cnpj, localizacao, proprietario, nomeLoja);
                parceriaList.add(newParceria);

                JOptionPane.showMessageDialog(null, "Parceria cadastrada!");
                telaAdicionar.dispose();
                displayParceriaData();
            }
        });
        panel.add(cadastrarButton);

        telaAdicionar.add(panel);
        telaAdicionar.setVisible(true);
    }

    private void abrirTelaRemover() {
        JFrame telaRemover = new JFrame("Remover Parceria");
        telaRemover.setSize(300, 150);
        telaRemover.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JLabel("CNPJ da Parceria:"));
        JTextField cnpjParceriaTextField = new JTextField();
        panel.add(cnpjParceriaTextField);

        JButton removerButton = new JButton("Remover");
        removerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cnpjParceria = cnpjParceriaTextField.getText();

                Parceria parceriaToRemove = findParceriaByCnpj(cnpjParceria);

                if (parceriaToRemove != null) {
                    parceriaList.remove(parceriaToRemove);
                    JOptionPane.showMessageDialog(null, "Parceria removida!");
                } else {
                    JOptionPane.showMessageDialog(null, "Parceria não encontrada!");
                }

                telaRemover.dispose();
                displayParceriaData();
            }
        });
        panel.add(removerButton);

        telaRemover.add(panel);
        telaRemover.setVisible(true);
    }

    private Parceria findParceriaByCnpj(String cnpj) {
        for (Parceria parceria : parceriaList) {
            if (parceria.getCnpj().equals(cnpj)) {
                return parceria;
            }
        }
        return null;
    }

    private void abrirTelaEditar() {
        JFrame telaEditar = new JFrame("Editar Parceria");
        telaEditar.setSize(400, 300);
        telaEditar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("CNPJ da Parceria:"));
        JTextField cnpjParceriaTextField = new JTextField();
        panel.add(cnpjParceriaTextField);

        panel.add(new JLabel("Nova Localização:"));
        JTextField novaLocalizacaoTextField = new JTextField();
        panel.add(novaLocalizacaoTextField);

        panel.add(new JLabel("Novo Proprietário:"));
        JTextField novoProprietarioTextField = new JTextField();
        panel.add(novoProprietarioTextField);

        panel.add(new JLabel("Novo Nome da Loja:"));
        JTextField novoNomeLojaTextField = new JTextField();
        panel.add(novoNomeLojaTextField);

        JButton editarButton = new JButton("Editar");
        editarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cnpjParceria = cnpjParceriaTextField.getText();
                String novaLocalizacao = novaLocalizacaoTextField.getText();
                String novoProprietario = novoProprietarioTextField.getText();
                String novoNomeLoja = novoNomeLojaTextField.getText();

                Parceria parceriaE = findParceriaByCnpj(cnpjParceria);

                if (parceriaE != null) {
                    parceriaE.setLocalizacao(novaLocalizacao);
                    parceriaE.setProprietario(novoProprietario);
                    parceriaE.setNomeLoja(novoNomeLoja);
                    JOptionPane.showMessageDialog(null, "Parceria editada!");
                } else {
                    JOptionPane.showMessageDialog(null, "Parceria não encontrada!");
                }

                telaEditar.dispose();
                displayParceriaData();
            }
        });
        panel.add(editarButton);

        telaEditar.add(panel);
        telaEditar.setVisible(true);
    }

    private void displayParceriaData() {
        if (this.parceriaList != null) {
            String[] columnNames = {"Proprietario", "CNPJ", "Localizacao", "Nome Loja"};
            DefaultTableModel modelo = new DefaultTableModel(getParceriaDataArray(), columnNames);
            tabela.setModel(modelo);
        } else {
            System.out.println("A lista de parcerias é nula ou não está inicializada.");
        }
    }

    private Object[][] getParceriaDataArray() {
        if (this.parceriaList != null) {
            Object[][] data = new Object[parceriaList.size()][6];

            for (int i = 0; i < parceriaList.size(); i++) {
                Parceria parceria = parceriaList.get(i);
                data[i][0] = parceria.getProprietario();
                data[i][1] = parceria.getCnpj();
                data[i][2] = parceria.getLocalizacao();
                data[i][3] = parceria.getNomeLoja();
            }

            return data;
        } else {
            System.out.println("A lista de parcerias é nula.");
            return new Object[0][0]; 
        }
    }

  
}

