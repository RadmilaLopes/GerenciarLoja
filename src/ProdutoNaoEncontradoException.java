import java.io.Serializable;

public class ProdutoNaoEncontradoException extends Exception {

    private static final String MENSAGEM = "Produto não encontrado";

    public ProdutoNaoEncontradoException(){
        super(MENSAGEM);
    }
}
