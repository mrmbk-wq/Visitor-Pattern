package kz.aitu.visitor.visitor;

import kz.aitu.visitor.element.Directory;
import kz.aitu.visitor.element.File;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.function.Predicate;

public class SearchVisitor implements FileSystemVisitor {

    private final Predicate<File> criterion;
    private final Deque<String> currentPath = new ArrayDeque<>();
    private final List<String> matchingPaths = new ArrayList<>();

    public SearchVisitor(Predicate<File> criterion) {
        this.criterion = criterion;
    }

    @Override
    public void visitFile(File file) {
        if (criterion.test(file)) {
            matchingPaths.add(pathOf(file.getName()));
        }
    }

    @Override
    public void visitDirectory(Directory directory) {
        currentPath.addLast(directory.getName());
        directory.getChildren().forEach(child -> child.accept(this));
        currentPath.removeLast();
    }

    public List<String> getMatchingPaths() {
        return Collections.unmodifiableList(matchingPaths);
    }

    private String pathOf(String fileName) {
        return String.join("/", currentPath) + "/" + fileName;
    }
}
