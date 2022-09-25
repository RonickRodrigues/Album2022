package br.com.ronick.album.modelo.entidade;

public class Figurinha {

    private final int numero;
    private final byte pagina;
    private String descricao;
    private byte quantidade;

    public Figurinha(int numero, byte pagina) {
        this.numero = numero;
        this.pagina = pagina;
    }

    public Figurinha(int numero, byte pagina,
            String descricao) {
        this.numero = numero;
        this.pagina = pagina;
        this.descricao = descricao;
    }

    public Figurinha(int numero, byte pagina,
            String descricao, byte quantidade) {
        this.numero = numero;
        this.pagina = pagina;
        this.descricao = descricao;
        this.quantidade = quantidade;
    }

    public int getNumero() {
        return numero;
    }

    public byte getPagina() {
        return pagina;
    }

    public String getDescricao() {
        return descricao;
    }

    public byte getQuantidade() {
        return quantidade;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setQuantidade(byte quantidade) {
        if (quantidade >= 0) {
            this.quantidade = quantidade;
        }
    }
}