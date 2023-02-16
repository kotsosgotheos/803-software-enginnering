JAVA = java
CC = $(JAVA)c
OPT =
VERSION =

FLAGS =
WARNINGS =
REMOVE_WARN =
HEADERS =
LIBS = -classpath ./libs/jspec.jar:./libs/commons-math3-3.6.1.jar:./libs/sqlite-jdbc-3.36.0.3.jar:.

APPINPUT = app/src/**/*.java
APPTESTINPUT = app/test/**/*.java app/test/TestRunner.java
DBINPUT = persistence/**/src/*.java persistence/*.java
DBTESTINPUT = persistence/**/test/*.java
STATSINPUT = statistics/**/*.java statistics/*.java
CLIINPUT = client/cli/*.java
WEBINPUT =

APPOUTPUT = app/src/**/*.class
APPTESTOUTPUT = app/test/**/*.class app/test/*.class
DBOUTPUT = persistence/**/src/*.class persistence/*.class
DBTESTOUTPUT = persistence/**/test/*.class persistence/*.class persistence/**/src/*.db
STATSOUTPUT = statistics/**/*.class statistics/*.class
CLIOUTPUT = client/cli/*.class
WEBOUTPUT = target

all: compile

compile: compile_jspec
	$(CC) $(LIBS) $(OPT) $(VERSION) $(HEADERS) $(FLAGS) $(WARNINGS) $(REMOVE_WARN) $(APPINPUT) $(DBINPUT) $(STATSINPUT)
	@echo

compile_jspec:
	cd jspec && make
	mv jspec/export/jspec.jar libs/

test: compile
	$(CC) $(LIBS) $(OPT) $(VERSION) $(HEADERS) $(FLAGS) $(WARNINGS) $(REMOVE_WARN) $(APPTESTINPUT)
	$(JAVA) $(LIBS) app/test/TestRunner

testdb: compile
	$(CC) $(LIBS) $(OPT) $(VERSION) $(HEADERS) $(FLAGS) $(WARNINGS) $(REMOVE_WARN) $(DBINPUT) $(DBTESTINPUT)
	$(JAVA) $(LIBS) persistence/TestRunner

cli: compile
	$(CC) $(LIBS) $(OPT) $(VERSION) $(HEADERS) $(FLAGS) $(WARNINGS) $(REMOVE_WARN) $(CLIINPUT)
	$(JAVA) $(LIBS) client/cli/EntryPoint

web: compile
	./mvnw spring-boot:run

clean:
	$(RM) -rf $(APPOUTPUT) $(APPTESTOUTPUT) $(CLIOUTPUT) $(GUIOUTPUT) $(WEBOUTPUT) $(DBOUTPUT) $(DBTESTOUTPUT) $(STATSOUTPUT) $(WEBOUTPUT)
