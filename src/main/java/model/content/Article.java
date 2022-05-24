package model.content;

public class Article extends AbstractMaterial {

    private Journal journal;

    public Article() {

    }

    public Article(Author author,
                   String title,
                   Category category,
                   Language language,
                   int year,
                   DocumentType documentType,
                   Journal journal,
                   String downloadUrl) {
        super(author, title, category, language, year, documentType, downloadUrl);
        this.journal = journal;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }
}
