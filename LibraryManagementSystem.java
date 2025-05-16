import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Book {
    int bookId;
    String title;
    String author;
    boolean isAvailable;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;  
    }

    @Override
    public String toString() {
        return "Book ID: " + bookId + ", Title: " + title + ", Author: " + author + ", Available: " + isAvailable;
    }
}

class Library {
    List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public Book searchBookByTitle(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public boolean issueBook(Book book) {
        if (book != null && book.isAvailable) {
            book.isAvailable = false; 
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book) {
        if (book != null && !book.isAvailable) {
            book.isAvailable = true;  
            return true;
        }
        return false;
    }

    public void displayBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}

public class LibraryManagementSystem {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    searchAndIssueBook();
                    break;
                case 3:
                    returnBook();
                    break;
                case 4:
                    displayAllBooks();
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- LIBRARY MANAGEMENT SYSTEM ---");
        System.out.println("1. Add Book");
        System.out.println("2. Search and Issue Book");
        System.out.println("3. Return Book");
        System.out.println("4. Display All Books");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addBook() {
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Book Author: ");
        String author = scanner.nextLine();

        Book book = new Book(bookId, title, author);
        library.addBook(book);
        System.out.println("Book added successfully!");
    }

    private static void searchAndIssueBook() {
        System.out.print("Enter Book Title to search: ");
        String title = scanner.nextLine();
        Book book = library.searchBookByTitle(title);
        if (book != null) {
            if (library.issueBook(book)) {
                System.out.println("Book issued successfully!");
            } else {
                System.out.println("Sorry, the book is currently unavailable.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void returnBook() {
        System.out.print("Enter Book Title to return: ");
        String title = scanner.nextLine();
        Book book = library.searchBookByTitle(title);
        if (book != null) {
            if (library.returnBook(book)) {
                System.out.println("Book returned successfully!");
            } else {
                System.out.println("This book was not issued.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    private static void displayAllBooks() {
        library.displayBooks();
    }
}
	
