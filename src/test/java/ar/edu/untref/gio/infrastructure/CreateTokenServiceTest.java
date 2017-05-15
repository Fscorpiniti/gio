package ar.edu.untref.gio.infrastructure;

import ar.edu.untref.gio.domain.TokenRandomAlphanumericGenerator;
import ar.edu.untref.gio.domain.TokenRepository;
import ar.edu.untref.gio.domain.service.CreateTokenService;
import ar.edu.untref.gio.infrastructure.service.DefaultCreateTokenService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class CreateTokenServiceTest {

    private static final int DEFAULT_USER_ID = 1;
    private static final int AMOUNT_OF_DIGITS = 16;
    private static final int NUMBER_OF_INVOCATIONS = 1;

    @Mock
    private TokenRepository tokenRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenCreateTokenThenTokenIsCreated() {
        CreateTokenService createTokenService = buildCreateTokenService();
        String token = createTokenService.create(DEFAULT_USER_ID);
        Assert.assertNotNull(token);
    }

    @Test
    public void whenCreateTokenThenTokenRepositoryIsCalled() {
        CreateTokenService createTokenService = buildCreateTokenService();
        createTokenService.create(DEFAULT_USER_ID);
        Mockito.verify(tokenRepository, Mockito.times(NUMBER_OF_INVOCATIONS)).add(Mockito.any());
    }

    private DefaultCreateTokenService buildCreateTokenService() {
        return new DefaultCreateTokenService(new TokenRandomAlphanumericGenerator(AMOUNT_OF_DIGITS), tokenRepository);
    }

}
