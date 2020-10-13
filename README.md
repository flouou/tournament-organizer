# Tournament Organizer  

[![Build Status](https://travis-ci.com/flouou/tournament-organizer.svg?branch=master)](https://travis-ci.com/flouou/tournament-organizer) 
<a href="https://codecov.io/gh/flouou/tournament-organizer">
  <img src="https://codecov.io/gh/flouou/tournament-organizer/branch/master/graph/badge.svg?token=2H3qvycoBE"/>
</a>  
  
### API for organizing tournaments


### Setup Guide
1. clone the repository
2. cd into your project-folder
3. docker-compose -d
4. mvn flyway:clean
5. mvn flyway:migrate
6. spring-boot:run
7. call http://localhost:8080/login with userdata {username: test, password: test}
8. in the response body, we can get the JWT. Put this token in every one of your request inside the Authorization-Header

