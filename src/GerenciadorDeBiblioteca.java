import java.util.InputMismatchException;
import java.util.Scanner;

public class GerenciadorDeBiblioteca {
    // Declarando variavel global
    static Scanner ler = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // Declarando variaveis
        String[] tituloLivro = new String[100], autorLivro = new String[100];
        boolean confimaSaida = false;
        int[] anoPublicacao = new int[100];
        int opcao = 0; // Opção da escolha do menu
        // Entrando no loop
        do {
            // Mostrando Opções para o Usuario
            System.out.println("\n=========================");
            System.out.println("| >>>MENU DE OPÇÕES<<<  |");
            System.out.println("=========================");
            System.out.println("| 1. Cadastrar um livro.|");
            System.out.println("| 2. Pesquisar Livro.   |");
            System.out.println("| 3. Remover um Livro.  |");
            System.out.println("| 4. Listar Livros.     |");
            System.out.println("| 5. Sair               |");
            System.out.println("=========================");
            System.out.print("\n Digite o numero da opção desejada: ");
            try {
                opcao = ler.nextInt();// Recebe a opção digitada pelo usuario
                ler.nextLine(); // limpar o buffer
                // Recebe a opção do usuario e verifica ela no switch qual foi a opção escolhida
                switch (opcao) {
                    // Caso opção 1 seja digitada
                    case 1:
                        System.out.println("\n>>>Opção escolhida foi 1. Cadastrar um livro.<<<");// Messagem de opçãa
                                                                                                 // escolhida
                        cadastrarLivro(tituloLivro, autorLivro, anoPublicacao); // Chama o metodo que cadastra um livro
                        break;
                    // Caso opção 2 seja digitada
                    case 2:
                        System.out.println("\n>>>Opção escolhida foi 2. Pesquisar Livro.<<<");// Messagem de opçãa
                                                                                              // escolhida
                        System.out.println("Digite o titulo do livro que deseja pesquisar: ");
                        String titulo = ler.nextLine();// Recebe o titulo do livro que o usuario deseja pesquisar
                        int indice = pesquisarLivro(tituloLivro, titulo); // recebe o valor retornado pelo método
                        if (indice == -1) { // Se o valor retornado pelo metodo for -1 mostra a menssagem de livro não
                                            // cadastrado
                            System.out.println("Esse livro não está cadastrado!");
                        } else { // Se o valor retornado pelo metodo for o Indice no array do livro mostra o
                                 // livro cadastrado
                            System.out.println(">>>Esse livro está cadastrado!!<<<");
                            System.out.println("Título: " + tituloLivro[indice]);
                            System.out.println("Autor: " + autorLivro[indice]);
                            System.out.println("Ano: " + anoPublicacao[indice]);
                        }
                        break;
                    // Caso opção 3 seja digitada
                    case 3:
                        System.out.println("\n>>>Opção escolhida foi 3. Remover um Livro.<<<");// Mesagem de opçãa
                                                                                               // escolhida
                        System.out.println("Digite o titulo do livro para remover ou 'SAIR' para encerrar a remoção: ");
                        String tituloRemover = ler.nextLine();// Recebe o titulo do livro que o usuario deseja remover
                        removerLivro(tituloLivro, autorLivro, anoPublicacao, tituloRemover);// Chama o metodo de remoção
                                                                                            // do livro
                        break;
                    // Caso opção 4 seja digitada
                    case 4:
                        System.out.println("\n>>>Opção escolhida foi 4. Listar Livros.<<<");// Mesagem de opçãa
                                                                                            // escolhida
                        listarLivros(tituloLivro, autorLivro, anoPublicacao); // Chama o metodo de Listar Livros
                        break;
                    // Caso opção 5 seja digitada
                    case 5:
                        System.out.println(" Tem certeza que deseja sair?");
                        System.out.print(" Digite 'Sim' se realmente deseja sair ou 'Não' para cancelar saida: ");
                        String verificadorSaida = ler.nextLine();
                        if(verificadorSaida.equalsIgnoreCase("sim")){
                            confimaSaida = true;
                            System.out.println("Saindo do programa...");// Mesagem de encerramento do programa
                            break;
                        } else{
                            System.out.println(">>>Retornando ao menu<<<");
                        }
                        break;
                    // Caso nenhuma opção valida seja digitada
                    default:
                        System.out.println(">>>Digite uma opção valida!!!<<<");// Mesagem casa o usuario não digite
                                                                               // nunhma opçaõ disponivel
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println(">>>Digite uma opção valida!!!<<<");
                ler.nextLine();
            }
        } while ( !confimaSaida);// A condição é caso a opção digitada seja diferente de 5 o loop/Programa
                             // continue rodando caso contrario ele encerra
    }

