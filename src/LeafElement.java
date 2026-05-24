import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class LeafElement implements DocumentComponent {
    private final String name;
    private String value;

    public LeafElement(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getValue() { return value; }

    @Override
    public void setValue(String value) { this.value = value; }

    @Override
    public void add(DocumentComponent component) {
        throw new UnsupportedOperationException("Leaf element cannot contain children.");
    }

    @Override
    public void remove(DocumentComponent component) {
        throw new UnsupportedOperationException("Leaf element has no children.");
    }

    @Override
    public List<DocumentComponent> getChildren() { return Collections.emptyList(); }

    @Override
    public void print(String indent) {
        System.out.println(indent + "[-] " + name + ": " + value);
    }

    @Override
    public void writeToFile(BufferedWriter writer) throws IOException {
        writer.write(name + "=" + value);
        writer.newLine();
    }

    @Override
    public DocumentComponent clone() {
        return new LeafElement(this.name, this.value);
    }
}