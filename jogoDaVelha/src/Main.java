// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        int tamanho = 3;
        String [][] tabularo = new String[tamanho][tamanho];
        String [] jogadorX = espacoArmazenarJogadas(tamanho);
        String [] jogadorO = espacoArmazenarJogadas(tamanho);

        fazerJogada("00", tabularo, "x");
        fazerJogada("01", tabularo, "x");
        fazerJogada("02", tabularo, "x");

        // vetor teste
        String [] teste = {"00", "01", "02"};
        System.out.println(vitoriaHorizontal(teste));

        printTabuleiro(tabularo);
    }

    public static void fazerJogada(String posicao, String [][] tabuleiro, String jogador){
        int linha ;
        int coluna ;

        coluna = index(posicao.charAt(1));
        linha = index(posicao.charAt(0));

        if(tabuleiro[linha][coluna] == null){
            tabuleiro[linha][coluna] =  jogador;
        }
    }
    public static boolean vitoriaHorizontal(String [] jogadas){
        return percorreHorizontal(jogadas) == 3;
    }

    private static int percorreHorizontal( String [] jogadas){
        int jogadaNaHorizontal = 0;
        for (String jogada : jogadas) {
            jogadaNaHorizontal += verificaLinha(jogada);
        }
        return jogadaNaHorizontal;
    }

    private static int verificaLinha(String posicao){

        if( posicao != null){
            System.out.println("Index: " + index(posicao.charAt(0)));
            if( index(posicao.charAt(0)) == 0) return 1;
        }
        return 0;
    }

    private static String [] espacoArmazenarJogadas( int tamanho){
        return new String[tamanho];
    }

    private static int index( char caracter){
        return Integer.parseInt(String.valueOf(caracter));
    }

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