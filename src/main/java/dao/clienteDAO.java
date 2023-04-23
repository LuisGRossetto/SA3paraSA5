package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.cliente;

/**
 *
 * Esta classe DAO permite operações de inserção, leitura, atualização e
 * exclusão na tabela de 'cliente' do banco de dados.
 *
 */

public class clienteDAO {
	// URL de conexão do banco de dados 'jdbc_servlet' do SGBD MySQL
	private String jdbcURL = "jdbc:mysql://localhost/jdbc_servlet?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	// Nome de usuário 'root' para acesso ao banco de dados do SGBD MySQL
	private String jdbcNomecliente = "root";
	// Senha do usuário 'root' para acesso ao banco de dados do SGBD MySQL
	private String jdbcSenha = "20582299";

	private static final String INSERIR_cliente = "INSERT INTO cliente" + " (nome, endereco, modalidade) VALUES "
			+ " (?, ?, ?);";
	private static final String SELECIONAR_cliente = "SELECT matricula, nome, endereco, modalidade FROM cliente WHERE matricula = ?";
	private static final String SELECIONAR_clienteS = "SELECT * FROM cliente";
	private static final String DELETAR_cliente = "DELETE FROM cliente WHERE matricula = ?;";
	private static final String ATUALIZAR_cliente = "UPDATE cliente SET nome = ?, endereco = ? , modalidade = ? WHERE matricula = ?;";

	public clienteDAO() {
	}

	protected Connection getConnection() {
		Connection conexao = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection(jdbcURL, jdbcNomecliente, jdbcSenha);
		} catch (SQLException erro) {
			erro.printStackTrace();
		} catch (ClassNotFoundException erro) {
			erro.printStackTrace();
		}
		return conexao;
	}

	public void inserircliente(cliente cliente) throws SQLException {
		// Fecha automaticamente a conexão após o uso
		try (Connection conexao = getConnection();
				PreparedStatement executarComando = conexao.prepareStatement(INSERIR_cliente)) {
			// O matricula do usuário é omitido do comando, pois foi definido na tabela como
			// autoincremento
			executarComando.setString(1, cliente.getNome());
			executarComando.setString(2, cliente.getendereco());
			executarComando.setString(3, cliente.getmodalidade());
			System.out.println(executarComando);
			executarComando.executeUpdate();
		} catch (SQLException erro) {
			printSQLException(erro);
		}
	}

	public cliente selecionarcliente(int matricula) {
		cliente cliente = null;
		// Etapa 1: estabelece a conexão
		try (Connection conexao = getConnection();
				// Etapa 2: cria o comando da instrução usando o objeto da conexão
				PreparedStatement executarComando = conexao.prepareStatement(SELECIONAR_cliente);) {
			executarComando.setInt(1, matricula);
			System.out.println(executarComando);
			// Etapa 3: executa ou atualiza a query
			ResultSet resultado = executarComando.executeQuery();
			// Etapa 4: processa o objeto ResultSet
			while (resultado.next()) {
				String nome = resultado.getString("nome");
				String endereco = resultado.getString("endereco");
				String modalidade = resultado.getString("modalidade");
				cliente = new cliente(matricula, nome, endereco, modalidade);
			}
		} catch (SQLException erro) {
			printSQLException(erro);
		}
		return cliente;
	}

	public List selecionarclientes() {
		List clientes = new ArrayList<>();
		// Código boilerplate
		// Etapa 1: estabelece a conexão
		try (Connection conexao = getConnection();
				// Etapa 2: cria o comando da instrução usando o objeto da conexão
				PreparedStatement executarComando = conexao.prepareStatement(SELECIONAR_clienteS);) {
			System.out.println(executarComando);
			// Etapa 3: executa ou atualiza a query
			ResultSet resultado = executarComando.executeQuery();
			// Etapa 4: processa o objeto ResultSet
			while (resultado.next()) {
				int matricula = resultado.getInt("matricula");
				String nome = resultado.getString("nome");
				String endereco = resultado.getString("endereco");
				String modalidade = resultado.getString("modalidade");
				clientes.add(new cliente(matricula, nome, endereco, modalidade));
			}
		} catch (SQLException erro) {
			printSQLException(erro);
		}
		return clientes;
	}

	public boolean deletarcliente(int matricula) throws SQLException {
		boolean registroDeletado;
		try (Connection conexao = getConnection();
				PreparedStatement executarComando = conexao.prepareStatement(DELETAR_cliente);) {
			executarComando.setInt(1, matricula);
			System.out.println(executarComando);
			registroDeletado = executarComando.executeUpdate() > 0;
		}
		return registroDeletado;
	}

	public boolean atualizarcliente(cliente cliente) throws SQLException {
		boolean registroAtualizado;
		try (Connection connection = getConnection();
				PreparedStatement executarComando = connection.prepareStatement(ATUALIZAR_cliente);) {
			executarComando.setString(1, cliente.getNome());
			executarComando.setString(2, cliente.getendereco());
			executarComando.setString(3, cliente.getmodalidade());
			executarComando.setInt(4, cliente.getmatricula());
			registroAtualizado = executarComando.executeUpdate() > 0;
		}
		return registroAtualizado;
	}

	private void printSQLException(SQLException erro) {
		for (Throwable e : erro) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("Estado do SQL: " + ((SQLException) e).getSQLState());
				System.err.println("Código de erro: " + ((SQLException) e).getErrorCode());
				System.err.println("Mensagem: " + e.getMessage());
				Throwable causa = erro.getCause();
				while (causa != null) {
					System.out.println("Causa: " + causa);
					causa = causa.getCause();
				}
			}
		}
	}
}