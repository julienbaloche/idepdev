# idepdev


##Start Dev
Define as Environment variable for Database connection(via terminal or via IDE).
example: 
``
DB_URL=root:root@localhost:3306/idep
``


##Deploy on heroku from command line 

It's better to use the Github integration but for now this will do.

You must install the [Heroku CLI](https://devcenter.heroku.com/articles/heroku-cli). 
### Testing the build locally 

This may be done to check that everything is ready for production. It is recommended to do so when changing the code base a lot.

export your database environment variables used in application.properties

`export DB_URL=root:root@localhost:3306/idep`

build the project

`mvn clean install`

lauch the app on a local heroku server 

`heroku local web`

If everything is fine you can go to the next step.


### Deploy on Heroku

Be careful and be sure that the MySQL Database environment variable is defined in the heroku project.

At the moment it is defined and is a free AWS Mysql Database.

login to heroku: 
You mush have an heroku account and press a key.

`heroku login`

change to the heroku project (idep-2020 for example):

`heroku git:remote -a idep-2020` 

deploying the code : 

`git push heroku master`



