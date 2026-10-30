package kz.aitu.visitor.visitor;

import kz.aitu.visitor.element.Directory;
import kz.aitu.visitor.element.File;

public class SizeCalculatorVisitor implements FileSystemVisitor {

    private long totalSizeBytes;

    @Override
    public void visitFile(File file) {
        totalSizeBytes += file.getSizeBytes();
    }

    @Override
    public void visitDirectory(Directory directory) {
        directory.getChildren().forEach(child -> child.accept(this));
    }

    public long getTotalSizeBytes() {
        return totalSizeBytes;
    }
}
