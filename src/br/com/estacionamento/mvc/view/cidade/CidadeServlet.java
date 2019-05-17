package br.com.estacionamento.mvc.view.cidade;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import br.com.estacionamento.mvc.crud.CRUDCidade;
import br.com.estacionamento.mvc.crud.CRUDEstado;
import br.com.estacionamento.mvc.model.persistent_object.POCidade;
import br.com.estacionamento.mvc.model.persistent_object.POEstado;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

/**
 * Servlet implementation class ListarCidadeServlet
 */
@WebServlet("/CidadeServlet")
public class CidadeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CidadeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "";
		if(!request.getParameter("id-cidade").equals("0")){
			sql = "SELECT o FROM POCidade o WHERE o.idCidade = " + request.getParameter("id-cidade");
		}
		
		CRUDCidade crud = new CRUDCidade();
		ArrayList<POCidade> result = crud.select(sql);
		JSONArray arr = new JSONArray();
		if(result.size() > 0){
			for(POCidade poc : result){
				try {
					arr.put(poc.toJSON());
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
		CRUDCidade crud = new CRUDCidade();
		CRUDEstado crudEst = new CRUDEstado();
		POEstado estado = new POEstado();
		POCidade cidade = new POCidade();
		
		estado = crudEst.select("SELECT o FROM POEstado o WHERE o.siglaEstado like '"+request.getParameter("estado-cidade")+"'").get(0);
		
		cidade.setIdCidade(Integer.parseInt(request.getParameter("id-cidade")));
		cidade.setNomeCidade(request.getParameter("nome-cidade"));
		cidade.setEstadoCidade(estado);
		switch (request.getParameter("status-cidade")) {
			case "ativo":
				cidade.setStatusCidade(EnumStatus.ATIVO);
				break;
	
			case "inativo":
				cidade.setStatusCidade(EnumStatus.INATIVO);
				break;
				
			default:
				cidade.setStatusCidade(EnumStatus.INATIVO);
				break;
		}
		crud.update(cidade);
	}
}
