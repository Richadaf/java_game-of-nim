javac Nim.java
java Nim < $1.txt > out
diff $1output.txt out
