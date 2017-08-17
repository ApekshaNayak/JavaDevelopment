/**
 * 
 */
package client;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import entity.Entity;

/**
 * @author Sainath
 *
 */
public class Main {

	public Entity setEntity(Entity entity) {

		entity.setEmpName("Kabira");
		entity.setEmpEd("MSC");
		return entity;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure()
				.buildSessionFactory();

		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Entity entity = new Entity();
		Main main = new Main();
		Query query;

		// Creating Data.
		Integer id = (Integer) session.save(main.setEntity(entity));
		
		// Updating Data.
		String updateQuery = "UPDATE Entity set empEdu = :empEdu WHERE empId = :empId";
		query = session.createQuery(updateQuery);
		query.setParameter("empEdu", "MS");
		query.setParameter("empId", 3);
		//query.executeUpdate();

		// Delete data.
		String deleteQuery = "DELETE from Entity where empId = :empId";
		query = session.createQuery(deleteQuery);
		query.setParameter("empId",9);
		//query.executeUpdate();

		// Retrieving Data.
		String createQuery = "From Entity";
		query = session.createQuery(createQuery);
		List<Entity> result = query.list();
		Iterator<Entity> itr = result.iterator();
		Entity element;
		while (itr.hasNext()) {
			element = itr.next();
			System.out.print(" \t" + element.getEmpId() + " \t"
					+ element.getEmpName() + " \t" + element.getEmpEd() + "\n");

		}
		
		tx.commit();
		session.close();
		System.out.println("The end");

	}
}
