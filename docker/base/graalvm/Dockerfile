FROM oraclelinux:7-slim

ENV GRAALVM_PKG=graalvm-ce-1.0.0-rc6-linux-amd64.tar.gz \
    JAVA_HOME=/usr/graalvm-ce-1.0.0-rc6/jre \
    PATH=/usr/graalvm-ce-1.0.0-rc6/bin:$PATH \
    LEIN_ROOT=true

COPY lein/leiningen-2.7.1-standalone.jar /root/.lein/self-installs/ 
COPY lein/lein /usr/local/bin

ADD $GRAALVM_PKG /usr/

RUN yum -y install gcc; \
    yum -y install zlib-devel;  \
    alternatives --install /usr/bin/java  java  $JAVA_HOME/bin/java  20000 && \
    alternatives --install /usr/bin/javac javac $JAVA_HOME/bin/javac 20000 && \
    alternatives --install /usr/bin/jar   jar   $JAVA_HOME/bin/jar   20000
    