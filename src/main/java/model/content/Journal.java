package model.content;

public class Journal {

    private int Id;
    private String journal;

    public Journal(String journal) {
        this.journal = journal;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
}
