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
import intermediador.IntermediadorLoja;
import objetos.LojaSuplemento;

public class CadastroLojaSuplemento extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tabela;
	private IntermediadorLoja intermediador = new IntermediadorLoja();
	private ArrayList<LojaSuplemento> arrayLojaSuplemento = intermediador.listarLojaSuplementos();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroLojaSuplemento frame = new CadastroLojaSuplemento();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CadastroLojaSuplemento() {
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
				abrirTelaAdicionarLojaSuplemento();
			}
		});

		listarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arrayLojaSuplemento = intermediador.listarLojaSuplementos();
				atualizarTabela(tabela);
			}
		});

		tabela.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					abrirDetalhesLojaSuplemento();
				}
			}
		});

		panelBotoes.add(adicionarButton);
		panelBotoes.add(listarButton);

		contentPane.add(panelBotoes, BorderLayout.SOUTH);

		setVisible(true);
	}

	private void abrirDetalhesLojaSuplemento() {
		int row = tabela.getSelectedRow();
		if (row != -1) {
			LojaSuplemento lojaSuplemento = arrayLojaSuplemento.get(row);

			JFrame novaJanela = new JFrame("Detalhes da Loja de Suplementos");
			novaJanela.setSize(400, 300);
			novaJanela.setLayout(new GridLayout(14, 2));

			JTextField cnpjTextField = adicionarCampo("CNPJ:", lojaSuplemento.getCnpj(), novaJanela);
			JTextField localizacaoTextField = adicionarCampo("Localização:", lojaSuplemento.getLocalizacao(),
					novaJanela);
			JTextField proprietarioTextField = adicionarCampo("Proprietário:", lojaSuplemento.getProprietario(),
					novaJanela);

			JButton acaoButton = new JButton("Editar");
			JButton removerButton = new JButton("Remover");

			acaoButton.addActionListener(new ActionListener() {
				private boolean modoEditar = true;

				public void actionPerformed(ActionEvent e) {
					if (modoEditar) {
						// Modo "Editar"
						localizacaoTextField.setEditable(true);
						proprietarioTextField.setEditable(true);

						acaoButton.setText("Enviar");
					} else {
						// Modo "Enviar"
						String novaLocalizacao = localizacaoTextField.getText();
						String novoProprietario = proprietarioTextField.getText();

						// Execute a lógica desejada com os novos valores
						LojaSuplemento lojaSuplementoEditada = new LojaSuplemento(lojaSuplemento.getCnpj(),
								novaLocalizacao, novoProprietario);
						int deuCerto = intermediador.editar(lojaSuplementoEditada);
						if (deuCerto == 0) {
							atualizarTabela(tabela);
							JOptionPane.showMessageDialog(novaJanela, "Não foi possível editar a loja de suplementos.");
							novaJanela.dispose();
						} else {
							atualizarTabela(tabela);
							JOptionPane.showMessageDialog(novaJanela, "Loja de suplementos editada com sucesso!");
							novaJanela.dispose();
						}

						acaoButton.setText("Editar");

						// Desabilite a edição após enviar
						localizacaoTextField.setEditable(false);
						proprietarioTextField.setEditable(false);
					}

					modoEditar = !modoEditar;
				}
			});

			removerButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int deuCerto = intermediador.remover(lojaSuplemento);
					if (deuCerto == 0) {
						atualizarTabela(tabela);
						JOptionPane.showMessageDialog(novaJanela, "Não foi possível remover a loja de suplementos.");
						novaJanela.dispose();
					} else {
						atualizarTabela(tabela);
						JOptionPane.showMessageDialog(novaJanela, "Loja de suplementos removida com sucesso!");
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

	private void abrirTelaAdicionarLojaSuplemento() {
	    JFrame telaAdicionar = new JFrame("Adicionar Loja de Suplementos");
	    telaAdicionar.setSize(400, 300);
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

	    JButton cadastrarButton = new JButton("Cadastrar");
	    cadastrarButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String cnpj = cnpjTextField.getText();
	            String localizacao = localizacaoTextField.getText();
	            String proprietario = proprietarioTextField.getText();

	            // Verifique se os campos obrigatórios foram preenchidos
	            if (cnpj.isEmpty() || localizacao.isEmpty() || proprietario.isEmpty()) {
	                JOptionPane.showMessageDialog(telaAdicionar, "Todos os campos devem ser preenchidos.", "Erro",
	                        JOptionPane.ERROR_MESSAGE);
	                return;
	            }

	            LojaSuplemento novaLojaSuplemento = new LojaSuplemento(cnpj, localizacao, proprietario);
	            int deuCerto = intermediador.inserir(novaLojaSuplemento);
	            if (deuCerto == 0) {
	                JOptionPane.showMessageDialog(telaAdicionar, "Não foi possível cadastrar a loja de suplementos.",
	                        "Erro", JOptionPane.ERROR_MESSAGE);
	            } else {
	                atualizarTabela(tabela);
	                JOptionPane.showMessageDialog(telaAdicionar, "Loja de suplementos cadastrada com sucesso!");
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
		arrayLojaSuplemento = intermediador.listarLojaSuplementos();
		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		model.addColumn("CNPJ");
		model.addColumn("Localização");
		model.addColumn("Proprietário");

		for (LojaSuplemento lojaSuplemento : arrayLojaSuplemento) {
			Object[] rowData = { lojaSuplemento.getCnpj(), lojaSuplemento.getLocalizacao(),
					lojaSuplemento.getProprietario() };
			model.addRow(rowData);
		}

		tabelaEscolhida.setModel(model);
	}
}
