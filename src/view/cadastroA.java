package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

public class cadastroA extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabela;
    private List<Aparelho> aparelhoList;

	/**
	 * Launch the application.
	 */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    cadastroA frame = new cadastroA();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public cadastroA() {
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
                List<Aparelho> aparelhoList = Aparelho.getAparelhoList();

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("ID");
                model.addColumn("Qualidade");
                model.addColumn("Preço");
                model.addColumn("CNPJ da Academia");

                for (Aparelho aparelho : aparelhoList) {
                    Object[] rowData = {
                            aparelho.getId(),
                            aparelho.getQualidade(),
                            aparelho.getPreco(),
                            aparelho.getCnpjAcademia()
                    };
                    model.addRow(rowData);
                }
                tabela.setModel(model);
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
        JFrame telaAdicionar = new JFrame("Adicionar Aparelho");
        telaAdicionar.setSize(400, 300);
        telaAdicionar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Qualidade:"));
        JTextField qualidadeTextField = new JTextField();
        panel.add(qualidadeTextField);

        panel.add(new JLabel("Preço:"));
        JTextField precoTextField = new JTextField();
        panel.add(precoTextField);

        panel.add(new JLabel("ID:"));
        JTextField idTextField = new JTextField();
        panel.add(idTextField);

        panel.add(new JLabel("CNPJ da Academia:"));
        JTextField cnpjAcademiaTextField = new JTextField();
        panel.add(cnpjAcademiaTextField);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String qualidade = qualidadeTextField.getText();
                double preco = Double.parseDouble(precoTextField.getText());
                int id = Integer.parseInt(idTextField.getText());
                String cnpjAcademia = cnpjAcademiaTextField.getText();

                Aparelho newAparelho = new Aparelho(id, qualidade, preco, cnpjAcademia);

                aparelhoList.add(newAparelho);
                JOptionPane.showMessageDialog(null, "Aparelho cadastrado!");
                telaAdicionar.dispose();
                displayAparelhoData();
            }
        });
        panel.add(cadastrarButton);

        telaAdicionar.add(panel);
        telaAdicionar.setVisible(true);
    }

    private void abrirTelaRemover() {
        JFrame telaRemover = new JFrame("Remover Aparelho");
        telaRemover.setSize(300, 150);
        telaRemover.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("ID do Aparelho:"));
        JTextField idAparelhoTextField = new JTextField();
        panel.add(idAparelhoTextField);

        JButton removerButton = new JButton("Remover");
        removerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idAparelho = Integer.parseInt(idAparelhoTextField.getText());

                Aparelho aparelhoToRemove = findAparelhoById(idAparelho);

                if (aparelhoToRemove != null) {
                    aparelhoList.remove(aparelhoToRemove);
                    JOptionPane.showMessageDialog(null, "Aparelho removido!");
                } else {
                    JOptionPane.showMessageDialog(null, "Aparelho não encontrado!");
                }

                telaRemover.dispose();
                displayAparelhoData();
            }
        });
        panel.add(removerButton);

        telaRemover.add(panel);
        telaRemover.setVisible(true);
    }

    private Aparelho findAparelhoById(int id) {
        for (Aparelho aparelho : aparelhoList) {
            if (aparelho.getId() == id) {
                return aparelho;
            }
        }
        return null;
    }

    private void abrirTelaEditar() {
        JFrame telaEditar = new JFrame("Editar Aparelho");
        telaEditar.setSize(400, 300);
        telaEditar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("ID do Aparelho:"));
        JTextField idAparelhoTextField = new JTextField();
        panel.add(idAparelhoTextField);

        panel.add(new JLabel("Nova Qualidade:"));
        JTextField novaQualidadeTextField = new JTextField();
        panel.add(novaQualidadeTextField);

        panel.add(new JLabel("Novo Preço:"));
        JTextField novoPrecoTextField = new JTextField();
        panel.add(novoPrecoTextField);

        panel.add(new JLabel("Novo CNPJ da Academia:"));
        JTextField novoCnpjAcademiaTextField = new JTextField();
        panel.add(novoCnpjAcademiaTextField);

        JButton editarButton = new JButton("Editar");
        editarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int idAparelho = Integer.parseInt(idAparelhoTextField.getText());
                String novaQualidade = novaQualidadeTextField.getText();
                double novoPreco = Double.parseDouble(novoPrecoTextField.getText());
                String novoCnpjAcademia = novoCnpjAcademiaTextField.getText();

                Aparelho aparelhoE = findAparelhoById(idAparelho);

                if (aparelhoE != null) {
                    aparelhoE.setQualidade(novaQualidade);
                    aparelhoE.setPreco(novoPreco);
                    aparelhoE.setCnpjAcademia(novoCnpjAcademia);
                    JOptionPane.showMessageDialog(null, "Aparelho editado!");
                } else {
                    JOptionPane.showMessageDialog(null, "Aparelho não encontrado!");
                }

                telaEditar.dispose();
                displayAparelhoData();
            }
        });
        panel.add(editarButton);

        telaEditar.add(panel);
        telaEditar.setVisible(true);
    }

    private void displayAparelhoData() {
        String[] columnNames = {"ID", "Qualidade", "Preço", "CNPJ da Academia"};

        DefaultTableModel model = new DefaultTableModel(getAparelhoDataArray(), columnNames);

        tabela.setModel(model);
    }

    private Object[][] getAparelhoDataArray() {
        Object[][] data = new Object[aparelhoList.size()][4];

        for (int i = 0; i < aparelhoList.size(); i++) {
            Aparelho aparelho = aparelhoList.get(i);
            data[i][0] = aparelho.getId();
            data[i][1] = aparelho.getQualidade();
            data[i][2] = aparelho.getPreco();
            data[i][3] = aparelho.getCnpjAcademia();
        }

        return data;
    }

}
