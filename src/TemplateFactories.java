import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class ExistingFileFactory implements DocumentFactory {
    private final String filePath;
    public ExistingFileFactory(String filePath) { this.filePath = filePath; }

    @Override
    public Document createDocument() {
        DocumentBuilder builder = new DocumentBuilder("Файловый документ", filePath);
        boolean sectionOpen = false;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("[Section:") && line.endsWith("]")) {
                    if (sectionOpen) {
                        builder.endSection();
                    }
                    String sectionName = line.substring(9, line.length() - 1).trim();
                    builder.addSection(sectionName);
                    sectionOpen = true;
                } else if (line.contains("=")) {
                    String[] parts = line.split("=", 2);
                    builder.addLeaf(parts[0].trim(), parts[1].trim());
                }
            }
            if (sectionOpen) {
                builder.endSection();
            }
        } catch (IOException e) {
            System.out.println("-> Ошибка чтения файла: " + e.getMessage());
        }
        return builder.build();
    }
}

class ContractTemplateFactory implements DocumentFactory {
    private final String filePath;
    public ContractTemplateFactory(String filePath) { this.filePath = filePath; }

    @Override
    public Document createDocument() {
        return new DocumentBuilder("Договор", filePath)
                .addSection("Преамбула")
                .addLeaf("Дата", "24.05.2026")
                .addLeaf("Город", "Нижний Новгород")
                .endSection()
                .addSection("Стороны")
                .addLeaf("Продавец", "ООО Вектор")
                .addLeaf("Покупатель", "Физическое Лицо")
                .endSection()
                .build();
    }
}

class ReportTemplateFactory implements DocumentFactory {
    private final String filePath;
    public ReportTemplateFactory(String filePath) { this.filePath = filePath; }

    @Override
    public Document createDocument() {
        return new DocumentBuilder("Отчет", filePath)
                .addSection("Метаданные")
                .addLeaf("Департамент", "Разработка")
                .addLeaf("Квартал", "Q2")
                .endSection()
                .addSection("Показатели")
                .addLeaf("Затраты", "50000")
                .addLeaf("Выручка", "120000")
                .endSection()
                .build();
    }
}

class DefaultTemplateFactory implements DocumentFactory {
    private final String filePath;
    public DefaultTemplateFactory(String filePath) { this.filePath = filePath; }

    @Override
    public Document createDocument() {
        return new DocumentBuilder("Черновик", filePath)
                .addSection("Основной раздел")
                .addLeaf("Название", "Новый документ")
                .addLeaf("Автор", "Пользователь")
                .endSection()
                .build();
    }
}