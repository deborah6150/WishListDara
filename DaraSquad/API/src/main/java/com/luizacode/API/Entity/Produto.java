package com.luizacode.API.Entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity //Informa que se trata de uma entidade;
@Table(name = "TB_PRODUTO") //Informa nome da tabela que ela utiliza;
public class Produto implements Serializable {

    //id do produto gerado automaticamente e autoincrementado;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idProduto;

    private String nome;
    private BigDecimal valor;

    @JsonIgnore
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "produto_wishlist",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "wishlist_id"))
    private List <Wishlist> listaWishlist;


    //GETTERS E SETTERS

    public long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(long idProduto) {
        this.idProduto = idProduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public List<Wishlist> getListaWishlist() {
        return listaWishlist;
    }

    public void setListaWishlist(List<Wishlist> listaWishlist) {
        this.listaWishlist = listaWishlist;
    }


}
