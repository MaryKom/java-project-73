FROM gradle:7.2.0-jdk17

WORKDIR /

COPY . /

RUN gradle installDist

CMD build/install/app/bin/app
