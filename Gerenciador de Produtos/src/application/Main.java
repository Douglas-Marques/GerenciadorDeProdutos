package application;	

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Main extends Application {	
	Scene scene3;
	Estoque es;
	Stage janela;
	boolean retorno = false;
	TableView<Produto> tabela;
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage primaryStage) throws IOException {		
		janela = primaryStage;
		primaryStage.setTitle("Gerenciador de produtos");
		primaryStage.show();
		primaryStage.setOnCloseRequest(e -> {
			e.consume();
			fechamento();
		});
		
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Scene scene = new Scene(grid, 1920, 1080);
		primaryStage.setScene(scene);
		
		Text scenetitle = new Text("Bem-vindo!");
		scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid.add(scenetitle, 0, 0, 2, 1);

		Label userName = new Label("Login:");
		grid.add(userName, 0, 1);

		TextField userTextField = new TextField();
		userTextField.setPromptText("Nome ou login");
		grid.add(userTextField, 1, 1);
		
		Label pw = new Label("Senha:");
		grid.add(pw, 0, 2);

		PasswordField pwBox = new PasswordField();
		pwBox.setPromptText("Senha");
		grid.add(pwBox, 1, 2);
		
		primaryStage.setTitle("Gerenciador de Produtos - Inicio");
		primaryStage.show();
		
		Button btn = new Button("Entrar");
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 0, 4);
		
		Button btn2 = new Button("Criar cadastro");
		HBox hbBtn2 = new HBox(10);
		hbBtn2.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn2.getChildren().add(btn2);
		grid.add(hbBtn2, 1, 4);
		
		final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);  
		
		GridPane grid2 = new GridPane();
		grid2.setAlignment(Pos.CENTER);
		grid2.setHgap(10);
		grid2.setVgap(10);
		grid2.setPadding(new Insets(25, 25, 25, 25));
		
		Scene scene2 = new Scene(grid2, 1920, 1080);
		
		Text scenetitle2 = new Text("Preencha o formulário:");
		scenetitle2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid2.add(scenetitle2, 0, 0, 2, 1);

		Label userName2 = new Label("Nome completo:");
		grid2.add(userName2, 0, 1);

		TextField userTextFieldName = new TextField();
		userTextFieldName.setPromptText("Digite seu nome");
		grid2.add(userTextFieldName, 1, 1);
		
		Label userLogin = new Label("Crie seu login:");
		grid2.add(userLogin, 0, 2);

		TextField userTextFieldLogin = new TextField();
		userTextFieldLogin.setPromptText("Crie um login");
		grid2.add(userTextFieldLogin, 1, 2);
		
		Label userPassword = new Label("Senha:");
		grid2.add(userPassword, 0, 3);

		PasswordField pwBox2 = new PasswordField();
		pwBox2.setPromptText("Crie uma senha");
		grid2.add(pwBox2, 1, 3);
		
		Button btn3 = new Button("Voltar");
		HBox hbBtn3 = new HBox(10);
		hbBtn3.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn3.getChildren().add(btn3);
		grid2.add(hbBtn3, 0, 4);
		
		Button btn4 = new Button("Enviar");
		HBox hbBtn4 = new HBox(10);
		hbBtn4.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn4.getChildren().add(btn4);
		grid2.add(hbBtn4, 1, 4);
		
		final Text actiontarget2 = new Text();
        grid2.add(actiontarget2, 1, 6);
        
        GridPane grid3 = new GridPane();
        grid3.setAlignment(Pos.CENTER);
        grid3.setHgap(10);
        grid3.setVgap(10);
        grid3.setPadding(new Insets(25, 25, 25, 25));   
        
        es = new Estoque(new File("produtos.txt"));
        
        es.preencheEstoque();
               
        List<Produto> lista = Arrays.asList(es.imprimeEstoque());
        
        tabela = new TableView<>();
        
        tabela.setEditable(true);
        
        TableColumn<Produto, String> colunaNome = new TableColumn<>("Nome");
        TableColumn<Produto, String> colunaPreco = new TableColumn<>("Preco");
        TableColumn<Produto, String> colunaTipo = new TableColumn<>("Tipo");
        
        colunaNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colunaPreco.setCellValueFactory(new PropertyValueFactory<>("preco"));
        colunaTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        
        colunaNome.setCellFactory(TextFieldTableCell.forTableColumn());
        
        colunaNome.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Produto, String>>() {
			@Override
			public void handle(CellEditEvent<Produto, String> e) {				
				String[] salvar = new String[5];
				try {
					String salvo = excluirProduto(e.getOldValue().trim());
					salvar = salvo.trim().split("\\*");
					if(salvar[3].trim().equalsIgnoreCase("Alimento"))
						adicionarProduto(new Alimento(Double.parseDouble(salvar[2].trim()), salvar[1].trim(), salvar[3].trim(), e.getNewValue().trim(), salvar[4].trim()));
					else if(salvar[3].trim().equalsIgnoreCase("Eletrodomestico"))
						adicionarProduto(new Eletrodomestico(Double.parseDouble(salvar[2].trim()), salvar[1].trim(), salvar[3].trim(), e.getNewValue().trim(), (Double.parseDouble(salvar[4].trim()))));
					else if(salvar[3].equalsIgnoreCase("Vestuario"))
						adicionarProduto(new Vestuario(Double.parseDouble(salvar[2].trim()), salvar[1].trim(), salvar[3].trim(), e.getNewValue().trim(), (Integer.parseInt(salvar[4].trim()))));
				} catch (IOException e1) {
				}
			}
        });
        
        tabela.setItems(FXCollections.observableArrayList(lista));        
        tabela.getColumns().addAll(colunaTipo, colunaNome, colunaPreco); 
        tabela.setPrefWidth(350);
        tabela.setPrefHeight(500);
        
        grid3.add(tabela, 1, 1);
        
        Text scenetitle3 = new Text("Produtos: ");
        scenetitle3.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
		grid3.add(scenetitle3, 0, 0, 2, 1);
		
		Button delete = new Button("Excluir");
		Button adicionar = new Button("Adicionar");
		HBox topoCena3 = new HBox(10);
		topoCena3.setAlignment(Pos.TOP_RIGHT);
		topoCena3.getChildren().add(delete);
		topoCena3.getChildren().add(adicionar);
        grid3.add(topoCena3, 1, 0);      
		
		GridPane grid5 = new GridPane();
        grid5.setAlignment(Pos.TOP_RIGHT);
        grid5.setHgap(10);
        grid5.setVgap(10);
        grid5.setPadding(new Insets(25, 25, 25, 25));
        grid5.setStyle("-fx-background-color: lightgray;");
        
        TextField pesquisa = new TextField();
        pesquisa.setPromptText("Pesquisar");
        grid3.add(pesquisa, 1, 2);
        
        Button pesquisarAcao = new Button("Pesquisar");
        HBox hbPesquisarAcao = new HBox(10);
        hbPesquisarAcao.setAlignment(Pos.BOTTOM_CENTER);
        hbPesquisarAcao.getChildren().add(pesquisarAcao);
        grid3.add(hbPesquisarAcao, 1, 3);
        
        final Text actionTarget3 = new Text();
        grid5.add(actionTarget3, 1, 0);
        
        Label usuario = new Label("Olá, " + actionTarget3.getText().trim());
        grid5.add(usuario, 0, 0);
        
        Button btn5 = new Button("Sair");
		HBox hbBtn5 = new HBox(10);
		hbBtn5.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn5.getChildren().add(btn5);
        grid5.add(hbBtn5, 1, 1);  
        
        GridPane grid8 = new GridPane();
        grid8.setAlignment(Pos.TOP_CENTER);
        grid8.setHgap(10);
        grid8.setVgap(10);
        grid8.setPadding(new Insets(25, 25, 25, 25));
        grid8.setStyle("-fx-background-color: lightgray;");
        
        Label direitos = new Label("Software desenvolvido por Douglas Marques ®");        
        grid8.add(direitos, 0, 0);
        BorderPane borda = new BorderPane();
        borda.setTop(grid5);
        borda.setCenter(grid3);
        borda.setBottom(grid8);
        
        final Text actiontarget4 = new Text();
        grid3.add(actiontarget4, 1, 4);  
        
        scene3 = new Scene(borda, 1920, 1080);
	
        btn.setOnAction(new EventHandler<ActionEvent>() {     
            @Override
            public void handle(ActionEvent e) {
            	String user = userTextField.getText().trim();
            	String pas = pwBox.getText().trim();
            	actionTarget3.setText(user);
            	actionTarget3.setFill(Color.BLUE);
            	try {
            		if(user.equalsIgnoreCase("")) {            			
            			if(pas.equalsIgnoreCase("")) { 	
            				actiontarget.setFill(Color.FIREBRICK);
            				actiontarget.setText("Login e/ou senha estão vazios");             			
            			}
            		}
            		else {
            			boolean teste = false;
            			Scanner s = new Scanner(new FileInputStream("Usuario.txt"));
            			do {
            				String line = s.nextLine();
            				line = s.nextLine();
            				String var[] = line.split("\\*");
            				String nome = var[0].trim();
            				String login = var[1].trim();
            				String senha = var[2].trim();
            				if(login.equalsIgnoreCase(user) || nome.equalsIgnoreCase(user))
            					if(senha.equalsIgnoreCase(pas)) {
            						teste = true;
            						break;
            					}
            			} while(s.hasNext());
            			s.close();
            			if(teste == true) {
            				actiontarget.setFill(Color.BLUE);
            				actiontarget.setText(user + " entrou em nosso sistema");
            				primaryStage.setScene(scene3);
            			}
            			else {
            				throw new LoginException();            				
            			}
            		}
			} catch (FileNotFoundException e1) {
				actiontarget.setFill(Color.FIREBRICK);
				actiontarget.setText("Não foi possível conectar ao nosso banco de dados!");
			} catch (ArrayIndexOutOfBoundsException a) {
				actiontarget.setFill(Color.FIREBRICK);
				actiontarget.setText("Erro desconhecido!");
			} catch (NoSuchElementException n) {
				actiontarget.setFill(Color.FIREBRICK);
				actiontarget.setText("Usuário não existe!");
			} catch(LoginException l) {
				actiontarget.setFill(Color.FIREBRICK);
				actiontarget.setText("Usuário não existe!");
			}
            }            
        });
	
		btn2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				primaryStage.setScene(scene2);
				userTextField.setText("");
				pwBox.setText("");
				actiontarget.setText("");
			}
	    });
		
		btn3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				primaryStage.setScene(scene);
				actiontarget2.setText("");
			}
		});
		
		btn4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String nome = userTextFieldName.getText().trim();
				String login = userTextFieldLogin.getText().trim();
				String senha = pwBox2.getText().trim();				
				if(nome.equalsIgnoreCase("") || login.equalsIgnoreCase("") || senha.equalsIgnoreCase("")) {
					actiontarget2.setFill(Color.FIREBRICK);
	                actiontarget2.setText("Algum campo está vazio!");
				}
				else {
					try {
						boolean teste = true;						
						Scanner s = new Scanner(new FileInputStream("Usuario.txt"));	
						loop:
							do {
								String line = s.nextLine(); 
								if(s.nextLine() == null) {
									teste = false;
									break loop;
								}
								line = s.nextLine();
								String var[] = line.split("\\*");
								String login2 = var[1].trim();
								if(login2.equalsIgnoreCase(login)) {
									teste = false;
									break loop;
								}
							} while(s.hasNext());
						s.close();
						if(teste == true) {
							new User(nome, login, senha);
							actiontarget2.setFill(Color.BLUE);
							actiontarget2.setText("Usuário criado com sucesso!");
						}
						else {
							actiontarget2.setFill(Color.FIREBRICK);
							actiontarget2.setText("Usuário já existe!");
						}
					} catch (FileNotFoundException e1) {
						new User(nome, login, senha);
						actiontarget2.setFill(Color.BLUE);
						actiontarget2.setText("Usuário criado com sucesso!");
					} catch (NoSuchElementException n) {
						new User(nome, login, senha);
						actiontarget2.setFill(Color.BLUE);
						actiontarget2.setText("Usuário criado com sucesso!");
					}
					userTextFieldName.setText(null);
					userTextFieldLogin.setText(null);
					pwBox2.setText(null);
				}
			}
		});
		
		btn5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				pesquisa.setText("");
				primaryStage.setScene(scene);
				userTextField.setText("");
				pwBox.setText("");
				actiontarget.setText("Até mais!");
			}
		}
			);
		
		delete.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {					
				ObservableList<Produto> produtoSelecionado, todosOsProdutos;
				todosOsProdutos = tabela.getItems();
				produtoSelecionado = tabela.getSelectionModel().getSelectedItems();	
				try {
					if(produtoSelecionado == null) {
						actiontarget4.setFill(Color.FIREBRICK);
						actiontarget4.setText("Nenhum item selecionado!");
					} else
						excluirProduto(tabela.getSelectionModel().getSelectedItems().toString());
				} catch (IOException e1) {
				}
				produtoSelecionado.forEach(todosOsProdutos::remove);
			}
		});
		
		pesquisarAcao.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				String pesquisar = pesquisa.getText().trim();
					if(pesquisar.equalsIgnoreCase("")) {
						actiontarget4.setFill(Color.FIREBRICK);
						actiontarget4.setText("Campo de busca vazio!");
					} else {
						Produto retorno = pesquisa(pesquisar, es.imprimeEstoque());
						if(retorno instanceof Produto) {
							pesquisa.setText("");
							telaPesquisa(retorno);
							actiontarget4.setText("");
						} else {
							actiontarget4.setFill(Color.FIREBRICK);
							actiontarget4.setText("Não foi possível encontrar esse item em nosso estoque!");
					}
				}
			}
		}
		);
		
		adicionar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				pesquisa.setText("");
				telaAdicionaProduto();
			}			
		});
	}
	
	public String excluirProduto(String pro) throws IOException {
		File f = new File("produtos.txt");  
        FileReader reader = new FileReader(f);  
        BufferedReader leitor = new BufferedReader(reader);  
        FileWriter writer = new FileWriter(f, true);  
        PrintWriter escritor = new PrintWriter(writer);  
  
        String linha;  
        String factors[];  
        String salvo = null;
        
        @SuppressWarnings("resource")
		LineNumberReader linhaLeitura = new LineNumberReader(new FileReader(f));
		linhaLeitura.skip(f.length());
		int q = linhaLeitura.getLineNumber();
        String[] reforco = new String[q];        
        for(int i = 0; i < reforco.length; i++) {       	
	        linha = leitor.readLine();  
	    	if(linha.equalsIgnoreCase("")) {
	    		break;
	    	}
	    	else {
		        factors = linha.split("\\*");  
		        if(!factors[0].trim().equalsIgnoreCase(pro.trim())) { 
		        	reforco[i] = linha;  
		        }  
		        else {
		        	salvo = linha;
		        }
	    	}
        }
         
        reader.close();  
        leitor.close(); 
        
        FileWriter apaga = new FileWriter(f);
        apaga.close();
        
        for(int i = 0; i < reforco.length; i++) {
        	if(reforco[i] == null) {
        		reforco[i] = "";
        	} else
        		escritor.println(reforco[i]);        				
        }
        
        writer.close();
        escritor.close();
		return salvo; 
	}
	
	public void telaAdicionaProduto() {
		Stage janela3 = new Stage();
		janela3.initModality(Modality.APPLICATION_MODAL);
		janela3.setTitle("Gerenciador de Produtos - Adiciona");
		
		DatePicker checkInDatePicker = new DatePicker();
		checkInDatePicker.setValue(LocalDate.now());
		
		TextField produtoIpi = new TextField();
		produtoIpi.setPromptText("Digite o IPI do produto");
		
		TextField produtoQuantidade = new TextField();
		produtoQuantidade.setPromptText("Digite a quantidade de produtos");
		
		GridPane grid7 = new GridPane();
		grid7.setAlignment(Pos.CENTER);
		grid7.setHgap(10);
        grid7.setVgap(10);
        grid7.setPadding(new Insets(25, 25, 25, 25));
        grid7.setStyle("-fx-background-color: lightgray;");
        
        Text scenetitle4 = new Text("Adicionar produto: ");
        scenetitle4.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid7.add(scenetitle4, 0, 0, 2, 1);
		
		TextField produtoNome = new TextField();
		produtoNome.setPromptText("Nome do produto");
		grid7.add(produtoNome, 0, 1);
		
		Label l2 = new Label("Adicione alguma descrição:");
		grid7.add(l2, 0, 2);
		
		TextArea produtoDescricao = new TextArea();
		produtoDescricao.setPromptText("Adicione aqui alguma informação relevante sobre o produto");
		grid7.add(produtoDescricao, 0, 3);
		
		Label l3 = new Label("Digite o preco(R$): ");
		grid7.add(l3, 0, 4);
		
		TextField produtoPreco = new TextField();
		produtoPreco.setPromptText("Digite o preco do produto");
		produtoPreco.setMaxWidth(150);
		produtoPreco.setText("0");
		grid7.add(produtoPreco, 0, 5);
		
		ComboBox<String> produtoTipo = new ComboBox<>();
		produtoTipo.getItems().addAll("Alimento", "Eletrodomestico", "Vestuario");
		produtoTipo.setPromptText("Tipo deproduto");
		produtoTipo.setOnAction(e -> {
			if(produtoTipo.getValue().equalsIgnoreCase("Alimento")) {
				grid7.getChildren().removeAll(produtoIpi, produtoQuantidade);
				grid7.add(checkInDatePicker, 4, 2);
			}
			else if(produtoTipo.getValue().equalsIgnoreCase("Eletrodomestico")) {
				grid7.getChildren().removeAll(checkInDatePicker, produtoQuantidade);
				grid7.add(produtoIpi, 4, 2);
			}
			else if(produtoTipo.getValue().equalsIgnoreCase("Vestuario")) {
				grid7.getChildren().removeAll(checkInDatePicker, produtoIpi);
				grid7.add(produtoQuantidade, 4, 2);
			}
		});
		
		grid7.add(produtoTipo, 4, 1);
		
		Button cancel = new Button("Cancelar");
		Button accept = new Button("Salvar");
		
		HBox buttons = new HBox(10);
		buttons.setAlignment(Pos.BOTTOM_RIGHT);
		buttons.getChildren().addAll(cancel, accept);
		grid7.add(buttons, 4, 5);
		
		final Text actiontarget5 = new Text();
        grid7.add(actiontarget5, 4, 3);  
		
		cancel.setOnAction(e -> {
			janela.setScene(scene3);
        	janela3.close();
		});
		
		accept.setOnAction(e -> {		
			if(produtoNome.getText().isEmpty() || produtoDescricao.getText().isEmpty() || produtoPreco.getText().isEmpty() || produtoTipo.getValue().toString().isEmpty()) {
				actiontarget5.setFill(Color.FIREBRICK);
				actiontarget5.setText("Algum campo está vazio!");
			} else {		
				String nome = produtoNome.getText().trim();
				String descricao = produtoDescricao.getText().trim();
				Double preco = Double.parseDouble(produtoPreco.getText().trim());
				String tipo = produtoTipo.getValue().toString().trim();				
				if(tipo.equalsIgnoreCase("Alimento")) {
					if(checkInDatePicker.getValue().toString().isEmpty()) {
						actiontarget5.setFill(Color.FIREBRICK);
						actiontarget5.setText("A data está vazia!");
					} else {
						String data = checkInDatePicker.getValue().toString().trim();
						boolean retorno = adicionarProduto(new Alimento(preco, descricao, tipo, nome, data));
						if(retorno) {
							tabela.getItems().add(new Alimento(preco, descricao, tipo, nome, data));
							actiontarget5.setFill(Color.BLUE);
							actiontarget5.setText("Produto adicionado com sucesso!");
						}
						else {
							actiontarget5.setFill(Color.FIREBRICK);
							actiontarget5.setText("Não foi possível adicionar esse produto!");
						}
					}
				}
				else if(tipo.equalsIgnoreCase("Eletrodomestico")) {
					if(produtoIpi.getText().isEmpty()){ 
						actiontarget5.setFill(Color.FIREBRICK);
						actiontarget5.setText("IPI está vazio!");
					} else {
						Double ipi = Double.parseDouble(produtoIpi.getText().trim());
						boolean retorno = adicionarProduto(new Eletrodomestico(preco, descricao, tipo, nome, ipi));
						if(retorno) {
							tabela.getItems().add(new Eletrodomestico(preco, descricao, tipo, nome, ipi));
							actiontarget5.setFill(Color.BLUE);
							actiontarget5.setText("Produto adicionado com sucesso!");
						}
						else {
							actiontarget5.setFill(Color.FIREBRICK);
							actiontarget5.setText("Não foi possível adicionar esse produto!");
							tabela.getItems();
						}
					}
				}
				else if(tipo.equalsIgnoreCase("Vestuario")) {
					if(produtoQuantidade.getText().isEmpty()) {
						actiontarget5.setFill(Color.FIREBRICK);
						actiontarget5.setText("A quantidade de produtos está vazio!");
					} else {
						int quantProd = Integer.parseInt(produtoQuantidade.getText().trim());
						boolean retorno = adicionarProduto(new Vestuario(preco, descricao, tipo, nome, quantProd));
						if(retorno) {
							tabela.getItems().add(new Vestuario(preco, descricao, tipo, nome, quantProd));
							actiontarget5.setFill(Color.BLUE);
							actiontarget5.setText("Produto adicionado com sucesso!");
						}
						else {
							actiontarget5.setFill(Color.FIREBRICK);
							actiontarget5.setText("Não foi possível adicionar esse produto!");
						}
					}
				}
			}
		});
		
		Scene scene6 = new Scene(grid7);
		janela3.setScene(scene6);
		janela3.showAndWait();
	}
	
	public boolean adicionarProduto(Produto p) {
		try {
			FileOutputStream out = new FileOutputStream(new File("produtos.txt"), true);
			BufferedOutputStream buff = new BufferedOutputStream(out);
			PrintStream ps = new PrintStream(buff);
			if(p instanceof Alimento) {
				ps.println(p.getNome() + " * " + p.getDescricao() + " * " + p.getPreco() + " * " + p.getTipo() + " * "+ ((Alimento)p).getValidade());
				ps.flush();
				ps.close();
				return true;
			}
			else if(p instanceof Eletrodomestico) {
				ps.println(p.getNome() + " * " + p.getDescricao() + " * " + p.getPreco() + " * " + p.getTipo() + " * " + ((Eletrodomestico)p).getIpi());
				ps.flush();
				ps.close();
				return true;
			}
			else if(p instanceof Vestuario) {
				ps.println(p.getNome() + " * " + p.getDescricao() + " * " + p.getPreco() + " * " + p.getTipo() + " * " + ((Vestuario)p).getQuantEstoque());
				ps.flush();
				ps.close();
				return true;
			}
			ps.flush();
			ps.close();
		} catch (IOException e) {
		}
		return false;
	}
	
	public void telaPesquisa(Produto p) {
		Stage janela2 = new Stage();
		janela2.initModality(Modality.APPLICATION_MODAL);
		janela2.setTitle("Gerenciador de Produto - Busca");
		
		GridPane grid6 = new GridPane();
        grid6.setAlignment(Pos.CENTER);
        grid6.setHgap(10);
        grid6.setVgap(10);
        grid6.setPadding(new Insets(25, 25, 25, 25));
        grid6.setStyle("-fx-background-color: lightgray;");
        
        Label l1 = new Label("Produto: " + p.getNome() + "\nTipo: " + p.getTipo() + "\nPreço: " + Double.toString(p.getPreco()) + "\nDescricao: " + p.getDescricao());
        
        if(p instanceof Alimento) {
        	Label l2 = new Label("Validade: " + ((Alimento)p).getValidade());
        	grid6.add(l2, 0, 1);
        }
        else if(p instanceof Eletrodomestico) {
        	Label l2 = new Label("IPI: " + Double.toString(((Eletrodomestico)p).getIpi()));
        	grid6.add(l2, 0, 1);
        }
        else if(p instanceof Vestuario) {
        	Label l2 = new Label("Quantidade em estoque: " + Integer.toString(((Vestuario)p).getQuantEstoque()));
        	grid6.add(l2, 0, 1);
        }
        
        grid6.add(l1, 0, 0);        
        
        Button voltar = new Button("Voltar");
        HBox hbVoltar = new HBox(10);
        hbVoltar.setAlignment(Pos.BOTTOM_CENTER);
        hbVoltar.getChildren().add(voltar);
        grid6.add(hbVoltar, 1, 2);
        
        voltar.setOnAction(e -> {
        	janela.setScene(scene3);
        	janela2.close();
        	
        });
        
        Scene scene5 = new Scene(grid6);
        janela2.setScene(scene5);
        janela2.showAndWait();
	}
	
	public void fechamento() {
		boolean resposta = popUpConfirmWindow("Gerenciador de Produtos - Saida", "Tem certeza que deseja fechar o programa?");		
		if(resposta)
			janela.close();
	}
	
	public Produto pesquisa(String p, Produto[] ps) { 
		Produto retorno = null;
        for(int i = 0; i < ps.length-1; i++) {
        	if(ps[i].getNome().equalsIgnoreCase(p))
        		retorno = ps[i];
        }
        return retorno;
	}
	
	public boolean popUpConfirmWindow(String titulo, String mensagem) {		
		Stage janela = new Stage();
		janela.initModality(Modality.APPLICATION_MODAL);
		janela.setTitle(titulo);
		
		GridPane grid4 = new GridPane();
        grid4.setAlignment(Pos.CENTER);
        grid4.setHgap(10);
        grid4.setVgap(10);
        grid4.setPadding(new Insets(25, 25, 25, 25));
        grid4.setStyle("-fx-background-color: lightgray;");
		
		Label l = new Label();
		l.setText(mensagem);
		grid4.add(l, 1, 0);
		
		Button confirma = new Button("Sair");
		HBox hbConfirma = new HBox(10);
		hbConfirma.setAlignment(Pos.BOTTOM_LEFT);
		hbConfirma.getChildren().add(confirma);
		grid4.add(hbConfirma, 0, 4);
		
		Button cancela = new Button("Cancelar");
		HBox hbCancela = new HBox(10);
		hbCancela.setAlignment(Pos.BOTTOM_RIGHT);
		hbCancela.getChildren().add(cancela);
		grid4.add(hbCancela, 1, 4);
		
		confirma.setOnAction(e -> {
			retorno = true;
			janela.close();
		});
		
		cancela.setOnAction(e -> {
			retorno = false;
			janela.close();
		});
		
		Scene scene4 = new Scene(grid4);
		janela.setScene(scene4);
		janela.showAndWait();		
		
		return retorno;
	}

	public static void main(String[] args) {
		launch(args);
	}
}