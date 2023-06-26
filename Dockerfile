FROM ubuntu:latest
LABEL authors="SETTING"

ENTRYPOINT ["top", "-b"]