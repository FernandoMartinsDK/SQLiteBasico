package fernando.com.br.sqllite.dataModel;

import java.io.Serializable;

/**
 * Realiza a manipulação de dados
 */

public class ItemLogin implements Serializable {
    private int id_usuario;
    private String email_usuario;
    private String senha_usuario;

    /* public ItemLogin(int id,String email,String senha){
        this.id_usuario=id;
        this.email_usuario = email;
        this.senha_usuario = senha;
    } */

    public ItemLogin(){
        super();
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEmail_usuario() {
        return email_usuario;
    }

    public void setEmail_usuario(String email_usuario) {
        this.email_usuario = email_usuario;
    }

    public String getSenha_usuario(String senha_usuario) {
        return this.senha_usuario;
    }

    public void setSenha_usuario(String senha_usuario) {
        this.senha_usuario = senha_usuario;
    }
}
