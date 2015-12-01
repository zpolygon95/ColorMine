jar: classes.txt
	if [ -f dist/PolyColorSpace.jar ] ; then \
		rm dist/PolyColorSpace.jar ; \
	fi
	jar cfn dist/PolyColorSpace.jar @classes.txt

classes.txt: sources.txt
	javac @sources.txt -d ./build/main
	echo "compiled sources"
	find build/main -name "*.class" > classes1.txt
	echo "\n" >> classes1.txt
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

SOURCES := $(find src -name "*.java")
sources.txt: $(SOURCES)
	find src -name "*.java" > sources1.txt
	echo "\n" >> sources1.txt
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
	rm sources.txt
	rm classes.txt
	rm -rf build/main/*
	rm dist/*

test:
	javac -cp dist/PolyColorSpace.jar:. build/test/PolyColorTest.java
	

run:

