[![Build Status](https://travis-ci.org/zSvetik/GameDomino.svg?branch=master)](https://travis-ci.org/zSvetik/GameDomino)

GameDomino
=====================================================
1. Для конфигурации MySQL запустите файл `GameDomino/src/main/resources/createDB.sql`.

```
$ mysql -u <user> -p<password> < GameDomino/src/main/resources/createDB.sql
```

2. Измените в файле `GameDomino/src/main/resources/application.properties` имя пользователя MySQL (**user_mysql**) и пароль (**user_password**) на свои.

```java
user=user_mysql
password=user_password
url=jdbc:mysql://localhost:3306/domino?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
driver=com.mysql.jdbc.Driver
```

3. Для запуска приложения требуется JRE 1.8.

4. Запустите файл `GameDomino/run.bat`.

5. Откройте в браузере домашнюю страницу проекта [http://localhost:8080/](http://localhost:8080/).

6. Для остановки Jetty в командной строке нажмите комбинацию клавиш:

	<kbd>CTRL</kbd>+<kbd>C</kbd>
