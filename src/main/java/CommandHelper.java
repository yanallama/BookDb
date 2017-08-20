package main.java;

import java.util.Arrays;
import java.util.List;

public class CommandHelper {
	
	private CommandHelper() {}
	
	private static final String CMD_MEMO = "Commands:\n"
			+ "lt <author> - list titles by author\n"
			+ "la <title> - list authors by title\n"
			+ "a <title> <author> [<co-author> <co-author> <...>] - add book\n"
			+ "rt <title> - remove book by title\n"
			+ "ra <author> - remove book by author\n"
			+ "q - quit\n";
	
	public static void executeCmdAddNewRecord(BookStore store, String[] params) {
		if (params.length < 3) {
			System.out.println("This command requires title and author(s): a <title> <author> [<co-author> <co-author> <...>]\n"
					+ "If author names contains spaces, use double quotation marks\n");
			return;
		}
		if (store.addNewRecord(params[1], Arrays.copyOfRange(params, 2, params.length)))
			System.out.println("Added\n");
		else 
			System.out.println("Title already exist\n");
	}

	public static void executeCmdListAuthorsByTitle(BookStore store, String[] params) {
		if (titleParamCheck(params[0], params.length)) {
			String title = params[1];
			List<String> authors = store.getAuthorsByTitle(params[1]);
			if (authors != null) {
				System.out.println(title + " is written by:");
				authors.forEach(System.out::println);
				System.out.println();
			} else {
				System.out.println("No books named " + title + "\n");
			}
		}
	}

	public static void executeCmdQuit() {
		System.out.println("Thank you for using book database");
		System.exit(0);
	}

	public static void executeCmdListTitlesByAuthor(BookStore store, String[] params) {
		if (authorParamCheck(params[0], params.length)) {
			String author = params[1];
			List<String> titles = store.getTitlesByAuthor(author);
			if (titles != null) {
				System.out.println(author + " wrote:");
				titles.forEach(System.out::println);
				System.out.println();
			} else {
				System.out.println("No books by " + author + "\n");
			}
		}
	}

	public static void printHelpMemo() {
		System.out.println(CMD_MEMO);
		System.out.println();
	}

	public static void executeCmdRemoveByTitle(BookStore store, String[] params) {
		if (titleParamCheck(params[0], params.length)) {
			if (store.removeByTitle(params[1]))
				System.out.println("Removed\n");
			else 
				System.out.println("Nothing to remove\n");
		}
	}

	public static void executeCmdRemoveByAuthor(BookStore store, String[] params) {
		if (authorParamCheck(params[0], params.length)) {
			if (store.removeByAuthor(params[1]))
				System.out.println("Removed\n");
			else
				System.out.println("Nothing to remove\n");
		}
	}
	
	private static boolean authorParamCheck(String cmd, int length) {
		if (length != 2) {
			System.out.println("This command requires author name: " + cmd + " <author>\n"
					+ "If author name contains spaces, use double quotation marks\n");
			return false;
		}
		return true;
	}
	
	private static boolean titleParamCheck(String cmd, int length) {
		if (length != 2) {
			System.out.println("This command requires book title: " + cmd + " <title>\n"
					+ "If book title contains spaces, use double quotation marks\n");
			return false;
		}
		return true;
	}

}
