import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

public interface DocumentComponent extends Cloneable {
    String getName();
    String getValue();
    void setValue(String value);
    void add(DocumentComponent component);
    void remove(DocumentComponent component);
    List<DocumentComponent> getChildren();
    void print(String indent);
    void writeToFile(BufferedWriter writer) throws IOException;
    DocumentComponent clone();
}