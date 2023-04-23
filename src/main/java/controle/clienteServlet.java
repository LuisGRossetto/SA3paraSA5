package controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.clienteDAO;
import modelo.cliente;

/**
 * Este servlet atua como um controlador de página da aplicação, atendendo as
 * solicitações do usuário.
 *
 */

@WebServlet("/")
public class clienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private clienteDAO clienteDAO;

	public void init() {
		clienteDAO = new clienteDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acao = request.getServletPath();

		try {
			switch (acao) {
			case "/novo":
				exibirFormularioInclusao(request, response);
				break;
			case "/inserir":
				inserircliente(request, response);
				break;
			case "/deletar":
				deletarcliente(request, response);
				break;
			case "/editar":
				exibirFormularioEdicao(request, response);
				break;
			case "/atualizar":
				atualizarcliente(request, response);
				break;
			default:
				listarcliente(request, response);
				break;
			}
		} catch (SQLException erro) {
			throw new ServletException(erro);
		}
	}

	private void listarcliente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List listarcliente = clienteDAO.selecionarclientes();
		request.setAttribute("listarcliente", listarcliente);
		RequestDispatcher renderizar = request.getRequestDispatcher("listagem-cliente.jsp");
		renderizar.forward(request, response);
	}

	private void exibirFormularioInclusao(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher renderizar = request.getRequestDispatcher("formulario-cliente.jsp");
		renderizar.forward(request, response);
	}

	private void exibirFormularioEdicao(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int matricula = Integer.parseInt(request.getParameter("matricula"));
		cliente selecaocliente = clienteDAO.selecionarcliente(matricula);
		RequestDispatcher renderizar = request.getRequestDispatcher("formulario-cliente.jsp");
		request.setAttribute("cliente", selecaocliente);
		renderizar.forward(request, response);
	}

	private void inserircliente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String modalidade = request.getParameter("modalidade");
		cliente adicaocliente = new cliente(nome, endereco, modalidade);
		clienteDAO.inserircliente(adicaocliente);
		response.sendRedirect("listagem");
	}

	private void atualizarcliente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int matricula = Integer.parseInt(request.getParameter("matricula"));
		String nome = request.getParameter("nome");
		String endereco = request.getParameter("endereco");
		String modalidade = request.getParameter("modalidade");
		cliente atualizacaocliente = new cliente(matricula, nome, endereco, modalidade);
		clienteDAO.atualizarcliente(atualizacaocliente);
		response.sendRedirect("listagem");
	}

	private void deletarcliente(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int matricula = Integer.parseInt(request.getParameter("matricula"));
		clienteDAO.deletarcliente(matricula);
		response.sendRedirect("listagem");
	}

}
