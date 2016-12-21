Roi Watcher
===========

Web application monitors site [www.roi.ru](https://www.roi.ru).

Developed using Gradle, Groovy, Spring Framework, Jackson JSON Processor, jsoup, Hibernate, JUnit, Mockito, jQuery,
Underscore.js and PostgreSQL.

## Requirements

* JRE 8,
* Apache Tomcat 7 or more,
* PostgreSQL.

## Install

Install [PostgreSQL](https://www.postgresql.org) and create database:

```
$ createuser -D -P -R -S -U postgres roi_watcher
$ createdb -E UTF-8 -O roi_watcher -U postgres roi_watcher
$ psql -f sql/create-schema.sql -U roi_watcher roi_watcher
```

Install JDK

Install [Gradle](https://gradle.org) and build application:

```
$ gradle war
```

Install [Apache Tomcat](https://tomcat.apache.org/) and install application.
