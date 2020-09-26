# assignment-program

This is a spring boot application.
We require below software prerequisites.

1) JDK Install (1.8.0_91)
2) STS (Spring Tool Suite) 3.9.0.RELEASE
3) Gradle Version 4.6


# Step to run application.

1) Checkout the code.
2) Go to project directory.
3) Create war file using command `gradle clean build -x test`.
4) Go to `project directory > build > lib` directory where `assignment-program-0.0.1-SNAPSHOT.war` file available.
5) Run `java -jar assignment-program-0.0.1-SNAPSHOT.war` command.
6) Run url `http://localhost:8080/`
7) User  user to login on application

   Normal User:
   
          Email: user@gmail.com
          
          Password: 000000
          
   Admin User:
   
          Email: admin@gmail.com
          
          Password: 111111
 
 8) Login page (https://github.com/PiyushChaudhari/assignment-program/blob/master/login-page.JPG)
 
 9) Search atm by enter city name. (https://github.com/PiyushChaudhari/assignment-program/blob/master/locator-atm.JPG)
 
 10) Search atm result (https://github.com/PiyushChaudhari/assignment-program/blob/master/locator-atm-search.JPG)
 
 11) if you are login as normal user and hit `http://localhost:8080/user/list` url then you get `unauthorized` page (https://github.com/PiyushChaudhari/assignment-program/blob/master/locator-anauthorized.JPG)
