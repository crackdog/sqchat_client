JC = javac
JFLAGS = #-g

PROG = Sqchat
JAR = sqchat.jar
MFILE = Manifest.txt

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Sqchat.java \
        Connection.java #\
        #Library.java \
        #Main.java 

$(PROG): classes

classes: $(CLASSES:.java=.class)

jar: $(PROG) $(JAR)

$(JAR): $(RPOG)
	jar -cvfm $(JAR) $(MFILE) $(CLASSES:.java=.class)

.PHONY: clean
clean:
	rm -f *.class $(JAR)

