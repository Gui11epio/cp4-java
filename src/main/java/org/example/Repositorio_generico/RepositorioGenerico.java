package org.example.Repositorio_generico;

import org.example.Entidades.Artista;
import org.example.Entidades._EntidadeBase;

import java.util.ArrayList;

public interface RepositorioGenerico <T>{
    void adicionar(T entidade);
    ArrayList<T> exibir();
    void editar(T entidade);
    void excluir(int id);
    T buscarPorId(int id);


}
