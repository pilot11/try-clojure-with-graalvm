#!/bin/bash


#time \
#java -cp target/http-api-0.1.0-SNAPSHOT-standalone.jar \
#clojure.main -e "(use 'http-api.core)(-main :exit)"
#
#time target/server 1


call_http (){
    for i in {1..100}
    do
        curl -X GET http://127.0.0.1:8080/call > /dev/null 2>&1
    done
}

time call_http