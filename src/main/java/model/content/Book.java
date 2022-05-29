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
                int pages,
                String downloadUrl) {
        super(author, title, category, language, year, documentType, downloadUrl);
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{" + super.toString() +
                "pages=" + pages +
                '}';
    }
}
