package main.java.com.generation.joana.dto;

public class UsuarioDTO {

    private String nome;
    private String email;
    private String senha;
    private String imagem;
    private String vendedor;
    private String token;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getVendedor() {
        return vendedor;
    }
}