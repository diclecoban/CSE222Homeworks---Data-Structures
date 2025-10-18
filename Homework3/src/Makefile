JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -protected -splitindex -use -author -version -d ./javadoc
RM = rm
JR = java

OUT_DIR = ../out

CLASSES = \
		Inventory.java \
		Device.java \
		Main.java \
		SmartPhone.java \
		Laptop.java \
		Console.java\
		Tablet.java\
		DeviceClass.java\
		TV.java

CLASS_FILES = $(addprefix $(OUT_DIR)/,$(CLASSES:.java=.class))

all: $(CLASS_FILES)

run: $(CLASS_FILES)
	$(JR) -classpath $(OUT_DIR) Main

$(OUT_DIR)/%.class: %.java | $(OUT_DIR)
	$(JC) $(JFLAGS) $< -d $(OUT_DIR)

doc:
	$(JD) $(JDFLAGS) *.java

$(OUT_DIR):
	mkdir -p $(OUT_DIR)


clean:
	$(RM) $(OUT_DIR)/*.class

cleandoc:
	$(RM) -r ./javadoc
