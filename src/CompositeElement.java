import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CompositeElement implements DocumentComponent {
    private final String name;
    private final List<DocumentComponent> children = new ArrayList<>();

    public CompositeElement(String name) {
        this.name = name;
    }

    @Override
    public String getName() { return name; }

    @Override
    public String getValue() { return ""; }

    @Override
    public void setValue(String value) {
        throw new UnsupportedOperationException("Cannot set value to a composite element.");
    }

    @Override
    public void add(DocumentComponent component) { children.add(component); }

    @Override
    public void remove(DocumentComponent component) { children.remove(component); }

    @Override
    public List<DocumentComponent> getChildren() { return children; }

    @Override
    public void print(String indent) {
        System.out.println(indent + "[+] " + name);
        for (DocumentComponent child : children) {
            child.print(indent + " ");
        }
    }

    @Override
    public void writeToFile(BufferedWriter writer) throws IOException {
        if (!name.equals("RootFileDocument")) {
            writer.write("[Section: " + name + "]");
            writer.newLine();
        }
        for (DocumentComponent child : children) {
            child.writeToFile(writer);
        }
    }

    @Override
    public DocumentComponent clone() {
        CompositeElement clone = new CompositeElement(this.name);
        for (DocumentComponent child : this.children) {
            clone.add(child.clone());
        }
        return clone;
    }
}