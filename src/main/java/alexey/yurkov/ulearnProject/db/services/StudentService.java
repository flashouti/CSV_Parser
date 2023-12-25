package alexey.yurkov.ulearnProject.db.services;

import alexey.yurkov.ulearnProject.db.entities.Student;
import alexey.yurkov.ulearnProject.db.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findOne(int id) {
        Optional<Student> foundStudent = studentRepository.findById(id);
        return foundStudent.orElse(null);
    }

    public List<Student> getStudentsByFullName(String firstName, String lastName) {
        return studentRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Transactional
    public void save(Student student) {
        studentRepository.save(student);
    }

    @Transactional
    public void update(String id, Student updatedStudent) {
        updatedStudent.setUlearnID(id);
        studentRepository.save(updatedStudent);
    }

    @Transactional
    public void delete(int id) {
       studentRepository.deleteById(id);
    }

}
