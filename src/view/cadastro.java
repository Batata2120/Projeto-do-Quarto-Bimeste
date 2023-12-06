package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import dao.*;
import objetos.*;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

import intermediador.IntermediadorCliente;

public class cadastro extends JFrame {

	private JPanel contentPane;
	private JTable tabela;
	private List<Cliente> clienteList;
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
                	if(cliente instanceof ClientePremium) {
                		Object[] rowData = {
                    			cliente.getNome(),
                    			cliente.getCpf(),
                    			cliente.getFichaCorporal(),
                    			cliente.getEndereco().getCep(),
                    			cliente.getEndereco().getEstado(),
                    			cliente.getEndereco().getCidade(),
                    			cliente.getEndereco().getBairro(),
                    			cliente.getEndereco().getRua(),
                    			cliente.getEndereco().getNumero(),
                    			"Sim",
                    			((ClientePremium) cliente).getDietaPlanejada(),
                    			((ClientePremium) cliente).getDesconto()
                    	};
                		model.addRow(rowData);
                    }
                	else if(cliente instanceof Cliente) {
                    	Object[] rowData = {
                    			cliente.getNome(),
                    			cliente.getCpf(),
                    			cliente.getFichaCorporal(),
                    			cliente.getEndereco().getCep(),
                    			cliente.getEndereco().getEstado(),
                    			cliente.getEndereco().getCidade(),
                    			cliente.getEndereco().getBairro(),
                    			cliente.getEndereco().getRua(),
                    			cliente.getEndereco().getNumero(),
                    			"Não",
                    			"-",
                    			"-"                    			
                    	};
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
                    if(clientePremium.equals("Sim")) {
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
                    if(clientePremium.equals("Sim")) {
                    	 JTextField dietaPlanejadaField = adicionarCampo("Dieta planjada:", dietaPlanejada, novaJanela);
                    	 dietaPlanejadaField.setColumns(2);
                         JTextField descontoField = adicionarCampo("Desconto:", String.valueOf(desconto), novaJanela);
                    }
                    
                    JButton acaoButton = new JButton("Editar");
                    JButton removerButton = new JButton("Remover");
                    
                    removerButton.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            intermediador.remover(new Cliente(nome, new Endereco(cep, estado, cidade, bairro, rua, numero), cpf, fichaCorporal));
                            arrayCliente = intermediador.listarClientes();
                            boolean flag = false;
                            for(Cliente cliente : arrayCliente) {
                            	if(cliente.getCpf().equals(cpf)) {
                            		flag = true;
                            	}
                            }
                        	listarClientes(tabela);
                            if(!(flag)) {
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
                                cpfTextField.setEditable(true);
                                fichaCorporalTextField.setEditable(true);
                                cepTextField.setEditable(true);
                                estadoTextField.setEditable(true);
                                cidadeTextField.setEditable(true);
                                bairroTextField.setEditable(true);
                                ruaTextField.setEditable(true);
                                numeroTextField.setEditable(true);

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
                
                                // Execute a lógica desejada com os novos valores
                                Cliente cliente = new Cliente(novoNome, new Endereco(novoCep, novoEstado, novaCidade, novoBairro, novaRua, novoNumero), novoCpf, novaFichaCorporal);
                                int deuCerto = intermediador.editar(cliente);
                                if(deuCerto == 0) {
                                	listarClientes(tabela);
                                	JOptionPane.showMessageDialog(novaJanela, "Não foi possivel editar");
                                	novaJanela.dispose();
                                }else {
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

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Nome:"));
        JTextField nomeTextField = new JTextField();
        panel.add(nomeTextField);

        panel.add(new JLabel("CPF:"));
        JTextField cpfTextField = new JTextField();
        panel.add(cpfTextField);

        panel.add(new JLabel("Ficha Corporal:"));
        JTextField fichaTextField = new JTextField();
        panel.add(fichaTextField);

        panel.add(new JLabel("Endereço:"));
        JTextField enderecoTextField = new JTextField();
        panel.add(enderecoTextField);

        panel.add(new JLabel("Cliente Premium (S/N):"));
        JTextField premiumTextField = new JTextField();
        panel.add(premiumTextField);

        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	String nome = nomeTextField.getText();
                String cpf = cpfTextField.getText();
                String fichaCorporal = fichaTextField.getText();
                String endereco = enderecoTextField.getText();

                

                JOptionPane.showMessageDialog(null, "Cliente cadastrado!");
                telaAdicionar.dispose();
                displayClientData();
            }
        });
        panel.add(cadastrarButton);

        telaAdicionar.add(panel);
        telaAdicionar.setVisible(true);
    }

