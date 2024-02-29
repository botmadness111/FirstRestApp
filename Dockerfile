#from - из какого образа будет собираться образ
FROM openjdk:20

#add - из директории "target" возьмем файл и добавим его в образ в виде файла "backend.jar"
ADD /target/FirstRestApp-0.0.1-SNAPSHOT.jar backend.jar

#entrypoint - запускает файл "backend.jar", в качетве аргументов указываем то, что мы вызываем: "java" и "-jar"
ENTRYPOINT ["java", "-jar", "backend.jar"]

# после этого можем собрать свой образ: в терминале пищем "docker build <<path to Dockerfile>>"
