package sticks;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class StickListTests {
	
	@Test
	public void shouldCreateEmptyInstance() {
		new StickList();
	}
	
	@Test
	public void shouldCreateFilledInstance() {
		createStickList(30);
	}
	
	@Test
	public void shouldRemoveStickValid() {
		StickList list = createStickList(30);
		list.remove(0);
	}
	
	@Test
	public void shouldRemoveStickInvalid() {
		StickList list = createStickList(30);
		try					{ list.remove(-1); }
		catch(Exception e)	{ fail("Failed test"); }
		try					{ list.remove(31); }
		catch(Exception e)	{ fail("Failed test"); }
		try					{ list.remove(30); }
		catch(Exception e)	{ fail("Failed test"); }
	}
	
	@Test
	public void shouldHaveCorrectSizeAfterRemove() {
		StickList list = createStickList(30);
		list.remove(0);			//29 sticks left
		list.remove(0);			//28 sticks left
		list.remove(30);		//Not valid
		list.remove(25);		//27 sticks left
		
		assertEquals(list.size(), 27);
		assertNotEquals(list.size(), 26);
		assertNotEquals(list.size(), 28);
	}
	
	@Test
	public void shouldGetStick() {
		StickList list = createStickList(5);
		Stick stick = new Stick();
		list.add(stick);
		assertEquals(list.get(list.size()-1), stick);
	}
	
	@Test
	public void shouldGetStickInvalid() {
		StickList list = createStickList(5);
		try						{ list.get(-1); }
		catch(Exception e)		{ fail("Failed test"); }
		try						{ list.get(6); }
		catch(Exception e)		{ fail("Failed test"); }
		
	}
	
	@Test
	public void shouldUseStick() {
		StickList list = createStickList(1);
		list.use(1);
		assertEquals(list.used(), 1);
		list.use(1);
		assertEquals(list.used(), 1);
		
		list = createStickList(0);
		list.use(1);
		assertEquals(list.used(), 0);
		
		use(list, 5);
	}
	
	@Test
	public void shouldUseStickInvalid() {
		StickList list = createStickList(5);
		try						{ list.use(-1); }
		catch(Exception e)		{ fail("Failed test"); }
		try						{ list.use(6); }
		catch(Exception e)		{ fail("Failed test"); }
		try						{ list.use(0); }
		catch(Exception e)		{ fail("Failed test"); }
		assertEquals(list.used(), 0);						//Still remained unused so far
		list.use(3);										//Valid
		try						{ list.use(4); }			//Invalid, 3 + 4 = 7, list only has 5
		catch(Exception e)		{ fail("Failed test"); }
		
		list = createStickList(5);
		try						{ list.use(5); }			//Valid, may not catch exception
		catch(Exception e)		{ fail("Failed test"); }
		assertEquals(list.used(), 5);						//All sticks used without exception
		
	}
	
	@Test
	public void shouldUseSticksAndPrintIt() {
		StickList list = createStickList(8);
		list.use(3);
		assertEquals(list.toString(), "IIIII---");
		assertNotEquals(list.toString(), "---IIIII");
		assertNotEquals(list.toString(), "IIIIII--");
		assertNotEquals(list.toString(), "IIII----");
	}
	
	@Test
	public void shouldShowCorrectUsedSticks() {
		StickList list = createStickList(8);
		list.use(3);
		assertEquals(list.used(), 3);
		assertNotEquals(list.used(), 2);
		assertNotEquals(list.used(), 4);
	}
	
	@Test
	public void shouldShowCorrectUnusedSticks() {
		StickList list = createStickList(8);
		list.use(3);
		assertEquals(list.unused(), 5);
		assertNotEquals(list.unused(), 4);
		assertNotEquals(list.unused(), 6);
		list = createStickList(8);
		assertEquals(list.unused(), 8);
	}
	
	@Test
	public void shouldVerifyStickList() {
		StickList list = mock(StickList.class);
		Stick stick = new Stick();
		
		for (int i=0; i<10; i++)
			list.add(stick);
		verify(list, times(10)).add(stick);
		
		for (int i=0; i<5; i++)
			list.use();
		verify(list, times(5)).use();
		
		list.reset();
		list.use(5);
		verify(list).reset();
		verify(list).use(5);
	}
	
	
	
	private StickList createStickList(int size) {
		StickList list = new StickList();
		for (int i=0; i<size; i++) {
			Stick stick = new Stick();
			list.add(stick);
		}
		return list;
	}
	
	private void use(StickList list, int rounds) {
		list = createStickList(rounds);
		for (int i=0; i<rounds; i++) {
			list.use(1);
			assertEquals(list.used(), i+1);
		}
	}
	
}
