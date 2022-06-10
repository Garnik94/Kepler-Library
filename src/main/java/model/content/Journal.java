package model.content;

public class Journal implements Comparable<Journal> {

    private int Id;
    private String journalName;

    public Journal(String journal) {
        this.journalName = journal;
    }

    public String getJournalName() {
        return journalName;
    }

    public void setJournalName(String journalName) {
        this.journalName = journalName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int compareTo(Journal journal) {
        return this.journalName.compareTo(journal.getJournalName());

    }

    @Override
    public String toString() {
        return journalName;
    }

}
