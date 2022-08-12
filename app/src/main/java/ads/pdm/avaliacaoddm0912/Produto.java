package ads.pdm.avaliacaoddm0912;

public class Produto {
    private String nome;
    private String marca;
    private String quantidade;
    private String comprado;

    public Produto(String nome, String marca, String quantidade) {
        this.nome = nome;
        this.marca = marca;
        this.quantidade = quantidade;
        this.comprado = "";
    }

    public String getNome() {
        return this.nome;
    }

    public String getMarca() {
        return this.marca;
    }

    public String getQuantidade() {
        return this.quantidade;
    }

    public String getComprado() {
        return this.comprado;
    }

    public void setComprado(String comprado) {
        this.comprado = comprado;
    }
}
