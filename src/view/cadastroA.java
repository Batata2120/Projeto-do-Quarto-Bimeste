package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import dao.*;
import intermediador.IntermediadorAparelho;
import intermediador.IntermediadorCliente;
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
	private IntermediadorAparelho intermediador = new IntermediadorAparelho();
	private ArrayList<Aparelho> arrayAparelho = intermediador.listarAparelhos();

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
        JButton listarButton = new JButton("Listar");

        adicionarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirTelaAdicionar();
            }
        });
        // String qualidade, double preco, int id, String cnpj_academia, String marca
        listarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arrayAparelho = intermediador.listarAparelhos();

				DefaultTableModel model = new DefaultTableModel() {
					@Override
					public boolean isCellEditable(int row, int column) {
						// Torna todas as células não editáveis
						return false;
					}
				};
				model.addColumn("Id");
				model.addColumn("Qualidade");
				model.addColumn("Preço");
				model.addColumn("Marca");

				for (Aparelho Aparelho : arrayAparelho) {
						Object[] rowData = { Aparelho.getId(), Aparelho.getQualidade(), Aparelho.getPreco(), Aparelho.getMarca()};
						model.addRow(rowData);

				}
				tabela.setModel(model);
			}
		});
        
        tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			    if (e.getClickCount() == 2) { // Verifica se foi um clique duplo
			        JTable target = (JTable) e.getSource();
			        int row = target.getSelectedRow(); // Obtém a linha clicada

			        // Agora, você pode acessar os dados da linha clicada
			        int id = (Integer) tabela.getValueAt(row, 0);
			        String qualidade = (String) tabela.getValueAt(row, 1);
			        double preco = (Double) tabela.getValueAt(row, 2);
			        String marca = (String) tabela.getValueAt(row, 3);

			        // Crie uma nova janela com os dados da linha clicada
			        JFrame novaJanela = new JFrame("Detalhes do Aparelho");
			        novaJanela.setSize(400, 300);
			        novaJanela.setLayout(new GridLayout(14, 2)); // Ajustado para 14 linhas, 2 colunas

			        // Adicione componentes à nova janela para exibir os detalhes do aparelho
			        JTextField idTextField = adicionarCampo("ID:", String.valueOf(id), novaJanela);
			        JTextField qualidadeTextField = adicionarCampo("Qualidade:", qualidade, novaJanela);
			        JTextField precoTextField = adicionarCampo("Preço:", String.valueOf(preco), novaJanela);
			        JTextField marcaTextField = adicionarCampo("Marca:", marca, novaJanela);

			        JButton acaoButton = new JButton("Editar");
			        JButton removerButton = new JButton("Remover");

			        removerButton.addActionListener(new ActionListener() {
			            public void actionPerformed(ActionEvent e) {
			                String cnpjAcademia = null;
			                arrayAparelho = intermediador.listarAparelhos();
			                for (Aparelho aparelho : arrayAparelho) {
			                    if (aparelho.getId() == id) {
			                        cnpjAcademia = aparelho.getCnpj_academia();
			                    }
			                }

			                intermediador.remover(new Aparelho(qualidade, preco, id, cnpjAcademia, marca));
			                arrayAparelho = intermediador.listarAparelhos();
			                boolean flag = false;
			                for (Aparelho aparelho : arrayAparelho) {
			                    if (aparelho.getId() == id) {
			                        flag = true;
			                    }
			                }
			                listarAparelhos(tabela);
			                if (!flag) {
			                    JOptionPane.showMessageDialog(novaJanela, "Aparelho removido com sucesso!");
			                } else {
			                    JOptionPane.showMessageDialog(novaJanela, "Ocorreu um erro ao remover o aparelho.");
			                }
			                novaJanela.dispose();
			            }
			        });

			        novaJanela.add(acaoButton);
			        novaJanela.add(removerButton);

			        acaoButton.addActionListener(new ActionListener() {
			            private boolean modoEditar = true;

			            public void actionPerformed(ActionEvent e) {
			                if (modoEditar) {
			                    // Modo "Editar"
			                    qualidadeTextField.setEditable(true);
			                    precoTextField.setEditable(true);
			                    marcaTextField.setEditable(true);

			                    acaoButton.setText("Enviar");
			                } else {
			                    // Modo "Enviar"
			                    // Obtenha os valores dos campos de texto e realize a ação desejada
			                    int novoId = Integer.parseInt(idTextField.getText());
			                    String novaQualidade = qualidadeTextField.getText();
			                    double novoPreco = Double.parseDouble(precoTextField.getText());
			                    String novaMarca = marcaTextField.getText();

			                    // Execute a lógica desejada com os novos valores
			                    Aparelho aparelho = new Aparelho(novaQualidade, novoPreco, novoId, null, novaMarca);
			                    int deuCerto = intermediador.editar(aparelho);
			                    if (deuCerto == 0) {
			                        listarAparelhos(tabela);
			                        JOptionPane.showMessageDialog(novaJanela, "Não foi possível editar o aparelho.");
			                    } else {
			                        listarAparelhos(tabela);
			                        JOptionPane.showMessageDialog(novaJanela, "Aparelho editado com sucesso!");
			                    }

			                    acaoButton.setText("Editar");

			                    // Desabilite a edição após enviar
			                    idTextField.setEditable(false);
			                    qualidadeTextField.setEditable(false);
			                    precoTextField.setEditable(false);
			                    marcaTextField.setEditable(false);
			                }

			                modoEditar = !modoEditar;
			            }
			        });

			        novaJanela.setVisible(true);
			    }
        
			}
			private JTextField adicionarCampo(String rotulo, String valor, JFrame janela) {
				JLabel label = new JLabel(rotulo);
				JTextField campoTexto = new JTextField(valor);
				campoTexto.setEditable(false); // Torna o campo de texto não editável
				janela.add(label);
				janela.add(campoTexto);

				return campoTexto;
			}
        });
        
        panelBotoes.add(adicionarButton);
		panelBotoes.add(listarButton);

		contentPane.add(panelBotoes, BorderLayout.SOUTH);

		setVisible(true);
    }

    private void abrirTelaAdicionar() {
        JFrame telaAdicionar = new JFrame("Adicionar Aparelho");
        telaAdicionar.setSize(400, 300);
        telaAdicionar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Qualidade:"));
        JTextField qualidadeTextField = new JTextField();
        panel.add(qualidadeTextField);

        panel.add(new JLabel("Preço:"));
        JTextField precoTextField = new JTextField();
        panel.add(precoTextField);
        
        panel.add(new JLabel("Marca:"));
        JTextField marcaTextField = new JTextField();
        panel.add(marcaTextField);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String qualidade = qualidadeTextField.getText();
                double preco = Double.parseDouble(precoTextField.getText());
                String marca = marcaTextField.getText();

                Aparelho novoAparelho = new Aparelho(qualidade, preco, 0, null, marca);
                int deuCerto = intermediador.inserir(novoAparelho);
                if (deuCerto == 0) {
                    listarAparelhos(tabela);
                    JOptionPane.showMessageDialog(telaAdicionar, "Não foi possível cadastrar o aparelho.");
                } else {
                    listarAparelhos(tabela);
                    JOptionPane.showMessageDialog(telaAdicionar, "Aparelho cadastrado com sucesso!");
                }
                telaAdicionar.dispose();
            }
        });
        panel.add(cadastrarButton);

        telaAdicionar.add(panel);
        telaAdicionar.setVisible(true);
    }
    public void listarAparelhos(JTable tabelaEscolhida) {
			arrayAparelho = intermediador.listarAparelhos();

			DefaultTableModel model = new DefaultTableModel() {
				@Override
				public boolean isCellEditable(int row, int column) {
					// Torna todas as células não editáveis
					return false;
				}
			};
			model.addColumn("Id");
			model.addColumn("Qualidade");
			model.addColumn("Preço");
			model.addColumn("Marca");

			for (Aparelho Aparelho : arrayAparelho) {
					Object[] rowData = { Aparelho.getId(), Aparelho.getQualidade(), Aparelho.getPreco(), Aparelho.getMarca()};
					model.addRow(rowData);

			}
			tabelaEscolhida.setModel(model);
	}

}
