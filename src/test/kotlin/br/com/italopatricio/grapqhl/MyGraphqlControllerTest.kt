package br.com.italopatricio.grapqhl

import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [DgsAutoConfiguration::class, MyGraphqlController::class])
class MyGraphqlControllerTest {
    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    @Test
    fun shows() {
        val titles : List<String> = dgsQueryExecutor.executeAndExtractJsonPath("""
            {
                shows {
                    title
                    releaseYear
                }
            }
        """.trimIndent(), "data.shows[*].title")

        assertThat(titles).contains("Ozark")
    }
}