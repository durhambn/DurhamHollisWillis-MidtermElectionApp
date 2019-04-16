CSCI_360_Project
======
## Election Software application end of semester project for CSCI 360.

### Requirements:
1. [JDBC MySQL Connector Version 5.1.47](https://dev.mysql.com/downloads/connector/j/ "MySQL :: Download Connector/J")
   (Be sure to click on "Looking for previous GA versions?" and grab the 5.1.47 platform independent version)
2. [MySQL Community Server Version 5.7.25](https://dev.mysql.com/downloads/mysql/ "MySQL :: Download MySQL Community Server")
   (Be sure to click on "Looking for previous GA versions?" and grab the 5.7.25 version that fits your operating system.)
3. JUnit 4 (For the JUnit Testing package to work)
4. Java 8

### Instructions on building the application:
1. Clone the repository
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

### Adding Test Voters & Removing Them:
* **You can only have 1 Test Voter at a time using the Admin Add Tester button**
1. You can add and remove test voters from inside the Admin login section.
2. The user name is **admin** and the password is **M.^9_6'Pdh'#&qXz** (The password is also inside the admin.txt file inside the model package)
3. The Test Voters username will be set to **testUser** and the password will be set to **TestUser123***
4. The application will automatically set the created date of the Test Voter to a date that allows them to vote for easy testing while also creating a way to prevent back door entries so long as you remember to delete the Test Voter.

### Instructions on running the application:
   #### Key points to take note of:
   * The database that is used for this application is created during the initialization phase.
   * The tables that are used as well as the mock candidates that are used are created during the initialization phase.
   * The database.java file has instructions for common connection errors.
   * In order to vote a person must be registered first and from the time that the voter is created 24 hours must pass.
      In order to bypass this do the following:
     1. Login to mysql (Assumes that you have ran main.java and have added at least one voter to the database)
            
         ```console
         foo@bar: /path/to/mysql -u <USERNAME> -p <PASSWORD> election_database
         ```
         (This should connect you to the database that the project uses if not omit election_database then run USE election_database inside MySQL)
      2. Inside MySQL run 
         ```MySQL
         mysql> UPDATE VOTERS SET created="2019-04-05 17:21:49" WHERE username=<Your Username Here>;
         ```
         (The username is shown to you after registering a voter and is supplied in the console as well. This will enable the voter to vote and tell the database that more than 24 hours have elapsed. Note: Any time can be used in the created date as long as it is 24 hours before the current time.)
   * At current the default Admin login is set up by reading from a file labeled admin.txt
   
### Information on Logs:
There are 2 log files being created as the application runs.
1. log.txt  
This is a log of every action that occurs as the application runs. For example, it logs when a user registers or when a user votes. There is no sensitive information stored in this log.
2. backupBallot.txt  
This a backup file for our ballot database table. It keeps a record of each ballot submitted. On each line there is a new ballot with the winner of the categories name listed.   
**If you delete the election_database database, also delete the logs.**

