package FolhaDePagamento;

import java.util.Scanner;

public class Main {
    private static String[][] funcs = new String[1000][10];
    private static int indice_funcs = 0;

    // [i][0] = Nome
    // [i][1] = Endereço
    // [i][2] = Tipo de funcionário (1 - Horário, 2 - Assalariado, 3 - Comissionado)
    // [i][3] = Valor Hora
    // [i][4] = Salário
    // [i][5] = Comissão
    // [i][6] = Ativo
    // [i][7] = Pertence à sindicato
    // [i][8] = Taxa do sindicato
    // [i][9] = ID

    private static String[][] vendas_realizadas = new String[5000][3];
    private static int indice_vendas_realizadas = 0;

    // [i][0] = ID do funcionário
    // [i][1] = Valor
    // [i][2] = Data da venda

    private static String[][] cartoes_ponto = new String[5000][5];
    private static int indice_cartoes_ponto = 0;

    // [i][0] = ID
    // [i][1] = Data entrada
    // [i][2] = Hora entrada
    // [i][3] = Data saída
    // [i][4] = Hora saída

    private static String[][] taxas_de_servicos = new String[5000][2];
    private static int indice_taxas_de_servicos = 0;

    // [i][0] = ID
    // [i][1] = Valor da taxa

    /* ----------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------- */
    /* -------------------------------------- MENUS -------------------------------------- */
    /* ----------------------------------------------------------------------------------- */
    /* ----------------------------------------------------------------------------------- */
    private static void menus(int opc) {
        switch(opc) {
            case 0: // Menu principal
                System.out.println("\n-=-=- FOLHA DE PAGAMENTO -=-=-");
                System.out.println("1 - Funcionários");
                System.out.println("2 - Cartão Ponto");
                System.out.println("3 - Venda");
                System.out.println("4 - Taxa de Serviço");
                System.out.println("5 - Rodar Folha de Pagamento");
                System.out.println("6 - Agenda de Pagamentos");
                System.out.println("7 - Undo");
                System.out.println("8 - Redo");
                System.out.println("9 - Sair");
                System.out.print("--> ");
                break;
            case 1: // Menu de funcionários
                System.out.println("\n-=-=- FUNCIONÁRIOS -=-=-");
                System.out.println("1 - Adicionar Funcionário");
                System.out.println("2 - Remover Funcionário");
                System.out.println("3 - Editar Funcionário");
                System.out.println("4 - Listar Funcionários");
                System.out.println("5 - Retornar");
                System.out.print("--> ");
                break;
            case 2: // Menu de cartões ponto
                System.out.println("\n-=-=- CARTÃO PONTO -=-=-");
                System.out.println("1 - Bater Cartão Ponto Entrada");
                System.out.println("2 - Bater Cartão Ponto Saída");
                System.out.println("3 - Listar Cartões Ponto");
                System.out.println("4 - Retornar");
                System.out.print("--> ");
                break;
            case 3: // Menu de vendas
                System.out.println("\n-=-=- VENDAS -=-=-");
                System.out.println("1 - Lançar Venda");
                System.out.println("2 - Listar Vendas");
                System.out.println("3 - Retornar");
                System.out.print("--> ");
                break;
            case 4: // Menu de taxas de serviço
                System.out.println("\n-=-=- TAXA DE SERVIÇO -=-=-");
                System.out.println("1 - Lançar Taxa de Serviço");
                System.out.println("2 - Listar Taxas de Serviço");
                System.out.println("3 - Retornar");
                System.out.print("--> ");
                break;
            case 6: // Menu de agendas de pagamento
                System.out.println("\n-=-=- AGENDAS DE PAGAMENTO -=-=-");
                System.out.println("1 - Listar Agendas de Pagamento");
                System.out.println("2 - Cadastrar nova Agenda de Pagamento");
                System.out.println("3 - Retornar");
                System.out.print("--> ");
                break;
            default:
                System.out.println("\nMenu inexistente!\n");
        }
    } // Fim de "menus"


    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    /* --------------------------------------- MAIN --------------------------------------- */
    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    public static void main(String[] args) {
        while(true) {
            menus(0);
            Scanner input = new Scanner(System.in);
            int opcao = input.nextInt();
            if(opcao == 9) {
                System.out.println("\nAté logo!\n");
                System.exit(0);
            }

            switch(opcao) {
                case 1:
                    // System.out.println("FUNCIONÁRIOS");
                    funcionarios();
                    break;
                case 2:
                    // System.out.println("CARTÃO PONTO");
                    cartao_ponto();
                    break;
                case 3:
                    // System.out.println("VENDA");
                    vendas();
                    break;
                case 4:
                    // System.out.println("TAXA DE SERVIÇO");
                    taxa_de_servico();
                    break;
                case 5:
                    System.out.println("\nRODAR FOLHA DE PAGAMENTOS\n");
                    // rodar_folha_de_pagamentos();
                    break;
                case 6:
                    // System.out.println("AGENDAS DE PAGAMENTO");
                    agendas_de_pagamento();
                    break;
                case 7:
                    System.out.println("\nUNDO\n");
                    // undo();
                    break;
                case 8:
                    System.out.println("\nREDO\n");
                    // redo();
                    break;
                default:
                    System.out.println("\nOPÇÃO INVÁLIDA!\n");
            }
        }

    } // Fim da Main


    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    /* ----------------------------------- FUNCIONÁRIOS ----------------------------------- */
    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */

