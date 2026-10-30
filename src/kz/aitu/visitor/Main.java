package kz.aitu.visitor;

import kz.aitu.visitor.element.Directory;
import kz.aitu.visitor.element.File;
import kz.aitu.visitor.visitor.SearchVisitor;
import kz.aitu.visitor.visitor.SizeCalculatorVisitor;
import kz.aitu.visitor.visitor.TreePrinterVisitor;

public class Main {

    private static final long KILOBYTE = 1024;
    private static final long LARGE_FILE_BYTES = 100 * KILOBYTE;

    public static void main(String[] args) {
        Directory sourceCode = new Directory("src")
                .add(new File("Main.java", 4 * KILOBYTE))
                .add(new File("Visitor.java", 2 * KILOBYTE));
        Directory documents = new Directory("docs")
                .add(new File("report.pdf", 250 * KILOBYTE))
                .add(new File("uml.png", 80 * KILOBYTE));
        Directory images = new Directory("images")
                .add(new File("logo.png", 120 * KILOBYTE));
        Directory project = new Directory("project")
                .add(sourceCode)
                .add(documents)
                .add(images)
                .add(new File("README.md", 3 * KILOBYTE));

        printTree(project);
        printTotalSize(project);
        printTotalSize(documents);
        printSearchResult(project, "*.java files", new SearchVisitor(file -> file.getExtension().equals("java")));
        printSearchResult(project, "files larger than 100 KB",
                new SearchVisitor(file -> file.getSizeBytes() > LARGE_FILE_BYTES));
    }

    private static void printTree(Directory directory) {
        TreePrinterVisitor treePrinter = new TreePrinterVisitor();
        directory.accept(treePrinter);
        System.out.println(treePrinter.getTree());
    }

    private static void printTotalSize(Directory directory) {
        SizeCalculatorVisitor sizeCalculator = new SizeCalculatorVisitor();
        directory.accept(sizeCalculator);
        System.out.printf("Total size of '%s': %d KB%n",
                directory.getName(), sizeCalculator.getTotalSizeBytes() / KILOBYTE);
    }

    private static void printSearchResult(Directory directory, String description, SearchVisitor searchVisitor) {
        directory.accept(searchVisitor);
        System.out.println("Search for " + description + ":");
        searchVisitor.getMatchingPaths().forEach(path -> System.out.println("  " + path));
    }
}
