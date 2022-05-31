package model;

import model.content.Category;
import model.content.DocumentType;
import model.content.Journal;
import model.content.Language;

public class SearchingOption {

    private String inputSearchOption;
    private String searchBy;
    private Category category;
    private DocumentType documentType;
    private Journal journal;
    private Language language;

    public SearchingOption(String inputSearchOption,
                           String searchBy,
                           Category category,
                           DocumentType documentType,
                           Language language) {
        this.inputSearchOption = inputSearchOption;
        this.searchBy = searchBy;
        this.category = category;
        this.documentType = documentType;
        this.language = language;
    }

    public String getInputSearchOption() {
        return inputSearchOption;
    }

    public void setInputSearchOption(String inputSearchOption) {
        this.inputSearchOption = inputSearchOption;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    @Override
    public String toString() {
        return "SearchingOption{" +
                "inputSearchOption='" + inputSearchOption + '\'' +
                ", searchBy='" + searchBy + '\'' +
                ", category=" + category +
                ", documentType=" + documentType +
                ", journal=" + journal +
                ", language=" + language +
                '}';
    }
}
