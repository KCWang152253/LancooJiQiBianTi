package com.lancoo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * ps:跨域配置
 */
@Configuration
public class CorsConfig {

	// 设置允许跨域的源
    private static String[] originsVal = new String[]{};

    /**
     * ps:跨域过滤器
     */
    @Bean
    public CorsFilter corsFilter() {
	    
       UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	   CorsConfiguration corsConfiguration = new CorsConfiguration();
	   this.addAllowedOrigins(corsConfiguration);
	   corsConfiguration.addAllowedHeader("*");// 允许任何头
	   corsConfiguration.addAllowedMethod("*");// 允许任何方法（post、get等） 
	   corsConfiguration.addAllowedOrigin("*");// 允许任何域名使用
	   source.registerCorsConfiguration("/**", corsConfiguration);
	    
	   return new CorsFilter(source);
	   
    }

    private void addAllowedOrigins(CorsConfiguration corsConfiguration) {
    	
       for (String origin : originsVal) {
         corsConfiguration.addAllowedOrigin("http://" + origin);
         corsConfiguration.addAllowedOrigin("https://" + origin);
       }
       
    }
	
}
