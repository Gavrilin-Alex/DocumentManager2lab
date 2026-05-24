import java.util.Stack;

public class DocumentBuilder {
    private final String docType;
    private final String filePath;
    private final CompositeElement root;
    private final Stack<CompositeElement> sectionStack = new Stack<>();

    public DocumentBuilder(String docType, String filePath) {
        this.docType = docType;
        this.filePath = filePath;
        this.root = new CompositeElement("RootFileDocument");
        this.sectionStack.push(root);
    }

    public DocumentBuilder addSection(String sectionName) {
        CompositeElement newSection = new CompositeElement(sectionName);
        sectionStack.peek().add(newSection);
        sectionStack.push(newSection);
        return this;
    }

    public DocumentBuilder endSection() {
        if (sectionStack.size() > 1) {
            sectionStack.pop();
        }
        return this;
    }

    public DocumentBuilder addLeaf(String name, String value) {
        LeafElement leaf = new LeafElement(name, value);
        sectionStack.peek().add(leaf);
        return this;
    }

    public Document build() { return new Document(docType, root, filePath); }
}
