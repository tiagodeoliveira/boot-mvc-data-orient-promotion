FROM java:8

ENV APP_VERSION=1.0-SNAPSHOT
ENV APP_NAME=boot-mvc-data-orient-promotion
ENV DIST_NAME=${APP_NAME}-${APP_VERSION}
ENV BIN_PATH=/${DIST_NAME}/bin/${APP_NAME}

ADD build/distributions/${DIST_NAME}.tar /
RUN chmod a+x ${BIN_PATH}
EXPOSE 8008
CMD ${BIN_PATH}