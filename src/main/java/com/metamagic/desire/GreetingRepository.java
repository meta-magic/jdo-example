package com.metamagic.desire;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GreetingRepository {

	@Autowired
	PersistenceManagerFactory pmf;

	private PersistenceManager pm() {
		return pmf.getPersistenceManager();
	}

	public void update(Greeting greeting) {
		PersistenceManager pm = pm();
		Transaction tx = pm.currentTransaction();
		try
		{
		    tx.begin();
		    pm.setDetachAllOnCommit(true);
		    pm.makePersistent(greeting);
		    tx.commit();
		}
		finally
		{
		    if (tx.isActive())
		    {
		        tx.rollback();
		    }
		}
	}
	
	public Greeting findById(String id){
		Greeting working_greeting = null;
		PersistenceManager pm = pm();
		try
		{
		    Greeting greeting = pm.getObjectById(Greeting.class, id);
		    working_greeting = (Greeting)pm.detachCopy(greeting);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		}
		return working_greeting;
	}
	
	@SuppressWarnings("unchecked")
	public List<Greeting> findAll() {
		
		List<Greeting> working_greeting = null;
		PersistenceManager pm = pm();
		//Transaction tx=pm.currentTransaction();
		try
		{
		    //tx.begin();
		    Query query = pm.newQuery(Greeting.class);
			List<Greeting> list = (List<Greeting>) query.execute();
			 working_greeting = (List<Greeting> )pm.detachCopyAll(list);
			 
		   // tx.commit();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
//		    if (tx.isActive())
//		    {
//		        tx.rollback();
//		    }
		}
		
		return working_greeting;
	}
	
	public void save(Greeting greeting) {
		PersistenceManager pm = pm();
		pm.setDetachAllOnCommit(true);
		pm.makePersistent(greeting);
		pm.close();
	}

	
	
	

}
