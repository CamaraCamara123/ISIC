package services;

import java.util.List;

import dao.IDaoLocale;
import dao.IDaoRemote;
import entities.Hotel;
import entities.Ville;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless(name = "HotelDao")
public class HotelService implements IDaoRemote<Hotel>, IDaoLocale<Hotel>{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Hotel create(Hotel o) {
		em.persist(o);
		return o;
	}

	@Override
	public boolean delete(Hotel o) {
		em.remove(o);
		return true;
	}

	@Override
	public Hotel update(Hotel o) {
		return em.merge(o);
	}

	@Override
	public Hotel findById(int id) {
		return em.find(Hotel.class, id);
	}

	@Override
	public List<Hotel> findAll() {
		Query query = em.createQuery("select h from Hotel h");
		return query.getResultList();
	}

	@Override
	public boolean delete(int id) {
		Hotel v = findById(id);
		return delete(v);
	}
	
	public List<Hotel> HotelByVille(int villeId){
		Query query = em.createQuery("SELECT h FROM Hotel h WHERE h.ville.id = :villeId");
	    query.setParameter("villeId", villeId);
	    return query.getResultList();
	}

}
