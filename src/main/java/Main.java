package main.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Main {

	private static final String FILE = "books.csv";
	private static final Logger LOG = Logger.getLogger(Main.class.getName());

	public static void main(String[] args) {
		BookStore store = new BookStoreImpl();
		List<Book> books = readCsvFromFile(FILE);
		for (Book book : books) {
			store.addNewRecord(book.getTitle(), book.getAuthors());
		}
		
		Scanner sc = new Scanner(System.in);
		CommandHelper.printHelpMemo();
		
		while (true) {
			System.out.println("Input command: ");
			String cmd = sc.nextLine();
			
			String[] params = cmd.split("\"?( |$)(?=(([^\"]*\"){2})*[^\"]*$)\"?");
			switch (params[0]) {
			case "lt": 
				CommandHelper.executeCmdListTitlesByAuthor(store, params);
				break;
			case "la": 
				CommandHelper.executeCmdListAuthorsByTitle(store, params);
				break;
			case "a": 
				CommandHelper.executeCmdAddNewRecord(store, params);
				break;
			case "rt":
				CommandHelper.executeCmdRemoveByTitle(store, params);
				break;
			case "ra":
				CommandHelper.executeCmdRemoveByAuthor(store, params);
				break;
			case "q":
				sc.close();
				CommandHelper.executeCmdQuit();
			default:
				CommandHelper.printHelpMemo();
			}			
		}
	}

	private static List<Book> readCsvFromFile(String fileName) {
		List<Book> books = null;
		Path path = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			books = br.lines()
				    .skip(1)
				    .map(mapToBook)
				    .collect(Collectors.toList());
		} catch (IOException e) {
			LOG.log(Level.WARNING, "Books csv reading failed", e);
		}
		return books;
	}
	
	private static Function<String, Book> mapToBook = (line) -> {
		String[] book = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
		return new Book(book[0].replaceAll("\"", ""), book[1].replaceAll("\"", "").split(", "));
	};

}
