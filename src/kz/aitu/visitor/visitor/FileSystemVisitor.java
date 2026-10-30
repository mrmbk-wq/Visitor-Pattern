package kz.aitu.visitor.visitor;

import kz.aitu.visitor.element.Directory;
import kz.aitu.visitor.element.File;

public interface FileSystemVisitor {

    void visitFile(File file);

    void visitDirectory(Directory directory);
}
