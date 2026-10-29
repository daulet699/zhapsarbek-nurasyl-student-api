package kz.iitu.devops.nurasyl.web;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @Value("${STUDENT_NAME:Nurasyl Zhapsarbek}") private String studentName;
    @Value("${STUDENT_GROUP:IT2-2308}") private String studentGroup;
    @Value("${STUDENT_ID:37841}") private String studentId;
    @Value("${APP_VERSION:v1.0}") private String version;

    @GetMapping("/")
    public Map<String, String> info() {
        return Map.of("application", "Student CRUD API",
                "student", studentName, "group", studentGroup,
                "studentId", studentId, "version", version,
                "status", "running");
    }
}
