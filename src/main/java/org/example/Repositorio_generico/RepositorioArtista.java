package org.example.Repositorio_generico;

import org.example.Entidades.Artista;
import org.example.Log.Loggable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class RepositorioArtista implements RepositorioGenerico<Artista>, Loggable<Artista> {


    private List<Artista> artistas = new ArrayList<>();
    private int idAtual = 1;

    @Override
    public void adicionar(Artista artista) {
        artista.setId(idAtual++); // adiciona um valor quando é criado o artista
        logInfo("Artista cadastrado com sucesso");
        artistas.add(artista);

    }

    @Override
    public ArrayList<Artista> exibir() {
        return new ArrayList<>(artistas);
    }

    @Override
    public void editar(Artista artista) {
        Artista atual = buscarPorId(artista.getId());
        if (atual != null){
            atual.setNome(artista.getNome());
            atual.setGeneroMusical(artista.getGeneroMusical());
            logInfo("Artista atualizado com sucesso");
        }


    }

    @Override
    public void excluir(int id) {
        Artista artista = buscarPorId(id);
        if(artista != null){
            artistas.remove(artista);
            logInfo("Artista excluido com sucesso");
        }

    }

    @Override
    public Artista buscarPorId(int id) {
        return artistas.stream().filter(a -> a.getId() == id && !a.isDeleted()).findFirst().orElse(null);
    }

    public List<Artista> buscarPorGenero(String genero) {
        return artistas.stream().filter(a -> a.getGeneroMusical().equalsIgnoreCase(genero)).collect(Collectors.toList());
    }
}
