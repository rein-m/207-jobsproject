package use_case.account;

import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Tests for EditAccountInteractor.
 */
public class EditAccountInteractorTest {

    @Mock
    private EditAccountUserDataAccessInterface userDataAccess;

    @Mock
    private EditAccountOutputBoundary presenter;

    private EditAccountInteractor interactor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        interactor = new EditAccountInteractor(userDataAccess, presenter);
    }

    /**
     * user exists, data is updated, saved, and presenter gets success.
     */
    @Test
    public void execute_UserExists_Success() {
        String id = "patrick";
        String email = "patrick.star@gmail.com";
        String phone = "123-456-7890";
        String address = "Bikini Bottom";
        String education = "BSc CS";
        String work = "2 years dev";
        String projects = "Proj1, Proj2";
        String skills = "Java, SQL";
        String languages = "Java, Python";
        String frameworks = "Spring, React";
        String tools = "Git, Docker";

        EditAccountInputData input = new EditAccountInputData(
                id, email, phone, address,
                education, work, projects, skills,
                languages, frameworks, tools
        );

        // user exists
        when(userDataAccess.existsByIdentifier(id)).thenReturn(true);

        // mock entity
        User user = mock(User.class);
        when(userDataAccess.get(id)).thenReturn(user);

        // what the entity will return after update (interactor reads from entity)
        when(user.getEmail()).thenReturn(email);
        when(user.getPhone()).thenReturn(phone);
        when(user.getLocation()).thenReturn(address);
        when(user.getEducation()).thenReturn(education);
        when(user.getWorkExperience()).thenReturn(work);
        when(user.getProjects()).thenReturn(projects);
        when(user.getSkills()).thenReturn(skills);
        when(user.getProgrammingLanguages()).thenReturn(languages);
        when(user.getFrameworksAndLibraries()).thenReturn(frameworks);
        when(user.getToolsAndTechnologies()).thenReturn(tools);

        interactor.execute(input);

        // verify entity was updated
        verify(user).updateContactInfo(address, email, phone);
        verify(user).updateQualifications(
                education, work, projects, skills,
                languages, frameworks, tools
        );

        // verify persistence
        verify(userDataAccess).save(user);

        // capture presenter output
        ArgumentCaptor<EditAccountOutputData> captor =
                ArgumentCaptor.forClass(EditAccountOutputData.class);
        verify(presenter).prepareSuccessView(captor.capture());

        EditAccountOutputData out = captor.getValue();
        assertEquals(id, out.getIdentifier());
        assertEquals(email, out.getEmail());
        assertEquals(phone, out.getPhone());
        assertEquals(address, out.getAddress());
        assertEquals(education, out.getEducation());
        assertEquals(work, out.getWorkExperience());
        assertEquals(projects, out.getProjects());
        assertEquals(skills, out.getSkills());
        assertEquals(languages, out.getProgrammingLanguages());
        assertEquals(frameworks, out.getFrameworksAndLibraries());
        assertEquals(tools, out.getToolsAndTechnologies());
    }

    /**
     * Error path: user does not exist, presenter gets fail view,
     * and DAO get/save are never called.
     */
    @Test
    public void execute_UserDoesNotExist_Fail() {
        String id = "missing_user";

        EditAccountInputData input = new EditAccountInputData(
                id,
                "email@gmail.com",
                "000-000-0000",
                "Somewhere",
                "edu", "work", "proj", "skills",
                "lang", "fw", "tools"
        );

        when(userDataAccess.existsByIdentifier(id)).thenReturn(false);

        interactor.execute(input);

        verify(presenter).prepareFailView("User '" + id + "' does not exist.");
        verify(userDataAccess, never()).get(any());
        verify(userDataAccess, never()).save(any(User.class));
    }
}