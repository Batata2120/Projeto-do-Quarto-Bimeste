package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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
import intermediador.IntermediadorFuncionario;
import objetos.Funcionario;

public class CadastroFuncionario extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable tabela;
    private IntermediadorFuncionario intermediador = new IntermediadorFuncionario();
    private ArrayList<Funcionario> arrayFuncionario = intermediador.listarFuncionarios();

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CadastroFuncionario frame = new CadastroFuncionario();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public CadastroFuncionario() {
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
        JButton listarButton = new JButton("Listar");

        adicionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaAdicionarFuncionario();
            }
        });

        listarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                arrayFuncionario = intermediador.listarFuncionarios();
                atualizarTabela(tabela);
            }
        });

        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    abrirDetalhesFuncionario();
                }
            }
        });

        panelBotoes.add(adicionarButton);
        panelBotoes.add(listarButton);

        contentPane.add(panelBotoes, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void abrirDetalhesFuncionario() {
        int row = tabela.getSelectedRow();
        if (row != -1) {
            Funcionario funcionario = arrayFuncionario.get(row);

            JFrame novaJanela = new JFrame("Detalhes do Funcionário");
            novaJanela.setSize(400, 300);
            novaJanela.setLayout(new GridLayout(17, 2));

            JTextField nomeTextField = adicionarCampo("Nome:", funcionario.getNome(), novaJanela);
            JTextField cpfTextField = adicionarCampo("CPF:", funcionario.getCpf(), novaJanela);
            JTextField funcaoTextField = adicionarCampo("Função:", funcionario.getFuncao(), novaJanela);
            JTextField salarioTextField = adicionarCampo("Salário:", String.valueOf(funcionario.getSalario()),
                    novaJanela);
            JTextField dataNascimentoTextField = adicionarCampo("Data de Nascimento:",
                    funcionario.getDataNascimento(), novaJanela);


            JButton acaoButton = new JButton("Editar");
            JButton removerButton = new JButton("Remover");

            acaoButton.addActionListener(new ActionListener() {
                private boolean modoEditar = true;

                public void actionPerformed(ActionEvent e) {
                    if (modoEditar) {
                        // Modo "Editar"
                        funcaoTextField.setEditable(true);
                        salarioTextField.setEditable(true);

                        acaoButton.setText("Enviar");
                    } else {
                        // Modo "Enviar"
                        String novaFuncao = funcaoTextField.getText();
                        double novoSalario = Double.parseDouble(salarioTextField.getText());

                        // Execute a lógica desejada com os novos valores
                        Funcionario funcionarioEditado = new Funcionario(funcionario.getNome(), funcionario.getCpf(),
                                novaFuncao, novoSalario, funcionario.getDataNascimento(),
                                funcionario.getCnpjAcademiaResponsavel(), funcionario.getAcademiaResponsavel());
                        int deuCerto = intermediador.editar(funcionarioEditado);
                        if (deuCerto == 0) {
                            atualizarTabela(tabela);
                            JOptionPane.showMessageDialog(novaJanela, "Não foi possível editar o funcionário.");
                            novaJanela.dispose();
                        } else {
                            atualizarTabela(tabela);
                            JOptionPane.showMessageDialog(novaJanela, "Funcionário editado com sucesso!");
                            novaJanela.dispose();
                        }

                        acaoButton.setText("Editar");

                        // Desabilite a edição após enviar
                        funcaoTextField.setEditable(false);
                        salarioTextField.setEditable(false);
                    }

                    modoEditar = !modoEditar;
                }
            });

            removerButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int deuCerto = intermediador.remover(funcionario);
                    if (deuCerto == 0) {
                        atualizarTabela(tabela);
                        JOptionPane.showMessageDialog(novaJanela, "Não foi possível remover o funcionário.");
                        novaJanela.dispose();
                    } else {
                        atualizarTabela(tabela);
                        JOptionPane.showMessageDialog(novaJanela, "Funcionário removido com sucesso!");
                        novaJanela.dispose();
                    }
                }
            });

            novaJanela.add(acaoButton);
            novaJanela.add(removerButton);
            novaJanela.add(acaoButton);
            novaJanela.add(removerButton);

            novaJanela.setVisible(true);
        }
    }

    private void abrirTelaAdicionarFuncionario() {
        JFrame telaAdicionar = new JFrame("Adicionar Funcionário");
        telaAdicionar.setSize(400, 300);
        telaAdicionar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 2));
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

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeTextField.getText();
                String cpf = cpfTextField.getText();
                String funcao = funcaoTextField.getText();
                double salario = Double.parseDouble(salarioTextField.getText());
                String dataNascimento = dataNascimentoTextField.getText();

                // Verifique se os campos obrigatórios foram preenchidos
                if (nome.isEmpty() || cpf.isEmpty() || funcao.isEmpty() || dataNascimento.isEmpty()) {
                    JOptionPane.showMessageDialog(telaAdicionar, "Todos os campos devem ser preenchidos.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Funcionario novoFuncionario = new Funcionario(nome, cpf, funcao, salario, dataNascimento.replace('/', '-'),
                		"12345678901234", null); // Você precisa fornecer a Academia aqui
                int deuCerto = intermediador.inserir(novoFuncionario);
                if (deuCerto == 0) {
                    JOptionPane.showMessageDialog(telaAdicionar, "Não foi possível cadastrar o funcionário.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    atualizarTabela(tabela);
                    JOptionPane.showMessageDialog(telaAdicionar, "Funcionário cadastrado com sucesso!");
                    telaAdicionar.dispose();
                }
            }
        });
        panel.add(cadastrarButton);

        telaAdicionar.add(panel);
        telaAdicionar.setVisible(true);
    }

    private JTextField adicionarCampo(String rotulo, String valor, JFrame janela) {
        JLabel label = new JLabel(rotulo);
        JTextField campoTexto = new JTextField(valor);
        campoTexto.setEditable(false);
        janela.add(label);
        janela.add(campoTexto);

        return campoTexto;
    }

    private void atualizarTabela(JTable tabelaEscolhida) {
        arrayFuncionario = intermediador.listarFuncionarios();
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Nome");
        model.addColumn("CPF");
        model.addColumn("Função");
        model.addColumn("Salário");
        model.addColumn("Data de Nascimento");

        for (Funcionario funcionario : arrayFuncionario) {
            Object[] rowData = { funcionario.getNome(), funcionario.getCpf(), funcionario.getFuncao(),
                    funcionario.getSalario(), funcionario.getDataNascimento() };
            model.addRow(rowData);
        }

        tabelaEscolhida.setModel(model);
    }
}
