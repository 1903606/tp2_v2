package com.example.travailpratique2_v2;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UserUnitTest {
    @Test
    public void createUser() throws Exception{
        User usager = new User("Nathy","Maclennan","Femme","4388885555");

        assertEquals("Nathy", usager.getPrenom());

        assertEquals("Femme", usager.getGender());
    }

    @Test
    public void AuthentificationUser() throws Exception{
        AuthActivity authActivity = mock(AuthActivity.class);
        when(authActivity.getUserToken()).thenReturn("SuperTokenIdentificationUser");

        String jeton = authActivity.getUserToken();

        assertEquals("SuperTokenIdentificationUser", jeton);

    }

    @Test
    public void testInsertDataWithEmptyFields() {
        Register insertActivity = mock(Register.class); // Instantiate your activity or class
    }

    @Test
    public void testInsertDataWithValidFields() {
        Register insertActivity = mock(Register.class); // Instantiate your activity or class

    }
}



