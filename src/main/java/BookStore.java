package main.java;

import java.util.List;

public interface BookStore {
	
	public List<String> getTitlesByAuthor(String author);
	public List<String> getAuthorsByTitle(String title);
	public boolean addNewRecord(String title, String... authors);
	public boolean removeByTitle(String title);
	public boolean removeByAuthor(String author);
	
}
