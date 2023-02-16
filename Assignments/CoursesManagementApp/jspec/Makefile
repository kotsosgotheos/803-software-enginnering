NAME = jspec

JAVA = java
CC = $(JAVA)c
OPT =
VERSION =

JAR = jar
JAR_OPT = -cvf

FLAGS =
WARNINGS =
REMOVE_WARN =
HEADERS =
#LIBS = -classpath ./libs/junit-4.13.1.jar:./libs/commons-io-2.6.jar

INPUT = jspec/*.java
CLASSFILES = jspec/*.class
OUTPUT = $(NAME).jar
EXPORT = export
EXAMPLES = examples

all: compiler

compiler:
	$(CC) $(OPT) $(VERSION) $(HEADERS) $(FLAGS) $(WARNINGS) $(REMOVE_WARN) $(LIBS) $(INPUT)
	$(JAR) $(JAR_OPT) $(OUTPUT) $(CLASSFILES)
	$(RM) -rf $(EXPORT)
	mkdir $(EXPORT)
	cp $(OUTPUT) $(EXPORT)/
	$(RM) $(OUTPUT)
	$(RM) $(CLASSFILES)
	@echo

clean:
	$(RM) $(CLASSFILES)
	$(RM) $(EXPORT)/$(OUTPUT)
