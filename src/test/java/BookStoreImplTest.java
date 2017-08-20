package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.java.BookStore;
import main.java.BookStoreImpl;

public class BookStoreImplTest {
	
	private static final String NEW_AUTHOR = "author1";
	private static final String NEW_TITLE = "title1";
	private static final String CO_AUTHOR = "co-author";
	private static final String AUTHOR = "author";
	private static final String TITLE = "title";
	private BookStore store;

	@Before
	public void setUp() throws Exception {
		store = new BookStoreImpl();
	}
	
	@Test
	public void testGetTitlesByAuthor() {
		assertNull(store.getTitlesByAuthor(AUTHOR));
		
		store.addNewRecord(TITLE, AUTHOR, CO_AUTHOR);
		List<String> res = Arrays.asList(TITLE);
		
		assertEquals(res, store.getTitlesByAuthor(AUTHOR));
		assertEquals(res, store.getTitlesByAuthor(CO_AUTHOR));
		
		store.addNewRecord(NEW_TITLE, AUTHOR, CO_AUTHOR);
		res = Arrays.asList(NEW_TITLE, TITLE);
		
		assertEquals(res, store.getTitlesByAuthor(AUTHOR));
		assertEquals(res, store.getTitlesByAuthor(CO_AUTHOR));
	}

	@Test
	public void testGetAuthorsByTitle() {
		assertNull(store.getAuthorsByTitle(TITLE));
		
		store.addNewRecord(TITLE, AUTHOR, CO_AUTHOR);
		List<String> res = Arrays.asList(AUTHOR, CO_AUTHOR);
		assertEquals(res, store.getAuthorsByTitle(TITLE));
		
		store.addNewRecord(NEW_TITLE, NEW_AUTHOR);
		res = Arrays.asList(NEW_AUTHOR);
		assertEquals(res, store.getAuthorsByTitle(NEW_TITLE));
	}

	@Test
	public void testAddNewRecord() {
		assertNull(store.getAuthorsByTitle(TITLE));
		assertNull(store.getTitlesByAuthor(AUTHOR));
		assertNull(store.getTitlesByAuthor(CO_AUTHOR));
		
		store.addNewRecord(TITLE, AUTHOR, CO_AUTHOR);
		List<String> authors = Arrays.asList(AUTHOR, CO_AUTHOR);
		assertEquals(authors, store.getAuthorsByTitle(TITLE));
		
		List<String> books = Arrays.asList(TITLE);
		assertEquals(books, store.getTitlesByAuthor(AUTHOR));
		assertEquals(books, store.getTitlesByAuthor(CO_AUTHOR));
		
		store.addNewRecord(TITLE, AUTHOR, CO_AUTHOR); // testing dupes (titles should be unique)
		assertEquals(authors, store.getAuthorsByTitle(TITLE));
		assertEquals(books, store.getTitlesByAuthor(AUTHOR));
		assertEquals(books, store.getTitlesByAuthor(CO_AUTHOR));
		
		store.addNewRecord(TITLE, CO_AUTHOR); // testing dupes (titles should be unique)
		assertEquals(authors, store.getAuthorsByTitle(TITLE));
		assertEquals(books, store.getTitlesByAuthor(AUTHOR));
		assertEquals(books, store.getTitlesByAuthor(CO_AUTHOR));
		
		store.addNewRecord(NEW_TITLE, AUTHOR);
		List<String> moreBooks = Arrays.asList(TITLE, NEW_TITLE);
		List<String> oneAuthor = Arrays.asList(AUTHOR);
		
		assertEquals(authors, store.getAuthorsByTitle(TITLE));
		assertEquals(oneAuthor, store.getAuthorsByTitle(NEW_TITLE));
		assertEquals(moreBooks, store.getTitlesByAuthor(AUTHOR));
		assertEquals(books, store.getTitlesByAuthor(CO_AUTHOR));
	}

	@Test
	public void testRemoveByTitle() {
		store.addNewRecord(TITLE, AUTHOR);
		assertEquals(Arrays.asList(AUTHOR), store.getAuthorsByTitle(TITLE));
		assertEquals(Arrays.asList(TITLE), store.getTitlesByAuthor(AUTHOR));
		
		store.removeByTitle(TITLE);
		assertNull(store.getAuthorsByTitle(TITLE));
		assertNull(store.getTitlesByAuthor(AUTHOR));
	}

	@Test
	public void testRemoveByAuthor() {
		store.removeByAuthor(AUTHOR);
	}

}
