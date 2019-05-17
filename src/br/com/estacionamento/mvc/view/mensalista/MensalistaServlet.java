package br.com.estacionamento.mvc.view.mensalista;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import br.com.estacionamento.mvc.crud.CRUDEstado;
import br.com.estacionamento.mvc.crud.CRUDMensalista;
import br.com.estacionamento.mvc.model.persistent_object.POEstado;
import br.com.estacionamento.mvc.model.persistent_object.POMensalista;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

/**
 * Servlet implementation class ListarEstadoServlet
 */
@WebServlet("/MensalistaServlet")
public class MensalistaServlet extends HttpServlet {
       
    public MensalistaServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "";
		if(!request.getParameter("id-mensalista").equals("0")){
			sql = "SELECT o FROM POMensalista o WHERE o.idMensalista = " + request.getParameter("id-mensalista");
		}
		
		CRUDMensalista crud = new CRUDMensalista();
		ArrayList<POMensalista> result = crud.select(sql);
		JSONArray arr = new JSONArray();
		if(result.size() > 0){
			for(POMensalista pom : result){
				try {
					arr.put(pom.toJSON());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(arr.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CRUDMensalista crud = new CRUDMensalista();
		POMensalista mensalista = new POMensalista();
		
		System.out.println(request.getParameter("id-mensalista"));
		
		mensalista.setIdMensalista(Integer.parseInt(request.getParameter("id-mensalista")));
		mensalista.setNomeMensalista(request.getParameter("nome-mensalista"));
		mensalista.setCpfMensalista(request.getParameter("cpf-mensalista"));
		switch (request.getParameter("status-mensalista")) {
			case "ativo":
				mensalista.setStatusMensalista(EnumStatus.ATIVO);
				break;
	
			case "inativo":
				mensalista.setStatusMensalista(EnumStatus.INATIVO);
				break;
				
			default:
				mensalista.setStatusMensalista(EnumStatus.INATIVO);
				break;
		}
		crud.update(mensalista);
	}
	

//	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		CRUDEstado crud = new CRUDEstado();
//		POEstado estado = new POEstado();
//		
//		System.out.println(request.getParameter("nome-estado"));
//		
//		estado.setIdEstado(Integer.parseInt(request.getParameter("id-estado")));
//		estado.setNomeEstado(request.getParameter("nome-estado"));
//		estado.setSiglaEstado(request.getParameter("sigla-estado"));
//		switch (request.getParameter("status-estado")) {
//			case "ativo":
//				estado.setStatus(EnumStatus.ATIVO);
//				break;
//	
//			case "inativo":
//				estado.setStatus(EnumStatus.INATIVO);
//				break;
//				
//			default:
//				estado.setStatus(EnumStatus.INATIVO);
//				break;
//		}
//		crud.update(estado);
//	}

}
