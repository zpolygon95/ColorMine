jar: src
	echo "jar >> test"

src: sources.txt
	javac @sources.txt -d ./build/main
	@echo "src >> compiled sources"

sources.txt:
	find src -name "*.java" > sources1.txt
	if [ -f sources.txt ] ; then \
		if diff sources.txt sources1.txt > /dev/null ; then \
			rm sources1.txt ; \
			echo "sources.txt >> has not been modified" ; \
		else \
			rm sources.txt ; \
			mv sources1.txt sources.txt ; \
			echo "sources.txt >> has been updated" ; \
		fi ; \
	else \
		mv sources1.txt sources.txt ; \
		echo "sources.txt >> has been created" ; \
	fi

clean:
	rm sources.txt
	rm -rf build/main/*