    // Apenas direciona para as devidas funções de acordo com a entrada do usuário
    private static void funcionarios() {
        while(true) {
            menus(1);
            Scanner input = new Scanner(System.in);
            int opc = input.nextInt();
            switch(opc) {
                case 1:
                    // System.out.println("\nADICIONAR FUNCIONÁRIO\n");
                    adicionar_funcionario();
                    break;
                case 2:
                    // System.out.println("\nREMOVER FUNCIONÁRIO\n");
                    remover_funcionario();
                    break;
                case 3:
                    // System.out.println("\nEDITAR FUNCIONÁRIO\n");
                    editar_funcionario(-1);
                    break;
                case 4:
                    // System.out.println("\nLISTAR FUNCIONÁRIOS\n");
                    listar_funcionario();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("\nOPÇÃO INVÁLIDA!\n");
            }
        }
    } // Fim de "funcionarios"

    // Adiciona os novos funcionários
    private static void adicionar_funcionario() {
        Scanner input = new Scanner(System.in);

        String entrada;
        System.out.print("\nNome: ");
        entrada = input.nextLine();
        funcs[indice_funcs][0] = entrada;

        System.out.print("Endereço: ");
        entrada = input.nextLine();
        funcs[indice_funcs][1] = entrada;

        System.out.println("Tipo de funcionário: ");
        System.out.println("1 - Horário");
        System.out.println("2 - Assalariado");
        System.out.println("3 - Comissionado");
        System.out.print("--> ");
        entrada = input.nextLine();
        funcs[indice_funcs][2] = entrada;

        if(entrada.equals("1")) {
            System.out.print("Valor da hora: R$");
            String valor_hora = input.nextLine();
            funcs[indice_funcs][3] = valor_hora;
            funcs[indice_funcs][4] = "-1";
            funcs[indice_funcs][5] = "-1";
        } else if(entrada.equals("2")) {
            System.out.print("Salário: R$");
            String salario = input.nextLine();
            funcs[indice_funcs][3] = "-1";
            funcs[indice_funcs][4] = salario;
            funcs[indice_funcs][5] = "-1";
        } else if(entrada.equals("3")) {
            System.out.print("Salário: R$");
            String salario = input.nextLine();
            System.out.print("Comissão (sem '%'): ");
            String comissao = input.nextLine();
            funcs[indice_funcs][3] = "-1";
            funcs[indice_funcs][4] = salario;
            funcs[indice_funcs][5] = comissao;
        }

        funcs[indice_funcs][6] = "s";

        System.out.print("Pertence a Sindicato (s/n): ");
        entrada = input.nextLine();
        funcs[indice_funcs][7] = entrada;

        if(entrada.equals("s")) {
            System.out.print("Taxa do Sindicato: R$");
            String taxa_sindicato = input.nextLine();
            funcs[indice_funcs][8] = taxa_sindicato;
        } else {
            funcs[indice_funcs][8] = "-1";
        }

        funcs[indice_funcs][9] = Integer.toString(indice_funcs + 1);

        indice_funcs++;

    } // Fim de "adicionar_funcionario"

