package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import dao.*;
import objetos.*;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import intermediador.IntermediadorCliente;

public class cadastro extends JFrame {

	private JPanel contentPane;
	private JTable tabela;
	private IntermediadorCliente intermediador = new IntermediadorCliente();
	private ArrayList<Cliente> arrayCliente = intermediador.listarClientes();

	/**
	 * Launch the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cadastro frame = new cadastro();
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
	public cadastro() {
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

		listarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arrayCliente = intermediador.listarClientes();

				DefaultTableModel model = new DefaultTableModel() {
					@Override
					public boolean isCellEditable(int row, int column) {
						// Torna todas as células não editáveis
						return false;
					}
				};
				model.addColumn("Nome");
				model.addColumn("CPF");
				model.addColumn("Ficha Corporal");
				model.addColumn("Cep");
				model.addColumn("Estado");
				model.addColumn("Cidade");
				model.addColumn("Bairro");
				model.addColumn("Rua");
				model.addColumn("Numero");
				model.addColumn("Cliente Premium");
				model.addColumn("Dieta Planejada");
				model.addColumn("Desconto");

				for (Cliente cliente : arrayCliente) {
					if (cliente instanceof ClientePremium) {
						Object[] rowData = { cliente.getNome(), cliente.getCpf(), cliente.getFichaCorporal(),
								cliente.getEndereco().getCep(), cliente.getEndereco().getEstado(),
								cliente.getEndereco().getCidade(), cliente.getEndereco().getBairro(),
								cliente.getEndereco().getRua(), cliente.getEndereco().getNumero(), "Sim",
								((ClientePremium) cliente).getDietaPlanejada(),
								((ClientePremium) cliente).getDesconto() };
						model.addRow(rowData);
					} else if (cliente instanceof Cliente) {
						Object[] rowData = { cliente.getNome(), cliente.getCpf(), cliente.getFichaCorporal(),
								cliente.getEndereco().getCep(), cliente.getEndereco().getEstado(),
								cliente.getEndereco().getCidade(), cliente.getEndereco().getBairro(),
								cliente.getEndereco().getRua(), cliente.getEndereco().getNumero(), "Não", "-", "-" };
						model.addRow(rowData);
					}
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

					String dietaPlanejada = null;
					Double desconto = null;

					// Agora, você pode acessar os dados da linha clicada
					String nome = (String) tabela.getValueAt(row, 0);
					String cpf = (String) tabela.getValueAt(row, 1);
					String fichaCorporal = (String) tabela.getValueAt(row, 2);
					String cep = (String) tabela.getValueAt(row, 3);
					String estado = (String) tabela.getValueAt(row, 4);
					String cidade = (String) tabela.getValueAt(row, 5);
					String bairro = (String) tabela.getValueAt(row, 6);
					String rua = (String) tabela.getValueAt(row, 7);
					String numero = (String) tabela.getValueAt(row, 8);
					String clientePremium = (String) tabela.getValueAt(row, 9);
					if (clientePremium.equals("Sim")) {
						dietaPlanejada = (String) tabela.getValueAt(row, 10);
						desconto = (Double) tabela.getValueAt(row, 11);
					}
					// Crie uma nova janela com os dados da linha clicada
					JFrame novaJanela = new JFrame("Detalhes do Cliente");
					novaJanela.setSize(400, 300);
					novaJanela.setLayout(new GridLayout(14, 2)); // Ajustado para 11 linhas, 2 colunas

					// Adicione componentes à nova janela para exibir os detalhes do cliente
					JTextField nomeTextField = adicionarCampo("Nome:", nome, novaJanela);
					JTextField cpfTextField = adicionarCampo("CPF:", cpf, novaJanela);
					JTextField fichaCorporalTextField = adicionarCampo("Ficha Corporal:", fichaCorporal, novaJanela);
					JTextField cepTextField = adicionarCampo("CEP:", cep, novaJanela);
					JTextField estadoTextField = adicionarCampo("Estado:", estado, novaJanela);
					JTextField cidadeTextField = adicionarCampo("Cidade:", cidade, novaJanela);
					JTextField bairroTextField = adicionarCampo("Bairro:", bairro, novaJanela);
					JTextField ruaTextField = adicionarCampo("Rua:", rua, novaJanela);
					JTextField numeroTextField = adicionarCampo("Número:", numero, novaJanela);

					// Adiciona a caixa de marcação para "Cliente Premium"
					JCheckBox clientePremiumCheckBox = new JCheckBox("Cliente Premium");
					clientePremiumCheckBox.setSelected(clientePremium.equals("Sim"));
					clientePremiumCheckBox.setEnabled(false); // Torna o JCheckBox não editável
					novaJanela.add(new JLabel("Cliente Premium:"));
					novaJanela.add(clientePremiumCheckBox);
					JTextField dietaPlanejadaField = adicionarCampo("Dieta planjada:", dietaPlanejada, novaJanela);
					dietaPlanejadaField.setColumns(2);
					JTextField descontoField = adicionarCampo("Desconto:", String.valueOf(desconto), novaJanela);
					clientePremiumCheckBox.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							if (e.getStateChange() == ItemEvent.SELECTED) {
								// Habilita os campos de texto para dieta e desconto quando a caixa está marcada
								dietaPlanejadaField.setEditable(true);
								descontoField.setEditable(true);
							} else {
								// Desabilita os campos de texto quando a caixa está desmarcada
								dietaPlanejadaField.setEditable(false);
								descontoField.setEditable(false);
							}
						}
					});

					JButton acaoButton = new JButton("Editar");
					JButton removerButton = new JButton("Remover");

					removerButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							intermediador.remover(new Cliente(nome,
									new Endereco(cep, estado, cidade, bairro, rua, numero), cpf, fichaCorporal));
							arrayCliente = intermediador.listarClientes();
							boolean flag = false;
							for (Cliente cliente : arrayCliente) {
								if (cliente.getCpf().equals(cpf)) {
									flag = true;
								}
							}
							listarClientes(tabela);
							if (!(flag)) {
								novaJanela.dispose();
							}
						}
					});
					novaJanela.add(acaoButton);
					novaJanela.add(removerButton);

					acaoButton.addActionListener(new ActionListener() {
						private boolean modoEditar = true;

						public void actionPerformed(ActionEvent e) {
							if (modoEditar) {
								// Modo "Editar"
								nomeTextField.setEditable(true);
								fichaCorporalTextField.setEditable(true);
								cepTextField.setEditable(true);
								estadoTextField.setEditable(true);
								cidadeTextField.setEditable(true);
								bairroTextField.setEditable(true);
								ruaTextField.setEditable(true);
								numeroTextField.setEditable(true);
								clientePremiumCheckBox.setEnabled(true);
								if (clientePremium.equals("Sim")) {
									dietaPlanejadaField.setEditable(true);
									descontoField.setEditable(true);
								}

								acaoButton.setText("Enviar");
							} else {
								// Modo "Enviar"
								// Obtenha os valores dos campos de texto e realize a ação desejada
								String novoNome = nomeTextField.getText();
								String novoCpf = cpfTextField.getText();
								String novaFichaCorporal = fichaCorporalTextField.getText();
								String novoCep = cepTextField.getText();
								String novoEstado = estadoTextField.getText();
								String novaCidade = cidadeTextField.getText();
								String novoBairro = bairroTextField.getText();
								String novaRua = ruaTextField.getText();
								String novoNumero = numeroTextField.getText();
								String novaDietaPlanejada = dietaPlanejadaField.getText();
								String novoDescontoString = descontoField.getText();
								double novoDesconto = 0;
								if (novoDescontoString == null) {
									novoDesconto = 0;
								} else {
									try {
										novoDesconto = Double.parseDouble(novoDescontoString.replace(",", "."));
									} catch (Exception ex) {
										novoDesconto = 0;
									}
								}

								if (clientePremiumCheckBox.isSelected()) {
									if (clientePremium.equals("Sim")) {
										ClientePremium clienteP = new ClientePremium(novoNome,
												new Endereco(novoCep, novoEstado, novaCidade, novoBairro, novaRua,
														novoNumero),
												novoCpf, novaFichaCorporal, novaDietaPlanejada, novoDesconto);
										intermediador.editarPremium(clienteP);
									} else {
										ClientePremium clienteP = new ClientePremium(novoNome,
												new Endereco(novoCep, novoEstado, novaCidade, novoBairro, novaRua,
														novoNumero),
												novoCpf, novaFichaCorporal, novaDietaPlanejada, novoDesconto);
										intermediador.inserirPremium(clienteP);
									}
								} else {
									if (clientePremium.equals("Sim")) {
										ClientePremium clienteP = new ClientePremium(novoNome,
												new Endereco(novoCep, novoEstado, novaCidade, novoBairro, novaRua,
														novoNumero),
												novoCpf, novaFichaCorporal, novaDietaPlanejada, novoDesconto);
										intermediador.removerPremium(clienteP);
									}
								}

								// Execute a lógica desejada com os novos valores
								Cliente cliente = new Cliente(novoNome,
										new Endereco(novoCep, novoEstado, novaCidade, novoBairro, novaRua, novoNumero),
										novoCpf, novaFichaCorporal);
								int deuCerto = intermediador.editar(cliente);
								if (deuCerto == 0) {
									listarClientes(tabela);
									JOptionPane.showMessageDialog(novaJanela, "Não foi possivel editar");
									novaJanela.dispose();
								} else {
									listarClientes(tabela);
									JOptionPane.showMessageDialog(novaJanela, "Cliente editado com sucesso");
									novaJanela.dispose();
								}
								// ...

								acaoButton.setText("Editar");

								// Desabilite a edição após enviar
								nomeTextField.setEditable(false);
								cpfTextField.setEditable(false);
								fichaCorporalTextField.setEditable(false);
								cepTextField.setEditable(false);
								estadoTextField.setEditable(false);
								cidadeTextField.setEditable(false);
								bairroTextField.setEditable(false);
								ruaTextField.setEditable(false);
								numeroTextField.setEditable(false);

							}

							modoEditar = !modoEditar;
						}
					});

					novaJanela.setVisible(true);

					Cliente clienteTelefone = new Cliente();
					for (int i = 0; i < arrayCliente.size(); i++) {
						if (arrayCliente.get(i).getCpf().equals(cpf)) {
							clienteTelefone = arrayCliente.get(i);
							break;
						}
					}

					// Crie uma nova janela para exibir os telefones
					JFrame telefoneJanela = new JFrame("Telefones");
					telefoneJanela.setSize(400, 300);
					telefoneJanela.setLayout(new GridLayout(14, 2));

					int i = 0;

					// Iterar sobre os telefones existentes do cliente e adicionar campos com botões
					for (String telefone : clienteTelefone.getArrayTelefones()) {
						i++;
						JTextField telefoneField = adicionarCampo("Telefone " + i, telefone, telefoneJanela);

						// Adicionar botão ao lado de cada campo de telefone
						JButton removerTelefoneExistenteButton = new JButton("Remover");
						telefoneJanela.add(removerTelefoneExistenteButton);
						Cliente clienteTelefone1 = clienteTelefone;
						removerTelefoneExistenteButton.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								// Obtenha o telefone associado a este campo
								String telefoneRemover = telefoneField.getText();

								// Remova o telefone do cliente
								int resultadoRemocao = intermediador.removerTelefone(telefoneRemover, clienteTelefone1);
								if (resultadoRemocao == 0) {
									JOptionPane.showMessageDialog(telefoneJanela,
											"Não foi possível remover o telefone.");
									listarClientes(tabela);
									telefoneJanela.dispose();
									novaJanela.dispose();
								} else {
									JOptionPane.showMessageDialog(telefoneJanela, "Telefone removido com sucesso");
									listarClientes(tabela);
									telefoneJanela.dispose();
									novaJanela.dispose();
								}
							}
						});
					}
					JLabel label = new JLabel("Novo telefone:");
					JTextField campoTexto = new JTextField("");
					telefoneJanela.add(label);
					telefoneJanela.add(campoTexto);
					Cliente clienteTelefone1 = clienteTelefone;
					// Adicione um botão para inserir novo telefone
					JButton inserirTelefoneButton = new JButton("Inserir");
					inserirTelefoneButton.addActionListener(new ActionListener() {
					    public void actionPerformed(ActionEvent e) {
					        // Obtenha o texto atual no campo de texto
					        String novoTelefone = campoTexto.getText().trim();

					        // Verifique se o texto não está vazio antes de realizar a inserção
					        if (!novoTelefone.isEmpty()) {
					            // Insira o novo telefone para o cliente
					            int resultadoInsercao = intermediador.inserirTelefone(novoTelefone, clienteTelefone1);
					            if (resultadoInsercao == 0) {
									JOptionPane.showMessageDialog(telefoneJanela,
											"Não foi possível inserir o telefone.");
									listarClientes(tabela);
									telefoneJanela.dispose();
									novaJanela.dispose();
								} else {
									JOptionPane.showMessageDialog(telefoneJanela, "Telefone inserido com sucesso");
									listarClientes(tabela);
									telefoneJanela.dispose();
									novaJanela.dispose();
								}
					        }
					    }
					});
					telefoneJanela.add(inserirTelefoneButton);
					telefoneJanela.setVisible(true);
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
		JFrame telaAdicionar = new JFrame("Adicionar Cliente");
		telaAdicionar.setSize(400, 300);
		telaAdicionar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(new GridLayout(10, 2));
		panel.add(new JLabel("Nome:"));
		JTextField nomeTextField = new JTextField();
		panel.add(nomeTextField);

		panel.add(new JLabel("CPF:"));
		JTextField cpfTextField = new JTextField();
		panel.add(cpfTextField);

		panel.add(new JLabel("Ficha Corporal:"));
		JTextField fichaTextField = new JTextField();
		panel.add(fichaTextField);

		panel.add(new JLabel("Cep:"));
		JTextField cepTextField = new JTextField();
		panel.add(cepTextField);

		panel.add(new JLabel("Estado:"));
		JTextField estadoTextField = new JTextField();
		panel.add(estadoTextField);
		
		panel.add(new JLabel("Cidade:"));
		JTextField cidadeTextField = new JTextField();
		panel.add(cidadeTextField);
		
		panel.add(new JLabel("Bairro:"));
		JTextField bairroTextField = new JTextField();
		panel.add(bairroTextField);
		
		panel.add(new JLabel("Rua:"));
		JTextField ruaTextField = new JTextField();
		panel.add(ruaTextField);
		
		panel.add(new JLabel("Numero:"));
		JTextField numeroTextField = new JTextField();
		panel.add(numeroTextField);

		JButton cadastrarButton = new JButton("Cadastrar");
		cadastrarButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nome = nomeTextField.getText();
				String cpf = cpfTextField.getText();
				String fichaCorporal = fichaTextField.getText();
				String cep = cepTextField.getText();
				String estado = estadoTextField.getText();
				String cidade = cidadeTextField.getText();
				String bairro = bairroTextField.getText();
				String rua = ruaTextField.getText();
				String numero = numeroTextField.getText();
				Endereco enderecoNovo = new Endereco(cep, estado, cidade, bairro, rua, numero);
				Cliente clienteNovo = new Cliente(nome, enderecoNovo, cpf, fichaCorporal);
				
				int deuCerto = intermediador.inserir(clienteNovo);
				if(deuCerto != 0) {
					listarClientes(tabela);
					JOptionPane.showMessageDialog(telaAdicionar, "Cliente cadastrado!");
					telaAdicionar.dispose();
				}else {
					listarClientes(tabela);
					JOptionPane.showMessageDialog(telaAdicionar, "Ocorreu um erro ao cadastrar o cliente");
					telaAdicionar.dispose();
				}
			}
		});
		panel.add(cadastrarButton);

		telaAdicionar.add(panel);
		telaAdicionar.setVisible(true);
	}

	// Método para listar os telefones do cliente na lista
	private void listarTelefones(Cliente cliente, DefaultListModel<String> model) {
		model.clear();
		ArrayList<String> telefones = intermediador.listarTelefones(cliente);
		for (String telefone : telefones) {
			model.addElement(telefone);
		}
	}

	public void listarClientes(JTable tabelaEscolhida) {
		arrayCliente = intermediador.listarClientes();

		DefaultTableModel model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// Torna todas as células não editáveis
				return false;
			}
		};
		model.addColumn("Nome");
		model.addColumn("CPF");
		model.addColumn("Ficha Corporal");
		model.addColumn("Cep");
		model.addColumn("Estado");
		model.addColumn("Cidade");
		model.addColumn("Bairro");
		model.addColumn("Rua");
		model.addColumn("Numero");
		model.addColumn("Cliente Premium");
		model.addColumn("Dieta Planejada");
		model.addColumn("Desconto");

		for (Cliente cliente : arrayCliente) {
			if (cliente instanceof ClientePremium) {
				Object[] rowData = { cliente.getNome(), cliente.getCpf(), cliente.getFichaCorporal(),
						cliente.getEndereco().getCep(), cliente.getEndereco().getEstado(),
						cliente.getEndereco().getCidade(), cliente.getEndereco().getBairro(),
						cliente.getEndereco().getRua(), cliente.getEndereco().getNumero(), "Sim",
						((ClientePremium) cliente).getDietaPlanejada(), ((ClientePremium) cliente).getDesconto() };
				model.addRow(rowData);
			} else if (cliente instanceof Cliente) {
				Object[] rowData = { cliente.getNome(), cliente.getCpf(), cliente.getFichaCorporal(),
						cliente.getEndereco().getCep(), cliente.getEndereco().getEstado(),
						cliente.getEndereco().getCidade(), cliente.getEndereco().getBairro(),
						cliente.getEndereco().getRua(), cliente.getEndereco().getNumero(), "Não", "-", "-" };
				model.addRow(rowData);
			}
		}
		tabelaEscolhida.setModel(model);
	}

}
