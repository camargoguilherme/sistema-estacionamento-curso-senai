package br.com.estacionamento.mvc.view.endereco;

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
import br.com.estacionamento.mvc.crud.CRUDEndereco;
import br.com.estacionamento.mvc.crud.CRUDEstado;
import br.com.estacionamento.mvc.crud.CRUDMensalista;
import br.com.estacionamento.mvc.model.persistent_object.POCidade;
import br.com.estacionamento.mvc.model.persistent_object.POEndereco;
import br.com.estacionamento.mvc.model.persistent_object.POMensalista;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

/**
 * Servlet implementation class ListarEnderecoServlet
 */
@WebServlet("/EnderecoServlet")
public class EnderecoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public EnderecoServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sql = "";
		if(!request.getParameter("id-endereco").equals("0")){
			sql = "SELECT o FROM POEndereco o WHERE o.idEndereco = " + request.getParameter("id-endereco");
		}
		
		CRUDEndereco crud = new CRUDEndereco();
		ArrayList<POEndereco> result = crud.select(sql);
		JSONArray arr = new JSONArray();
		if(result.size() > 0){
			for(POEndereco poe : result){
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
		CRUDCidade crudCid = new CRUDCidade();
		CRUDMensalista crudMen = new CRUDMensalista();
		CRUDEndereco crudEnd = new CRUDEndereco();
		
		POEndereco endereco = new POEndereco();
		POMensalista mensalista = new POMensalista();
		POCidade cidade = new POCidade();
		
		mensalista = crudMen.select("SELECT o FROM POMensalista o WHERE o.nomeMensalista like '"+request.getParameter("mensalista-endereco")+"'").get(0);
		cidade = crudCid.select("SELECT o FROM POCidade o WHERE o.nomeCidade like '"+request.getParameter("cidade-endereco")+"'").get(0);
		endereco.setIdEndereco(Integer.parseInt(request.getParameter("id-endereco")));
		endereco.setCompEndereco(request.getParameter("complemento-endereco"));
		endereco.setNumEndereco(request.getParameter("numero-endereco"));
		endereco.setCepEndereco(request.getParameter("cep-endereco"));
		endereco.setLogradouroEndereco(request.getParameter("rua-endereco"));
		endereco.setCidadeEndereco(cidade);
		endereco.setMensalistaEndereco(mensalista);

		switch (request.getParameter("statu-endereco")) {
			case "ativo":
				endereco.setStatusEndereco(EnumStatus.ATIVO);
				break;
	
			case "inativo":
				endereco.setStatusEndereco(EnumStatus.INATIVO);
				break;
				
			default:
				endereco.setStatusEndereco(EnumStatus.INATIVO);
				break;
		}
		
		crudEnd.update(endereco);
	}
}
