package alexey.yurkov.ulearnProject.controllers;


import alexey.yurkov.ulearnProject.vkApi.VkApiParser;
import alexey.yurkov.ulearnProject.parser.CSVParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
public class AppController {

    private final CSVParser csvParser;
    private final VkApiParser vkApiParser;

    @Autowired
    public AppController(CSVParser csvParser, VkApiParser vkApiParser) {
        this.csvParser = csvParser;
        this.vkApiParser = vkApiParser;
    }

    @PostMapping("/parseFile")
    public void parse() {
        csvParser.saveStudents();
    }

    @PostMapping("/parseVK")
    public void parseVkData(@RequestParam Integer id,  @RequestParam String access_token,  @RequestParam String secretKey,  @RequestParam String groupId){
        vkApiParser.getSomeInfoFromVK(id, access_token, secretKey, groupId);
    }


}
