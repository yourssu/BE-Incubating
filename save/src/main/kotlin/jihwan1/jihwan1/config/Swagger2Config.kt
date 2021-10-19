package jihwan1.jihwan1.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class Swagger2Config {

    @Bean
    fun api() : Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("jihwan1.jihwan1.controller"))
                .build()
                .apiInfo(apiInfo())
    }

    fun apiInfo(): ApiInfo{
        return ApiInfoBuilder().apply {
            title("MEMO API")
        }.build()
    }


}