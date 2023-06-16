import java.math.BigDecimal;
import java.math.BigInteger;

public abstract class Produto {

    private Long codigo;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private BigInteger estoque;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public BigInteger getEstoque() {
        return estoque;
    }

    public void setEstoque(BigInteger estoque) {
        this.estoque = estoque;
    }

    public String exibirResumoProduto(){
        return this.getCodigo() + " - " + this.getNome();
    }

    public abstract String exibirDetalhesProtuto();

}
