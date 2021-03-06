package service;

import model.SearchingOption;
import model.content.Book;
import model.content.Category;
import model.content.DocumentType;
import model.content.Language;
import service.dao.BookDAO;
import service.dao.CategoryDAO;
import service.dao.DocumentTypeDAO;
import service.dao.LanguageDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import java.util.*;

import static service.dao.CategoryDAO.*;
import static service.dao.LanguageDAO.*;
import static service.dao.DocumentTypeDAO.*;

public class BookContentDisplayService {

    public static List<Book> bookList = new ArrayList<>();

    public static void mainSearch(HttpServletRequest request, Connection connection) {
        HttpSession session = request.getSession();
        SearchingOption searchingOption = (SearchingOption) session.getAttribute("searchingOption");
        bookList.clear();
        setCategories(getAllCategories(connection));
        setLanguages(getAllLanguages(connection));
        setDocumentTypes(getAllDocumentTypes(connection));
        if (searchingOption.getSearchBy().equals("Author")) {
            searchBooksByAuthor(connection, searchingOption.getInputSearchOption());
        } else if (searchingOption.getSearchBy().equals("Title")) {
            searchBooksByTitle(connection, searchingOption.getInputSearchOption());
        }
        filterBooksByLanguage(searchingOption.getLanguage());
        filterBooksByCategory(searchingOption.getCategory());
        filterBooksByDocumentType(searchingOption.getDocumentType());
        filterCategories(bookList);
        filterLanguages(bookList);
        filterDocumentTypes(bookList);
    }

    public static void searchBooksByAuthor(Connection connection, String author) {
        bookList = BookDAO.getBooksByAuthor(connection, author);
    }

    public static void searchBooksByTitle(Connection connection, String title) {
        bookList = BookDAO.getBooksByTitle(connection, title);
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
                    return o2.getTitle().compareTo(o1.getTitle());
                }
            });
        } else if (askDesk == -1) {
            Collections.sort(bookList, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getTitle().compareTo(o2.getTitle());
                }
            });
        }
    }

    public static void sortBooksByPage(int askDesk) {
        if (askDesk == 1) {
            Collections.sort(bookList, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o2.getPages() - o1.getPages();
                }
            });
        } else if (askDesk == -1) {
            Collections.sort(bookList, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getPages() - o2.getPages();
                }
            });
        }
    }

    public static void sortBooksByYear(int askDesk) {
        if (askDesk == 1) {
            Collections.sort(bookList, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o2.getYear() - o1.getYear();
                }
            });
        } else if (askDesk == -1) {
            Collections.sort(bookList, new Comparator<Book>() {
                @Override
                public int compare(Book o1, Book o2) {
                    return o1.getYear() - o2.getYear();
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


    public static void filterCategories(List<Book> books) {
        List<Category> categories = CategoryDAO.getCategories();
        Set<Category> filteredCategories = new LinkedHashSet<>();
        for (Book book : books) {
            for (Category category : categories) {
                if (category.getId() == book.getCategory().getId()) {
                    filteredCategories.add(category);
                }
            }
        }
        setCategories(new ArrayList<>(filteredCategories));
    }

    public static void filterLanguages(List<Book> books) {
        List<Language> languages = LanguageDAO.getLanguages();
        Set<Language> filteredLanguages = new LinkedHashSet<>();
        for (Book book : books) {
            for (Language language : languages) {
                if (language.getId() == book.getLanguage().getId()) {
                    filteredLanguages.add(language);
                }
            }
        }
        LanguageDAO.setLanguages(new ArrayList<>(filteredLanguages));
    }

    public static void filterDocumentTypes(List<Book> books) {
        List<DocumentType> documentTypes = DocumentTypeDAO.getDocumentTypes();
        Set<DocumentType> filterDocumentTypes = new LinkedHashSet<>();
        for (Book book : books) {
            for (DocumentType documentType : documentTypes) {
                if (documentType.getId() == book.getDocumentType().getId()) {
                    filterDocumentTypes.add(documentType);
                }
            }
        }
        DocumentTypeDAO.setDocumentTypes(new ArrayList<>(filterDocumentTypes));
    }

}
