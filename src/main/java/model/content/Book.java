package model.content;

public class Book {

    private int Id;
    private Author author;
    private String title;
    private Category category;
    private Language language;
    private int year;
    private int pages;
    private DocumentType documentType;
    private String downloadUrl;

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
        this.author = author;
        this.title = title;
        this.category = category;
        this.language = language;
        this.year = year;
        this.documentType = documentType;
        this.pages = pages;
        this.downloadUrl = downloadUrl;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return super.toString() + "\n" + pages;
    }
}
