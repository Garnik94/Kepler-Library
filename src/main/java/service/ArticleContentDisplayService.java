package service;

import model.SearchingOption;
import model.content.*;
import service.dao.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

import static service.dao.CategoryDAO.getAllCategories;
import static service.dao.CategoryDAO.setCategories;
import static service.dao.DocumentTypeDAO.getAllDocumentTypes;
import static service.dao.DocumentTypeDAO.setDocumentTypes;
import static service.dao.LanguageDAO.getAllLanguages;
import static service.dao.LanguageDAO.setLanguages;

public class ArticleContentDisplayService {

    public static List<Article> articleList = new ArrayList<>();

    public static void mainSearch(HttpServletRequest request) {
        HttpSession session = request.getSession();
        SearchingOption searchingOption = (SearchingOption) session.getAttribute("searchingOption");
        articleList.clear();
        setCategories(getAllCategories());
        setLanguages(getAllLanguages());
        setDocumentTypes(getAllDocumentTypes());
        if (searchingOption.getSearchBy().equals("Author")) {
            searchArticlesByAuthor(searchingOption.getInputSearchOption());
        } else if (searchingOption.getSearchBy().equals("Title")) {
            searchArticlesByTitle(searchingOption.getInputSearchOption());
        }
        filterArticlesByLanguage(searchingOption.getLanguage());
        filterArticlesByCategory(searchingOption.getCategory());
        filterArticlesByDocumentType(searchingOption.getDocumentType());
        filterCategories(articleList);
        filterLanguages(articleList);
        filterDocumentTypes(articleList);
    }

    public static void searchArticlesByAuthor(String author) {
        articleList = ArticleDAO.getArticlesByAuthor(author);
    }

    public static void searchArticlesByTitle(String title) {
        articleList = ArticleDAO.getArticlesByTitle(title);
    }

    public static void filterArticlesByLanguage(Language language) {
        if (language != null) {
            Iterator<Article> iterator = articleList.iterator();
            while (iterator.hasNext()) {
                Article article = iterator.next();
                if (article.getLanguage().getId() != language.getId()) {
                    iterator.remove();
                }
            }
        }
    }

    public static void filterArticlesByCategory(Category category) {
        if (category != null) {
            Iterator<Article> iterator = articleList.iterator();
            while (iterator.hasNext()) {
                Article article = iterator.next();
                if (article.getCategory().getId() != category.getId()) {
                    iterator.remove();
                }
            }
        }
    }

    public static void filterArticlesByDocumentType(DocumentType documentType) {
        if (documentType != null) {
            Iterator<Article> iterator = articleList.iterator();
            while (iterator.hasNext()) {
                Article article = iterator.next();
                if (article.getDocumentType().getId() != documentType.getId()) {
                    iterator.remove();
                }
            }
        }
    }

    public static void sortArticlesByTitle(int askDesk) {
        if (askDesk == 1) {
            Collections.sort(articleList, new Comparator<Article>() {
                @Override
                public int compare(Article o1, Article o2) {
                    return o2.getTitle().compareTo(o1.getTitle());
                }
            });
        } else if (askDesk == -1) {
            Collections.sort(articleList, new Comparator<Article>() {
                @Override
                public int compare(Article o1, Article o2) {
                    return o1.getTitle().compareTo(o2.getTitle());
                }
            });
        }
    }

    public static void sortArticlesByYear(int askDesk) {
        if (askDesk == 1) {
            Collections.sort(articleList, new Comparator<Article>() {
                @Override
                public int compare(Article o1, Article o2) {
                    return o2.getYear() - o1.getYear();
                }
            });
        } else if (askDesk == -1) {
            Collections.sort(articleList, new Comparator<Article>() {
                @Override
                public int compare(Article o1, Article o2) {
                    return o1.getYear() - o2.getYear();
                }
            });
        }
    }

    public static void sortArticlesByRecentlyAdded() {
        Collections.sort(articleList, new Comparator<Article>() {
            @Override
            public int compare(Article o1, Article o2) {
                return o1.getId() - o2.getId();
            }
        });
    }


    public static void filterCategories(List<Article> articles) {
        List<Category> categories = CategoryDAO.getCategories();
        Set<Category> filteredCategories = new LinkedHashSet<>();
        for (Article article : articles) {
            for (Category category : categories) {
                if (category.getId() == article.getCategory().getId()) {
                    filteredCategories.add(category);
                }
            }
        }
        setCategories(new ArrayList<>(filteredCategories));
    }

    public static void filterLanguages(List<Article> articles) {
        List<Language> languages = LanguageDAO.getLanguages();
        Set<Language> filteredLanguages = new LinkedHashSet<>();
        for (Article article : articles) {
            for (Language language : languages) {
                if (language.getId() == article.getLanguage().getId()) {
                    filteredLanguages.add(language);
                }
            }
        }
        LanguageDAO.setLanguages(new ArrayList<>(filteredLanguages));
    }

    public static void filterDocumentTypes(List<Article> articles) {
        List<DocumentType> documentTypes = DocumentTypeDAO.getDocumentTypes();
        Set<DocumentType> filterDocumentTypes = new LinkedHashSet<>();
        for (Article article : articles) {
            for (DocumentType documentType : documentTypes) {
                if (documentType.getId() == article.getDocumentType().getId()) {
                    filterDocumentTypes.add(documentType);
                }
            }
        }
        DocumentTypeDAO.setDocumentTypes(new ArrayList<>(filterDocumentTypes));
    }
}
