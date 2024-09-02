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
            System.out.println("\t1 - Cadastro");
            System.out.println("\t2 - Mostrar");
            System.out.println("\t3 - Buscar");
            System.out.println("\t4 - Excluir ");
            System.out.println("\t5 - Atualizar");
            System.out.println("\t6 - Sair");
            System.out.println("\t");
            System.out.println("Escolha uma das opções: ");

            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha){
                case 1:
                    while(true){
                        System.out.println("==== CADASTRO ====");
                        System.out.println("\t1 - Cadastrar Artista");
                        System.out.println("\t2 - Cadastrar Álbum");
                        System.out.println("\t3 - Cadastrar Música");
                        System.out.println("\t0 - Sair");

                        int escolhaCadastro = scanner.nextInt();
                        scanner.nextLine();

                        switch (escolhaCadastro){
                            case 1:
                                System.out.println("==== CADASTRAR ARTISTA ====");
                                System.out.println("Nome do artista: ");
                                String nomeArtista = scanner.nextLine();
                                System.out.println("Gênero Musical: ");
                                String generoArtista = scanner.nextLine();


                                Artista artista = new Artista(nomeArtista,generoArtista);
                                if(ValidacaoEntidade.validacaoArtista(artista)){
                                    repositorioArtista.adicionar(artista);
                                    break;
                                }else {
                                    System.out.println("Dados Inválidos");
                                    logger.warn("Artista com dados inválidos. O Artista não pode ser vazio");
                                    break;
                                }


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
                                        break;
                                    } else {
                                        System.out.println("Dados Inválidos");
                                        logger.warn("Álbum com dados inválidos.");
                                        break;
                                    }
                                } else{
                                    System.out.println("ID do artista não foi encontrado");
                                    break;
                                }



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
                                        break;
                                    }

                                }
                                else {
                                    System.out.println("ID do álbum  não foi encontrado");
                                    break;
                                }

                            case 0:
                                break;

                            default:
                                System.out.println("Opção Inválida.Tente novamente");
                        }
                        if(escolhaCadastro == 0){
                            break;
                        }
                    }
                    break;


                case 2:
                    while(true){
                        System.out.println("==== EXIBIÇÃO ====");
                        System.out.println("\t1 - Exibir Artista");
                        System.out.println("\t2 - Exibir Álbums");
                        System.out.println("\t3 - Exibir Músicas");
                        System.out.println("\t0 - Sair");

                        int escolhaExibir = scanner.nextInt();
                        scanner.nextLine();

                        switch (escolhaExibir){
                            case 1:
                                System.out.println("==== LISTA DOS ARTISTAS ====");
                                for(Artista artista1 : repositorioArtista.exibir())
                                    System.out.println("\n- ID: " + artista1.getId()
                                            + "\n- Nome: " + artista1.getNome()
                                            + "\n- Gênero Musical: " + artista1.getGeneroMusical());
                                System.out.println("\t");
                                break;


                            case 2:
                                System.out.println("==== LISTA DE ÁLBUM ====");
                                for(Album album1 : repositorioAlbum.exibir())
                                    System.out.println("\n- ID: " + album1.getId()
                                            + "\n- Título: " + album1.getTitulo()
                                            + "\n- Ano de Lançamento: " + album1.getAnoLancamento()
                                            + "\n- Artista: " + album1.getArtista());
                                System.out.println("\t");
                                break;



                            case 3:
                                System.out.println("==== LISTA DE MÚSICA ====");
                                for(Musica musica : repositorioMusica.exibir())
                                    System.out.println("\n- ID: " + musica.getId()
                                            + "\n- Título: " + musica.getTitulo()
                                            + "\n- Duração: " + musica.getDuracao() + " minutos"
                                            + "\n- Álbum: " + musica.getAlbum());
                                System.out.println("\t");
                                break;


                            case 0:
                                break;

                            default:
                                System.out.println("Opção Inválida.Tente Novamente");
                        }
                        if(escolhaExibir == 0){
                            break;
                        }
                    }
                    break;

                case 3:
                    while(true){
                        System.out.println("==== BUSCA ====");
                        System.out.println("\t1 - Buscar Artista");
                        System.out.println("\t2 - Buscar Álbum");
                        System.out.println("\t0 - Sair");

                        int escolhaBusca = scanner.nextInt();
                        scanner.nextLine();

                        switch (escolhaBusca){
                            case 1:
                                System.out.println("==== BUSCAR ARTISTA POR GÊNERO ====");
                                System.out.println("\t");
                                System.out.println("Coloque o gênero musical: ");
                                String genero = scanner.nextLine();

                                List<Artista> artistas = repositorioArtista.buscarPorGenero(genero);
                                if(artistas.isEmpty()){
                                    System.out.println("Nenhum gênero encontrado");
                                    break;
                                }else{
                                    System.out.println("Artistas encontrados: ");
                                    for(Artista artista1: artistas){
                                        System.out.println("\n- ID: " + artista1.getId()
                                                + "\n- Nome: " + artista1.getNome());

                                    }
                                    break;
                                }



                            case 2:
                                System.out.println("==== BUSCAR ÁLBUM POR ANO DE LANÇAMENTO ====");
                                System.out.println("Coloque o Ano de Lançamento: ");
                                int ano = scanner.nextInt();

                                List<Album> albums = repositorioAlbum.buscarPorAno(ano);
                                if(albums.isEmpty()){
                                    System.out.println("Nenhum álbum encontrado para esse ano");
                                    break;
                                }else {
                                    System.out.println("==== Álbums encontrados ====");
                                    for (Album album1 : albums){
                                        System.out.println("\n- ID: " + album1.getId()
                                                + "\n- Título: " + album1.getTitulo());

                                    }
                                    break;
                                }


                            case 0:
                                break;

                            default:
                                System.out.println("Opção Inválida.Tente Novamente");
                        }
                        if (escolhaBusca == 0){
                            break;
                        }
                    }
                    break;

                case 4:
                    while (true){
                        System.out.println("==== EXCLUIR ====");
                        System.out.println("\t1 - Excluir Artista");
                        System.out.println("\t2 - Excluir Álbum");
                        System.out.println("\t3 - Excluir Música");
                        System.out.println("\t0 - Voltar");

                        int escolhaExcluir = scanner.nextInt();
                        scanner.nextLine();

                        switch (escolhaExcluir){
                            case 1:
                                System.out.println("==== EXCLUIR ARTISTA ====");
                                System.out.println("\t");
                                for(Artista artista1 : repositorioArtista.exibir())
                                    System.out.println("\n- ID: " + artista1.getId()
                                            + "\n- Nome: " + artista1.getNome()
                                            + "\n- Gênero Musical: " + artista1.getGeneroMusical());
                                System.out.println("\t");

                                System.out.println("ID do Artista que deseja excluir: ");
                                int idArtista = scanner.nextInt();
                                scanner.nextLine();

                                Artista artista = repositorioArtista.buscarPorId(idArtista);
                                if(artista != null){
                                    repositorioArtista.excluir(artista.getId());
                                    break;
                                }else{
                                    System.out.println("Nenhum artista encontrado");
                                    break;
                                }


                            case 2:
                                System.out.println("==== EXCLUIR ÁLBUM ====");
                                for(Album album1 : repositorioAlbum.exibir())
                                    System.out.println("\n- ID: " + album1.getId()
                                            + "\n- Título: " + album1.getTitulo()
                                            + "\n- Ano de Lançamento: " + album1.getAnoLancamento()
                                            + "\n- Artista: " + album1.getArtista());
                                System.out.println("\t");

                                System.out.println("ID do Álbum que deseja excluir: ");
                                int idAlbum = scanner.nextInt();
                                scanner.nextLine();

                                Album album = repositorioAlbum.buscarPorId(idAlbum);
                                if(album != null){
                                    repositorioAlbum.excluir(album.getId());
                                    break;
                                }else{
                                    System.out.println("Nenhum Álbum encontrado");
                                    break;
                                }


                            case 3:
                                for(Musica musica1 : repositorioMusica.exibir())
                                    System.out.println("\n- ID: " + musica1.getId()
                                            + "\n- Título: " + musica1.getTitulo()
                                            + "\n- Duração: " + musica1.getDuracao()
                                            + "\n- Álbum: " + musica1.getAlbum());
                                System.out.println("\t");

                                System.out.println("ID da Música que deseja excluir: ");
                                int idMusica = scanner.nextInt();
                                scanner.nextLine();

                                Musica musica = repositorioMusica.buscarPorId(idMusica);
                                if(musica != null){
                                    repositorioMusica.excluir(musica.getId());
                                    break;
                                }else{
                                    System.out.println("Nenhuma Música encontrada");
                                    break;
                                }

                            case 0:
                                break;

                            default:
                                System.out.println("Opção Inválida. Tente Novamente");
                        }
                        if(escolhaExcluir == 0){
                            break;
                        }
                    }
                    break;

                case 5:
                    while(true){
                        System.out.println("==== ATUALIZAR ====");
                        System.out.println("\t");
                        System.out.println("\t1 - Atualizar Artista");
                        System.out.println("\t2 - Atualizar Álbum");
                        System.out.println("\t3 - Atualizar Música");
                        System.out.println("\t0 - Voltar");

                        int escolhaAtl = scanner.nextInt();
                        scanner.nextLine();

                        switch (escolhaAtl){
                            case 1:
                                System.out.println("==== ATUALIZAR ARTISTA ====");
                                for(Artista artista1 : repositorioArtista.exibir())
                                    System.out.println("\n- ID: " + artista1.getId()
                                            + "\n- Nome: " + artista1.getNome()
                                            + "\n- Gênero Musical: " + artista1.getGeneroMusical());
                                System.out.println("\t");

                                System.out.println("ID do Artista que deseja editar: ");
                                int idArtista = scanner.nextInt();
                                scanner.nextLine();

                                Artista artista = repositorioArtista.buscarPorId(idArtista);
                                if(artista != null){
                                    System.out.println("Novo nome: ");
                                    String novoNome = scanner.nextLine();
                                    System.out.println("Novo gênero Musical: ");
                                    String novoGenero = scanner.nextLine();

                                    artista.setNome(novoNome);
                                    artista.setGeneroMusical(novoGenero);

                                    repositorioArtista.editar(artista);
                                    break;
                                }else {
                                    logger.info("Artista não foi encontrado");
                                    break;
                                }

                            case 2:
                                System.out.println("==== ATUALIZAR ÁLBUM ====");
                                for(Album album1 : repositorioAlbum.exibir())
                                    System.out.println("\n- ID: " + album1.getId()
                                            + "\n- Título: " + album1.getTitulo()
                                            + "\n- Ano de Lançamento: " + album1.getAnoLancamento()
                                            + "\n- Artista: " + album1.getArtista());
                                System.out.println("\t");

                                System.out.println("ID do Álbum que deseja editar: ");
                                int idAlbum = scanner.nextInt();
                                scanner.nextLine();

                                Album album = repositorioAlbum.buscarPorId(idAlbum);
                                if(album != null){
                                    System.out.println("Novo Título: ");
                                    String novoTitulo = scanner.nextLine();
                                    System.out.println("Novo ano de lançamento: ");
                                    int novoAno = scanner.nextInt();

                                    album.setTitulo(novoTitulo);
                                    album.setAnoLancamento(novoAno);

                                    repositorioAlbum.editar(album);
                                    break;
                                }else {
                                    logger.info("Álbum não encontrado");
                                    break;
                                }
                            case 3:
                                System.out.println("==== ATUALIZAR MÚSICA ====");
                                for(Musica musica1 : repositorioMusica.exibir())
                                    System.out.println("\n- ID: " + musica1.getId()
                                            + "\n- Título: " + musica1.getTitulo()
                                            + "\n- Duração: " + musica1.getDuracao()
                                            + "\n- Álbum: " + musica1.getAlbum());
                                System.out.println("\t");

                                System.out.println("ID da Música que deseja excluir: ");
                                int idMusica = scanner.nextInt();
                                scanner.nextLine();

                                Musica musica = repositorioMusica.buscarPorId(idMusica);
                                if(musica != null){
                                    System.out.println("Novo título: ");
                                    String novoTitulo = scanner.nextLine();
                                    System.out.println("Nova Duração: ");
                                    int novaDuracao = scanner.nextInt();
                                    scanner.nextLine();

                                    musica.setTitulo(novoTitulo);
                                    musica.setDuracao(novaDuracao);

                                    repositorioMusica.editar(musica);
                                    break;
                                }else{
                                    System.out.println("Nenhuma Música encontrada");
                                    break;
                                }

                            case 0:
                                break;

                            default:
                                System.out.println("Opção Inválida. Tente Novamente");
                        }
                        if(escolhaAtl == 0){
                            break;
                        }
                    }
                    break;





                case 6:
                    opcao = 4;
                    break;

                default:
                    System.out.println("Opção Inválida. Tente Novamente");


            }
        }

    }
}