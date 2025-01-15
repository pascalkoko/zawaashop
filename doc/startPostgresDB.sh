#!/bin/sh

echo
echo "This script creates 2 PostgresSQL DBMS with its DBs as docker containers (https://hub.docker.com/_/postgres)."
echo "The DBMSs are published on the following ports:"
echo "    Port 5432 is foreseen for manual testing the NBS source code"
echo "    Port 5433 is foreseen for setting up the DB model automatically by Flyway"
echo

docker run \
 -d \
 --name PostgresContainer4ZawaaShop \
 -e POSTGRES_PASSWORD=xxxxxx \
 -e POSTGRES_DB=ZawaaShopDB \
 -p 5432:5432 \
 postgres:13

echo
echo "Running docker processes:"
docker ps -a


echo
echo "Now both DBMS with there DBs are available on different ports."
echo "Access them with the following parameters:"
echo "      connection URL:   jdbc:postgresql://localhost:<port>/<db-name>"
echo "      user/role:        postgres"
echo "      passwd:           xxxxx"
echo "      schema:           public"
echo
echo "Stop and start the containers with docker [stop|start] <container-name>"
echo


echo
echo "Comment the following in for creating a schema inside the DB"
echo


#echo "Wait 5 seconds until the containers are up"
#sleep 5


#docker run --rm -it --link=PostgresContainer4ZawaaShop postgres:13 \
#  psql --username=postgres --dbname=ZawaaShopDB --host=PostgresContainer4ZawaaShop \
#       --command="CREATE ROLE postgres LOGIN PASSWORD 'xxxxxx'; \
#                  CREATE SCHEMA IF NOT EXISTS AUTHORIZATION postgres;"
#


#echo
#echo "Now both DBMS with there DBs are available on different ports."
#echo "Access them with the following parameters:"
#echo "      connection URL:   jdbc:postgresql://localhost:<port>/<db-name>"
#echo "      user/role:        zawaa"
#echo "      passwd:           xxxxx"
#echo "      schema:           zawaa"
#echo