PolyColorSpace.jar: classes.txt
	if [ -f PolyColorSpace.jar ] ; then \
		rm PolyColorSpace.jar ; \
	fi
	jar cfn PolyColorSpace.jar @classes.txt

classes.txt: sources.txt
	javac @sources.txt
	echo "compiled sources"
	find io -name "*.class" > classes1.txt
	printf "\n" >> classes1.txt
	if [ -f classes.txt ] ; then \
		if diff classes.txt classes1.txt > /dev/null ; then \
			rm classes1.txt ; \
			touch classes.txt ; \
			echo "classes.txt >> has not been modified" ; \
		else \
			rm classes.txt ; \
			mv classes1.txt classes.txt ; \
			echo "classes.txt >> has been updated" ; \
		fi ; \
	else \
		mv classes1.txt classes.txt ; \
		echo "classes.txt >> has been created" ; \
	fi

SOURCES := $(find io -name "*.java")
sources.txt: $(SOURCES)
	find io -name "*.java" > sources1.txt
	printf "\n" >> sources1.txt
	if [ -f sources.txt ] ; then \
		if diff sources.txt sources1.txt > /dev/null ; then \
			rm sources1.txt ; \
			touch sources.txt ; \
			echo "sources.txt has not been modified" ; \
		else \
			rm sources.txt ; \
			mv sources1.txt sources.txt ; \
			echo "sources.txt has been updated" ; \
		fi ; \
	else \
		mv sources1.txt sources.txt ; \
		echo "sources.txt has been created" ; \
	fi

clean:
	rm -f $(cat classes.txt)
	rm -f sources.txt
	rm -f classes.txt
	rm -f PolyColorSpace.jar

run: PolyColorTest.class PolyColorSpace.jar
	java -cp PolyColorSpace.jar:. PolyColorTest $(ARGS)

PolyColorTest.class: PolyColorTest.java
	javac -cp PolyColorSpace.jar:. PolyColorTest.java

