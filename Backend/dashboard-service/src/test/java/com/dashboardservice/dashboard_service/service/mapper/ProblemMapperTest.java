import com.dashboardservice.dashboard_service.dto.ProblemDto;
import com.dashboardservice.dashboard_service.dto.ProblemTopicDto;
import com.dashboardservice.dashboard_service.model.Problem;
import com.dashboardservice.dashboard_service.model.ProblemTopic;
import com.dashboardservice.dashboard_service.service.mapper.ProblemMapper;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProblemMapperTest {

    private final ModelMapper modelMapper = new ModelMapper();
    private final ProblemMapper problemMapper = new ProblemMapper(modelMapper);

    @Test
    public void testProblemDtoToProblem() {
        // Create ProblemDto
        ProblemDto dto = new ProblemDto();
        dto.setProblemName("Sample Problem");
        dto.setDescription("Sample Description");
        dto.setDifficulty("Easy");
        dto.setNumUsersSolved(100);
        dto.setNumSubmissions(200);
        dto.setProblemTopicId(UUID.randomUUID().toString());

        // Map to Entity
        Problem problem = problemMapper.toEntity(dto);

        // Assertions
        assertEquals("Sample Problem", problem.getProblemName());
        assertEquals("Sample Description", problem.getDescription());
        assertEquals("Easy", problem.getDifficulty());
        assertEquals(100, problem.getNumUsersSolved());
        assertEquals(200, problem.getNumSubmissions());
        // ProblemTopic mapping should be tested where appropriate, not here
    }

    @Test
    public void testProblemTopicDtoToProblemTopic() {
        // Create ProblemTopicDto
        ProblemTopicDto dto = new ProblemTopicDto();
        dto.setProblemTopicName("Algorithms");
        dto.setProblemTopicDescription("A collection of algorithmic problems.");
        dto.setProblemList(new ArrayList<>()); // Empty problem list for simplicity

        // Map to Entity
        ProblemTopic problemTopic = problemMapper.toEntity(dto);

        // Assertions
        assertEquals("Algorithms", problemTopic.getProblemTopicName());
        assertEquals("A collection of algorithmic problems.", problemTopic.getProblemTopicDescription());
        assertNotNull(problemTopic.getProblemsList());
        assertEquals(0, problemTopic.getProblemsList().size());
    }

    @Test
    public void testProblemTopicToDto() {
        // Create ProblemTopic
        ProblemTopic topic = new ProblemTopic();
        topic.setProblemTopicName("Algorithms");
        topic.setProblemTopicDescription("A collection of algorithmic problems.");
        topic.setProblemsList(new ArrayList<>()); // Empty list for simplicity

        // Map to DTO
        ProblemTopicDto dto = problemMapper.toDto(topic);

        // Assertions
        assertEquals("Algorithms", dto.getProblemTopicName());
        assertEquals("A collection of algorithmic problems.", dto.getProblemTopicDescription());
        assertNotNull(dto.getProblemList());
        assertEquals(0, dto.getProblemList().size());
    }

    @Test
    public void testProblemToDto() {
        // Create Problem
        Problem problem = new Problem();
        problem.setProblemName("Sample Problem");
        problem.setDescription("Sample Description");
        problem.setDifficulty("Easy");
        problem.setNumUsersSolved(100);
        problem.setNumSubmissions(200);

        // Map to DTO
        ProblemDto dto = problemMapper.toDto(problem);

        // Assertions
        assertEquals("Sample Problem", dto.getProblemName());
        assertEquals("Sample Description", dto.getDescription());
        assertEquals("Easy", dto.getDifficulty());
        assertEquals(100, dto.getNumUsersSolved());
        assertEquals(200, dto.getNumSubmissions());
    }

    @Test
    public void testProblemToDtoWithProblemTopic() throws Exception {
        // Create ProblemTopic
        ProblemTopic topic = new ProblemTopic();
        topic.setProblemTopicName("Algorithms");
        // Set ID using reflection since it is a @GeneratedValue field
        var field = ProblemTopic.class.getDeclaredField("id");
        field.setAccessible(true);
        UUID topicId = UUID.randomUUID();
        field.set(topic, topicId);

        // Create Problem
        Problem problem = new Problem();
        problem.setProblemName("Sample Problem");
        problem.setDescription("Sample Description");
        problem.setDifficulty("Easy");
        problem.setNumUsersSolved(100);
        problem.setNumSubmissions(200);
        problem.setProblemTopic(topic);

        // Map to DTO
        ProblemDto dto = problemMapper.toDto(problem);

        // Assertions
        assertEquals("Sample Problem", dto.getProblemName());
        assertEquals("Sample Description", dto.getDescription());
        assertEquals("Easy", dto.getDifficulty());
        assertEquals(100, dto.getNumUsersSolved());
        assertEquals(200, dto.getNumSubmissions());
        assertEquals(topicId.toString(), dto.getProblemTopicId());
    }
}
