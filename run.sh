hdfs dfs -rm -r -f /user/iedrc/temp

rm Triangle*.class
hadoop com.sun.tools.javac.Main TriangleCount.java
jar cf triangle.jar Triangle*.class