public class ChangeAttributeCommand implements Command {
    private final Document document;
    private final String targetName;
    private final String newValue;

    public ChangeAttributeCommand(Document document, String targetName, String newValue) {
        this.document = document;
        this.targetName = targetName;
        this.newValue = newValue;
    }

    @Override
    public void execute() {
        boolean updated = updateValueRecursive(document.getRoot(), targetName, newValue);
        if (updated) {
            System.out.println("-> Значение поля '" + targetName + "' успешно изменено на '" + newValue + "' в памяти.");
        } else {
            System.out.println("-> Ошибка: Поле '" + targetName + "' не найдено в структуре документа.");
        }
    }

    private boolean updateValueRecursive(DocumentComponent current, String name, String value) {
        if (current.getName().equalsIgnoreCase(name) && current instanceof LeafElement) {
            current.setValue(value);
            return true;
        }
        for (DocumentComponent child : current.getChildren()) {
            if (updateValueRecursive(child, name, value)) return true;
        }
        return false;
    }
}