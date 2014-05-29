TimesheetAutomation
===================

Automatically fill our beeline timesheet

This has been tested on a Mac only!  For Windows user, you can use the code, but you may want to get chromeDriver for windows instead.  

Steps:
- make sure you have chrome installed
- make sure you can compile/run java
- download selenium-java-2.41.0.zip from http://selenium-release.storage.googleapis.com/index.html?path=2.41/
- open project using eclipse
- unzip selenium-java-2.41.0.zip into timesheet folder, which would replace the empty selenium-2.41.0 folder in the project
- change username in Main.java
- build, and run Main.java as a java application
- watch the browser do its job, watch for both the browser and the system log on eclipse.


Future plan:
- make it to a maven project (or ant)
- make it run on windows machine
- move user out to a json/xml/properties/whatever type of storage
- make it support multiple users
- make it automatically run every friday! 
