/**
 *
 * @author paulo
 */
package Model;


public class Usuario {
    private int id;
    private String usuario;
    private String senha;
    private String nome;
    private Double saldo;

    public Usuario(String usuario, String senha, String nome, double saldo) {
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
        this.saldo = saldo;
    }
    
    
    //Get and SETTERS:
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    

   
    
    public void depositar(Double valor) {
        this.saldo += valor;
    }
    
    
    //Constructor
    public Usuario(int id, String usuario, String senha, String nome, Double saldo) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
        this.saldo = saldo; // Valor inicial do saldo
    }
  

 
    
    //validador
    public Usuario(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
        
    }
    
    //salvador
    public Usuario(String usuario, String senha, String nome) {
        this.usuario = usuario;
        this.senha = senha;
        this.nome = nome;
        
    }
    
    
    
    
}
