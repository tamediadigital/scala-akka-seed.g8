FROM anapsix/alpine-java

ENV APP_DIR /usr/local/$name$/

RUN mkdir -p \${APP_DIR}

WORKDIR \${APP_DIR}

COPY target/universal/version.properties \${APP_DIR}

COPY target/universal/$name$*.zip \${APP_DIR}

RUN unzip \$(cat version.properties).zip

RUN apk add --no-cache tini

ENTRYPOINT ["/sbin/tini", "--"]

CMD \$(cat version.properties)/bin/$name$


## create user
RUN addgroup -g $randomNumber$ $docker_user$
RUN adduser -D -H -u $randomNumber$ -G $docker_user$ $docker_user$
USER $docker_user$

EXPOSE 9000
