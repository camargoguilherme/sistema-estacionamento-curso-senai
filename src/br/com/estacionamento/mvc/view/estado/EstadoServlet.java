package br.com.estacionamento.mvc.view.estado;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import br.com.estacionamento.mvc.crud.CRUDEstado;
import br.com.estacionamento.mvc.model.persistent_object.POEstado;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

/**
 * Servlet implementation class ListarEstadoServlet
 */
@WebServlet("/EstadoServlet")
public class EstadoServlet extends HttpServlet {
       
    public EstadoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "";
		if(!request.getParameter("id-estado").equals("0")){
			sql = "SELECT o FROM POEstado o WHERE o.idEstado = " + request.getParameter("id-estado");
		}
		
		CRUDEstado crud = new CRUDEstado();
		ArrayList<POEstado> result = crud.select(sql);
		JSONArray arr = new JSONArray();
		if(result.size() > 0){
			for(POEstado poe : result){
				try {
					arr.put(poe.toJSON());
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
		CRUDEstado crud = new CRUDEstado();
		POEstado estado = new POEstado();
		
		System.out.println(request.getParameter("id-estado"));
		
		estado.setIdEstado(Integer.parseInt(request.getParameter("id-estado")));
		estado.setNomeEstado(request.getParameter("nome-estado"));
		estado.setSiglaEstado(request.getParameter("sigla-estado"));
		switch (request.getParameter("status-estado")) {
			case "ativo":
				estado.setStatus(EnumStatus.ATIVO);
				break;
	
			case "inativo":
				estado.setStatus(EnumStatus.INATIVO);
				break;
				
			default:
				estado.setStatus(EnumStatus.INATIVO);
				break;
		}
		crud.update(estado);
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
