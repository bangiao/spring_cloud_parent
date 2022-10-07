package com.zhazha.webflux.test;

import com.github.jasync.sql.db.mysql.MySQLConnection;
import com.github.jasync.sql.db.mysql.MySQLConnectionBuilder;
import com.github.jasync.sql.db.pool.ConnectionPool;
import com.zhazha.webflux.controller.CityController;
import com.zhazha.webflux.domain.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.FluxExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@WebFluxTest(controllers = CityController.class)
//@SpringBootTest(classes = {WebfluxMvcApplication.class})
public class DemoApplicationTests {

    @Test
    public void test01() throws Exception {
    }

    @Test
    public void testUsersFindAll() throws Exception {
        ConnectionPool<MySQLConnection> connectionPool = MySQLConnectionBuilder.createConnectionPool(
                "jdbc:mysql://$host:$port/$database?user=$username&password=$password");
    }


    @Resource
    private WebTestClient webTestClient;

    @Test
    public void getAllMessagesShouldBeOk() throws Exception {
        FluxExchangeResult<City> result = webTestClient.get().uri("/city")
                .exchange().returnResult(City.class);
        System.out.println(result.getResponseBody().blockFirst());
    }

}
