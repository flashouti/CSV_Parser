package alexey.yurkov.ulearnProject.parser;

import alexey.yurkov.ulearnProject.db.entities.Mark;
import alexey.yurkov.ulearnProject.db.entities.Student;
import alexey.yurkov.ulearnProject.db.services.StudentService;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVtoStudentParser {


    private final StudentService studentService;

    @Autowired
    public CSVtoStudentParser(StudentService studentService) throws FileNotFoundException {
        this.studentService = studentService;
    }

    private String[] headers;

    public void saveStudents(String filePath) throws IOException, CsvValidationException{
        var fileInputStream = new FileInputStream("basicprogramming.csv");
        var inputReader = new InputStreamReader(fileInputStream, Charset.forName("windows-1251"));
        var csvReader = new CSVReaderBuilder(inputReader).build();
        headers = getHeaders();
        csvReader.skip(1);
        String[] line;
        var cnt = 0;
        while ((line = csvReader.readNext()) != null) {
            Student student = parseStudent(line);
            studentService.save(student);
            System.out.println(++cnt);
        }
    }


    public Student parseStudent(String[] line){
        String[] studentData = String.join(",", line).split(";");
        String[] nameParts = studentData[0].split("\\s+");

        var student = new Student();
        student.setFirstName(nameParts.length > 1 ? nameParts[1] : nameParts[0]);
        student.setLastName(nameParts.length == 1 ? "" : nameParts[0]);
        student.setUlearnID(studentData[1]);
        student.setEmail(studentData[2]);
        student.setMarks(parseMarks(studentData));

        return student;
    }

    private String[] getHeaders() throws IOException, CsvValidationException {
        var fileInputStream = new FileInputStream("basicprogramming.csv");
        var inputReader = new InputStreamReader(fileInputStream, Charset.forName("windows-1251"));
        var csvReader = new CSVReaderBuilder(inputReader).build();
        return String.join(",", csvReader.readNext()).split(";");
    }

    private List<Mark> parseMarks(String[] dataParts) {
        var marks = new ArrayList<Mark>();

        for (var i = 8; i < dataParts.length; i++) {
            var mark = new Mark();

            mark.setScore(Integer.valueOf(dataParts[i]));
            marks.add(mark);
        }

        return marks;
    }
}
