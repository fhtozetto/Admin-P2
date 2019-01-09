package br.com.lph.adminp2.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;



@Configuration // Classe de configuração
@EnableWebSecurity //
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private Environment env;
	
	private static final String[] PUBLIC_MATCHERS = { // define a URIs liberadas
			"/h2-console/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_GET = { // define a URIs liberadas apenas para consultas
			"/produtos/**",
			"/codigosbarras/**",
			"/usuarios/**"
	};
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) { //estou pegando os profiles ativos do projeto e se for o "test"
            http.headers().frameOptions().disable(); //comando que libera acesso ao "h2-console"
		}
		
		http.cors().and().csrf().disable(); //chama o "corsConfigurationSource" caso esteja definido como está // como não armazena seção foi desabilitado o "csrf"
		http.authorizeRequests()
			.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()// O SpringSecurity libera apenas as consultas.
			.antMatchers(PUBLIC_MATCHERS).permitAll()// todos liberados pelo SpringSecurity
			.anyRequest().authenticated(); // todo o resto o SpringSecurity bloqueia
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // usada para asegurar que nosso backend não irá criar sessão de usuário.
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		/*
		 * Libera acesso básico de multiplas fontes para todos os caminhos com as configurações básicas.
		 */
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() { //responsavel por criptografar campos do banco de dados
		return new BCryptPasswordEncoder();
	}
}
