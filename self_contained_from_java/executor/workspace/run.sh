cd $1
jars=""
for i in *.jar lib/*.jar; 
do 
    jars=$jars$i":"; 
done
java -Dfile.encoding=UTF-8 -cp $jars. com.seniorgeek.samples.scfj.program.App
