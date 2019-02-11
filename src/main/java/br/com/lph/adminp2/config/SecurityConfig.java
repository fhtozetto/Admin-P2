package br.com.lph.adminp2.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import br.com.lph.adminp2.security.JWTAuthenticationFilter;
import br.com.lph.adminp2.security.JWTAuthorizationFilter;
import br.com.lph.adminp2.security.JWTUtil;



@Configuration // Classe de configuração
@EnableWebSecurity //
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private Environment env;
	
	@Autowired
	private UserDetailsService userDetailsService; // foi injetado a inteface o spring busca automaticamente o userDetailsServiceImpl
	
	@Autowired
	private JWTUtil jwtUtil;
	
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
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // usada para asegurar que nosso backend não irá criar sessão de usuário.
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
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
