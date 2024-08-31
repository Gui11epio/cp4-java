package org.example.Repositorio_generico;

import org.example.Entidades.Musica;
import org.example.Log.Loggable;

import java.util.ArrayList;
import java.util.List;

public class RepositorioMusica implements RepositorioGenerico<Musica>, Loggable<Musica> {
    private List<Musica> musicas = new ArrayList<>();
    private int idAtual = 1;


    public RepositorioMusica(){
        this.musicas = new ArrayList<>();
    }

    @Override
    public void adicionar(Musica musica) {
        musica.setId(idAtual++);
        logInfo("Música Cadastrada com sucesso");
        musicas.add(musica);
    }

    @Override
    public ArrayList<Musica> exibir() {
        return new ArrayList<>(musicas);
    }

    @Override
    public void editar(Musica entidade) {

    }

    @Override
    public void excluir(int id) {
        Musica musica = buscarPorId(id);
        if(musica != null){
            musicas.remove(musica);
        }

    }

    @Override
    public Musica buscarPorId(int id) {
        return musicas.stream().filter(a -> a.getId() == id && !a.isDeleted()).findFirst().orElse(null);
    }


}
