package hello.exception;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

// 이거 말고 BasicErrorController 사용해도 됨
@Component
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> {

    /**
     * 커스텀 에러페이지 등록
     * 등록된 예외코드가 던져지면, argument로 받은 에러페이지 path로 재요청을 보내어 응답값을 가져온다.
     */
    @Override
    public void customize(ConfigurableWebServerFactory factory) {
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404");
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500");
        ErrorPage errorPageEx = new ErrorPage(RuntimeException.class, "/error-page/500"); // RuntimeException의 자식클래스들 포함하여 해당 에러페이지 호출

        factory.addErrorPages(errorPage404, errorPage500, errorPageEx);
    }
}
