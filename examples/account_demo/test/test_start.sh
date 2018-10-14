#!/bin/bash

#cd ..
#lein uberjar

#time \
#java -cp target/account-demo-1.0.0-SNAPSHOT-standalone.jar \
#clojure.main -e "(use 'account.main)(-main :exit)"

#java -cp target/account-demo-1.0.0-SNAPSHOT-standalone.jar \
#clojure.main -e "(use 'math)(-main 1000000)"

call_http (){
    for i in {1..100}
    do
        curl -X GET http://127.0.0.1:8080/api/call > /dev/null 2>&1
    done
}

time call_http
