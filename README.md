# Домашняя работа #2

## Задание
Реализовать счетчик символов для входного текстового файла.
 
## Требования
1. Чтение обрабатываемого файла выполнять построчно в основном потоке.
2. Подсчет количества символов в строках выполнять в отдельных потоках.
3. Подсчет сумм символов между строками выполнять в отдельных потоках.
4. Успешное прохождение существующего теста.

##  Критерии приемки
1. Проходит E2E тест - 1 балл
2. Функционал дополнительно покрыт unit тестами - 1 балл
3. Класссы соответствуют принципам  SOLID - 1 балл

##  Комментарии, Подсказки
1. В качестве исполнителей, можно использовать пулы потоков или свои потоки.
2. Реализовать программу можно как и с помощью предложенных интерфейсов, так и модифицируя их 
   или используя свои, за исключением класса `AsyncFileLetterCounter`.
3. Допустимо использовать любые языковые конструкции и классы.
4. Как вариант предложенной реализации - можно реализовывать подсчет по строкам,затем 
   производить суммирование счетчиков по всем строкам.

## Сборка приложения 
```shell script
# загружает gradle wrapper 6.6
./gradlew wrapper

# сборка проекта, прогон тестов
./gradlew clean build
```


##  Как сдавать?
* Fork этого репозитория
* Merge request вашей реализации в этот репозиторий
