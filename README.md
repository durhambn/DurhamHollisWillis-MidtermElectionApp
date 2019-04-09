# CSCI_360_Project
Election Software application end of semester project for CSCI 360.

Requirements:
1. [JDBC MySQL Connector Version 5.1.47](https://dev.mysql.com/downloads/connector/j/ "MySQL :: Download Connector/J")
   (Be sure to click on "Looking for previous GA versions?" and grab the 5.1.47 platform independent version)
2. [MySQL Community Server Version 5.7.25](https://dev.mysql.com/downloads/mysql/ "MySQL :: Download MySQL Community Server")
   (Be sure to click on "Looking for previous GA versions?" and grab the 5.7.25 version that fits your operating system.)
3. JUnit 4 (For the JUnit Testing package to work)
4. Java 8

Instructions on building the application:
1. Download code
2. Configure the Build Path to have JUnit 4 and mysql-connector-java-5.1.47-bin.jar
2. In database.java, com.csci360.electionap.model.database.java, on line 49 and 52 change username and password to your mySQL information.
3. Start a process of mySQL database server:
   - Mac:
      ```console
      foo@bar: sudo /usr/local/mysql/support-files/mysql.server start
      ```
   - Linux:
     ```console
     foo@bar: /etc/init.d/mysqld start 
     ```
   - Windows:
     ```console
     C:\> "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysqld"
     ```
4. Run main.java

Instructions on running the application:
