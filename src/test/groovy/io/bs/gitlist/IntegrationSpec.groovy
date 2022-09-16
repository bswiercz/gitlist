package io.bs.gitlist

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.event.annotation.BeforeTestClass
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

@SpringBootTest(classes = [GitList])
@AutoConfigureMockMvc
abstract class IntegrationSpec extends Specification {
    @Autowired
    MockMvc mockMvc
}
