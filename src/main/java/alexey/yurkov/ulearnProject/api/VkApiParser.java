package alexey.yurkov.ulearnProject.api;


import alexey.yurkov.ulearnProject.db.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VkApiParser {

    private final StudentService studentService;

    @Autowired
    public VkApiParser(StudentService studentService) {
        this.studentService = studentService;
    }
}
