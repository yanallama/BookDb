package main.java;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Titles are unique
 * Author can have many books
 */
public class BookStoreImpl implements BookStore {
	
	Map<String, Book> titleMap = new HashMap<>();
	Map<String, Collection<Book>> authorMap = new HashMap<>();
	

	@Override
	public List<String> getTitlesByAuthor(String author) {
		if (!authorMap.containsKey(author)) {
			return null;
		}
		return authorMap.get(author).stream()
				.map(Book::getTitle)
				.collect(Collectors.toList());
	}

	@Override
	public List<String> getAuthorsByTitle(String title) {
		if (!titleMap.containsKey(title)) {
			return null;
		}
		return Arrays.asList(titleMap.get(title).getAuthors());
	}

	@Override
	public boolean addNewRecord(String title, String... authors) {
		if (titleMap.containsKey(title)) {
			return false;
		}
		
		Book book = new Book(title, authors);
		titleMap.put(title, book);
		
		for (String author : authors) {
			Collection<Book> booksByAuthor = authorMap.get(author);
			if (booksByAuthor == null) {
				booksByAuthor = new HashSet<Book>();
				authorMap.put(author, booksByAuthor);
			}
			booksByAuthor.add(book);
		}
		return true;
	}

	@Override
	public boolean removeByTitle(String title) {
		if (!titleMap.containsKey(title)) {
			return false;
		}
		
		Book rmBook = titleMap.remove(title);
		for (String author : rmBook.getAuthors()) {
			Collection<Book> booksByAuthor = authorMap.get(author);
			if (booksByAuthor != null) {
				booksByAuthor.remove(rmBook);
			}
			if (booksByAuthor.size() == 0) {
				authorMap.remove(author);
			}
		}
		return true;
	}

	@Override
	public boolean removeByAuthor(String author) {
		if (!authorMap.containsKey(author)) {
			return false;
		}
		
		Collection<Book> rmBooks = authorMap.remove(author);
		for (Book book : rmBooks) {
			if (!coAuthorExistsCheck(book)) {
				titleMap.remove(book.getTitle());
			}
		}
		return true;
	}

	private boolean coAuthorExistsCheck(Book book) {
		for(String author : book.getAuthors()) {
			if (authorMap.containsKey(author)) {
				return true;
			}
		}
		return false;
	}
}
