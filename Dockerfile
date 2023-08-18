FROM maven:3.6.3-jdk-11

COPY . .
CMD ["mvn","clean","test","-Dsuite=src/test/resources/testng"]
