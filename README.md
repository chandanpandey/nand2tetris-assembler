# nand2tetris-assembler
First build the project using your ide.

To build a jar file please run "mvn clean package" in your terminal.

You will find "assembler-1.0-SNAPSHOT.jar" in the "target" directory. You can use or distribute the jar file.

To convert a .asm file to .hack file please use following command:
"assembler-1.0-SNAPSHOT.jar yourfilename.asm"
File named "yourfilename.hack" will be produced in the working directory. To run "assembler-1.0-SNAPSHOT.jar yourfilename.asm" please use your shell specific command based on OS and shell for example on linux it would be "java -jar assembler-1.0-SNAPSHOT.jar pong.asm"  

Exceptions are not written in the program. May be I write exception in the future or maybe you will.
