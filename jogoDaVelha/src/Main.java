import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean jogoOn = true;
        String [][] tabularo = new String[3][3];
        String [] jogadorX = new String[5];
        String [] jogadorO = new String[5];

        Scanner scanner = new Scanner(System.in);
        String posicao ;
        String simbolo;

        while (jogoOn){

            try{
                System.out.println("Informe posicão que deseja jogar, Ex: 01");
                posicao = scanner.nextLine();
                System.out.println("Simbolo");
                simbolo = scanner.nextLine().toLowerCase();

                if(fazerJogada(posicao, tabularo, simbolo)){

                    if(simbolo.equals("x")){
                        salvarPosicao(jogadorX, posicao);
                        jogoOn = venceu(jogadorX);
                    }else{
                        salvarPosicao(jogadorO, posicao);
                        jogoOn = venceu(jogadorO);
                    }
                    printTabuleiro(tabularo);

                    if(jogoOn) {
                        jogoOn = empate(tabularo);
                    }
                }else {
                    System.out.println("Posição já foi escolhida");
                }
            }catch (RuntimeException ex){
                System.out.println(ex.getMessage());
            }
        }
        System.out.println("FIM DO JOGO!");
    }

    public static boolean venceu( String [] jogadas){
        if(vitoriaNaVertical(jogadas)){
            return false;
        } else if (vitoriaNaHorizontal(jogadas)) {
            return false;
        } else if(vitoriaDiagonal(jogadas)){
            return false;
        }else {
            return true;
        }
    }

    // Efetua uma jogada no tabuleiro em uma pocisão vazia.
    public static boolean fazerJogada(String posicao, String [][] tabuleiro, String jogador){
        int linha ;
        int coluna ;

        coluna = index(posicao.charAt(1));
        linha = index(posicao.charAt(0));

        if(tabuleiro[linha][coluna] == null){
            tabuleiro[linha][coluna] =  jogador;
            return true;
        }
        return false;
    }

    public static boolean vitoriaNaHorizontal(String [] jogadas){
        return confereVitoria(jogadas, 0);
    }

    public static boolean vitoriaNaVertical(String [] jogadas){
        return confereVitoria(jogadas, 1);
    }

    public static boolean vitoriaDiagonal(String [] jogadas){
        return vitoriaDiagonal1(jogadas) || vitoriaDiagonal2(jogadas);
    }

    public static boolean vitoriaDiagonal1(String [] jogadas){
        // Soma l-> linha e c -> coluna que deve ser igual a 2, isso significa que é uma das posições na diagonal {20, 11, 02}
        int l;
        int c;
        int cont = 0;
        for(String jogada: jogadas){
            if(jogada != null){
                l = index(jogada.charAt(0));
                c = index(jogada.charAt(1));
                if( (l + c) == 2){
                    cont ++;
                }
            }
        }
        return cont == 3;
    }

    public static boolean vitoriaDiagonal2(String [] jogadas){
        // verifica se lina coluna são iguais {00, 11, 22}
        int l;
        int c;
        int cont = 0;
        for(String jogada: jogadas){
            if(jogada != null){
                l = index(jogada.charAt(0));
                c = index(jogada.charAt(1));
                if( l == c){
                    cont ++;
                }
            }
        }
        return cont == 3;
    }
    private static boolean confereVitoria( String [] jogadas, int linhaOuColuna){
        int jogadaNaHorizontal = 0;
        for(int j = 0; j < 3; j++){
            for (String jogada : jogadas) {
                jogadaNaHorizontal += verificaOcorrencia(jogada, j, linhaOuColuna);
            }
            if( jogadaNaHorizontal == 3){
                return true;
            }
            jogadaNaHorizontal = 0;
        }
        return false;
    }

    private static int verificaOcorrencia(String posicao, int linha, int linhaOuColuna){

        if( posicao != null){
            if( index(posicao.charAt(linhaOuColuna)) == linha) return 1;
        }
        return 0;
    }

    private static int index( char caracter){
        return Integer.parseInt(String.valueOf(caracter));
    }

    public static void salvarPosicao(String [] jogadas, String posicao){
        for (int i = 0; i < 5; i++) {
            if(jogadas[i] == null){
                jogadas[i] = posicao;
                break;
            }
        }
    }
public static boolean empate(String [][] tabuleiro){
    for (String [] linha: tabuleiro){
        for (String posicao: linha){
            if(posicao == null){
                return true;
            }
        }
    }
    return false;
}
    // Mostra tabuleiro na tela
    public static void printTabuleiro(String [][] tabuleiro){
        for (String [] linha: tabuleiro){
            for ( String jogada: linha){
                if( jogada != null){
                    System.out.print("|" + jogada + "|");
                } else {
                    System.out.print("|" + " " + "|");
                }
            }
            System.out.println("\n");
        }
    }


}