package service;

import model.SearchingOption;
import model.content.Book;
import model.content.Category;
import model.content.DocumentType;
import model.content.Language;
import service.dao.BookDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class ContentDisplayService {

    public static List<Book> bookList = new ArrayList<>();

//    public List<Book> getAllBooks() {
//        try {
//            return BookDAO.getAllBooks();
//        } catch (SQLException throwables) {
//            throw new RuntimeException("Something went wrong");
//        }
//    }

    public static void mainSearch(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SearchingOption searchingOption = (SearchingOption) session.getAttribute("searchingOption");
        if (searchingOption.getSearchBy().equals("Author")) {
            searchBooksByAuthor(searchingOption.getInputSearchOption());
        } else if (searchingOption.getSearchBy().equals("Title")) {
            searchBooksByTitle(searchingOption.getInputSearchOption());
        }
        filterBooksByLanguage(searchingOption.getLanguage());
        filterBooksByCategory(searchingOption.getCategory());
        filterBooksByDocumentType(searchingOption.getDocumentType());
    }


    public static void searchBooksByAuthor(String author) {
//        if ((currentPage - 1) % 3 == 0 || currentPage == 1) {
        bookList = BookDAO.getBooksByAuthor(author);
//        }
    }

    public static void searchBooksByTitle(String title) {
//        if ((currentPage - 1) % 3 == 0 || currentPage == 1) {
        bookList = BookDAO.getBooksByTitle(title);
//        }
    }

    public static void filterBooksByLanguage(Language language) {
        if (language != null) {
            Iterator<Book> iterator = bookList.iterator();
            while (iterator.hasNext()) {
                Book book = iterator.next();
                if (book.getLanguage().getId() != language.getId()) {
                    iterator.remove();
                }
            }
        }
    }

    public static void filterBooksByCategory(Category category) {
        if (category != null) {
            Iterator<Book> iterator = bookList.iterator();
            while (iterator.hasNext()) {
                Book book = iterator.next();
                if (book.getCategory().getId() != category.getId()) {
                    iterator.remove();
                }
            }
        }
    }

    public static void filterBooksByDocumentType(DocumentType documentType) {
        if (documentType != null) {
            Iterator<Book> iterator = bookList.iterator();
            while (iterator.hasNext()) {
                Book book = iterator.next();
                if (book.getDocumentType().getId() != documentType.getId()) {
                    iterator.remove();
                }
            }
        }
    }

    public static void sortBooksByTitle(int askDesk) {
        if (askDesk == 1) {
            Collections.sort(bookList, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getTitle().compareTo(o2.getTitle());
                }
            });
        } else if (askDesk == -1) {
            Collections.sort(bookList, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o2.getTitle().compareTo(o1.getTitle());
                }
            });
        }
    }

    public static void sortBooksByPage(int askDesk) {
        if (askDesk == 1) {
            Collections.sort(bookList, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getPages() - o2.getPages();
                }
            });
        } else if (askDesk == -1) {
            Collections.sort(bookList, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o2.getPages() - o1.getPages();
                }
            });
        }
    }

    public static void sortBooksByYear(int askDesk) {
        if (askDesk == 1) {
            Collections.sort(bookList, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getYear() - o2.getYear();
                }
            });
        } else if (askDesk == -1) {
            Collections.sort(bookList, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o2.getYear() - o1.getYear();
                }
            });
        }
    }

    public static void sortBooksByRecentlyAdded() {
        Collections.sort(bookList, new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getId() - o2.getId();
            }
        });
    }

}
