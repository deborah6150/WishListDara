package com.luizacode.API.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity //Informa que se trata de uma entidade;
@Table(name = "TB_WISHLIST") //Informa nome da tabela que ela utiliza;
public class Wishlist {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idWishlist;

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "idCliente")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cliente cliente;

    @ManyToMany(mappedBy = "listaWishlist", cascade = CascadeType.ALL)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Produto> listaProdutos = new ArrayList<>();


    public Wishlist() {
    }

    public Wishlist(Cliente cliente) {
        this.cliente = cliente;
    }

    //GETTERS E SETTERS

    public long getIdWishlist() {
        return idWishlist;
    }

    public void setIdWishlist(long idWishlist) {
        this.idWishlist = idWishlist;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }
}
