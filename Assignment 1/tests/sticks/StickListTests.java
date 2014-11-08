package sticks;
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
	}
	
	@Test
	public void shouldHaveCorrectSizeAfterRemove() {
		final int size = 30;
		
		StickList list = createStickList(size);
		list.remove(0);			//29 sticks left
		list.remove(0);			//28 sticks left
		list.remove(30);		//Not valid
		list.remove(25);		//27 sticks left
		
		int checks = 0;			//Perform 3 checks
		if (list.size() == 27)	//Should be 27
			checks++;
		if (list.size() != 28)	//Should not be 28
			checks++;
		if (list.size() != 26)	//Should not be 26
			checks++;
		if (checks != 3)		//Fail is checks are not passed
			fail("Failed test");
	}
	
	
	
	private StickList createStickList(int size) {
		StickList list = new StickList();
		for (int i=0; i<size; i++) {
			Stick stick = new Stick();
			list.add(stick);
		}
		return list;
	}
	
}
