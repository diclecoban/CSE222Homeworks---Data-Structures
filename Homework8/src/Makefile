JC = javac
JFLAGS = -classpath .
JD = javadoc
JDFLAGS = -private -splitindex -use -author -version -d ./javadoc
RM = rm
JR = java -ea

CLASSES = \
		Main.java \
		Person.java \
		SocialNetworkGraph.java \
		Menu.java

all : Main.class

run : 
	$(JR) Main

classes : $(CLASSES:.java=.class)

%.class : %.java
	$(JC) $(JFLAGS) $<

doc:
	$(JD) $(JDFLAGS) *.java 

clean:
	$(RM) *.class 

cleandoc:
	$(RM) -r ./javadoc
