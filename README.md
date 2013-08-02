# Automation Environment Installation/Setup

Install Core Dependencies
-------------------------

* Download & Install JDK 1.7(Java SE Development Kit 7u9)
*  Windows
 * [Win 32-Windows x86](http://www.oracle.com/technetwork/java/javase/downloads/jdk7u9-downloads-1859576.html)
 * [Win 64-Windows x64](http://www.oracle.com/technetwork/java/javase/downloads/jdk7u9-downloads-1859576.html)
 * [Set Path](http://java.com/en/download/help/path.xml)

Verify JDK is configured correctly
 * java -version
 * javac -version	
	
	
Install Ant 1.8.4 or higher
---------------------------
Requirement: Ant 1.8.4

	  i> Installing Ant on Windows
		* Enter the URL: http://ant.apache.org/bindownload.cgi
                * On the Apache Ant Project page, find the heading 'Current Release of Ant.'
                * Select apache-ant-1.8.4-bin.zip [PGP] [SHA1] [SHA512] [MD5]
                * Click Save to unzip and save it to your C:\ directory 
                * Set Environment Variable; follow 
                *http://www.daimi.au.dk/~hbc/technical/ant/setup.html
     ii> Verify ANT installed correctly 
                a> open terminal
                b> ant -version
         

Setup Property Files- config package
--------------------
**Note:** 'config' property file needs to be placed at \src\com\intelegencia\tests\config folder in our project structure.  Please contact joshua for a copy.



Run Automation
--------------
Automation can run when above steps are followed in order.

      i> Open cmd
     ii> Navigate to *BASEDIR* (working directory Path where build.xml is located)
    iii> Run Automation
               ______________________________________________________________________________________________________
            a> ant clean compile run makexsltreports 
		