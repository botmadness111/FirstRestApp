#from - из какого образа будет собираться образ
FROM openjdk:20

#add - из директории "target" возьмем файл и добавим его в образ в виде файла "backend.jar"
ADD /target/FirstRestApp-0.0.1-SNAPSHOT.jar backend.jar

#entrypoint - запускает файл "backend.jar", в качетве аргументов указываем то, что мы вызываем: "java" и "-jar"
ENTRYPOINT ["java", "-jar", "backend.jar"]

# после этого можем собрать свой образ: в терминале пищем "docker build <<path to Dockerfile>>"


# Используем базовый образ Jenkins
FROM jenkins/jenkins:latest

# Переключаемся в режим root для выполнения дополнительных команд
USER root

# Устанавливаем пакет lsb-release, который потребуется для установки Docker
RUN apt-get update && apt-get install -y lsb-release

# Скачиваем ключ для репозитория Docker
RUN curl -fsSLo /usr/share/keyrings/docker-archive-keyring.asc \
    https://download.docker.com/linux/debian/gpg

# Добавляем репозиторий Docker в список источников пакетов
RUN echo "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.asc] https://download.docker.com/linux/debian $(lsb_release -cs) stable" \
    > /etc/apt/sources.list.d/docker.list

# Обновляем список пакетов и устанавливаем Docker
RUN apt-get update && apt-get install -y docker-ce-cli

# Переключаемся обратно в режим пользователя Jenkins
USER jenkins

# Устанавливаем плагины Jenkins через jenkins-plugin-cli
RUN jenkins-plugin-cli --plugins "blueocean docker-workflow"