    // Remove o funcionário escolhido pelo usuário
    private static void remover_funcionario() {
        int indice_funcionario = localizar_funcionario();
        if(indice_funcionario == -1) {
            System.out.println("\nUsuário não encontrado!");
            return;
        }

        System.out.println("\nUsuário localizado!\n");
        int id = Integer.parseInt(funcs[indice_funcionario][9]);
        System.out.printf("ID: %d\n", id);
        System.out.printf("Nome: %s\n", funcs[indice_funcionario][0]);

        System.out.print("\nConfirmar remoção de funcionário? (s/n) ");
        Scanner input = new Scanner(System.in);
        String confirmar = input.nextLine();
        if(confirmar.equals("s")) {
            funcs[indice_funcionario][6] = "n";
        }

    } // Fim de "remover_funcionario"

    // Edita todas as informações de um determinado funcionário escolhido pelo usuário
    private static void editar_funcionario(int func) {
        int indice_funcionario;
        if(func == -1) {
            indice_funcionario = localizar_funcionario();
        } else {
            indice_funcionario = func;
        }

        imprimir_funcionario(indice_funcionario);

        System.out.println("\nO que você deseja editar?");
        System.out.println("1 - Nome");
        System.out.println("2 - Endereço");
        System.out.println("3 - Tipo de Funcionário");
        System.out.println("4 - Sindicato");
        System.out.print("--> ");
        Scanner input_opc = new Scanner(System.in);
        int opcao = input_opc.nextInt();

        Scanner input = new Scanner(System.in);
        switch(opcao) {
            case 1:
                System.out.print("\nNovo nome: ");
                String novo_nome = input.nextLine();
                funcs[indice_funcionario][0] = novo_nome;

                System.out.println("\nNome alterado com sucesso!");
                break;
            case 2:
                System.out.print("\nNovo endereço: ");
                String novo_endereco = input.nextLine();
                funcs[indice_funcionario][1] = novo_endereco;

                System.out.println("\nEndereço alterado com sucesso!");
                break;
            case 3:
                System.out.println("\nNovo tipo de funcionário:");
                System.out.println("1 - Horário");
                System.out.println("2 - Assalariado");
                System.out.println("3 - Comissionado");
                System.out.print("--> ");
                String novo_tipo = input.nextLine();
                funcs[indice_funcionario][2] = novo_tipo;
                if(novo_tipo.equals("1")) {
                    System.out.print("Novo valor da hora: R$");
                    String valor_hora = input.nextLine();
                    funcs[indice_funcionario][3] = valor_hora;
                    funcs[indice_funcionario][4] = "-1";
                    funcs[indice_funcionario][5] = "-1";
                } else if(novo_tipo.equals("2")) {
                    System.out.print("Novo salário: R$");
                    String salario = input.nextLine();
                    funcs[indice_funcionario][3] = "-1";
                    funcs[indice_funcionario][4] = salario;
                    funcs[indice_funcionario][5] = "-1";
                } else if(novo_tipo.equals("3")) {
                    System.out.print("Novo salário: R$");
                    String salario = input.nextLine();
                    System.out.print("Nova comissão (sem '%'): ");
                    String comissao = input.nextLine();
                    funcs[indice_funcionario][3] = "-1";
                    funcs[indice_funcionario][4] = salario;
                    funcs[indice_funcionario][5] = comissao;
                }

                System.out.println("\nTipo de funcionário alterado com sucesso!");
                break;
            case 4:
                System.out.print("\nPertence a sindicato? (s/n) ");
                String sindicato = input.nextLine();
                if(sindicato.equals("s")) {
                    Scanner in = new Scanner(System.in);
                    funcs[indice_funcionario][7] = "s";
                    System.out.print("Taxa de sindicato: R$");
                    String taxa_sindicato = in.nextLine();
                    funcs[indice_funcionario][8] = taxa_sindicato;
                } else {
                    funcs[indice_funcionario][7] = "n";
                    funcs[indice_funcionario][8] = "-1";
                }

                System.out.println("\nSindicato alterado com sucesso!");
                break;
        }

        Scanner in = new Scanner(System.in);
        System.out.print("\nDeseja editar algo mais? (s/n) ");
        String continuar = in.nextLine();

        if(continuar.equals("s")) {
            editar_funcionario(indice_funcionario);
            return;
        }

    } // Fim de "editar_funcionario"

