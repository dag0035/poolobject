/**
 * 
 */
package ubu.gii.dass.test.c01;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ubu.gii.dass.c01.DuplicatedInstanceException;
import ubu.gii.dass.c01.NotFreeInstanceException;
import ubu.gii.dass.c01.Reusable;
import ubu.gii.dass.c01.ReusablePool;

/**
 * @author Daniel Arnaiz
 * @author Miguel Perez Lopez de Echazarreta
 *
 */
public class ReusablePoolTest {

	
	ReusablePool reusablePool;
	Reusable r1, r2, r3;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		reusablePool = ReusablePool.getInstance();		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		reusablePool = null;
		r1 = null;
		r2 = null;
		r3 = null;
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		//fail("Not yet implemented");
		
		assertNotNull(reusablePool);
		assertEquals(reusablePool, ReusablePool.getInstance());
		assertTrue(reusablePool instanceof ReusablePool);
	}
	
	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#acquireReusable()}.
	 * @throws NotFreeInstanceException
	 */
	@Test
	public void testAcquireReusable() throws NotFreeInstanceException{
		//fail("Not yet implemented");
		
		r1 = reusablePool.acquireReusable();
		assertNotNull(r1);
		assertTrue(r1 instanceof Reusable);
		
		r2 = reusablePool.acquireReusable();
		assertNotNull(r2);
		assertTrue(r2 instanceof Reusable);
		
		try {
			r3 = reusablePool.acquireReusable();
			assertNull(r3);
		} catch (NotFreeInstanceException e) { }		 
		
		
	}

	/**
	 * Test method for {@link ubu.gii.dass.c01.ReusablePool#releaseReusable(ubu.gii.dass.c01.Reusable)}.
	 * @throws DuplicatedInstanceException 
	 */
	@Test
	public void testReleaseReusable() throws DuplicatedInstanceException {
		//fail("Not yet implemented");
		
		try {
			r1 = reusablePool.acquireReusable();
			r2 = reusablePool.acquireReusable();
		} catch (NotFreeInstanceException e1) {	}
		
		// Test if releases a null object
		reusablePool.releaseReusable(null);
		
		// Release both objects
		reusablePool.releaseReusable(r1);
		reusablePool.releaseReusable(r2);	
		
		// Test a release of an already released object
		try {
			reusablePool.releaseReusable(r2);	
		} catch (DuplicatedInstanceException e) { }
		
		
	}

}