    // Metodo que cadastra um livro caso opção 1 seja escolhida
    public static void cadastrarLivro(String[] tituloLivro, String[] autorLivro, int[] anoPublicacao) {
        String titulo, autor;
        int i = 0, ano;
        boolean sair = false;

        while (!sair && i < tituloLivro.length) {
            System.out.println("Digite o titulo do livro para cadastrar ou 'SAIR' para encerrar o cadastro: ");
            titulo = ler.nextLine();
            // se sair tiver sido digitado, sair passa a ser true e sai do loop
            if (titulo.equalsIgnoreCase("sair")) {
                System.out.println("Saindo do cadastro...");
                sair = true;
            } else { // Se sair não estiver sido digitado ele continua o loop/cadastro
                if (livroCadastrado(tituloLivro, titulo)) {
                    System.out.println(">>>Livro ja esta cadastrado!!<<<\n");
                } else {
                    tituloLivro[i] = titulo; // adiciona o título do livro no array
                    System.out.println("Digite o autor do livro: ");
                    autor = ler.nextLine();
                    autorLivro[i] = autor; // adiciona o autor do livro no array
                    System.out.println("Digite o ano de publicação do livro: ");
                    ano = ler.nextInt();
                    ler.nextLine(); // limpar o buffer
                    anoPublicacao[i] = ano; // adiciona o ano de publicação do livro no array
                    i++; // incrementa o índice
                    System.out.println(">>>Livro foi cadastrado com SUCESSO!!<<<\n");
                }
            }
        }
    }

    // Metodo verifica se o livro ja esta cadastradro ou não
    public static boolean livroCadastrado(String[] tituloLivro, String titulo) {
        // lopp para percorrer o array para verificar se o livro esta cadastrado
        for (int i = 0; i < tituloLivro.length; i++) {
            if (titulo.equalsIgnoreCase(tituloLivro[i])) {
                return true;// Retorna true se livro ja estiver cadastrado
            }
        }
        return false;// Retorna false se livro não estiver cadastrado
    }

    // Metodo que pesquisa um livro caso opção 2 seja escolhida
    public static int pesquisarLivro(String[] tituloLivro, String titulo) {
        for (int i = 0; i < tituloLivro.length; i++) {
            if (titulo.equalsIgnoreCase(tituloLivro[i])) {
                return i; // retorna o índice do livro no array
            }
        }
        return -1; // retorna -1 se não encontrar o livro no array
    }

    // Metodo que remover um livro caso opção 3 seja escolhida
    public static void removerLivro(String[] tituloLivro, String[] autorLivro, int[] anoPublicacao, String titulo) {
        int indice = pesquisarLivro(tituloLivro, titulo); // recebe o valor retornado pelo método
        boolean sair = false;
        while (!sair) {// O loop continua saso o usuario não digite sair
            if (titulo.equalsIgnoreCase("sair")) {// Caso o usuario digitar "sair" o programa sai da remoção de livros
                System.out.println("Saindo da remoção de livro...");
                sair = true;// A variavel sair passa a ser true para encerrar o loop
            } else {// Caso o não digitar "sair"
                if (indice == -1) { // Se o indice do array retornado pelo metodo pesquisarLivro for igual a -1
                    System.out.println("Esse livro não está cadastrado!");
                    break;
                } else {// Caso o indice do array retornado pelo metodo pesquisarLivro
                    tituloLivro[indice] = null; // remove o título do livro do array
                    autorLivro[indice] = null; // remove o autor do livro do array
                    anoPublicacao[indice] = 0; // remove o ano de publicação do livro do array
                    for (int i = indice + 1; i < tituloLivro.length; i++) { // compacta os elementos restantes
                        tituloLivro[i - 1] = tituloLivro[i];
                        tituloLivro[i] = null;
                        autorLivro[i - 1] = autorLivro[i];
                        autorLivro[i] = null;
                        anoPublicacao[i - 1] = anoPublicacao[i];
                        anoPublicacao[i] = 0;
                    }
                    System.out.println("O livro foi removido com sucesso!");
                    break; // Encerra o loop
                }
            }
        }
    }

    // Metodo que lista os livros cadastrados caso opção 4 seja escolhida
    public static void listarLivros(String[] tituloLivro, String[] autorLivro, int[] anoPublicacao) {
        int contador = 0; // contador de livros
        System.out.println("Livros cadastrados:");
        for (int i = 0; i < tituloLivro.length; i++) { // percorre o array
            if (tituloLivro[i] != null) { // verifica se o elemento não é nulo
                contador++; // incrementa o contador
                System.out.println(
                        contador + ". " + tituloLivro[i] + " - " + autorLivro[i] + " (" + anoPublicacao[i] + ")"); // mostra as informações do livro
            }
        }
        if (contador == 0) { // verifica se nenhum livro foi listado
            System.out.println("Não há livros cadastrados!");
        }
    }

}