    // Imprime as informações de um funcionário específico
    private static void imprimir_funcionario(int indice_funcionario) {
        int id = Integer.parseInt(funcs[indice_funcionario][9]);
        System.out.printf("\nID: %d\n", id);
        System.out.printf("Nome: %s\n", funcs[indice_funcionario][0]);
        System.out.printf("Endereço: %s\n", funcs[indice_funcionario][1]);
        System.out.print("Tipo de funcionário: ");
        if(funcs[indice_funcionario][2].equals("1")) {
            System.out.println("Horário");
            float valor_hora = Float.parseFloat(funcs[indice_funcionario][3]);
            System.out.printf("Valor da hora: R$%.2f\n", valor_hora);
        } else if(funcs[indice_funcionario][2].equals("2")) {
            System.out.println("Assalariado");
            float salario = Float.parseFloat(funcs[indice_funcionario][4]);
            System.out.printf("Salário: R$%.2f\n", salario);
        } else {
            System.out.println("Comissionado");
            float salario = Float.parseFloat(funcs[indice_funcionario][4]);
            System.out.printf("Salário: R$%.2f\n", salario);
            float comissao = Float.parseFloat(funcs[indice_funcionario][5]);
            System.out.printf("Comissão: %.1f%%\n", comissao);
        }
        System.out.print("Pertence a sindicato: ");
        if(funcs[indice_funcionario][7].equals("s")) {
            System.out.println("Sim");
            System.out.printf("Taxa do sindicato: R$%s\n", funcs[indice_funcionario][8]);
        } else {
            System.out.println("Não");
        }
    } // Fim de "imprimir_funcionario"

    // Lista todas as informações de todos os funcionários
    private static void listar_funcionario() {
        for(int i = 0; i < indice_funcs; i++) {
            if(funcs[i][6].equals("s")) {
                System.out.printf("\nID: %d\n", Integer.parseInt(funcs[i][9]));
                System.out.printf("Nome: %s\n", funcs[i][0]);
                System.out.printf("Endereço: %s\n", funcs[i][1]);
                System.out.print("Tipo de funcionário: ");
                if(funcs[i][2].equals("1")) {
                    System.out.println("Horário");
                    float valor_hora = Float.parseFloat(funcs[i][3]);
                    System.out.printf("Valor da hora: R$%.2f\n", valor_hora);
                } else if(funcs[i][2].equals("2")) {
                    System.out.println("Assalariado");
                    float salario = Float.parseFloat(funcs[i][4]);
                    System.out.printf("Salário: R$%.2f\n", salario);
                } else {
                    System.out.println("Comissionado");
                    float salario = Float.parseFloat(funcs[i][4]);
                    System.out.printf("Salário: R$%.2f\n", salario);
                    float comissao = Float.parseFloat(funcs[i][5]);
                    System.out.printf("Comissão: %.1f%%\n", comissao);
                }
                System.out.print("Pertence a sindicato: ");
                if(funcs[i][7].equals("s")) {
                    System.out.println("Sim");
                    System.out.printf("Taxa do sindicato: R$%s\n", funcs[i][8]);
                } else {
                    System.out.println("Não");
                }
            }
        }
    } // Fim de "listar_funcionario"

