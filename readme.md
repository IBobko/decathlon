## Getting start with project

1. You should execute maven:
``mvn clean install``
2. You should pass to the target directory and start project with two parameters.
    1. The CSV file which contains sportsmen data.
    2. The output xml file.
    <br/> For example: ``java -jar csv-1.0-SNAPSHOT.jar "C:/results.csv" "C:/Users/Игорь/IdeaProjects/csv/file.xml"``
    

### Information for different format of input data
You may redefine ``com.company.reader.CalculateRecord`` and implement ``com.company.RecordReader`` interface if your have another algorithm for reading data.

### Unit test information
For running tests please put the right paths in ``com.company.AppTest`` class.