import java.math.BigInteger;

public class ProdutoDigital extends Produto {
    private String formato;
    private BigInteger tamanho;

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public BigInteger getTamanho() {
        return tamanho;
    }

    public void setTamanho(BigInteger tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String exibirDetalhesProtuto() {
        return "Tipo de Produto: DIGITAL"
                + "\nCódigo: " + super.getCodigo()
                + "\nNome: " + super.getNome()
                + "\nDescrição: " + super.getDescricao()
                + "\nPreço: " + super.getPreco()
                + "\nQuantidade Estoque: " + super.getEstoque()
                + "\nFormato do Arquivo: " + getFormato()
                + "\nTamanho (MB): " + getTamanho();
    }
}