    // Localiza no sistema o funcionário pelo ID e retorna seu índice
    private static int localizar_funcionario() {

        Scanner input = new Scanner(System.in);
        System.out.print("\nInsira o ID do funcionário: ");
        int id = input.nextInt();
        if(id <= indice_funcs) {
            return id - 1; // Retorna o índice do funcionário no array
        } else {
            return -1; // Não encontrou o empregado
        }

    } // Fim de "localizar_empregado"


    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    /* ----------------------------------- CARTÃO PONTO ----------------------------------- */
    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    private static void cartao_ponto() {
        while(true) {
            menus(2);
            Scanner input = new Scanner(System.in);
            int opc = input.nextInt();
            switch(opc) {
                case 1:
                    // System.out.println("\nLANÇAR CARTÃO PONTO\n");
                    entrada_cartao_ponto();
                    break;
                case 2:
                    saida_cartao_ponto();
                    break;
                case 3:
                    // System.out.println("\nLISTAR CARTÕES PONTO\n");
                    listar_cartoes_ponto();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("\nOPÇÃO INVÁLIDA!\n");
            }
        }
    } // Fim de "cartao_ponto"

    private static void entrada_cartao_ponto() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nInsira o ID do funcionário: ");
        String id = input.nextLine();

        if(indice_cartoes_ponto != 0) {
            for(int i = 0; i < indice_cartoes_ponto; i++) {
                if(cartoes_ponto[i][0].equals(id)) {
                    System.out.println("\nFuncionário já bateu ponto de entrada!");
                    return;
                }
            }
        }

        cartoes_ponto[indice_cartoes_ponto][0] = id;

        System.out.print("Data de entrada (dd/mm/aa): ");
        String data_entrada = input.nextLine();
        cartoes_ponto[indice_cartoes_ponto][1] = data_entrada;

        System.out.print("Hora de entrada (hh:mm): ");
        String hora_entrada = input.nextLine();
        cartoes_ponto[indice_cartoes_ponto][2] = hora_entrada;

        cartoes_ponto[indice_cartoes_ponto][3] = "-1";
        cartoes_ponto[indice_cartoes_ponto][4] = "-1";

