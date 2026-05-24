import java.util.Date;

public class DocumentMemento {
    private final DocumentComponent rootState;
    private final Date timestamp;

    public DocumentMemento(DocumentComponent state) {
        this.rootState = state.clone();
        this.timestamp = new Date();
    }

    public DocumentComponent getSavedState() { return rootState.clone(); }
    public Date getTimestamp() { return timestamp; }
}