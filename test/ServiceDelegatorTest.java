import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServiceDelegatorTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testFoo() throws Exception {
        ResourceForDelegation resource = mock(ResourceForDelegation.class);
        when(resource.foo(2)).thenReturn(2); //imitate result 2 (actual result result 4)
        when(resource.foo(3)).thenReturn(3).thenReturn(5); //imitate result 2 (actual result result 4)
        //when(resource.foo(anyInt())).thenReturn(0); //imitate result 0 for any int (actual result result 4)

        ServiceDelegator service = spy(new ServiceDelegator(resource));

        int result1 = service.foo(2);
        Assert.assertEquals(2, result1);

        int result2 = service.foo(3);
        Assert.assertEquals(3, result2);
        int result3 = service.foo(3);
        Assert.assertEquals(5, result3);

        verify(resource).foo(2);    // проверяем был ли вызов resource.foo(int)
        verify(service).foo(2);     // проверяем был ли вызов service.foo(int)
        verify(resource, times(2)).foo(3);    // проверяем был ли вызов
        //verify(resource).foo(4);    // проверяем был ли вызов
        verify(resource, atLeastOnce()).bar();    // проверяем был ли вызов хотя бы один раз
        verifyNoMoreInteractions(resource); // проверяем что больше не было интеракций
    }


}