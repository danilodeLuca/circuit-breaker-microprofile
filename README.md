# circuit-breaker-microprofile


docker build . -t circuit-breaker-app
docker tag circuit-breaker-app registry.heroku.com/whispering-tundra-13699/web

docker push registry.heroku.com/whispering-tundra-13699/web 

heroku container:release web

https://dashboard.heroku.com/apps/whispering-tundra-13699/resources

https://whispering-tundra-13699-2ebc6440802a.herokuapp.com/circuit-breaker/api/conferences/a
