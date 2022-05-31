package model;

import model.content.Category;
import model.content.DocumentType;
import model.content.Journal;
import model.content.Language;

public class SearchingOption {

    private String inputSearchOption;
    private boolean isTitleSelected;
    private boolean isAuthorSelected;
    private Category category;
    private DocumentType documentType;
    private Journal journal;
    private Language language;

    public SearchingOption(String inputArgument,
                           String isTitleSelected,
                           String isAuthorSelected,
                           Category category,
                           DocumentType documentType,
                           Language language) {
        this.inputSearchOption = inputArgument;
        setTitleSelected(isTitleSelected);
        setAuthorSelected(isAuthorSelected);
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

    public boolean isTitleSelected() {
        return isTitleSelected;
    }

    public void setTitleSelected(String titleSelected) {
        isTitleSelected = titleSelected.equals("checked");
    }

    public boolean isAuthorSelected() {
        return isAuthorSelected;
    }

    public void setAuthorSelected(String authorSelected) {
        isAuthorSelected = authorSelected.equals("checked");
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

    @Override
    public String toString() {
        return "SearchingOption{" +
                "inputArgument='" + inputSearchOption + '\'' +
                ", isTitleSelected=" + isTitleSelected +
                ", isAuthorSelected=" + isAuthorSelected +
                ", category=" + category +
                ", documentType=" + documentType +
                ", journal=" + journal +
                ", language=" + language +
                '}';
    }
}
