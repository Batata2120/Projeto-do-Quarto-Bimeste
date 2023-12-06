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

public class cadastroF extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tabela;
    private List<Funcionario> funcionarioList;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    cadastroF frame = new cadastroF();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public cadastroF() {
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
                displayFuncionarioData();
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
        JFrame telaAdicionar = new JFrame("Adicionar Funcionário");
        telaAdicionar.setSize(400, 300);
        telaAdicionar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Nome:"));
        JTextField nomeTextField = new JTextField();
        panel.add(nomeTextField);

        panel.add(new JLabel("CPF:"));
        JTextField cpfTextField = new JTextField();
        panel.add(cpfTextField);

        panel.add(new JLabel("Função:"));
        JTextField funcaoTextField = new JTextField();
        panel.add(funcaoTextField);

        panel.add(new JLabel("Salário:"));
        JTextField salarioTextField = new JTextField();
        panel.add(salarioTextField);

        panel.add(new JLabel("Data de Nascimento:"));
        JTextField dataNascimentoTextField = new JTextField();
        panel.add(dataNascimentoTextField);

        panel.add(new JLabel("CNPJ da Academia:"));
        JTextField cnpjAcademiaTextField = new JTextField();
        panel.add(cnpjAcademiaTextField);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeTextField.getText();
                String cpf = cpfTextField.getText();
                String funcao = funcaoTextField.getText();
                double salario = Double.parseDouble(salarioTextField.getText());
                String dataNascimento = dataNascimentoTextField.getText();
                String cnpjAcademia = cnpjAcademiaTextField.getText();

                Funcionario newFuncionario = new Funcionario(nome, cpf, funcao, salario, dataNascimento, cnpjAcademia);

                funcionarioList.add(newFuncionario);
                JOptionPane.showMessageDialog(null, "Funcionário cadastrado!");
                telaAdicionar.dispose();
                displayFuncionarioData();
            }
        });
        panel.add(cadastrarButton);

        telaAdicionar.add(panel);
        telaAdicionar.setVisible(true);
    }

    private void abrirTelaRemover() {
        JFrame telaRemover = new JFrame("Remover Funcionário");
        telaRemover.setSize(300, 150);
        telaRemover.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Nome do Funcionário:"));
        JTextField nomeFuncionarioTextField = new JTextField();
        panel.add(nomeFuncionarioTextField);

        JButton removerButton = new JButton("Remover");
        removerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeFuncionario = nomeFuncionarioTextField.getText();

                Funcionario funcionarioToRemove = findFuncionario(nomeFuncionario);

                if (funcionarioToRemove != null) {
                    funcionarioList.remove(funcionarioToRemove);
                    JOptionPane.showMessageDialog(null, "Funcionário removido!");
                } else {
                    JOptionPane.showMessageDialog(null, "Funcionário não encontrado!");
                }

                telaRemover.dispose();

                displayFuncionarioData();
            }
        });
        panel.add(removerButton);

        telaRemover.add(panel);
        telaRemover.setVisible(true);
    }

    private Funcionario findFuncionario(String nome) {
        for (Funcionario funcionario : funcionarioList) {
            if (funcionario.getNome().equals(nome)) {
                return funcionario;
            }
        }
        return null;
    }

    private void abrirTelaEditar() {
        JFrame telaEditar = new JFrame("Editar Funcionário");
        telaEditar.setSize(400, 300);
        telaEditar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Nome do Funcionário:"));
        JTextField nomeFuncionarioTextField = new JTextField();
        panel.add(nomeFuncionarioTextField);

        panel.add(new JLabel("Novo Nome:"));
        JTextField novoNomeTextField = new JTextField();
        panel.add(novoNomeTextField);

        panel.add(new JLabel("Nova Função:"));
        JTextField novaFuncaoTextField = new JTextField();
        panel.add(novaFuncaoTextField);

        panel.add(new JLabel("Novo Salário:"));
        JTextField novoSalarioTextField = new JTextField();
        panel.add(novoSalarioTextField);

        panel.add(new JLabel("Nova Data de Nascimento:"));
        JTextField novaDataNascimentoTextField = new JTextField();
        panel.add(novaDataNascimentoTextField);

        panel.add(new JLabel("Novo CNPJ da Academia:"));
        JTextField novoCnpjAcademiaTextField = new JTextField();
        panel.add(novoCnpjAcademiaTextField);

        JButton editarButton = new JButton("Editar");
        editarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nomeFuncionario = nomeFuncionarioTextField.getText();
                String novoNome = novoNomeTextField.getText();
                String novaFuncao = novaFuncaoTextField.getText();
                double novoSalario = Double.parseDouble(novoSalarioTextField.getText());
                String novaDataNascimento = novaDataNascimentoTextField.getText();
                String novoCnpjAcademia = novoCnpjAcademiaTextField.getText();

                Funcionario funcionarioE = findFuncionario(nomeFuncionario);

                if (funcionarioE != null) {
                    funcionarioE.setNome(novoNome);
                    funcionarioE.setFuncao(novaFuncao);
                    funcionarioE.setSalario(novoSalario);
                    funcionarioE.setDataNascimento(novaDataNascimento);
                    funcionarioE.setCnpjAcademia(novoCnpjAcademia);
                    JOptionPane.showMessageDialog(null, "Funcionário editado!");
                } else {
                    JOptionPane.showMessageDialog(null, "Funcionário não encontrado!");
                }

                telaEditar.dispose();
                displayFuncionarioData();
            }
        });
        panel.add(editarButton);

        telaEditar.add(panel);
        telaEditar.setVisible(true);
    }

    private void displayFuncionarioData() {
        if (this.funcionarioList != null) {
            String[] columnNames = {"Nome", "CPF", "Função", "Salário", "Dados de Nascimento", "CNPJ da Academia"};
            DefaultTableModel modelo = new DefaultTableModel(getFuncionarioDataArray(), columnNames);
            tabela.setModel(modelo);
        } 
       
    else {
            System.out.println("A lista de funcionários é nula ou não está inicializada.");
        }
    }

    private Object[][] getFuncionarioDataArray() {
        if (this.funcionarioList != null) {
            Object[][] data = new Object[funcionarioList.size()][6];

            for (int i = 0; i < funcionarioList.size(); i++) {
                Funcionario funcionario = funcionarioList.get(i);
                data[i][0] = funcionario.getNome();
                data[i][1] = funcionario.getCpf();
                data[i][2] = funcionario.getFuncao();
                data[i][3] = funcionario.getSalario();
                data[i][4] = funcionario.getDataNascimento();
                data[i][5] = funcionario.getCnpjAcademia();
            }

            return data;
        } else {
            System.out.println("A lista de funcionários é nula.");
            return new Object[0][0]; 
        }
}
}


