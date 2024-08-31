package org.example.Repositorio_generico;

import org.example.Entidades.Album;
import org.example.Entidades.Artista;
import org.example.Log.Loggable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RepositorioAlbum implements RepositorioGenerico<Album>, Loggable<Album> {
    private List<Album> albums = new ArrayList<>();
    private int idAtual = 1;


    @Override
    public void adicionar(Album album) {
        album.setId(idAtual++);
        logInfo("Album cadastrado com sucesso");
        albums.add(album);

    }

    @Override
    public ArrayList<Album> exibir() {
        return new ArrayList<>(albums);
    }

    @Override
    public void editar(Album entidade) {

    }

    @Override
    public void excluir(int id) {
        Album album = buscarPorId(id);
        if(album != null){
            albums.remove(album);
        }

    }

    @Override
    public Album buscarPorId(int id) {
        return albums.stream().filter(a -> a.getId() == id && !a.isDeleted()).findFirst().orElse(null);
    }

    public List<Album> buscarPorAno(int ano) {
        return albums.stream().filter(a -> a.getAnoLancamento() == ano).collect(Collectors.toList());
    }
}
