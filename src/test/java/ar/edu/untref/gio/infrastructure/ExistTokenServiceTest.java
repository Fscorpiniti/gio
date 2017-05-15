package ar.edu.untref.gio.infrastructure;

import ar.edu.untref.gio.domain.Token;
import ar.edu.untref.gio.domain.TokenRepository;
import ar.edu.untref.gio.domain.service.ExistTokenService;
import ar.edu.untref.gio.infrastructure.exception.UserIllegalAccessException;
import ar.edu.untref.gio.infrastructure.service.DefaultExistTokenService;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;

public class ExistTokenServiceTest {

    private static final int USER_ID = 1;
    private static final String TOKEN = "test";

    @Mock
    private TokenRepository tokenRepository;

    @Rule
    public ExpectedException thrown= ExpectedException.none();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenNotExistTokenThenExceptionIsThrown() {
        Mockito.when(tokenRepository.findBy(USER_ID, TOKEN)).thenReturn(new ArrayList<Token>());

        ExistTokenService existTokenService = new DefaultExistTokenService(tokenRepository);

        thrown.expect(UserIllegalAccessException.class);
        existTokenService.exist(USER_ID, TOKEN);
    }

    @Test
    public void whenExistTokenThenExceptionIsNotThrown() {
        Mockito.when(tokenRepository.findBy(USER_ID, TOKEN)).thenReturn(Arrays.asList(new Token(USER_ID, TOKEN)));
        ExistTokenService existTokenService = new DefaultExistTokenService(tokenRepository);
        existTokenService.exist(USER_ID, TOKEN);
    }

}
