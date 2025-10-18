JC = javac
JFLAGS = -g
TARGET = Main
SOURCES = Main.java AVLTree.java GUIVisualization.java Stock.java StockDataManager.java
ARGS ="input.txt"

default: $(TARGET)

$(TARGET): $(SOURCES)
	$(JC) $(JFLAGS) $^
	java -Xint  $(TARGET) $(ARGS)

clean:
	rm -f *.class

javadoc:
	javadoc -d javadoc -author -version -private -linksource $(SOURCES)

jclean:
	rm -rf javadoc

.PHONY: clean default
