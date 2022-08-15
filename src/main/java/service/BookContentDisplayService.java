package service;

import com.google.common.base.Objects;
import com.google.common.collect.Ordering;
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

import static service.dao.CategoryDAO.getAllCategories;
import static service.dao.CategoryDAO.setCategories;
import static service.dao.DocumentTypeDAO.getAllDocumentTypes;
import static service.dao.DocumentTypeDAO.setDocumentTypes;
import static service.dao.LanguageDAO.getAllLanguages;
import static service.dao.LanguageDAO.setLanguages;

public class BookContentDisplayService {

    private static List<Book> bookList = new ArrayList<>();

    public static List<String> sortingOptions = new ArrayList<>(Arrays.asList("Recently added", "Last added",
            "Title (A-Z)", "Title (Z-A)", "Page up -> down", "Page down -> up", "Year up -> down", "Year down -> up"));

    public static List<Book> getBookList() {
        return new ArrayList<>(bookList);
    }

    public static void setBookList(List<Book> bookList) {
        BookContentDisplayService.bookList = bookList;
    }

    public static void mainSearch(HttpServletRequest request, Connection connection) {
        HttpSession session = request.getSession();
        SearchingOption searchingOption = (SearchingOption) session.getAttribute("searchingOption");
        bookList.clear();
        setCategories(getAllCategories(connection));
        setLanguages(getAllLanguages(connection));
        setDocumentTypes(getAllDocumentTypes(connection));
        if (searchingOption.getSearchBy() != null &&
                Objects.equal(searchingOption.getSearchBy(), "Author")) {
            searchBooksByAuthor(connection, searchingOption.getInputSearchOption());
        } else if (searchingOption.getSearchBy() != null &&
                Objects.equal(searchingOption.getSearchBy(), "Title")) {
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
//        String processedAuthor = author == null ? "" : author;
        setBookList(BookDAO.getBooksByAuthor(connection, author));
    }

    public static void searchBooksByTitle(Connection connection, String title) {
//        String processedTitle = title == null ? "" : title;
        setBookList(BookDAO.getBooksByTitle(connection, title));
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

    private static final Ordering<Book> ascOrderingByTitle = new Ordering<Book>() {
        @Override
        public int compare(Book book1, Book book2) {
            return book1.getTitle().compareTo(book2.getTitle());
        }
    };

    private static final Ordering<Book> descOrderingByTitle = new Ordering<Book>() {
        @Override
        public int compare(Book book1, Book book2) {
            return book2.getTitle().compareTo(book1.getTitle());
        }
    };

    public static void sortBooksByTitle(int ascDesc) {
        if (ascDesc == 1) {
            bookList.sort(ascOrderingByTitle);
        } else if (ascDesc == -1) {
            bookList.sort(descOrderingByTitle);
        }
    }

    private static final Ordering<Book> ascOrderingByPage = new Ordering<Book>() {
        @Override
        public int compare(Book book1, Book book2) {
            return book1.getPages() - book2.getPages();
        }
    };

    private static final Ordering<Book> descOrderingByPage = new Ordering<Book>() {
        @Override
        public int compare(Book book1, Book book2) {
            return book2.getPages() - book1.getPages();
        }
    };

    public static void sortBooksByPage(int ascDesc) {
        if (ascDesc == 1) {
            bookList.sort(ascOrderingByPage);
        } else if (ascDesc == -1) {
            bookList.sort(descOrderingByPage);
        }
    }

    private static final Ordering<Book> ascOrderingByYear = new Ordering<Book>() {
        @Override
        public int compare(Book book1, Book book2) {
            return book1.getYear() - book2.getYear();
        }
    };

    private static final Ordering<Book> descOrderingByYear = new Ordering<Book>() {
        @Override
        public int compare(Book book1, Book book2) {
            return book2.getYear() - book1.getYear();
        }
    };

    public static void sortBooksByYear(int ascDesc) {
        if (ascDesc == 1) {
            bookList.sort(ascOrderingByYear);
        } else if (ascDesc == -1) {
            bookList.sort(descOrderingByYear);
        }
    }

    private static final Ordering<Book> ascOrderingByRecentlyAdded = new Ordering<Book>() {
        @Override
        public int compare(Book book1, Book book2) {
            return book1.getId() - book2.getId();
        }
    };

    private static final Ordering<Book> descOrderingByRecentlyAdded = new Ordering<Book>() {
        @Override
        public int compare(Book book1, Book book2) {
            return book2.getId() - book1.getId();
        }
    };

    public static void sortBooksByRecentlyAdded(int ascDesc) {
        if (ascDesc == 1) {
            bookList.sort(ascOrderingByRecentlyAdded);
        } else if (ascDesc == -1) {
            bookList.sort(descOrderingByRecentlyAdded);
        }
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

    public static SearchingOption cacheSearchingOptions(HttpServletRequest request) {
        Category category = getSelectedCategory(request);
        DocumentType documentType = getSelectedDocumentType(request);
        Language language = getSelectedLanguage(request);
//        String searchBy = request.getParameter("searchBy") == null ? "Author" : request.getParameter("searchBy");
        return new SearchingOption(request.getParameter("searchBook"),
                request.getParameter("searchBy"),
                category,
                documentType,
                language);
    }

    public static Category getSelectedCategory(HttpServletRequest request) {
        Category category = null;
        if (request.getParameter("selectedCategory") != null &&
                !Objects.equal(request.getParameter("selectedCategory"), "All Categories")) {
            category = new Category(request.getParameter("selectedCategory"));
            category.setId(CategoryDAO.getCategoryIdByName(category));
        }
        return category;
    }

    public static DocumentType getSelectedDocumentType(HttpServletRequest request) {
        DocumentType documentType = null;
        if (request.getParameter("selectedDocumentType") != null &&
                !Objects.equal(request.getParameter("selectedDocumentType"), "All Document Types")) {
            documentType = new DocumentType(request.getParameter("selectedDocumentType"));
            documentType.setId(DocumentTypeDAO.getDocumentTypeIdByName(documentType));
        }
        return documentType;
    }

    public static Language getSelectedLanguage(HttpServletRequest request) {
        Language language = null;
        if (request.getParameter("selectedLanguage") != null &&
                !Objects.equal(request.getParameter("selectedLanguage"), "All Languages")) {
            language = new Language(request.getParameter("selectedLanguage"));
            language.setId(LanguageDAO.getLanguageIdByName(language));
        }
        return language;
    }

    public static void deleteBookFromBookList(Book checkedBook) {
        Iterator<Book> iterator = BookContentDisplayService.bookList.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId() == checkedBook.getId()) {
                iterator.remove();
                break;
            }
        }
    }

    public static void updateBookFromBookList(Book checkedBook, Connection connection) {
        for (int i = 0; i < BookContentDisplayService.bookList.size(); i++) {
            if (BookContentDisplayService.bookList.get(i).getId() == checkedBook.getId()) {
                BookContentDisplayService.bookList.set(i, BookDAO.getBookById(connection, checkedBook.getId()).get(0));
                break;
            }
        }
    }

}
