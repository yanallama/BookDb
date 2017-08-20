package main.java;

public class Book {
	private String title;
	private String[] authors;	

	public Book(String title, String... authors) {
		super();
		this.title = title;
		this.authors = authors;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String[] getAuthors() {
		return authors;
	}

	public void setAuthors(String[] authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", authors=" + String.join(", ", authors) + "]";
	}
	
}
