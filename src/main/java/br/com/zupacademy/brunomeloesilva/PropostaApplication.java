package br.com.zupacademy.brunomeloesilva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PropostaApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PropostaApplication.class, args);
		
		
		//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		//String senha = encoder.encode("senha");
		//System.err.println("SENHA = " + senha);
		
		//String salt = KeyGenerators.string().generateKey(); 
		//System.err.println("SALT = " + salt);
		
		//TextEncryptor textEncryptor = Encryptors.queryableText("$2a$10$g94wrka7jg87hvfnfvcmcePM8CBfi2US0s34UhqyeTcr4SoGv/Eym" 
		//		, "6fc69b60ac13ab3a" );
		//String encrypt = textEncryptor.encrypt("Bruno");
		//System.err.println("CRIPTOGRAFADO = " + encrypt);
		
		//String decrypt = textEncryptor.decrypt(encrypt);
		//System.err.println("DESCRIPTOGRAFADO = " + decrypt);
		
		
		/*
		  	SENHA = $2a$10$g94wrka7jg87hvfnfvcmcePM8CBfi2US0s34UhqyeTcr4SoGv/Eym
			SALT = 6fc69b60ac13ab3a
		#Encryptors.Text : Altera para o mesmo conteudo
			CRIPTOGRAFADO = 7297d7cb6c26dbb2c23d27b301bc48f67420a2d04f78a9eb170497af209ee85b
			DESCRIPTOGRAFADO = Bruno
		#Encryptors.queryableText : NAO altera para o mesmo conteudo
			CRIPTOGRAFADO = c7bdfe61cefc34a31ac5d78020b8c262
			DESCRIPTOGRAFADO = Bruno
		 */
	}

}
