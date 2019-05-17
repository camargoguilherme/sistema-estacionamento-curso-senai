package br.com.estacionamento.mvc.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.estacionamento.mvc.crud.CRUDEstado;
import br.com.estacionamento.mvc.model.persistent_object.POEstado;
import br.com.estacionamento.mvc.model.persistent_object.enums.EnumStatus;

/**
 * Servlet implementation class HelloWorld
 */
@WebServlet("/HelloWorld")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HelloWorld() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	PrintWriter out  = response.getWriter();
    	
    	CRUDEstado crudEstado = new CRUDEstado();
    	POEstado estado = new POEstado();
    	estado.setNomeEstado(request.getParameter("nome"));
    	estado.setSiglaEstado(request.getParameter("sigla"));
    	out.println(request.getParameter("status"));
    	switch (request.getParameter("status")) {
			case "ativo":
				estado.setStatus(EnumStatus.ATIVO);
				break;
	
			case "inativo":
				estado.setStatus(EnumStatus.INATIVO);
				break;
		}
    	
    	crudEstado.insert(estado);
    	
    	ArrayList<POEstado> estadoResult = crudEstado.select("SELECT o FROM POEstado o");
    	
    	if(estado.getIdEstado() > 0){
    		response.sendRedirect(request.getContextPath() + "/sucesso.jsp");
    	}else{
    		response.sendRedirect(request.getContextPath() + "/erro.jsp");
    	}
    	/*
    	out.println("<html>");
    	out.println("<body>");
    	out.print("<table>");
    	out.print("<thead>");
    	out.print("<th>ID</th>");
    	out.print("<th>NOME</th>");
    	out.print("<th>SIGLA</th>");
    	out.print("<th>STATUS</th>");
    	out.print("</thead>");
    	out.print("<tbody>");
    	for(Object o: estadoResult){
    		POEstado e = (POEstado) o; 
    		out.print("<tr>");
    		out.println("<td>" + e.getIdEstado() + "</td>");
        	out.println("<td>" + e.getNomeEstado() + "</td>");
        	out.println("<td>" + e.getSiglaEstado() + "</td>");
        	out.println("<td>" + e.getStatus() + "</td>");
        	out.print("</tr>");
    	}
    	out.print("</tbody>");
    	out.print("</table>");
    	out.println("</body>");
    	out.println("</html>");
		*/
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
