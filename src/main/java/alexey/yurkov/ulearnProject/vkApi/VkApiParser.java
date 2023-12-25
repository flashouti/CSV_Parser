package alexey.yurkov.ulearnProject.vkApi;


import alexey.yurkov.ulearnProject.db.services.StudentService;
import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.ServiceActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.Fields;
import com.vk.api.sdk.objects.users.responses.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VkApiParser {

    private final StudentService studentService;

    @Autowired
    public VkApiParser(StudentService studentService) {
        this.studentService = studentService;
    }

    public void getSomeInfoFromVK(Integer id, String accessToken, String secretKey, String groupId) {
        VkApiClient vkApiClient = new VkApiClient(HttpTransportClient.getInstance());
        ServiceActor actor = new ServiceActor(id, secretKey, accessToken);

        try {
            ArrayList<String> items = new ArrayList<>();
            for (Integer integer : vkApiClient.groups().getMembers(actor).groupId(groupId).execute().getItems()) {
                String str = Objects.toString(integer);
                items.add(str);
            }
            List<GetResponse> groupMembers = vkApiClient.users().get(actor).userIds(String.valueOf(items))
                    .fields(Fields.EDUCATION, Fields.BDATE, Fields.FOLLOWERS_COUNT, Fields.SEX)
                    .lang(Lang.RU).execute();

            for (var member : groupMembers) {
                saveStudentWithNewInfo(member);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveStudentWithNewInfo(GetResponse user) {
        var students = studentService.getStudentsByFullName(user.getFirstName(), user.getLastName());

        for (var student : students) {
            student.setBdate(user.getBdate());
            student.setEducation(user.getEducationForm());
            student.setSex(user.getSex());
            student.setFollowersQuantity(user.getFollowersCount());
            studentService.save(student);
        }
    }
}
