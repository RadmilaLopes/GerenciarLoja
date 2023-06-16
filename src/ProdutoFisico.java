import java.math.BigDecimal;

public class ProdutoFisico extends Produto {
    private BigDecimal peso;
    private String dimensao;

    public BigDecimal getPeso() {
        return peso;
    }

    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }

    public String getDimensao() {
        return dimensao;
    }

    public void setDimensao(String dimensao) {
        this.dimensao = dimensao;
    }


    @Override
    public String exibirDetalhesProtuto() {
        return "Tipo de Produto: FISICO"
                + "\nCódigo: " + super.getCodigo()
                + "\nNome: " + super.getNome()
                + "\nDescrição: " + super.getDescricao()
                + "\nPreço: " + super.getPreco()
                + "\nQuantidade Estoque: " + super.getEstoque()
                + "\nDimensão (AxLxP): " + getDimensao()
                + "\nPeso : " + getPeso();
    }
}
