package model.content;

public class Book extends AbstractMaterial {

    private int pages;

    public Book() {
    }

    public Book(Author author,
                String title,
                Category category,
                Language language,
                int year,
                DocumentType documentType,
                int pages) {
        super(author, title, category, language, year, documentType);
        this.pages = pages;
    }
}
