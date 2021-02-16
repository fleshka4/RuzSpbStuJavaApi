# Java Api для сервиса РУЗ СПбПУ

## Содержание

* [Вступление](#вступление)
* [Оглавление](#оглавление)
* [Установка](#установка)

### Вступление

Чтобы использовать методы API, необходимо и достаточно написать: `RuzSpbStu.название-метода(параметры)`

### Оглавление

* [Расписание](#расписание)
* [Факультеты (институты)](#факультеты-(институты))
* [Группы](#группы)
* [Преподаватели](#преподаватели)
* [Корпуса](#корпуса)
* [Аудитории](#аудитории)

#### Расписание

`Schedule getScheduleByGroupId(int id)` - возвращает расписание группы на текущую неделю по ее ID  
`Schedule getScheduleByGroupIdAndDate(int id, LocalDate date)` - возвращает расписание группы по ее ID на неделю, в
которую входит указанная дата  
`Schedule getScheduleByTeacherId(int id)` - возвращает расписание преподавателя на текущую неделю по его ID  
`Schedule getScheduleByTeacherIdAndDate(int id, LocalDate date)` - возвращает расписание преподавателя по его ID на
неделю, в которую входит указанная дата  
`Schedule getScheduleByAuditoryId(int id)` - возвращает расписание аудитории на текущую неделю по ее ID  
`Schedule getScheduleByAuditoryIdAndDate(int id, LocalDate date)` - возвращает расписание аудитории по ее ID на неделю,
в которую входит указанная дата

#### Факультеты (институты)

`ArrayList<Faculty> getFaculties()` - возвращает список факультетов (институтов)  
`Faculty getFacultyById(int id)` - возвращает факультет (институт) по его ID  
`ArrayList<Faculty> searchFacultiesByName(String name)` - выполняет поиск факультетов (институтов)
по названию/аббревиатуре и возвращает найденные факультеты (институты)

#### Группы

`ArrayList<Group> getGroupsbyFacultyId(int id)` - возвращает группы факультета (института) по его ID  
`ArrayList<Group> searchGroupsByName(String name)` - выполняет поиск и возвращает найденные группы факультета
(института) по переданной строке

#### Преподаватели

`ArrayList<Teacher> getTeachers()` - возвращает список преподавателей  
`Teacher getTeacherById(int id)` - возвращает данные о преподавателе по его ID  
`ArrayList<Teacher> searchTeachersByName(String name)` - выполняет поиск преподавателей по имени/фамилии/отчеству

#### Корпуса

`ArrayList<Building> getBuildings()` - возвращает список корпусов  
`Building getBuildingById(int id)` - возвращает информацию о корпусе по его ID  
`Building findBuildingByAuditoryId(int id)` - выполняет поиск корпуса по ID аудитории  
`ArrayList<Building> searchBuildingsByName(String name)` - выполняет поиск корпусов по названию/аббревиатуре
и возвращает найденные корпуса

#### Аудитории

`Auditory getAuditoriesByBuildingId(int id)` - возвращает объект класса Auditory, который содержит в себе список
аудиторий корпуса по его ID  
`ArrayList<Auditory> searchAuditoriesByName(String name)` - выполняет поиск аудиторий по названию аудиторий и
возвращает найденные аудитории

### Установка

[![](https://jitpack.io/v/fleshka4/RuzSpbStuJavaApi.svg)](https://jitpack.io/#fleshka4/RuzSpbStuJavaApi)

Step 1. Добавьте JitPack репозиторий в ваш файл конфигурации

#### Gradle

Вставьте это в build.gradle:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

Step 2. Добавьте зависимость

	dependencies {
	        implementation 'com.github.fleshka4:RuzSpbStuJavaApi:v1.2.1'
	}

#### Maven

    <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>

Step 2. Добавьте зависимость

	<dependency>
	    <groupId>com.github.fleshka4</groupId>
	    <artifactId>RuzSpbStuJavaApi</artifactId>
	    <version>v1.2.1</version>
	</dependency>