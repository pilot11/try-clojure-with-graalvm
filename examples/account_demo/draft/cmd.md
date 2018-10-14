curl -X POST \
-H "content-type:application/json" \
-d "{\"username\":\"foo\",\"password\":\"123456\"}" \
http://127.0.0.1:8080/api/reg


curl -X GET \
http://127.0.0.1:8080/api/exist/foo


