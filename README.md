# запуск в Chrome

```bash
mvn test
```

# запуск с Firefox
если mvn test падает с ошибкой 500 и не находит бинарник, то запускаем с параметром

```bash
mvn -Dbrowser=firefox -Dwebdriver.firefox.bin=/usr/bin/firefox test
```S p r i n t _ 4  
 