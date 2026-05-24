import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Document {
    private final String docType;
    private DocumentComponent root;
    private final String associatedFilePath;

    public Document(String docType, DocumentComponent root, String associatedFilePath) {
        this.docType = docType;
        this.root = root;
        this.associatedFilePath = associatedFilePath;
    }

    public DocumentComponent getRoot() { return root; }
    public void setRoot(DocumentComponent root) { this.root = root; }

    public DocumentMemento save() { return new DocumentMemento(root); }

    public void restore(DocumentMemento memento) {
        this.root = memento.getSavedState();
        saveToFile();
    }

    public void display() {
        System.out.println("\n=== Содержимое файла [" + docType + "] ===");
        root.print("");
        System.out.println("========================================");
    }

    public void saveToFile() {
        if (associatedFilePath == null) return;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(associatedFilePath))) {
            root.writeToFile(writer);
        } catch (IOException e) {
            System.out.println("-> Ошибка сохранения в файл: " + e.getMessage());
        }
    }
}