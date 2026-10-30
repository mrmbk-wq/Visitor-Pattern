package kz.aitu.visitor.visitor;

import kz.aitu.visitor.element.Directory;
import kz.aitu.visitor.element.File;

public class TreePrinterVisitor implements FileSystemVisitor {

    private static final String INDENT = "    ";
    private static final long KILOBYTE = 1024;

    private final StringBuilder tree = new StringBuilder();
    private int depth;

    @Override
    public void visitFile(File file) {
        appendLine(file.getName() + " (" + file.getSizeBytes() / KILOBYTE + " KB)");
    }

    @Override
    public void visitDirectory(Directory directory) {
        appendLine(directory.getName() + "/");
        depth++;
        directory.getChildren().forEach(child -> child.accept(this));
        depth--;
    }

    public String getTree() {
        return tree.toString();
    }

    private void appendLine(String text) {
        tree.append(INDENT.repeat(depth)).append(text).append(System.lineSeparator());
    }
}
