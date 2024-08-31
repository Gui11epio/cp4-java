package org.example.Repositorio_generico;

import org.example.Entidades._EntidadeBase;

import java.util.ArrayList;

public  abstract class RepositorioImplementacao <T extends _EntidadeBase> implements RepositorioGenerico<T> {

    ArrayList<T> lista = new ArrayList<>();

    @Override
    public void adicionar(T entidade) {
        lista.add(entidade);

    }

    @Override
    public ArrayList<T> exibir() {
        return lista;
    }

    @Override
    public void editar(T entidade) {
        var entidadeAtualizado = lista.stream().filter(x -> x.getId() == entidade.getId()).findFirst().get();
        lista.remove(entidadeAtualizado);
        lista.add(entidade);


    }

    @Override
    public void excluir(int id) {
        lista.remove(id);

    }

    public RepositorioImplementacao(ArrayList<T> lista) {
        this.lista = lista;
    }
}
