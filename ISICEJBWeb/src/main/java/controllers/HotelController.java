package controllers;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.IDaoLocale;
import entities.Hotel;
import entities.Ville;

/**
 * Servlet implementation class HotelController
 */
@WebServlet("/hotelController")
public class HotelController extends HttpServlet{
    private static final long serialVersionUID = 1L;

    @EJB(beanName="HotelDao")
    private IDaoLocale<Hotel> ejb;

    @EJB(beanName="VilleDao")
    private IDaoLocale<Ville> ejbVille;


    public HotelController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	
        String action = request.getParameter("action");
        List<Ville> villes = ejbVille.findAll();
        villes.forEach(v->{
        	System.out.println(v.getNom());
        });
        if (action != null) {
            switch (action) {
                case "delete":
                    deleteHotel(request, response);
                    break;
                case "formEdit":
                    formEditHotel(request, response);
                    break;
                case "accueil":
                	listHotels(request, response);
                	break;
                default:
                	listHotels(request, response);
                    break;
            }
        } else {
        	
        }
        listHotels(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String id = request.getParameter("id");
    	if (id == null || id.equals("")) {
    	    addHotel(request, response);
    	} else {
    	    editHotel(request, response);
    	}
    }

    private void addHotel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String adresse = request.getParameter("adresse");
        String telephone = request.getParameter("telephone");
        int villeId = Integer.parseInt(request.getParameter("villeId"));

        Hotel hotel = new Hotel();
        hotel.setNom(nom);
        hotel.setAdresse(adresse);
        hotel.setTelephone(telephone);

        Ville ville = new Ville();
        ville.setId(villeId);
        hotel.setVille(ville);

        ejb.create(hotel);
        request.setAttribute("hotel", new Hotel());
        listHotels(request, response);
    }

    private void deleteHotel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        ejb.delete(id);

        listHotels(request, response);
    }

    private void listHotels(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Hotel> hotels = ejb.findAll();
        request.setAttribute("hotels", hotels);
        
        List<Ville> villes = ejbVille.findAll();
        request.setAttribute("villes", villes);
        villes.forEach(v->{
        	System.out.println(v.getNom());
        });
        RequestDispatcher dispatcher = request.getRequestDispatcher("Hotel.jsp");
        dispatcher.forward(request, response);
    }
    
    private void formEditHotel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Hotel hotel = ejb.findById(id);

        List<Ville> villes = ejbVille.findAll();
        request.setAttribute("villes", villes);
        request.setAttribute("hotels", ejb.findAll());
        request.setAttribute("hotel", hotel);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Hotel.jsp");
        dispatcher.forward(request, response);
    }
    
    private void editHotel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String adresse = request.getParameter("adresse");
        String telephone = request.getParameter("telephone");
        int villeId = Integer.parseInt(request.getParameter("villeId"));

        Hotel hotel = ejb.findById(id);
        hotel.setNom(nom);
        hotel.setAdresse(adresse);
        hotel.setTelephone(telephone);

        Ville ville = ejbVille.findById(villeId);
        hotel.setVille(ville);

        ejb.update(hotel);

        listHotels(request, response);
    }

}
