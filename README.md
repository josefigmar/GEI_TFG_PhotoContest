# PhotoContest: Aplicación de gestión de concursos fotográficos.

Trabajo Final de Grado en Ingeniería Informática.

## Ejecutar aplicación

Se necesitan las siguientes dependencias:

* Node 14.15.0+.
* Java 11
* Maven 3.6+.
* MySQL 8+.

Luego de su instalación:

[Backend]

```
cd backend
mvn sql:execute (only first time to create tables)
mvn spring-boot:run
```
[Frontend]

```
cd frontend
npm install (only first time to download libraries)
npm start
```

## Resumen

Existen una serie de problemas en la realización de los concursos fotográficos a día de hoy en el ámbito de la fotografía amateur: estos concursos suelen depender de páginas externas como Facebook para la subida de las fotografías y también de agentes externos. También fallan a la hora del seguimiento del concurso, siendo el propio participante el que tiene que enterarse del transcurso del mismo, en vez de que el concurso vaya notificando a los participantes de ciertos eventos. Por último, el proceso de finalización del concurso y entrega de resultados es mejorable, ya que esto suele hacerlo el organizador tiempo después y no siempre se deja constancia precisa de los resultados obtenidos en el concurso.

Por tanto, el TFG consiste en crear una aplicación web que reúna toda esa funcionalidad: permitir la creación de concursos de fotografía, permitir la subida de fotografías por parte de los participantes, permitir la evaluación posterior de estas fotos y finalmente la entrega automática de notificaciones y resultados. De esta manera se tendrán todos los datos y funcionalidad centralizada en una única página, facilitando el trabajo de los organizadores y los participantes.

Para ello se dotará a la aplicación de la capacidad de la creación de usuarios con roles en función de las tareas que desempeñen. Los organizadores se encargarán de la creación del concurso y sus modificaciones: eligen los elementos descriptivos como el nombre y la descripción, las fechas de inicio y fin, qué usuarios formarán parte del concurso y qué roles desempeñarán (comité organizador, jurado o concursante), la categoría del concurso (bodegón, naturaleza, macrofotografía...), detalles técnicos tales como el formato de imagen requerido o los datos que se aportan por cada imagen y entre otros los detalles de cómo se llevará a cabo la votación, pues se permitirá configurar diferentes parámetros tales como el tipo de participantes que pueden votar o cómo se elige a las fotografías ganadoras y el tipo de votación.

Con este desarrollo se intenta proveer a la comunidad una herramienta gratuita que satisfaga sus necesidades de una manera eficaz.

## Summary

There are some problems with the procedures in current amateur photography contests: this contests usually rely on external websites like Facebook for the upload of the images and also on external agents. They also fail on notyfing the user with important events related with the contest they are participating on. Also, the finale of the contest and the results presentation has margin to improve, as this is usually managed by the manager of the contest and the results are not always presented in a clear and precise way.

Therefore, this project aims to develop a web application that unifies that functionality: allowing the creation of photography contests, allowing the participants to upload the images, allowing the evaluation of that images and finally the delivery of automatic notifications and the results. Doing so, all the data and functionality are centralizated on the application, making it easier for the managers and the participants.

To achieve this, the application will allow the creation of users with different roles deppending on the tasks they´ll perform. The contest managers are going to be in charge of the creation of the contest an the modifications: they choose the descriptive elements such as the name, the description, starting and ending dates, the category (still life, nature, macro photography) technical details like the required format of the images or the data that will be supported with each image and, among others, the details on how the voting process is going to perform, since several configurations will be permitted such as the type of the participants. How the winning photos will be determined and the type of voting will be also configurable.

With this development the objetive is to provide the comunity a free and efective tool.
