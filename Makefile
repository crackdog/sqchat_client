JC = javac
JFLAGS = #-verbose

PROG = Sqchat
JAR = sqchat.jar
MFILE = Manifest.txt

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
        Sqchat.java \
        sqchat/Connection.java \
        sqchat/Encryption.java \
        sqchat/Client.java \
        sqchat/Contact.java \
        gui/Frame.java 

$(PROG): classes

classes: $(CLASSES:.java=.class)

jar: $(JAR)

$(JAR): $(RPOG) classes
	jar -cvfm $(JAR) $(MFILE) $(CLASSES:.java=.class)

.PHONY: clean
clean:
	rm -f $(CLASSES:.java=.class) $(JAR)

