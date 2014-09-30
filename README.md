BrlnClck
========

Simple berlin clock conversion demo

Berlin clock legal boundaries:
- Y OOOO OOOO OOOOOOOOOOO OOOO (00:00:00) >> ... >> O RRRR RRRR YYRYYRYYRYY YYYY (24:59:59)

Version: Java 7

Main class: prs.ansh.bc.driver.Main
- Repeatedly reads HH:MM:SS lines from stdin and writes in the above berlin clock text format to stdout

src/test/java got the tests

It is ready to be imported as an eclipse project.

To use in any other way
src/main got the source; lib/ has dependency jars; 

