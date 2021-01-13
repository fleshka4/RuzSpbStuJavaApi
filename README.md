# Java Api для сервиса РУЗ СПбПУ

## Оглавление

* [Расписание](#расписание)
* [Факультеты (институты)](#факультеты-(институты))
* [Группы](#группы)
* [Преподаватели](#преподаватели)
* [Корпуса](#корпуса)
* [Аудитории](#аудитории)

### Расписание

`Schedule getScheduleByGroupId(int id)` - возвращает расписание группы на текущую неделю по ее ID  
`Schedule getScheduleByGroupIdAndDate(int id, LocalDate date)` - возвращает расписание группы по ее ID на неделю, в
которую входит указанная дата  
`Schedule getScheduleByTeacherId(int id)` - возвращает расписание преподавателя на текущую неделю по его ID  
`Schedule getScheduleByTeacherIdAndDate(int id, LocalDate date)` - возвращает расписание преподавателя по его ID на
неделю, в которую входит указанная дата  
`Schedule getScheduleByAuditoryId(int id)` - возвращает расписание аудитории на текущую неделю по ее ID  
`Schedule getScheduleByAuditoryIdAndDate(int id, LocalDate date)` - возвращает расписание аудитории по ее ID на неделю,
в которую входит указанная дата

### Факультеты (институты)

`ArrayList<Faculty> getFaculties()` - возвращает список факультетов (институтов)  
`Faculty getFacultyById(int id)` - возвращает факультет (институт) по его ID

### Группы

`ArrayList<Group> getGroupsbyFacultyId(int id)` - возвращает группы факультета (института) по его ID  
`ArrayList<Group> searchGroupsByName(String name)` - выполняет поиск и возвращает найденные группы факультета (
института)
по переданной строке

### Преподаватели

`ArrayList<Teacher> getTeachers()` - возвращает список преподавателей  
`Teacher getTeacherById(int id)` - возвращает данные о преподавателе по его ID  
`ArrayList<Teacher> searchTeachersByName(String name)` - выполняет поиск преподавателей по имени/фамилии/отчеству

### Корпуса

`ArrayList<Building> getBuildings()` - возвращает список корпусов  
`Building getBuildingById(int id)` - возвращает информацию о корпусе по его ID  
`Building findBuildingByAuditoryId(int id)` - выполняет поиск корпуса по ID аудитории

### Аудитории

`ArrayList<Auditory> getAuditoriesByBuildingId(int id)` - возвращает список аудиторий корпуса по его ID
