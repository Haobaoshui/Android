package xyz.hbs.huarongdao.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * 跨域问题
 */
@Configuration
public class CorsConfig {
    /**
     * 允许任何域名使用
     * 允许任何头
     * 允许任何方法（post、get等）
     */
    private static CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // addAllowedOrigin 不能设置为* 因为与 allowCredential 冲突

        //production configure开发配置
        //corsConfiguration.addAllowedOrigin("https://www.haobaoshui.com");
        //corsConfiguration.addAllowedOrigin("http://127.0.0.1:8000");

        //服务器端test configure测试配置
        // corsConfiguration.addAllowedOrigin("https://www.haobaoshui.com");
        // corsConfiguration.addAllowedOrigin("http://127.0.0.1:8000");

        //本地test configure测试配置
        corsConfiguration.addAllowedOrigin("*");//测试用

        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        // allowCredential 需设置为true
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", CorsConfig.buildConfig());
        return new CorsFilter(source);
    }

}
