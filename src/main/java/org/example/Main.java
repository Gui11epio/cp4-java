package org.example;

import org.example.Entidades.Album;
import org.example.Entidades.Artista;
import org.example.Entidades.Musica;
import org.example.Repositorio_generico.RepositorioAlbum;
import org.example.Repositorio_generico.RepositorioArtista;
import org.example.Repositorio_generico.RepositorioMusica;
import org.example.Serviço_De_Validacao.ValidacaoEntidade;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Main {
    private static Logger logger = LogManager.getLogger(Main.class);
    private static  RepositorioArtista repositorioArtista = new RepositorioArtista();
    private static RepositorioAlbum repositorioAlbum = new RepositorioAlbum();
    private static RepositorioMusica repositorioMusica = new RepositorioMusica();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        while(opcao != 4){
            System.out.println("===== BEM-VINDO =====");
            System.out.println("\t");
            System.out.println("\t1 - Cadastrar Artista");
            System.out.println("\t2 - Cadastrar Álbum");
            System.out.println("\t3 - Cadastrar Música");
            System.out.println("\t4 - Mostrar");
            System.out.println("\t5 - Buscar Artista");
            System.out.println("\t6 - Buscar Álbum");
            System.out.println("\t7 - Sair");
            System.out.println("\t");
            System.out.println("Escolha uma das opções: ");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha){
                case 1:
                    System.out.println("==== CADASTRAR ARTISTA ====");
                    System.out.println("Nome do artista: ");
                    String nomeArtista = scanner.nextLine();
                    System.out.println("Gênero Musical");
                    String generoArtista = scanner.nextLine();


                    Artista artista = new Artista(nomeArtista,generoArtista);
                    if(ValidacaoEntidade.validacaoArtista(artista)){
                        repositorioArtista.adicionar(artista);
                    }else {
                        System.out.println("Dados Inválidos");
                        logger.warn("Artista cadastrado com dados inválidos");
                    }
                    System.out.println("\t");
                    break;


                case 2:
                    System.out.println("==== CADASTRAR ÁLBUM ====");
                    System.out.println("Título do Albúm: ");
                    String tituloAlbum = scanner.nextLine();
                    System.out.println("Ano de Lançamento: ");
                    int anoAlbum = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("ID do Artista: ");
                    int idArtista = scanner.nextInt();
                    scanner.nextLine();

                    Artista artistaAlbum = repositorioArtista.buscarPorId(idArtista);
                    if(artistaAlbum!= null){
                        Album album = new Album(tituloAlbum,anoAlbum,artistaAlbum);
                        if(ValidacaoEntidade.validacaoAlbum(album)){
                            repositorioAlbum.adicionar(album);
                            artistaAlbum.addAlbum(album);
                        } else {
                            System.out.println("Dados Inválidos");
                            logger.warn("Álbum cadastrado com dados inválidos");
                        }
                    } else{
                        System.out.println("Artista não foi encontrado");
                    }
                    System.out.println("\t");
                    break;




                case 3:
                    System.out.println("==== CADASTRO DE MÚSICA ====");
                    System.out.println("Título da Música: ");
                    String tituloMusica = scanner.nextLine();
                    System.out.println("Duração da música: ");
                    double duracaoMusica = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("ID da Música: ");
                    int idAlbum = scanner.nextInt();
                    scanner.nextLine();

                    Album album = repositorioAlbum.buscarPorId(idAlbum);
                    if(album!= null){
                        Musica musica = new Musica(tituloMusica,duracaoMusica,album);
                        if(ValidacaoEntidade.validacaoMusica(musica)){
                            repositorioMusica.adicionar(musica);
                            album.addMusica(musica);
                            System.out.println("\t");
                            break;

                        } else{
                            System.out.println("Dados inválidos");
                            logger.warn("Música cadastrada com dados inválidos");
                        }

                    }
                    else {
                        System.out.println("Nenhum Álbum encontrado");
                    }
                    System.out.println("\t");
                    break;
                case 4:
                    System.out.println("==== LISTA DOS ARTISTAS ====");
                    for(Artista artista1 : repositorioArtista.exibir())
                        System.out.println("\n- ID: " + artista1.getId()
                                + "\n- Nome: " + artista1.getNome()
                                + "\n- Gênero Musical: " + artista1.getGeneroMusical());

                    System.out.println("\t");

                    System.out.println("==== LISTA DE ÁLBUM ====");
                    for(Album album1 : repositorioAlbum.exibir())
                        System.out.println("\n- ID: " + album1.getId()
                                + "\n- Título: " + album1.getTitulo()
                                + "\n- Ano de Lançamento: " + album1.getAnoLancamento()
                                + "\n- Artista: " + album1.getArtista());

                    System.out.println("\t");

                    System.out.println("==== LISTA DE MÚSICA ====");
                    for(Musica musica : repositorioMusica.exibir())
                        System.out.println("\n- ID: " + musica.getId()
                                + "\n- Título: " + musica.getTitulo()
                                + "\n- Duração: " + musica.getDuracao() + " minutos"
                                + "\n- Álbum: " + musica.getAlbum());
                    System.out.println("\t");
                    break;



                case 5:
                    System.out.println("==== BUSCA POR GÊNERO ====");
                    System.out.println("\t");
                    System.out.println("Coloque o gênero musical: ");
                    String genero = scanner.nextLine();

                    List<Artista> artistas = repositorioArtista.buscarPorGenero(genero);
                    if(artistas.isEmpty()){
                        System.out.println("Nenhum gênero encontrado");
                    }else{
                        System.out.println("Artistas encontrados: ");
                        for(Artista artista1: artistas){
                            System.out.println("\n- ID: " + artista1.getId()
                                    + "\n- Nome: " + artista1.getNome());
                        }
                    }
                    System.out.println("\t");
                    break;

                case 6:
                    System.out.println("==== BUSCA POR ANO DE LANÇAMENTO ====");
                    System.out.println("Coloque o Ano de Lançamento: ");
                    int ano = scanner.nextInt();

                    List<Album> albums = repositorioAlbum.buscarPorAno(ano);
                    if(albums.isEmpty()){
                        System.out.println("Nenhum álbum encontrado para esse ano");
                    }else {
                        System.out.println("==== Álbums encontrados ====");
                        for (Album album1 : albums){
                            System.out.println("\n- ID: " + album1.getId()
                                    + "\n- Título: " + album1.getTitulo());
                        }
                    }
                    System.out.println("\t");
                    break;

                case 7:
                    opcao = 4;
                    break;

            }
        }

    }
}