# Visitor Pattern — File System Visitor

Assignment #6, ShP-2216 Software Design Patterns (Astana IT University).

A small file system is built from `File` and `Directory` elements. New operations on it
are added as visitors, without changing the element classes:

- `SizeCalculatorVisitor` — total size of a directory tree
- `SearchVisitor` — finds files that match a criterion (any `Predicate<File>`)
- `TreePrinterVisitor` — prints the tree (added later to show extensibility)

Each element's `accept(visitor)` calls `visitor.visitFile(this)` or
`visitor.visitDirectory(this)` — this is the double-dispatch step, so there is no
`instanceof` anywhere in the code.

## Project structure

```
src/kz/aitu/visitor/
├── Main.java                       # Client: builds the structure and runs the visitors
├── element/
│   ├── FileSystemElement.java      # Element interface: accept(FileSystemVisitor)
│   ├── File.java                   # Concrete element
│   └── Directory.java              # Concrete element + object structure (holds children)
└── visitor/
    ├── FileSystemVisitor.java      # Visitor interface: visitFile, visitDirectory
    ├── SizeCalculatorVisitor.java  # Concrete visitor
    ├── SearchVisitor.java          # Concrete visitor
    └── TreePrinterVisitor.java     # Concrete visitor (extensibility demo)
```

## Running

**IntelliJ IDEA:** File → Open → select this folder. Set the Project SDK to JDK 17
(File → Project Structure → Project), then run `kz.aitu.visitor.Main`.

**Command line (JDK 17+):**

```bash
javac -d out $(find src -name "*.java")
java -cp out kz.aitu.visitor.Main
```

## Sample output

```
project/
    src/
        Main.java (4 KB)
        Visitor.java (2 KB)
    docs/
        report.pdf (250 KB)
        uml.png (80 KB)
    images/
        logo.png (120 KB)
    README.md (3 KB)

Total size of 'project': 459 KB
Total size of 'docs': 330 KB
Search for *.java files:
  project/src/Main.java
  project/src/Visitor.java
Search for files larger than 100 KB:
  project/docs/report.pdf
  project/images/logo.png
```

See [REPORT.md](REPORT.md) for the UML diagram and the Clean Code analysis.
