package model;

import model.content.Category;
import model.content.DocumentType;
import model.content.Journal;
import model.content.Language;

public class SearchingOption {

    private String inputArgument;
    private boolean isTitleSelected;
    private boolean isAuthorSelected;
    private Category category;
    private DocumentType documentType;
    private Journal journal;
    private Language language;

    public String getInputArgument() {
        return inputArgument;
    }

    public void setInputArgument(String inputArgument) {
        this.inputArgument = inputArgument;
    }

    public boolean isTitleSelected() {
        return isTitleSelected;
    }

    public void setTitleSelected(boolean titleSelected) {
        isTitleSelected = titleSelected;
    }

    public boolean isAuthorSelected() {
        return isAuthorSelected;
    }

    public void setAuthorSelected(boolean authorSelected) {
        isAuthorSelected = authorSelected;
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
}