        indice_cartoes_ponto++;
    } // Fim de "entrada_cartao_ponto"

    private static void saida_cartao_ponto() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nInsira o ID do funcionário: ");
        String id = input.nextLine();

        for(int i = 0; i < indice_cartoes_ponto; i++) {
            if(cartoes_ponto[i][0].equals(id) && cartoes_ponto[i][3].equals("-1")) {
                System.out.print("Data de saída (dd/mm/aa): ");
                String data_saida = input.nextLine();
                cartoes_ponto[i][3] = data_saida;

                System.out.print("Hora de saída (hh:mm): ");
                String hora_saida = input.nextLine();
                cartoes_ponto[i][4] = hora_saida;
                break;
            } else {
                System.out.println("\nFuncionário já bateu o ponto de saída!");
                break;
            }
        }
    } // Fim de "saida_cartao_ponto"

    private static void listar_cartoes_ponto() {
        for(int i = 0; i < indice_cartoes_ponto; i++) {
            System.out.printf("\nID: %s\n", cartoes_ponto[i][0]);
            int id = Integer.parseInt(cartoes_ponto[i][0]);
            System.out.printf("Nome: %s\n", funcs[id - 1][0]);
            System.out.printf("Data de entrada: %s\n", cartoes_ponto[i][1]);
            System.out.printf("Hora de entrada: %s\n", cartoes_ponto[i][2]);

            if(!cartoes_ponto[i][3].equals("-1")) {
                System.out.printf("Data de saída: %s\n", cartoes_ponto[i][3]);
                System.out.printf("Hora de saída: %s\n", cartoes_ponto[i][4]);
            }
        }
    } // Fim de "listar_cartoes_ponto"


    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    /* -------------------------------------- VENDAS -------------------------------------- */
    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    private static void vendas() {
        while(true) {
            menus(3);
            Scanner input = new Scanner(System.in);
            int opc = input.nextInt();
            switch(opc) {
                case 1:
                    // System.out.println("\nLANÇAR VENDA\n");
                    lancar_venda();
                    break;
                case 2:
                    // System.out.println("\nLISTAR VENDAS\n");
                    listar_vendas();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("\nOPÇÃO INVÁLIDA!\n");
            }
        }
    } // Fim de "vendas"

    private static void lancar_venda() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nInsira o ID do Funcionário: ");
        String id = input.nextLine();
        vendas_realizadas[indice_vendas_realizadas][0] = id;

        System.out.print("Valor da venda: R$");
        String valor = input.nextLine();
        vendas_realizadas[indice_vendas_realizadas][1] = valor;

        System.out.print("Data da venda (dd/mm/aa): ");
        String data = input.nextLine();
        vendas_realizadas[indice_vendas_realizadas][2] = data;

        indice_vendas_realizadas++;
    } // Fim de "lançar_venda"

    private static void listar_vendas() {
        for(int i = 0; i < indice_vendas_realizadas; i++) {
            int id = Integer.parseInt(vendas_realizadas[i][0]);
            System.out.printf("\nID: %d\n", id);
            System.out.printf("Nome: %s\n", funcs[id - 1][0]);
            float valor = Float.parseFloat(vendas_realizadas[i][1]);
            System.out.printf("Valor da venda: R$%.2f\n", valor);
            System.out.printf("Data da venda: %s\n", vendas_realizadas[i][2]);
        }
    } // Fim de "listar_vendas"

    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    /* --------------------------------- TAXA DE SERVIÇOS --------------------------------- */
    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    private static void taxa_de_servico() {
        while(true) {
            menus(4);
            Scanner input = new Scanner(System.in);
            int opc = input.nextInt();
            switch(opc) {
                case 1:
                    // System.out.println("\nLANÇAR TAXA DE SERVIÇO\n");
                    lancar_taxa_de_servico();
                    break;
                case 2:
                    // System.out.println("\nLISTAR TAXAS DE SERVIÇO\n");
                    listar_taxas_de_servico();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("\nOPÇÃO INVÁLIDA!\n");
            }
        }
    } // Fim de "taxa_de_servico"

    private static void lancar_taxa_de_servico() {
        Scanner input = new Scanner(System.in);
        System.out.print("\nInsira o ID do funcionário: ");
        String id = input.nextLine();
        taxas_de_servicos[indice_taxas_de_servicos][0] = id;
        System.out.print("Valor da taxa: R$");
        String valor_taxa = input.nextLine();
        taxas_de_servicos[indice_taxas_de_servicos][1] = valor_taxa;

        indice_taxas_de_servicos++;
    } // Fim de "lancar_taxa_de_servico"

    private static void listar_taxas_de_servico() {
        for(int i = 0; i < indice_taxas_de_servicos; i++) {
            System.out.printf("\nID: %s\n", taxas_de_servicos[i][0]);
            int id = Integer.parseInt(taxas_de_servicos[i][0]);
            System.out.printf("Nome: %s\n", funcs[id - 1][0]);
            float valor = Float.parseFloat(taxas_de_servicos[i][1]);
            System.out.printf("Valor da taxa: R$%.2f\n", valor);
        }
    } // Fim de "listar_taxas_de_servico"

    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------- AGENDAS DE PAGAMENTO ------------------------------- */
    /* ------------------------------------------------------------------------------------ */
    /* ------------------------------------------------------------------------------------ */
    private static void agendas_de_pagamento() {
        while(true) {
            menus(6);
            Scanner input = new Scanner(System.in);
            int opc = input.nextInt();
            switch(opc) {
                case 1:
                    System.out.println("\nLISTAR AGENDAS DE PAGAMENTO\n");
                    // listar_agendas_de_pagamento();
                    break;
                case 2:
                    System.out.println("\nCADASTRAR AGENDAS DE PAGAMENTO\n");
                    // cadastrar_agendas_de_pagamento();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("\nOPÇÃO INVÁLIDA!\n");
            }
        }
    } // Fim de "agendas_de_pagamento"

} // Fim da Classe Main
