package br.com.estacionamento.mvc.view.telefone;

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
import br.com.estacionamento.mvc.crud.CRUDTelefone;
import br.com.estacionamento.mvc.model.persistent_object.POMensalista;
import br.com.estacionamento.mvc.model.persistent_object.POTelefone;
import br.com.estacionamento.mvc.model.persistent_object.POTelefone;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumTipoTelefone;

/**
 * Servlet implementation class ListarTelefoneServlet
 */
@WebServlet("/TelefoneServlet")
public class TelefoneServlet extends HttpServlet {
    
	
    public TelefoneServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "";
		if(!request.getParameter("id-telefone").equals("0")){
			sql = "SELECT o FROM POTelefone o WHERE o.idTelefone = " + request.getParameter("id-telefone");
		}
		
		CRUDTelefone crud = new CRUDTelefone();
		ArrayList<POTelefone> result = crud.select(sql);
		JSONArray arr = new JSONArray();
		if(result.size() > 0){
			for(POTelefone pot : result){
				try {
					arr.put(pot.toJSON());
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
		CRUDTelefone crudTel = new CRUDTelefone();
		CRUDMensalista crudMen = new CRUDMensalista();
		POTelefone telefone = new POTelefone();
		POMensalista mensalista = new POMensalista();
		
		mensalista = crudMen.select("SELECT o FROM POMensalista o WHERE o.nomeMensalista like '"+request.getParameter("nome-mensalista")+"'").get(0);
		
		System.out.println(request.getParameter("id-estado"));
		telefone.setMensalistaTelefone(mensalista);
		telefone.setIdTelefone(Integer.parseInt(request.getParameter("id-telefone")));
		telefone.setNumTelefone(request.getParameter("numero-telefone"));
		switch (request.getParameter("tipo-telefone")) {
			case "comercial":
				telefone.setTipoTelefone(EnumTipoTelefone.COMERCIAL);
				break;
	
			case "residencial":
				telefone.setTipoTelefone(EnumTipoTelefone.RESIDENCIAL);
				break;
			
			case "ceular":
				telefone.setTipoTelefone(EnumTipoTelefone.CELULAR);
				break;	
				
			default:
				telefone.setTipoTelefone(EnumTipoTelefone.RESIDENCIAL);
				break;
		}
		
		switch (request.getParameter("status-telefone")) {
			case "ativo":
				telefone.setStatusTelefone(EnumStatus.ATIVO);
				break;
	
			case "inativo":
				telefone.setStatusTelefone(EnumStatus.INATIVO);
				break;
				
			default:
				telefone.setStatusTelefone(EnumStatus.INATIVO);
				break;
		}
		crudTel.update(telefone);
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
