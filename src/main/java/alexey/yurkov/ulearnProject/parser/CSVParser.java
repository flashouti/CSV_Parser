package alexey.yurkov.ulearnProject.parser;

import alexey.yurkov.ulearnProject.db.entities.Mark;
import alexey.yurkov.ulearnProject.db.entities.Student;
import alexey.yurkov.ulearnProject.db.entities.Task;
import alexey.yurkov.ulearnProject.db.services.StudentService;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CSVParser {


    private final StudentService studentService;
    private String[] headers;
    private Task[] tasks;

    @Autowired
    public CSVParser(StudentService studentService) throws IOException, CsvValidationException {
        this.studentService = studentService;
        this.headers = parseHeaders();
        this.tasks = parseTasks();
    }


     public String[] parseHeaders() throws IOException, CsvValidationException {
        var fileInputStream = new FileInputStream("basicprogramming.csv");
        var inputReader = new InputStreamReader(fileInputStream, Charset.forName("windows-1251"));
        var csvReader = new CSVReaderBuilder(inputReader).build();
        return String.join(",", csvReader.readNext()).split(";");
    }


    public void saveStudents(){
        try{
            var fileInputStream = new FileInputStream("basicprogramming.csv");
            var inputReader = new InputStreamReader(fileInputStream, Charset.forName("UTF-8"));
            var csvReader = new CSVReaderBuilder(inputReader).build();
            csvReader.skip(1);
            String[] line;
            var cnt = 0;
            while ((line = csvReader.readNext()) != null) {
                Student student = parseStudent(line);
                studentService.save(student);
                System.out.println(++cnt);
            }
        }catch (IOException | CsvValidationException e){
            e.printStackTrace();
        }
    }

    public Task[] parseTasks(){
        tasks = Arrays.stream(headers).map(s -> {
            Task task = new Task();
            task.setName(s);
            return task;
        }).toArray(Task[]::new);
        return tasks;
    }

    public Student parseStudent(String[] line){
        String[] studentData = String.join(",", line).split(";");
        String[] nameParts = studentData[0].split("\\s+");

        var student = new Student();
        student.setFirstName(nameParts.length > 1 ? nameParts[1] : nameParts[0]);
        student.setLastName(nameParts.length == 1 ? "" : nameParts[0]);
        student.setUlearnID(studentData[1]);
        student.setEmail(studentData[2]);
        student.setMarks(parseMarks(studentData,student));

        return student;
    }

    private List<Mark> parseMarks(String[] dataParts, Student student) {
        var marks = new ArrayList<Mark>();

        for (var i = 8; i < dataParts.length; i++) {
            var mark = new Mark();

            mark.setTask(tasks[i]);
            mark.setScore(Integer.valueOf(dataParts[i]));
            mark.setStudent(student);
            marks.add(mark);
        }

        return marks;
    }
}
