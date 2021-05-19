package br.com.zupacademy.brunomeloesilva.share.seguranca;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "br.com.zupacademy.brunomeloesilva.proposta.criptografia") 
public class Criptografia {
	
    private String senha;
    private String salt;
    
	public String getSenha() {
		return senha;
	}
	public String getSalt() {
		return salt;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	
	public String criptografar(String dado) {
		return Encryptors.queryableText(senha, salt).encrypt(dado);
	}
	
	public String descriptografar(String dadoEncrypt) {
		return Encryptors.queryableText(senha, salt).decrypt(dadoEncrypt);
	}
}