    private void abrirTelaRemover() {
        JFrame telaRemover = new JFrame("Remover Cliente");
        telaRemover.setSize(300, 150);
        telaRemover.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Nome do Cliente:"));
        JTextField nomeClienteTextField = new JTextField();
        panel.add(nomeClienteTextField);

        panel.add(new JLabel("CPF do Cliente:"));
        JTextField cpfClienteTextField = new JTextField();
        panel.add(cpfClienteTextField);

        JButton removerButton = new JButton("Remover");
        removerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String nomeCliente = nomeClienteTextField.getText();
                String cpfCliente = cpfClienteTextField.getText();

               
                Cliente clienteToRemove = findCliente(nomeCliente, cpfCliente);

                
                if (clienteToRemove != null) {
                    clienteList.remove(clienteToRemove);
                    JOptionPane.showMessageDialog(null, "Cliente removido!");
                } else {
                    
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
                }

               
                telaRemover.dispose();

               
                displayClientData();
            }
        });
        panel.add(removerButton);

        telaRemover.add(panel);
        telaRemover.setVisible(true);
    }
    //Ajuda na busca por cliente
    private Cliente findCliente(String nome, String cpf) {
        for (Cliente cliente : clienteList) {
            if (cliente.getNome().equals(nome) && cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }
        return null; 
    }
    

    private void abrirTelaEditar() {
        JFrame telaEditar = new JFrame("Editar Cliente");
        telaEditar.setSize(400, 300);
        telaEditar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2));
        panel.add(new JLabel("Nome do Cliente:"));
        JTextField nomeClienteTextField = new JTextField();
        panel.add(nomeClienteTextField);

        panel.add(new JLabel("CPF do Cliente:"));
        JTextField cpfClienteTextField = new JTextField();
        panel.add(cpfClienteTextField);

        panel.add(new JLabel("Novo Nome:"));
        JTextField novoNomeTextField = new JTextField();
        panel.add(novoNomeTextField);

        panel.add(new JLabel("Novo CPF:"));
        JTextField novoCpfTextField = new JTextField();
        panel.add(novoCpfTextField);

        panel.add(new JLabel("Nova Ficha Corporal:"));
        JTextField novaFichaTextField = new JTextField();
        panel.add(novaFichaTextField);

        panel.add(new JLabel("Novo Endereço:"));
        JTextField novoEnderecoTextField = new JTextField();
        panel.add(novoEnderecoTextField);

        panel.add(new JLabel("Cliente Premium (S/N):"));
        JTextField novoPremiumTextField = new JTextField();
        panel.add(novoPremiumTextField);

        JButton editarButton = new JButton("Editar");
        editarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	String nomeCliente = nomeClienteTextField.getText();
                String cpfCliente = cpfClienteTextField.getText();
                String novoNome = novoNomeTextField.getText();
                String novoCpf = novoCpfTextField.getText();
                String novaFicha = novaFichaTextField.getText();
                String novoEndereco = novoEnderecoTextField.getText();
                String novoPremium = novoPremiumTextField.getText();

                
                Cliente clienteE = findCliente(nomeCliente, cpfCliente);
               
                if (clienteE != null) {
                    clienteE.setNome(novoNome);
                    clienteE.setCpf(novoCpf);
                    clienteE.setFichaCorporal(novaFicha);

                    JOptionPane.showMessageDialog(null, "Cliente editado!");
                } else {        
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
                }

                telaEditar.dispose();  
                displayClientData();
            }
        });
        panel.add(editarButton);

        telaEditar.add(panel);
        telaEditar.setVisible(true);
    }
    
    private void displayClientData() {
        
        String[] columnNames = {"Nome", "CPF", "Ficha Corporal", "Endereço"};

        
        DefaultTableModel model = new DefaultTableModel(getClientDataArray(), columnNames);

        
        tabela.setModel(model);
    }

    private Object[][] getClientDataArray() {
        
        Object[][] data = new Object[clienteList.size()][5];

        for (int i = 0; i < clienteList.size(); i++) {
            Cliente cliente = clienteList.get(i);
            data[i][0] = cliente.getNome();
            data[i][1] = cliente.getCpf();
            data[i][2] = cliente.getFichaCorporal();
            data[i][3] = cliente.getEndereco();

        }

        return data;
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
        	if(cliente instanceof ClientePremium) {
        		Object[] rowData = {
            			cliente.getNome(),
            			cliente.getCpf(),
            			cliente.getFichaCorporal(),
            			cliente.getEndereco().getCep(),
            			cliente.getEndereco().getEstado(),
            			cliente.getEndereco().getCidade(),
            			cliente.getEndereco().getBairro(),
            			cliente.getEndereco().getRua(),
            			cliente.getEndereco().getNumero(),
            			"Sim",
            			((ClientePremium) cliente).getDietaPlanejada(),
            			((ClientePremium) cliente).getDesconto()
            	};
        		model.addRow(rowData);
            }
        	else if(cliente instanceof Cliente) {
            	Object[] rowData = {
            			cliente.getNome(),
            			cliente.getCpf(),
            			cliente.getFichaCorporal(),
            			cliente.getEndereco().getCep(),
            			cliente.getEndereco().getEstado(),
            			cliente.getEndereco().getCidade(),
            			cliente.getEndereco().getBairro(),
            			cliente.getEndereco().getRua(),
            			cliente.getEndereco().getNumero(),
            			"Não",
            			"-",
            			"-"                    			
            	};
            	model.addRow(rowData);
            }
        }
        tabelaEscolhida.setModel(model);
    }
    
    
}





