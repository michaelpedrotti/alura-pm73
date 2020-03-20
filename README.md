
docker run -d \
    --name mysql \
    -p 3306:3306 \
    -e MYSQL_ROOT_PASSWORD=root \
    -e MYSQL_DATABASE=pm73 \
    mysql:5.7

> docker exec -it mysql bash
>> mysql -u root -p
>> INSERT pm73.Usuario VALUES(null, "joao@silvas.com.br", "Joao da silva");

mvn install:install-file \
    -DgroupId=org.hsqldb \
    -DartifactId=hsqldb \
    -Dversion=1.0 \
    -Dfile=./src/lib/hsqldb.jar \
    -Dpackaging=jar

mvn install:install-file \
    -DgroupId=org.mockito \
    -DartifactId=mockito-core \
    -Dversion=1.8.5 \
    -Dfile=./src/lib/mockito-core-1.8.5.jar \
    -Dpackaging=jar
      
mvn install:install-file \
    -DgroupId=org.objenesis \
    -DartifactId=objenesis \
    -Dversion=1.0 \
    -Dfile=./src/lib/objenesis-1.0.jar \
    -Dpackaging=jar
        
mvn install:install-file \
    -DgroupId=antlr \
    -DartifactId=antlr \
    -Dversion=2.7.7 \
    -Dfile=./src/lib/hibernate/antlr-2.7.7.jar \
    -Dpackaging=jar
        
mvn install:install-file \
    -DgroupId=org.hibernate.annotations.common \
    -DartifactId=hibernate-commons-annotations \
    -Dversion=4.0.1.Final \
    -Dfile=./src/lib/hibernate/hibernate-commons-annotations-4.0.1.Final.jar \
    -Dpackaging=jar
      
mvn install:install-file \
    -DgroupId=org.hibernate.javax.persistence \
    -DartifactId=hibernate-jpa-2.0-api \
    -Dversion=1.0.1.Final \
    -Dfile=./src/lib/hibernate/hibernate-jpa-2.0-api-1.0.1.Final.jar \
    -Dpackaging=jar
        
mvn install:install-file \
    -DgroupId=org.jboss.logging \
    -DartifactId=jboss-logging \
    -Dversion=3.1.0.GA \
    -Dfile=./src/lib/hibernate/jboss-logging-3.1.0.GA.jar \
    -Dpackaging=jar
        
mvn install:install-file \
    -DgroupId=org.dom4j \
    -DartifactId=dom4j \
    -Dversion=1.6.1 \
    -Dfile=./src/lib/hibernate/dom4j-1.6.1.jar \
    -Dpackaging=jar
       
mvn install:install-file \
    -DgroupId=org.hibernate \
    -DartifactId=hibernate-core \
    -Dversion=4.1.7.Final \
    -Dfile=./src/lib/hibernate/hibernate-core-4.1.7.Final.jar  \
    -Dpackaging=jar
       
mvn install:install-file \
    -DgroupId=org.javassist \
    -DartifactId=javassist \
    -Dversion=3.15.0-GA \
    -Dfile=./src/lib/hibernate/javassist-3.15.0-GA.jar \
    -Dpackaging=jar
        
mvn install:install-file \
    -DgroupId=org.jboss.spec.javax.transaction \
    -DartifactId=jboss-transaction-api_1.1_spec \
    -Dversion=1.0.0.Final \
    -Dfile=./src/lib/hibernate/jboss-transaction-api_1.1_spec-1.0.0.Final.jar \
    -Dpackaging=jar

mvn install

mvn test

wget -c https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java_8.0.19-1ubuntu19.10_all.deb

# instale o conector

mvn "-Dexec.args=-classpath %classpath br.com.caelum.pm73.curso.CriaTabelas" \
     -Dexec.executable=/usr/lib/jvm/java-8-openjdk-amd64/bin/java org.codehaus.mojo:exec-maven-plugin:1.5.0:exec

