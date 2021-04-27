public class AlphaBeta {
    int minMax(Quadro quadroAtual, int alpha, int beta, String jogadorAtual) {
        if (quadroAtual.terminou){
            return funcaoUtilidade(quadroAtual, jogadorAtual);
        }
        if (quadroAtual.jogador == jogadorAtual){
            return valorMax(quadroAtual, alpha, beta, jogadorAtual);
        }
        else{
            return valorMin(quadroAtual, alpha, beta, jogadorAtual);
        }
    }

    int funcaoUtilidade(Quadro quadroAtual, String jogador) {
        String contraQuem;
        if (jogador == "x"){
            contraQuem = "o";
        }
        else {
            contraQuem = "x";
        }
        if (quadroAtual.terminou && quadroAtual.vencedor == jogador) {
            return 1;
        }
        else if (quadroAtual.terminou && quadroAtual.vencedor == contraQuem) {
            return -1;
        }
        else {
            return 0;
        }
    }

    int valorMax(Quadro quadroAtual, int alpha, int beta, String jogadorAtual) {
        int m = Integer.MIN_VALUE;
        int indice = -1;

        for (int moves : quadroAtual.movimentosDisponiveis) {
            Quadro novoQuadro = quadroAtual.copiarQuadro();
            novoQuadro.jogar(moves/3, moves%3);

            int valor = minMax(novoQuadro, alpha, beta, jogadorAtual);

            if (valor > m) {
                m = valor;
                indice = m;
            }

            if (m >= beta) {
                if (indice > -1) quadroAtual.jogar(indice/3, indice%3);
                return m;
            }

            if (m > alpha) {
                alpha = valor;
                indice = moves;
            }
        }

        if (indice > -1) {
            quadroAtual.jogar(indice/3, indice%3);
        }
        return m;
    }

    int valorMin(Quadro quadroAtual, int alpha, int beta, String jogadorAtual) {
        int m = Integer.MAX_VALUE;
        int indice = -1;

        for (int moves : quadroAtual.movimentosDisponiveis) {
            Quadro novoQuadro = quadroAtual.copiarQuadro();
            novoQuadro.jogar(moves/3, moves%3);

            int valor = minMax(novoQuadro, alpha, beta, jogadorAtual);
            if (valor < m) {
                m = valor;
                indice = moves;
            }

            if (m <= alpha) {
                if (indice > -1) quadroAtual.jogar(indice/3, indice%3);
                return m;
            }

            if (m < beta) {
                beta = valor;
                indice = moves;
            }
        }

        if (indice > -1) {
            quadroAtual.jogar(indice/3, indice%3);
        }
        return m;
    }

    public static void main(String[] args) {
        Tela t = new Tela();
    }
}