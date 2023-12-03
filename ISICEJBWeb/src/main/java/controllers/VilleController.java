package controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.IDaoLocale;
import entities.Ville;

/**
 * Servlet implementation class VilleController
 */
@WebServlet("/villeController")
public class VilleController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB(beanName = "VilleDao")
    private IDaoLocale<Ville> ejb;

    public VilleController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "delete":
                    deleteVille(request, response);
                    break;
                case "formEdit":
                    formEditVille(request, response);
                    break;
                case "edit":
                    editVille(request, response);
                    break;
                default:
                    break;
            }
        } else {
        	
        }
        List<Ville> villes = ejb.findAll();
        request.setAttribute("villes", villes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ville.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String id = request.getParameter("id");
    	if(id.equals(""))
        addVille(request, response);
    	else 
    		editVille(request,response);
    	
    	List<Ville> villes = ejb.findAll();
        request.setAttribute("villes", villes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ville.jsp");
        dispatcher.forward(request, response);
    }

    private void addVille(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("ville");
        ejb.create(new Ville(nom));

        List<Ville> villes = ejb.findAll();
        request.setAttribute("villes", villes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ville.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteVille(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            int id = Integer.parseInt(idParam);
            ejb.delete(id);
        }

        List<Ville> villes = ejb.findAll();
        request.setAttribute("villes", villes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ville.jsp");
        dispatcher.forward(request, response);
    }

    private void formEditVille(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idString = request.getParameter("id");
        int id = Integer.parseInt(idString);
        Ville v = ejb.findById(id);
        request.setAttribute("ville", v);
        List<Ville> villes = ejb.findAll();
        request.setAttribute("villes", villes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ville.jsp");
        dispatcher.forward(request, response);
    }

    private void editVille(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	int id = Integer.parseInt(request.getParameter("id"));
    	String nom = request.getParameter("ville");
    	Ville ville = new Ville();
    	ville.setId(id);
    	ville.setNom(nom);
    	ejb.update(ville);
    	List<Ville> villes = ejb.findAll();
        request.setAttribute("villes", villes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("ville.jsp");
        dispatcher.forward(request, response);
    	
    }
}
