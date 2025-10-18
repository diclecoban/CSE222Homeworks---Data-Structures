JC = javac
JFLAGS = -g
TARGET = Main
SOURCES = Main.java AVLTree.java GUIVisualization.java Stock.java StockDataManager.java


default: $(TARGET)

$(TARGET): $(SOURCES)
	$(JC) $(JFLAGS) $^
	java -Xint $(TARGET)
	java Main input.txt

clean:
	rm -f *.class

.PHONY: clean default
